package egreer.hw;

/**
 * Represents a chemical element in the universe.
 * 
 * To date, there are 117 chemical elements discovered. Each has a name, a shorter symbol,
 * and an atomic mass. Some elements are unstable, in which case this information needs to
 * be recorded as well.  
 * 
 * @author egreer
 */

public class Element {

	/** Name. */
	String name;
	
	/** Symbol */
	String symbol;
	
	/** Number */
	int number;
		
	/** Mass */
	float mass;
	
	/** Stability. Element might be unstable. */
	boolean stable;
	
	/**
	 * Constructor for a chemical element
	 * 
	 * @param name     Name of Element
	 * @param symbol   Symbol of the element
	 * @param number   Atomic Number
	 * @param stable   True if this email is stable.
	 */
	public Element(String name, String symbol, int number, boolean stable){
		this.name = name;
		this.symbol = symbol;
		this.number = number;
		this.stable =  stable;
		
	}

	/** 
	 * Return the name for this chemical element. 
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/** 
	 * Return the mass for this chemical element. 
	 *
	 * @return atomic mass
	 */
	public float getMass() {
		return mass;
	}

	/** 
	 * Return the number for this chemical element. 
	 *
	 * @return atomic number
	 */
	public int getNumber() {
		return number;
	}

	/** 
	 * Determine if this chemical element is unstable.
	 *
	 * @return true if the element is unstable; false otherwise.
	 */
	public boolean isUnstable() {
		return !stable;
	}

	/** 
	 * Return the chemical symbol for this chemical element. 
	 *
	 * A chemical element is either one, two, or three characters. Three character symbols
	 * are temporary ones, pending confirmation, and always start with a 'U'.
	 *
	 * @return a String of either one, two, or three characters. 
	 */
	public String getSymbol() {
		return symbol;
	}

	
	/**
	 * Return String representation of Element.
	 * 
	 * @return String representation of element.
	 */
	public String toString(){
		if(stable){
			return symbol + " " + mass;
		
		}else {
			return symbol + " [" + mass + "]";
		}
		
	}
	
	public boolean equals (Object o){
		if (o == null) return false;
		if (o instanceof Element){
			Element other = (Element) o;
			return other.symbol.equals(symbol) &&
					other.name.equals(name) &&
					other.number == number &&
					other.mass == mass &&
					other.stable == stable;
		}
		
		//nope
		return false;
	}

}
