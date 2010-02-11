package lpf.model.core;

import junit.framework.TestCase;
import lpf.model.core.Cell;
import lpf.model.core.Location;
import lpf.model.core.Value;

/**
 * Test cases for the Cell class in the common model
 * 
 * @author Andrew Yee
 *
 */
public class CellTest extends TestCase {

	/**
	 * Tests the Cell's constructor
	 */
	public void testCellConstructor() {
		Cell c = new Cell(new Location(1, 'A'));
		
		assertEquals(c.loc, new Location(1, 'A'));
		assertFalse(c.loc.equals(new Location(2, 'A')));
		assertNull(c.digit);
		assertTrue(c.marks.isEmpty());
	}
	
	/**
	 * Tests the Cell method setDigit
	 */
	public void testSetDigit() {
		Cell c = new Cell(new Location(1, 'A'));
		Value v = new Value('5');
		
		c.setDigit(v);
		
		assertTrue(c.isOccupied());
		assertTrue(c.getDigit().equals(new Value('5')));
		assertFalse(c.getDigit().equals(new Value('B')));
		
		
	}
	
	/**
	 * Tests the Cell method clearDigit
	 */
	public void testClearDigit() {
		Cell c = new Cell(new Location(1, 'A'));
		Value v = new Value('5');
		
		c.setDigit(v);
		
		assertNotNull(c.getDigit());
		
		c.clearDigit();
		
		assertNull(c.getDigit());
	}
	
	/**
	 * Tests Get Marks
	 */
	public void testGetMarks() {
		Value one = new Value('1');
		Value two = new Value('2');
		
		Cell testCell = new Cell(new Location(1, 'A'));
		testCell.addMark(one);
		testCell.addMark(two);
		
		assertTrue(testCell.getMarks().contains(one));
		assertTrue(testCell.getMarks().contains(two));
		
	}
	
	/**
	 * Tests the Cell method addMark
	 */
	public void testAddMark() {
		Cell c = new Cell(new Location(1, 'A'));
		Value v1 = new Value ('5');
		Value v2 = new Value ('F');
		
		assertTrue(c.marks.isEmpty());
		
		c.addMark(v1);
		
		assertFalse(c.marks.isEmpty());
		assertTrue(c.marks.contains(new Value('5')));
		assertFalse(c.marks.contains(new Value('F')));
		
		c.addMark(v2);
		
		assertTrue(c.marks.contains(new Value('F')));
		assertTrue(c.marks.contains(new Value('5')) && c.marks.contains(new Value('F')));
	}
	
	/**
	 * Tests the Cell methods clearMark and clearMarks 
	 */
	public void testClearMark() {
		Cell c = new Cell(new Location(1, 'A'));
		Value v1 = new Value ('5');
		Value v2 = new Value ('F');
		
		c.addMark(v1);
		c.addMark(v2);
		
		c.clearMark(v1);
		
		assertFalse(c.marks.isEmpty());
		assertFalse(c.marks.contains(new Value('5')));
		assertTrue(c.marks.contains(new Value('F')));
		
		c.clearMark(v2);
		
		assertFalse(c.marks.contains(new Value('5')));
		assertFalse(c.marks.contains(new Value('F')));
		assertTrue(c.marks.isEmpty());
		
		c.addMark(v1);
		c.addMark(v2);
		
		assertFalse(c.marks.isEmpty());
		
		c.clearMarks();
		
		assertTrue(c.marks.isEmpty());
	}
	
	/**
	 * Tests the Cell method equals
	 */
	public void testEqualCells() {
		Cell c1 = new Cell(new Location(1, 'A'));
		Cell c2 = new Cell(new Location(2, 'B'));
		Value v1 = new Value('5');
		Value v2 = new Value('F');
		
		// Test empty Cells are equal
		assertTrue(c1.equals(c2));
		
		c1.addMark(v1);
		c2.addMark(v2);
		
		// Test Cells with no digit but different marks
		assertTrue(c1.equals(c2));
		
		c1.setDigit(v1);
		
		// Test one Cell with a digit, one without
		assertFalse(c1.equals(c2));
		
		c2.setDigit(v2);
		
		// Test Cells with different digits
		assertFalse(c1.equals(c2));
		
		c2.setDigit(v1);
		
		// Test Cells with the same digit
		assertTrue(c1.equals(c2));
		
		//Test Not a cell
		assertFalse(c1.equals(new Integer(1)));
	}
	
	/**
	 * Tests the cell method isOccupied
	 */
	public void testIsOccupied() {
		Cell c = new Cell(new Location(1, 'A'));
		Value v = new Value('5');
		
		c.addMark(v);
		
		// Adding marks shouldn't count as the Cell being occupied
		assertFalse(c.isOccupied());
		
		c.setDigit(v);
		
		assertTrue(c.isOccupied());
	}
	
	
	public void testToStrings(){
		//Test Location
		Location l = new Location(1,'a');
		assertEquals("[1,a]", l.toString());
		
		Cell c = new Cell(l);
		//Test Cell
		assertEquals("[1,a]", c.toString());
	}
	
}
