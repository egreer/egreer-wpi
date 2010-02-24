package bagPacker;

/** A binary constraint relates two Items and a type of constraint. 
 * If the constraint is a mutual inclusion then it also relates the bags together. 
 * 
 * @author Eric Greer
 */
public class BinaryConstraint {
	Item item1;
	Item item2;
	Bag bag1;
	Bag bag2;
	int type;

	/**
	 * Constructor for a binary constraint object
	 * 
	 * @param item1	First item for equal and not equal
	 * @param item2	Second item for equal and not equal
	 * @param type	1 = equal 2 = not equal
	 */
	public BinaryConstraint(Item item1, Item item2, int type) {
		this(item1, item2, null, null, type);
	}

	/**
	 * Constructor for a binary constraint object
	 * 
	 * @param item1	First item for equal and not equal
	 * @param item2	Second item for equal and not equal
	 * @param bag1	First bag for mutual inclusion
	 * @param bag2	Second bag for mutual inclusion
	 * @param type	 1 = equal 2 = not equal 3 = mutual inclusion
	 */
	public BinaryConstraint(Item item1, Item item2, Bag bag1, Bag bag2, int type) {
		this.item1 = item1;
		this.item2 = item2;
		this.bag1 = bag1;
		this.bag2 = bag2;
		this.type = type;
	}

}
