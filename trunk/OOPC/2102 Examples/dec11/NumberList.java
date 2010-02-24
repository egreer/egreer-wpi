package dec11;


/**
 * Represents a linked list of int values maintained in ascending order.
 * 
 * @author heineman
 */
public class NumberList {
	/** head. */
	NumberNode head;
	
	/**
	 * Default constructor. 
	 */
	public NumberList () {
		head = null;	
	}
	
	/**
	 * Simple prepend onto list.
	 * 
	 * @param i
	 */
	public void prepend (int i) {
		NumberNode node = new NumberNode(i);
		node.next = head;
		head = node;
	}
	
	/**
	 * Recursive search solution.
	 */
	public boolean find (int i) {
		long start = System.currentTimeMillis();
		
		boolean rc;
		
		// empty? Done here
		if (head == null) {
			rc = false;
		} else {
			// invoke recursive solution
			rc = head.find(i);
		}
		
		long end = System.currentTimeMillis();
		System.out.println ("Recursive find: " + (end-start));
		return rc;
	}
	
	/**
	 * Iterative search solution.
	 */
	public boolean findIterative (int i) {
		long start = System.currentTimeMillis();
		
		boolean rc = false;
		
		NumberNode node = head;
		while (node != null) {
			if (node.value == i) {
				rc = true;
				break;     // stop while loop NOW!
			}
			
			node = node.next;   // advance
		}
		
		long end = System.currentTimeMillis();
		System.out.println ("Iterative find: " + (end-start));
		return rc;
	}
	
}