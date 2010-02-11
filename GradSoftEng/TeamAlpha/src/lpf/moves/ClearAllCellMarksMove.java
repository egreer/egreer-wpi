/**
 * 
 */
package lpf.moves;

import java.util.ArrayList;
import java.util.Iterator;

import lpf.model.core.Cell;
import lpf.model.core.Value;


/**
 * 
 * Move for clearing all marks in a Cell in a Grid Game
 * 
 * @author Andrew Yee
 *
 */
public class ClearAllCellMarksMove implements IGridGameMove {

	Cell cell;
	ArrayList<Value> oldMarks;
	
	public ClearAllCellMarksMove(Cell c) {
		this.cell = c;
		oldMarks = new ArrayList<Value>();
	}
	
	/**
	 * Clears the list of marks in cell, and stores them
	 * in oldMarks
	 * 
	 * @return	true		if move was done
	 */
	public boolean doMove() {
		oldMarks.addAll(cell.getMarks());
		
		cell.clearMarks();
		return true;
	}

	/**
	 * Checks if the move is a valid move
	 * 
	 * Clearing all marks in a cell is valid if
	 * there are marks currently in the and cell
	 * does not have a digit
	 * 
	 * @return	true		if move is valid
	 * 			false		if move is invalid
	 */
	public boolean isValid() {
		if (cell.getMarks().isEmpty()) {
			return false;
		}
		
		if ((! cell.getMarks().isEmpty()) &&
				(cell.getDigit() == null)) {
			return true;
		} else {
			return false;
		}
		
	}

	/**
	 * Adds all of the marks in oldMarks to cell's
	 * list of marks
	 * 
	 * @return	true		if undo was done
	 */
	public boolean undo() {
		Iterator<Value> iterator = oldMarks.iterator();
		while(iterator.hasNext()){
			cell.addMark(iterator.next());
		}
		return true;
	}

}
