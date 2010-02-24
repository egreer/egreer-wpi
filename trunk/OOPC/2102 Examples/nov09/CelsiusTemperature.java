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
 * Also, constructor properly ensures this (although, without Exceptions, there is no
 * real enforcement measure. One thing at a time, people...)
 * 
 * @author heineman
 */
public class CelsiusTemperature {

	/** Temperature. */
	private double value;
	
	public CelsiusTemperature (double temp) {
		setTemperature (temp);
	}
	
	/**
	 * Set temperature to the desired value.
	 * 
	 * Ensure that newVal >= -273.15 (absolute zero on celsius scale).
	 * 
	 * @param newVal       the desired new value.
	 */
	public void setTemperature (double newVal) {
		if (newVal < -273.15) {
			System.err.println ("Attempt to set value less than allowed.");
			return;
		}
		
		value = newVal;
	}
}
