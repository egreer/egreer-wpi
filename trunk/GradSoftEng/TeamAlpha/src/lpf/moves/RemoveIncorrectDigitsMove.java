package lpf.moves;

import java.util.ArrayList;
import java.util.Iterator;

import lpf.model.core.Cell;
import lpf.model.core.Puzzle;

/**
 * 
 * Move for removing incorrect digits in a puzzle
 * 
 * @author Eric Greer
 *
 */
public class RemoveIncorrectDigitsMove implements IGridGameMove {
	
	ArrayList<Cell> removedCells;
	Puzzle puzzle;
	
	/**
	 * Creates a new RemoveIncorrectDigitsMove
	 * 
	 * @param p			the Puzzle being modified
	 */
	public RemoveIncorrectDigitsMove(Puzzle p){
		puzzle = p;
		removedCells = new ArrayList<Cell>();
	}
	
	/**
	 * Removes the incorrect digits in a puzzle's Player Grid by
	 * checking against the Solution Grid
	 */
	public boolean doMove() {
		Iterator<Cell> incorrectIter = puzzle.getIncorrect().iterator();
		while(incorrectIter.hasNext()){
			Cell temp = incorrectIter.next();
			//Saves the cells digit
			Cell save = new Cell(temp.loc);
			save.setDigit(temp.getDigit());
			removedCells.add(save);
			temp.clearDigit();
		}
		return true;
	}

	/**
	 * It is always valid to remove incorrect digits from the grid
	 * 
	 * @return true
	 */
	public boolean isValid() {
		return true;
	}

	/**
	 * The removed digits contained in removedCells are placed back
	 * into puzzle's Player Grid
	 * 
	 * @return true			if undo was successful
	 */
	public boolean undo() {
		Iterator<Cell> removedIter = removedCells.iterator();
		while(removedIter.hasNext()){
			Cell temp = removedIter.next();
			puzzle.getPlayerGrid().getCellAtLocation(temp.loc).setDigit(temp.getDigit());
		}
		removedCells.clear();
		return true;
	}

}
