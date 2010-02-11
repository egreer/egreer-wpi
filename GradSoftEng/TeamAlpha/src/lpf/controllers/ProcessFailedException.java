package lpf.controllers;

/**
 * An exception thrown for when the process for a controller has failed
 * 
 * @author Nik Deapen
 *
 */
public class ProcessFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new ProcessFailedException
	 */
	public ProcessFailedException(){
		super();
	}
	
	/**
	 * Creates a new ProcessFailed Exception with the given message
	 * 
	 * @param s			the exception's message
	 */
	public ProcessFailedException(String s){
		super(s);
	}
}
