package lpf.gui;


import java.io.File;

import junit.framework.TestCase;
import lpf.model.core.Cell;
import lpf.model.core.Location;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.fileManagement.FileLoader;
import lpf.model.kenken.fileManagement.PuzzleLibrary;
import lpf.model.kenken.preferences.Difficulty;
import lpf.model.kenken.preferences.KenKenPreference;

public class KenKenGUITest extends TestCase {

	public void testInitialGUI()
	{
		KenKenPreference pref = new KenKenPreference();
		pref.clearPreferences();		

		KenKenGUI gui = new KenKenGUI();
		
		assertFalse(gui.isVisible());		
		assertFalse(gui.isPlaying());		
		assertNull(gui.getFileLoader());		
		assertNull(gui.getPuzzle());
		assertNull(gui.getPuzzleLibrary());
		assertNull(gui.getHistory());
		assertNull(gui.getCurrentCell());
		assertNotNull(gui.getPreference());
		assertNotNull(gui.getMenu());
		assertNotNull(gui.getMainPanel());		
		 
		PuzzleLibrary puzzleLibrary = new PuzzleLibrary(new File("puzzles/test/good/valid_library1.zip"));
		gui.setPuzzleLibrary(puzzleLibrary);		
		assertNotNull(gui.getPuzzleLibrary());
		
		FileLoader fl = new FileLoader(new File("puzzles/test/good/kenken_4x4_easy2.xml"));
		gui.setFileLoader(fl);
		assertNotNull(gui.getFileLoader());	
		
		KenKenPuzzle puzzle = fl.getKenKenPuzzle();
		gui.setPuzzle(puzzle);
		assertNotNull(gui.getPuzzle());
		
		assertNotNull(gui.getPreference());
		
		Cell cell = new Cell(new Location(1,'A'));		
		gui.setCurrentCell(cell);
		assertNotNull(gui.getCurrentCell());
		
		gui.buildNewGame(puzzle);
		assertTrue(gui.isPlaying());		
		
		gui.setPlaying(true);
		assertTrue(gui.isPlaying());
		
		gui.setPlaying(false);
		assertFalse(gui.isPlaying());
		assertNull(gui.getCurrentCell().getDigit());
		assertEquals(gui.getCurrentCell().loc.column, 'A');
		assertEquals(gui.getCurrentCell().loc.row, 1);
		
		gui.setPreference(pref);
		assertNotNull(gui.getPreference());
	}
	
	public void testGUIwithLibrary()
	{
		KenKenPreference pref = new KenKenPreference();
		pref.clearPreferences();
		pref.setPuzzleLibraryLocation("puzzles/test/good/valid_library1.zip");
		pref.setPreferredDifficulty(Difficulty.EASY);
		pref.setPreferredSize(4);
		
		KenKenGUI gui = new KenKenGUI();
		
		assertNotNull(gui.getPuzzleLibrary());
		assertNotNull(gui.getPuzzle());
	}
}
