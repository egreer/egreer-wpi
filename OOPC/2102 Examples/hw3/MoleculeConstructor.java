package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Sample program showing use of Element/Molecule classes.
 * 
 *   Try for input
 *   
 *        Stibnite
 *        Sb
 *        S
 *        71.7
 * 
 * The resulting calculates should output the molecule Sb_2S_3 (that is two Sb atoms for every
 * 3 S atoms).
 * 
 * @author heineman
 */
public class MoleculeConstructor {

	/**
	 * Allows users to compute a simple question in general chemistry.
	 * @throws FileNotFoundException    if elements.txt not present.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// INPUT
		Scanner sc = new Scanner (System.in);

		System.out.println ("This program will calculate the proper molecular formula");
		System.out.println ("for simple minerals.");
		System.out.println ();
		System.out.println ("What is the name of the mineral");
		String mineralName = sc.nextLine();
		
		System.out.println ("This program only works for two elements.");
		
		System.out.println ("What is the symbol of the first element:");
		String elementSymbol1 = sc.nextLine();
		System.out.println ("What is the symbol of the second element:");
		String elementSymbol2 = sc.nextLine();
		
		System.out.println ("What is the calculated percentage of weight of " + mineralName + " that can be attributed to " + elementSymbol1 + " [0 - 100]:");
		double percElement1 = sc.nextDouble();
		sc.nextLine();  // throw away the NewLine
		
		// PROCESSING
		PeriodicTable.initialize(new File ("hw3\\elements.txt"));
		Element e1 = PeriodicTable.get(elementSymbol1);
		Element e2 = PeriodicTable.get(elementSymbol2);
		if (e1 == null) {
			System.err.println ("Unable to locate element: " + elementSymbol1);
			System.exit(0);
		} else if (e1.isUnstable()) {
			System.err.println ("Unable to compute compounds with unstable elements");
			System.exit(0);
		}
		if (e2 == null) {
			System.err.println ("Unable to locate element: " + elementSymbol2);
			System.exit(0);
		} else if (e2.isUnstable()) {
			System.err.println ("Unable to compute compounds with unstable elements");
			System.exit(0);
		}
		
		// compute based on a theoritical 100.0 grams of the material.
		double atomGrams1 = percElement1/e1.getMass();
		double atomGrams2 = (100-percElement1)/e2.getMass();
		double ratio = atomGrams1/atomGrams2;
		
		// find closest "n::d" to ratio and return Molecule
		Molecule m = findClosestIntegerRatio(ratio, e1, e2);
		
		// OUTPUT
		System.out.println(atomGrams1 + " g-atom of " + e1.getSymbol());
		System.out.println(atomGrams2 + " g-atom of " + e2.getSymbol());
		System.out.println ("the molecule is " + m);
	}

	/**
	 * Locate the n/d which is closest to the given ratio.
	 * 
	 * Note: only searches the space for n/d in the range 1..100
	 * 
	 * @param ratio   known ratio
	 * @param e1      element 1
	 * @param e2      element 2
	 * 
	 * @return        Molecule composed of E1_n E2_d by computing closest n/d to ratio
	 */
	private static Molecule findClosestIntegerRatio(double ratio, Element e1, Element e2) {
		// assume 1::1
		double smallestDiff = (1-ratio);
		int n = 1; 
		int d = 1;
		
		// compute all ratios.
		for (int n1 = 1; n1 <= 100; n1++) {
			for (int d2 = 1; d2 <= 100; d2++) {
				double r = (n1*1.0)/d2;
				
				// if we are a closer fit, then use it instead.
				if (Math.abs(r-ratio) < smallestDiff) {
					smallestDiff = Math.abs(r-ratio);
					n = n1;
					d = d2;
				}				
			}
		}
		
		// OK. Return best molecular fit.
		Molecule m = new Molecule (e1, n);
		m.add(e2,d);
		
		return m;
	}
}
