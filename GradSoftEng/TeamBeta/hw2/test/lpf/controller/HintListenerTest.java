package lpf.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Iterator;

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

public class HintListenerTest extends TestCase {
	KenKenGUI gui;

	public void setUp() {
		gui = new KenKenGUI();
		AboutListener listener = new AboutListener(gui);
		assertNotNull(listener.gui);

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
		puzzle.getPlayerGrid().getCellAtLocation(new Location(2, 'B')).setDigit(
				new Value('3'));
		gui.setPuzzle(puzzle);

		int numSolved = 0;
		Iterator<Cell> it = puzzle.getPlayerGrid().iterator();
		while (it.hasNext()) {
			if (it.next().isOccupied()) {
				numSolved++;
			}
		}

		assertEquals(1, numSolved);
	}

	/*
	 * Test for 'actionPerformed(ActionEvent e)'
	 */
	public void testActionPerformed() {
		HintListener listener = new HintListener(gui);
		listener.actionPerformed(new ActionEvent(this, 1, ""));

		int numSolved = 0;
		Iterator<Cell> it = gui.getPuzzle().getPlayerGrid().iterator();
		while (it.hasNext()) {
			if (it.next().isOccupied()) {
				numSolved++;
			}
		}

		assertEquals(2, numSolved);
	}
}
