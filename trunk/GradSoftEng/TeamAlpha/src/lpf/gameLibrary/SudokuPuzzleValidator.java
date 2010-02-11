package lpf.gameLibrary;

import java.util.ArrayList;
import java.util.Iterator;

import lpf.model.core.*;


/**
 * 
 * A validator for a sudoku puzzle that checks whether a sudoku game
 * is a valid sudoku puzzle
 * 
 * @author Eric Greer
 *
 */
public class SudokuPuzzleValidator {

	/**
	 * Validates that the given puzzle is a valid SudokuPuzzle
	 * 
	 * @param p			the puzzle to validate
	 * @return			whether the puzzle is valid
	 */
	public static boolean ValidatePuzzle(Puzzle p){
		//Check height and width
		boolean returner = (p.getSolutionGrid().height == p.getSolutionGrid().width);
		
		//Check initial grid
		returner &= ValidateInitialGrid(p);
		
		//Check solution rows
		returner &= ValidateAllRows(p.getSolutionGrid());
		
		//Check solution columns
		returner &= ValidateAllColumns(p.getSolutionGrid());
		
		//Check to see if the puzzle is a perfect square then validate regions
		if(Math.sqrt(p.getSolutionGrid().height) == Math.floor(Math.sqrt(p.getSolutionGrid().height))){
			
			//Validate all regions
			returner &= ValidateAllRegions(p.getSolutionGrid());
		}
		
		return returner;
	}
	
	/**
	 * Validates that there are no duplicates values in each rows of the grid
	 * This ignores empty cells
	 * 
	 * @param g		The grid to validate 
	 * @return		true if all rows are valid, false if any are not
	 */
	public static boolean ValidateAllRows(Grid g){
		boolean returner = true;
		for(int i =1 ; returner && i <= g.height ; i++){
			returner &= ValidateRow(g, i);
		}
		return returner;
	}

	/**
	 * Validates that a row in the grid contains no duplicates
	 * This ignores empty cells
	 * 
	 * @param g		The grid to check
	 * @param row	The row to check
	 * @return		Returns true if values are not duplicated in the give row, false if they are.
	 */
	public static boolean ValidateRow(Grid g, int row){
		ArrayList<Value> unique = new ArrayList<Value>();
		
		for(char i = 'A' ; i < g.width + 'A' ; i++){
			Value temp = g.getCellAtLocation(new Location(row, i)).getDigit();
			if(temp != null) {
				if(unique.contains(temp)) return false;
				else unique.add(temp);
			}
		}
		
		return true;
	}
	
	/**
	 * Validates that there are no duplicates values in each column of the grid
	 * This ignores empty cells
	 * 
	 * @param g		The grid to validate 
	 * @return		true if all columns are valid, false if any are not
	 */
	public static boolean ValidateAllColumns(Grid g){
		boolean returner = true;
		for(char i = 'A' ; returner && i < g.width + 'A'; i++){
			returner &= ValidateCol(g, i);
		}
		return returner;
	}
	
	
	/**
	 * Validates that a column in the grid contains no duplicates
	 * This ignores empty cells
	 *  
	 * @param g			The grid to check
	 * @param column	The column to check
	 * @return			Returns true if values are not duplicated in the given 
	 * 					column,	false if they are.
	 */
	public static boolean ValidateCol(Grid g, char column){
		ArrayList<Value> unique = new ArrayList<Value>();
		
		for(int i = 1 ; i <= g.height ; i++){
			Value temp = g.getCellAtLocation(new Location(i, column)).getDigit();
			if(temp != null) {
				if(unique.contains(temp)) return false;
				else unique.add(temp);
			}
		}
		
		return true;
	}
	
	
	/**
	 * Validates that each region in a Grid contains nod duplicates.
	 * This ignores empty cells.
	 * 
	 * Grid must have the same height and width, and the length of a
	 * side must be a perfect square to generate the regions.
	 *
	 * 
	 * @param g		The grid to validate regions
	 * @return		Returns true is all regions are valid
	 * 				returns false if any region is not valid
	 * 				or a valid grid was not provided 
	 */
	public static boolean ValidateAllRegions(Grid g){
		boolean returner = true;
		
		if (g.height != g.width) return false;
		
		//if the sides are not perfect square then then regions cannot be validated
		int regionLength = (int) Math.floor(Math.sqrt(g.height));
		if( Math.sqrt(g.height) != regionLength) return false;
		
		int i = 1;
		char j = 'A';
		
		while(returner && i <= g.height && j <= g.width + 'A'){
			returner &= ValidateRegion(g, i, i + regionLength - 1, j, (char) (j + regionLength - 1));
			i += regionLength;
			j += regionLength;
		}
				
		return returner;
	}
	
	

	/**
	 * Validates a region of a grid contains no duplicates
	 * This ignores empty cells
	 * 
	 * @param g		The grid to validate
	 * @param sRow	The row that begins the region (inclusive)
	 * @param eRow  the row that ends the region (inclusive)
	 * @param sCol  The Column that begins the region (inclusive)
	 * @param eCol  The Column that ends the region (inclusive)
	 * @return
	 */
	public static boolean ValidateRegion(Grid g, int sRow, int eRow, char sCol, char eCol){
		if(sRow < 1 || g.height < eRow || sCol < 'A' || g.width + 'A' < eCol) return false; //TODO possibly thro exception
		ArrayList<Value> unique = new ArrayList<Value>();
				
		for(int i = sRow ; i <= eRow ; i++){
			for(char j = sCol ; j <=eCol ; j++){
				Value temp = g.getCellAtLocation(new Location(i, j)).getDigit();
				if(temp != null) {
					if(unique.contains(temp)) return false;
					else unique.add(temp);
				} 
			}
		}
		
		return true;
	}
	
	/**
	 * Validates that the cells in the initial grid are filled the same as the 
	 * solution grid.  
	 * 
	 * @param p		The puzzle to validate the grids
	 * @return		Returns true if the values in the initial grid match the 
	 * 				solution grid false if it does not match 
	 */
	public static boolean ValidateInitialGrid(Puzzle p){
		Iterator<Cell> initalCells = p.getInitialGrid().iterator();
		
		while(initalCells.hasNext()){
			Cell initCell = initalCells.next();
			
			if ( initCell.getDigit() == null ) continue;
			Cell solCell = p.getSolutionGrid().getCellAtLocation(initCell.loc);
			
			if (!initCell.equals(solCell)){
				return false;
			}
		}
		return true;
	}
}
