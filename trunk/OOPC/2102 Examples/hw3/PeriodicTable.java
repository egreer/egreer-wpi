package hw3;

import java.io.*;
import java.util.Scanner;

/**
 * Represents the periodic table of elements.
 * 
 * @author heineman
 */
public class PeriodicTable {
	
	/** Number of elements. */
	static int numElements = 117;

	/** Array of elements. */
	static Element[] elements;
	
	/**
	 * Return an Element object representing the given symbol
	 * 
	 * @param symbol      one, two, or three character symbol for element.
	 * @return            Element object or null if no such symbol.
	 */
	public static Element get (String symbol) {
		for (int i = 0; i < numElements; i++) {
			if (elements[i].getSymbol().equals (symbol)) {
				return elements[i];
			}
		}
		
		// not found
		return null;
	}

	/**
	 * Load up elements from the identified text file. 
	 * 
	 * Each line must be of the form:
	 * 
	 * 1 H Hydrogen 1.00794
	 * 
	 * @param f
	 * @throws FileNotFoundException
	 */
	public static void initialize(File f) throws FileNotFoundException {
		elements = new Element[numElements];
		Scanner sc = new Scanner (f);
		for (int i = 0; i < numElements; i++) {
			// load up a line.
			int num = sc.nextInt();
			String symbol = sc.next();
			String name = sc.next();
			
			float mass;
			boolean stable = true;
			if (sc.hasNextFloat()) {
				mass = sc.nextFloat();
			} else {
				// input is of form "[290]"
				String input = sc.next();
				String isotopeMass = input.substring(1,input.length()-1);
				
				mass = Integer.parseInt(isotopeMass);
				stable = false;
			}
			
			sc.nextLine();   // to clean out the line.
			
			Element e = new Element (name, symbol, num, mass, stable);
			elements[i] = e;
		}
	}
	
	/**
	 * Output the table of elements to the console.
	 */
	public static void outputElements () {
		for (int i = 0; i < numElements; i++) {
			System.out.println (elements[i]);
		}
	}
}
