package nov28;

/**
 * Show example of a Checked Exception.
 * 
 * @author heineman
 */
public class MyCheckedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5996809609792820450L;

	/**
	 * Default constructor for Exceptions.
	 * 
	 * @param msg   message for the exception
	 */
	public MyCheckedException (String msg) {
		super(msg);
	}
}
