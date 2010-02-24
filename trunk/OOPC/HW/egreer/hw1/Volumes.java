/**
 * egreer and jm4games
 * Package for HW1
 */
package egreer.hw1;

public class Volumes {

	public static void main(String[] args) {
		//Input
		int cylinderRadius = 6;
		int cylinderHeight = 3;
				
		int sphereRadius = 5;
		
		int pyramidHeight = 4;
		int pyramidBaseWidth = 2;
		int pyramidBaseLength = 3;
		
		double sphereVolume;
		@SuppressWarnings("unused")
		double cylinderVolume;
		@SuppressWarnings("unused")
		double pyramidVolume;
		
		//Processing
		cylinderVolume =  (Math.PI * Math.pow(cylinderRadius, 2.0) * cylinderHeight);
		sphereVolume = (Math.PI * Math.pow(sphereRadius, 3.0) * 4 / 3);
		pyramidVolume = (1.0 / 3.0 * pyramidBaseWidth * pyramidBaseLength * pyramidHeight);
		
		//Output
		System.out.println ("The Sphere has the largest volume of " + sphereVolume);

	}

}
