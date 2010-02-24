package dec08;

import java.util.*;

/**
 * Show use of Iterator over an ArrayList.
 * 
 * @author heineman
 */
public class IteratorExample {

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
		
		Iterator it = al.iterator();
		while (it.hasNext()) {
			String s = (String) it.next();
			System.out.println (s);
			
			// no need to advance. next() has done it.
		}

	}

}
