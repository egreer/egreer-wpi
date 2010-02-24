package ebc.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import ebc.model.IModelUpdated;
import ebc.model.Model;

public class SquareDrawingCanvas extends Canvas implements IModelUpdated {

	/** Here to prevent warning in Eclipse. */ 
	private static final long serialVersionUID = 1L;

	/** Double buffer. */
	Image screenImage = null;
	
	/** Model being viewed. */
	Model model;
	
	public static final int OffsetX = 10;
	public static final int OffsetY = 10;
	
	
	public SquareDrawingCanvas (Model m) {
		super();
		
		this.model = m;
	}
	
	/**
	 * Ensures that the image file into which decorators are drawn is properly constructed.
	 *
	 * Because of the way Java AWT works, you can't create an Image object until the Application
	 * is visible; however, this leads to a catch-22, so we perform "lazy computation" by
	 * creating the image once it is really needed.
	 */
	private void ensureImageAvailable() {
		// not yet created the background image. Must do now; can't do this until
		// we have a valid Graphics object.
		if (screenImage == null) {
			screenImage = this.createImage(this.getWidth(), this.getHeight());
		}
	}

	public void redrawState() {
		ensureImageAvailable();
		
		// clear the image.
		Graphics sc = screenImage.getGraphics();
		sc.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		// now draw rectangle. 
		int s = model.getColor().getValue();
		
		Color c = new Color (s,s,s);
		sc.setColor(c);
		sc.fillRect(OffsetX, OffsetY,
				model.getWidth().getValue(),
				model.getHeight().getValue());
	}
	
	/** Paint a rectangle based upon model parameters. */
	public void paint (Graphics g) {
		// not visible yet (RARELY occurs).
		if (g == null) {
			return;
		}
		
		// simply overwrite the image.
		int w = getWidth();
		int h = getHeight();
		g.drawImage(screenImage, 0, 0, w, h, this);
	}


	/** Respond to changes in the underlying model. */
	public void modelChanged() {
		redrawState();
		repaint();
	}
	
}
