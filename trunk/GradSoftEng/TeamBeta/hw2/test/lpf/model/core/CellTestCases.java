package lpf.model.core;

import junit.framework.TestCase;

public class CellTestCases extends TestCase {
	
	Cell cellDefault;
	Cell cellDefaultNull;
	
	/**
	 * Setup some sample values to work with.
	 */
	protected void setUp() {
		cellDefault = new Cell(new Location(1,'B'));
		cellDefault.setDigit(new Value('8'));
		
		cellDefaultNull = new Cell(new Location(2,'C'));
	}
	
	/*
	 * Test for 'Cell(loc)'
	 */
	public void testCell() {
		Cell cell = new Cell(new Location(1,'B'));
		
		assertEquals(cell.loc, new Location(1,'B'));
		assertTrue(cell.getMarks().isEmpty());
		assertNull(cell.getDigit());
	}
	
	/*
	 * Test for 'addMark(Value)'
	 */
	public void testAddMark() {
		Cell cell1 = new Cell(new Location(4,'B'));
		
		cell1.addMark(new Value('1'));
		cell1.addMark(new Value('2'));
		cell1.addMark(new Value('3'));
		cell1.addMark(new Value('3'));
		
		assertTrue(cell1.getMarks().contains(new Value('1')));
		assertTrue(cell1.getMarks().contains(new Value('2')));
		assertTrue(cell1.getMarks().contains(new Value('3')));
		assertFalse(cell1.getMarks().contains(new Value('4')));
		assertEquals(cell1.getMarks().size(), 3);
	}
	
	/*
	 * Test for 'removeMark(Value)'
	 */
	public void testRemoveMark() {
		Cell cell1 = new Cell(new Location(4,'B'));
		
		cell1.addMark(new Value('1'));
		cell1.addMark(new Value('2'));
		cell1.addMark(new Value('3'));
		
		cell1.removeMark(new Value('1'));
		cell1.removeMark(new Value('3'));
		
		assertFalse(cell1.getMarks().contains(new Value('1')));
		assertFalse(cell1.getMarks().contains(new Value('3')));
		assertTrue(cell1.getMarks().contains(new Value('2')));
		assertEquals(cell1.getMarks().size(), 1);
		assertFalse(cell1.getMarks().isEmpty());
	}
	
	/*
	 * Test for 'clearMarks()'
	 */
	public void testClearMarks() {
		Cell cell1 = new Cell(new Location(4,'B'));
		
		cell1.addMark(new Value('1'));
		cell1.addMark(new Value('2'));
		cell1.addMark(new Value('3'));
		
		cell1.clearMarks();
		assertEquals(cell1.getMarks().size(), 0);
		assertTrue(cell1.getMarks().isEmpty());
	}
	
	/*
	 * Test for 'testGetMarks()'
	 */
	public void testGetMarks() {
		Cell cell1 = new Cell(new Location(4,'B'));
		
		cell1.addMark(new Value('1'));
		cell1.addMark(new Value('2'));
		cell1.addMark(new Value('3'));
		
		assertFalse(cell1.getMarks().isEmpty());
		assertTrue(cell1.getMarks().contains(new Value('1')));
		assertTrue(cell1.getMarks().contains(new Value('2')));
		assertTrue(cell1.getMarks().contains(new Value('3')));
		assertEquals(cell1.getMarks().size(), 3);
	}
	
	/*
	 * Test for 'testGetDigit()'
	 */
	public void testGetDigit() {
		assertEquals(cellDefault.getDigit(), new Value('8'));
		assertNull(cellDefaultNull.getDigit());
	}
	
	/*
	 * Test for 'testSetDigit(Value)'
	 */
	public void testSetDigit() {
		Cell cell1 = new Cell(new Location(4,'B'));
		cell1.setDigit(new Value('9'));
		
		assertEquals(cell1.getDigit(), new Value('9'));
	}
	
	/*
	 * Test for 'testEquals(Object)'
	 */
	public void testEquals() {
		Cell cell1 = new Cell(new Location(4,'B'));
		Cell cell2 = new Cell(new Location(4,'B'));
		Cell cell3 = new Cell(new Location(4,'C'));
		
		Cell cellEmpty1 = new Cell(new Location(4, 'B'));
		Cell cellEmpty2 = new Cell(new Location(4, 'B'));
		Cell cellEmpty3 = new Cell(new Location(4, 'D'));
		Object obj = new Object();
		
		// test cells with same location and digits
		cell1.setDigit(new Value('5'));
		cell2.setDigit(new Value('5'));
		assertTrue(cell1.equals(cell2));
		
		// test cells with same location but different digits
		cell2.setDigit(new Value('6'));
		assertFalse(cell1.equals(cell2));
		
		// test cell with same digits but different location
		cell3.setDigit(new Value('5'));
		assertFalse(cell1.equals(cell3));
		
		// test cell with other unrecognized object
		assertFalse(cell1.equals(obj));
		
		// test cells both with no digits in
		assertTrue(cellEmpty1.equals(cellEmpty2));
		
		// test cells, one has digit, other has no digit
		assertFalse(cellEmpty1.equals(cell1));
		assertFalse(cell1.equals(cellEmpty1));
	}
	
	/*
	 * Test for 'testEquals(Object)'
	 */
	public void testIsOccupied() {
		assertTrue(cellDefault.isOccupied());
		assertFalse(cellDefaultNull.isOccupied());
	}
	
	/*
	 * Test for 'testClearDigit(Object)'
	 */
	public void testClearDigit() {
		Cell cell1 = new Cell(new Location(4,'B'));
		cell1.setDigit(new Value('1'));
		
		assertEquals(cell1.getDigit(), new Value('1'));
		cell1.clearDigit();
		assertNull(cell1.getDigit());
	}
}
