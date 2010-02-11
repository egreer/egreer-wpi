/**
 * 
 */
package lpf.controllers;

import lpf.model.core.Cell;
import lpf.model.core.Value;
import lpf.moves.MoveManager;
import lpf.moves.RemoveMarkMove;

/**
 * 
 * Controller for removing a single mark from a Cell
 * 
 * @author Andrew Yee
 *
 */
public class RemoveMarkController extends UpdateController {

	Cell cell;
	Value mark;
	
	/**
	 * Creates a new RemoveMarkController
	 * 
	 * @param c				the Cell being modified
	 * @param v				the mark being removed
	 */
	public RemoveMarkController(Cell c, Value v) {
		cell = c;
		mark = v;
	}
	
	/**
	 * Creates a RemoveMarkMove object that is given to the MoveManager
	 * which will do the move and add it to the undo stack
	 * 
	 * @throws ProcessFailedException 
	 */
	public void performUpdate() throws ProcessFailedException {
		RemoveMarkMove move = new RemoveMarkMove(cell, mark);
		if (move.isValid()) {
			MoveManager.getInstance().processMove(move);
		} else{
			throw new ProcessFailedException();
		}
	}

}
