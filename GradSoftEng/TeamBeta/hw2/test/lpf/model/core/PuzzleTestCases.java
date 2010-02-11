package lpf.model.core;

import java.util.Collection;

import junit.framework.TestCase;

public class PuzzleTestCases extends TestCase {
	public Grid g1 = new Grid(3,3);
	public Grid g2 = new Grid(3,3);
	// Empty
	public Grid g3 = new Grid(3,3);
	// playerGrid compared to g1
	public Grid g4 = new Grid(3,3);
	
	Puzzle initial = new Puzzle(g3);
	Puzzle solution = new Puzzle(g1);
	Puzzle player = new Puzzle(g3);
	
	/**
	 * Setup some sample values to work with.
	 */
	public void setUp() {
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
		
		g1.cells[0][0].addMark(new Value('1'));
		g1.cells[0][0].addMark(new Value('2'));
		g1.cells[0][0].addMark(new Value('3'));
		g1.cells[0][0].addMark(new Value('4'));
		g1.cells[0][0].addMark(new Value('5'));
		g1.cells[0][0].addMark(new Value('6'));
		g1.cells[0][0].addMark(new Value('7'));
		g1.cells[0][0].addMark(new Value('8'));
		g1.cells[0][0].addMark(new Value('9'));
		
		g1.cells[1][0].addMark(new Value('1'));
		g1.cells[1][0].addMark(new Value('2'));
		g1.cells[2][0].addMark(new Value('3'));
		g1.cells[2][0].addMark(new Value('4'));
		g1.cells[1][1].addMark(new Value('5'));
		g1.cells[1][2].addMark(new Value('6'));
		g1.cells[2][0].addMark(new Value('7'));
		g1.cells[2][2].addMark(new Value('8'));
		g1.cells[2][2].addMark(new Value('9'));
		
		g2.cells[0][0] = new Cell(new Location(1,'A'));
		g2.cells[0][1] = new Cell(new Location(1,'B'));
		g2.cells[0][2] = new Cell(new Location(1,'C'));
		g2.cells[1][0] = new Cell(new Location(2,'A'));
		g2.cells[1][1] = new Cell(new Location(2,'B'));
		g2.cells[1][2] = new Cell(new Location(2,'C'));
		g2.cells[2][0] = new Cell(new Location(3,'A'));
		g2.cells[2][1] = new Cell(new Location(3,'B'));
		g2.cells[2][2] = new Cell(new Location(3,'C'));
		
		g2.cells[0][0].setDigit(new Value('2'));
		g2.cells[0][1].setDigit(new Value('3'));
		g2.cells[0][2].setDigit(new Value('1'));
		g2.cells[1][0].setDigit(new Value('3'));
		g2.cells[1][1].setDigit(new Value('1'));
		g2.cells[1][2].setDigit(new Value('2'));
		g2.cells[2][0].setDigit(new Value('1'));
		g2.cells[2][1].setDigit(new Value('2'));
		g2.cells[2][2].setDigit(new Value('3'));
		
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
		g4.cells[0][1].setDigit(new Value('2'));
		g4.cells[0][2].setDigit(new Value('3'));
		g4.cells[1][0].setDigit(new Value('2'));
		g4.cells[1][1].setDigit(new Value('3'));
		g4.cells[1][2].setDigit(new Value('1'));
		g4.cells[2][0].setDigit(new Value('3'));
		g4.cells[2][1].setDigit(new Value('1'));
		g4.cells[2][2].setDigit(new Value('2'));
		
	}
	
	/*
	 * Test for 'Puzzle(Grid)'
	 */
	public void testPuzzle() {
		Puzzle puz = new Puzzle(g1);
		
		assertNotNull(puz.solutionGrid);
		assertNull(puz.initialGrid);
		assertNull(puz.playerGrid);
	}
	
	/*
	 * Test for 'hasWon()'
	 */
	public void testHasWon() {
		Puzzle puz = new Puzzle(g1);
		
		// the player grid should have not won
		assertFalse(puz.hasWon());
		
		// clone solutionGrid to playerGrid 
		puz.playerGrid = (Grid)puz.deepClone(g1);
		assertTrue(puz.hasWon());
	}
	
	/* 
	 * 
	 */
	public void testGetSize() {
		Puzzle puz = new Puzzle(g1);
		
		assertEquals(puz.getSize(), 3);
	}
	
