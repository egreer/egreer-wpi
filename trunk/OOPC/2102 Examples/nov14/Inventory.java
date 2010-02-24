package nov14;

/**
 * An inventory allows users to add Items to the collection and then print
 * out the total weight and the full report of all items.
 * 
 * Make it possible to count the items in an inventory.
 * 
 * @author heineman
 */
public class Inventory {

	/** Keep track of items. */
	ItemNode   head;
	
	/** Default constructor. */
	public Inventory() {
		head = null;
	}
	
	/** Add this item to the end of the inventory. */
	public void add (Item i) {
		// 1. An empty list     => set head to be the new node

		if (head == null) {
			head = new ItemNode(i);
			return;
		} 
		
		// use while loop to identify the LAST node in the list
		//    the last NODE in the list has its 'next' reference to be null
		ItemNode node = head;   // INITIALIZE
		while (node.next != null) {
			// processing
			
			node = node.next;   // ADVANCE			
		}
		
		// node points to the LAST one in the list.
		node.next = new ItemNode(i);
	}
	
	public void output () {
		System.out.println ("Inventory");
		// go over all of the list
		for (ItemNode node = head; node != null; node = node.next) {
			System.out.println (node);
		}
	}
	
	/** Compute number of elements in the inventory. */
	public int count () {
		int sum = 0;
		
		ItemNode node = head;  // INITIALIZATION
		while (node != null) {
			// increment weight
			sum ++;
				
			node = node.next;    // ADVANCE			
		}
		
		return sum;
	}
	
	/** Calculate weight of the total inventory. */
	public double getWeight () {
		double sum = 0;
		
		ItemNode node = head;  // INITIALIZATION
		while (node != null) {
			// increment weight
			sum += node.item.weight; 
				
			node = node.next;    // ADVANCE			
		}
		
		return sum;
	}

}
