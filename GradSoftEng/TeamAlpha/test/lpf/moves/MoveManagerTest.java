package lpf.moves;


import java.util.Stack;

import junit.framework.TestCase;
import lpf.model.core.Cell;
import lpf.model.core.Location;
import lpf.model.core.Value;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** Tested using RemoveDigitMove
 * 
 * 
 * @author Eric Greer
 * 
 * @update (eg) Changed tests to reflect the change from singleton 
 */
public class MoveManagerTest extends TestCase {

	
	Cell testCell1;
	Location a1 = new Location(1, 'A');
	Value val1 = new Value('1');
	Value val2 = new Value('2');
	RemoveDigitMove testMove;
	MoveManager moveMgr = MoveManager.getInstance();
	
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
		moveMgr.clearStack();
	}
	
	@Test
	public void testDoMove() {
		//Test Illegal
		testCell1.clearDigit(); 
		try{
			moveMgr.processMove(testMove);
			fail("doMove Should have thrown IllegalArgumentException");
		}catch (IllegalArgumentException e){
			assertFalse(moveMgr.moves.contains(testMove));
		}
		
		moveMgr.clearStack();
		
		//Test null move
		try{
			moveMgr.processMove(null);
			fail("doMove Should have thrown IllegalArgumentException");
		}catch (IllegalArgumentException e){
			assertFalse(moveMgr.moves.contains(testMove));
		}
		
		moveMgr.clearStack();
		
		
		//Test Legal
		testCell1.setDigit(val1);
		moveMgr.processMove(testMove);
		assertTrue(moveMgr.moves.contains(testMove));
		
		
	}

	@Test
	public void testUndoMove() {
		//Try Undoing nothing
		try{
			moveMgr.undoMove();
			fail("UndoMove should have thrown UnsupportedOperationException");
		}catch (UnsupportedOperationException e){
			assertTrue(moveMgr.futureMove.isEmpty());
			assertEquals("Moves stack contains no moves", e.getMessage());
		}
	
		
		moveMgr.processMove(testMove);
				
		assertTrue(moveMgr.moves.contains(testMove));
		assertEquals(0, moveMgr.futureMove.size());
		assertNull(testCell1.getDigit());
		
		moveMgr.undoMove();
		assertTrue(moveMgr.futureMove.contains(testMove));
		assertEquals(testCell1.getDigit(), val1);
		
	}

	@Test
	public void testRedoMove() {
		//Test Redoing Nothing
		try{
			moveMgr.redoMove();
			fail("UndoMove should have thrown UnsupportedOperationException");
		}catch (UnsupportedOperationException e){
			assertEquals("FutureMove stack contains no moves", e.getMessage());
		}
		
		moveMgr.processMove(testMove);
		moveMgr.undoMove();
		
		//Redo a valid move
		moveMgr.redoMove();
		assertTrue(moveMgr.moves.contains(testMove));
		assertFalse(moveMgr.futureMove.contains(testMove));
		assertNull(testCell1.getDigit());
		
	}

	@Test
	public void testClearStack() {
		Stack<IGridGameMove> moves = moveMgr.moves;
		Stack<IGridGameMove> future = moveMgr.futureMove;
		moveMgr.clearStack();
		assertNotSame(moves, moveMgr.moves);
		assertNotSame(future, moveMgr.futureMove);
	}

	@Test
	public void testGetInstance() {
		MoveManager test = MoveManager.getInstance();
		assertSame(test, moveMgr);
	}

}
