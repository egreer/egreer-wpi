/**
 * 
 */
package lpf.moves;

import lpf.model.core.Cell;
import lpf.model.core.Value;

/**
 * 
 * Move for removing a Mark from a Cell in a Grid Game
 * 
 * @author Andrew Yee
 *
 */
public class RemoveMarkMove implements IGridGameMove {

	Cell cell;
	Value mark;
	
	public RemoveMarkMove(Cell c, Value v) {
		this.cell = c;
		this.mark = v;
	}
	
	/**
	 * Removes mark from cell's list of marks 
	 * 
	 * @return	true		if move was done
	 */
	public boolean doMove() {
		cell.clearMark(mark);
		return true;
	}

	/**
	 * Checks if the move is a valid move
	 * 
	 * Removing a mark is valid if the mark being
	 * removed is in the cell's list of marks
	 */
	public boolean isValid() {
		if (cell.getMarks().contains(mark)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds mark to cell's list of marks
	 * 
	 * @return	true		if undo was done
	 */
	public boolean undo() {
		cell.addMark(mark);
		return true;
	}

}
