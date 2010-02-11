package lpf.GUIs.GridView;

import static org.junit.Assert.*;
import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.model.core.Cell;
import lpf.model.core.Grid;
import lpf.model.core.Location;
import lpf.model.core.Puzzle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPuzzleManager {

	SudokuAlphaGameConfiguration config;
	Puzzle testPuzzle;
	PuzzleManager testPManager;
	
	@Before
	public void setUp() throws Exception {
		
		config = SudokuAlphaGameConfiguration.getInstance();
		config.setPuzzle(config.getPuzzleLibrary().getRandomPuzzle(2));
		testPuzzle = config.getCurrentPuzzle();
		testPManager = new PuzzleManager(testPuzzle);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPuzzleManager() {
		
		testPManager.setMarkMode(true);
		assertTrue(testPManager.isMarkMode());
		testPManager.setMarkMode(false);
		assertTrue(!testPManager.isMarkMode());
		
		Grid g = testPManager.getGrid();
		assertEquals(g,testPuzzle.getPlayerGrid());
		
		Cell c = testPManager.getSelectedCell();
		assertEquals(c,null);
		
		testPManager.setSelectedLocation(new Location(1,'a'));
		Location l = new Location(1,'a');
		assertEquals(testPManager.getSelectedLocation(),l);
		assertEquals(testPManager.getSelectedLocation(),testPManager.getSelectedCell().loc);

	}

	@Test
	public void testSetMarkMode() {
		testPManager.setMarkMode(true);
		assertTrue(testPManager.isMarkMode);
		
		testPManager.setMarkMode(false);
		assertFalse(testPManager.isMarkMode);
	}
	
	@Test
	public void testIsMarkMode(){
		assertFalse(testPManager.isMarkMode());
		
		testPManager.setMarkMode(true);
		assertTrue(testPManager.isMarkMode());
	}
}
