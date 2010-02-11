package lpf.GUIs.GridView.controllers;

import java.awt.event.MouseEvent;

import lpf.GUIs.GridView.PuzzleManager;
import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.controllers.ProcessFailedException;
import lpf.controllers.UpdateController;

/**
 * 
 * Controller for when a Cell on the Grid is clicked
 * 
 * @author Nik Deapen
 *
 */
public class GridClickedController extends UpdateController {

	SudokuAlphaGameConfiguration config = SudokuAlphaGameConfiguration.getInstance();
	PuzzleManager gridManager;
	MouseEvent mouseEvent;
	
	/**
	 * Creates a new GridClickedController
	 * 
	 * @param gridManager2			the PuzzleManager whose grid/view is being affected
	 * @param e
	 */
	public GridClickedController(PuzzleManager gridManager2, MouseEvent e){
		this.gridManager = gridManager2;
		this.mouseEvent = e;
	}
	
	/**
	 * Provides an indication of which Cell was selected, and whether it is
	 * in mark mode or not
	 * 
	 * @throws	ProcessFailedException
	 */
	@Override
	public void performUpdate() throws ProcessFailedException {
		
		// set the selected location
		gridManager.selectLocation(mouseEvent.getPoint());
		
		// change the mode accordingly
		int button = mouseEvent.getButton();
		if (button == MouseEvent.BUTTON1)
			gridManager.setMarkMode(false);
		else
			gridManager.setMarkMode(true);
			
		// consume the event
		mouseEvent.consume();
	}

}
