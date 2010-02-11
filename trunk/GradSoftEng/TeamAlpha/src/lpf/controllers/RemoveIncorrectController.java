/**
 * 
 */
package lpf.controllers;

import lpf.model.core.Puzzle;
import lpf.moves.MoveManager;
import lpf.moves.RemoveIncorrectDigitsMove;

/**
 * 
 * Controller for removing all incorrect digits on a Grid
 * 
 * @author Eric Greer
 *
 */
public class RemoveIncorrectController extends UpdateController {


	Puzzle puzzle;
	
	/**
	 * Creates a new RemoveIncorrectController
	 *  
	 * @param p			the Puzzle being modified
	 */
	public RemoveIncorrectController(Puzzle p) {
		this.puzzle = p;
	}
	
	/**
	 * Calls the MoveManager to perform and store a new
	 * RemoveIncorrectDigitsMove
	 * 
	 * @throws ProcessFailedException
	 */
	public void performUpdate() throws ProcessFailedException{
		RemoveIncorrectDigitsMove move = new RemoveIncorrectDigitsMove(puzzle);
		
		if(move.isValid()){
			MoveManager.getInstance().processMove(move);
		}else {
			throw new ProcessFailedException();
		}

	}

}
