package nov14;

/**
 * Represents a business item, which adds the cost property to the Item.
 *
 * @author heineman
 */
public class BusinessItem extends Item {
	
	/** Cost of item. */
	double value;
	
	/** A Business item has name, weight and a value. */
	public BusinessItem (String name, double weight, double value) {
		// This must be the first statement in the method definition
		super (name, weight);
		
		this.value = value;
	}
	
	/**
	 * Return value of item.
	 * 
	 * @return
	 */
	public double getValue() {
		return value;
	}
	
	/** 
	 * Comparing a BusinessItem against another BusinessItem must take the cost into account.
	 * 
	 * Use parent class to compute equality of inherited information.
	 */
	public boolean equals (BusinessItem bi) {
		return ((value == bi.value) && super.equals(bi));
	}
	
	/** 
	 * Return proper format of Business Item, incorporating calculated toString() of parent class.
	 */
	public String toString () {
		return "[BusinessItem " + super.toString() + " (value:" + value + ")]";
	}
}
