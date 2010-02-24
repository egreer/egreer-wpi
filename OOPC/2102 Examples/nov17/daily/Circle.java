package nov17.daily;


/**
 * Represents a Circle which is a type of figure with a known area.
 * 
 * @author heineman
 */
public class Circle extends Figure {

	/** Circle stores this information. */
	int radius;

	/**
	 * Construct circle using radius
	 * 
	 * @param r    radius of circle
	 */
	public Circle (int radius) {
		this.radius = radius;
		
		setArea (Math.PI*radius*radius);
	}
	
	/** Return String representation. */
	public String toString () {
		return "[Circle (" + radius + ") area=" + getArea() + "]";
	}
}
