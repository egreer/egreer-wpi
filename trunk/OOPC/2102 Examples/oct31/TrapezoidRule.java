package oct31;

import java.util.Scanner;

/**
 * Compute an approximation of the integral of f(x) over 0..9 for a given
 * set of values of f(x) using the trapezoid approximation.
 * 
 * @author heineman
 *
 */
public class TrapezoidRule {

	/**
	 * Read in the ten values.
	 */
	public static void main(String[] args) {
		// INPUT
		Scanner sc = new Scanner (System.in);
		int []ar = new int[10];
		System.out.println ("Enter in the ten y values representing f(0) .. f(9)");
		
		int i = 0;
		while (i < ar.length) {
			ar[i] = sc.nextInt();
			
			i++;  // ADVANCE
		}
		
		// PROCESSING
		double area = 0;
		i = 0; 
		while (i < ar.length - 1) {
			double trapArea = (ar[i] + ar[i+1])/2.0;
			area += trapArea;
			
			i++;  // ADVANCE
		}
		
		
		// OUTPUT
		System.out.println ("The approximation to the integral is " + area);

	}

}
