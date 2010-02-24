package hw4;


/**
 * This class forms the basis for the chain of elements in a molecule.
 * 
 * @author heineman
 */
public class ElementNode {

	/** The Element value of interest. */
	Element value;
	
	/** Number of repetitions of the Element. */
	int count;
	
	/** 
	 * The next ElementValue in the List of Element Values. It may be 
	 * null (this is the last in the List of Element Values) or it may 
	 * point to the next one. 
	 */
	ElementNode next;

	/**
	 * Construct an ElementValue to be inserted into a molecule to represent the
	 * element in a given number of repetitions.
	 * 
	 * @param e         Element that forms the element value.
	 * @param count     Number of repetitions
	 */
	public ElementNode(Element e, int count) {
		this.value = e;
		this.count = count;
	}
	
	/**
	 * Return its element as a String representation.
	 * 
	 * Note that H2 is hydrogen.
	 */
	public String toString () {
		String rep = value.getSymbol();
		if (count > 1) {
			rep = rep + count;
		}

		return rep;
	}
}
