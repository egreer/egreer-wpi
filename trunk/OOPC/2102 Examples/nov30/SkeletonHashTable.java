package nov30;

/**
 * Show the inner working of a Hashtable.
 * 
 * Briefly, the purpose of a Hashtable is to store a collection of <key, value> pairs with the
 * intent to return values upon request when given a key. It follows, then, that each key is
 * associated with only a single value.
 * 
 * One can request to put (key, value) into the hashtable, and then later retrieve the value
 * by means of a get (key).
 * 
 * Hashtable only functions properly when:
 * 
 *    1. The class for the key objects has a proper hashCode method implemented
 *    2. The class for the key objects has a proper equals(Object o) method implemented.
 *    
 * In this brief example, I construct a hashtable by maintaining an array of ten linked
 * lists, each of which store nodes with <key,value> and a next pointer for the list.
 * 
 * @author heineman
 */
public class SkeletonHashTable {

	/**
	 *  Maintain an array of linked lists. Each of which contains <key,value> pairs for
	 *  those keys with hashCode mod 10.
	 *  
	 *  Note that this makes room for 10 linked lists; it doesn't create them.
	 */
	SkeletonHashNode head[] = new SkeletonHashNode[10];
	
	/**
	 * Put <key, value> pair into the hashtable.
	 * 
	 * @param key      key to be associated with value
	 * @param value    value to be associated with key
	 * @return         prior value (if any) that had been associated with the key; return null if none.
	 */
	public Object put (Object key, Object value) {
		// which bucket does this <key,value> go into?
		int bucket = key.hashCode() % 10;
		
		// Add to list, if null
		if (head[bucket] == null) {
			head[bucket] = new SkeletonHashNode (key, value);
			return null;
		}
			
		// see if we are in the linked list. If we aren't then we prepend
		SkeletonHashNode node = find (head[bucket], key);
		if (node == null) {
			// prepend, since not in list
			node = new SkeletonHashNode (key, value);
			node.next = head[bucket];
			head[bucket] = node;
			return null;
		}
		
		// otherwise, we replace value, and don't forget to return the old associatd value
		Object oldValue = node.value;
		node.value = value;
		return oldValue;
	}
		
	
	/**
	 * Return the value associated with the key, if one exists.
	 * 
	 * @param key   
	 * @return
	 */
	public Object get (Object key ) {
		// which bucket will this key be found?
		int bucket = key.hashCode() % 10;
		
		SkeletonHashNode node = head[bucket];
		while (node != null) {
			if (node.key.equals (key)) {
				return node.value;
			}
			node = node.next;  // advance
		}
		
		// no associated value.
		return null;	
	}
	

	/**
	 * Given the head of a linked list, return the node that matches this key.
	 * 
	 * Private since no-one outside of this class needs to know.
	 * 
	 * @param head    head of linked list, or null if empty
	 * @param key     target key
	 * @return        node representing this key, or null if none in the list. 
 	 */
	private SkeletonHashNode find (SkeletonHashNode head, Object key) {
		SkeletonHashNode node = head;
		while (node != null) {
			if (node.key.equals (key)) {
				return node;
			}
			node = node.next;  // advance
		}
		
		return null;   // nope
	}

   
}
