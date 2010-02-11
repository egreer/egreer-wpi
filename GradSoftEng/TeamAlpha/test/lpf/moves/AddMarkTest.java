package lpf.moves;

import junit.framework.TestCase;
import lpf.model.core.*;

/**
 * 
 * @author Andrew Yee, Eric Greer
 *
 */
public class AddMarkTest extends TestCase {

	/**
	 * Tests the AddMarkMove constructor
	 */
	public void testAddMarkMove() {
		Cell cell = new Cell(new Location(1, 'A'));
		Value mark = new Value('5');
		AddMarkMove move = new AddMarkMove(cell, mark);

		assertEquals(move.cell, new Cell(new Location(1, 'A')));
		assertEquals(move.mark, new Value('5'));

		try{
			move = new AddMarkMove(cell, null);
			fail("Creating a new AddMarkMove with null mark should have thrown exception");
		}catch(IllegalArgumentException e){
			assertEquals("AddMarkMove cannot have null arguments", e.getMessage());
		}
		
		try{
			move = new AddMarkMove(null, mark);
			fail("Creating a new AddMarkMove with null cell should have thrown exception");
		}catch(IllegalArgumentException e){
			assertEquals("AddMarkMove cannot have null arguments", e.getMessage());
		}
	}

	/**
	 * Tests the AddMarkMove method doMove
	 */
	public void testDoMove() {
		Cell cell = new Cell(new Location(1, 'A'));
		Value mark1 = new Value('5');
		Value mark2 = new Value('9');
		AddMarkMove move1 = new AddMarkMove(cell, mark1);

		assertTrue(cell.getMarks().isEmpty());

		move1.doMove();

		assertFalse(cell.getMarks().isEmpty());
		assertEquals(cell.getMarks().size(), 1);
		assertTrue(cell.getMarks().contains(new Value('5')));

		AddMarkMove move2 = new AddMarkMove(cell, mark2);
		move2.doMove();

		assertFalse(cell.getMarks().isEmpty());
		assertEquals(cell.getMarks().size(), 2);
		assertTrue(cell.getMarks().contains(new Value('5')));
		assertTrue(cell.getMarks().contains(new Value('9')));
		
		
		AddMarkMove move3 = new AddMarkMove(cell, new Value('5'));
		//Test Invalid Move
		try{
			move3.doMove();
			fail("AddMarkMove should have thrown exception");
		}
		catch (UnsupportedOperationException e){
			assertEquals("Move is not valid", e.getMessage());
		}
	}

	/**
	 * Tests the AddMarkMove method isValid
	 */
	public void testIsValid() {
		Cell cell = new Cell(new Location(1, 'A'));
		Cell cell2 = new Cell(new Location(2, 'B'));
		Value mark = new Value('5');
		Value mark2 = new Value('2');
		cell2.addMark(mark);

		// Mark not already contained in list of marks
		AddMarkMove move1 = new AddMarkMove(cell, mark);

		assertTrue(move1.isValid());

		// Mark already contained in list of marks
		AddMarkMove move2 = new AddMarkMove(cell2, mark);

		assertFalse(move2.isValid());

		//Marks already exist, and adding new mark
		AddMarkMove move3 = new AddMarkMove(cell2, mark2);

		assertTrue(move3.isValid());

	}

	/**
	 * Tests the AddMarkMove method undo
	 */
	public void testUndo() {
		Cell cell = new Cell(new Location (1, 'A'));
		Value mark = new Value('5');

		AddMarkMove move = new AddMarkMove(cell, mark);
		move.doMove();

		assertFalse(cell.getMarks().isEmpty());
		assertTrue(cell.getMarks().contains(new Value('5')));

		move.undo();

		assertTrue(cell.getMarks().isEmpty());
		assertFalse(cell.getMarks().contains(new Value('5')));
	}

}
