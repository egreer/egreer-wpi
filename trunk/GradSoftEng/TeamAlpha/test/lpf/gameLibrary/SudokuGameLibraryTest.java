package lpf.gameLibrary;

import static org.junit.Assert.*;
import lpf.gameLibrary.NoPuzzleAvailableException;
import lpf.gameLibrary.SudokuGameLibrary;
import lpf.model.core.Grid;
import lpf.model.core.Location;
import lpf.model.core.Puzzle;
import lpf.model.core.Value;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SudokuGameLibraryTest {
	SudokuGameLibrary lib;
	Puzzle one;
	Puzzle two;
	Puzzle three;
	
	@Before
	public void setUp() throws Exception{
		lib = new SudokuGameLibrary();
		one = new Puzzle(new Grid(1, 1));
		two = new Puzzle(new Grid(1, 1));
		three = new Puzzle(new Grid(1, 1));
		
		//Makes the solution grids valid
		one.getSolutionGrid().getCellAtLocation(new Location(1, 'A')).setDigit(new Value('1'));
		two.getSolutionGrid().getCellAtLocation(new Location(1, 'A')).setDigit(new Value('1'));
		three.getSolutionGrid().getCellAtLocation(new Location(1, 'A')).setDigit(new Value('1'));
		
		//Makes the initial grids valid
		one.setInitialGrid(new Grid(1, 1));
		two.setInitialGrid(new Grid(1, 1));
		three.setInitialGrid(new Grid(1, 1));
		
		one.setDifficulty(1);
		two.setDifficulty(2);
		three.setDifficulty(1);
	}
	
	@After
	public void tearDown() throws Exception {
		lib =  null;
		one = two = three = null;
	}
	
	@Test
	public void testSudokuGameLibrary() {
		assertTrue(lib.puzzles.containsKey(10));
		assertEquals(lib.puzzles.get(1).size(), 0);
		assertFalse(lib.puzzles.containsKey(0));
	}

	@Test
	public void testGetPuzzles() {
		lib.addPuzzle(one);
		lib.addPuzzle(two);
		lib.addPuzzle(three);
		assertTrue(lib.getPuzzles().contains(one));
		assertTrue(lib.getPuzzles().contains(two));
		assertTrue(lib.getPuzzles().contains(three));
	}

	@Test
	public void testGetRandomPuzzle() {
		
		lib.addPuzzle(one);
		lib.addPuzzle(three);
		lib.addPuzzle(two);
		try{
		Puzzle randomPuzzle = lib.getRandomPuzzle(1);
		assertTrue(randomPuzzle == one || randomPuzzle == three);
		
		randomPuzzle = lib.getRandomPuzzle(2);
		assertEquals(randomPuzzle, two);
		} catch (NoPuzzleAvailableException e){
			fail("NoPuzzleAvailableException thrown");
		}
		
		//Test Exceptions
		try{
			lib.getRandomPuzzle(10);
			fail("Failed to throw NoPuzzleAvailableException");
		}catch (NoPuzzleAvailableException e){
			assertEquals(e.getMessage(), "No puzzle of difficulty: 10");
		}
		
		try{
			lib.getRandomPuzzle(0);
			fail("Failed to throw NoPuzzleAvailableException");
		}catch (NoPuzzleAvailableException e){
			assertEquals(e.getMessage(), "No puzzle of difficulty: 0");
		}
	}
	
	@Test
	public void testAddPuzzle() {
		lib.addPuzzle(one);
		assertEquals(lib.puzzles.get(1).size(), 1);
		assertTrue(lib.puzzles.get(1).contains(one));
		
		lib.addPuzzle(three);
		assertEquals(lib.puzzles.get(1).size(), 2);
		assertTrue(lib.puzzles.get(1).contains(three));
	}

}
