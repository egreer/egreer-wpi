package oct31;

import java.util.Scanner;

/**
 * Show logic in printing n-dimensional point as "(d1,d2,d3)"
 * 
 * @author heineman
 */
public class FormatPointNDimensional {

	/**
	 * Show nifty while loop that actually has code within while loop AFTER the advance
	 */
	public static void main(String[] args) {
		// INPUT
		Scanner sc = new Scanner (System.in);
		System.out.println ("Enter the number of dimensions.");
		int d = sc.nextInt();
		
		// the points to be read in will each have d-values
		int []point = new int[d];
		
		// Read point
		System.out.println ("Enter " + d + " dimensional values for point, separated by spaces");
		int i = 0;
		while (i < point.length) {
			point[i] = sc.nextInt();
			
			i++;  // ADVANCE
		}
		
		// OUTPUT in special form.
		System.out.print ("(");        // open paren
		
		i = 0;
		while (i < point.length) {
			System.out.print (point[i]);
			
			
			i++;  // ADVANCE, and print comma if more are to come...
			
			if (i < point.length) {
				System.out.print (","); 
			}
		}
		
		System.out.print (")");        // close paren
 
	}

}
