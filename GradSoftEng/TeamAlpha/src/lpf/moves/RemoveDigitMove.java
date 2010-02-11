/**
 * 
 */
package lpf.moves;

import lpf.model.core.Cell;
import lpf.model.core.Value;

/**
 * 
 * Move for removing a digit from a Cell in a Grid Game
 * 
 * @author Andrew Yee
 *
 */
public class RemoveDigitMove implements IGridGameMove {

	Cell cell;
	Value oldDigit;
	
	public RemoveDigitMove(Cell c) {
		this.cell = c;
	}
	
	/**
	 * Stores cell's digit in oldDigit, and then clears
	 * cell's digit
	 * 
	 * @return	true		if move was done
	 */
	public boolean doMove() {
		oldDigit = cell.getDigit();
		cell.clearDigit();
		return true;
	}

	/**
	 * Checks if the move is a valid move
	 * 
	 * Removing the digit is valid if the cell
	 * contains a digit
	 * 
	 * @return	true		if move is valid
	 * 			false		if move is invalid
	 */
	public boolean isValid() {
		if (cell.getDigit() != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Set cell's digit to the oldDigit
	 * 
	 * @return	true		if undo was done
	 */
	public boolean undo() {
		cell.setDigit(oldDigit);
		return true;
	}

}
