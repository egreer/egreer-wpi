package nov09;

/**
 * Represents a List of String Values.
 * 
 * @author heineman
 */
public class StringList {
	/** 
	 * Every List of String Values has a head. It may be null (empty) or it
	 * points to the first StringValue in the list. 
	 */
	StringValue head;
	
	/**
	 * Prepend a String to the front of a List of String Values. 
	 * 
	 * Goal: head points to new StringValue object, whose next value
	 *       points to the original head.
	 */
	public void prepend (String s) {
		/** Create the StringValue node. */
		StringValue sv = new StringValue();
		sv.value = s;
		
		/** Redirect links so next value points to original head. */
		sv.next = head;
		
		/** Make sure our linked list starts, now, with this new head. */
		head = sv;
	}
	
	/**
	 * Write search method that determines if a String is in a List of String Values
	 * 
	 * @param s       target String to search for.
	 * @return true   if s is contained within List of String Values.
	 */
	public boolean search (String s) {
		StringValue p = head;
		
		/** While there are more in this list, continue to search. */
		while (p != null) {
			// Once we have found the target string, return in success.
			if (p.value.equals(s)) {
				return true;
			}
			
			p = p.next;    // advance to next in list			
		}
		
		// nope. Not found
		return false;
	}
	
	
}
