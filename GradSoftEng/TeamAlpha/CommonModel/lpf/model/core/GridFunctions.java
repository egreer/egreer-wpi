package lpf.model.core;

import java.util.Iterator;

/**
 * 
 * @author Eric Greer
 *
 */
public class GridFunctions {
	
	/** Creates a new copy of a grid.
	 * 
	 * @param orig 	The grid to clone
	 * @return		returns a new copy of the grid passed
	 */
	public static Grid cloneGrid(Grid orig){
		Grid returner = new Grid(orig.height, orig.width);
		Iterator<Cell> copyIterator = orig.iterator();
		//Copy cells over
		while(copyIterator.hasNext()){
			Cell copyCell = copyIterator.next();
			Cell newCell = returner.getCellAtLocation(copyCell.loc);
			newCell.setDigit(copyCell.getDigit());
			
			//Copy marks over
			Iterator<Value> markIterator = (Iterator<Value>) copyCell.getMarks().iterator();
			while(markIterator.hasNext()){
				newCell.addMark(markIterator.next());
			}
		}
		return returner;
	}
	
	/**  Compares two grids to see if they are the same.
	 * 	Grids are equal if they are the same size, and all locations 
	 *  contain the same values in the cell at the location 
	 * 
	 * @param one	The first grid
	 * @param two	the second grid
	 * @return		True if the grids are the same
	 */
	public static boolean compareGrids(Grid one, Grid two){
		if (one.height != two.height || two.width != two.width) return false;
		Iterator<Cell> oneCells = one.iterator();

		while(oneCells.hasNext()){
			Cell oneCell = oneCells.next();
			Cell twoCell = two.getCellAtLocation(oneCell.loc);
			if (!oneCell.equals(twoCell)){
				return false;
			}
		}
		return true;
	}
	
	
	/** Copies all digits from one grid to another and 
	 * copies all marks, removing any that currently exist
	 * 
	 * @param copyTo 	The grid to copy values to 
	 * @param copyFrom  The grid to copy values from
	 * @return			returns the copyTo grid
	 */
	public static Grid copyGrid(Grid copyTo, Grid copyFrom){
		
		Iterator<Cell> copyIterator = copyFrom.iterator();
		//Copy cells over
		while(copyIterator.hasNext()){
			Cell copyCell = copyIterator.next();
			Cell newCell = copyTo.getCellAtLocation(copyCell.loc);
			newCell.setDigit(copyCell.getDigit());
			
			//Copy marks over
			newCell.clearMarks();
			Iterator<Value> markIterator = (Iterator<Value>) copyCell.getMarks().iterator();
			while(markIterator.hasNext()){
				newCell.addMark(markIterator.next());
			}
		}
		return copyTo;
	}
}
