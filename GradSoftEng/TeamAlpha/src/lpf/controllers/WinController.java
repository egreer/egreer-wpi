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
 * Controller for when the player wins the game
 * 
 * @author Nik Deapen
 *
 */
public class WinController implements IGridGameController {

	SudokuAlphaGameConfiguration config = SudokuAlphaGameConfiguration.getInstance();
	
	/**
	 * Displays a message saying that the player has won the game if the
	 * Player Grid and Solution Grid are the same
	 */
	public void process() {
		
		Puzzle p = config.getCurrentPuzzle();
		
		if(p != null && GridFunctions.compareGrids(p.getPlayerGrid(), p.getSolutionGrid())){
			
			// thread because ..... i really dont know but it doesnt work without it
			// its a painting issue
			new Thread() {
				public void run(){
					JOptionPane.showMessageDialog(null, "You Win!!!");
				}
			}.start();
			
		}
	}
}
