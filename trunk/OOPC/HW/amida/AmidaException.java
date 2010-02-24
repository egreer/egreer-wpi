package amida;

/**
 * General exception thrown when errors occur on Amida board.
 * 
 * @author heineman
 */
public class AmidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4828646062584222289L;

	/**
	 * Default constructor.
	 * 
	 * @param s     Message
	 */
	public AmidaException (String s) {
		super(s);
	}
}
