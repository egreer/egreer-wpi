package goldbug.v2;

import java.io.File;
import java.io.FileNotFoundException;
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
	
		Screen scr = new Screen (sc, c);
		scr.interact();
	}
}
