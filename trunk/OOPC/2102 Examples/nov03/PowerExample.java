package nov03;

public class PowerExample {
	
	/**
	 * Compute the power of 'base' to the given exponent.
	 * 
	 * Exponent must be >= 0
	 * Result may overflow integer arithmetic
	 * 
	 * @param base       base to be raised to a given power.     
	 * @param exponent   the given power to raise base
	 * @return           returns base^exponent
	 */
	public static int power (int base, int exponent) {
		// handle base^0
		if (exponent == 0) {
			return 1;
		}
		
		int result = 1;
		for (int i = 1; i <= exponent; i++) {
			result *= base;
		}
		
		return result;		
	}

	/**
	 * Test cases:
	 * 
	 * 1. 7^3    343
	 * 2. 9^9    387420489
	 * 3. 19^19  -306639989     (math overflow)
	 */
	public static void main(String[] args) {
		System.out.println ("7^3 = " + power(7,3));
		
		System.out.println ("9^9 = " + power(9,9));
		
		System.out.println ("Mathematical overflow. Number is too large to fit into 32 bits");
		System.out.println ("19^19 = " + power(19,19));
	}

}
