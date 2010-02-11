/**
 * 
 */
package lpf.moves;

import lpf.model.core.Cell;
import lpf.model.core.Value;

/**
 * 
 * Move for adding a mark to a Cell in a Grid Game
 * 
 * @author Andrew Yee
 *
 * @revised - added error correction (ND)
 */
public class AddMarkMove implements IGridGameMove {

	Cell cell;
	Value mark;
	
	public AddMarkMove(Cell c, Value v) {
		if (c == null || v == null)
			throw new IllegalArgumentException("AddMarkMove cannot have null arguments");
		this.cell = c;
		this.mark = v;
	}
	
	/**
	 * Adds the mark to cell's collection of marks
	 * 
	 * @return	true		if move was done
	 */
	public boolean doMove() {
		if (!this.isValid())
			throw new UnsupportedOperationException("Move is not valid");
		cell.addMark(mark);
		return true;
	}

	/**
	 * Checks if the move is a valid move
	 * 
	 * Adding a mark is valid if the mark is a
	 * valid input (depending on the puzzle), and
	 * the digit is not already contained in
	 * cell's marks if cell has at least one mark
	 * 
	 * @return	true		if move is valid
	 * 			false		if move is invalid
	 */
	public boolean isValid() {
		
		if (cell.getMarks().isEmpty()) {
			return true;
		}
		
		if ((! cell.getMarks().isEmpty()) && 
				(! cell.getMarks().contains(mark))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Removes the mark from cell's collection of marks
	 * 
	 * @return	true		if undo was done
	 */
	public boolean undo() {
		cell.clearMark(mark);
		return true;
	}

}
