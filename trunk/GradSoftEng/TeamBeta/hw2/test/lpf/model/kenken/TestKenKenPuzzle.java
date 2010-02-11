package lpf.model.kenken;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;

import junit.framework.TestCase;
import lpf.model.core.Grid;
import lpf.model.core.Location;
import lpf.model.core.Value;
import lpf.model.kenken.preferences.Difficulty;

/**
 * Tests for KenKenPuzzle
 * 
 * @author Peter Kalauskas
 * @author Nam Do
 * 
 */
public class TestKenKenPuzzle extends TestCase {
	
	private Grid solution;
	
	@Before
	public void setUp() throws Exception
	{
		solution = new Grid(3, 3);
		solution.getCellAtLocation(new Location(1, 'A')).setDigit(new Value('3'));
		solution.getCellAtLocation(new Location(2, 'A')).setDigit(new Value('2'));
		solution.getCellAtLocation(new Location(3, 'A')).setDigit(new Value('1'));

		solution.getCellAtLocation(new Location(1, 'B')).setDigit(new Value('1'));
		solution.getCellAtLocation(new Location(2, 'B')).setDigit(new Value('3'));
		solution.getCellAtLocation(new Location(3, 'B')).setDigit(new Value('2'));

		solution.getCellAtLocation(new Location(1, 'C')).setDigit(new Value('2'));
		solution.getCellAtLocation(new Location(2, 'C')).setDigit(new Value('1'));
		solution.getCellAtLocation(new Location(3, 'C')).setDigit(new Value('3'));
	}
	
	public void testGoodPuzzle() throws InvalidKenKenPuzzleException {
		
		solution.getCellAtLocation(new Location(1, 'A')).setDigit(new Value('3'));
		solution.getCellAtLocation(new Location(2, 'A')).setDigit(new Value('2'));
		solution.getCellAtLocation(new Location(3, 'A')).setDigit(new Value('1'));

		solution.getCellAtLocation(new Location(1, 'B')).setDigit(new Value('1'));
		solution.getCellAtLocation(new Location(2, 'B')).setDigit(new Value('3'));
		solution.getCellAtLocation(new Location(3, 'B')).setDigit(new Value('2'));

		solution.getCellAtLocation(new Location(1, 'C')).setDigit(new Value('2'));
		solution.getCellAtLocation(new Location(2, 'C')).setDigit(new Value('1'));
		solution.getCellAtLocation(new Location(3, 'C')).setDigit(new Value('3'));

		ArrayList<Cage> cages = new ArrayList<Cage>();

		ArrayList<Location> locs1 = new ArrayList<Location>();
		locs1.add(new Location(1, 'A'));
		locs1.add(new Location(2, 'A'));
		locs1.add(new Location(3, 'A'));
		Cage cage1 = new Cage('*', 6, locs1);
		cages.add(cage1);

		ArrayList<Location> locs2 = new ArrayList<Location>();
		locs2.add(new Location(1, 'B'));
		locs2.add(new Location(2, 'B'));
		locs2.add(new Location(2, 'C'));
		Cage cage2 = new Cage('+', 5, locs2);
		cages.add(cage2);

		ArrayList<Location> locs3 = new ArrayList<Location>();
		locs3.add(new Location(1, 'C'));
		Cage cage3 = new Cage('+', 2, locs3);
		cages.add(cage3);

		ArrayList<Location> locs4 = new ArrayList<Location>();
		locs4.add(new Location(3, 'B'));
		locs4.add(new Location(3, 'C'));
		Cage cage4 = new Cage('+', 5, locs4);
		cages.add(cage4);

		KenKenPuzzle puzzle = new KenKenPuzzle(cages, solution);
		assertNull(puzzle.getDifficultyLevel());
		
		puzzle.setDifficulty(5);
		assertEquals(puzzle.getDifficultyLevel(), Difficulty.MEDIUM);
		
		assertEquals(cages,puzzle.getCages());
		
		Grid playerGrid = new Grid(3,3);
		playerGrid.getCellAtLocation(new Location(1, 'A')).setDigit(new Value('3'));
		playerGrid.getCellAtLocation(new Location(3, 'A')).setDigit(new Value('1'));

		playerGrid.getCellAtLocation(new Location(3, 'B')).setDigit(new Value('2'));

		playerGrid.getCellAtLocation(new Location(1, 'C')).setDigit(new Value('2'));
		playerGrid.getCellAtLocation(new Location(3, 'C')).setDigit(new Value('3'));
		
		puzzle.setInitialGrid(playerGrid);
		puzzle.identifyIncorrect();
		
		assertEquals(puzzle.getIdentifiedIncorrectCells().size(),0);
		
		playerGrid = new Grid(3,3);
		playerGrid.getCellAtLocation(new Location(1, 'A')).setDigit(new Value('3'));
		playerGrid.getCellAtLocation(new Location(3, 'A')).setDigit(new Value('1'));

		playerGrid.getCellAtLocation(new Location(3, 'B')).setDigit(new Value('3'));

		playerGrid.getCellAtLocation(new Location(1, 'C')).setDigit(new Value('2'));
		playerGrid.getCellAtLocation(new Location(3, 'C')).setDigit(new Value('1'));
		
		puzzle.setInitialGrid(playerGrid);
		puzzle.identifyIncorrect();
		
		assertEquals(puzzle.getIdentifiedIncorrectCells().size(), 2);
		puzzle.removeIncorrect();
		
		assertNotNull(puzzle.getPlayerGrid().getCellAtLocation(new Location(1, 'A')).getDigit());
		assertNotNull(puzzle.getPlayerGrid().getCellAtLocation(new Location(3, 'A')).getDigit());
		
		assertNull(puzzle.getPlayerGrid().getCellAtLocation(new Location(3, 'B')).getDigit());
		
		assertNotNull(puzzle.getPlayerGrid().getCellAtLocation(new Location(1, 'C')).getDigit());
		assertNull(puzzle.getPlayerGrid().getCellAtLocation(new Location(3, 'C')).getDigit());
		
	}

