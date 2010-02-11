package lpf.GUIs.GridView.controllers;

import lpf.GUIs.GridView.PuzzleManager;
import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.controllers.ProcessFailedException;
import lpf.controllers.UpdateController;
import lpf.model.core.Location;

/**
 * 
 * Controller for changing the selected location on the Grid
 * 
 * @author Nik Deapen
 *
 */
public class ChangeSelectedLocationController extends UpdateController {
	
	public static final int ABOVE = 0, BELOW = 1, RIGHT = 2, LEFT = 3;
	SudokuAlphaGameConfiguration config = SudokuAlphaGameConfiguration.getInstance();
	PuzzleManager gridManager;
	int newLocation;
	
	/**
	 * Creates the controller
	 * @param gridManager - the grid manager
	 * @param newLocation - the new location (use static final values)
	 */
	public ChangeSelectedLocationController(PuzzleManager gridManager, int newLocation){
		this.gridManager = gridManager;
		this.newLocation = newLocation;
	}
	
	/**
	 * Change the location based on the newLocation
	 */
	@Override
	public void performUpdate() throws ProcessFailedException {
		switch(newLocation){
			case ABOVE:
				gridManager.setSelectedLocation(locationAbove(gridManager.getSelectedLocation()));
				break;
			case BELOW:
				gridManager.setSelectedLocation(locationBelow(gridManager.getSelectedLocation()));
				break;
			case RIGHT:
				gridManager.setSelectedLocation(locationToTheRightOf(gridManager.getSelectedLocation()));
				break;
			case LEFT:
				gridManager.setSelectedLocation(locationToTheLeftOf(gridManager.getSelectedLocation()));
				break;
			default:
				throw new IllegalArgumentException("Location is not valid.");
		}
	}
		
	/**
	 * Location to the Left
	 * @param l the start location
	 * @return the new location left of l
	 */
	private Location locationToTheLeftOf(Location l) {
		char col = l.column;
		if (col == 'a')
			col += config.getCharacterSet().getNumValues()-1 ;
		else
			col --;
		return new Location(l.row,(char)col);
	}
	
	/**
	 * Location to the Right
	 * @param l the start location
	 * @return the location to the right of l
	 */
	private Location locationToTheRightOf(Location l){
		int col = l.column - 'a';
		col += 1;
		if (col == config.getCharacterSet().getNumValues())
			col = 0;
		col += 'a';
		return new Location(l.row,(char)col);
	}
	
	/**
	 * Location Above
	 * @param l - the start location
	 * @return - the location above l
	 */
	private Location locationAbove(Location l){
		int newRow = l.row-1;
		if (newRow == 0)
			newRow = config.getCharacterSet().getNumValues();
		return new Location(newRow,l.column);
	}
	
	/**
	 * Location Below
	 * @param l - the start location
	 * @return the location below l
	 */
	private Location locationBelow(Location l){
		int numVals = config.getCharacterSet().getNumValues();
		if (l.row == numVals)
			return new Location(1,l.column);
		return new Location(l.row+1,l.column);
	}
}
