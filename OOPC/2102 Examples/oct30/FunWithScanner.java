package oct30;

import java.util.Scanner;

public class FunWithScanner {
	
	/**
	 * Note you need to be careful with nextLine(). Either you always retrieve
	 * output using it ALONE. Or, when you are ready to fetch a line of input
	 * you make sure that you "clean up" the previous nextLine() so you are ready
	 * to go.
	 */
	public static void main(String[] args) {
		/** Declare interest in keyboard input. */
		Scanner sc = new Scanner(System.in);
		
		/** Get an int value from the keyboard. */
		System.out.println("Type an integer");
		int ivar = sc.nextInt();
		
		System.out.println("You typed " + ivar);
		System.out.println("Now type either a number or a boolean value.");
		
		if (sc.hasNextBoolean()) {
			boolean bvar = sc.nextBoolean();
			System.out.println (" Next typed boolean:" + bvar);
		} else if (sc.hasNextFloat()) {
			float fvar = sc.nextFloat();
			System.out.println (" Next typed float:" + fvar);
		} else {
			System.out.println ("Unable to understand:" + sc.next());
		}
		
		sc.nextLine();  // this clears the previous '\n' so we can now fetch lines-at-a-time
		
		System.out.println("Now type any sequence of characters on a line.");
		if (sc.hasNext()) {
			String s = sc.nextLine();
			System.out.println ("LINE:" + s);
		} else {
			System.out.println ("You must have terminated input with Control-Z");
		}
		
	}
	
}
