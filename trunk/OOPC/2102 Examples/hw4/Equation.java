package hw4;

/**
 * Represents an Equation whose left and right side is a sequence of Molecules.
 * 
 * 
 * @author heineman
 *
 */
public class Equation {

	/** Left hand side. */
	MoleculeList lhs;
	
	/** Right hand side. */
	MoleculeList rhs;
	
	/**
	 * Build an equation with a left-hand and right-hand side.
	 * 
	 * @param left
	 * @param right
	 */
	
	public Equation (MoleculeList left, MoleculeList right) {
		lhs = left;
		rhs = right;
	}
	
	/**
	 * Output string representation of the equation. 
	 */
	public String toString() {
		return lhs + " = " + rhs;
	}

	/**
	 * Two equations are equal if their respective LHS and RHS match.
	 * 
	 * @param eq
	 * @return
	 */
	public boolean equals (Equation eq) {
		return lhs.equals(eq.lhs) && rhs.equals(eq.rhs);
	}
	
	/**
	 * Determine if there are the same number of elements on the left hand side
	 * as there are on the right hand side.
	 * 
	 * @return
	 */
	public boolean isBalanced() {
		// Build up a MoleculeList
		MoleculeList base = new MoleculeList();
		
		MoleculeNode node = lhs.head;
		while (node != null) {
			Molecule m = node.value;
			int mct = node.count;
			
			ElementNode en = m.head;
			while (en != null) {
				Element e = en.value;
				int ct = en.count;
				
				base.add(new Molecule(e, 1), mct*ct);
				
				en = en.next;  // ADVANCE
			}
			
			node = node.next;  // ADVANCE
		}
		
		// now subtract from rhs
		node = rhs.head;
		while (node != null) {
			Molecule m = node.value;
			int mct = node.count;
			
			ElementNode en = m.head;
			while (en != null) {
				Element e = en.value;
				int ct = en.count;
				
				// unable to remove! Mustn't be balanced!
				if (base.remove(new Molecule(e, 1), mct*ct) == false) {
					return false;
				}
				
				en = en.next;  // ADVANCE
			}
			
			node = node.next;  // ADVANCE
		}
		
		return base.isEmpty();
	}
	
}
