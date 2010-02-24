package goldbug.v1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 1. But, there being no division, my first step was to ascertain the predominant
 *  letters, as well as the least frequent. Counting all, I constructed a table, 
 *  thus:
 * 
 * @author George Heineman
 *
 */
public class Goldbug {
	static Scanner sc;
	
	public static void main (String []args) throws FileNotFoundException {
		sc = new Scanner (System.in);
		System.out.println ("What file contains the coded text?");
		String fileName = sc.nextLine();
		
		File f = new File (fileName);
		if (!f.exists()) {
			System.out.println (f + " does not exist.");
			System.exit(0);
		}
		
		Scanner fscan = new Scanner (f);
		StringBuilder sb = new StringBuilder();
		while (fscan.hasNext()) {
			sb.append(fscan.nextLine());
		}
		fscan.close();
		
		System.out.println ("Welcome to the GoldBug decoding system");
		System.out.println ("Version 1.0");
		
		Code c = new Code(sb.toString());
		String s;
		while (true) {
			System.out.println ("\nCode is:");
			System.out.println (c.toString());
			System.out.println (c.decoded());
			System.out.println ("\nCurrent coded letters:");
			System.out.println (c.showKey());
			
			System.out.println ("\nOptions:");
			System.out.println (" [f] Show letter frequency");
			System.out.println (" [a] Assign a letter");
			System.out.println (" [c] Clear a letter assignment");
			System.out.println (" [q] Quit!");
			System.out.println ();
			
			try {
				while (true) {
					s = sc.nextLine();
					if (s.equals("q")) { System.exit(0); }
					if (s.equals("f")) {
						for (Iterator<Character> it = c.chars(); it.hasNext(); ) {
							System.out.print (it.next());
							System.out.print (" ");
						}
						break;
					} else if (s.equals ("c")) {
						System.out.println ("Enter the coded letter you wish to clear");
						s = sc.nextLine();
						c.clear(s.charAt(0));
						break;
					} else if (s.equals ("a")) {
						System.out.println ("Enter the coded letter you wish to assign");
						s = sc.nextLine();
						char c1 = s.charAt(0);
						System.out.println ("What code should be given?");
						s = sc.nextLine();
						char c2 = s.charAt(0);
						c.assign(c1, c2);
						break;
					}
					
					System.out.println ("\n Enter one of [facq] please");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println ("\nAn error occured. Try again!");
			}
		}
	}
}
