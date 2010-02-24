package lab1;

import java.util.Scanner;

/**
 * Design a program where the user enters a number n (which must be > 3) that determines 
 * the number of integers in a set that will be entered, one per line. Because the user 
 * is entering in a set, you can be assured that all numbers will be distinct. 
 * Your program must then read in n numbers from the keyboard and print out the average 
 * of the n-2 numbers that remain when you ignore the highest and the lowest numbers 
 * from the set.
 * 
 * @author heineman
 */
public class Lab1 {
	
	/**
	 * Sample solution for Lab1.
	 */
	public static void main(String[] args) {
		// INPUT
		System.out.println ("Enter the Size of the Set.");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		// create array and read in from keyboard.
		int []ar = new int[num];
		System.out.println ("Now enter the " + num + " numbers, one per line.");
		int i = 0;
		while (i < ar.length) {
			ar[i] = sc.nextInt();
			i++;   // ADVANCE
		}
		
		// PROCESSING.
		int total = ar[0];   // assume first one is min and max (and also total)
		int min = ar[0];
		int max = ar[0];
		
		i = 1;
		while (i < ar.length) {
			//Each time through this loop, min (and max) is appropriate for ar[0] .. ar[i-1]
			total += ar[i];
			
			if (ar[i] < min) {
				min = ar[i];
			}
			if (ar[i] > max) {
				max = ar[i];
			}
			
			i++;
		}
		
		// OUTPUT
		double average = 1.0*(total-min-max)/(num-2);  // by multiplying by 1.0 we enforce double-style arithmetic
		System.out.println("The average of these numbers after removing the high value (" + max + ") and the low value (" + min + ") is " + average);
	}
	
}
