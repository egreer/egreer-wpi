package lpf.controller;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JOptionPane;

import junit.framework.TestCase;
import lpf.gui.KenKenGUI;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.fileManagement.FileLoader;
import lpf.model.kenken.fileManagement.PuzzleLibrary;
import lpf.model.kenken.preferences.Difficulty;
import lpf.model.kenken.preferences.KenKenPreference;

public class RestartListenerTest extends TestCase {

	KenKenGUI gui = new KenKenGUI();

	public void setUp() {
		gui = new KenKenGUI();
		RestartListener listener = new RestartListener(gui);
		assertNotNull(listener.gui);

		KenKenPreference pref = new KenKenPreference();
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
		KenKenPuzzle puzzle = this.gui.getPuzzle();
		RestartListener listener = new RestartListener(gui);
		listener.actionPerformed(new ActionEvent(this, 1, ""));
		assertEquals(this.gui.getPuzzle().getPlayerGrid(), puzzle.getInitialGrid());
	}

	public void testRestartListenerNo(){
		JOptionPane.showMessageDialog(gui, "Please select No in the next window");
		KenKenPuzzle puzzle = this.gui.getPuzzle();
		RestartListener listener = new RestartListener(gui);
		listener.actionPerformed(new ActionEvent(this, 1, ""));
		assertEquals(this.gui.getPuzzle().getPlayerGrid(), puzzle.getInitialGrid());
		
	}

}
