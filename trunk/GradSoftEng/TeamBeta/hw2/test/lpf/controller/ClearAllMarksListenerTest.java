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

public class ClearAllMarksListenerTest extends TestCase {
	KenKenGUI gui;

	public void setUp() {
		gui = new KenKenGUI();

		KenKenPreference pref = new KenKenPreference();
		pref.clearPreferences();
		pref.setPuzzleLibraryLocation("puzzles/test/good/valid_library1.zip");
		pref.setPreferredDifficulty(Difficulty.EASY);
		pref.setPreferredSize(4);
		gui.setPreference(pref);

		gui.setPuzzleLibrary(new PuzzleLibrary(new File(
				"puzzles/test/good/valid_library1.zip")));
		FileLoader fl = gui.getPuzzleLibrary().randomPuzzleLoader(gui.getPreference());
		KenKenPuzzle puzzle = fl.getKenKenPuzzle();
		gui.setPuzzle(puzzle);

		Location loc = new Location(2, 'B');
		Cell cell = puzzle.getPlayerGrid().getCellAtLocation(loc);
		
		cell.addMark(new Value('2'));	
		cell.addMark(new Value('4'));
		cell.addMark(new Value('5'));
		cell.addMark(new Value('8'));

		gui.setCurrentCell(cell);
		assertEquals(gui.getCurrentCell().getMarks().size(), 4);
	}

	public void testItemStateChanged() {
		// Select the clear box
		JCheckBox markButtons[] = this.gui.getMainPanel().getMarkPanel().getMarkButtons();
		markButtons[markButtons.length - 1].setSelected(true);
		
		ClearAllMarksListener listener = new ClearAllMarksListener(gui);
		listener.actionPerformed(null);
		
		assertEquals(gui.getCurrentCell().getMarks().size(), 0);
	}
}
