package lpf.moves;

/**
 * 
 * Interface for moves performed in grid games
 * 
 * @author Andrew Yee
 *
 */
public interface IGridGameMove {

	/**
	 * Method for how the move will be performed
	 * 
	 * @return	true		If the move was performed
	 * 			false		If the move was not performed	
	 */
	public boolean doMove();
	
	/**
	 * 
	 * Method for determining if the move is a valid move for the puzzle
	 * 
	 * @return	true		If the move is valid
	 * 			false		If the move is not valid
	 */
	public boolean isValid();
	
	/**
	 * 
	 * Method for undoing this move
	 * 
	 * @return	true		If the move was undone
	 * 			false		If the move was not undone
	 */
	public boolean undo();
	
}
