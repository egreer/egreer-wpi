package oct26;

/**
 * 1. What is wrong with this program?
 * 2. What do you need to change it?
 * 
 * @author heineman
 */
public class TemperatureExample {

	/**
	 * Program to convert Celsius into fahrenheit.
	 */
	public static void main(String[] args) {
		int celsius = 100;
		
		int fahrenheit = (9/5)*celsius + 32;
		
		System.out.print   (celsius + " degrees Celsius = ");
		System.out.println (fahrenheit + " degrees Fahrenheit.");
	}

}
