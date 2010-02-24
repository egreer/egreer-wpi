package hw4;

public class MoleculeNode {

	/** Value. */
	Molecule value;
	
	/** count. */
	int count;
	
	/** next one. */
	MoleculeNode next;

	/**
	 * Construct a Molecule Node object.
	 * 
	 * @param m
	 */
	public MoleculeNode (Molecule m) {
		this(m, 1);
	}
	
	/**
	 * Construct a Molecule Node object.
	 * 
	 * @param m
	 * @param ct
	 */
	public MoleculeNode (Molecule m, int ct) {
		this.value = m;
		count = ct;
		next= null;
	}
	
	/** Reasonable toString method. */
	public String toString () {
		String rep = "";
		if (count !=1) {
			rep += count;
		}
		
		return rep + value.toString();
	}
	
}
