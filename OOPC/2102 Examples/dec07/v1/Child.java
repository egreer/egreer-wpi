package dec07.v1;

/**
 * Note how this class maintains a reference back to its referent.
 * 
 * Name(s) of relationship "parent of" and "child of"
 * Cardinality: one-to-one
 * 
 * @author heineman
 */
public class Child {

	/** Each child knows its parent. */
	Parent parent;

	/**
	 * Construct a new Child object, given its parent.
	 * 
	 * share the setParent(p) method to ensure bi-directionality is maintained.
	 */
	public Child (Parent p) {
		setParent (p);
	}
	
	/**
	 * Need to store the Parent for proper execution of the methods in this class.
	 * 
	 * Use "!=" to prevent infinite recursion.
	 * 
	 * @param p   Newly desired parent for this child object.
	 */
	public void setParent(Parent p) {
		// detaching parent.
		if (p == null) {
			parent = null;
			return;
		}
		
		if (parent != p) {
			if (parent != null) { parent.setChild(null); }    // detach old child
			parent = p;
			if (parent != null) { parent.setChild(this); }    // attach new child
		}			
	}
	
	/**
	 * Retrieve parent.
	 */
	public Parent getParent () {
		return parent;
	}
}
