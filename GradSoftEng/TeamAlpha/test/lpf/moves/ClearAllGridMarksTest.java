package lpf.moves;

import junit.framework.TestCase;
import lpf.model.core.Grid;
import lpf.model.core.GridFunctions;
import lpf.model.core.Location;
import lpf.model.core.Value;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Eric Greer
 *
 */
public class ClearAllGridMarksTest extends TestCase {

	Grid testGrid;

	//Set up Values
	Value one = new Value('1');
	Value two = new Value('2');
	Value three = new Value('3');
	Value four = new Value('4');

	//Set up the locations
	Location a1 = new Location (1, 'A');
	Location b1 = new Location (1, 'B');
	ClearAllGridMarksMove testMove;
	
	@Before
	public void setUp() throws Exception{
		testGrid = new Grid(1,2);
		testGrid.getCellAtLocation(a1).addMark(one);
		testGrid.getCellAtLocation(a1).addMark(two);
		
		testGrid.getCellAtLocation(b1).addMark(one);
		testGrid.getCellAtLocation(b1).addMark(two);
		testGrid.getCellAtLocation(b1).setDigit(one);
		testMove = new ClearAllGridMarksMove(testGrid);
	}

	@After
	public void tearDown() throws Exception{
		testGrid = null;
		testMove = null;
	}
	
	@Test
	public void testClearAllGridMarksMove() {
		assertSame(testMove.grid, testGrid);
		assertNull(testMove.oldGrid);
	}

	@Test
	public void testDoMove() {
		assertTrue(testMove.doMove());
		assertTrue(testMove.grid.getCellAtLocation(a1).getMarks().isEmpty());
		assertFalse(testMove.grid.getCellAtLocation(b1).getMarks().isEmpty());
		
		assertFalse(testMove.oldGrid.getCellAtLocation(a1).getMarks().isEmpty());
		assertTrue(GridFunctions.compareGrids(testMove.oldGrid, testGrid));
	}

	@Test
	public void testIsValid() {
		assertTrue(testMove.isValid());
		
		testGrid.getCellAtLocation(a1).setDigit(one);
		assertFalse(testMove.isValid());
	}

	@Test
	public void testUndo() {
		testMove.doMove();
		testMove.undo();
			
		assertTrue(testMove.grid.getCellAtLocation(a1).getMarks().containsAll(testMove.oldGrid.getCellAtLocation(a1).getMarks()));
		assertTrue(testMove.grid.getCellAtLocation(b1).getMarks().containsAll(testMove.oldGrid.getCellAtLocation(b1).getMarks()));
		
	}

}
