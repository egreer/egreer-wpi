package oct27;

/**
 * Show basic structure for conditional compositions
 *
 *   IF (guard) { true-BLOCK} else { false-BLOCK }
 * 
 * These can be nested:
 * 
 *   IF (guard) { IF (guard) { true-BLOCK} else { false-BLOCK } } else { false-BLOCK }
 *                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                         This is the true-BLOCK
 * @author heineman
 */
public class Conditional {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int x = 4;
		
		// I STRONGLY advocate the following formatting guideline for the if statement.
		if (x > 3) {
			System.out.println ("greater than 3"); 
		} else { 
			System.out.println ("Not greater than 3."); 
		}

		// Compose together. Observe the importance of indentation!
		if (x > 3) {
			if (x < 5) {
				System.out.println ("greater than 3 and less than 5");
			} else {
				System.out.println ("greater than 3 but not less than 5");
			}
		} else { 
			System.out.println ("Not greater than 3."); 
		}
	
		// Can you understand this?
		if (x > 3) { if (x < 5) {
			System.out.println ("greater than 3 and less than 5");
		} else {
			System.out.println ("greater than 3 but not less than 5");
		}} else { 
			System.out.println ("Not greater than 3."); 
		}
		
	}

}
