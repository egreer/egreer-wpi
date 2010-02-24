package oct26;

/**
 * Write a Java program that determines the letter to be used ('A' ... 'Z') in the 
 * dictionary where a word, represented by a String, would be placed.
 * 
 * @author heineman
 */
public class DictionaryLetter {

	/**
	 * Main method
	 */
	public static void main(String[] args) {
		// INPUT
		String s = "Pulchritudinous";

		// PROCESSING
		// Note that index values are in the range [0 .. s.length()-1]
		char letter = s.charAt(0);
		
		// OUTPUT
		System.out.println ("The letter for " + s + " is " + letter);
	}
}
