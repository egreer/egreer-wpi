package dec12;

public class NumberList {

	/** Head of the list. */
	NumberNode   head;
	
	/** Tail of the list. */
	NumberNode   tail;
	
	
	/**
	 * Prepend at the front.
	 * 
	 * @param i
	 */
	public void prepend (int i) {
		// empty list?
		if (head == null) {
			head = new NumberNode(i);
			tail = head;
			return;
		}
		
		// prepend does not affect tail.
		NumberNode node = new NumberNode(i);
		node.next = head;
		head = node;
	}
	
	/**
	 * Return the NumberNode object that represents this value (or null if none exists).
	 * 
	 * Bad idea to expose the NumberNode objects to the outside world.
	 * 
	 * @param i  sought for number
	 * @return   Node or null if not found.
	 */
	public NumberNode find (int i) {
		NumberNode node = head;
		while (node != null) {
			if (node.value == i) {
				return node;
			}
			
			node = node.next;  // advance
		}
		
		return null;  // nothing here.
	}
	
	/**
	 * Append at end. 
	 * 
	 * @param i
	 */
	public void append (int i) {
		// empty list?
		if (head == null) {
			head = new NumberNode(i);
			tail = head;
			return;
		}
		
		// append does not affect head
		tail.next = new NumberNode(i);
		tail = tail.next;
	}
	
	/**
	 * Reasonable toString method. 
	 */
	public String toString () {
		String s = "[";
		NumberNode node = head;
		while (node != null) {
			s += node.value;
			
			if (node.next != null) {
				s += ",";
			}
			
			node = node.next;
		}
		
		return s + "]";
	}
	
	// Note that implementing remove means you are going to have to take care of 
	// the cases where you are removing either the head or the tail of the list.

}
