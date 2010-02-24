package nov17.daily;


/**
 * Represents a Triangle which is a type of figure with a known area.
 * 
 * @author heineman
 */
public class Triangle extends Figure {

	/** Triangle stores this information. */
	int shortSide1;
	int shortSide2;
	int longSide;

	/**
	 * Construct right triangle of short sides a,b and long side c.
	 * 
	 * @param a   short side 1
	 * @param b   short side 1
	 * @param c   long side
	 */
	public Triangle (int a, int b, int c) {
		shortSide1 = a;
		shortSide1 = b;
		longSide = c;
		
		setArea(a*b/2.0);
	}
	
	/** Return String representation. */
	public String toString () {
		return "[Triangle (" + shortSide1 + "," + shortSide2 + "," + longSide + ") area=" + getArea() + "]";
	}

}
