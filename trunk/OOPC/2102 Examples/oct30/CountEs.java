package oct30;

/**
 * Count the number of times the letter E appears in a given String.
 * @author George
 *
 */
public class CountEs {

	/**
	 * Execute main method
	 */
	public static void main(String[] args) {
		// INPUT
		String s = "Tweedle-dee and Tweedle-dum";
		
		// PROCESSING
		int numEs = 0;
		int i = 0;
		while (i < s.length()) {
			if ((s.charAt(i) == 'e') || (s.charAt(i) == 'E')) {
				numEs++;
			}
			
			i++;  // ADVANCE
		}
		
		System.out.println (s + " has " + numEs + " letter Es");
		

	}

}
