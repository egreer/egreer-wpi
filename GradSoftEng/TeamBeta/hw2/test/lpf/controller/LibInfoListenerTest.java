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

public class LibInfoListenerTest extends TestCase {
	
	/*
	 * Test for 'LibInfoListener(KenKenGUI)'
	 */
	public void testLibInfoListener() {
		KenKenGUI gui = new KenKenGUI();
		LibInfoListener listener = new LibInfoListener(gui);
		assertNotNull(listener.gui);
	}
	
	/*
	 * Test for 'actionPerformed(ActionEvent e)'
	 */
	public void testActionPerformed() {
		KenKenGUI gui = new KenKenGUI();
		
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
		
		LibInfoListener listener = new LibInfoListener(gui);
		
		listener.actionPerformed(new ActionEvent(this, 1, ""));
	}
	
}
