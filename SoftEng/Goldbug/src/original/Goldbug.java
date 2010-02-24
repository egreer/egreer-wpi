package original;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
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
	/** Read input. */
	static Scanner sc;
	
	/** The code. */
	static String code;
	
	/** Unknown character. */
	public static char unknown = '\001';
	
	/** Coded characters and their replacement. */
	static Hashtable<Character,Character> key = new Hashtable<Character,Character>(); 
	
	/** Characters in decreasing frequency. */
	static char chars[];
	
	/** And their frequency. */
	static int freq[];

	/** Compute frequency and build key table. */
	private static void process() {
		for (int i = 0; i < code.length(); i++) {
			char ch = code.charAt(i);
			if (key.containsKey(ch)) continue;
			
			key.put(ch, unknown);
		}
		
		int numUnique = key.size();
		chars = new char[numUnique];
		freq = new int [numUnique];
		
		int idx = 0;
		for (Iterator<Character> it = key.keySet().iterator(); it.hasNext(); idx++) {
			char ch = it.next();
			chars[idx] = ch;
			for (int i = 0 ; i < code.length(); i++) {
				if (code.charAt(i) == ch) {
					freq[idx]++;
				}
			}
		}
		
		// now sort by frequency. Bubble sort, for goodness' sake!
		for (int i = 0; i < freq.length - 1; i++) {
			for (int j = i+1; j < freq.length; j++) {
				if (freq[i] < freq[j]) {
					int tmp = freq[i];
					freq[i] = freq[j];
					freq[j] = tmp;
					
					char tc = chars[i];
					chars[i] = chars[j];
					chars[j] = tc;
				}
			}
		}
	}
	
	public static String decoded () {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < code.length(); i++) {
			sb.append(key.get(code.charAt(i)));
		}
		
		return sb.toString();
	}
	
	/** Output current key. */
	public static String showKey() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<Character> it = key.keySet().iterator(); it.hasNext(); ) {
			char ch = it.next();
			char dec = key.get(ch);
			if (dec != unknown) {
				sb.append (" ").append(ch).append("=").append(dec).append(" ");
			}
		}
		
		return sb.toString();
	}
	
	/** Launch whole program. */
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
		
		code = sb.toString();
		process();
		
		String s;
		while (true) {
			System.out.println ("\nCode is:");
			System.out.println (code);
			System.out.println (decoded());
			System.out.println ("\nCurrent coded letters:");
			System.out.println (showKey());
			
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
						for (int i = 0; i < chars.length; i++) {
							System.out.print (chars[i]);
							System.out.print (" ");
						}
						break;
					} else if (s.equals ("c")) {
						System.out.println ("Enter the coded letter you wish to clear");
						s = sc.nextLine();
						key.put(s.charAt(0), unknown);
						break;
					} else if (s.equals ("a")) {
						System.out.println ("Enter the coded letter you wish to assign");
						s = sc.nextLine();
						char c1 = s.charAt(0);
						System.out.println ("What code should be given?");
						s = sc.nextLine();
						char c2 = s.charAt(0);
						key.put(c1,	c2);
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
