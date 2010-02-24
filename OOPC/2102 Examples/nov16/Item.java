package nov16;

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
	
	/** UPC code. */
	String upc;
	
	/** default unknown upc code. */
	public static final String unknown = "unknown UPC"; 
	
	/**
	 * Construct Item whose information is represented as a double with upc
	 * 
	 * @param name        Name of item
	 * @parma d           item price as a double
	 * @param upc         String upc code
	 */
	public Item(String name, double w, String upc) {
		this.name = name;
		this.weight = w;
		this.upc = upc;
	}
	
	/**
	 * Construct Item whose information is represented as a double. 
	 * 
	 * @param name        Name of item
	 * @parma d           item price as a double
	 */
	public Item(String name, double w) {
		this (name, w, unknown);
	}
	

	// default methods that all classes should be providing.

	/**
	 * Determines if two Items are equivalent.
	 */
	public boolean equals (Object o) {
		if (o == null) {
			return false;
		}
		
		// replace the condition of the if statement below to
		// be (o.getClass() == getClass()) and then the proper behavior
		// will result, enabling us to compare BusinessItem only against
		// BusinessItem objects.
		
		if (o instanceof Item) {
			Item it = (Item) o;
			return it.name.equals(name) && (it.weight == weight);
		}
		
		return false;  // nope
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
