package ks.client.gamefactory.skin;


import java.awt.Rectangle;
import java.awt.Color;

import ks.common.view.Container;
import ks.common.view.RectangleHierarchy;
import ks.common.view.RectangleHierarchyVisitor;

/**
 * Manages the responsibility of filling rectangles that need refreshing
 * into a specific graphics context object using a particular color.
 * <p>
 * Default Skin for a container; simply copies a known color everywhere.
 * <p>
 * @author George T. Heineman (heineman@cs.wpi.edu)
 */
public class Psychedelic implements RectangleHierarchyVisitor, Runnable {
	
	/** My color to show. */
	protected java.awt.Color myColor;
	/** The thread to oversee the scrolling. */
	protected java.lang.Thread thread;
	/** Delay between color changing. */
	protected int delay = 50;
	/** Are we visible (assume we are initially visible). */
	protected boolean visible = true;

	/** Target graphics object. */
	protected java.awt.Graphics myGraphics;
	/** Target container. */
	protected Container container = null;
/**
 * Psychedelic constructor comment.
 * @param cont    Container in which background will be drawn
 * @param c       Color to use
 */
public Psychedelic(Container cont, java.awt.Color c) {
	if (cont == null) throw new IllegalArgumentException ("Psychadelic::Psychadelic(Container, Color) received null Container parameter.");
	if (c == null) throw new IllegalArgumentException ("Psychadelic::Psychadelic(Container, Color) received null Color parameter.");

	myColor = c;
	container = cont;
	myGraphics = container.getGraphics();

	// get thread going  
	thread = new Thread (this);
	thread.start();	
}
/**
 * Detect if we are visible.
 * Creation date: (1/5/02 12:49:03 PM)
 * @return boolean
 */
public boolean isVisible() {
	return visible;
}
/**
 * Returns a number from (-width/2) .. 0 .. (width/2)
 */

protected int randomOffset (int width) {
	double d = Math.random();
	int rn = (int) (width * d);  // 0 .. 
	return rn - (width/2);
}
/**
 * Thread will operate by pausing n milliseconds and then checking if container is in 
 * a drag. Once it is verified that we can scroll, we do so, and the entire container
 * background is redrawn.
 */
public void run() {

	while (visible) {
		// Sleep for pre-determined delay
		try {
			Thread.sleep(delay);
		} catch (InterruptedException ie) {
			// nothing real that we can do here...
		}

		// if container is dragging, no changes...
		if (container.getLastDrag() != null) continue;

		// alter the color in some random direction
		int r = myColor.getRed();
		int g = myColor.getGreen();
		int b = myColor.getBlue();

		// perturb in random amounts
		r = (r + randomOffset(25));
		if (r < 0) r = 0;
		g = (g + randomOffset(25));
		if (g < 0) g = 0;
		b = (b + randomOffset(25));
		if (b < 0) b = 0; 
		myColor = new Color (r%256,g%256,b%256);

		// update the background
		container.repaintBackground();
	}
}
/**
 * Set the time delay between image scrolling.
 * @param newDelay int
 */
public void setDelay(int newDelay) {
	if (newDelay < 0) {
		throw new IllegalArgumentException ("Psychadelic::setDelay() received delay time < 0.");
	}
	
	delay = newDelay;
}
/**
 * Visitor's chance to release resources once done.
 * <p>
 * @param visible boolean
 */
public void setVisible(boolean visible) {
	this.visible = visible;
}
/**
 * Copy the appropriate image as based by this node's value. 
 * <p>
 * @param node
 */
public void visit (RectangleHierarchy node) {
	// reuse more general method.
	visit (node, null);
}
/**
 * Copy the appropriate image as based by this node's value. Use clip
 * as a truncating clipping region.
 * <p>
 * clip is unaffected by this method.
 * <p>
 * @param node
 * @param clip
 */
public void visit (RectangleHierarchy node, Rectangle clip) {

	// If there are any children, we must move on because drawing only
	// occurs on leaf nodes.
	RectangleHierarchy rh[] = node.getChildren();
	if (rh != null) {
		for (int i = 0; i < rh.length; i++) {
			if (rh[i] != null) {
				rh[i].accept (this);
			}
		}
		return;
	}

	// to get here, we must be a leaf node. Calculate bounds
	Rectangle bounds = node.getBounds();
	if (clip != null) {
		bounds = bounds.intersection (clip);
	}

	// return if no overlap
	if (bounds.isEmpty()) return;

	// perform operation (fillRect)
	Color oldColor = myGraphics.getColor(); 
	myGraphics.setColor (myColor);
	myGraphics.fillRect (bounds.x, bounds.y, bounds.width, bounds.height);

	// restore color
	myGraphics.setColor (oldColor);
}
}
