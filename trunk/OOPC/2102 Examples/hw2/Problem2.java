package hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Compute the raw percentage of bases (a, c, t, g) within an input Genome.
 * 
 * The genomic input file is guaranteed to be composed only of a single line with
 * (lowercase) 'a', 'c', 't', and 'g' characters.
 * 
 * @author heineman
 */
public class Problem2 {

	/**
	 * Process "input.txt" file, throwing Exception if not found.
	 * 
	 * @throws FileNotFoundException   if 'hw2\\input.txt' is not found. 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// INPUT: Grab the dna as a single String.
		Scanner sc = new Scanner (new File ("hw2\\input.txt"));
		String dna = sc.next();
		
		// PROCESSING
		
		// [0] is 'a', [1] is 'c', [2] is 't', [3] is 'g', [4] is "OTHER"
		int []counts = new int[4];
		
		// see nov07 example "IndexingExample" for why this works.
		String bases = "actg";
		
		int i = 0;
		while (i < dna.length()) {
			char c = dna.charAt(i);
			
			// find which base is present. And update entry in array.
			int idx = bases.indexOf(c);
			if (idx == -1) {
				counts[4]++;
			} else {
				counts[idx]++;
			}
			
			i++;   // ADVANCE
		}
		
		// OUTPUT
		double percs[] = new double[4];
		i = 0;
		while (i < percs.length) {
			percs[i] = counts[i];
			percs[i] = percs[i] / dna.length();
			
			i++;  // advance
		}
		
		double max = 0;
		char letter = '\0';
		i = 0;
		while (i < percs.length) {
			if (percs[i] > max) {
				max = percs[i];
				letter = bases.charAt(i);
			}
			System.out.println (bases.charAt(i) + ":" + percs[i]);
			
			i++;
		}
		
		System.out.println ("\nThe most common letter is: " + letter);
	}

}
