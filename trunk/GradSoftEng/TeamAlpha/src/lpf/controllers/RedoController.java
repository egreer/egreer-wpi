/**
 * 
 */
package lpf.controllers;

import lpf.moves.MoveManager;

/**
 * 
 * Controller for redoing a Move in the game
 * 
 * 
 * @author Eric Greer
 *
 */
public class RedoController extends UpdateController {

	
	/**
	 * Creates a new RedoController
	 */
	public RedoController() {
	}
	
	/**
	 * 
	 * Calls the MoveManager to redo the last available move
	 * 
	 * @throws ProcessFailedException 
	 * 
	 */
	public void performUpdate() throws ProcessFailedException {
		try{
			MoveManager.getInstance().redoMove();
		} catch (UnsupportedOperationException e){
			throw new ProcessFailedException("Could not redo last move");
		}
	}

}
