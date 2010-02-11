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

public class PreferenceListenerTest extends TestCase {
	KenKenGUI gui = new KenKenGUI();
	KenKenPreference pref;

	public void setUp() {
		pref = new KenKenPreference();
		RestartListener listener = new RestartListener(gui);
		assertNotNull(listener.gui);

		pref = new KenKenPreference();
		pref.clearPreferences();
		pref.setPuzzleLibraryLocation("puzzles/test/good/valid_library1.zip");
		pref.setPreferredDifficulty(Difficulty.EASY);
		pref.setPreferredSize(4);
		gui.setPreference(pref);

		gui.setPuzzleLibrary(new PuzzleLibrary(new File(
				"puzzles/test/good/valid_library1.zip")));
		FileLoader fl = gui.getPuzzleLibrary().randomPuzzleLoader(
				gui.getPreference());
		KenKenPuzzle puzzle = fl.getKenKenPuzzle();
		gui.setPuzzle(puzzle);
	}
	
	/*
	 * Test for 'actionPerformed(ActionEvent e)'
	 */
	public void testActionPerformed() {
		PreferenceListener listener = new PreferenceListener(gui);
		gui.setPreference(null);
		listener.actionPerformed(new ActionEvent(this, 1, ""));
		gui.setPreference(pref);
		listener.actionPerformed(new ActionEvent(this, 1, ""));
	}
}
