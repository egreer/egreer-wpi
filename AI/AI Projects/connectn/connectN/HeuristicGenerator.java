package connectN;

/**
 * Generates a Heuristic object that represents a board state.
 * 
 * @author egreer & shl
 *
 */
public class HeuristicGenerator {

	final static int EMPTY = 0;
	final static int US = 1;
	final static int THEM = 2;

	/**
	 * Generates a Heuristic object that represents a board state.
	 * 
	 * @param board			the board state to represent
	 * @param numconnect	the number of peices in a row needed to win
	 * @return	the Heuristic object
	 */
	public static Heuristic evaluate(int[][] board, int numconnect){
		Heuristic value = new Heuristic(new int[2*numconnect]);
		Heuristic temp = evaluateAcr(board, numconnect);
		value = value.add(temp);
		temp = evaluateVert(board, numconnect);
		value = value.add(temp);
		temp = evaluateRightDown(board, numconnect);
		value = value.add(temp);
		temp = evaluateRightUp(board, numconnect);
		return value.add(temp);
	}
	
	/**
	 * Generates a Heuristic object that represents the diagonal n-in-a-rows on the board that go right and down.
	 * 
	 * @param board			the board state to represent
	 * @param numconnect	the number of peices in a row needed to win
	 * @return	the Heuristic object
	 */
	private static Heuristic evaluateRightDown(int[][] board, int numconnect) {
		boolean[][] checked = new boolean[board.length][board[0].length];
		Heuristic value = new Heuristic(new int[2*numconnect]);
		for(int i = board.length - 1; i >= 0; i--){
			for(int j = 0; j < board[i].length; j++){
				if(!checked[i][j] && board[i][j] != EMPTY){
					int numinrow = 0;
					int player = board[i][j];
					int tempi = i;
					int tempj = j;
					while(fits(tempi, tempj, board) && board[tempi][tempj] == player
							&& numinrow < numconnect){
						checked[tempi][tempj] = true;
						numinrow++;
						tempi--;
						tempj++;
					}
					int openspaces = 0;
					while(fits(tempi, tempj, board) && board[tempi][tempj] == EMPTY){
						openspaces++;
						tempi--;
						tempj++;
					}
					tempi = i+1;
					tempj = j-1;
					while(fits(tempi, tempj, board) && board[tempi][tempj] == EMPTY){
						openspaces++;
						tempi++;
						tempj--;
					}
					Heuristic toadd = score(openspaces, numinrow, numconnect, player);
					value = value.add(toadd);
				}
			}
		}
		return value;
	}
	
	/**
	 * Generates a Heuristic object that represents the diagonal n-in-a-rows on the board that go right and up.
	 * 
	 * @param board			the board state to represent
	 * @param numconnect	the number of peices in a row needed to win
	 * @return	the Heuristic object
	 */
	private static Heuristic evaluateRightUp(int[][] board, int numconnect) {
		boolean[][] checked = new boolean[board.length][board[0].length];
		Heuristic value = new Heuristic(new int[2*numconnect]);
		for(int i = 0; i < board.length; i++){
			for(int j = board[i].length - 1; j >= 0; j--){
				if(!checked[i][j] && board[i][j] != EMPTY){
					int numinrow = 0;
					int player = board[i][j];
					int tempi = i;
					int tempj = j;
					while(fits(tempi, tempj, board) && board[tempi][tempj] == player
							&& numinrow < numconnect){
						checked[tempi][tempj] = true;
						numinrow++;
						tempi++;
						tempj++;
					}
					int openspaces = 0;
					while(fits(tempi, tempj, board) && board[tempi][tempj] == EMPTY){
						openspaces++;
						tempi++;
						tempj++;
					}
					tempi = i-1;
					tempj = j-1;
					while(fits(tempi, tempj, board) && board[tempi][tempj] == EMPTY){
						openspaces++;
						tempi--;
						tempj--;
					}
					Heuristic toadd = score(openspaces, numinrow, numconnect, player);
					value = value.add(toadd);
				}
			}
		}
		return value;
	}

	/**
	 * Generates a Heuristic object that represents the diagonal n-in-a-rows on the board that go across.
	 * 
	 * @param board			the board state to represent
	 * @param numconnect	the number of peices in a row needed to win
	 * @return	the Heuristic object
	 */
	private static Heuristic evaluateAcr(int[][] board, int numconnect) {
		boolean[][] checked = new boolean[board.length][board[0].length];
		Heuristic value = new Heuristic(new int[2*numconnect]);
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if(!checked[i][j] && board[i][j] != EMPTY){
					int numinrow = 0;
					int player = board[i][j];
					int tempj;
					for(tempj = j; tempj < board[i].length && board[i][tempj] == player
					&& numinrow < numconnect; tempj++){
						checked[i][tempj] = true;
						numinrow++;
					}
					int openspaces = 0;
					while(fits(i, tempj, board) && board[i][tempj] == EMPTY){
						openspaces++;
						tempj++;
					}
					tempj = j-1;
					while(fits(i, tempj, board) && board[i][tempj] == EMPTY){
						openspaces++;
						tempj--;
					}
					Heuristic toadd = score(openspaces, numinrow, numconnect, player);
					value = value.add(toadd);
				}
			}
		}
		return value;
	}

	/**
	 * Generates a Heuristic object that represents the diagonal n-in-a-rows on the board that go vertical.
	 * 
	 * @param board			the board state to represent
	 * @param numconnect	the number of peices in a row needed to win
	 * @return	the Heuristic object
	 */
	private static Heuristic evaluateVert(int[][] board, int numconnect) {
		boolean[][] checked = new boolean[board.length][board[0].length];
		Heuristic value = new Heuristic(new int[2*numconnect]);
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if(!checked[i][j] && board[i][j] != EMPTY){
					int numinrow = 0;
					int player = board[i][j];
					int tempi;
					for(tempi = i; tempi < board.length && board[tempi][j] == player
					&& numinrow < numconnect; tempi++){
						checked[tempi][j] = true;
						numinrow++;
					}
					if(tempi < board.length && board[tempi][j] == EMPTY){
						int openspaces = 0;
						while(fits(tempi, j, board) && board[tempi][j] == EMPTY){
							openspaces++;
							tempi++;
						}
						Heuristic toadd = score(openspaces, numinrow, numconnect, player);
						value = value.add(toadd);
					}
				}
			}
		}
		return value;
	}

	/**
	 * Determines whether a coordinate i,j fits into a matrix m.
	 * 
	 * @param i	the first coordinate
	 * @param j	the second coordinate
	 * @param m	the matrix
	 * @return	true if it does, else false
	 */
	private static boolean fits(int i, int j, int[][] m){
		return i >= 0 && j >= 0 && i < m.length && j < m[i].length;
	}
	
	/**
	 * Scores one incomplete connection.
	 * 
	 * @param openspaces	the number of open spaces on either side of the connection
	 * @param have			the number in a row the connection is
	 * @param need			the number of pieces in a row you need to win
	 * @param player		the player who is connecting pieces (1 for player, 2 for opponent)
	 * @return
	 */
	private static Heuristic score(int openspaces, int have, int need, int player){
		int[] builder = new int[need*2];
		int temphave = have;
		if(have > need){
			temphave = need;
		}
		if(openspaces < need - have){
			return new Heuristic(builder);
		}
		builder[temphave*2-1] = 1;
		builder[temphave*2-2] = openspaces;
		if(player == THEM){
			builder[temphave*2-1] *= -1;
			builder[temphave*2-2] *= -1;
		}
		return new Heuristic(builder);
	}
}

