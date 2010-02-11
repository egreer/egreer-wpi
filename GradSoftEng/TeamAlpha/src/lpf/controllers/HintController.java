/**
 * 
 */
package lpf.controllers;

import java.util.Iterator;

import lpf.model.core.Cell;
import lpf.model.core.Puzzle;

/**
 * Controller for giving hints to the player.
 * A hint removes an incorrect digit from the player board, if it can't then 
 * an empty digit is filled in with a correct digit from the solution. 
 * 
 * @author Eric Greer
 *
 */
public class HintController extends UpdateController {

	Puzzle puzzle;

	/**
	 * Creates a new HintController
	 * 
	 * @param p			the puzzle getting the hint
	 */
	public HintController(Puzzle p) {
		puzzle = p;
	}

	/** 
	 * Executes the Hint Controller
	 */
	public void performUpdate() throws ProcessFailedException {
		Cell modifyCell = null;
		boolean done = false;
		//First see if there is an incorrect digit that can be removed
		Iterator<Cell> incorrectIterator =  puzzle.getIncorrect().iterator();

		while(!done && incorrectIterator.hasNext()){
			modifyCell = incorrectIterator.next();

			//Check to see if there is a digit in the cell
			if(modifyCell.getDigit() != null){
				RemoveDigitController removeDC = new RemoveDigitController(modifyCell);
				removeDC.process();
				done = true;
			}
		}


		//If nothing to remove then reveal a digit
		Iterator<Cell> playerIter = puzzle.getPlayerGrid().iterator();
		while(!done && playerIter.hasNext()){
			modifyCell = playerIter.next();

			//If there is no digit then reveal one and end loop
			if (modifyCell.getDigit()== null){
				if (!modifyCell.getMarks().isEmpty()){
					new ClearAllCellMarksController(modifyCell).process();
				}
				
				AddDigitController addDC= new AddDigitController(modifyCell, puzzle.getSolutionGrid().getCellAtLocation(modifyCell.loc).getDigit());
				addDC.process();
				done = true;
			} 

		}

		//If nothing else win
		if (!done){
			new WinController().process();
			
		} 
	}

}
