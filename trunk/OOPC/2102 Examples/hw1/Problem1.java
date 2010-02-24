package hw1;

/**
 * [20 pts.] Write a program that computes (a) the volume of a cylinder with radius 6 and 
 * height 3; (b) the  volume of a sphere given radius 5; (c) the volume of a pyramid whose
 * height is 4 and whose base is a square with dimensions 2 by 3. Once all values are 
 * calculated, you should output the statement "The XXXX has the largest volume of YYYY" 
 * where XXXX is the shape that had the largest volume, and YYYY is the actual volume 
 * as calculated in steps (a), (b), or (c)
 *
 * @author heineman
 */
public class Problem1 {

	/**
	 * Compute volumes and output highest one
	 */
	public static void main(String[] args) {
		// INPUT
		double cylinderVolume = Math.PI*6*6*3;         // radius of 6, height of 3
		double sphereVolume = (4.0*Math.PI*5*5*5)/3.0; // radius = 5
		double pyramidVolume = (2*3)*4/3.0;            // Area = 2*3, height = 4;
		
		// PROCESSING
		double maxVolume;                              // largest Volume
		String shape;                                  // Shape with largest volume.
		
		// find max of first two; then compare with pyramid
		if (cylinderVolume > sphereVolume) {
			shape = "cylinder";
			maxVolume = cylinderVolume;
		} else {
			shape = "sphere";
			maxVolume = sphereVolume;
		}
		
		// now we need only check to see if the pyramid is too much.
		if (pyramidVolume > maxVolume) {
			shape = "pyramid";
			maxVolume = pyramidVolume;
		}
		
		// OUTPUT
		System.out.println ("The " + shape + " has the largest volume of " + maxVolume);
	}

}
