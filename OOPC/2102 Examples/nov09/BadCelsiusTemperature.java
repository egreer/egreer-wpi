package nov09;

/**
 * Example of information hiding.
 * 
 * Note that there is a concept known as "Absolute Zero" [http://en.wikipedia.org/wiki/Absolute_zero]
 * Thus at no point should the value ever be lower than this point.
 * 
 * Within a setTemperature() method, you can protect against the improper setting of 
 * the value. If the instance variable were public, then there would be no ability to 
 * check this.
 * 
 * @author heineman
 */
public class BadCelsiusTemperature {

	/** Temperature. */
	public double value;
	
	/**
	 * Return string representation of temperature.
	 */
	public String toString () {
		return value + " degrees Celsius";
	}
}
