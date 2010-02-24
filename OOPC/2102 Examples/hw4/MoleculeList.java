package hw4;

public class MoleculeList {

	/** Next molecule. */
	MoleculeNode head;
	
	/** Determines if list is empty. */
	public boolean isEmpty() {
		return (head == null);
	}
	
	public String toString() {
		String rep = "";
		MoleculeNode mn = head;
		while (mn != null) {
			rep += mn.toString();
			
			mn= mn.next;
			
			if (mn != null) { rep += " + "; }
		}
		
		return rep;
	}
	
	/**
	 * Remove a single molecule from this list.  
	 * 
	 * @param m    Molecule to remove
	 * @return     status of operation
	 */
	public boolean remove (Molecule m) {
		return remove (m, 1);
	}
	
	/**
	 * Remove given molecule (and counts) from the equation.
	 * 
	 * Note that we take advantage of the fact that each Molecule in the MoleculeList is 
	 * only represented once! Thus, once we find it, we are assured of being able to 
	 * determine how to remove 'ct'
	 * 
	 * @param m
	 * @param ct
	 * @return
	 */
	public boolean remove (Molecule m, int ct) {
		// empty.
		if (head == null) {
			return false;
		}

		// see if head value. If so, then update and return, possibly removing
		if (head.value.equals(m)) {
			if (head.count < ct) {
				head = head.next;     // simply remove
				return false;
			}
			
			head.count -= ct;
			if (head.count == 0) {
				head = head.next;
			}
			
			return true;
		}
		
		// ok. go through entire list.
		MoleculeNode mn = head;
		while (mn.next != null) {
			// already in list? update counts
			if (m.equals(mn.next.value)) {
				// error.
				if (mn.next.count < ct) {
					mn.next = mn.next.next;   // REMOVE!
					return false;
				}
				
				mn.next.count -= ct;
				if (mn.next.count == 0) {
					// remove
					mn.next = mn.next.next;
				}
				
				return true;
			}
			
			// move on to the next one.
			mn = mn.next;
		}
		
		// not in the list.
		return false;
	}
	
	public boolean add (Molecule m, int ct)  {
		if (head == null) {
			head = new MoleculeNode(m, ct);
		} else {
			// special case: If head of the list is the one we are adding...
			if (m.equals (head.value)) {
				head.count += ct;
				return true;
			}
			
			MoleculeNode mn = head.next;
			MoleculeNode prev = head;
			while (mn != null) {
				// already in list? update counts
				if (m.equals(mn.value)) {
					mn.count += ct;
					return true;
				}
				
				// move on to the next one.
				mn = mn.next;
				prev = prev.next;
			}
			
			// simply not present at all, so add.
			prev.next = new MoleculeNode(m,ct);
		}
		
		return true;
	}
	
	/**
	 * Molecule List comparison
	 * @param ml
	 * @return
	 */
	public boolean equals (Object o) {
		if (o == null) return false;
		if (!(o instanceof MoleculeList)) {
			return false;
		}
		
		MoleculeList ml = (MoleculeList) o;
		MoleculeNode node1 = head;
		MoleculeNode node2 = ml.head;
		
		while (node1 != null && node2 != null) {
			if (!node1.value.equals(node2.value)) {
				return false;
			}
			
			// ADVANCE. I can't believe I forgot this until my JUnit test case was written!
			node1 = node1.next;  
			node2 = node2.next;  
		}
		
		// different sizes? return false!
		if (node1 != null) {
			return false; 
		}
		if (node2 != null) {
			return false;
		}
		
		// must be true!
		return true;
	}
}
