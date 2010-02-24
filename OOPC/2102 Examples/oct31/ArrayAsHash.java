package oct31;

import java.util.Scanner;

/**
 * Use Array to store range of possible values.
 * 
 * @author heineman
 *
 */
public class ArrayAsHash {

	/**
	 * Report student progress 
	 */
	public static void main(String[] args) {
		// INPUT
		System.out.println ("Enter all student grades for exam1. Press Control-Z when done");
		Scanner sc = new Scanner (System.in);
		
		// Note that grades are in the range from 0..100, which means 101 elements!
		int []grades = new int[101];
		
		while (sc.hasNext()) {
			int grade = sc.nextInt();
			
			// Increment the value found in the position for grade.
			grades[grade]++;
		}

	}

}
