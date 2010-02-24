package oct27;

/**
 * Compute Quadrant for (x,y) in cartesian coordinates. The only restrictions is that
 * there can only be a single System.out.println() Statement in the program.
 * 
 * http://en.wikipedia.org/wiki/Cartesian_coordinate_system

 * @author George
 *
 */
public class QuadrantChecker {

	/**
	 * Method to compute quadrant for (x,y)
	 */
	public static void main(String[] args) {
		// INPUT
		int x = 5;
		int y = 2;

		// PROCESSING
		String quadrant = "on an axis";  // reasonable default value.
		
		// Since each of these four conditions is mutually independent, we could have
		// made these statements a chain of four independent IF statements. However, 
		// we chain them together via 'else' to show the semantic grouping.
		if ((x > 0) && (y > 0)) {
			quadrant = "in quadrant I";
		} else if ((x > 0) && (y < 0)) {
			quadrant = "in quadrant IV";
		} else if ((x < 0) && (y > 0)) {
			quadrant = "in quadrant II";
		} else if ((x < 0) && (y < 0)) {
			quadrant = "in quadrant III";
		}
		
		// OUTPUT
		System.out.println ("The point (" + x + "," + y + ") is " + quadrant);
	}
}
