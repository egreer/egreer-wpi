package oct26;

/**
 * Given the DictionaryLetter question below, which method in the String class on page 39 
 * could help? Explain how (or just write the program).
 * 
 * We can first convert the string to upper case, so each time we extract the first
 * character, we are going to get an uppercase letter.
 * 
 * @author heineman
 *
 */
public class DailyQuestion {

	/**
	 * Use additional methods to help with problem
	 */
	public static void main(String[] args) {
		// INPUT
		String s = "Pulchritudinous";

		// PROCESSING
		// to avoid problems with upperCase/LowerCase, force to upcase first
		s = s.toUpperCase();
		
		// Note that index values are in the range [0 .. s.length()-1]
		char letter = s.charAt(0);
		
		// OUTPUT
		System.out.println ("The letter for " + s + " is " + letter);

	}

}
