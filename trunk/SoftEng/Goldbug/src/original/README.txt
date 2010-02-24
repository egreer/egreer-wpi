This is a serviceable program. But look at how everything is conflated together.
Here, for example, are the attributes for the class

    /** Read input. */
	static Scanner sc;
	
	/** The code. */
	static String code;
	
	/** Unknown character. */
	public static char unknown = '\001';
	
	/** Coded characters and their replacement. */
	static Hashtable<Character,Character> key = new Hashtable<Character,Character>(); 
	
	/** Characters in decreasing frequency. */
	static char chars[];
	
	/** And their frequency. */
	static int freq[];
	
Some of these are to process the input. Others contain the core knowledge of the
application.

The main method contains way too much. Note how it takes over and assumes all input
is to be through the console.  What if you were responsible for converting this
into a GUI program? What would you do?

Hint: This will occupy our time for the first week.
