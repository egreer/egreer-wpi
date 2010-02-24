package amida;

/**
 * General exception thrown when errors occur on Amida board.
 * 
 * @author heineman
 */
public class AmidaException extends Exception {

	/**
	 * Default constructor.
	 * 
	 * @param s     Message
	 */
	public AmidaException (String s) {
		super(s);
	}
}
