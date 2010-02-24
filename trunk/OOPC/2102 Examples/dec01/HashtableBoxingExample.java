package dec01;

import java.util.Hashtable;

/**
 * Small example showing the addition (in JDK 5.0) of automatic boxing and 
 * unboxing of wrapper objects for int, float, long, double.
 * 
 * @author heineman
 */
public class HashtableBoxingExample {

	/**
	 * Show an example of boxing/unboxing
	 */
	public static void main(String[] args) {
		Hashtable h = new Hashtable();

		// this is not as simple as it might seem.
		h.put(10, 99);
		
		// The above really is translated (under the hood, so to speak) into the
		// following code when it is compiled. This is done because you need 
		// objects for <key, value> and not primitive types. 
		Object o = h.put(new Integer(10), new Integer(99));
		
		System.out.println (o);
	}

}
