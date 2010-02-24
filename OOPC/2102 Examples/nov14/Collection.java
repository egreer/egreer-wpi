package nov14;

/**
 * Represents a collection of Item objects.
 * 
 * From a collection, one can calculate (a) the total weight; (b) the total value.
 * 
 * @author heineman
 */
public class Collection {
	/** First one in Inventory. */
	ItemNode head;
	
	/** 
	 * Default constructor.
	 * 
	 * Initializes collection to be empty.
	 */
	public Collection () {
		head = null;
	}
	
	/** 
	 * Compute the total weight of all items in the list. 
	 * @return   Total weight of all items in the collection
	 */
	public double totalWeight () {
		double weight = 0.0;

		// Simply add each one in turn.
		ItemNode n = head;
		while (n != null) {
			weight += n.item.weight;
			
			n = n.next; // ADVANCE
		}
		
		return weight;
	}
	
	/** 
	 * Compute the total weight of all items in the list. 
	 * @return   Total weight of all items in the collection
	 */
	public double totalCost () {
		double weight = 0.0;

		// Simply add each one in turn.
		ItemNode n = head;
		while (n != null) {
			weight += n.item.weight;
			
			n = n.next; // ADVANCE
		}
		
		return weight;
	}
	
	/**
	 * Insert item into the collection.
	 * 
	 * @param it    Item to be inserted. 
	 */
	public void insert (Item it) {
		ItemNode newNode = new ItemNode (it);
		
		if (head == null) {
			head = newNode;
		} else {
			// pre-pend
			newNode.next = head;
			
			// re-attach
			head = newNode;
		}
	}
	
	/** 
	 * Produce String representation by concatenating description of each item in collection. 
	 * 
	 * Representation will be of form [item1\nitem2\n...] 
	 */
	public String toString () {
		String s = "[";
		
		ItemNode node = head;
		while (node != null) {
			s = s + node.toString();
			
			node = node.next;    // advance
			if (node != null) {  // if we haven't yet reached the end, then insert '\n' carriage return
				s = s + "\n";
			}
		}
		
		s = s + "]";
		return s;
	}
}