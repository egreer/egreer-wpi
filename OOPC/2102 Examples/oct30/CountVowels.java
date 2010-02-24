package oct30;

// The Scanner class is provided for us by some helpful engineers at 
// Sun Microsystems, the developers of Java. Technically, the name of
// the Scanner class is java.util.Scanner (note the hierarchy).
import java.util.Scanner;

/**
 * A small program that counts the number of vowels in a line of input.
 * 
 * TEST CASES
 * 
 *   1. "George"     ==>   6
 *   2. ""           ==>   0
 *   3. "AeiUOY"     ==>   6
 * 
 * @author heineman
 *
 */
public class CountVowels {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// INPUT
		// Prompt user
		System.out.println ("Enter a String and I will tell you how many vowels were present.");
		
		// Properly configure a Scanner to help us read a line of input
		Scanner sc = new Scanner (System.in);
		String s = sc.nextLine();
		
		// Must iterate over each character in the string. Note that the characters 
		// start at index position 0 and continue right through (and including)
		// position n-1 where n is the length of the string.
		
		// PROCESSING
		int vowelCount = 0;
		String upCase = s.toUpperCase();
		int idx = 0;
		while (idx < upCase.length()) {
			// extract the idx-th character and compare against vowels.
			char c = upCase.charAt(idx);
			
			if ((c == 'A') || 
				(c == 'E') || 
				(c == 'I') ||
				(c == 'O') || 
				(c == 'U') || 
				(c == 'Y')) {
				vowelCount++;
				
			}
			idx++;  // advance
		}
		
		// OUTPUT
		double percent = vowelCount;
		percent = percent / upCase.length();
		
		// Tell compiler that we are OK with losing precision. We know that the
		// right-hand-side is a double, but we want to force a truncate to int.
		// This is known as 'casting'
		int ratio = (int) (100 * percent);
		
		System.out.println ("There were " + vowelCount + " vowels typed in. (" + ratio + "%)");
		

	}

}
