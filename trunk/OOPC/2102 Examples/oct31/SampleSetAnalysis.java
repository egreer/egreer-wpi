package oct31;

import java.util.Scanner;

/**
 * Use an array to load a set of int values and determine the number of values
 * that exceed the average value.
 * 
 * @author heineman
 */
public class SampleSetAnalysis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// INPUT
		Scanner sc = new Scanner (System.in);
		System.out.println ("How many numbers are in the set?");
		int num = sc.nextInt();
		
		// create array into which values are to be input from the console.
		int []nums = new int[num];
		System.out.println ("Enter in " + num + " numbers, separated by white space.");
		int i = 0;
		while (i < nums.length) {
			nums[i] = sc.nextInt();
			
			i++; // ADVANCE
		}
		
		// PROCESSING
		
		// calculate the average
		i = 0;
		double average = 0;
		while (i < nums.length) {
			average += nums[i];
			
			i++; // ADVANCE
		}
		average = average / nums.length;
		
		// see how many are greater than average
		int ct = 0;
		i= 0; 
		while (i < nums.length) {
			if (nums[i] > average) {
				ct++;
			}
			
			i++; // ADVANCE
		}
		
		// OUTPUT
		System.out.println ("There are " + ct + " numbers greater than the average (" + average + ")");		
	}

}
