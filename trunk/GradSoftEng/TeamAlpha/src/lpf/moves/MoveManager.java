package lpf.moves;

import java.util.Stack;
/**
 * 
 * @author Nik Deapen
 * 
 */
public class MoveManager {
	
	Stack<IGridGameMove> moves = new Stack<IGridGameMove>();
	Stack<IGridGameMove> futureMove = new Stack<IGridGameMove>();
	
	/**
	 * adds a move to the listof moves
	 * @param m the move to add the the stack
	 */
	public void processMove(IGridGameMove m){
		if (m == null ||  !m.isValid())
			throw new IllegalArgumentException();
		
		m.doMove();
		
		// reset future moves
		futureMove = new Stack<IGridGameMove>();
	
		// add the move to the stack
		moves.add(m);
	}
	
	/**
	 * Undoes the last move to be added
	 * throws UnsupportedOperationException if there are no moves to be undone
	 */
	public void undoMove(){
		if(moves.isEmpty()) 
			throw new UnsupportedOperationException("Moves stack contains no moves");
		
		// get the move from the grid game
		IGridGameMove m = moves.pop();
		
		// undo the move
		m.undo();
		
		// push it onto the future move stack
		futureMove.push(m);
	}
	
	/**
	 * Re-does the last move undone
	 * @throws UnsupportedOperationException() if there is no move to be redones
	 */
	public void redoMove(){
		if(futureMove.isEmpty()) throw new UnsupportedOperationException("FutureMove stack contains no moves");
		IGridGameMove m = futureMove.pop();
				
		m.doMove();
		moves.push(m);
	}
	
	/**
	 * Whipes the state clean
	 */
	public void clearStack(){
		moves = new Stack<IGridGameMove>();
		futureMove = new Stack<IGridGameMove>();
	}
	
	
	
	static MoveManager instance;
	private MoveManager(){}
	public static MoveManager getInstance(){
		if (instance == null)
			instance = new MoveManager();
		return instance;
	}

}
