package oct30;

import java.util.Scanner;

/**
 * Program to calculate center of mass for a set of particles in the 2D plane
 * with given mass (in double)
 * 
 * @author George
 *
 */
public class CenterOfMass {

	/**
	 * @param args
	 */
	public static void main (String []args) {
        // INPUT & PROCESSING
		Scanner sc = new Scanner (System.in);
		System.out.println ("Enter individual masses as \"x y mass\". Then press control-Z when done");
		double totalMass = 0;
		double xVec = 0, yVec = 0;
		
        while (sc.hasNext()) {
        	// process each line of input (x y mass-as-double)
        	int x = sc.nextInt();
        	int y = sc.nextInt();
        	double mass = sc.nextDouble();
        	
        	// update vectors and overall Mass calculation
        	xVec += mass * x;
        	yVec += mass * y;
        	
        	totalMass += mass;
        }
        
        // OUTPUT
        xVec /= totalMass;
        yVec /= totalMass;
        System.out.println ("(" + xVec + "," + yVec + ") with total mass of " + totalMass);
        
	}
}