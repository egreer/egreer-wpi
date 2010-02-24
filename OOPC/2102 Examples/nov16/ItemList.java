package nov16;

public class ItemList {
	/* Knows first in the list. */
	ItemNode head;
	
	/**
	 * Default constructor.
	 */
	public ItemList () {
		head = null;
	}
	
	/**
	 * Remove the first item in the list that equals() this item.
	 * @param it
	 */
	public void remove (Item it) {
		// 1. List might be empty
		if (head == null) {
			return;
		}
		
		// 2. The object being removed is the head! In this case, we simply
		//    truncate the list.
		if (it.equals(head.item)) {
			head = head.next;
			return;
		}
		
		// 3. Ok. Now we know that if we find an item that matches, there must 
		//    be a preceding item, which we refer to by variable 'prev'. Note 
		//    how prev always points 'next' to the node.
		ItemNode prev = head;
		ItemNode node = head.next;
		while (node != null) {
			// if the node ever is the one we want to remove, then we adjust
			// the 'next' link of prev to pass it by. Make sure you understand
			// this for the homeworks.
			if (it.equals(node.item)) {
				prev.next = node.next;    // remove 'node' from the list by linking around it
				return;
			}
			
			node = node.next;   // advance, and ensure that prev.next refers to node
			prev = prev.next;
		}
		
		// if we get here, we have gone through the whole list.
	}
	
	
	/**
	 * prepend, once again.
	 * 
	 * @param it
	 */
	public void prepend (Item it) {
		ItemNode node = new ItemNode (it);
		
		// empty? Make the first in the list.
		if (head == null) {
			head = node;
		} else {
			// reassign links, and make new node the head.
			node.next = head;
			head = node;
		}
	}
	
	/**
	 * Count number in list
	 */
	public int count () {
		int ct = 0;
		ItemNode node = head;
		
		while (node != null) {
			ct++;
			
			node = node.next;  // ADVANCE
		}
		return ct;
	}
	
}
