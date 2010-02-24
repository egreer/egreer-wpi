package kdaquila;

import java.awt.event.MouseEvent;

import ks.common.model.BuildablePile;
import ks.common.model.Column;
import ks.common.model.Move;
import ks.common.model.Stack;
import ks.common.view.BuildablePileView;
import ks.common.view.ColumnView;
import ks.common.view.Container;
import ks.common.view.Widget;

/**
 * Controls all actions to do with mouse events over the BuildablePileView widget.
 * @author: Kevin J. D'Aquila (kdaquila@wpi.edu)
 */
public class SpiderBuildablePileController extends java.awt.event.MouseAdapter {
	
	/** Which widget generated the source of this action. */
	protected BuildablePileView src;
	
	/** The Spider Solitaire Game. */
	protected Spider theGame;

/** SpiderBuildablePileController knows of the source pile where card came from */
public SpiderBuildablePileController(Spider theGame, BuildablePileView srcBPV)
{
	super();

	this.src = srcBPV;
	this.theGame = theGame;
}
/**
 * Do we have a complete stack?  This is a mini-controller of sorts to handle complete stack
 * @param ks.games.Solitaire theGame
 */
private void checkIfComplete()
{

	BuildablePile tempPile = (BuildablePile)src.getModelElement();
	// Return if we don't have enough cards
	if (tempPile.getNumFaceUp() < 13) return;
	
	tempPile.select(tempPile.getNumFaceUp());
	Stack tempStack = tempPile.getSelected();

	if (tempStack.descending() && tempStack.sameSuit())
	{
		// Try to make a completeStack move
		Move m = new CompleteStackMove(tempStack, tempPile, theGame);
		if (m.doMove(theGame))
		{   // SUCCESS
			theGame.pushMove(m);
		} else
		{   // DOH, return
			// ??
		}
	} else tempPile.push(tempStack);

		
}
/**
 * Coordinate reaction to the beginning of a Drag Event.
 * <p>
 * @param mouseE java.awt.event.MouseEvent
 */
public void mousePressed(MouseEvent mouseE) {

	// Get the all-important container
	Container c = theGame.getContainer();
	
	// Return if there is no card to be chosen.
	BuildablePile thePile = (BuildablePile) src.getModelElement();
	if (thePile.count() == 0) {
		return;
	}

	// Get the columnView corresponding to our mouse event (top card bunch selected)
	ColumnView columnView = src.getColumnView(mouseE);

	// Return if it's not valid
	if (columnView == null) return;
	
	Column column = (Column)columnView.getModelElement();
	int count = column.count();

	// Call the private isDescending() method to check if this is a valid column
	if (count > 1)
	{
		// If it's not valid, release and return
		if (!column.sameSuit() || !column.descending())
		{
			thePile.push(column);
			return;
		}
	}

	// If we get to this point, then the user has clicked on a valid card or bunch of cards.
	// Get the active dragging object so we can move this around the screen
	Widget w = c.getActiveDraggingObject();
	if (w != Container.getNothingBeingDragged()) {
		System.err.println("SpiderBuildablePileController::mousePressed(): Unexpectedly encountered a Dragging Object during a Mouse press.");
		return;
	}

	// Tell container which object is being dragged, and where in that widget the user clicked.
	c.setActiveDraggingObject(columnView, mouseE);
	
	// We ask the Container to remember where the drag originates, so if need be, we can undo or restore later.
	c.setDragSource(src);

	// Redraw our source pile to avoid flicker
	src.redraw();

	columnView.paint (c.getGraphics());
}
/**
 * Coordinate reaction to the end of a Drag Event.
 * <p>
 * @param me java.awt.event.MouseEvent
 */
public void mouseReleased(MouseEvent me) {
	Container c = theGame.getContainer();

	// Return if there is nothing being dragged
	Widget w = c.getActiveDraggingObject();
	if (w == Container.getNothingBeingDragged()) {
		c.releaseDraggingObject();
		return;
	}
	
	// Must be the columnView widget
	ColumnView columnView = (ColumnView) w;
	Column theColumn = (Column)columnView.getModelElement();
	if (theColumn == null) {
		System.err.println("SpiderBuildablePileController::mouseReleased(): somehow ColumnView model element is null.");
		return;
	}

	// Recover the fromBuildablePileView
	BuildablePileView fromBuildablePileView = (BuildablePileView) c.getDragSource();
	if (fromBuildablePileView == null) {
		System.err.println("SpiderBuildablePileController::mouseReleased(): somehow dragSource for container is null.");
		return;
	}
	
	BuildablePile fromBuildablePile = (BuildablePile) fromBuildablePileView.getModelElement();

	// Determine the To Pile
	BuildablePile toBuildablePile = (BuildablePile) src.getModelElement();

	// Dragged onto itself, simply return it!
	if (fromBuildablePileView == src)
	{
		toBuildablePile.push(theColumn);
		theColumn.removeAll();
	}
	
	int count = theColumn.count();
	if (count == 1)
	{
		// Try to make the move
		Move m = new MoveCardMove(fromBuildablePile, theColumn.peek(), toBuildablePile);
		if (m.doMove(theGame))
		{   // SUCCESS
			theGame.pushMove(m);
			checkIfComplete();
		} else
		{   // DOH, return
			fromBuildablePile.push(theColumn);
		}
	}
	
	if (count > 1)
	{
		// Try to make the move
		Move m = new MoveBunchMove(fromBuildablePile, theColumn, toBuildablePile);
		if (m.doMove (theGame))
		{   // SUCCESS
			theGame.pushMove(m);
			checkIfComplete();
		} else
		{   // DOH, return
			fromBuildablePile.push(theColumn);
		}
	}
	
	// Since we could be released over a widget, or over the container, we must repaintAll() clipped to
	// the region we are concerned about.
	theGame.repaintAll (columnView.getBounds()); // first refresh the last known position of the column being dragged.
	theGame.refreshWidgets();					 // then the widgets.

	// release the dragging object (this will reset container's dragSource).
	c.releaseDraggingObject();
}
}
