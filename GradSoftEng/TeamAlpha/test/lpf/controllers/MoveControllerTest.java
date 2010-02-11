package lpf.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import lpf.GUIs.SudokuAlphaMainGUI;
import lpf.GUIs.GridView.GridView;
import lpf.GUIs.GridView.PuzzleManager;
import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.model.core.*;

/**
 * 
 * @author Andrew Yee, Eric Greer
 *
 */
public class MoveControllerTest extends TestCase {
	
	Value one = new Value('1');
	Value two = new Value('2');
	Value five = new Value('5');
	Value seven = new Value('7');
	
	@Before
	public void setUp() throws Exception{
				
		SudokuAlphaMainGUI gui = new SudokuAlphaMainGUI();
		SudokuAlphaGameConfiguration.getInstance().setGUI(gui);
	
		PuzzleManager pm = new PuzzleManager(null);
		gui.setGridView(new GridView(pm));
	}
	
	@After
	public void tearDown() throws Exception{
		SudokuAlphaGameConfiguration.getInstance().getGUI().dispose();
	}
	/**
	 * Tests the AddDigitController
	 */
	@Test
	public void testAddDigit() {
		
		Cell cell1 = new Cell(new Location (1, 'A'));
				
		// check controller constructor
		AddDigitController controller1 = new AddDigitController(cell1, five);
		assertEquals(controller1.cell, new Cell(new Location(1, 'A')));
		assertEquals(controller1.digit, five);
		
		// check a valid move
		try {
			controller1.process();
		} catch (ProcessFailedException e) {
			fail("AddDigitController Should not have thrown error");		
		}
		
		assertEquals(cell1.getDigit(), five);
		
		// check an invalid move
		Cell cell2 = new Cell(new Location (2, 'B'));
		Value mark = new Value('7');
		cell2.addMark(mark);
		AddDigitController controller2 = new AddDigitController(cell2, five);
		
		try {
			controller2.process();
			fail("AddDigitController Should have thrown exception");
		} catch (ProcessFailedException e) {
			assertNull(cell2.getDigit());
		}
			
	}
	
	/**
	 * Tests the AddMarkController
	 */
	@Test
	public void testAddMark() {
		Cell cell1 = new Cell(new Location (1, 'A'));
				
		// check controller constructor
		AddMarkController controller1 = new AddMarkController(cell1, five);
		assertEquals(controller1.cell, new Cell(new Location(1, 'A')));
		assertEquals(controller1.mark, five);
		
		// check a valid move
		try {
			controller1.process();
		} catch (ProcessFailedException e) {
			fail("AddMarkController should not have thrown exception");
		}
		assertTrue(cell1.getMarks().contains(new Value('5')));
		
		// check an invalid move
		Cell cell2 = new Cell(new Location (2, 'B'));
		cell2.addMark(five);
		AddMarkController controller2 = new AddMarkController(cell2, new Value('5'));
		
		try {
			controller2.process();
			fail("AddMarkController Should have thrown Exception");
		} catch (ProcessFailedException e) {
			assertEquals(cell2.getMarks().size(), 1);
		}
		
	}
	
	/**
	 * Tests the ClearAllCellMarksController
	 */
	@Test
	public void testClearAllCellMarks() {
		Cell cell = new Cell(new Location(1,'A'));
				
		cell.addMark(five);
		cell.addMark(seven);
		
		//Test Valid Move
		ClearAllCellMarksController controller = new ClearAllCellMarksController(cell);
		
		assertEquals(cell.getMarks().size(), 2);
		
		try {
			controller.process();
		} catch (ProcessFailedException e) {
			fail("ClearAllCellMarks should not have thrown an exception");
		}
		
		assertTrue(cell.getMarks().isEmpty());
		
		try{
			controller.process();
			fail("ClearAllCellMarks should have thrown an exception");
		} catch (ProcessFailedException e){
			assertEquals("Could not process ClearAllCellMarks Move", e.getMessage());
		}
	}
	
