package nov03;

import java.util.Scanner;

public class ExampleFunction {

	/**
	  * Compute the sine of an angle of the given degree.
	  *
	  * @param    degree is in the range 0 <= degree <= 360
	  * @return   computation of the trigonometric sine function 
	  */
	public static double sine (int degree) {
	    // convert degrees into Radians, and return result.
	    double rad = degree*2*Math.PI/360.0;
	    double val = Math.sin(rad);
	    
	    return val;
	}
	
	/**
	 * Test cases
	 *   1. 0    --> 0.0
	 *   2. 45   --> 0.7071067811865475    (~ sqrt(2)/2)
	 *   3. 90   --> 1.0
	 *   4. 360  --> -2.4492935982947064E-16   (fairly close to zero!)
	 *   5. 1    --> 0.01745240643728351
	 */
	public static void main(String[] args) {
		// INPUT
		Scanner sc = new Scanner (System.in);
		System.out.println ("Enter degree (0 <= deg <= 360)");
		int x = sc.nextInt();
		
		// PROCESSING
		double s = sine(x);
		
		// OUTPUT
		System.out.println ("Sin(" + x + ") = " + s);

	}

}
