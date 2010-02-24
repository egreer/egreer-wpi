package dec11;

public class FibonacciExample {

	/**
	 * Compute fibonacci n using recursion.
	 * 
	 * @param n   greater than or equal to zero
	 * @return
	 */
	public static int fibonacci (int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return 1;
		}
		
		// recurse onwards
		return fibonacci(n-1) + fibonacci(n-2);
	}
	
	/**
	 * Compute fibonacci n using iteration.
	 * 
	 * @param n   greater than or equal to zero
	 * @return
	 */
	public static int iterativeFibonacci (int n) {
		if (n == 0) {
			return 1;
		} 
		if (n == 1) {
			return 1;
		}
		
		int prev1 = 1;
		int prev2 = 1;
		n = n - 2;
		while (n > 0) {
			int f = prev1 + prev2;
			prev2 = prev1;
			prev1 = f;
			
			n = n-1;
		}
		
		return prev1 + prev2;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println ("fibs");
		long start = System.currentTimeMillis();
		for (int i = 1; i < 40; i++) {
			System.out.println (iterativeFibonacci(i));
		}
		long end = System.currentTimeMillis();
		System.out.println ((end-start) + " ms.");
		
		System.out.println ("fibs recursively");
		start = System.currentTimeMillis(); 
		for (int i = 1; i < 40; i++) {
			System.out.println (fibonacci(i));
		}
		end = System.currentTimeMillis();
		System.out.println ((end-start) + " ms.");
	}

}
