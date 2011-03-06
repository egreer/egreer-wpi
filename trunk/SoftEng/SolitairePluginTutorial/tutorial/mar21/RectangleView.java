package tutorial.mar21;

import ks.common.view.Widget;

public class RectangleView extends Widget {

	public RectangleView (RectangleElement e) {
		super(e);
	}
	
	@Override
	public void redraw() {
		// Type the Model Element.
		RectangleElement re = (RectangleElement) getModelElement(); 
		
		if (getImage() == null) {
			/** Create image of the right size. */
			java.awt.Component ct = container;
			if (ct == null) {
				System.err.println ("IntegerView::redraw() has no container.");
				return;
			}
			setImage (ct.createImage(width, height));
		}

		java.awt.Graphics g = getImage().getGraphics();

		g.setColor (re.getColor());
		g.fillRect (0,0, width, height);
	}

}
