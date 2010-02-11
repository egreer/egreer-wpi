package lpf.model.core.sudoku;

import static org.junit.Assert.*;
import lpf.model.core.Grid;
import lpf.model.core.GridFunctions;
import lpf.model.core.Location;
import lpf.model.core.Value;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GridFunctionsTest {

	Grid ig;
	Grid sg;

	//Set up Values
	Value one = new Value('1');
	Value two = new Value('2');
	Value three = new Value('3');
	Value four = new Value('4');

	//Set up location
	Location a1 = new Location (1, 'A');
	Location a2 = new Location (2, 'A');
	Location b1 = new Location (1, 'B');
	Location b2 = new Location (2, 'B');
	
	
	@Before
	public void setUp() throws Exception {
		ig = new Grid(2,2);
		
		ig.getCellAtLocation(a1).addMark(one);
		ig.getCellAtLocation(a1).addMark(two);
		ig.getCellAtLocation(b2).addMark(one);
		ig.getCellAtLocation(b2).addMark(two);
		
		ig.getCellAtLocation(a1).setDigit(one);
		ig.getCellAtLocation(a2).setDigit(two);
		ig.getCellAtLocation(b1).setDigit(three);
		ig.getCellAtLocation(b2).setDigit(four);

	}

	@After
	public void tearDown() throws Exception {
		ig = null;
	}

	@Test
	public void testCloneGrid() {
		Grid cloned = GridFunctions.cloneGrid(ig);
		assertNotSame(cloned, ig);
		assertTrue(ig.getCellAtLocation(a1).equals(cloned.getCellAtLocation(a1)));
		assertTrue(ig.getCellAtLocation(a2).equals(cloned.getCellAtLocation(a2)));
		assertTrue(ig.getCellAtLocation(b1).equals(cloned.getCellAtLocation(b1)));
		assertTrue(ig.getCellAtLocation(b2).equals(cloned.getCellAtLocation(b2)));
		
		//Test Marks
		assertTrue(ig.getCellAtLocation(a1).getMarks().containsAll(cloned.getCellAtLocation(a1).getMarks()));
		assertTrue(ig.getCellAtLocation(b2).getMarks().containsAll(cloned.getCellAtLocation(b2).getMarks()));
	}

	@Test
	public void testCompareGrid() {
		Grid test = ig;
		assertTrue(GridFunctions.compareGrids(ig, test));
		assertTrue(GridFunctions.compareGrids(test, ig));
		
		test = new Grid(2,2);
		test.getCellAtLocation(a1).setDigit(one);
		test.getCellAtLocation(a2).setDigit(one);
		test.getCellAtLocation(b1).setDigit(three);
		test.getCellAtLocation(b2).setDigit(four);
		
		test.getCellAtLocation(b2).setDigit(one);
		assertFalse(GridFunctions.compareGrids(ig, test));
		
		test = new Grid(1, 2);
		test.getCellAtLocation(a1).setDigit(one);
		test.getCellAtLocation(b1).setDigit(three);
		assertFalse(GridFunctions.compareGrids(ig, test));
	}

	@Test
	public void testCopyGrid() {
		Grid copyied = new Grid(2,2);
		copyied.getCellAtLocation(a1).setDigit(three);
		copyied.getCellAtLocation(a2).addMark(four);
		copyied.getCellAtLocation(a2).addMark(two);
		
		//Test function
		Grid returned = GridFunctions.copyGrid(copyied, ig);
		assertSame(copyied, returned);
		assertTrue(ig.getCellAtLocation(a1).equals(copyied.getCellAtLocation(a1)));
		assertTrue(ig.getCellAtLocation(a2).equals(copyied.getCellAtLocation(a2)));
		assertTrue(ig.getCellAtLocation(b1).equals(copyied.getCellAtLocation(b1)));
		assertTrue(ig.getCellAtLocation(b2).equals(copyied.getCellAtLocation(b2)));
		
		//Test Marks
		assertTrue(ig.getCellAtLocation(a1).getMarks().containsAll(copyied.getCellAtLocation(a1).getMarks()));
		assertTrue(ig.getCellAtLocation(b2).getMarks().containsAll(copyied.getCellAtLocation(b2).getMarks()));
	}
}
