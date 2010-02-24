package nov27;

/**
 * Example showing how unchecked exceptions can be used with code.
 * 
 * @author heineman
 */
public class BusinessItem extends Item {

	/** Value of BusinessItem. */
	double value;
	
	/**
	 * Construct a BusinessItem.
	 * 
 	 * Pre condition: value > 0 and Item's pre-conditions
	 * 
	 *
	 * @param name     name of item
	 * @param weight   weight in ounces
	 * @param value    value of item
	 * @exception      IllegalArgumentException if arguments are invalid.
	 */
	public BusinessItem(String name, double weight, double value) {
		super(name, weight);

		if (value <= 0) { 
			throw new IllegalArgumentException ("Value can't be less than zero.");
		}
		
		this.value = value;
	}

	

}
