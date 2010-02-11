package lpf.model.core;

import java.util.Iterator;

import junit.framework.TestCase;

public class GridTestCases extends TestCase {
	Grid g1;
	Grid g2;
	Grid g3;
	Grid g4;
	
	/**
	 * Setup some sample values to work with.
	 */
	protected void setUp() {
		g1 = new Grid(3,3);
		g1.cells[0][0] = new Cell(new Location(1,'A'));
		g1.cells[0][1] = new Cell(new Location(1,'B'));
		g1.cells[0][2] = new Cell(new Location(1,'C'));
		g1.cells[1][0] = new Cell(new Location(2,'A'));
		g1.cells[1][1] = new Cell(new Location(2,'B'));
		g1.cells[1][2] = new Cell(new Location(2,'C'));
		g1.cells[2][0] = new Cell(new Location(3,'A'));
		g1.cells[2][1] = new Cell(new Location(3,'B'));
		g1.cells[2][2] = new Cell(new Location(3,'C'));
		
		g1.cells[0][0].setDigit(new Value('1'));
		g1.cells[0][1].setDigit(new Value('2'));
		g1.cells[0][2].setDigit(new Value('3'));
		g1.cells[1][0].setDigit(new Value('2'));
		g1.cells[1][1].setDigit(new Value('3'));
		g1.cells[1][2].setDigit(new Value('1'));
		g1.cells[2][0].setDigit(new Value('3'));
		g1.cells[2][1].setDigit(new Value('1'));
		g1.cells[2][2].setDigit(new Value('2'));
		
		g3 = new Grid(1,1);
		
		g2 = new Grid(3,3);
		g2.cells[0][0] = new Cell(new Location(1,'A'));
		g2.cells[0][1] = new Cell(new Location(1,'B'));
		g2.cells[0][2] = new Cell(new Location(1,'C'));
		g2.cells[1][0] = new Cell(new Location(2,'A'));
		g2.cells[1][1] = new Cell(new Location(2,'B'));
		g2.cells[1][2] = new Cell(new Location(2,'C'));
		g2.cells[2][0] = new Cell(new Location(3,'A'));
		g2.cells[2][1] = new Cell(new Location(3,'B'));
		g2.cells[2][2] = new Cell(new Location(3,'C'));
		
		g2.cells[0][0].setDigit(new Value('1'));
		g2.cells[0][1].setDigit(new Value('2'));
		g2.cells[0][2].setDigit(new Value('3'));
		g2.cells[1][0].setDigit(new Value('2'));
		g2.cells[1][1].setDigit(new Value('3'));
		g2.cells[1][2].setDigit(new Value('1'));
		g2.cells[2][0].setDigit(new Value('3'));
		g2.cells[2][1].setDigit(new Value('1'));
		g2.cells[2][2].setDigit(new Value('2'));
		
		g4 = new Grid(3,3);
		g4.cells[0][0] = new Cell(new Location(1,'A'));
		g4.cells[0][1] = new Cell(new Location(1,'B'));
		g4.cells[0][2] = new Cell(new Location(1,'C'));
		g4.cells[1][0] = new Cell(new Location(2,'A'));
		g4.cells[1][1] = new Cell(new Location(2,'B'));
		g4.cells[1][2] = new Cell(new Location(2,'C'));
		g4.cells[2][0] = new Cell(new Location(3,'A'));
		g4.cells[2][1] = new Cell(new Location(3,'B'));
		g4.cells[2][2] = new Cell(new Location(3,'C'));
		
		g4.cells[0][0].setDigit(new Value('2'));
		g4.cells[0][1].setDigit(new Value('3'));
		g4.cells[0][2].setDigit(new Value('1'));
		g4.cells[1][0].setDigit(new Value('3'));
		g4.cells[1][1].setDigit(new Value('1'));
		g4.cells[1][2].setDigit(new Value('2'));
		g4.cells[2][0].setDigit(new Value('1'));
		g4.cells[2][1].setDigit(new Value('2'));
		g4.cells[2][2].setDigit(new Value('3'));
		
	}
	
	/*
	 * Test for 'Grid(int, int)'
	 */
	public void testGrid() {
		Grid g2 = new Grid(3,3);
		
		assertEquals(g2.width, 3);
		assertEquals(g2.height, 3);
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				assertEquals(g2.cells[i][j], new Cell(new Location(i + 1, (char)('A' + j))));
			}
		}
	}
	
	/*
	 * Test for 'getCellAtLocation(Location)'
	 */
	public void testGetCellAtLocation() {
		Cell c = g1.getCellAtLocation(new Location(1,'C'));
		
		assertEquals(c.loc, new Location(1, 'C'));
		assertEquals(c.getDigit(), new Value('3'));
	}
	
	/*
	 * Test for 'getCellAtLocation(Location)'
	 */
	public void testIterator() {
		Iterator<Cell> it;
		
		for (it = g1.iterator(); it.hasNext();) {
			System.out.println(((Cell)it.next()).getDigit().value + "");
		}
		
		assertEquals(it.next(), null);
	}
	
	/*
	 * Test for 'ctoi(char)'
	 */
	public void testCtoi() {
		//assertEquals(g1.ctoi('C'), 2);
	}
	
	/*
	 * Test for 'itoc(char)'
	 */
	public void testItoc() {
		//assertEquals(g1.itoc(2), 'C');
	}
	
	/*
	 * Test for 'equals(Object)'
	 */
	public void testEquals() {
		assertFalse(g1.equals(g3));
		assertTrue(g1.equals(g2));
		assertFalse(g1.equals(g4));
		
		Object obj = new Object();
		assertFalse(g1.equals(obj));
	}
}
