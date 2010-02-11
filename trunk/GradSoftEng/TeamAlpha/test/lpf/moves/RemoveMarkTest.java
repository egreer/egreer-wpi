package lpf.moves;

import junit.framework.TestCase;
import lpf.model.core.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Eric Greer
 *
 */
public class RemoveMarkTest extends TestCase{

	Cell testCell1;
	Location a1 = new Location(1, 'A');
	Value val1 = new Value('1');
	Value val2 = new Value('2');
	Value val3 = new Value('3');
	
	
	@Before
	public void setUp() throws Exception {
		testCell1 = new Cell(a1);
		testCell1.addMark(val1);
		testCell1.addMark(val2);
	}

	@After
	public void tearDown() throws Exception {
		testCell1 = null;
	}
	
	@Test
	public void testRemoveMarkMove() {
		RemoveMarkMove testMove= new RemoveMarkMove(testCell1, val1);
		assertSame(testMove.cell, testCell1);
		assertSame(testMove.mark, val1);
	}

	@Test
	public void testDoMove() {
		RemoveMarkMove testMove= new RemoveMarkMove(testCell1, val1);
		assertTrue(testCell1.getMarks().contains(val1));
		
		testMove.doMove();
		assertFalse(testCell1.getMarks().contains(val1));
		
	}

	@Test
	public void testIsValid() {
		RemoveMarkMove testMove= new RemoveMarkMove(testCell1, val1);
		assertTrue(testMove.isValid());
		
		testMove= new RemoveMarkMove(testCell1, val3);
		assertFalse(testMove.isValid());
	}

	@Test
	public void testUndo() {
		RemoveMarkMove testMove= new RemoveMarkMove(testCell1, val1);
		testMove.doMove();
		assertFalse(testCell1.getMarks().contains(val1));
		
		assertTrue(testMove.undo());
		assertTrue(testCell1.getMarks().contains(val1));
	}

}
