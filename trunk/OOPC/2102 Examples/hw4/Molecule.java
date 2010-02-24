package hw4;

import hw4.ElementNode;

/**
 * Represents a molecule
 * 
 * @author George
 * @ref    http://en.wikipedia.org/wiki/Molecule
 */
public class Molecule {
	
	/** Head in the list of elements. */
	ElementNode head;

	/** Compare. */
	public boolean equals (Molecule m) {
		ElementNode n1 = head;
		ElementNode n2 = m.head;
		
		// see if any discrepancies.
		while (n1 != null && n2 != null) {
			if ((!n1.value.equals(n2.value)) ||
				(n1.count != n2.count)) {
				return false;
			}
			
			n1 = n1.next;
			n2 = n2.next;
		}
		
		return (n1 == null && n2 == null);
	}
	
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
		head = new ElementNode (e, count);
	}
	
	/**
	 * Adds an element (with repetition count of 1)
	 * @param e           Element which is to be added to the molecule
	 */
	public boolean add (Element e) {
		return add(e, 1);
	}
	
	/** return the mass of this molecule. */
	public float getMass () {
		float mass = 0;
		ElementNode list = head;
		while (list != null) {
			mass += list.value.getMass() * list.count;
			
			list = list.next;   // advance
		}
				
		return mass;
	}
	
	/**
	 * Adds an element (with repetition count)
	 * @param e           Element which is to be added to the molecule
	 * @param count       number of repetitions of that element.
	 */
	public boolean add (Element e, int count) {
		if (head == null) {
			head = new ElementNode (e, count);
		} else {
			// advance until list is pointing to the last in the list.
			ElementNode list = head;
			while (list.next != null) {
				list = list.next;
			}
			
			list.next = new ElementNode (e, count);
		}
		
		return true;
	}
	
	/**
	 * Remove an element (with repetition count).
	 * 
	 * If too many are requested to be removed, then false is returned, but the Molecule
	 * is still updated.
	 */
	public boolean remove (Element e, int count) {
		// if empty, then can only return true if zero is passed in.
		if (head == null) {
			return (count == 0);
		}
		
		// only one in the list. Be patient and cover all cases.
		if (head.next == null) {
			if (!head.value.equals(e)) return false;
			if (count > head.count) {
				head = null;
				return false;
			} else if (count == head.count) {
				head = null;
				return true;
			} else {
				head.count -= count;
				return true;
			}
		}
		
		// now we know that there is always one preceding it.
		ElementNode prev = head;
		ElementNode node = head.next;
		while (node != null) {
			// found. 
			if (node.value.equals(e)) {
				// can do it all here.
				if (count < node.count) {
					node.count -= count;
					return true;
				} else if (count == node.count) {
					prev.next = node.next;
					return true;
				} else {
					// still have more to go.
					count -= node.count;
					prev.next = node.next;
				}
			}
		}
		
		return true;
	}

	/**
	 * Determine hash code for this Molecule.
	 * 
	 * Incorporate element hash codes as well as counts.
	 * 
	 * @return            hash value
	 */
	public int hashCode() {

		// different in any element or count? Return in the false.
		int sum = 0;
		ElementNode node= head;
		while (node != null) {
			sum += node.value.hashCode() * node.count;
			
			node = node.next;
		}
		
		return sum;
	}
	
	/**
	 * Return string representation of this molecule.
	 */
	public String toString () {
		String rep = "";
		ElementNode list = head;
		while (list != null) {
			rep += list.toString();
			
			list = list.next;   // advance
		}
				
		return rep;
	}

}
