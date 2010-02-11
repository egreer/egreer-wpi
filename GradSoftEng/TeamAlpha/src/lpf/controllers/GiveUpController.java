/**
 * 
 */
package lpf.controllers;

import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.model.core.GridFunctions;
import lpf.model.core.Puzzle;
import lpf.moves.MoveManager;

/**
 * 
 * Controller for when the player gives up in a game
 * 
 * @author Eric Greer
 *
 */
public class GiveUpController extends UpdateController {
	
	/**
	 * Ends the game by displaying the game's solution and telling
	 * the user that they have lost
	 * 
	 * @throws ProcessFailedException
	 */
	public void performUpdate() throws ProcessFailedException {
		Puzzle current = SudokuAlphaGameConfiguration.getInstance().getCurrentPuzzle();
		
		if(current != null){
			GridFunctions.copyGrid(current.getPlayerGrid(), current.getSolutionGrid());
			
			//Clear Undo Stack
			MoveManager.getInstance().clearStack();
		}else{
			throw new ProcessFailedException("No Puzzle Exists to Give Up");
		}
		
		new LoseController().process();
	}

}
