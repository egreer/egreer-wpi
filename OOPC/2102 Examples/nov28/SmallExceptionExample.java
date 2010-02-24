package nov28;

/**
 * Show how an exception may be thrown.
 * 
 * @author George
 *
 */
public class SmallExceptionExample {

	/**
	 * Returns the last character of the string.
	 * 
	 * This method is the one that throws the Exception
	 *
	 * @param  name
	 */
	public static char badMethod (String s) {
		return s.charAt(s.length());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char ch = badMethod("George");
		
		// note that we never get to execute this code, because the Exception 
		// occurs within the badMethod, and execution terminates BECAUSE no-one 
		// is prepared to catch this exception.
		System.out.println ("last character is " + ch);

	}

}
