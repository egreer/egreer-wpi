package egreer.hw4;

public class ElementNode {
	/** Function being captured. */
	Element name;
	
	/** Number of atoms */
	int atoms;
	
	/** Next element in the list. */
	ElementNode next;
	
	/** Constructor for ElementNode. */
	public ElementNode (Element f) {
		name = f;
		atoms = 1;
		next = null;
	}
	
	/** Constructor for ElementNode with atoms */
	public ElementNode (Element f, int i) {
		name = f;
		atoms = i;
		next = null;
	}

}
