package nov03;

import java.util.Scanner;

public class SampleQuestion {
	/**
	 * Read using the given Scanner object an array of n int values, as
	 * determined by the user.
	 * 
	 * @param    sc is object from which to read input.
	 * @return   array of int values
	 */
	public static int[] readArray(Scanner sc) {
		System.out.println("How many numbers to enter?");
		int num = sc.nextInt();

		int[] vals = new int[num];

		/** Load up values. */
		System.out.println("Enter " + num + " values.");
		for (int i = 0; i < num; i++) {
			vals[i] = sc.nextInt();
		}
		return vals;
	}

	/** output. */
	public static void outputLessThan50(int[] ar) {
		int ct = 0;
		for (int i = 0; i < ar.length; i++) {
			// NOTE: Prior to testing, this said "i < 50"!!! HAH!
			if (ar[i] < 50) {
				ct++;
			}
		}
		
		System.out.println (ct + " vals were less than 50.");
	}

	
	/** 
	 * You must fill in the helper methods to make this example work. 
	 * 
	 * Test Cases:
	 * 1. Empty Array      --> 0
	 * 2. [26]             --> 1
	 * 3. [50]             --> 0
	 * 4. [99]             --> 0
	 * 5. [2, 49, 50, 51]  --> 2
	 * 
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nums[] = readArray(sc);
		outputLessThan50(nums);
	}
}