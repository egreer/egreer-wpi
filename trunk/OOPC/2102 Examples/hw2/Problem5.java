package hw2;

import java.util.Scanner;

/**
 * Read in a sequence of ints and detect the longest running sequence of duplicate
 * numbers.
 * 
 * @author heineman
 */
public class Problem5 {

	/**
	 * Find longest sequence of same values in an array.
	 */
	public static void main(String[] args) {
		// INPUT
		System.out.println ("How many numbers are in the sequence");
		Scanner sc = new Scanner (System.in);
		int num = sc.nextInt();
		System.out.println ("Please enter " + num + " numbers separated by whitespace");
		int []ar = new int[num];
		
		// read into the array.
		for (int i =  0; i < ar.length; i++) {
			ar[i] = sc.nextInt();
		}
		
		// PROCESS
		// find longest sequence. Start with the observation that the first value in
		// the array is a potential "longest sequence" of size 1. This will always be
		// the length of the current Sequence.
		int seqSize = 1;
		int val = ar[0];         // value of the current sequence.
		
		int maxSeq = 1;          // max number-in-a-row seen so far
		int maxVal = ar[0];      // the value of this sequence.
		
		int i = 1;
		
		// The key to solving this problem was realizing that Each time through the loop:
		//     (seqSize, val) records the length and value of the current sequence;
		//     (maxSeq, maxVal) records the current record-holding sequence.
		//
		while (i < ar.length) {
			// if we are extending the current sequence (based on 'val') then increase size.
			if (ar[i] == val) {
				seqSize++;
			} else {
				// we have terminated a sequence because we have changed values. See if 
				// we have broken a new record. If so, update maxSeq and maxVal.
				if (seqSize > maxSeq) {
					maxSeq = seqSize;
					maxVal = val;
				}
				
				// now we reset our values as we go through the loop again.
				seqSize = 1;
				val = ar[i];
			}
			
			i++;  // ADVANCE
		}
		
		// OUTPUT
		System.out.println ("max sequence has " + maxSeq + " ints whose value is " + maxVal);
	}
}
