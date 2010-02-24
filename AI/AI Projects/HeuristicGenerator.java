

public class HeuristicGenerator {
	final static int EMPTY = 0;
	final static int US = 1;
	final static int THEM = 2;
	final static long CANWIN = Long.MAX_VALUE/4;
	final static long WIN = CANWIN * 4;
	final static int MULT = 1000;
	public static long evaluate(int[][] board, int numconnect){
		long value = 0;
		long temp = evaluateAcc(board, numconnect);
		if(temp >= CANWIN || temp <= CANWIN * -1){
			if(temp < 0){
				return WIN * -1;
			}
			return WIN * 4;
		}
		value += temp;
		temp = evaluateVert(board, numconnect); 
		if(temp >= CANWIN || temp <= CANWIN * -1){
			if(temp < 0){
				return WIN * -1;
			}
			return WIN;
		}
		value += temp;
		temp = evaluateRightDown(board, numconnect);
		if(temp >= CANWIN || temp <= CANWIN * -1){
			if(temp < 0){
				return WIN * -1;
			}
			return WIN;
		}
		value += temp;
		temp = evaluateRightUp(board, numconnect);
		if(temp >= CANWIN || temp <= CANWIN * -1){
			if(temp < 0){
				return WIN * -1;
			}
			return WIN;
		}
		return value + temp;
	}
	private static long evaluateRightDown(int[][] board, int numconnect) {
		boolean[][] checked = new boolean[board.length][board[0].length];
		long value = 0;
		for(int i = board.length - 1; i >= 0; i--){
			for(int j = 0; j < board[i].length; j++){
				if(!checked[i][j] && board[i][j] != EMPTY){
					int numinrow = 0;
					int player = board[i][j];
					int tempi = i;
					int tempj = j;
					while(fits(tempi, tempj, board) && board[i][tempj] == player
							&& numinrow < numconnect){
						checked[tempi][tempj] = true;
						numinrow++;
						tempi--;
						tempj++;
					}
					if(numinrow == numconnect){
						if(player == THEM){
							return CANWIN * -1;
						}
						return CANWIN;
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
					long toadd = score(openspaces, numinrow, numconnect, player);
					value += toadd;
					if(value >= CANWIN || value <= CANWIN * -1){
						if(value < 0){
							return CANWIN * -1 + 1;
						}
						return CANWIN - 1;
					}
				}
			}
		}
		return value;
	}
	private static long evaluateRightUp(int[][] board, int numconnect) {
		boolean[][] checked = new boolean[board.length][board[0].length];
		long value = 0;
		for(int i = 0; i < board.length; i++){
			for(int j = board[i].length - 1; j >= 0; j--){
				if(!checked[i][j] && board[i][j] != EMPTY){
					int numinrow = 0;
					int player = board[i][j];
					int tempi = i;
					int tempj = j;
					while(fits(tempi, tempj, board) && board[i][tempj] == player
							&& numinrow < numconnect){
						checked[tempi][tempj] = true;
						numinrow++;
						tempi++;
						tempj++;
					}
					if(numinrow == numconnect){
						if(player == THEM){
							return CANWIN * -1;
						}
						return CANWIN;
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
					long toadd = score(openspaces, numinrow, numconnect, player);
					value += toadd;
					if(value >= CANWIN || value <= CANWIN * -1){
						if(value < 0){
							return CANWIN * -1 + 1;
						}
						return CANWIN - 1;
					}
				}
			}
		}
		return value;
	}
	private static long evaluateAcc(int[][] board, int numconnect) {
		boolean[][] checked = new boolean[board.length][board[0].length];
		long value = 0;
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
					if(numinrow == numconnect){
						if(player == THEM){
							return CANWIN * -1;
						}
						return CANWIN;
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
					long toadd = score(openspaces, numinrow, numconnect, player);
					value += toadd;
					if(value >= CANWIN || value <= CANWIN * -1){
						if(value < 0){
							return CANWIN * -1 + 1;
						}
						return CANWIN - 1;
					}
				}
			}
		}
		return value;
	}
	private static long evaluateVert(int[][] board, int numconnect) {
		boolean[][] checked = new boolean[board.length][board[0].length];
		long value = 0;
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
					if(numinrow == numconnect){
						if(player == THEM){
							return CANWIN * -1;
						}
						return CANWIN;
					}
					if(tempi < board.length && board[tempi][j] == EMPTY){
						int openspaces = 0;
						while(fits(tempi, j, board) && board[tempi][j] == EMPTY){
							openspaces++;
							tempi++;
						}
						long toadd = score(openspaces, numinrow, numconnect, player);
						value += toadd;
						if(value >= CANWIN || value <= CANWIN * -1){
							if(value < 0){
								return CANWIN * -1 + 1;
							}
							return CANWIN - 1;
						}
					}
				}
			}
		}
		return value;
	}
	private static boolean fits(int i, int j, int[][] m){
		return i > 0 && j > 0 && i < m.length && j < m[i].length;
	}
	/**
	 * Scores one incomplete connection.
	 * 
	 * @param openspaces
	 * @param have
	 * @param need
	 * @return
	 */
	private static long score(int openspaces, int have, int need, int player){
		if(openspaces < need - have){
			return 0;
		}
		long first = MULT;
		for(int i = have; i > 1; i--){
			first *= MULT;
			first *= MULT;
		}
		long second = first/MULT;
		second *= openspaces;
		long returnable = first + second;
		if(player == THEM){
			returnable *= -1;
		}
		return returnable;
	}
}

