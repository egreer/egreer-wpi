package nov02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * For Homework2, questions 3 and 4, you will need to be processing a (potentially large)
 * set of input sequences, one at a time. Since you don't know how many are in the list,
 * you may not know what to do with the intermediate outputs.
 * 
 * You can just output your results as you calculate them.
 * 
 * @author George
 *
 */
public class IntermixInputOutput {

	/**
	 * Read in a file, line by line, and count the number of characters in each line.
	 * 
	 * As each calculation is made, it is output to the console.
	 * 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// INPUT & PROCESSING & OUTPUT
		Scanner sc = new Scanner (new File ("SampleInput.txt"));

		int lineNum = 1;
		while (sc.hasNext()) {
			String line = sc.nextLine();
			
			// process and update count.
			System.out.println(lineNum + ". " + line.length() + " characters");
			lineNum++;
		}
	}

}
