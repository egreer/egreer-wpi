package dec04;

/**
 * Represents a 3x3 grid of character marks.
 * 
 * @author heineman
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
	 * Mark the given location with the given mark.
	 * 
	 * @param r    desired row in the range 1..3
	 * @param c    desired column in the range 1..3
	 * @param mark mark to be placed in the board.
	 * @return     true if space was unoccupied; false otherwise
	 * @exception  ArrayIndexOutOfBoundsException if r,c invalid
	 */
	public boolean setMark(int row, int column, char mark) {
		if (board[row][column] == SPACE) {
			board[row][column] = mark;
			return true;
		} else {
			return false;
		}
	}
	
}
