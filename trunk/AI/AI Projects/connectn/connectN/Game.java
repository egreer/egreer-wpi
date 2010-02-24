package connectN;

import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * The Game class contains information important to processing connectN. 
 * It stores the current board, and executes moves on that board.
 * It also stores information about the rules of the current game 
 *
 * @author Eric Greer (egreer)
 * @author Samuel LaFleche (shl)
 *
 */
public class Game {
	
	protected int rows, columns, connect, turn, timeLimit;
	protected Board board;
	DefaultMutableTreeNode tree;
	MinMax minmax = new MinMax();
	Object[] results;
	Timer timeTimer;
	MoveThread background;
	Thread me;
	
	/** Constructor for a game object. 
	 *  Game Configuration array:
	 *  All values should be the string version of an int
	 *  [0] = number of rows
	 *  [1] = number of columns  
	 *  [2] = number to connect  
	 *  [4] = number turn/player
	 *  [5] = time limit in seconds  
	 *    
	 * @param gameConfig  An array containing the configuration information
	 */
	Game(String[] gameConfig){
		rows = Integer.parseInt(gameConfig[0]);
        columns = Integer.parseInt(gameConfig[1]);     
        connect = Integer.parseInt(gameConfig[2]);
        turn = Integer.parseInt(gameConfig[3]);
        timeLimit = (Integer.parseInt(gameConfig[4]) * 1000);
        board = new Board(new int[rows][columns], connect, -1);
        tree = new DefaultMutableTreeNode(board);
        timeTimer = new Timer();
        
	}
	
	/** Updates the game board with the move by the player
	 * 
	 * @param move		the column to move into.
	 * @param player	the player making the move.
	 */
	void updateBoard(int move,int player){
		board = board.updateBoard(move, player);
		System.err.println(board);
		Enumeration<DefaultMutableTreeNode> e = tree.children();
		boolean treeupdated = false;
		while(e.hasMoreElements()){
			DefaultMutableTreeNode n = e.nextElement();
			treeupdated = n.getUserObject().equals(board);
			if(treeupdated){
				tree = n;
				break;
			}
		}
		if(!treeupdated){
			tree = new DefaultMutableTreeNode(board);
		}
		tree = new DefaultMutableTreeNode(board);
	}
	
	/** Best move finds the best move to execut based on the current board.
	 * 
	 * @param player	The player making the move. 
	 * @return		Returns the column to execute the move on
	 */
	int bestMove(int player){
		me = Thread.currentThread();
		background = new MoveThread(tree, player);
		background.run();
		this.timeTimer.schedule(
				new TimerTask() {
					public void run() {
						background.interrupt();
					}
				}
		, (this.timeLimit - 1000));
		
		try{
				me.sleep(timeLimit - 500);
		}catch(Exception e){
			System.err.println("Interrupted current thread");
		}
		
		System.err.println("result" + results[0]);
		return (Integer)results[0];
	}

	/**
	 * Changes the current turn.
	 */
	void advanceTurn(){
		turn = 1 - turn;
	}
	
	
	/**
	 * MoveThread is a private class that searches for the optimum move for the player to take
	 *
	 */
	private class MoveThread extends Thread {
		DefaultMutableTreeNode head;
		int player;
	
		/**Constructor of the thread
		 * 
		 * @param head
		 * @param player
		 */
		public MoveThread(DefaultMutableTreeNode head, int player){
			this.head = head;
			this.player = player;
			
		}
		
		public boolean running = true;

		/**
		 * Runs through to find the best move
		 */
		public void run () {
			results = minmax.nextLevel(tree, player, null);
			int i = 0;
			
			while (running){
				i++;
				System.err.println("Ran: " + i);
				minmax.nextLevel(tree, player, null);
				results = minmax.nextLevel(tree, player, null);
				if (i >= 3){
					me.interrupt();
					break;
				}
			}
		
		}
		
		/**
		 * Time's up finish up the search
		 */
		public void interrupt(){
			this.running = false;
		}
	
	}

}
