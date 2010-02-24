package scaviola;

import ks.common.view.Widget;
import ks.common.view.Container;
/**
 * Represents the handler to deal with mouse release events in the container's space
 *
 * Creation date: (10/28/01 9:12:34 AM)
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 * Modify date: (12/3/01 8:30 PM)
 * Modified by: Mike Scaviola (scaviola@wpi.edu)
 */
public class BCastleReleasedAdapter extends java.awt.event.MouseAdapter {
	/** The BCastle game. */
	protected BCastle theGame = null;
/**
 * BCastleReleasedAdapter constructor comment.
 */
public BCastleReleasedAdapter(BCastle theGame) {
	super();

	this.theGame = theGame;
}
/**
 * React to mouse release events by sending 'null' as the toPile
 *
 * Creation date: (10/28/01 9:14:15 AM)
 * @param me java.awt.event.MouseEvent
 */
public void mouseReleased(java.awt.event.MouseEvent me) {
	Container c = theGame.getContainer();

	/** Return if there is no card being dragged chosen. */
	Widget draggingWidget = c.getActiveDraggingObject();
	if (draggingWidget == Container.getNothingBeingDragged()) return;
	
	/** Recover the From BuildablePile OR Waste Pile */
	Widget fromWidget = c.getDragSource();
	if (fromWidget == null) {
		c.releaseDraggingObject();		
		return;
	}

	// Simply return the widget to where it came from.
	fromWidget.returnWidget (draggingWidget);

	// Since we could be released over a widget, or over the container, we must repaintAll() clipped to
	// the region we are concerned about.
	theGame.repaintAll (draggingWidget.getBounds());   // first refresh the last known position of the card being dragged.
	theGame.refreshWidgets();				           // then the widgets.

	// release the dragging object, (container will reset dragSource).
	c.releaseDraggingObject();
}
}
