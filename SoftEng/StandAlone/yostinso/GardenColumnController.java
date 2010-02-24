package yostinso;

import java.awt.event.MouseEvent;

import ks.common.model.Column;
import ks.common.model.Element;
import ks.common.model.Move;
import ks.common.view.ColumnView;
import ks.common.view.Container;
import ks.common.view.Widget;


/**
 * Controller class for the GardenColumnView
 * Creation date: (11/29/2001 4:41:24 PM)
 * @author: E.O. Stinson (revised for KS 1.8 by heineman@cs.wpi.edu)
 */
public class GardenColumnController extends java.awt.event.MouseAdapter {
	protected GardenColumnView src; // Store the source view
	protected FlowerGarden theGame; // And the parent game
/**
 * GardenColumnController constructor comment.
 */
public GardenColumnController(FlowerGarden theGame, GardenColumnView bpv) {
	super();
	this.theGame = theGame;
	this.src = bpv;
}
public void mouseClicked(MouseEvent me) {
	// If the user doubleclicks on a card
	if (me.getClickCount() > 1) {
		// Get the container
		Container c = theGame.getContainer();  // Get the container
		
		// Get the GardenColumn model element for the GardenColumnView we clicked on
		Column theColumn = (Column) src.getModelElement();

		// Make sure the GardenColumn isn't empty
		if (theColumn.count() == 0)
			return;
		
		// Get the selected cards from the Column
		ColumnView columnView = src.getColumnViewForSelectedCards(me); // Get the card

		// Make sure that some cards were selected
		if (columnView == null)
			return;

		// Move the card
		Move m = new MoveGCSPMove((Column) src.getModelElement(),(Column) columnView.getModelElement(), (SuitPile) null);
		if (m.doMove (theGame)) {
			// SUCCESS! Store move for undo
			theGame.pushMove(m);
		} else {
			// otherwise, put the card back where it came from
			src.returnWidget(columnView);
		}

		theGame.repaintAll (columnView.getBounds());     // first refresh the last known position of the card being dragged.
		theGame.refreshWidgets();				       // then the widgets.
	}
}
public void mousePressed(MouseEvent me) {
	
	// The container manages several critical pieces of information; namely, it
	// is responsible for the draggingObject; in our case, this would be a CardView
	// Widget managing the card we are trying to drag between two piles.
	Container c = theGame.getContainer(); 

	/** Return if there is no card to be chosen. */
	Column theColumn = (Column) src.getModelElement();
	if (theColumn.count() == 0) {
		c.releaseDraggingObject();
		return;
	}

	// Getthe top card from the row. Note that this method will alter the model
	ColumnView columnView = src.getColumnViewForSelectedCards(me);

	// an invalid selection (not the top card selected...)
	if (columnView == null) {
		c.releaseDraggingObject();
		return;
	}
	
	// If we get here, then the user has indeed clicked on the top card in the GardenColumn and
	// we are able to now move it on the screen at will. For smooth action, the bounds for the
	// cardView widget reflect the original card location on the screen. (maybe)
	Widget w = c.getActiveDraggingObject();
	if (w != Container.getNothingBeingDragged()) {
		System.err.println ("RowController::mousePressed(): Unexpectedly encountered a Dragging Object during a Mouse press.");
		return;
	}

	// Tell container which object is being dragged, and where in that widget the user clicked.
	c.setActiveDraggingObject (columnView, me);
	
	// Have container keep track of source widget that initiated the drag.
	c.setDragSource(src);

	src.redraw();
}
public void mouseReleased(MouseEvent me) {
	Container c = theGame.getContainer();

	/** Return if there is no card being dragged chosen. */
	Widget w = c.getActiveDraggingObject();
	if (w == Container.getNothingBeingDragged()) return;
	
	/** Must be the ColumnView widget (or else!). */
	ColumnView columnView = (ColumnView) w;
	if (columnView == null) {
		System.err.println ("GardenColumnController::mouseReleased(): somehow ColumnView model element is null.");
		return;
	}

	/** Recover the From element */
	Widget fromW = c.getDragSource();
	if (fromW == null) { 
		System.err.println ("GardenColumnController::mouseReleased(): somehow the source widget is broken.");
		return;
	}

	Element sourceElement = null;
	// Now find the source element, to pass along to our move
	if(fromW instanceof GardenColumnView) {
		// Find source GardenColumn
		GardenColumnView fromGardenColumnView = (GardenColumnView) fromW;
		sourceElement = (Column) fromGardenColumnView.getModelElement();
	} else { // if (fromW instanceof GardenRowView) 
		// Find source GardenRow
		GardenRowView fromGardenRowView = (GardenRowView) fromW;
		sourceElement = (GardenRow) fromGardenRowView.getModelElement();
	}

	// Find the target GardenColumn
	Column targetGardenColumn = (Column) src.getModelElement();
	// Get the cards from it
	Column theColumn = (Column) columnView.getModelElement();
	
	// Let's verify the move
	if (sourceElement instanceof Column) {
		Column sourceGardenColumn = (Column) sourceElement;

		Move m = new MoveGCGCMove (sourceGardenColumn, theColumn, targetGardenColumn, theColumn.count());
		if (m.doMove (theGame)) {
			// SUCCESS! Add to our game.
			theGame.pushMove (m);
		} else {
			// Invalid move. Restore to original column. NO MOVE MADE
			fromW.returnWidget(w);
		}
		
	} else if (sourceElement instanceof GardenRow) {
		GardenRow sourceGardenRow = (GardenRow) sourceElement;

		Move m = new MoveGRGCMove(sourceGardenRow, theColumn, targetGardenColumn, sourceGardenRow.getLastSelectionPoint());
		if (m.doMove (theGame)) {
			// SUCCESS! add to our set.
			theGame.pushMove(m);
		} else {
			fromW.returnWidget(w);
		}
	} 
	// Since we could be released over a widget, or over the container, we must repaintAll() clipped to
	// the region we are concerned about.
	theGame.repaintAll (columnView.getBounds());     // first refresh the last known position of the card being dragged.
	theGame.refreshWidgets();				       // then the widgets.

	// release the dragging object, (container will reset dragSource)
	c.releaseDraggingObject();
}
}
