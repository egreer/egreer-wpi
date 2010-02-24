package dec07.v1;

/**
 * Small example of bidirectional 'HAS_A' relationship
 * 
 * Name(s) of relationship "parent of" and "child of"
 * Cardinality: one-to-one
 * 
 * @author heineman
 */
public class Parent {

	/** Instance variable. */
	Child child;
	
	/**
	 * Constructor for Parent. 
	 * 
	 * This constructor cannot have a parameter Child. Do you see why?
	 */
	public Parent () {
		
	}
	
	/**
	 * Return parent's (only) child.
	 * @return
	 */
	public Child getChild() {
		return child;
	}

	/**
	 * Set the child for this parent.
	 * 
	 * Make sure that we update the parent links for this new child.
	 * @param c   Newly desired child for this parent object.

	 */
	public void setChild (Child c) {
		// detaching child
		if (c == null) {
			child = null;
			return;
		}
		
		if (child != c) {
			if (child != null) { child.setParent(null); }     // Detach old parent
			child = c;
			if (child != null) { c.setParent(this); }     // Attach new parent
		}
	}
	
}
