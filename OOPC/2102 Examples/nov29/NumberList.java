package nov29;

/**
 * Represents a linked list of int values maintained in ascending order.
 * 
 * @author INSERT NAME HERE
 */
public class NumberList {
	/** head of the sorted list. */
	NumberNode head;
	
	/**
	 * Insert a node into the list at its appropriate location to represent i.
	 * 
	 * The list is meant to be sorted in ascending order, thus if node n appears
	 * before node p in the list, then n.value <= p.value
	 * 
	 * @param i   the number to be inserted.
	 */
	public void insert (int i) {
		// construct the node
		@SuppressWarnings("unused")
		NumberNode newOne = new NumberNode(i);
		
		// empty list? Handle here and return.
		if (head == null) {
			// fill in logic to create first node in the list.
			return;
		}
		
		// is the new node to be added smaller or equal to head? Just prepend, then.
		if (i <= head.value) {
			// fill in logic to prepend node here.
			return;
		}
		
		// ok. Let's traverse the list with two references 'prev' and 'node' where
		// you know that 'prev.next == node'.
		
		// Now we need to find a node such that 
		//      (1) the value of the prev node is less than or equal to i 
		//      (2) either (a) node is null because prev was the last node in the list
		//                 (b) the value of node is greater than or equal to i
		NumberNode prev = head;
		NumberNode node = head.next;
		while (prev != null) {
			// fill in logic here. Given the information above, you will construct
			// an if statement of the following form:
			// 
			//    if (condition1 && (condition2a || condition2b)) {
			//        DO SOMETHING
			// 
			//        return;
			//    }
			
			
			// advance
			node = node.next;
			prev = prev.next;
		}
		
		// note that we will never get to this point in the program, because if you
		// set up the while loop properly, it will take care of its business.
	}
	
	/**
	 * Return String representation of the number list.
	 * 
	 * Format is {e1,e2,e3}
	 */
	public String toString () {
		String ret = "{";
		NumberNode node = head;
		
		// Go through list and append each value. Take care to only add
		// commas when we know there are more nodes in the list (i.e., node.next != null)
		while (node != null) {
			ret += node.value;
			if (node.next != null) {
				ret += ",";
			}
			
			node= node.next;
		}
		ret += "}";
		
		return ret;
	}
	
	
}
