package nov30;

import java.util.Hashtable;
import java.util.Scanner;

/**
 * Show example use of a hashtable.
 * 
 * @author heineman
 */
public class SimpleHashExample {

	/**
	 * Enter in numbers until a duplicate has been seen.
	 * 
	 * @param args
	 */
	public static void main (String []args) {
		Scanner sc = new Scanner (System.in);
		System.out.println ("Enter in words, and I will let you know once you have entered in the same word more than once.");
		
		// Key is going to be String word. Value is just going to be a java.lang.Integer
		// since both the key and value for a hashtable must be a real object, not a primitive type
		Hashtable<String, Integer> h = new Hashtable<String, Integer>();
		
		// loop forever, reading single words, one at a time from the scanner.
		while (sc.hasNext()) {
			String s = sc.next();
			
			// if the word has already been seen. Get the value associated with that word
			// and increment count. Don't forget to put back in
			if (h.containsKey(s)) {
				Integer val = (Integer) h.get (s);
				val = new Integer (val.intValue() + 1);
				h.put(s, val);    // if we didn't do this, the (key,value) pair wouldn't be updated
				
				System.out.println (s + " has been seen " + val + " times");
			} else {
				h.put(s, new Integer(1));
			}
		}
	}
}
