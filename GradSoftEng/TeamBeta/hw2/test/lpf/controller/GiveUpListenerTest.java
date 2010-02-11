package lpf.controller;

import java.awt.event.ActionEvent;
import java.io.File;

import junit.framework.TestCase;
import lpf.gui.KenKenGUI;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.fileManagement.FileLoader;
import lpf.model.kenken.fileManagement.PuzzleLibrary;
import lpf.model.kenken.preferences.Difficulty;
import lpf.model.kenken.preferences.KenKenPreference;

public class GiveUpListenerTest extends TestCase {
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
		gui.setPuzzle(puzzle);
	}

	/*
	 * Test for 'actionPerformed(ActionEvent e)'
	 */
	public void testActionPerformed() {
		GiveUpListener listener = new GiveUpListener(gui);
		listener.actionPerformed(new ActionEvent(this, 1, ""));
		
		assertFalse(gui.isPlaying());
	}
}
