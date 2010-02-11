/**
 * 
 */
package lpf.controllers;

import lpf.model.core.Cell;
import lpf.moves.MoveManager;
import lpf.moves.RemoveDigitMove;

/**
 * 
 * Controller for clearing a digit from a Cell
 * 
 * @author Andrew Yee
 *
 */
public class RemoveDigitController extends UpdateController {

	Cell cell;
	
	/**
	 * Creates a new RemoveDigitController
	 * 
	 * @param c			the Cell being modified
	 */
	public RemoveDigitController(Cell c) {	
		cell = c;
	}
	

	/**
	 * Creates a RemoveDigitMove object that is given to the MoveManager
	 * which will do the move and add it to the undo stack
	 * @throws ProcessFailedException 
	 */
	public void performUpdate() throws ProcessFailedException {
		RemoveDigitMove move = new RemoveDigitMove(cell);
		if (move.isValid()) {
			MoveManager.getInstance().processMove(move);
		} else{
			throw new ProcessFailedException("RemoveDigitMove is not valid");
		} 
		
	}

}
