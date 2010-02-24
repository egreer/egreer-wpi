package dec07.v2;

import java.util.ArrayList;

/**
 * A Molecule can have any number of Compounds.
 *
 * A Compound is formed from an Element and an int count.
 * 
 * Cardinality: one-to-many
 * 
 * @author heineman
 */
public class Molecule {

	/** Instance variable. Contents are of type Compound. */
	ArrayList   compounds;
	
	/**
	 * Constructor for Molecule. 
	 */
	public Molecule () {
		compounds = new ArrayList();
	}
	
	/**
	 * Add a compound to the end of this molecule.
	 *
	 * @param c    compound to be added into the Molecule.
	 */
	public void add (Compound c) {
		compounds.add(c);
	}
	
}
