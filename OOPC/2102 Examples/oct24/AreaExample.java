/**
 * The package name 'oct24' declares the ownership context
 */
package oct24;

/**
 * Show problem in composing computations. Pay attention to '(' and ')' 
 * @author heineman
 */
public class AreaExample {

	/** Define main function, the entrypoint to a Java program. */
	public static void main (String []args) {
		// INPUT
		int x = 3;
		int y = 4;
		
		// PROCESS
		// Compute the area of a rectangle with edges (y+2) and (x+5). Note how the 
		// use of parentheses is critical
		int area = (y+2)*(x+5);
		int badArea = y+2*x+5;
		
		// OUTPUT
		System.out.println ("Area:" + area);
		System.out.println ("Bad Area:" + badArea);
	}
}
