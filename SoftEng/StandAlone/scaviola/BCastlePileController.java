package scaviola;

import ks.common.view.RowView;
import ks.common.model.Pile;
import ks.common.model.Move;
import ks.common.view.Widget;
import ks.common.model.Column;
import ks.common.view.Container;
import ks.common.view.PileView;

import java.awt.event.MouseEvent;
/**
 * Controls all actions to do with mouse events over the Pile widget.
 * In this case, cards will only be released over a Pile, never picked up, so
 * we are only responding to mouse released events.
 * <p>
 * Creation date: (12/3/01 6:30 PM)
 * @author: Mike Scaviola (scaviola@wpi.edu)
 */
public class BCastlePileController extends java.awt.event.MouseAdapter {
	
	/** Which widget generated the source of this action. */
	protected PileView src;
	
	/** The BCastle Solitaire Game. */
	protected BCastle theGame;
/**
 * BCastlePileController knows of the source Pile where card came from
 */
public BCastlePileController(BCastle theGame, PileView src) {
	super();

	this.src = src;
	this.theGame = theGame;
}
/**
 * Respond to mouse released events.
 * Creation date: (10/27/01 5:46:55 PM)
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
		System.err.println ("BCastleRowController::mouseReleased(): somehow ColumnView model element is null.");
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
	Pile toPile = (Pile) src.getModelElement();

	// Try to make the move
	Move m = new MoveCardFoundationMove (fromRow, col, toPile, col.count());
	if (m.doMove (theGame)) {
		// SUCCESS
		theGame.pushMove(m);
	} else {
		dragSource.returnWidget (rowView);
	}

	// Since we could be released over a widget, or over the container, we must repaintAll() clipped to
	// the region we are concerned about.
	theGame.repaintAll (rowView.getBounds());    // first refresh the last known position of the card being dragged.
	theGame.refreshWidgets();					 // then the widgets.

	// release the dragging object, and reset fromRowView
	c.releaseDraggingObject();
}
}
