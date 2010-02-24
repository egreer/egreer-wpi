package oct31;

import java.util.Scanner;

/**
 * Read in coordinates for n-dimensional points, and compute euclidiean
 * distance in n dimensions.
 * 
 * @author heineman
 */
public class ComputeNDimensionalDistance {
	
	/**
	 * Read two values and compute n-dimensional distance
	 */
	public static void main(String[] args) {
		// INPUT
		Scanner sc = new Scanner (System.in);
		System.out.println ("Enter the number of dimensions.");
		int d = sc.nextInt();
		
		// the points to be read in will each have d-values
		int []point1 = new int[d];
		int []point2 = new int[d];
		
		// Read first point
		System.out.println ("Enter " + d + " dimensional values for point-1, separated by spaces");
		int i = 0;
		while (i < point1.length) {
			point1[i] = sc.nextInt();
			
			i++;  // ADVANCE
		}
		
		// Read second point
		System.out.println ("Enter " + d + " dimensional values for point-2, separated by spaces");
		i = 0;
		while (i < point2.length) {
			point2[i] = sc.nextInt();
			
			i++;  // ADVANCE
		}
		
		// PROCESSING
		double distance = 0;
		i =0 ;
		while (i < point2.length) {
			distance += (point1[i] - point2[i]) * (point1[i] - point2[i]);
			
			i++;  // ADVANCE
		}
		distance = Math.sqrt(distance);
		
		// OUTPUT
		System.out.println ("The distance is " + distance);
	}
	
}
