package scaviola;

import ks.common.model.Move;
import ks.common.model.Column;
import ks.common.view.Widget;
import ks.common.view.Container;
import ks.common.view.RowView;

import java.awt.event.MouseEvent;
/**
 * Controlls all actions to do with mouse events over the Row widget.
 *
 * Creation date: (12/3/01 7:30 PM)
 * @author: Mike Scaviola (scaviola@wpi.edu)
 */
public class BCastleRowController extends java.awt.event.MouseAdapter {
	
	/** Which widget generated the source of this action. */
	protected RowView src;
	
	/** The BCastle Solitaire Game. */
	protected BCastle theGame;
/**
 * BCastleRowController knows of the source Row where card came from
 */
public BCastleRowController(BCastle theGame, RowView src) {
	super();

	this.src = src;
	this.theGame = theGame;
}
/**
 * Respond to mouse click events.
 * Creation date: (10/27/01 5:46:55 PM)
 * @param me java.awt.event.MouseEvent
 */
public void mouseClicked(MouseEvent me) {
	theGame.clickCardController (src, me);
}
/**
 * Respond to mouse press events.
 * Creation date: (10/27/01 5:46:55 PM)
 * @param me java.awt.event.MouseEvent
 */
public void mousePressed(MouseEvent me) {
	// The container manages several critical pieces of information; namely, it
	// is responsible for the draggingObject; in our case, this would be a CardView
	// Widget managing the card we are trying to drag between two rows.
	Container c = theGame.getContainer();
	
	/** Return if there is no card to be chosen. */
	Column theRow = (Column) src.getModelElement();
	if (theRow.count() == 0) {
		c.releaseDraggingObject();
		return;
	}

	// Get the top card in the row from RowView.
	// Note that this method will alter the model for columnView if the condition is met.
	RowView rv = src.getRowView(me);
	if (rv == null) {
		return; // sanity check, but should never happen.
	}

	// If we get here, then the user has indeed clicked on the top card in the RowView and
	// we are able to now move it on the screen at will. For smooth action, the bounds for the
	// cardView widget reflect the original card location on the screen.
	Widget w = c.getActiveDraggingObject();
	if (w != Container.getNothingBeingDragged()) {
		System.err.println ("BCastleRowController::mousePressed(): Unexpectedly encountered a Dragging Object during a Mouse press.");
		return;
	}

	// Tell container which object is being dragged, and where in that widget the user clicked.
	c.setActiveDraggingObject (rv, me);
	src.redraw();
	
	c.setDragSource (src);  // remember where we came from...
}
/**
 * Coordinate reaction to the conclusion of a Drag Event for when a card is released over a RowView
 * <p>
 * Creation date: (10/4/01 6:56:34 PM)
 * @param me java.awt.event.MouseEvent
 */
public void mouseReleased(MouseEvent me) {
	Container c = theGame.getContainer();

	/** Return if there is no card being dragged chosen. */
	Widget w = c.getActiveDraggingObject();
	if (w == Container.getNothingBeingDragged()) return;
	
	/** Must be the RowView widget. */
	RowView rowView = (RowView) w;
	Column col = (Column) rowView.getModelElement();
	if (col == null) {
		return;
	}

	/** Recover the From Row */
	Widget dragSource = c.getDragSource();
	if (dragSource == null) {
		System.err.println ("BCastleRowController::mouseReleased(): somehow fromRowView is null.");
		return;
	}
	Column fromRow = (Column) dragSource.getModelElement();

	/** Get destination element. */
	Column to = (Column) src.getModelElement();

	// Try to make the move
	Move m = new MoveColumnMove (fromRow, col, to, col.count());
	if (m.doMove (theGame)) {
		// SUCCESS
		theGame.pushMove(m);
	} else {
		dragSource.returnWidget (rowView);
	}

	// Since we could be released over a widget, or over the container, we must repaintAll() clipped to
	// the region we are concerned about.
	theGame.repaintAll (rowView.getBounds());   // first refresh the last known position of the card being dragged.
	theGame.refreshWidgets();					 // then the widgets.

	// release the dragging object, and reset fromRowView
	c.releaseDraggingObject();
}
}
