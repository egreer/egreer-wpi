package hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Given an DNA sequence of the K12 strain of eColi (4,639,675 base pairs), search
 * for fragmentary DNA sequences within the K12 genome from a set of sample sequences.
 * 
 * Note that all sequences in DNA are counted from basePair 1 (not ZERO as in Java).
 * 
 * This single file contains the solution for both Problem3 and Problem4 in a single
 * pass.
 * 
 * Note the use of a helper method to compute the reverse Complement of a target string.
 * 
 * @author heineman
 */
public class Problem3and4 {

	/**
	 * Compute the reverse(complement(s)) where s is a DNA sequence composed only
	 * of 'a', 'c', 't', and 'g' characters.
	 * 
	 * @param s      the input DNA string.
	 * @return       rev(comp(s))
	 */
	public static String reverseComplement (String seq) {

		String reverse = "";
		int i = 0;
		while (i < seq.length()) {
			char c = seq.charAt(i);
			
			// properly create complement.
			if (c == 'a') {
				reverse = 't' + reverse;
			} else if (c == 't') {
				reverse = 'a' + reverse;
			} else if (c == 'c') {
				reverse = 'g' + reverse;
			} else if (c == 'g') {
				reverse = 'c' + reverse;
			}
			
			i++;
		}
		
		return reverse;
	}
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// INPUT: Grab the dna as a single String.
		Scanner sc = new Scanner (new File ("hw2\\input.txt"));
		String dna = sc.next();
		
		Scanner seqScanner = new Scanner (new File("sequences"));
		while (seqScanner.hasNext()) {
			String seq = seqScanner.next();
			
			// search within DNA. If not found, then do negative reverse search.
			// if not found, then output "UNMATCHED"
			// If found, output the first 10 base pairs followed by ...
			int idx = dna.indexOf(seq);
			int end = idx + seq.length() - 1;
			
			// found on the "+" strand
			if (idx != -1) {
				System.out.print ((idx+1) + ".." + (end+1) + " + " );
				if (seq.length() > 10) {
					System.out.println (seq.substring(0,10) + "... ");
				} else {
					System.out.println (seq);
				}
			} else {
				// search the negative strand. To do this, we must create complement 
				// in REVERSE to see if that string exists
				String reverseComp = reverseComplement (seq);
				
				// OK at this point, we can check.
				idx = dna.indexOf(reverseComp);
				end = idx + reverseComp.length() - 1;
				
				if (idx == -1) {
					System.out.println ("UNMATCHED");
				} else {
					System.out.print((end+1) + ".." + (idx+1) + " - ");
					if (reverseComp.length() > 10) {
						System.out.println (reverseComp.substring(0,10) + "...");
					} else {
						System.out.println (reverseComp);
					}
				}
			}
		}
	}
}
