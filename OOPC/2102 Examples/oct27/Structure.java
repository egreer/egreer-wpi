package oct27;

public class Structure {

	/**
	 * Example of SEQUENTIAL composition
	 */
	public static void main(String[] args) {
		int s=9;    // first statement
		
		// This is a BLOCK of statements. It is rather silly to use this syntax for no reason.
		// But it explains two key points. (1) A BLOCK of Java statements is delimited by '{' ... '}'
		// And (2) Each BLOCK may have its own "local" variables that are not visible outside
		// of that BLOCK. Note that BLOCKs may be nested
		{
			// this 't' variable is different from the one below. Its initial value is copied from s
			@SuppressWarnings("unused")
			int t = s;
		}
		
		{ 
			// This 't' variable is different from the above one
			@SuppressWarnings("unused")
			int t = 9;
		}

		
		
		
	}

}
