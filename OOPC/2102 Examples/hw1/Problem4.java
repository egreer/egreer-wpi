package hw1;

import java.util.Scanner;

/**
 * Write a program to read from the keyboard two lines of input. Each line is prompted 
 * by a statement "Enter Line #:". The input will contain a sequence of characters which
 * was terminated by the user pressing "Enter/Return". Your program should print out
 * the statement "Line 2 is a reverse of Line 1" if the sequence of characters for Line
 * 2 are the reverse of Line 1; Otherwise, output "No Comparison". For example,
 * a sample run of your program is shown below:
 * 
 * @author heineman
 */
public class Problem4 {

	/**
	 * Compare whether two lines of input are reversals.
	 */
	public static void main(String[] args) {
		// INPUT
		Scanner sc = new Scanner (System.in);
		System.out.println ("Enter Line 1:");
		String line1 = sc.nextLine();
		System.out.println ("Enter Line 2:");
		String line2 = sc.nextLine();
		
		// PROCESSING
		boolean isReverse = true;    // assume we are properly a reverse...
		
		// compare if reverse? Can't be true if our lengths are different
		if (line1.length() != line2.length()) {
			isReverse = false;
		} else {
			// now that we know the length is right, our code is quite simple. Note that
			// i is an index into line1, while r is an index into line2.
			int i = 0;
			int r = line2.length() - 1;
			while (i < line1.length()) {
				if (line1.charAt(i) != line2.charAt(r)) {
					isReverse = false;
				}
				
				i++;  // ADVANCE
				r--;
			}
		}
		
		
		// OUTPUT
		if (isReverse) {
			System.out.println ("Line 2 is a reverse of Line 1");
		} else {
			System.out.println ("No Comparison");
		}
		
	}

}
