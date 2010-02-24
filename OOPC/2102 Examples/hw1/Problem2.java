package hw1;

/**
 * [20 pts.] Write a program that determines whether a String value starts and ends with
 * the same letter. (Ignore capitalization, thus 'A' should be the same letter as 'a'). 
 * Your program must follow the design recipe introduced in class on Friday Oct-27 and
 * described on the course web page for design recipes. Your program should output
 * on a single line the statement "The word starts and ends with the same letter"
 * if the String value does, and "The word does not start and end with the same letter" 
 * if the String value doesn't.
 * 
 * @author heineman
 */
public class Problem2 {

	/**
	 * Compare String's first and last letters
	 */
	public static void main(String[] args) {
		// INPUT
		String s = "Abracadabra";
		
		// PROCESSING
		String upcase = s.toUpperCase();
		boolean startsSame = (upcase.charAt(0) == upcase.charAt(upcase.length()-1)); 

		// OUTPUT
		if (startsSame) {
			System.out.println ("The word starts and ends with the same letter");
		} else {
			System.out.println ("The word does not start and end with the same letter");
		}
	}

}
