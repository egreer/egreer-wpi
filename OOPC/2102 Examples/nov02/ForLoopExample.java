package nov02;

public class ForLoopExample {

	/**
	 * Show mapping between for loop and while loop 
	 */
	public static void main(String[] args) {
		// count from 1 to 10
		int idx = 0;  // INITIALIZATION
		while (idx < 10) {   // GUARD expression
			// BODY
			System.out.println (idx);
				
			idx++;    // ADVANCE
		}
		System.out.println();
		
		// now do with a for loop
		// for (INITIALIZATION; GUARD expression; ADVANCE) {
		//     BODY
		// }
		int i;
		for (i = 0; i < 10; i++) {
			System.out.println (i);
		}
		
		// convenience suggests the following opportunity, which you CAN'T take advantage
		// of with a while loop. Define local variable j that can only be used within the 
		// FOR LOOP.
		for (int j = 0; j < 10; j++) {
			System.out.println (j);
		}
		
	}

}
