package lpf.moves;

import junit.framework.TestCase;
import lpf.model.core.Cell;
import lpf.model.core.Location;
import lpf.model.core.Value;

/**
 * 
 * @author Andrew Yee, Eric Greer
 *
 */
public class AddDigitTest extends TestCase {

	/**
	 * Tests the AddDigitMove constructor
	 */
	public void testAddDigitMove() {
		Cell cell = new Cell(new Location(1, 'A'));
		Value digit = new Value('5');
		AddDigitMove move = new AddDigitMove(cell, digit);
		
		assertEquals(move.cell, new Cell(new Location(1, 'A')));
		assertEquals(move.digit, new Value('5'));
		
		//Test Invalid Parameters
		try{
			move = new AddDigitMove(cell, null);
			fail("Creating a new AddDigitMove with null digit should have thrown exception");
		}catch(IllegalArgumentException e){
			assertEquals("AddDigitMove cannot have null arguments", e.getMessage());
		}
		
		try{
			move = new AddDigitMove(null, digit);
			fail("Creating a new AddDigitMove with null cell should have thrown exception");
		}catch(IllegalArgumentException e){
			assertEquals("AddDigitMove cannot have null arguments", e.getMessage());
		}
	}

	/**
	 * Tests the AddDigitMove method doMove
	 */
	public void testDoMove() {
		Cell cell = new Cell(new Location(1, 'A'));
		Value digit1 = new Value('5');
		Value digit2 = new Value('9');
		AddDigitMove move1 = new AddDigitMove(cell, digit1);
		
		assertNull(cell.getDigit());
		
		move1.doMove();
		
		assertEquals(cell.getDigit(), new Value('5'));
		
		AddDigitMove move2 = new AddDigitMove(cell, digit2);
		
		assertEquals(cell.getDigit(), new Value('5'));
		
		move2.doMove();
		
		assertEquals(cell.getDigit(), new Value('9'));
		
		//Test doing an invalid move
		cell.clearDigit();
		cell.addMark(digit1);
		
		// why did u create this if u dont use it????
		//AddDigitMove move3 = new AddDigitMove(cell, digit2);
		
		try{
		move2.doMove();
		}  catch (UnsupportedOperationException e){
			assertEquals("The move is not valid", e.getMessage());
		}
	}

	/**
	 * Tests the AddDigitMove method isValid
	 */
	public void testIsValid() {
		Value digit = new Value('5');
		Value existing = new Value('9');
		Value mark1 = new Value('2');
		Value mark2 = new Value('5');
		
		// Test with no marks and no existing digit
		Cell cell1 = new Cell(new Location(1, 'A'));
		AddDigitMove move1 = new AddDigitMove(cell1, digit);
		
		assertTrue(move1.isValid());
		
		// Test with no marks and existing digit
		Cell cell2 = new Cell(new Location(2, 'B'));
		cell2.setDigit(existing);
		AddDigitMove move2 = new AddDigitMove(cell2, digit);
		
		assertTrue(move2.isValid());
		
		// Test with marks, digit included, and no existing digit
		Cell cell3 = new Cell(new Location(3, 'C'));
		cell3.addMark(mark1);
		cell3.addMark(mark2);
		AddDigitMove move3 = new AddDigitMove(cell3, digit);
		
		assertTrue(move3.isValid());
		
		// Test with marks, digit included, and existing digit
		Cell cell4 = new Cell(new Location(4, 'D'));
		cell4.setDigit(existing);
		cell4.addMark(mark1);
		cell4.addMark(mark2);
		AddDigitMove move4 = new AddDigitMove(cell4, digit);
		
		assertTrue(move4.isValid());
		
		// Test with marks, digit not included, and no existing digit
		Cell cell5 = new Cell(new Location(5, 'E'));
		cell5.addMark(mark1);
		AddDigitMove move5 = new AddDigitMove(cell5, digit);
		
		assertFalse(move5.isValid());
		
		// Test with marks, digit not included, and existing digit
		Cell cell6 = new Cell(new Location(6, 'F'));
		cell6.setDigit(existing);
		cell6.addMark(mark1);
		AddDigitMove move6 = new AddDigitMove(cell6, digit);
		
		assertFalse(move6.isValid());
		
	}

	/**
	 * Tests the AddDigitMove method undo
	 */
	public void testUndo() {
		Value digit1 = new Value('5');
		Value digit2 = new Value('7');
		
		// Test with no existing digit
		Cell cell1 = new Cell(new Location(1, 'A'));
		AddDigitMove move1 = new AddDigitMove(cell1, digit1);
		
		move1.doMove();
		
		assertEquals(cell1.getDigit(), new Value('5'));
		assertEquals(move1.cell.getDigit(), new Value('5'));
		assertEquals(move1.digit, new Value('5'));
		assertNull(move1.oldDigit);
		
		move1.undo();
		
		assertNull(cell1.getDigit());
		assertNull(move1.cell.getDigit());
		
		// Test with existing digit
		Cell cell2 = new Cell(new Location(2, 'B'));
		cell2.setDigit(digit1);
		AddDigitMove move2 = new AddDigitMove(cell2, digit2);
		
		assertEquals(cell2.getDigit(), new Value('5'));
		
		move2.doMove();
		
		assertEquals(cell2.getDigit(), new Value('7'));
		assertEquals(move2.cell.getDigit(), new Value('7'));
		assertEquals(move2.digit, new Value('7'));
		assertEquals(move2.oldDigit, new Value('5'));
		
		move2.undo();
		
		assertEquals(cell2.getDigit(), new Value('5'));
		assertEquals(move2.cell.getDigit(), new Value('5'));
		
	}

}
