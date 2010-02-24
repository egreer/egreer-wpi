package nov09;

/**
 * Small example showing weaknesses of public instance variables.
 * 
 * @author heineman
 *
 */
public class CelsiusTest {

	/**
	 * Small example showing weaknesses of public instance variables.
	 */
	public static void main(String[] args) {
		// If temperature
		BadCelsiusTemperature ct = new BadCelsiusTemperature();
		
		// this must not be possible! Yet there is no way to prevent it!
		ct.value = -9999;

		// The solution is to rely on information hiding. This means we must
		//    (a) design a constructor for the initial allocation
		//    (b) design mutator methods to update the object state properly.
	}

}
