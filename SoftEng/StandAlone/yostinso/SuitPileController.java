package yostinso;

import java.awt.event.MouseEvent;

import ks.common.model.Column;
import ks.common.model.Element;
import ks.common.model.Move;
import ks.common.view.ColumnView;
import ks.common.view.Container;
import ks.common.view.Widget;

/**
 * Controller object for the SuitPileView
 * Creation date: (11/29/2001 10:23:20 PM)
 * @author: E.O. Stinson
 */
public class SuitPileController extends java.awt.event.MouseAdapter {
	protected SuitPileView src;
	protected FlowerGarden theGame;
/**
 * SuitPileController constructor comment.
 */
public SuitPileController(FlowerGarden theGame, SuitPileView bpv) {
	super();
	this.theGame = theGame;
	this.src = bpv;
}
public void mouseReleased(MouseEvent me) {
	Container c = theGame.getContainer();

	/** Return if there is no card being dragged chosen. */
	Widget w = c.getActiveDraggingObject();
	if (w == Container.getNothingBeingDragged()) return;
	
	/** Must be the ColumnView widget (or else!). */
	ColumnView columnView = (ColumnView) w;
	if (columnView == null) {
		System.err.println ("SuitPileController::mouseReleased(): somehow ColumnView model element is null.");
		return;
	}

	/** Recover the From element */
	Widget fromW = c.getDragSource();
	if (fromW == null) { 
		System.err.println ("SuitPileController::mouseReleased(): somehow the source widget is broken.");
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

	// Find the target SuitPile
	SuitPile targetSuitPile = (SuitPile) src.getModelElement();
	// Get the cards from the dragging thing
	Column theColumn = (Column) columnView.getModelElement();
	
	// Let's verify the move
	if (sourceElement instanceof Column) {
		Column sourceGardenColumn = (Column) sourceElement;

		Move m = new MoveGCSPMove(sourceGardenColumn, theColumn, targetSuitPile);
		if (m.doMove (theGame)) {
			// Success!
			theGame.pushMove (m);
		} else {
			// Invalid move. Restore to original column. NO MOVE MADE
			fromW.returnWidget(w);
		}
		
	} else if (sourceElement instanceof GardenRow) {
		GardenRow sourceGardenRow = (GardenRow) sourceElement;

		Move m = new MoveGRSPMove(sourceGardenRow, theColumn, targetSuitPile, sourceGardenRow.getLastSelectionPoint());
		if (m.doMove (theGame)) {
			// SUCCESS! Add to our set.
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
