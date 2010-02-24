package nov28;

public class CheckedExceptionExample {

	/**
	 * Purport to do something, but fail miserably
	 * 
	 * @param name                   student name
	 * @throws MyCheckedException    if a non-student name is passed in.
	 */
	public static void anotherBadOne(String name) throws MyCheckedException {
		// note how NullPointerException doesn't need to be declared
		if (name.equals("George")) {
			throw new MyCheckedException ("This is the professor!");
			
			// Execution immediately proceeds to the closest 'catch' that is
			// prepared to field this exception; if none is available, then 
			// the entire program is terminated immediately and a stack trace
			// is generated.
		}
		
		// do something meaningful.
	}
	
	/**
	 * Main example, showing checked exceptions.
	 */
	public static void main (String args[]) {
		// any invocation to this method MUST be enclosed within a try .. catch block
		try {
			anotherBadOne ("George");
		} catch (MyCheckedException e) {
			// reasonable default that you should consider using.
			e.printStackTrace();
		}
	}
}
