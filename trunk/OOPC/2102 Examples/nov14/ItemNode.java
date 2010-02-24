package nov14;

/**
 * Represents an element within a linked list of Items.
 * 
 * @author heineman
 */
public class ItemNode {
	/** Item. */
	Item item;
	
	/** next one in list. */
	ItemNode next;
	
	/**
	 * Default constructor.
	 * 
	 * @param it     Item being referenced.
	 */
	public ItemNode (Item it) {
		this.item = it;
		this.next = null;
	}
	
	/** Expose inner string representation. */
	public String toString() {
		return item.toString();
	}
	
}