	/*
	 * Test for 'getIncorrect()'
	 */
	public void testGetIncorrect() {
		Puzzle puz = new Puzzle(g1);
		
		// clone g4 with playerGrid
		puz.playerGrid = (Grid)puz.deepClone(g4);
		Collection<Cell> set = puz.getIncorrect();
		
		for(Cell cell:set) {
			System.out.println("Incorrect:" + cell.loc.row + 
					"/" + cell.loc.column);
		}
	}
	
	/*
	 * Test for 'reset()'
	 */
	public void testReset() {
		Puzzle puz = new Puzzle(g1);
		
		puz.initialGrid = (Grid)puz.deepClone(g3);
		assertNull(((Cell)puz.initialGrid.cells[0][0]).getDigit());
		puz.playerGrid = (Grid)puz.deepClone(g4);
		assertEquals(((Cell)puz.playerGrid.cells[0][0]).getDigit().value, '2');
		
		puz.reset();
		assertNull(((Cell)puz.playerGrid.cells[0][0]).getDigit());
		
	}
	
	/*
	 * Test for 'getPlayerGrid()'
	 */
	public void testGetPlayerGrid() {
		Puzzle puz = new Puzzle(g1);
		
		puz.playerGrid = (Grid)puz.deepClone(g2);
		CellsIterator it = (CellsIterator)puz.getPlayerGrid().iterator();
		
		while(it.hasNext()) {
			Cell cell = (Cell)it.next();
			char c = cell.getDigit().value;
			System.out.println("GetPlayerGrid:" + cell.loc.row + "/" + cell.loc.column + "('" + c + "')");
		}
	}
	
	/*
	 * Test for 'setInitialGrid(Grid)'
	 */
	public void testSetInitialGridGrid() {
		Puzzle puz = new Puzzle(g1);
		
		assertNull(puz.initialGrid);
		puz.setInitialGrid(g2);
		
		CellsIterator it = (CellsIterator)puz.getInitialGrid().iterator();
		
		while(it.hasNext()) {
			Cell cell = (Cell)it.next();
			char c = cell.getDigit().value;
			System.out.println("SetInitialGrid:" + cell.loc.row + "/" + cell.loc.column + "('" + c + "')");
		}
	}
	
	/*
	 * Test for 'getSolutionGrid()'
	 */
	public void testGetSolutionGrid() {
		Puzzle puz = new Puzzle(g1);
		
		assertNotNull(puz.solutionGrid);
		
		CellsIterator it = (CellsIterator)puz.getSolutionGrid().iterator();
		
		while(it.hasNext()) {
			Cell cell = (Cell)it.next();
			char c = cell.getDigit().value;
			System.out.println("GetSolutionGrid:" + cell.loc.row + "/" + cell.loc.column + "('" + c + "')");
		}
	}
	
	/*
	 * Test for 'getInitialGrid()'
	 */
	public void testGetInitialGrid() {
		Puzzle puz = new Puzzle(g1);
		
		assertNull(puz.initialGrid);
		puz.setInitialGrid(g2);
		
		CellsIterator it = (CellsIterator)puz.getInitialGrid().iterator();
		
		while(it.hasNext()) {
			Cell cell = (Cell)it.next();
			char c = cell.getDigit().value;
			System.out.println("GetInitialGrid:" + cell.loc.row + "/" + cell.loc.column + "('" + c + "')");
		}
	}
	
	/*
	 * Test for 'getDifficulty()'
	 */
	public void testGetDifficulty() {
		Puzzle puz = new Puzzle(g1);
		
		assertEquals(puz.getDifficulty(), 0);
	}
	
	/*
	 * Test for 'setDifficulty(int)'
	 */
	public void testSetDifficulty() {
		Puzzle puz = new Puzzle(g1);
		
		puz.setDifficulty(3);
		assertEquals(puz.getDifficulty(), 3);
	}
	
	/*
	 * Test for 'DeepClone()'
	 */
	public void testDeepClone() {
		Puzzle curr = new Puzzle(g3);
		Grid newone = (Grid)curr.deepClone(g1);
		
		((Cell)newone.cells[0][0]).clearDigit();
		assertNull(((Cell)newone.cells[0][0]).getDigit());
		assertEquals(((Cell)newone.cells[0][0]).getDigit(), null);
		assertNotNull(((Cell)g1.cells[0][0]).getDigit());
		assertEquals(((Cell)g1.cells[0][0]).getDigit(), new Value('1'));
	}
}
