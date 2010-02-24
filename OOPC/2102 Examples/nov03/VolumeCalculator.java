package nov03;

import java.util.Scanner;

public class VolumeCalculator {

	/**
	 * Compute volume of a cylinder with radius r and height h.
	 *
	 * @param     r is the radius of the cylinder
	 * @param     h is the height of the cylinder
	 * @return     calculate volume
	 */
	 public static double cylinderVolume (double r, double h) {
	   double val = Math.PI * r * r * h;
	   return val;
	 }

	
	/**
	 * Compute volume of cylinder.
	 * 
	 * Test Cases (radius, height) => volume
	 * 1. (1, 1)  =>   3.141592653589793
	 * 2. (1, 10) =>  31.41592653589793
	 * 3. (5, 7)  => 549.7787143782139
	 */
	public static void main(String[] args) {
		// INPUT
		Scanner sc = new Scanner (System.in);
		double radius = sc.nextDouble();
		double height = sc.nextDouble();

		// PROCESSING
		// compute volume
		double volume = cylinderVolume(radius, height);
		
		// OUTPUT
		System.out.println ("Volume is:" + volume);
	}

}
