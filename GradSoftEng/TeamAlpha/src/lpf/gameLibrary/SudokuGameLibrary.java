package lpf.gameLibrary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import lpf.model.core.Puzzle;


/**
 * 
 * The library of Sudoku games
 * 
 * @author Eric Greer
 *
 */
public class SudokuGameLibrary implements IPuzzleLibrary {
	HashMap<Integer, ArrayList<Puzzle>> puzzles;
	
	/**
	 * Default constructor for a library
	 */
	public SudokuGameLibrary(){
		this.puzzles = new HashMap<Integer, ArrayList<Puzzle>>(11, 1);
		
		for (int i = 1 ; i <= 10; i++ ){
			puzzles.put(i, new ArrayList<Puzzle>());
			
		}
	}
	
	/**
	 * Returns a collection of the puzzles in the library
	 * 
	 * @return	Returns all puzzles in the library
	 */
	public Collection<Puzzle> getPuzzles() {
		Collection<Puzzle> returner = new LinkedList<Puzzle>();
		Iterator<ArrayList<Puzzle>> puzzlesCollection = this.puzzles.values().iterator();
		while(puzzlesCollection.hasNext()){
			returner.addAll(puzzlesCollection.next());
		}
		
		return returner;
	}

	/**
	 * Get random puzzle returns a puzzle from the library of the desired difficulty
	 * 
	 * @param	difficulty	The desired difficulty of the puzzle return 
	 * @return				The random puzzle
	 * @throws				Throws an NoPuzzleAvailableException if there isn't a puzzle of that difficulty 
	 */
	public Puzzle getRandomPuzzle(int difficulty)
			throws NoPuzzleAvailableException {
		Puzzle returner = null;

		ArrayList<Puzzle> puzzlesOfDifficulty = this.puzzles.get(difficulty);
		
		//Get random Puzzle
		if(puzzlesOfDifficulty == null || puzzlesOfDifficulty.size() == 0) throw new NoPuzzleAvailableException("No puzzle of difficulty: " + difficulty ); 
		while(returner == null){
			int rand = (int) Math.round(Math.random() * puzzlesOfDifficulty.size());
			if (0 <= rand && rand < puzzlesOfDifficulty.size()) returner = puzzlesOfDifficulty.get(rand); 
		}
						
		return returner;
	}

	/**
	 * Adds a Puzzle to the library if it is valid
	 * 
	 * @param p	The puzzle to be added to the library.
	 */
	public void addPuzzle(Puzzle p){
		if(SudokuPuzzleValidator.ValidatePuzzle(p)){
			puzzles.get(p.getDifficulty()).add(p);
		}
	}

}
