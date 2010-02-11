/**
 * 
 */
package lpf.controllers;

import lpf.model.core.Cell;
import lpf.model.core.Value;
import lpf.moves.AddDigitMove;
import lpf.moves.MoveManager;

/**
 * 
 * Controller for placing a digit Value in a Cell
 * 
 * @author Andrew Yee
 *
 */
public class AddDigitController extends UpdateController {

	Cell cell;
	Value digit;
	
	/**
	 * Creates a new AddDigitController
	 * 
	 * @param c			the Cell being modified
	 * @param v			the digit that will be set in the Cell
	 */
	public AddDigitController(Cell c, Value v) {
		cell = c;
		digit = v;
	}
	
	/**
	 * Creates a AddDigitMove object that is given to the MoveManager
	 * which will do the move and add it to the undo stack
	 * 
	 * @throws ProcessFailedException 	Throws if it couldn't add the digit
	 */
	public void performUpdate() throws ProcessFailedException{
		AddDigitMove move = new AddDigitMove(cell, digit);
		if (move.isValid()) {
			MoveManager.getInstance().processMove(move);
		}
		else{
			throw new ProcessFailedException();
		}
		
		// see if we won
		new WinController().process();
	}

}
