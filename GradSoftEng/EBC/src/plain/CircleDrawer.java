package plain;

import java.awt.Color;
import java.awt.Graphics;

public class CircleDrawer extends AllTogether {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint (Graphics g) {

		Color c = new Color (color,color,color);
		g.setColor(c);
		g.fillOval(310, 10, width, height);
	}
}
