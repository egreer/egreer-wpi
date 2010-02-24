package nov02;

import java.io.*;
import java.util.Scanner;

/**
 * Show how to load information from a File on disk, using the Scanner object.
 * 
 * The example relies on using Exceptions, which won't be taught until later in
 * the course (check out syllabus).
 * 
 * @author heineman
 */
public class LoadFromFile {

	/**
	 * Load information from a file (NOTE path name). If not found, we are
	 * capable of throwing that exception (throws clause).
	 * 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		int num1=0, num2=0;
		Scanner sc;
		
		// INPUT
		// File name must be relative to the project within which you execute.
		// NOTE: if you have a package called 'username.hw2' in which your code
		// is written, then you must use '\\' to separate the divisions. Thus, 
		// the file name below would be "username\\hw2\\TEST.txt"
		sc = new Scanner (new File ("nov02\\TEST.txt"));
		num1 = sc.nextInt();
		num2 = sc.nextInt();

		// OUTPUT
		System.out.println (num1 + " " + num2);
		
		// THIS WON't work on your computer and will throw an exception. Program execution
		// terminates immediately.
		sc = new Scanner (new File ("c:\\Documents and Settings\\heineman\\Desktop\\sample.txt"));
		num1 = sc.nextInt();
		System.out.println (num1);
	}

}
