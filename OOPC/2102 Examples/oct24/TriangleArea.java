package oct24;

/**
 * Write a Java program to compute the area of a right triangle with short sides 5 and 7
 * @author heineman
 */
public class TriangleArea {

	/**
	 * Man program.
	 */
	public static void main(String[] args) {
		// INPUT
		double s1 = 5;
		double s2 = 7;
		
		// Compute area of a triangle using s1,s2 as short sides.
		double area = s1*s2/2;
		System.out.println ("Area:" + area);
	}

}
