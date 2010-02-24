package dec11;

/**
 * A node in the list.
 * 
 * Note that NumberNode has absolutely no idea that it is being used within a list
 * of ascending integers.
 * 
 * @author heineman
 *
 */
public class NumberNode {
	/** Value of this node. */
	int value;
	
	/** The next node in the list. */
	NumberNode next;
	
	/**
	 * Default constructor
	 * 
	 * @param i    number represented by this node.
	 */
	public NumberNode (int i) {
		value = i;
		next = null;
	}
	
	/**
	 * Recursive implementation
	 */
	public boolean find (int i) {
		// base case(s)
		if (value == i) {
			return true;
		}
		
		if (next == null) {
			return false;
		}
		
		// recurse onwards!
		return next.find(i);
	}
}
