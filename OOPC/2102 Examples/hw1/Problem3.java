package hw1;

/**
 * Write a program that uses a while loop that prints a table of temperature conversions
 * from Celsius to Fahrenheit. Note that the order of the output is in reverse order
 * from 40 degree Celsius down to -10 degrees Celsius. 
 * 
 * @author heineman
 */
public class Problem3 {

	/**
	 * Output Celsius/Fahrenheit table.
	 */
	public static void main(String[] args) {
		
		System.out.println ("Celsius : Fahrenheit");
		System.out.println ("--------------------");
		
		int c = 40;    // Initialize loop
		while (c >= -10) {
			// compute conversion
			double f = (c * 9.0 / 5.0) + 32;
			System.out.println (c + " : " + f);
			
			c = c - 1;  // ADVANCE
		}

	}

}
