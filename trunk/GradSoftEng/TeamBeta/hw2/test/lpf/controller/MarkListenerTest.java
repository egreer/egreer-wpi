package lpf.controller;

import java.io.File;
import javax.swing.JCheckBox;
import junit.framework.TestCase;
import lpf.gui.KenKenGUI;
import lpf.model.core.Cell;
import lpf.model.core.Location;
import lpf.model.core.Value;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.fileManagement.FileLoader;
import lpf.model.kenken.fileManagement.PuzzleLibrary;
import lpf.model.kenken.preferences.Difficulty;
import lpf.model.kenken.preferences.KenKenPreference;

public class MarkListenerTest extends TestCase {
	
	KenKenGUI gui;
	
	/**
	 * Setup some sample values to work with.
	 */
	protected void setUp() {
		gui = new KenKenGUI();
		
		KenKenPreference pref = new KenKenPreference();
		pref.clearPreferences();
		pref.setPuzzleLibraryLocation("puzzles/test/good/valid_library1.zip");
		pref.setPreferredDifficulty(Difficulty.EASY);
		pref.setPreferredSize(4);
		gui.setPreference(pref);
		
		gui.setPuzzleLibrary(new PuzzleLibrary(new File("puzzles/test/good/valid_library1.zip")));
		FileLoader fl = gui.getPuzzleLibrary().randomPuzzleLoader(gui.getPreference());
		KenKenPuzzle puzzle = fl.getKenKenPuzzle();
		gui.setPuzzle(puzzle);
		
		Location loc = new Location(2, 'B');
		Cell cell = puzzle.getPlayerGrid().getCellAtLocation(loc);

		gui.setCurrentCell(cell);
	}
	
	
	/*
	 * Test for 'MarkListener(KenKenGUI)'
	 */
	public void testMarkListener() {
		KenKenGUI gui = new KenKenGUI();
		MarkListener listener = new MarkListener(gui);
		assertNotNull(listener);
	}
	
	/*
	 * Test for 'ItemStateChanged(ActionEvent)'
	 */
	public void testItemStateChanged() {
		JCheckBox markButtons[] = this.gui.getMainPanel().getMarkPanel().getMarkButtons();
		Cell cell = this.gui.getPuzzle().getPlayerGrid().cells[1][1];
		
		// check all check boxes are empty
		assertEquals(markButtons[0].isSelected(), false);
		assertEquals(markButtons[1].isSelected(), false);
		assertEquals(markButtons[2].isSelected(), false);
		assertEquals(markButtons[3].isSelected(), false);
		
		// check on
		markButtons[3].doClick();
		assertEquals(markButtons[3].isSelected(), true);
		assertTrue(cell.getMarks().contains(new Value('4')));
		
		
		// test the undo/redo history 
		this.gui.getHistory().undo();
		assertEquals(markButtons[3].isSelected(), true);
		assertFalse(cell.getMarks().contains(new Value('4')));
		this.gui.getHistory().redo();
		assertTrue(cell.getMarks().contains(new Value('4')));
		
		// check off
		markButtons[3].doClick();
		assertEquals(markButtons[3].isSelected(), false);
		assertFalse(cell.getMarks().contains(new Value('4')));
		
		// test the undo/redo history
		assertFalse(cell.getMarks().contains(new Value('4')));
		this.gui.getHistory().undo();
		assertEquals(markButtons[3].isSelected(), false);
		assertTrue(cell.getMarks().contains(new Value('4')));
		this.gui.getHistory().redo();
		assertFalse(cell.getMarks().contains(new Value('4')));
		
	}
	
}
