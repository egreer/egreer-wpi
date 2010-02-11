package lpf.gameLibrary;

import java.util.Collection;

import lpf.model.core.Puzzle;


/**
 * 
 * Interface for a puzzle game library
 * 
 * @author Nik Deapen
 * @since 1.0.0
 *
 */
public interface IPuzzleLibrary {
	
	/**
	 * 
	 * @return all the puzzles in the library
	 */
	public Collection<Puzzle> getPuzzles();
	
	/**
	 * 
	 * @param d the desired difficulty level
	 * @return a puzzle of that difficulty
	 * @throws NoPuzzleAvailableException if no puzzle is available of that difficulty
	 */
	public Puzzle getRandomPuzzle(int difficulty) throws NoPuzzleAvailableException;
	
	/** Adds a Puzzle to the library if it is valid
	 * 
	 * @param p	The puzzle to be added to the library.
	 */
	public void addPuzzle(Puzzle p);

	
}
