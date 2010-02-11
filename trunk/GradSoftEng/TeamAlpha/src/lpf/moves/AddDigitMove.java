/**
 * 
 */
package lpf.moves;

import lpf.model.core.Cell;
import lpf.model.core.Value;

/**
 * 
 * Move for adding a digit from a Cell in a Grid Game
 * 
 * @author Andrew Yee
 * 
 * @revision - provided error checking (ND)
 */
public class AddDigitMove implements IGridGameMove {

	Cell cell;
	Value digit;
	Value oldDigit;
	
	public AddDigitMove(Cell c, Value v) {
		if (c == null || v == null)
			throw new IllegalArgumentException("AddDigitMove cannot have null arguments");
		this.cell = c;
		this.digit = v;
	}
	
	/**
	 * Adds the digit to cell by setting it as the Cell's
	 * digit, and saves the old digit in cell
	 * 
	 * @return	true		if move was done
	 * @throw UnsupportedOperationException if the move is not valid
	 */
	public boolean doMove() {
		if (!this.isValid())
			throw new UnsupportedOperationException("The move is not valid");
		oldDigit = cell.getDigit();
		cell.setDigit(digit);
		return true;
	}

	/**
	 * Checks if the move is a valid move
	 * 
	 * Adding a digit is valid if the digit is a
	 * valid input (depending on the puzzle), and
	 * the digit is contained in cell's marks
	 * if cell has at least one mark
	 * 
	 * @return	true		if move is valid
	 * 			false		if move is invalid
	 */
	public boolean isValid() {
		
		if (cell.getMarks().isEmpty()) {
			return true;
		}
		if ((! cell.getMarks().isEmpty()) &&
				(cell.getMarks().contains(digit))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sets cell's digit to the digit that was previously
	 * cell's digit.
	 * 
	 * @return	true		if undo was done
	 */
	public boolean undo() {
		cell.setDigit(oldDigit);
		return true;
	}

}
