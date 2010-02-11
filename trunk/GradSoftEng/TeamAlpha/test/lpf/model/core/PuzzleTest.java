package lpf.model.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import lpf.model.core.Cell;
import lpf.model.core.Grid;
import lpf.model.core.Location;
import lpf.model.core.Puzzle;
import lpf.model.core.Value;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** Tests the FunctionPuzzle 
 * 
 * @author Eric Greer
 *
 */
public class PuzzleTest {

	Grid ig;
	Grid sg;
	Puzzle p;

	//Set up Values
	Value one = new Value('1');
	Value two = new Value('2');
	Value three = new Value('3');
	Value four = new Value('4');

	//Set up the locations
	Location a1 = new Location (1, 'A');
	Location a2 = new Location (2, 'A');
	Location b1 = new Location (1, 'B');
	Location b2 = new Location (2, 'B');

	@Before
	public void setUp() throws Exception{
		ig = new Grid(2,2);
		sg = new Grid(2,2);
		p = new Puzzle(sg);
	}

	@After
	public void tearDown() throws Exception {
		ig = null;
		sg = null;
		p = null;
	}

	@Test
	public void testPuzzle() {
		assertEquals(p.solutionGrid, sg);
	}

	@Test
	public void testHasWon() {
		//Setup puzzle
		Grid wsg = new Grid(1,1);
		Grid wig = new Grid(1,1);
		Puzzle wp = new Puzzle(wsg);
		wp.setInitialGrid(wig);

		wp.getSolutionGrid().getCellAtLocation(a1).setDigit(one);
		wp.getPlayerGrid().getCellAtLocation(a1).setDigit(one);

		assertTrue(wp.hasWon());

		wp.getPlayerGrid().getCellAtLocation(a1).setDigit(two);
		assertFalse(wp.hasWon());
	}

	@Test
	public void testGetIncorrect() {
		p.setInitialGrid(ig);



		//Set values in solution
		p.getSolutionGrid().getCellAtLocation(a1).setDigit(one);
		p.getSolutionGrid().getCellAtLocation(a2).setDigit(two);
		p.getSolutionGrid().getCellAtLocation(b1).setDigit(three);
		p.getSolutionGrid().getCellAtLocation(b2).setDigit(four);

		p.getPlayerGrid().getCellAtLocation(a1).setDigit(two);
		p.getPlayerGrid().getCellAtLocation(a2).setDigit(two);

		// Create Solution Set
		Iterator<Cell> playerIterator = p.getPlayerGrid().iterator();
		ArrayList<Cell> playerCells = new ArrayList<Cell>();
		while( playerIterator.hasNext()){
			playerCells.add(playerIterator.next());
		}

		//Start Testing
		Iterator<Cell> incorrect = p.getIncorrect().iterator();

		int i = 1;
		while (incorrect.hasNext()){
			assertTrue(playerCells.contains(incorrect.next()));
			i++;
		}

		assertEquals(i, playerCells.size());

	}

	@Test
	public void testReset() {
		p.setInitialGrid(ig);
		//p.getPlayerGrid().getCellAtLocation(loc);
		p.reset();
		assertNotSame(p.playerGrid, p.initialGrid);
	}

	@Test
	public void testGetPlayerGrid() {
		assertSame(p.getPlayerGrid(), p.playerGrid);
	}

	@Test
	public void testGetInitialGrid() {
		p.setInitialGrid(ig);
		assertSame(p.getInitialGrid(), p.initialGrid);
		assertSame(p.getInitialGrid(), ig);
	}

	@Test
	public void testGetSolutionGrid() {
		assertSame(p.getSolutionGrid(), p.solutionGrid);
		assertSame(p.getSolutionGrid(), sg);
	}

	@Test
	public void testSetInitialGrid() {
		p.setInitialGrid(ig);
		assertSame(p.initialGrid, ig);
		assertNotSame(p.playerGrid, ig);
	}

	@Test 
	public void testSetDifficulty() {
		p.setDifficulty(10);
		assertEquals(p.difficulty, 10);
	}

	@Test 
	public void testSetDifficultyError() {
		try{
			p.setDifficulty(11);
			fail("SetDifficulty Should have Thrown Exception");
		} catch (IllegalArgumentException e){
			assertEquals("Difficulty outside of allowed range.", e.getMessage());

		}try{
			p.setDifficulty(0);
			fail("SetDifficulty Should have Thrown Exception");
		} catch (IllegalArgumentException e){
			assertEquals("Difficulty outside of allowed range.", e.getMessage());

		}
	}

	@Test
	public void testGetDifficulty() {
		p.setDifficulty(10);
		assertEquals(p.getDifficulty(), 10);
	}

}
