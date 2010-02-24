package oct26;

/**
 * Write a Java program to compute and print the hypotenuse for the right triangle
 * with short sides 5 and 7. Confirm the result with manual computation.
 * @author heineman
 */
public class Hypotenuse {

	/**
	 * Main routine
	 */
	public static void main(String[] args) {
		// INPUT
		double s1 = 5;
		double s2 = 7;
		
		// PROCESSING
		double hyp = Math.sqrt(s1*s1+s2*s2);
		
		// OUTPUT
		System.out.println ("Hypotenuse is: " + hyp);
	}
}
