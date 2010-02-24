package hw3;

/**
 * Represents a molecule
 * 
 * @author George
 * @ref    http://en.wikipedia.org/wiki/Molecule
 */
public class Molecule {
	
	/** Element array. */
	Element [] elements = new Element[10];
	
	/** Counts of the respective elements. */
	int [] counts       = new int[10];
	
	/** Number of elements. */
	int numElements = 0;
	
	/**
	 * Construct a molecule consisting of a single element.
	 * 
	 * @param e       Element which forms the molecule
	 */
	public Molecule (Element e) {
		this(e, 1);
	}
	
	/**
	 * Construct a molecule consisting of a single element repeated a fixed number of times
	 * @param e          Element which forms the molecule
	 * @param count      Number of this element in the molecule
	 */
	public Molecule (Element e, int count) {
		numElements = 1;
		
		elements[0] = e;
		counts[0] = count;
	}
	
	/**
	 * Construct a molecule consisting of a single element.
	 * 
	 * The number of elements in 'els' must match the number of elements in 'count'.
	 * 
	 * @param els     Array of Elements which forms the molecule
	 * @param count   Array of ints that reflect the counts for the respective elements.
	 */
	public Molecule (Element []els, int []count) {
		if (els.length == count.length) {
			elements = els;
			counts = count;
			numElements = els.length;
		} else {
			// no good default value here.
			numElements = 0;
		}
	}
	
	/**
	 * Adds an element (with count 1)
	 * @param e           Element which is to be added to the molecule
	 * @return            true if added; false if full.
	 */
	public boolean add (Element e) {
		return add (e, 1);
	}
	
	/**
	 * Adds an element (with repetition count)
	 * @param e           Element which is to be added to the molecule
	 * @param count       number of repetitions of that element.
	 * @return            true if added; false if full.
	 */
	public boolean add (Element e, int count) {
		if (numElements == elements.length) {
			return false;
		}
		
		elements[numElements] = e;
		counts[numElements] = count;
		
		numElements++;
		return true;
	}
	
	/**
	 * Return string representation of this molecule.
	 * 
	 */
	public String toString () {
		String rep = "";
		for (int i = 0; i < numElements; i++) {
			rep += elements[i].getSymbol();
			if (counts[i] > 1) {
				rep += counts[i];
			}
		}
				
		return rep;
	}
	
	/**
	 * Return the mass of the molecule.
	 * 
	 * @return    calculated mass, taking into account element masses and their counts.
	 */
	public float getMass() {
		float sum = 0;
		for (int i = 0; i < numElements; i++) {
			sum += elements[i].getMass()*counts[i];
		}
		
		return sum;
	}

	/**
	 * Determine whether this object is equivalent to another molecule.
	 * 
	 * @param other       other molecule to be compared against
	 * @return            true if the molecules have the identical structure; false otherwise.
	 */
	public boolean equals (Molecule other) {
		if (numElements != other.numElements) {
			return false;
		}
		
		// different in any element or count? Return in the false.
		for (int i = 0; i < numElements; i++) {
			// not the same element?
			if (!elements[i].equals(other.elements[i])) {
				return false;
			}
			
			// not the same count?
			if (counts[i] != other.counts[i]) {
				return false;
			}
		}
		
		// must be the same.
		return true;
	}
	
	/**
	 * Determine hash code for this element.
	 * @return            hash value
	 */
	public int hashCode() {

		// different in any element or count? Return in the false.
		int hash = 0;
		for (int i = 0; i < numElements; i++) {
			hash += counts[i] * elements[i].hashCode();
		}
		
		return hash;
	}
	
}
