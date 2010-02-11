package lpf.controller;

import javax.swing.JOptionPane;

import junit.framework.TestCase;

import lpf.gui.KenKenGUI;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.preferences.Difficulty;
import lpf.model.kenken.preferences.KenKenPreference;

import org.junit.Before;
import org.junit.Test;

public class SelectGameListenerTest extends TestCase {

	KenKenGUI gui;
	KenKenPuzzle puzzle;
	
	@Before
	public void setUp() throws Exception {
		KenKenPreference pref = new KenKenPreference();
		pref.clearPreferences();
		pref.setPuzzleLibraryLocation("puzzles/test/good/valid_library1.zip");
		pref.setPreferredDifficulty(Difficulty.EASY);
		pref.setPreferredSize(4);
		
		
		gui = new KenKenGUI();
		gui.setPreference(pref);
		
		puzzle = gui.getPuzzle();
		gui.buildNewGame(gui.getPuzzle());
	}

	@Test
	public void testSelectGameListenerNo() {
		JOptionPane.showMessageDialog(gui, "Please select No");
		SelectGameListener listener = new SelectGameListener(gui);		
		listener.actionPerformed(null);
		assertEquals(puzzle, gui.getPuzzle());
	}
	
	@Test
	public void testSelectGameListenerInvalidFile() {
		JOptionPane.showMessageDialog(gui, "Please select Yes and choose a invalid XML file");
		SelectGameListener listener = new SelectGameListener(gui);		
		listener.actionPerformed(null);
		assertEquals(puzzle, gui.getPuzzle());
	}
	
	@Test
	public void testSelectGameListenerValidFile() {
		JOptionPane.showMessageDialog(gui, "Please select Yes and choose a valid XML file");
		SelectGameListener listener = new SelectGameListener(gui);		
		listener.actionPerformed(null);
		assertNotSame(puzzle, gui.getPuzzle());		
	}
}