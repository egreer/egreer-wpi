package nov02;

/**
 * Write main method that uses for loop to determine the smallest
 * circle with an int radius value whose area is greater than 100 
 * 
 * @author heineman
 */
public class CircleExample {

	/**
	 * Compute circle
	 */
	public static void main(String[] args) {
		// PROCESSING
		
		// compute area table
		for (int r = 1; r <= 10; r++) {
			System.out.println (r + ". " + Math.PI*r*r);
		}
		
		int radius = 1;
		while (Math.PI*radius*radius < 1000) {
			
			radius++;
		}
		
		// OUTPUT
		System.out.println ("the circle with radius " + radius + " is the smallest circle with integer radius whose area is greater than 1000.");
	}

}
