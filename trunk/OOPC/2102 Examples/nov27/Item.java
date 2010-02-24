package nov27;

/**
 * Properly designed class for representing an Item.
 * 
 * Items have weight > 0 and a name.
 * 
 * @author heineman
 */
public abstract class Item {

	/** Item Name. */
	String name;
	
	/** Item Weight. */
	double weight;
	
	/**
	 * Construct an item.
	 * 
	 * Pre condition: name != null and weight > 0
	 * 
	 * @param name
	 * @param weight
	 * @exception IllegalArgumentException if arguments invalid.
	 */
	public Item (String name, double weight) {
		if (name == null) {
			throw new IllegalArgumentException ("Name can't be null");
		}
		
		if (weight <= 0) {
			throw new IllegalArgumentException ("Invalid weight of " + weight);
		}
		
		this.name = name;
		this.weight = weight;
	}

}
