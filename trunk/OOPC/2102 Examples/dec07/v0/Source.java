package dec07.v0;

/**
 * Small example of 'HAS_A' relationship
 * 
 * Cardinality: one-to-one
 * 
 * @author heineman
 */
public class Source {

	/** Instance variable. */
	Target target;
	
	/**
	 * Constructor for Source. 
	 * 
	 * Note how Source cannot exist without having a proper Target? This indicates HAS_A COMPOSITION
	 *  
	 * @param t   target object without which source cannot operate.
	 * @exception NullPointerException     if target is missing
	 */
	public Source (Target t) {
		if (t == null) {
			throw new NullPointerException ("Unable to construct Source without a valid Target object.");
		}
		target = t;
	}

}
