package lpf.GUIs.GridView;

import java.awt.Point;
import java.awt.Rectangle;

import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.model.core.Cell;
import lpf.model.core.Grid;
import lpf.model.core.Location;
import lpf.model.core.Puzzle;

/**
 * 
 * A manager class for the a specific puzzle and game instance
 * 
 * @author Nik Deapen
 *
 */
public class PuzzleManager {

	SudokuAlphaGameConfiguration config = SudokuAlphaGameConfiguration.getInstance();
	boolean isMarkMode;
	GridView view;
	Puzzle p;
	Location selectedLocation;
	
	/**
	 * Creates a puzzle manager with a given puzzle 
	 * Note the puzzle can be null but shouldnt
	 * @param p - a puzzle
	 */
	public PuzzleManager(Puzzle p){
		this.p = p;
	}
	
	/**
	 * Sets the grid view
	 * @param gv the new grid view
	 */
	public void setView(GridView gv){
		this.view = gv;
	}
	
	/**
	 * Set the selected location
	 * @param l the location
	 */
	public void setSelectedLocation(Location l){
		this.selectedLocation = l;
	}
	
	/**
	 * Select a location by given a point 
	 * @param p the point
	 */
	public void selectLocation(Point p){
		this.selectedLocation = getLocationAtPoint(p);
	}
	
	/**
	 * Gets a location at the point p
	 * @param p the point
	 * @return the location at that point
	 */
	private Location getLocationAtPoint(Point p) {
		int numRows = config.getCharacterSet().getNumValues();
		
		int width = this.view.getWidth() / numRows;
		int height = this.view.getHeight() / numRows;
		int x = p.x / width;
		int y = p.y / height;
		
		Location l =  new Location(y+1,(char)(x+'a'));

		return l;
	}

	/**
	 * Get the selected location
	 * @return a lcation
	 */
	public Location getSelectedLocation(){
		return selectedLocation;
	}
	
	/**
	 * get the selected cell
	 * @return a cell
	 */
	public Cell getSelectedCell(){
		if (selectedLocation == null)
			return null;
		return this.p.getPlayerGrid().getCellAtLocation(selectedLocation);
	}
	
	/**
	 * Is mark mode?
	 * @return it is mark mode
	 */
	public boolean isMarkMode(){
		return this.isMarkMode;
	}
	
	/**
	 * Sets the mode
	 * @param b - true if mark mode
	 */
	public void setMarkMode(boolean b){
		this.isMarkMode = b;
	}

	/**
	 * Gets a rectangle of the bounds 
	 * @return the bounds of the selected cell
	 */
	public Rectangle getSelectedRectangle() {
		if (selectedLocation == null)
			return null;
		
		int numVals = config.getCharacterSet().getNumValues();
		int row = selectedLocation.row - 1;
		int col = selectedLocation.column - 'a';
		
		int width = this.view.getWidth() / numVals;
		int height = this.view.getHeight() / numVals;
		int x = width * col;
		int y = height * row;
		
		
		return new Rectangle(x,y,width,height);
	}

	/**
	 * Get the grid
	 * @return
	 */
	public Grid getGrid() {
		if(p==null)
			return null;
		return this.p.getPlayerGrid();
	}
	
	/**
	 * is the cell fixed/
	 * @param c the cell
	 * @return
	 */
	public boolean isFixed(Cell c){
		if (p.getInitialGrid().getCellAtLocation(c.loc).getDigit() == null)
			return false;
		return true;
	}
}
