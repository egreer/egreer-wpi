package lpf.controllers;

import lpf.model.core.Grid;
import lpf.moves.ClearAllGridMarksMove;
import lpf.moves.MoveManager;

/**
 * 
 * Controller for clearing all marks on a Grid
 * 
 * @author Andrew Yee
 *
 */
public class ClearAllGridMarksController extends UpdateController {

	Grid grid;
	
	/**
	 * Creates a new ClearAllGridMarksController
	 * 
	 * @param g			the grid being modified
	 */
	public ClearAllGridMarksController(Grid g) {
		grid = g;
	}
	
	
	/**
	 * Creates a ClearAllGridMarksMove object that is given to the MoveManager
	 * which will do the move and add it to the undo stack
	 * 
	 * @throws ProcessFailedException 
	 */
	public void performUpdate() throws ProcessFailedException {
		ClearAllGridMarksMove move = new ClearAllGridMarksMove(grid);
		if (move.isValid()) {
			MoveManager.getInstance().processMove(move);
		} else{
			throw new ProcessFailedException();
		}

	}

}
