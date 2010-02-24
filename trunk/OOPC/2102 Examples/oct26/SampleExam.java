package oct26;

/**
 * Write a Java program to compute the area of a right triangle with short sides 4 and 7
 * @author heineman
 *
 */
public class SampleExam {

	/**
	 * Man program.
	 */
	public static void main(String[] args) {
		// INPUT
		int s1 = 5;
		int s2 = 7;
		
		// show incorrect computation. It is incorrect because int division truncates results.
		int area = s1*s2/2;
		System.out.println ("Area:" + area);

		// This is no better. Make sure you know why!
		double area2 = s1*s2/2;
		System.out.println ("Area:" + area2);
		
		// Understand why this works.
		double area3 = s1*s2/2.0;
		System.out.println ("Area:" + area3);
		
		// This is appropriate. Always remember to perform computations using variables
		// whose precision is appropriate for the task at hand.
		double area4 = s1 * s2;
		area4 = area4 / 2;
		System.out.println ("Area:" + area4);
	}

}
