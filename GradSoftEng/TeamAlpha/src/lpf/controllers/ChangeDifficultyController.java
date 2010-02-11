/**
 * 
 */
package lpf.controllers;

import lpf.configuration.SudokuAlphaGameConfiguration;


/**
 * 
 * Controller for when the player changes the set difficulty
 * 
 * @author Andrew Yee
 * 
 * -- changed to make it take a configuration object
 *
 */
public class ChangeDifficultyController implements IGridGameController {

	int newDifficulty;
	
	/**
	 * Creates a new ChangeDifficultyController
	 * 
	 * @param difficulty		the game's new difficulty
	 */
	public ChangeDifficultyController(int difficulty){
		this.newDifficulty = difficulty;
	}
	
	/**
	 * Changes the current game's difficulty in the SudokuAlphaGameConfiguration
	 */
	@Override
	public void process() throws ProcessFailedException{
		// switched to try/catch because of new configuration settings (ND)
		try {
			SudokuAlphaGameConfiguration.getInstance().setDifficulty(newDifficulty);
		}
		catch (IllegalArgumentException e){
			throw new ProcessFailedException(); 
		}
	}

}
