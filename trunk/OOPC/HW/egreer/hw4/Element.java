package egreer.hw4;

/**
 * Represents a chemical element in the universe.
 * 
 * To date, there are 117 chemical elements discovered. Each has a name, a shorter symbol,
 * and an atomic mass. Some elements are unstable, in which case this information needs to
 * be recorded as well.  
 * 
 * @author heineman
 */
public class Element {

	/** Name. */
	String name;
	
	/** Symbol. */
	String symbol;
	
	/** Number. */
	int number;
	
	/** Mass. If the element is unstable, this is to be a clear integer. */
	float mass;
	
	/** Stability. Element might be unstable. */
	boolean stable;
	
	/**
	 * Constructor for a stable element.
	 * 
	 * @param name      Name of element.
	 * @param symbol    Symbol of that element.
	 * @param number    The atomic number.
	 * @param mass      The atomic mass.
	 */
	public Element (String name, String symbol, int number, float mass) {
		this (name, symbol, number, mass, true);
	}

	/**
	 * Constructor for a chemical element.
	 * 
	 * @param name      Name of element.
	 * @param symbol    Symbol of that element.
	 * @param number    The atomic number.
	 * @param mass      The atomic mass if stable; The mass of longest isotope, if not.
	 * @param stable    True if this element is stable.
	 */
	public Element (String name, String symbol, int number, float mass, boolean stable) {
		this.name = name;
		this.symbol = symbol;
		this.mass = mass;
		this.number = number;
		this.stable = stable;
	}
	
	/** 
	 * Frequence of chemical elements in the universe. 
	 *
	 * Since this list is by element number, I pad frequency[0] with zero, since
	 * there is no element with atomic number of 0.
	 * 
	 * This only goes up to element 83.
	 * 
	 * http://www.orionsarm.com/science/Abundance_of_Elements.html
	 */
	static int frequency[] = {0,1,2,8,6,7,10,14,12,26,16,
							18,13,20,28,11,24,15,25,19,22,
							27,17,9,23,30,32,29,4,21,40,
							36,38,34,3,41,5,31,37,33,35,
							39,54,56,58,52,60,42,44,82,50,
							62,78,46,48,76,57,59,64,66,68,
							70,77,53,55,45,47,80,51,72,83,
							63,65,79,67,74,49,81,90,75,92,
							69,71,73};
	
	/** if invertedIndex[i] == b, then the hashCode bin for element i is bin b */
	static int invertedIndex[] = {0, 0, 1, 6, 6, 6, 3, 4, 2, 6, 4, 6, 5, 6, 4, 6, 5};
	
	/**
	 * Assume we want to divide the elements into groups that are themselves 
	 * to be searched for matches. The idea behind this hashCode() implementation
	 * is that one will likely be searching for elements that are more common than
	 * others. Information based on:
	 * 
	 *  http://www.orionsarm.com/science/Abundance_of_Elements.html
	 *  
	 * Bin 0: 1 H (Hydrogen)
	 * Bin 1: 2 He (Helium)
	 * Bin 2: 8 O (Oxygen)
	 * Bin 3: 6 C (Carbon)
	 * Bin 4: (N,Ne,Si)     7 Nitrogen, 10 Neon, 14 Silicon
	 * Bin 5: (Mg, Fe, S)   12 Magnesium, 26 Iron, 16 Sulfur
	 * Bin 6: Elements 3, 4, 5, 9, 11, 13, 15,
	 * Bin 7: Elements 17-42
	 * Bin 8: Elements 43-68
	 * Bin 9: Elements 69-94
	 * Bin 10: Elements 95-118 
	 */
	public int hashCode() {
		if (number >= invertedIndex.length) {
			// 17-42 is 7     so 6+(number+9)/26
			// 43-68 is 8
			// 69-94 is 9     as more elements discovered, scales up
			return 6+(number+9)/26;
		}
		
		return invertedIndex[number];
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
	public String toString () {
		if (stable) {
			return symbol + " " + mass;
		} 

		int isotopeMass = (int) mass;
		return symbol + " [" + isotopeMass + "]";
	}
	
	/**
	 * Determine equality based solely on the atomic symbol.
	 * 
	 * @return   true if element objects are the same (based upon atomic symbol).
	 */
	public boolean equals (Object o ) {
		if (o == null) return false;
		if (o instanceof Element) {
			Element other = (Element) o;
			return other.symbol.equals(symbol) &&
					other.name.equals(name) &&
					other.number == number &&
					other.mass == mass &&
					other.stable == stable;
		}
		
		// nope
		return false;
	}
}
