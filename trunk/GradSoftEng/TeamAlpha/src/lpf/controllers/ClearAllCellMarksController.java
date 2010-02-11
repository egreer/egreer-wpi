/**
 * 
 */
package lpf.controllers;

import lpf.model.core.Cell;
import lpf.moves.ClearAllCellMarksMove;
import lpf.moves.MoveManager;

/**
 * 
 * Controller for clearing all marks in a Cell
 * 
 * @author Andrew Yee
 *
 */
public class ClearAllCellMarksController extends UpdateController {

	Cell cell;
	
	/**
	 * Creates a new ClearAllCellMarksController
	 * 
	 * @param c			the Cell being modified
	 */
	public ClearAllCellMarksController(Cell c) {
		cell = c;
	}
	
	/**
	 * Creates a ClearAllCellMarksMove object that is given to the MoveManager
	 * which will do the move and add it to the undo stack
	 * 
	 * @throws ProcessFailedException 
	 */
	public void performUpdate() throws ProcessFailedException {
		ClearAllCellMarksMove move = new ClearAllCellMarksMove(cell);
		if (move.isValid()) {
			MoveManager.getInstance().processMove(move);
		} else{
			throw new ProcessFailedException("Could not process ClearAllCellMarks Move");
		}

	}

}
