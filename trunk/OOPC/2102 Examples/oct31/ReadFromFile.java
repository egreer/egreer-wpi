package oct31;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Display input straight from a file.
 * 
 * @author heineman
 */
public class ReadFromFile {

	/**
	 * Read in word-by-word a file and output to the screen.
	 * 
	 * Note that we declare we are capable of throwing an Exception if we can't locate
	 * a particular file. For now, declare your method in this way. A full treatment of
	 * Exceptions will be presented starting on November 13th.
	 * 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// INPUT & PROCESSING & OUTPUT
		Scanner sc = new Scanner (new File ("SampleInput.txt"));
		
		int wordCount = 0;
		while (sc.hasNext()) {
			String word =sc.next();
			
			// process and update count.
			System.out.println(word);
			wordCount++;
		}
		
		// OUTPUT 
		System.out.println ("There were " + wordCount + " words in the file.");
	}

}
