package dec01;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Compare this with an example based on fixed arrays.
 * 
 * @author heineman
 */
public class ArrayListExample {

	/**
	 * Read ints into an ArrayList and output it back to the user in order.
	 */
	public static void main(String[] args) {
		// Input
		Scanner sc = new Scanner (System.in);
		System.out.println ("Enter the set of integers. Press Control-Z when done.");
		
		// Processing
		ArrayList al = new ArrayList();
		while (sc.hasNext()) {
			int i = sc.nextInt();
			Integer ival = new Integer(i);
			
			al.add(ival);
		}
		
		// Output
		for (int i = 0; i < al.size(); i++) {
			System.out.println (al.get(i));
		}

	}

}
