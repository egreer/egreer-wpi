package lpf.gameLibrary;

/**
 * Exception for when there is no puzzle available
 * 
 * @author Nik Deapen
 *
 */
public class NoPuzzleAvailableException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a new NoPuzzleAvailableException
	 * 
	 * @param s			the exception's message
	 */
	public NoPuzzleAvailableException(String s){
		super(s);
	}

}
