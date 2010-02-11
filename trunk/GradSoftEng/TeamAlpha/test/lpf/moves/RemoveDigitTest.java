package lpf.moves;

import junit.framework.TestCase;
import lpf.model.core.Cell;
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
public class RemoveDigitTest extends TestCase {

	Cell testCell1;
	Location a1 = new Location(1, 'A');
	Value val1 = new Value('1');
	Value val2 = new Value('2');
	RemoveDigitMove testMove;
	
	@Before
	public void setUp() throws Exception {
		testCell1 = new Cell(a1);
		testCell1.setDigit(val1);
		testMove = new RemoveDigitMove(testCell1);
	}

	@After
	public void tearDown() throws Exception {
		testCell1 = null;
		testMove = null;
	}
	
	
	@Test
	public void testRemoveDigitMove() {
		assertEquals(testMove.cell, testCell1);
		assertNull(testMove.oldDigit);
	}

	@Test
	public void testDoMove() {
		Value v = testCell1.getDigit();
		assertTrue(testMove.doMove());
		assertEquals(v, testMove.oldDigit);
		assertNull(testMove.cell.getDigit());
		assertNull(testCell1.getDigit());
		
	}

	@Test
	public void testIsValid() {
		assertTrue(testMove.isValid());
		
		testCell1.clearDigit();
		testMove = new RemoveDigitMove(testCell1);
		assertFalse(testMove.isValid());
	}

	@Test
	public void testUndo() {
		testMove.doMove();
		assertTrue(testMove.undo());
		assertEquals(testMove.cell.getDigit(), val1);		
	}

}
