package ks.common.controller;

import java.awt.Graphics;
import java.awt.Rectangle;

import ks.common.games.Solitaire;
import ks.common.view.Container;
import ks.common.view.Widget;

/**
 * Default MouseMotion Adapter for all solitaire plug-ins.
 * <p>
 * This controller will react to MouseDragged events and automatically update
 * the viewing of any widgets that are being dragged within the container by
 * using the <code>standardDragController</code> from the Solitare class.
 * <p>
 * As of Version 1.5.1, methods have been moved from the Solitaire class into the
 * controller where they belonged.
 * <p>
 * Creation date: (10/27/01 4:37:58 PM)
 * @author George T. Heineman (heineman@cs.wpi.edu)
 */
public class SolitaireMouseMotionAdapter extends java.awt.event.MouseMotionAdapter {

	/** The game being played. */
	protected Solitaire theGame = null;
	/** Rectangles used during motion. */
	protected Rectangle aRect = new Rectangle (0,0,0,0);
	/** Rectangles used during motion. */
	protected Rectangle dRect = new Rectangle (0,0,0,0);
	/** Rectangles used during motion. */
	protected Rectangle cRect = new Rectangle (0,0,0,0);
	/** Rectangles used during motion. */
	protected Rectangle bRect = new Rectangle (0,0,0,0);
/**
 * SolitaireMouseMotionAdapter constructor comment.
 */
public SolitaireMouseMotionAdapter(Solitaire theGame) {
	super();

	this.theGame = theGame;
}
/**
 * Coordinate reaction to a Drag Event.
 * <p>
 * Added code for V1.6.10 to refreshWidgets() on the first real drag event. This will ensure
 * that any changes to the source widget (causing the change) will be refreshed and drawn to
 * the screen appropriately.
 * <p>
 * Creation date: (10/27/01 4:38:53 PM)
 * @param me java.awt.event.MouseEvent
 */
public void mouseDragged(java.awt.event.MouseEvent me) {

	Container c = theGame.getContainer();
	
	/** first return if nothing is currently being dragged. */
	Widget w = c.getActiveDraggingObject();
	if (w == null) {
		// sanity check. should never happen.
		throw new IllegalArgumentException ("SolitaireMouseMotionAdapter::mouseDragged(). null active dragging object.");
	}
	if (w == Container.getNothingBeingDragged()) return;

	// Find the anchor point within the image to use as delta offsets.
	java.awt.Point anchor = c.getDraggingAnchor();

	// Refresh widgets just to be safe
	// Newly added to correctly ensure that on mousePressed -> mouseDragged
	// transitions, the modified widget will be redrawn. This must be done
	// here to avoid serious flicker when you see mousePressed -> mouseReleased ->
	// mouseClicked.
	// HEINEMAN Removed (10-11-2003). Clear causes flicker!
	//theGame.refreshWidgets();
			
	// extract the new location from the event that contains (x,y)
	
	Rectangle oldRect = new Rectangle (w.getBounds());
	w.setXY (me.getX() - anchor.x, me.getY() - anchor.y);	
	Rectangle wr = w.getBounds();
	
	// Repaint All (including background and widgets) that intersect the UNION of oldRect and w.
	Rectangle overlap = oldRect.intersection (wr);
	if (overlap.isEmpty()) {
		// totally separate. Blow away old card
		theGame.repaintAll (oldRect);
	} else {
		// Divide non-overlapped regions into a, b, c, and d.
		aRect.x=oldRect.x; aRect.width = overlap.x-aRect.x;
		aRect.y=oldRect.y; aRect.height = overlap.height;
		bRect.x=oldRect.x; bRect.width = oldRect.width;
		bRect.y=overlap.y+overlap.height; bRect.height = oldRect.height - overlap.height;
		
		cRect.x=oldRect.x; cRect.width=oldRect.width;
		cRect.y=oldRect.y; cRect.height = oldRect.height - overlap.height;
		
		dRect.x=overlap.x+overlap.width; dRect.width = oldRect.width-overlap.width;
		dRect.y=overlap.y; dRect.height=overlap.height;


//		Rectangle totalArea = oldRect.union (w.getBounds());
		//c.getGraphics().fillRect (aRect.x,aRect.y,aRect.width,aRect.height);
		theGame.repaintAll(aRect);
		
		//c.getGraphics().fillRect (bRect.x,bRect.y,bRect.width,bRect.height);
		theGame.repaintAll(bRect);
		
		theGame.repaintAll(cRect);
		theGame.repaintAll(dRect);


		dRect.x=oldRect.x; dRect.width = wr.x - oldRect.x;
		dRect.y=overlap.y; dRect.height=overlap.height;
		theGame.repaintAll(dRect);
		
//		c.getGraphics().fillRect (cRect.x,cRect.y,cRect.width,cRect.height);
//		theGame.repaintAll(cRect);
//		c.getGraphics().setColor (java.awt.Color.white);			
//		c.getGraphics().fillRect (dRect.x,dRect.y,dRect.width,dRect.height);			
//		theGame.repaintAll(dRect);
//		// If there were only some way to restrict the repainting of these widgets to *not* draw the
//		// given region. Same fore background.
//		theGame.repaintAll (totalArea);
	}
	
	// now paint dynamic one last. Make sure we dispose so we don't
	// endure memory leaks!
	Graphics g = c.getGraphics();
	w.paint (g);
	g.dispose();
}
}