	/**
	 * Tests the ClearAllGridMarksController
	 */
	@Test
	public void testClearAllGridMarks() {
		Grid testGrid = new Grid(2, 2);
		
		Cell testCellA1 = testGrid.getCellAtLocation(new Location(1,'A'));
		Cell testCellB1 = testGrid.getCellAtLocation(new Location(1,'B'));
		Cell testCellA2 = testGrid.getCellAtLocation(new Location(2,'A'));
		
		ClearAllGridMarksController testControl= new ClearAllGridMarksController(testGrid);
		
		try {
			testControl.process();
			fail("ClearAllGridMarksController should have thrown exception");
		} catch (ProcessFailedException e1) {
			assertTrue(true);
			//TODO Better test here?
		}
		
		testCellA1.addMark(five);
		testCellA1.addMark(seven);
		
		testCellB1.addMark(one);
		testCellB1.addMark(two);
		
		testCellA2.addMark(five);
		testCellA2.addMark(seven);
		testCellA2.setDigit(one);
		
		//Test Valid Move
		testControl= new ClearAllGridMarksController(testGrid);
		
		try{
			testControl.process();
		} catch (ProcessFailedException e){
			fail("ClearAllGridMarksController should not have thrown exception");
		}
		
		assertEquals(0, testCellA1.getMarks().size());
		assertEquals(0, testCellB1.getMarks().size());
		assertEquals(2, testCellA2.getMarks().size());
		
		
		
	}
	
	/**
	 * Tests the RemoveDigitController
	 */
	@Test
	public void testRemoveDigit() {
		Cell cell = new Cell(new Location(1, 'A'));
		Value digit = new Value('7');
		cell.setDigit(digit);
		
		assertEquals(cell.getDigit(), new Value('7'));
		
		//Test Valid Move
		RemoveDigitController controller = new RemoveDigitController(cell);
		
		try {
			controller.process();
		} catch (ProcessFailedException e) {
			fail("RemoveDigitController should have thrown exception");
		}
		
		assertNull(cell.getDigit());
		
		
		//Test Invalid move
		try{
			controller.process();
		} catch (ProcessFailedException e){
			assertEquals("RemoveDigitMove is not valid", e.getMessage());
		}
		assertNull(cell.getDigit());
	}
	
	/**
	 * Tests the RemoveIncorrectController
	 */
	@Test
	public void testRemoveIncorrect(){
		Grid ig;
		Grid sg;
		Puzzle p;

		//Set up Values
		Value one = new Value('1');
		Value two = new Value('2');
		Value three = new Value('3');
		
		//Set up the locations
		Location a1 = new Location (1, 'A');
		Location b1 = new Location (1, 'B');
				
			ig = new Grid(1,2);
			sg = new Grid(1,2);
			p = new Puzzle(sg);
			p.setInitialGrid(ig);
			
			//Setup some Test marks
			p.getPlayerGrid().getCellAtLocation(a1).addMark(one);
			p.getPlayerGrid().getCellAtLocation(a1).addMark(two);
			
			//Setup Player Grid
			p.getPlayerGrid().getCellAtLocation(a1).setDigit(two);
			p.getPlayerGrid().getCellAtLocation(b1).setDigit(three);
			
			//Setup Solution Grid
			p.getSolutionGrid().getCellAtLocation(a1).setDigit(one);
			p.getSolutionGrid().getCellAtLocation(b1).setDigit(three);

		RemoveIncorrectController testController = new RemoveIncorrectController(p); 			
		
		try {
			testController.process();
		} catch (ProcessFailedException e) {
			fail("RemoveIncorrectController should not have thrown exception");
		}
	}
	
	/**
	 * Tests the RemoveMarkController
	 */
	@Test
	public void testRemoveMark() {
		Cell cell = new Cell(new Location(1, 'A'));
		Value one = new Value('1');
		Value two = new Value('2');
		
		cell.addMark(one);
		cell.addMark(two);
		
		//Test Valid Move
		RemoveMarkController testControl = new RemoveMarkController(cell, one);
		
		try {
			testControl.process();
		} catch (ProcessFailedException e) {
			fail("RemoveMarkController should not have thrown exception");
		}
		
		assertFalse(cell.getMarks().contains(one));
		
		
		//Test Invalid Move
			
		try {
			testControl.process();
			fail("RemoveMarkController should have thrown exception");
		} catch (ProcessFailedException e) {
			assertFalse(cell.getMarks().contains(one));
		}
				
	}
	
}
