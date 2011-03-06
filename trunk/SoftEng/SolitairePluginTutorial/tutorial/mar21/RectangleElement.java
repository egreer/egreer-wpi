package tutorial.mar21;

import java.awt.Color;

import ks.common.model.Element;

public class RectangleElement extends Element {
	/** color. */
	Color color;
	
	public RectangleElement (String name, Color c) {
		super();
		
		setName(name);
		this.color = c;
	}
	
	public Color getColor() {
		return color;
	}
	
	/** Declare new color and announce change to listeners. */
	public void setColor (Color newColor) {
		this.color = newColor;
		
		hasChanged();
	}
}
