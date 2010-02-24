package tictactoe;

/**
 * Represents a game of TicTacToe
 * 
 * @author heineman
 *
 */
public class TicTacToe {

	/** Grid. */
	Grid   board;
	
	/** Player X. */
	public static final char playerX = 'X';
	
	/** Player O. */
	public static final char playerO = 'O';
	
	/** Who is the current player. */
	char   currentPlayer;
	
	/** Game Status. When INPROGRESS, the game is in progress. */
	int status;
	
	/** Possible Game Results. */
	public static final int   INPROGRESS = 0;
	public static final int   XWINS = 1;
	public static final int   OWINS = 2;
	public static final int   DRAW  = 3;
	
	
	/**
	 * Construct a new game of TicTacToe.
	 */
	public TicTacToe() {

		// Model the tic tac toe game as a Grid, onto which players make their marks.
		board = new Grid();
		
		currentPlayer = 'X';
		
		// game is in progress
		status = INPROGRESS;
	}
	
	/**
	 * Return the mark of the current player. 
	 * 
	 * @return    mark for current player.
	 */
	public char getCurrentPlayer () {
		return currentPlayer;
	}

	/**
	 * Returns false if the game is still in progress.
	 * 
	 * Returns true if 'X' or 'O' has won, or if the game results in a draw.
	 * 
	 * @return    true if game is over; false otherwise.
	 */
	public boolean isGameOver() {
		return (status != INPROGRESS);
	}

	/**
	 * Determine if the board space at [r, c] is available for a move.
	 * 
	 * @param r     row of desired space
	 * @param c     column of desired space
	 * @return      true if space is available; false otherwise
	 */
	public boolean isSpaceEmpty(int r, int c) {
		return board.isEmpty(r, c);
	}

	/**
	 * Assign a winner by selecting opponent of currentPlayer as the victor.
	 */
	public void forfeit() {
		if (currentPlayer == playerX) {
			status = OWINS;
		} else if (currentPlayer == playerO) {
			status = XWINS;
		}
	}

	/**
	 * Place current player's mark at the given space for the current player, and switch turns
	 * if the game is in progress, otherwise the game is completed.
	 * 
	 * Note that the caller must first validate that isSpaceEmpty (row, column) is true. This
	 * method must request the final game state to be computed.
	 * 
	 * @param row       row of the space 
	 * @param column    column of the space
	 * @return          true if mark could be placed; false otherwise.
	 */
	public boolean makeMove(int row, int column) {
		if (board.isEmpty(row, column)) {
			board.setMark (row, column, currentPlayer);
			if (!computeGameOver ()) {
				switchPlayers();
			}
			return true;
		}
		
		return false;  // ERROR
	}

	/**
	 * Determine if game has been won by current player, or drawn.
	 * 
	 * @return true if the game has been won by the current player or has been drawn; false
	 *              if the game is still at play.
	 */
	private boolean computeGameOver() {
		// see if current player has made three in a row horizontally
		for (int r = 0; r < 3; r++) {
			if (board.getMark(r, 0) == currentPlayer) {
				if (board.getMark (r, 1) == currentPlayer && board.getMark (r, 2) == currentPlayer) {
					markPlayerWins (currentPlayer);
					return true;
				}
			}
		}
		
		// see if current player has made three in a row vertically
		for (int c = 0; c < 3; c++) {
			if (board.getMark(0, c) == currentPlayer) {
				if (board.getMark (1, c) == currentPlayer && board.getMark (2, c) == currentPlayer) {
					markPlayerWins (currentPlayer);
					return true;
				}
			}
		}
		
		// diagonally
		if (board.getMark(1,1) == currentPlayer) {
			if (board.getMark(0,0) == currentPlayer && board.getMark(2,2) == currentPlayer) {
				markPlayerWins(currentPlayer);
				return true;
			}
			
			if (board.getMark(2,0) == currentPlayer && board.getMark(0,2) == currentPlayer) {
				markPlayerWins(currentPlayer);
				return true;
			}
		}
		
		if (!board.hasEmptySpace()) {
			markDraw();
			return true;
		}
		
		// nothing to say!
		return false;
	}

	/**
	 * Signal a draw.
	 */
	private void markDraw() {
		status = DRAW;
	}

	/**
	 * Proclaim player with given mark as the winner
	 * 
	 * @param mark    mark of winning player
	 */
	private void markPlayerWins(char mark) {
		if (mark == playerX) {
			status = XWINS;
		} else if (mark == playerO) {
			status = OWINS;
		}		
	}

	/**
	 * Upon the completion of a turn, the players switch.
	 */
	private void switchPlayers() {
		if (currentPlayer == playerX) {
			currentPlayer = playerO;
		} else {
			currentPlayer = playerX;
		}
	}

	/**
	 * Return String representation of the game.
	 */
	public String toString () {
		return board.toString();
	}

	/**
	 * Return status of the game, as a String
	 * 
	 * @return   String status of game state.
	 */
	public String getFinalStatus() {
		if (status == XWINS) {
			return "X wins";
		} else if (status == OWINS) {
			return "O wins";
		} else if (status == DRAW) {
			return "No winner. Game is drawn";
		}
		
		// default. Must be in progress
		return "in progress";
	}
	
	// Needed for proper GUI implementation to sit on top of this class.
	//     1. Need to find the marker in an individual Space. Can't just rely on
	//        toString() to dump entire contents out.
	
	/**
	 * Return the mark used at the given row, column.
	 * 
	 * If (r,c) values are invalid, then "?" is returned.
	 * 
	 * @param r     the row of the desired mark
	 * @param c     the column of the desired mark
	 */
	public char getMark (int r, int c) {
		try {
			return board.getMark(r, c);
		} catch (Exception e) {
			return '?';  // not sure what you want
		}
	}
}
