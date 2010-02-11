package lpf.GUIs.GridView.controllers;

import lpf.GUIs.GridView.PuzzleManager;
import lpf.controllers.AddDigitController;
import lpf.controllers.AddMarkController;
import lpf.controllers.IGridGameController;
import lpf.controllers.ProcessFailedException;
import lpf.controllers.RemoveDigitController;
import lpf.controllers.RemoveMarkController;
import lpf.model.core.Cell;
import lpf.model.core.Value;

/**
 * 
 * Controller for when a character is entered in a Cell
 * 
 * @author Nik Deapen
 *
 */
public class CharacterEnteredController implements IGridGameController {

	PuzzleManager gridManager;
	Value valueEntered;
	
	/**
	 * Character Entered Controller
	 * @param gm - the puzzle manager
	 * @param ve - the value entered
	 */
	public CharacterEnteredController(PuzzleManager gm, Value ve){
		this.gridManager = gm;
		this.valueEntered = ve;
	}
	
	@Override
	public void process() throws ProcessFailedException {
		// if the cell is fixed do nothing
		if (gridManager.isFixed(gridManager.getSelectedCell()))
			return;
	
		
		if (gridManager.isMarkMode()){
			markEntered(gridManager.getSelectedCell());
		}
		else {
			digitEntered(gridManager.getSelectedCell());
		}
	}
	
	/**
	 * Handles the digit mode control
	 * @param c - the selected cell
	 */
	private void digitEntered(Cell c) {
		if (c.getDigit() != null && c.getDigit().equals(valueEntered)){
			try {
				// remove the digit if it is already there
				new RemoveDigitController(c).process();
			} catch (ProcessFailedException e) {
			}
		}
		else {
			try {
				// add the digit if it is already there
				new AddDigitController(c,valueEntered).process();
			} catch (ProcessFailedException e) {
			}
		}
	}

	/**
	 * Handles the Mark Mode
	 * @param c - the selected cell
	 */
	private void markEntered(Cell c) {
		if (c.getMarks().contains(valueEntered)){
			try {
				// remove the mark if it is already there
				new RemoveMarkController(c,valueEntered).process();
			} catch (ProcessFailedException e) {
			}
		}
		else {
			try {
				// add the new mark
				new AddMarkController(c,valueEntered).process();
			} catch (ProcessFailedException e) {
			}
		}
	}

}
