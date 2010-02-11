package lpf.controller;

import java.awt.event.ItemEvent;
import java.io.File;

import javax.swing.JCheckBoxMenuItem;

import junit.framework.TestCase;
import lpf.gui.KenKenGUI;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.fileManagement.FileLoader;
import lpf.model.kenken.fileManagement.PuzzleLibrary;
import lpf.model.kenken.preferences.Difficulty;
import lpf.model.kenken.preferences.KenKenPreference;

import org.junit.Before;

public class TimerListenerTest extends TestCase{
	
	KenKenGUI gui;
	
	JCheckBoxMenuItem item;
	
	ItemEvent e;
	
	TimerListener listener;

	@Before
	public void setUp() throws Exception {
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
	
		listener = new TimerListener(gui);
		item = new JCheckBoxMenuItem();
	}
	
	public void testSelectTimer()
	{
		e = new ItemEvent(item, 0, item , ItemEvent.SELECTED);
		listener.itemStateChanged(e);
		
		assertTrue(gui.getMainPanel().getTimerPanel().isVisible());
	}
	
	public void testDeselectTimer()
	{
		e = new ItemEvent(item, 0, item , ItemEvent.DESELECTED);
		listener.itemStateChanged(e);
		
		assertFalse(gui.getMainPanel().getTimerPanel().isVisible());
	}

}
