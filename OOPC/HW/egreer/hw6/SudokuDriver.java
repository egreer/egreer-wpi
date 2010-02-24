package egreer.hw6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main driver to execute Sudoku game using board.
 * 
 * @author George Heineman (heineman@cs.wpi.edu)
 */
public class SudokuDriver {
	// input
	static Scanner in = new Scanner(System.in);

	/**
	 * Load a Sudoku board from the keyboard.
	 * 
	 * Once the first line is verified as being the string 
	 * "SAR1". We read nine strings one at a time where each 
	 * string represents a rank of the Sudoku board, from 
	 * rank 9 down to rank 1. A Rank String has 9 characters
	 * and, when read from left to right, represent the digits
	 * for that rank.
	 * 
	 * @return success of operation.
	 * @exception   FileNotFoundException    if invalid File.
	 */
	static SudokuBoard loadBoard (File inputFile) throws FileNotFoundException {
		Scanner fileInput = new Scanner (inputFile);
		try {
			// Read the first line of input to determine
			// the format (MUST be SAR1 -- see class Sudoku
			// document). 
			String s = fileInput.nextLine();
			if (!s.equals("SAR1")) {
				return null;
			}
			
			SudokuBoard board = new SudokuBoard();
			
			// for each row and column within that row, set board
			// if not empty. Catch any Exception and return null.
			for (int rank = 9; rank >= 1; rank--) {
				s = fileInput.nextLine();
				int idx = 0;
				for (char file = 'a'; file <= 'i'; file++) {
					// only set if not a space.
					char ch = s.charAt(idx);
					if (ch != ' ') {
						int digit = (int) (ch - '0');   // convert char => int
						board.set(file, rank, digit);
					}		
					
					idx++;   // advance within string s.
				}
			}
			
			// done and return board object!
			return board;			
		} catch (Exception e) {
			// on error, return null as the board description.
			return null;
		}
	}
	
	/**
	 * Run the game with simple text-based user interaction.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		// prepare for input by consolidating all input through common static reference
		in = new Scanner (System.in);
		
		// read in initial board.
		System.out.println ("Enter the Board Representation in SAR1 format.");
		SudokuBoard board = loadBoard(new File ("egreer\\hw6\\board.sar"));
		
		if (board == null) {
			System.out.println ("Error loading Sudoku board from System.in");
			System.exit(0);
		}
		
		// Output the initial board.
		while (! board.isFull()) {
			
			
			System.out.println ();	
			System.out.println ("Current Board:");
			System.out.println ();	
			System.out.println (board);
			// Interact with user input and construct a Move object
			System.out.println ("What is your move (i.e., \"b1(3)\" or \"-1\" to exit)? ");
			String s = in.nextLine();
			if (s.equals ("-1")) {
				break;
			}	
			
			try {
				char file = s.charAt(0);
				int rank = s.charAt(1) - '0';
				int digit = s.charAt(3) - '0';
			
				// construct the Move object.
				if (board.isEmpty(file, rank)) {
					board.set(file, rank, digit);
					System.out.println ();	
													
				} else {
					System.out.println (s + " is an invalid move: ");
				}
			} catch (Exception e) {
				System.out.println (s + " is an invalid move: " + s + " (" + e.getMessage() + ")");
				System.out.println ("Try again.");
			}
		}
		
		System.out.println ("Final board:");
		System.out.println (board.toString());

	}

}