	public void testIncompleteSolution() {
		Grid solution = new Grid(3, 3);
		solution.getCellAtLocation(new Location(1, 'A')).setDigit(new Value('3'));
		solution.getCellAtLocation(new Location(2, 'A')).setDigit(new Value('2'));
	
		solution.getCellAtLocation(new Location(1, 'B')).setDigit(new Value('1'));
		solution.getCellAtLocation(new Location(2, 'B')).setDigit(new Value('3'));
		solution.getCellAtLocation(new Location(3, 'B')).setDigit(new Value('2'));
	
		solution.getCellAtLocation(new Location(1, 'C')).setDigit(new Value('2'));
		solution.getCellAtLocation(new Location(2, 'C')).setDigit(new Value('1'));
		solution.getCellAtLocation(new Location(3, 'C')).setDigit(new Value('3'));
	
		try {
			new KenKenPuzzle(null, solution);
			fail();
		} catch (InvalidKenKenPuzzleException e) {
			assertEquals(e.getMessage(), "Solution is incomplete.");
		}
	}

	public void testNonSquareSolution() throws InvalidKenKenPuzzleException {
		Grid solution = new Grid(2, 3);
		solution.getCellAtLocation(new Location(1, 'A')).setDigit(new Value('3'));
		solution.getCellAtLocation(new Location(2, 'A')).setDigit(new Value('2'));
		solution.getCellAtLocation(new Location(3, 'A')).setDigit(new Value('1'));
	
		solution.getCellAtLocation(new Location(1, 'B')).setDigit(new Value('1'));
		solution.getCellAtLocation(new Location(2, 'B')).setDigit(new Value('3'));
		solution.getCellAtLocation(new Location(3, 'B')).setDigit(new Value('2'));
	
		try {
			new KenKenPuzzle(null, solution);
			fail();
		} catch (InvalidKenKenPuzzleException e) {
			assertEquals(e.getMessage(), "Solution is not square");
		}
	}

	public void testBadSolutionGridUniqueness() {		
		solution.getCellAtLocation(new Location(3, 'B')).setDigit(new Value('1'));
		
		try {
			new KenKenPuzzle(null, solution);
			fail();
		} catch (InvalidKenKenPuzzleException e) {
			assertEquals(e.getMessage(), "Row is not unique.");
		}
	}

	public void testBadSolutionGridValues() {
		
		solution.getCellAtLocation(new Location(3, 'B')).setDigit(new Value('4'));
	
		solution.getCellAtLocation(new Location(1, 'C')).setDigit(new Value('4'));
		
		try {
			new KenKenPuzzle(null, solution);
			fail();
		} catch (InvalidKenKenPuzzleException e) {
			assertEquals(e.getMessage(), "Value in solution grid is out of range.");
		}
	}

	public void testInvalidCages() throws IOException {
	
		ArrayList<Cage> cages = new ArrayList<Cage>();
	
		ArrayList<Location> locs1 = new ArrayList<Location>();
		locs1.add(new Location(1, 'A'));
		locs1.add(new Location(2, 'A'));
		locs1.add(new Location(3, 'A'));
		Cage cage1 = new Cage('*', 6, locs1);
		cages.add(cage1);
	
		ArrayList<Location> locs2 = new ArrayList<Location>();
		locs2.add(new Location(1, 'B'));
		locs2.add(new Location(2, 'B'));
		locs2.add(new Location(2, 'C'));
		Cage cage2 = new Cage('+', 3, locs2);
		cages.add(cage2);
	
		ArrayList<Location> locs3 = new ArrayList<Location>();
		locs3.add(new Location(1, 'C'));
		Cage cage3 = new Cage('+', 2, locs3);
		cages.add(cage3);
	
		ArrayList<Location> locs4 = new ArrayList<Location>();
		locs4.add(new Location(3, 'B'));
		locs4.add(new Location(3, 'C'));
		Cage cage4 = new Cage('+', 5, locs4);
		cages.add(cage4);
	
		try {
			new KenKenPuzzle(cages, solution);
			fail();
		} catch (InvalidKenKenPuzzleException e) {
			assertEquals(e.getMessage(), "Cage + 3 invalid.");
		}
	}

