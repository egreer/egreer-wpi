package nov28;

/**
 * Show how to catch an exception.
 * 
 * @author George
 *
 */
public class SmallExceptionExampleRevised {

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
		try {
			char ch = badMethod("George");	
			System.out.println ("last character is " + ch);
		} catch (StringIndexOutOfBoundsException sioobe) {
			// once the Exception is detected, and caught here, then the next
			// line of code to execute is here.
			System.out.println ("Something went wrong with badMethod.");
		}
		
		// and execution proceeds out to here.
	}

}
