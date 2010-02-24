package dec08;

import java.util.*;

/**
 * Show example of how an Iterator can be used to remove an element from a collection.
 * 
 * @author heineman
 */
public class RemoveExample {

	/**
	 * Show ability to user Iterator to access elements from a collection.
	 */
	public static void main(String[] args) {
		ArrayList al = new ArrayList();
		al.add ("William");
		al.add ("Christopher");
		al.add ("Zachary");
		al.add ("James");
		al.add ("Luke");
		al.add ("Jack");
		al.add ("Joe");
		al.add ("Timothy");
		al.add ("Nicholas");
		
		System.out.println ("There are now " + al.size() + " names in the list.");
		Iterator it = al.iterator();
		while (it.hasNext()) {
			String s = (String) it.next();
			if (s.equals ("James")) {
				System.out.println ("Removing James from the set.");
				it.remove();   // remove this last one found.
			}
			
			// no need to advance. next() has done it.
		}
		
		System.out.println ("There are now " + al.size() + " names in the list.");

	}
}
