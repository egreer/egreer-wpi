package nov14;

/**
 * Represents an item in an inventory.
 * 
 * @author heineman
 */
public class Item {
	
	/** Has a name. */
	String name;
	
	/** Has a weight. */
	double weight;
	
	/**
	 * Construct Item whose information is represented as a double. 
	 * 
	 * @param name        Name of item
	 * @parma d           item price as a double
	 */
	public Item(String name, double w) {
		this.name = name;
		this.weight = w;
	}
	

	// default methods that all classes should be providing.

	/**
	 * Determines if two Items are equivalent.
	 */
	public boolean equals (Item i) {
		return i.name.equals(name) && (i.weight == weight);
	}
		
	/** 
	 * Determine hashCode for an Item.
	 * 
	 * Any value will do as long as you meet the specification for hashCode, namely, that 
	 * two equals() objects have the same int value when hashCode() is invoked.
	 */
	public int hashCode() {
		return (int) (name.hashCode() * weight);
	}
	
	/** Default String representation. */
	public String toString() {
		return name + " (" + weight + " oz.)";
	}
}
