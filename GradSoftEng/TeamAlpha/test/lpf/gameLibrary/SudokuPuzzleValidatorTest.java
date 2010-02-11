package lpf.gameLibrary;

import static org.junit.Assert.*;

import java.io.File;

import lpf.gameLibrary.SudokuPuzzleValidator;
import lpf.gameLibrary.SudokuXMLParser;
import lpf.model.core.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class SudokuPuzzleValidatorTest {
	File testXML = new File("files/puzzles/SamplePuzzle.xml");
	Puzzle p; 
	Grid sol;
	Grid init;
	
	@Before
	public void setUp() throws Exception {
		p = SudokuXMLParser.createSodukuPuzzle(testXML);;
		sol = p.getSolutionGrid();
		init = p.getInitialGrid();
	}

	@After
	public void tearDown() throws Exception {
		p = null;
		sol = init = null;
	}

	@Test
	public void testValidatePuzzle() {
		assertTrue(SudokuPuzzleValidator.ValidatePuzzle(p));
		
		
		p = new Puzzle(new Grid(1,2));
		p.setInitialGrid(new Grid(1,2));
		p.getSolutionGrid().getCellAtLocation(new Location(1, 'A')).setDigit(new Value('1'));
		p.getSolutionGrid().getCellAtLocation(new Location(1, 'B')).setDigit(new Value('1'));
		assertFalse(SudokuPuzzleValidator.ValidatePuzzle(p));
	}

	@Test
	public void testValidateAllRows() {
		assertTrue(SudokuPuzzleValidator.ValidateAllRows(sol));
		
		sol.getCellAtLocation(new Location(9, 'B')).setDigit(new Value('8'));
		assertFalse(SudokuPuzzleValidator.ValidateAllRows(sol));
		
		assertTrue(SudokuPuzzleValidator.ValidateAllRows(init));
	}

	@Test
	public void testValidateRow() {
		assertTrue(SudokuPuzzleValidator.ValidateRow(sol, 9));
		
		sol.getCellAtLocation(new Location(9, 'B')).setDigit(new Value('8'));
		assertFalse(SudokuPuzzleValidator.ValidateRow(sol, 9));
		
		init.getCellAtLocation(new Location(9, 'A')).setDigit(new Value('8'));
		assertFalse(SudokuPuzzleValidator.ValidateRow(init, 9));
	}

	@Test
	public void testValidateAllColumns() {
		assertTrue(SudokuPuzzleValidator.ValidateAllColumns(sol));
		
		sol.getCellAtLocation(new Location(9, 'B')).setDigit(new Value('8'));
		assertFalse(SudokuPuzzleValidator.ValidateAllColumns(sol));
		
		assertTrue(SudokuPuzzleValidator.ValidateAllColumns(init));
	}

	@Test
	public void testValidateCol() {
		assertTrue(SudokuPuzzleValidator.ValidateCol(sol, 'b'));
		
		sol.getCellAtLocation(new Location(9, 'B')).setDigit(new Value('8'));
		assertFalse(SudokuPuzzleValidator.ValidateCol(sol, 'B'));
		
		init.getCellAtLocation(new Location(9, 'A')).setDigit(new Value('9'));
		assertFalse(SudokuPuzzleValidator.ValidateCol(init, 'A'));
	}

	@Test
	public void testValidateAllRegions() {
		assertTrue(SudokuPuzzleValidator.ValidateAllRegions(sol));
		sol.getCellAtLocation(new Location(1, 'A')).setDigit(new Value('4'));
		assertFalse(SudokuPuzzleValidator.ValidateAllRegions(sol));
		
		Grid testGrid = new Grid(6, 6);
		assertFalse(SudokuPuzzleValidator.ValidateAllRegions(testGrid));
		
		testGrid = new Grid(6, 4);
		assertFalse(SudokuPuzzleValidator.ValidateAllRegions(testGrid));
	}
	
	@Test
	public void testValidateRegion() {
		assertTrue(SudokuPuzzleValidator.ValidateRegion(sol, 1, 3, 'A', 'C'));
		assertFalse(SudokuPuzzleValidator.ValidateRegion(sol, 1, 4, 'A', 'C'));
		assertFalse(SudokuPuzzleValidator.ValidateRegion(sol, 1, 3, 'A', 'D'));
		assertFalse(SudokuPuzzleValidator.ValidateRegion(sol, 0, 3, 'A', 'C'));
		assertFalse(SudokuPuzzleValidator.ValidateRegion(sol, 1, 10, 'A', 'C'));
		assertFalse(SudokuPuzzleValidator.ValidateRegion(sol, 1, 3, '+', 'C'));
		assertFalse(SudokuPuzzleValidator.ValidateRegion(sol, 1, 3, 'A', 'I'));
	
	}

	
	@Test
	public void testValidateInitialGrid() {
		assertTrue(SudokuPuzzleValidator.ValidateInitialGrid(p));
		init.getCellAtLocation(new Location(9, 'A')).setDigit(new Value('9'));
		assertFalse(SudokuPuzzleValidator.ValidateInitialGrid(p));
	}

}
