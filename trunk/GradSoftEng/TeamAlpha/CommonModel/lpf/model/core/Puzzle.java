package lpf.model.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/** 
 * 
 * @author Eric Greer
 *
 */
public class Puzzle {
	public int difficulty;
	public Grid initialGrid;
	public Grid playerGrid;
	public Grid solutionGrid;
	
	/** Constructor for creating a puzzle
	 * 
	 * @param solution	The solution grid to the puzzle
	 */
	public Puzzle(Grid solution) {
		this.solutionGrid = solution;
	}
	
	/** hasWon is used to determine if the puzzle has been won based on the solution.
	 * 
	 * @return 	Returns true if the game is won
	 */
	public boolean hasWon() {
		if (this.getIncorrect().iterator().hasNext()){
			return false;
		}
		return true;
	}
	
	/** getIncorrect returns the cells from the playerGrid that have digits 
	 * that are not correctly placed when compared to the solution grid.
	 * 
	 * @return Returns a iterator of the incorrect cells, empty iterator if all cells match 
	 */
	public Collection<Cell> getIncorrect() {
		Iterator<Cell> playCells = this.playerGrid.iterator();
		ArrayList<Cell> incorrectCells = new ArrayList<Cell>();

		while(playCells.hasNext()){
			Cell playCell = playCells.next();
			Cell solCell = solutionGrid.getCellAtLocation(playCell.loc);
			if (!playCell.equals(solCell)){
				incorrectCells.add(playCell);
			}
		}
		return incorrectCells;
	}
	
	/** This method resets the puzzle's playing grid to the initial grid
	 * 
	 */
	public void reset() {
		GridFunctions.copyGrid(this.playerGrid, this.initialGrid);
	}
	
	/** getPlayerGrid returns the current playing grid
	 * 
	 * @return Returns the current grid
	 */
	public Grid getPlayerGrid() {
		return playerGrid;
	}
	
	/** getInitialGrid returns the initial grid of the puzzle
	 * 
	 * @return	Returns the initial grid 
	 */
	public Grid getInitialGrid() {
		return initialGrid;
	}
	
	/** Returns the solution grid for the puzzle
	 * 
	 * @return	Returns the solution grid for the puzzle
	 */
	public Grid getSolutionGrid() {
		return this.solutionGrid;
	}
	
	/** Sets the initial grid of the puzzle
	 * 
	 * @param g		The initial grid for the puzzle  
	 */
	public void setInitialGrid(Grid g) {
		//Sets the player grid
		this.playerGrid = GridFunctions.cloneGrid(g);
		
		//Sets the initial grid
		this.initialGrid = g;
	}
	
	/** Sets the difficulty of the current puzzle
	 * 
	 * @param d		The difficulty the puzzle should be set to  
	 */
	public void setDifficulty(int d) {
		if ( d <= 10 && d >= 1){
			this.difficulty = d;
		}else{
			throw new IllegalArgumentException("Difficulty outside of allowed range.");
		}
		
	}
	
	/** Returns the difficulty of the current puzzle
	 * 
	 * @return Returns an int of the current difficulty
	 */
	public int getDifficulty() {
		return this.difficulty;
	}
}
