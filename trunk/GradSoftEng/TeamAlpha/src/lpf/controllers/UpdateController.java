package lpf.controllers;

import lpf.configuration.SudokuAlphaGameConfiguration;


/**
 * 
 * Abstract class for any Move Controller that affects the contents
 * of the Grid in the game 
 * 
 * @author Nik Deapen
 *
 */
public abstract class UpdateController implements IGridGameController {

	SudokuAlphaGameConfiguration config = SudokuAlphaGameConfiguration.getInstance();
	
	/**
	 * Performs the controller's update and repaints the
	 * game's current grid
	 * 
	 * @throws ProcessFailedException
	 */
	@Override
	public final void process() throws ProcessFailedException {
		// perform the update
		this.performUpdate();
		
		// repaint the grid
		config.repaintGrid();
	}

	/**
	 * Perform the controller's update
	 * 
	 * @throws ProcessFailedException
	 */
	public abstract void performUpdate() throws ProcessFailedException;
	
}
