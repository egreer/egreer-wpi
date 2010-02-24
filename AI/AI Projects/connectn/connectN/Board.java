package connectN;

/**
 * Board contains the state of a given board, the heuristic value of that board,
 * and the last moved used to get to that board position.
 *
 * @author Eric Greer (egreer)
 * @author Samuel LaFleche (shl)
 *
 */
public class Board {
	int[][] board;
	Heuristic heuristic;
	int connect;
	int lastmove;

	/** Constructor for a board object
	 * 
	 * @param b			 	Arrary of the initial setup 
	 * @param numconnect	number needed to connect to win
	 * @param last			the last move used to get to this board
	 */
	public Board(int[][] b, int numconnect, int last){
		board = b;
		connect = numconnect;
		heuristic = HeuristicGenerator.evaluate(b, numconnect);
		lastmove = last;
	}

	/** Getter method for the heuristic.
	 * 
	 * @return returns the heuristic for the board.
	 */
	public Heuristic getHeuristic(){
		return heuristic;
	}

	/** Getter method for the board.
	 *
	 * @return  returns the placement data contained in a board.
	 */
	public int[][] getBoard(){
		return board;
	}

	/** Getter method for the number of pieces in a row that need to be connected to win.
	 *
	 *  @return	Returns the number of conects needed
	 */
	public int getConnect(){
		return connect;
	}

	/** Getter method for the last move that was used to get to this board.
	 *
	 *	@return Returns an int representing the column that was last moved into.
	 */
	public int getLastMove(){
		return lastmove;
	}

	/** updateBoard executes a given move on the board by a player.
	 *
	 * @param move		The column to drop a piece into
	 * @param player	The player executing the move
	 * @return 			Returns a new Board object
	 */
	Board updateBoard( int move, int player){
		for (int d = 0; d < board.length ; d++){
			if(board[d][move] == 0){
				int[][] newmatrix = new int[board.length][board[0].length];
				for(int i = 0; i < board.length; i++){
					for(int j = 0; j < board[i].length; j++){
						newmatrix[i][j] = board[i][j];
					}
				}
				newmatrix[d][move] = player;
				return new Board(newmatrix, connect, d);
			}
		}
		return null;
	}

	/** Equals compares 2 boards and returns if they are the same. 
	 *
	 * @param compare	Takes in a board object to compare to
	 * @return 			True if they have the same setup, and same heuristic
	 */
	boolean equals(Board compare){
		if(board[0].length == compare.getBoard()[0].length && board.length == compare.getBoard().length ){
			return false;
		}

		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if(board[i][j] != compare.getBoard()[i][j]){
					return false;
				}
			}
		}
		return ((heuristic.compareTo(compare.getHeuristic())  == 0));
	}

	/** toString method for the board.
	 * 
	 */
	public String toString(){
		String s = new String();
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				s += board[i][j];
			}
			s += '\n';
		}
		return s;
	}
}
