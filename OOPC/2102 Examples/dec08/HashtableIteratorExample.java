package dec08;

import java.util.*;

/**
 * Show use of Iterator over a Hashtable.
 * 
 * @author heineman
 */
public class HashtableIteratorExample {

	/**
	 * Show ability to user Iterator to access elements from a collection.
	 */
	public static void main(String[] args) {
		Hashtable h = new Hashtable();
		h.put ("William", 15);
		h.put ("Christopher", 10);
		h.put ("Timothy", 9);
		h.put ("Luke", 11);
		h.put ("James", 7);
		h.put ("Zachary", 6);
		h.put ("Joseph", 6);
		h.put ("Jack", 5);
		h.put ("Nicholas", 4);
		
		// get the set of all keys (what do you think is the order?)
		Iterator it = h.keySet().iterator();
		while (it.hasNext()) {
			String s = (String) it.next();
			System.out.println (s);
			
			// no need to advance. next() has done it.
		}
		
		// get the set of all values (what do you think is the order?)
		it = h.values().iterator();
		while (it.hasNext()) {
			Integer ival = (Integer) it.next();
			System.out.println (ival);
			
			// no need to advance. next() has done it.
		}

	}

}
