package nov20;

/**		
 * The base class for any Encryption filter.
 * 
 * @author heineman
 *
 */
public abstract class Encryption {

	/** 
	 * Given a plain text string, process some encryption algorithm on it.
	 * 
	 * @param plainText     input text to the algorithm
	 * @return              encrypted Text
	 */
	abstract String encrypt (String plainText);
}
