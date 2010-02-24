package egreer.lab3;

/**
 * A TripleFunction takes in an integer x and produces the result x*3 
 * 
 *   f(x) = x*3
 *   
 * @author egreer
 *
 */
public class TripleFunction extends Function {

	/**
	 * Compute f(x) where f is x*3
	 * 
	 * @param x      value in the domain for f(x)
	 * @return       range of the function f(x) where f(x) is x*3
	 */
	public int compute (int x) {
		return  x * 3;
	}
}