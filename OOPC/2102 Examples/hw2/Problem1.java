package hw2;

import java.util.Scanner;

/**
 * This program reads from the keyboard a line of input (containing any number
 * of characters including white space) terminated by the ('\n') character 
 * (see p. 83 of the text). The program outputs the reverse of this string back
 * to the console. 
 * 
 * TEST CASES
 * 
 *    1. "Now is the time"  ==> "emit eht si woN"
 *    2. ""                 ==> ""
 *    3. "Testing this out" ==> "tuo siht gnitseT"
 *    4. "G"                ==> "G"
 *    5. "GH"               ==> "HG"
 * 
 * @author heineman
 */
public class Problem1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println ("Enter a line of text and I will print it in reverse");
		Scanner sc = new Scanner (System.in);
		String s = sc.nextLine();
		
		String reverse = "";
		
		int i = 0;
		while (i < s.length()) {
			char c = s.charAt(i);
			
			
			reverse = c + reverse;
			
			// advance
			i++;
		}
		
		System.out.println (reverse);
	}
}
