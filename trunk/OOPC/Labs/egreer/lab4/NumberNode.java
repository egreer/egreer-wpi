package egreer.lab4;

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
}
