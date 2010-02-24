package nov09;

/**
 * Represents a StringValue as found within a List of String Values.
 * 
 * Note that it is critical for these fields to be 'package private' (no modifier).
 * 
 * @author heineman
 */
public class StringValue {
	/** The String value of interest. */
	String value;
	
	/** 
	 * The next StringValue in the List of String Values. It may be 
	 * null (this is the last in the List of String Values) or it may 
	 * point to the next one. 
	 */
	StringValue next;
	
	
}