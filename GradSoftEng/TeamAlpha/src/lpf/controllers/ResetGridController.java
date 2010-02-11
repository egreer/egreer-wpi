/**
 * 
 */
package lpf.controllers;

import lpf.model.core.Puzzle;

/**
 * 
 * Controller for resetting the playing Grid in a Puzzle
 *
 * 
 * @author Eric Greer
 *
 */
public class ResetGridController extends UpdateController {

	Puzzle puzzle;

	/**
	 * Creates a new ResetGridController
	 * 
	 * @param p			the Puzzle being modified
	 */
	public ResetGridController(Puzzle p) {
		this.puzzle = p;
	}
	
	/**
	 * Resets the puzzle's Player Grid
	 */
	public void performUpdate() throws ProcessFailedException{
		puzzle.reset();
	}

}
