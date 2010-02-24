package ks.common.controller;


import java.awt.event.MouseEvent;

import ks.common.games.Solitaire;
import ks.common.view.Container;
import ks.common.view.Widget;

/**
 * Default released adapter for use by all solitaire plug-ins.
 * <p>
 * The SolitaireReleasedAdapter is programmed to send back to the dragSource
 * any objects that were in progress when the mouse button was released over
 * the container.
 * <p>
 * @author George T. Heineman (heineman@cs.wpi.edu)
 * @since V2.0.2
 */
public class SolitaireReleasedAdapter extends java.awt.event.MouseAdapter {
	
	/** The game. */
	protected Solitaire theGame = null;
/**
 * SolitaireReleasedAdapter constructor comment.
 */
public SolitaireReleasedAdapter(Solitaire theGame) {
	super();

	this.theGame = theGame;
}
/**
 * Coordinate reaction to the completion of a Drag Event that must be canceled.
 * <p>
 * It would have been a bit of a challenge to construct the appropriate move, because cards
 * can be dragged both from the WastePile and the BuildablePileView. Since V1.6 of Kombat
 * Solitaire, a dragged widget can be returned to its originating source; this makes life
 * easier for the solitaire developer.
 * <p>
 * @param me java.awt.event.MouseEvent
 */
public void mouseReleased(MouseEvent me) {
	Container c = theGame.getContainer();

	/** Return if there is no card being dragged chosen. */
	Widget draggingWidget = c.getActiveDraggingObject(); 
	if (draggingWidget == Container.getNothingBeingDragged()) return;
	
	/** Recover from wherever it came. */
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
