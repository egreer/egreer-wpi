package lpf.controllers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.zip.ZipFile;

import lpf.GUIs.SudokuAlphaMainGUI;
import lpf.GUIs.GridView.GridView;
import lpf.GUIs.GridView.PuzzleManager;
import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.gameLibrary.SudokuLibraryLoader;
import lpf.model.core.*;
import lpf.moves.MoveManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Eric Greer
 *
 */
public class GamePlayControllerTest {

	Grid ig;
	Grid sg;
	Puzzle p;

	//Set up Values
	Value one = new Value('1');
	Value two = new Value('2');
	Value three = new Value('3');
	Value four = new Value('4');

	//Set up the locations
	Location a1 = new Location (1, 'A');
	Location a2 = new Location (2, 'A');
	Location b1 = new Location (1, 'B');
	Location b2 = new Location (2, 'B');
	
	@Before
	public void setUp() throws Exception{
		ig = new Grid(2,2);
		sg = new Grid(2,2);
		
		p = new Puzzle(sg);
		p.setInitialGrid(ig);
		sg.getCellAtLocation(a1).setDigit(one);
		sg.getCellAtLocation(a2).setDigit(two);
		sg.getCellAtLocation(b1).setDigit(three);
		sg.getCellAtLocation(b2).setDigit(four);
		
		p.getPlayerGrid().getCellAtLocation(a1).addMark(one);
		p.getPlayerGrid().getCellAtLocation(a1).addMark(two);
		p.getPlayerGrid().getCellAtLocation(a1).setDigit(two);
		p.getPlayerGrid().getCellAtLocation(b1).setDigit(three);
		
		SudokuAlphaGameConfiguration.getInstance().setPuzzle(p);
		SudokuAlphaMainGUI gui = new SudokuAlphaMainGUI();
		SudokuAlphaGameConfiguration.getInstance().setGUI(gui);
		
		PuzzleManager pm = new PuzzleManager(p);
		gui.setGridView(new GridView(pm));
	}

	@After
	public void tearDown() throws Exception {
		ig = null;
		sg = null;
		p = null;
		SudokuAlphaGameConfiguration.getInstance().setPuzzle(null);
		SudokuAlphaGameConfiguration.getInstance().setDifficulty(2);
		SudokuAlphaGameConfiguration.getInstance().getGUI().dispose();
		SudokuAlphaGameConfiguration.getInstance().setGUI(null);
	}
	
	@Test
	public void testChangeDifficulty() {
		
		// Valid Difficulty
		ChangeDifficultyController testControl = new ChangeDifficultyController(6);
		
		try {
			testControl.process();
		} catch (ProcessFailedException e) {
			fail("ChangeDifficultyController should not have thrown exception when testing valid change");
		}
		
		assertEquals(6, SudokuAlphaGameConfiguration.getInstance().getDifficulty());
		
		// Invalid Difficulty Low
		testControl = new ChangeDifficultyController(0);
		
		try {
			testControl.process();
			fail("ChangeDifficultyController should not have thrown exception when testing 0");
		} catch (ProcessFailedException e) {
			assertEquals(6, SudokuAlphaGameConfiguration.getInstance().getDifficulty());
		}
		
		
		// Invalid Difficulty High
		testControl = new ChangeDifficultyController(11);
		try {
			testControl.process();
			fail("ChangeDifficultyController should not have thrown exception when testing 11");
		} catch (ProcessFailedException e) {
			assertEquals(6, SudokuAlphaGameConfiguration.getInstance().getDifficulty());
		}

	}
	

	@Test
	public void testGiveUp() {
		
		//Test Valid Controller
		GiveUpController testControl= new GiveUpController();
		try{
			testControl.process();
		} catch (ProcessFailedException e) {
			fail("GiveUpController should not have thrown exception");
		}
		
		assertTrue(GridFunctions.compareGrids(p.getPlayerGrid(), p.getSolutionGrid()));
		
		//Test Invalid Controller
		SudokuAlphaGameConfiguration.getInstance().setPuzzle(null);
		
		testControl= new GiveUpController();
		try{
			testControl.process();
			fail("GiveUpController should have thrown exception");
		} catch (ProcessFailedException e) {
			assertEquals(e.getMessage(), e.getMessage());
			//TODO Better test here?
		}
		
	}
	
