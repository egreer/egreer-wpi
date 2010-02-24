package oct30;

/** Bring in Scanner concept. */
import java.util.Scanner;

public class SimplerFunWithScanner {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// INPUT
		/** Declare interest in keyboard input. */
		Scanner sc = new Scanner (System.in);
		
		/** Get an int value from the keyboard. */
		System.out.println ("Please enter an integer:");
		int ivar = sc.nextInt();
		
		System.out.println ("You typed " + ivar);
		if (!sc.hasNext()) {
			System.out.println ("Done!");
		} else {
			System.out.println (sc.next() + " typed");  
		}
	}
}
