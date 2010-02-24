package dec11;

public class FactorialExample {
	
	/**
	 * Given positive n, returns n!
	 * 
	 * @param n      n must be greater than or equal to zero.
	 * @return
	 */
	public static int factorial (int n) {
		// Base case
		if (n == 0) {
			return 1;
		}
		
		return n * factorial(n-1);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println ("5! = " + factorial(5));
		
		// what happens here? Watch out!
		System.out.println ("7! = " + factorial(-7));

	}

}
