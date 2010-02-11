/**
 * 
 */
package lpf.moves;

import java.util.Iterator;

import lpf.model.core.Cell;
import lpf.model.core.Grid;
import lpf.model.core.GridFunctions;
import lpf.model.core.Value;

/**
 * 
 * Move for clearing all the marks in a Grid in a Grid Game
 * 
 * @author Andrew Yee
 *
 */
public class ClearAllGridMarksMove implements IGridGameMove {

	Grid grid;
	Grid oldGrid;
	
	public ClearAllGridMarksMove(Grid g) {
		this.grid = g;
	}
	
	/**
	 * Clears all of the marks from the Cells in grid, and saves
	 * them to the Cells in oldGrid.
	 * 
	 * @return	true		if move was done
	 */
	public boolean doMove() {
		// clone Grid
		oldGrid = GridFunctions.cloneGrid(grid);
		
		Iterator<Cell> gridCells = grid.iterator();
		while(gridCells.hasNext()){
			Cell thisCell = gridCells.next();
			if ((thisCell.getDigit() == null) && 
					(! thisCell.getMarks().isEmpty())) {
				thisCell.clearMarks();
			}
		}
		return true;
	}

	/**
	 * Checks if the move is a valid move
	 * 
	 * Clearing all marks on a grid is valid if there is
	 * a cell in the grid that has at least one mark
	 * and no digit
	 * 
	 * @return	true		if move is valid
	 * 			false		if move is invalid
	 */
	public boolean isValid() {
		Iterator<Cell> gridCells = grid.iterator();
		while (gridCells.hasNext()) {
			Cell thisCell = gridCells.next();
			if ((thisCell.getDigit() == null) &&
					(! thisCell.getMarks().isEmpty())) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Takes all of the marks from the oldGrid and copies
	 * them to grid
	 * 
	 * @return	true		if undo was done
	 */
	public boolean undo() {
		Iterator<Cell> oldMarks = oldGrid.iterator();
		while (oldMarks.hasNext()) {
			Cell oldCell = oldMarks.next();
			Iterator<Value> copyMarks = oldCell.getMarks().iterator();
			while (copyMarks.hasNext()) {
				grid.getCellAtLocation(oldCell.loc).addMark(copyMarks.next());
			}
		}
		return true;
	}

}
