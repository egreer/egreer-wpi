package lab3;

/**
 * Resources for Lab3
 * 
 * A SquareFunction takes in an integer x and produces the result x^2 
 * 
 *   f(x) = x^2
 *   
 * @author heineman@cs.wpi.edu
 *
 */
public class SquareFunction extends Function {

	/**
	 * Compute f(x) where f is x^2
	 * 
	 * @param x      value in the domain for f(x)
	 * @return       range of the function f(x) where f(x) is x^2
	 */
	public int compute (int x) {
		return x * x;
	}
}
