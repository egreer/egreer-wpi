package lpf.GUIs.GridView.controllers;

import lpf.GUIs.GridView.PuzzleManager;
import lpf.controllers.ClearAllCellMarksController;
import lpf.controllers.IGridGameController;
import lpf.controllers.ProcessFailedException;
import lpf.controllers.RemoveDigitController;

/**
 * Handles the delete and backspace key events
 * @author Nik Deapen
 *
 */
public class DeleteController implements IGridGameController {
	
	PuzzleManager gridManager;
	
	/**
	 * Creates a new DeleteController
	 * 
	 * @param gm			the puzzle manager trying to delete
	 */
	public DeleteController(PuzzleManager gm){
		this.gridManager = gm;
	}
	
	/**
	 * Processes the delete events, depending on whether gridManager
	 * is in mark mode or not
	 */
	@Override
	public void process(){
		if (gridManager.isFixed(gridManager.getSelectedCell()))
			return;
		if (gridManager.isMarkMode()){
			deleteMarks();
		}
		else {
			deleteDigit();
		}
	}

	/**
	 * Removes a digit via the RemoveDigitController
	 */
	private void deleteDigit() {
		try {
			new RemoveDigitController(gridManager.getSelectedCell()).process();
		} catch (ProcessFailedException e) {
		}
	}
	
	/**
	 * Removes the marks via the ClearAllCellMarksController
	 */
	private void deleteMarks() {
		try {
			new ClearAllCellMarksController(gridManager.getSelectedCell()).process();
		} catch (ProcessFailedException e){
		}
	}
}
