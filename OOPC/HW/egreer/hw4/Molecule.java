package egreer.hw4;


/**
 * Represents a molecule
 * 
 * @author egreer
  */
public class Molecule {
	
	/** Element List.*/
	ElementNode head = null;
	
	
	/**Constructs a molecule with no elements*/
	public Molecule (){
		head = null; 
	}
	
	/**Constructs a molecule with given element not repeated.
	 * 
	 * @param e		Element of the molecule 
	 */ 
	public Molecule (Element e){
		head = new ElementNode(e);
	}
	
	
	/**Constructs a molecule with given element repeated a defined amout of times.
	 * 
	 * @param e		Element of the molecule 
	 * @param count	Number of atoms of that element
	 */ 
	public Molecule (Element e, int count){
		head = new ElementNode(e , count);
	}
	
	
	public Molecule(Element[] elements, int[] is) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Return string representation of this molecule.
	 * 
	 */
	public String toString(){
		String rep = "";
		
		ElementNode test = head;
		while (test != null) {
			rep += test.name.getSymbol();
			rep += Integer.toString(test.atoms);
			test = test.next;
			}
						
		return rep;
	}
	
	
	
	/** Adds a solitary atom to the Molecule 
	 * 
	 * @param e 	Element to add to the Molecule
	 * @return	 	always returns true
	 */
	
	public boolean add(Element e){
		
		ElementNode node = head;
		
		while (node.next != null) {
			node = node.next;
		} 
		
		node.next = new ElementNode(e);
		
		return true;
	}
	
	/** Adds several atoms of an Element to molecule  
	 * 
	 * @param e		Element to add to the Molecule
	 * @param count Number of atoms of specified Element 
	 * @return		Always returns true
	 */	
	public boolean add(Element e, int count){
		
		ElementNode node = head;
		
		while (node.next != null) {
			node = node.next;
		} 
		
		node.next = new ElementNode(e , count);
		
		return true;
	}
	
	
	/** getMass returns the mass of a molecule.
	 * 
	 * @return Returns total mass in form Float
	 */
	public float getMass() {
		float sum = 0;
		
		ElementNode node = head;
		while (node != null) {
			sum += node.name.getMass()*node.atoms;
			
			node = node.next;
		}
		return sum;
	}
	
	public boolean equals(Object o){
		Molecule it = (Molecule) o;
		
		ElementNode node = head;
		while (node != null){
			if ( (it.head.name == head.name) && (it.head.atoms == head.atoms)){
				return true;
				
			}
		}
		return false;
	
	}
		
		
	/**
	 * Determine hash code for this element.
	 * @return            hash value
	 */
	public int hashCode() {

		int hash = 0;
		ElementNode node = head;
		while (node != null) {
			hash += head.atoms * head.name.hashCode();
		}
			
		return hash;
	}
		
}
	
	
	
	
	
	
//	
//	/**
//	 * Construct a molecule consisting of a single element.
//	 * 
//	 * @param e       Element which forms the molecule
//	 */
//	public Molecule (Element e) {
//		this(e, 1);
//	}
//	
//
//	
//	/**
//	 * Construct a molecule consisting of a single element.
//	 * 
//	 * The number of elements in 'els' must match the number of elements in 'count'.
//	 * 
//	 * @param els     Array of Elements which forms the molecule
//	 * @param count   Array of ints that reflect the counts for the respective elements.
//	 */
//	public Molecule (Element []els, int []count) {
//		if (els.length == count.length) {
//			elements = els;
//			counts = count;
//			numElements = els.length;
//		} else {
//			// no good default value here.
//			numElements = 0;
//		}
//	}
//	
//	/**
//	 * Adds an element (with count 1)
//	 * @param e           Element which is to be added to the molecule
//	 * @return            true if added; false if full.
//	 */
//	public boolean add (Element e) {
//		return add (e, 1);
//	}
//	
//	/**
//	 * Adds an element (with repetition count)
//	 * @param e           Element which is to be added to the molecule
//	 * @param count       number of repetitions of that element.
//	 * @return            true if added; false if full.
//	 */
//	public boolean add (Element e, int count) {
//		if (numElements == elements.length) {
//			return false;
//		}
//		
//		elements[numElements] = e;
//		counts[numElements] = count;
//		
//		numElements++;
//		return true;
//	}
//	
//
//	
//	/**
//	 * Return the mass of the molecule.
//	 * 
//	 * @return    calculated mass, taking into account element masses and their counts.
//	 */
//	public float getMass() {
//		float sum = 0;
//		for (int i = 0; i < numElements; i++) {
//			sum += elements[i].getMass()*counts[i];
//		}
//		
//		return sum;
//	}
//
//	/**
//	 * Determine whether this object is equivalent to another molecule.
//	 * 
//	 * @param other       other molecule to be compared against
//	 * @return            true if the molecules have the identical structure; false otherwise.
//	 */
//	public boolean equals (Molecule other) {
//		if (numElements != other.numElements) {
//			return false;
//		}
//		
//		// different in any element or count? Return in the false.
//		for (int i = 0; i < numElements; i++) {
//			// not the same element?
//			if (!elements[i].equals(other.elements[i])) {
//				return false;
//			}
//			
//			// not the same count?
//			if (counts[i] != other.counts[i]) {
//				return false;
//			}
//		}
//		
//		// must be the same.
//		return true;
//	}
//	
//	/**
//	 * Determine hash code for this element.
//	 * @return            hash value
//	 */
//	public int hashCode() {
//
//		// different in any element or count? Return in the false.
//		int hash = 0;
//		for (int i = 0; i < numElements; i++) {
//			hash += counts[i] * elements[i].hashCode();
//		}
//		
//		return hash;
//	}
//	
//}
