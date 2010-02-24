package dec12;

public class NumberNode {

	/** value. */
	int value;
	
	/** Next. */
	NumberNode next;
	
	public NumberNode (int i) {
		this.value = i;
		this.next = null;
	}
	
	/**
	 * The following horrible method actually works, but please don't believe this is
	 * a good idea.
	 * 
	 * The concept appears to be "delete this NumberNode from the NumberList".
	 * 
	 * Note how this method [as bad as it is] is actually worse since it doesn't know
	 * or pay attention to the fact that NumberList stores 'head' and 'tail' values.
	 * 
	 * Calling this method may damage the integrity of the list.
	 * 
	 * Note that this 'instance method' never actually updates any of this object's instance 
	 * values! A SURE SIGN that this is in the wrong place.
	 */
	public void deleteFrom (NumberList node) {
		if (node.head == this) {
			node.head = next;
		} else {
			NumberNode n = node.head;
			while (n.next != this) {
				n = n.next;
				
				// run off the end? Gone!
				if (n == null) {
					return;
				}
			}
			
			// if we get here, then the next of 'n' points to this, so we can remove
			n.next = next;
		}
	}

}
