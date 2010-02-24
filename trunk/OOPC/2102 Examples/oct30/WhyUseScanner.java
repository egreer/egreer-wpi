package oct30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Example code showing how Scanner is so much easier to use than previous methods.
 * 
 * @author heineman
 */
public class WhyUseScanner {

	/**
	 * Compare two input approaches. Goal: To read in two Integers and a Double
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// old way: Can only read line-at-a-time for ease of use
		System.out.println ("Enter three lines of input: An int, an int, a double.");
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		int ivar1 = Integer.parseInt(br.readLine());
		int ivar2 = Integer.parseInt(br.readLine());
		double dvar1 = Double.parseDouble(br.readLine());

		System.out.println ("You typed (" + ivar1 + "," + ivar2 + "," + dvar1 + ")");
		
		// Now do this with Scanner.
		System.out.println ("Now enter int int double separated by whitespace.");
		Scanner sc = new Scanner (System.in);
		int ivar3 = sc.nextInt();
		int ivar4 = sc.nextInt();
		double dvar2 = sc.nextDouble();
		
		System.out.println ("You typed (" + ivar3 + "," + ivar4 + "," + dvar2 + ")");
		
		
	}

}
