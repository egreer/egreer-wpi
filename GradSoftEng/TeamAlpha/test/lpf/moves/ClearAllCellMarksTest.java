package lpf.moves;

import java.util.Collection;

import junit.framework.TestCase;
import lpf.model.core.Cell;
import lpf.model.core.Location;
import lpf.model.core.Value;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClearAllCellMarksTest extends TestCase {

	Cell testCell;

	//Set up Values
	Value one = new Value('1');
	Value two = new Value('2');
	Value three = new Value('3');
	Value four = new Value('4');

	ClearAllCellMarksMove testMove;
	
	@Before
	public void setUp() throws Exception{
		testCell = new Cell(new Location(1, 'A'));
		testCell.addMark(one);
		testCell.addMark(two);
		
		testMove = new ClearAllCellMarksMove(testCell);
	}

	@After
	public void tearDown() throws Exception{
		testCell = null;
		testMove = null;
	}
	
	@Test
	public void testClearAllCellMarksMove() {
		
		assertSame(testCell, testMove.cell);
		assertTrue(testMove.oldMarks.isEmpty());
	}

	@Test
	public void testDoMove() {
		Collection<Value> testMarks = testCell.getMarks();
		assertTrue(testMove.doMove());
		assertTrue(testMove.oldMarks.containsAll(testMarks));
	}

	@Test
	public void testIsValid() {
		assertTrue(testMove.isValid());
		
		testCell.setDigit(one);
		assertFalse(testMove.isValid());
		
		testCell = new Cell(new Location(1, 'A'));
		testMove = new ClearAllCellMarksMove(testCell);
		assertFalse(testMove.isValid());		
	}

	@Test
	public void testUndo() {
		Collection<Value> testMarks = testCell.getMarks();
		testMove.doMove();
		assertTrue(testMove.undo());
		assertTrue(testCell.getMarks().containsAll(testMove.oldMarks));
		assertTrue(testCell.getMarks().containsAll(testMarks));
	}

}
