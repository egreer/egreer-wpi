package nov30;

import java.util.Hashtable;

/**
 * An example of what goes wrong when there is no hashCode method.
 * 
 * @author heineman
 */

public class HashGoneWrongExample {
	
	/**
	 * Show how the lack of a hashCode implementation is a problem.
	 */
	public static void main (String args[]) {
		// create three hashtables, one for each sample element type.
		// The key is to be Element0, or element1 or element2. The value
		// is just an Integer. Note that it can't be a primitive type like int.
		Hashtable<BadPoint, String> h = new Hashtable<BadPoint, String> ();
		
		BadPoint bp = new BadPoint (2,5);
		
		// associate the value "home" with the key BadPoint[2,5]
		h.put(bp, "home");
		String s = (String) h.get(bp);
		System.out.println ("Found the associated value: " + s);
		
		// now try to find using another BadPoint
		BadPoint bp2 = new BadPoint (2,5);
		s = (String) h.get(bp2);
		System.out.println ("Found the associated value: " + s);
		
		// note that it couldn't find it? Perhaps the answer is because BadPoint doesn't
		// have an equals(Object o) method? Go back to that class and comment it out. You
		// will see that it doesn't work either.
	}
}
