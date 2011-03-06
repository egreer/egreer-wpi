package tutorial.v5;

import java.awt.Color;

import ks.common.model.Element;

public class RectangleElement extends Element {
	/** color. */
	final Color color;
	
	public RectangleElement (String name, Color c) {
		super();
		
		setName(name);
		this.color = c;
	}
	
	public Color getColor() {
		return color;
	}
}
