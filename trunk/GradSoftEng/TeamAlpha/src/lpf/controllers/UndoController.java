/**
 * 
 */
package lpf.controllers;

import lpf.moves.MoveManager;


/**
 * 
 * Controller for undoing a Move in a game
 * 
 * 
 * @author Eric Greer
 *
 */
public class UndoController extends UpdateController {
	
	/**
	 * Creates a new UndoController
	 */
	public UndoController() {
 	}
	
	/**
	 * Calls the MoveManager to undo the last available Move
	 */
	public void performUpdate() throws ProcessFailedException {
		try{
			MoveManager.getInstance().undoMove();
		}catch(UnsupportedOperationException e){
			throw new ProcessFailedException("Could not undo last move");
		}
		
	}

}
