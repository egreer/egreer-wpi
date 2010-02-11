package lpf.moves;

import static org.junit.Assert.*;
import lpf.model.core.Grid;
import lpf.model.core.Location;
import lpf.model.core.Puzzle;
import lpf.model.core.Value;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RemoveIncorrectDigitsMoveTest {

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
	RemoveIncorrectDigitsMove testMove;
	
	@Before
	public void setUp() throws Exception{
		ig = new Grid(1,2);
		sg = new Grid(1,2);
		p = new Puzzle(sg);
		p.setInitialGrid(ig);
		
		//Setup some Test marks
		p.getPlayerGrid().getCellAtLocation(a1).addMark(one);
		p.getPlayerGrid().getCellAtLocation(a1).addMark(two);
		
		//Setup Player Grid
		p.getPlayerGrid().getCellAtLocation(a1).setDigit(two);
		p.getPlayerGrid().getCellAtLocation(b1).setDigit(three);
		
		//Setup Solution Grid
		p.getSolutionGrid().getCellAtLocation(a1).setDigit(one);
		p.getSolutionGrid().getCellAtLocation(b1).setDigit(three);

		testMove = new RemoveIncorrectDigitsMove(p);
	}

	@After
	public void tearDown() throws Exception {
		ig = null;
		sg = null;
		p = null;
		testMove = null;
	}

	@Test
	public void testRemoveIncorrectDigitsMove() {
		assertSame(p, testMove.puzzle);
		assertTrue(testMove.removedCells.isEmpty());
	}

	@Test
	public void testDoMove() {
		assertTrue(testMove.doMove());
		assertEquals(1, testMove.removedCells.size());
		
		assertNull(p.getPlayerGrid().getCellAtLocation(a1).getDigit());
		assertTrue(p.getPlayerGrid().getCellAtLocation(a1).getMarks().contains(two));
		assertEquals(three, p.getPlayerGrid().getCellAtLocation(b1).getDigit());
	}

	@Test
	public void testIsValid() {
		assertTrue(testMove.isValid());
	}

	@Test
	public void testUndo() {
		testMove.doMove();
		assertTrue(testMove.undo());
		
		assertEquals(0, testMove.removedCells.size());
		
		assertEquals(two, p.getPlayerGrid().getCellAtLocation(a1).getDigit());
		assertTrue(p.getPlayerGrid().getCellAtLocation(a1).getMarks().contains(two));
		
		assertEquals(three, p.getPlayerGrid().getCellAtLocation(b1).getDigit());
	}

}
