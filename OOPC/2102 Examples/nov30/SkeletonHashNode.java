package nov30;

/**
 * Represents a <key, value> pair.
 * 
 * @author heineman 
 */
public class SkeletonHashNode {

	/** Key. */
	Object key;
	
	/** Value. */
	Object value;
	
	/** Next in the linked list. */
	SkeletonHashNode next;
	
	/**
	 * Default constructor for <key, value> pair.
	 * 
	 * @param key
	 * @param value
	 */
	public SkeletonHashNode (Object key, Object value) {
		this.key = key;
		this.value = value;
	}
}
