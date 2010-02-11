/**
 * 
 */
package lpf.controllers;

import javax.swing.JOptionPane;

import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.model.core.GridFunctions;
import lpf.model.core.Puzzle;


/**
 * 
 * Controller for when the player has lost the game
 * 
 * @author Eric Greer
 * @stolenFrom Nik Deapen
 *
 */
public class LoseController implements IGridGameController {

	SudokuAlphaGameConfiguration config = SudokuAlphaGameConfiguration.getInstance();
	
	/**
	 * Displays a window saying that the player has lost if
	 * the player grid and the solution grid are the same
	 */
	public void process() {
		
		Puzzle p = config.getCurrentPuzzle();
		
		if(p != null && GridFunctions.compareGrids(p.getPlayerGrid(), p.getSolutionGrid())){
			
			// thread because... I really don't know, but it doesn't work without it
			// it's a painting issue
			new Thread() {
				public void run(){
					JOptionPane.showMessageDialog(null, "You LOSE!!");
				}
			}.start();
			
		}
	}
}
