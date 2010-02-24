package lab3;

public class DivideByFunction extends Function {
	/** The divisor to use. */
	int divisor;
	
	public DivideByFunction (int n) {
		divisor = n;
	}
	
	/** Returns the division of x by divisor. */
	public int compute (int x) {
		return x / divisor;
	}
}