	public void testCagesOverlap() throws IOException {
		ArrayList<Cage> cages = new ArrayList<Cage>();

		ArrayList<Location> locs1 = new ArrayList<Location>();
		locs1.add(new Location(1, 'A'));
		locs1.add(new Location(2, 'A'));
		locs1.add(new Location(3, 'A'));
		Cage cage1 = new Cage('*', 6, locs1);
		cages.add(cage1);

		ArrayList<Location> locs2 = new ArrayList<Location>();
		locs2.add(new Location(1, 'B'));
		locs2.add(new Location(2, 'B'));
		locs2.add(new Location(2, 'C'));
		locs2.add(new Location(1, 'C'));
		Cage cage2 = new Cage('+', 7, locs2);
		cages.add(cage2);

		ArrayList<Location> locs3 = new ArrayList<Location>();
		locs3.add(new Location(1, 'C'));
		Cage cage3 = new Cage('+', 2, locs3);
		cages.add(cage3);

		ArrayList<Location> locs4 = new ArrayList<Location>();
		locs4.add(new Location(3, 'B'));
		locs4.add(new Location(3, 'C'));
		Cage cage4 = new Cage('+', 5, locs4);
		cages.add(cage4);

		try {
			new KenKenPuzzle(cages, solution);
			fail();
		} catch (InvalidKenKenPuzzleException e) {
			assertEquals(e.getMessage(), "Cages are overlapping");
		}
	}

	public void testCagesNotCoveringAllCells() throws InvalidKenKenPuzzleException {	
	
		ArrayList<Cage> cages = new ArrayList<Cage>();
	
		ArrayList<Location> locs1 = new ArrayList<Location>();
		locs1.add(new Location(1, 'A'));
		locs1.add(new Location(2, 'A'));
		locs1.add(new Location(3, 'A'));
		Cage cage1 = new Cage('*', 6, locs1);
		cages.add(cage1);
	
		ArrayList<Location> locs2 = new ArrayList<Location>();
		locs2.add(new Location(1, 'B'));
		locs2.add(new Location(2, 'B'));
		Cage cage2 = new Cage('+', 4, locs2);
		cages.add(cage2);
	
		ArrayList<Location> locs3 = new ArrayList<Location>();
		locs3.add(new Location(1, 'C'));
		Cage cage3 = new Cage('+', 2, locs3);
		cages.add(cage3);
	
		ArrayList<Location> locs4 = new ArrayList<Location>();
		locs4.add(new Location(3, 'B'));
		locs4.add(new Location(3, 'C'));
		Cage cage4 = new Cage('+', 5, locs4);
		cages.add(cage4);
	
		try {
			new KenKenPuzzle(cages, solution);
			fail();
		} catch (InvalidKenKenPuzzleException e) {
			assertEquals(e.getMessage(),
					"Not every cell location is covered by a cage.");
		}
	}

	public void testCellsNotAdjacentInCage() {	

		ArrayList<Cage> cages = new ArrayList<Cage>();

		ArrayList<Location> locs1 = new ArrayList<Location>();
		locs1.add(new Location(1, 'A'));
		locs1.add(new Location(2, 'A'));
		locs1.add(new Location(3, 'A'));
		Cage cage1 = new Cage('*', 6, locs1);
		cages.add(cage1);

		ArrayList<Location> locs2 = new ArrayList<Location>();
		locs2.add(new Location(1, 'B'));
		locs2.add(new Location(2, 'B'));
		locs2.add(new Location(2, 'C'));
		locs2.add(new Location(3, 'A'));
		Cage cage2 = new Cage('+', 6, locs2);
		cages.add(cage2);

		ArrayList<Location> locs3 = new ArrayList<Location>();
		locs3.add(new Location(1, 'C'));
		Cage cage3 = new Cage('+', 2, locs3);
		cages.add(cage3);

		ArrayList<Location> locs4 = new ArrayList<Location>();
		locs4.add(new Location(3, 'B'));
		locs4.add(new Location(3, 'C'));
		Cage cage4 = new Cage('+', 5, locs4);
		cages.add(cage4);

		try {
			new KenKenPuzzle(cages, solution);
			fail();
		} catch (InvalidKenKenPuzzleException e) {
			assertEquals(e.getMessage(), "Cells in Cage are not adjacent");
		}
	}
}
