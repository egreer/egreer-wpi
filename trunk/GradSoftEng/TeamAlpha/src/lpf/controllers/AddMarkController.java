/**
 * 
 */
package lpf.controllers;

import lpf.model.core.Cell;
import lpf.model.core.Value;
import lpf.moves.AddMarkMove;
import lpf.moves.MoveManager;

/**
 * 
 * Controller for adding a mark Value to a Cell
 * 
 * @author Andrew Yee
 *
 */
public class AddMarkController extends UpdateController {

	Cell cell;
	Value mark;
	
	/**
	 * Creates a new AddMarkController
	 * 
	 * @param c			the Cell being modified
	 * @param v			the mark that will be added to the Cell
	 */
	public AddMarkController(Cell c, Value v) {
		cell = c;
		mark = v;
	}
	
	/**
	 * Creates an AddMarkMove object that is given to the MoveManager
	 * which will do the move and add it to the undo stack
	 * 
	 * @throws ProcessFailedException	If the move is not valid 
	 */
	public void performUpdate() throws ProcessFailedException {
		AddMarkMove move = new AddMarkMove(cell, mark);
		if (move.isValid()) {
			MoveManager.getInstance().processMove(move);
		} else{
			throw new ProcessFailedException();
		}

	}
}
