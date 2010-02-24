package hw1;

import java.util.Scanner;

/**
 * [20 pts.] Write a program that reads a set of integers, one per line, until the end 
 * of input has been reached (in Eclipse, this is done by typing "Control-Z" in the Console
 * window. You can assume that at least two numbers will be typed in. Once all 
 * integers have been read, your program should output from this set of numbers: 
 * (a) the minimum number; (b) the maximum number; and (c) the total sum of all 
 * numbers entered. For example,
 *
 * @author George
 */
public class Problem5 {

	/**
	 * This solution (as shown) doesn't use arrays.
	 */
	public static void main(String[] args) {
		// INPUT & PROCESSING
		int min, max, total;
		System.out.println ("Enter in a set of numbers, and I'll calculate min, max, total");
		Scanner sc = new Scanner (System.in);
		
		// since we know we have > 2 numbers, take first one here, and set values.
		int i = sc.nextInt();
		
		min = i;
		max = i;
		total = i;
		
		// read each int, one at a time, until we are done. Update min, max, total
		while (sc.hasNext()) {
			i = sc.nextInt();
			
			if (i < min) {
				min = i;
			}
			
			if (i > max) {
				max = i;
			}
			
			total += i;
			
		}
				
		// OUTPUT
		System.out.println ("Minimum Number: " + min);
		System.out.println ("Maximum Number: " + max);
		System.out.println ("Total of Numbers: " + total);

	}

}
