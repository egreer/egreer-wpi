package egreer.lab3;

/**
 * Resources for Lab3
 * 
 * A Function takes in an integer and produces an integer, based on a calculation.
 * The default base class Function simple represents the function:
 * 
 *   f(x) = x
 *   
 * That is, it is the identity function, returning its input
 * 
 * @author heineman@cs.wpi.edu
 */
public class Function {

	/**
	 * Compute f(x) where f is the identify function.
	 * 
	 * @param x      value in the domain for f(x)
	 * @return       range of the function f(x) where f(x) is the identify function.
	 */
	public int compute (int x) {
		return x;
	}
}
