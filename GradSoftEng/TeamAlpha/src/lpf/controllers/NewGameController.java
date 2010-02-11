/**
 * 
 */
package lpf.controllers;

import lpf.GUIs.GridView.PuzzleManager;
import lpf.GUIs.GridView.GridView;
import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.gameLibrary.NoPuzzleAvailableException;

import lpf.model.core.Puzzle;


/**
 * 
 * Controller for starting up a new game
 * 
 * @author Nik Deapen
 *
 */
public class NewGameController extends UpdateController {

	SudokuAlphaGameConfiguration config = SudokuAlphaGameConfiguration.getInstance();
	

	/**
	 * Starts a new game with the current difficulty
	 * 
	 * @throws	ProcessFailedException
	 */
	@Override
	public void performUpdate() throws ProcessFailedException {
		int difficulty = config.getDifficulty();
		
		Puzzle p;
		
		try {
			// TODO get one at a certain difficulty
			p = config.getPuzzleLibrary().getRandomPuzzle(difficulty);
		} catch (NoPuzzleAvailableException e) {
			throw new ProcessFailedException("No games available at difficulty level" + config.getDifficulty() + ".");
		}
		
		// update the configuration
		config.setPuzzle(p);
		
		// set up a grid manager
		PuzzleManager pm = new PuzzleManager(p);
		
		// attach observer to grid
		config.setGridView(new GridView(pm));
		
		
		new ResetGridController(p).process();
	}	
	
}