	@Test
	public void testHint() {
		
		//Test Remove Digit
		HintController testControl = new HintController(SudokuAlphaGameConfiguration.getInstance().getCurrentPuzzle());
		
		try {
			testControl.process();
		} catch (ProcessFailedException e) {
			fail("HintController should not have thrown an exception");
		}
		assertNull(p.getPlayerGrid().getCellAtLocation(a1).getDigit());
		
		//Test Reveal Digit
		testControl = new HintController(SudokuAlphaGameConfiguration.getInstance().getCurrentPuzzle());
		try {
			testControl.process();
		} catch (ProcessFailedException e) {
			fail("HintController should not have thrown an exception");
		}
		
		assertEquals(one, p.getPlayerGrid().getCellAtLocation(a1).getDigit());
	
		//Test No Hint Available
		p.getPlayerGrid().getCellAtLocation(a2).setDigit(two);
		p.getPlayerGrid().getCellAtLocation(b2).setDigit(four);
		testControl = new HintController(SudokuAlphaGameConfiguration.getInstance().getCurrentPuzzle());
		//Then you have won
		try {
			testControl.process();
		} catch (ProcessFailedException e) {
			fail("HintController should have thrown an exception");
		}
	}
	
	@Test
	public void testNewGame() {
		try {
			SudokuAlphaGameConfiguration.getInstance().setPuzzleLibrary(SudokuLibraryLoader.loadLibrary(new ZipFile("files/TestPuzzlePackage.zip")));
		} catch (IOException e1) {
			fail("Setting Library should not have throw exception");
		}
		
		
		NewGameController testControl = new NewGameController();
		

		//Test Valid
		SudokuAlphaGameConfiguration.getInstance().setPuzzle(null);
		try {
			testControl.process();
		} catch (ProcessFailedException e) {
			fail("NewGameController should not have throw exception");
		}
		assertNotNull(SudokuAlphaGameConfiguration.getInstance().getCurrentPuzzle());
		
		
		//Reset and test Invalid 
		SudokuAlphaGameConfiguration.getInstance().setPuzzle(null);
		SudokuAlphaGameConfiguration.getInstance().setDifficulty(10);
				
		testControl = new NewGameController();
		try {
			testControl.process();
			fail("NewGameController should have throw exception");
		} catch (ProcessFailedException e) {
			assertNull(SudokuAlphaGameConfiguration.getInstance().getCurrentPuzzle());	
		}
		
		//Reset The puzzle Library
		try {
			SudokuAlphaGameConfiguration.getInstance().setPuzzleLibrary(SudokuLibraryLoader.loadLibrary(new ZipFile("files/sudokuLibrary.zip")));
		} catch (IOException e1) {
			fail("Setting Library should not have throw exception");
		}
	}
	
	@Test
	public void testUndoRedo() {
		UndoController testUndoControl = new UndoController();
		MoveManager.getInstance().clearStack();
		//Try to undo nothing
		try{
			testUndoControl.process();
			fail("Undo should have thrown exception when trying to undo nothing");
		}catch (ProcessFailedException e){
			assertEquals("Could not undo last move", e.getMessage());
		}
		
		//Do add digit move
		Cell testCell = new Cell(new Location(1, 'A'));
		Value one = new Value('1');
		AddDigitController sampleMove = new AddDigitController(testCell, one);
		try {
			sampleMove.process();
		} catch (ProcessFailedException e) {
			fail("AddigitController should not have thrown exception");
		}
		
		assertSame(one, testCell.getDigit());
		
		//Try to undo valid move
		try{
			testUndoControl.process();
		}catch (ProcessFailedException e){
			fail("Undo should not have thrown exception when trying to undo move");
		}
		
		assertNull(testCell.getDigit());
		
		//Try to redo valid move
		RedoController testRedoControl = new RedoController();
		try{
			testRedoControl.process();
		}catch (ProcessFailedException e){
			fail("Redo should not have thrown exception when trying to undo move");
		}
		assertSame(one, testCell.getDigit());
		
		//Try to redo nothing
		try{
			testRedoControl.process();
			fail("Redo should have thrown exception when trying to redo nothing");
		}catch (ProcessFailedException e){
			assertEquals("Could not redo last move", e.getMessage());
		}
		
	}
		
	@Test
	public void testResetGrid() {
		ResetGridController testControl = new ResetGridController(SudokuAlphaGameConfiguration.getInstance().getCurrentPuzzle());
		try {
			testControl.process();
		} catch (ProcessFailedException e) {
			fail("ResetGridController should not have thrown exception");
		}
		assertTrue(GridFunctions.compareGrids(p.getInitialGrid(), p.getPlayerGrid()));
	}
	

}
