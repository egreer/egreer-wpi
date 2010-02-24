package oct30;

import java.util.Scanner;

/**
 * Write a program that reads a word from the 
 * keyboard and outputs that word five times, once per line.
 * 
 * @author heineman
 */
public class DailyQuestion {

	/**
	 * Perform computation
	 */
	public static void main(String[] args) {
		// INPUT
		Scanner sc = new Scanner (System.in);
		System.out.println ("Enter a word:");
		String word = sc.next();
		
		// PROCESSING
		int i = 0;
		while (i < 5) {
			System.out.println (word);
			
			i++;  // ADVANCE
		}
		
		
	}

}
