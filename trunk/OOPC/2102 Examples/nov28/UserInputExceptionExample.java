package nov28;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/**
 * Show an example of reacting to bad user input.
 * 
 * @author heineman
 *
 */
public class UserInputExceptionExample {

	/** Shared scanner to use within this class. */
	static Scanner sc;
	
	/**
	 * Read an int value from the keyboard. If any invalid input appears, 
	 * then respond to user
	 * 
	 * @return    int value ultimately typed in by user
	 */
	public static int readInt () {
		int val = -1;
		
		while (true) {
			System.out.println ("Enter an integer");
			try {
				
				// Check out the documentation for the 'nextInt' method to see
				// what Exceptions it is capable of throwing:
				//
				// http://java.sun.com/j2se/1.5.0/docs/api/java/util/Scanner.html#nextInt()
				
				val = sc.nextInt();
				return val;
			} catch (InputMismatchException ime) {
				// Retrieve the offending input
				String s = sc.next();
				System.out.println ("  >> \"" + s + "\" is an Invalid integer value.");
				
				// drain the rest of the characters on this line and go back to try again.
				sc.nextLine();  
			} catch (NoSuchElementException nsee) {
				System.out.println ("  >> Input is terminated.");
				System.exit(0);
			} catch (IllegalStateException ise) {
				System.out.println ("  >> Input is terminated.");
				System.exit(0);				
			}
		}
	}
	
	/**
	 * Main method to launch things off.
	 */
	public static void main (String args[]) {
		sc = new Scanner (System.in);
		
		int x = readInt();
		System.out.println ("You typed " + x);
		
	}
}
