package egreer.lab3;

/**
 * A DivideByFunction takes in an integer x and integer n produces the result x/n 
 * 
 *   f(x) = x/n
 *   
 * @author egreer
 *
 */
public class DivideByFunction extends Function{
	int n =  0;
	
	public DivideByFunction(int n){
		this.n = n;
	}
		
	
	/**
	 * Compute f(x) where f is x/n
	 * 
	 * @param x     value in the domain for f(x)
	 * @return      range of the function f(x) where f(x) is x/n
	 */
	public int compute (int x) {
		return  x / n;
	}

}
