package tictactoe;

/**
 * Represents a 3x3 tic tac toe grid
 * 
 * @author heineman
 *
 */
public class Grid {

	/** Board characters. */
	char [][]board;
	
	/** empty character. */
	public static final char SPACE = ' ';

	/**
	 * Create a 3x3 TicTacToe grid.
	 */
	public Grid () {
		board = new char[3][3];
		
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				board[r][c] = SPACE;
			}
		}
	}
	
	/**
	 * Return mark at given space.
	 * 
	 * @param r    desired row in the range 1..3
	 * @param c    desired column in the range 1..3
	 * @return     mark used for given space.
	 * @exception  ArrayIndexOutOfBoundsException if r,c invalid
	 */
	public char getMark(int r, int c) {
		return board[r][c];
	}

	/**
	 * Determines if space is available.
	 * 
	 * @param r    desired row in the range 1..3
	 * @param c    desired column in the range 1..3
	 * @return     true if space is unoccupied; false otherwise
	 * @exception  ArrayIndexOutOfBoundsException if r,c invalid
	 */
	public boolean isEmpty(int r, int c) {
		return board[r][c] == SPACE;
	}

	/**
	 * Mark the given location with the given mark.
	 * 
	 * @param r    desired row in the range 1..3
	 * @param c    desired column in the range 1..3
	 * @param mark mark to be placed in the board.
	 * @return     true if space was unoccupied; false otherwise
	 * @exception  ArrayIndexOutOfBoundsException if r,c invalid
	 */
	public boolean setMark(int row, int column, char mark) {
		if (isEmpty (row, column)) {
			board[row][column] = mark;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Return String representation of a board.
	 * 
	 *   X | X | O
	 *  ---+---+---
	 *   O | O | 
	 *  ---+---+---
	 *   X | X | O
	 * 
	 */
	public String toString () {
		String ret = "";
		String divider = "---+---+---";
		
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				ret += " " + board[r][c];
				if (c < board[0].length-1) { ret += " |"; }
			}
			if (r < board.length-1) { ret += "\n" + divider + "\n"; }
		}
		
		return ret + "\n";
	}

	/**
	 * Determine if an empty space remains on the board.
	 * 
	 * @return   true if at least one space is empty.
	 */
	public boolean hasEmptySpace() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (board[r][c] == SPACE) {
					return true;
				}
			}
		}
		
		return false;  // nope
	}
	
}
