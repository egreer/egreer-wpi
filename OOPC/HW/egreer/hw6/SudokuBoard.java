package egreer.hw6;

/**
 * Represents a 9x9 Sudoku board.
 * 
 * A Sudoku board can have individual squares set and individual values
 * can be returned. One can also inspect a SudokuBoard to see if an 
 * individual square is empty.
 * 
 * A SudokuBoard has nine files (representing the columns from left to right) and
 * a set of nine rows (the bottom row is numbered 1 and the top row is numbered 9.
 * 
 * @author George Heineman (heineman@cs.wpi.edu)
 */
public class SudokuBoard {

	/** storage. */
	int [][] board = new int[9][9];
	
	/**
	 * Return contents of square identified by (file, rank).
	 * 
	 * @param file    'a' <= file <= 'i'
	 * @param rank     1  <= rank <= 9
	 *                
	 * @return 1 <= digit <= 9 if occupied; otherwise, returns 0
	 */
	public int get (char file, int rank) {
		
		// convert (file, rank) ==> (col, row)
		int col = fileToCol(file);
		int row = rankToRow(rank);
		
		int digit = board[row][col];
		return digit;
	}	
	
	/**
	 * Set the square identified by (file, rank) to given digit.
	 * 
	 * @param file    'a' <= file <= 'i'
	 * @param rank    1   <= rank <= 9
	 * @param digit   1   <= digit <= 9   to be placed at this location.
	 */
	public void set (char file, int rank, int digit) {
		// convert (file, rank) ==> (col, row)
		int col = fileToCol(file);
		int row = rankToRow(rank);
		
		board[row][col] = digit;
	}
	

	/**
	 * Clear contents of square identified by (file, rank).
	 * 
	 * @param file    'a' <= file <= 'i'
	 * @param rank     1   <= rank <= 9
	 */
	public void clear (char file, int rank) {
		// convert (file, rank) ==> (col, row)
		int col = fileToCol(file);
		int row = rankToRow(rank);
		
		board[row][col] = 0;
	}	
	
	/**
	 * Determine if the board has all of its squares taken.
	 * 
	 * @return  true if all squares are occupied; false otherwise.
	 */
	public boolean isFull () {
		// Iterate over all of the files. Note that we can use 'char' values here
		// because if you start with char 'a', then the next one (++) is going to
		// be 'b'.
		for (char file = 'a'; file <= 'i'; file++) {
			for (int rank = 1; rank <= 9; rank++) {
				// if this is empty, we are done.
				if (isEmpty(file, rank)) {
					return false;
				}
			}
		}
		
		// none are empty. We are full
		return true;
	}
	
	/** 
	 * Given a rank, return the internal row offsets used.
	 * 
	 * @param rank     values in the range 1 .. 9
	 * @return         int row in the range 0 .. 8
	 */
	private int rankToRow(int rank) {
		return rank-1;
	}

	/** 
	 * Given a file, return the internal column offsets used.
	 * 
	 * @param file     values in the range 'a' .. 'i'
	 * @return         int column in the range 0 .. 8
	 */
	private int fileToCol(char file) {
		return (file - 'a');
	}
	
	/**
	 * Represent board as a String. Make this representation a bit easier to see.
	 */
	public String toString() {
		String s = "";
		
		// for each row starting from 8 back to 0
		//    for each col starting from 0 up to 8
		//        print board[col][row]
		int numr = 0;
		// start with row
		s += "+---+---+---+" + "\n";
		for (int rank = 9; rank >= 1; rank--) {
			int numc = 0;
			
			for (char file = 'a'; file <= 'i'; file++) {
				s += get (file, rank); 
				
				// add vertical bar each third one.
				numc++;
				if (numc % 3 == 0) {
					s += "|";
				}
			}
			
			// add horizontal bar each third one.
			numr++;
			if (numr % 3 == 0) {
				s += "\n+---+---+---+";
			}
			
			s += "\n";   // terminating line.
		}
		
		// representation is complete. Return now.
		return s;
	}

	
	/**
	 * Determine if the square identified by (file, rank) is empty.
	 * 
	 * @param file    'a' <= file <= 'i'
	 * @param rank    1   <= rank <= 9
	 * @return   true if empty; false otherwise.
	 */
	public boolean isEmpty (char file, int rank) {
		// convert (file, rank) ==> (col, row)
		int col = fileToCol(file);
		int row = rankToRow(rank);
		
		return board[row][col] == 0;
	}

}
