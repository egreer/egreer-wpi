package lpf.gui;

import junit.framework.TestCase;
import lpf.model.kenken.preferences.Difficulty;
import lpf.model.kenken.preferences.KenKenPreference;

public class MenuTest extends TestCase {

	private KenKenGUI gui;
	
	public void testNoLibrary()
	{
		// Clear the preference
		KenKenPreference pref = new KenKenPreference();
		pref.clearPreferences();		
		
		gui = new KenKenGUI();
		
		Menu menu = gui.getMenu();
		
		assertTrue(menu.getMniLib().isEnabled());
		assertFalse(menu.getMniPref().isEnabled());
		assertFalse(menu.getMniPrint().isEnabled());
		assertTrue(menu.getMniExit().isEnabled());
		
		assertFalse(menu.getMniNew().isEnabled());
		assertTrue(menu.getMniSelect().isEnabled());
		assertFalse(menu.getMniGiveUp().isEnabled());
		assertFalse(menu.getMniRestart().isEnabled());
		
		assertFalse(menu.getMniUndo().isEnabled());
		assertFalse(menu.getMniRedo().isEnabled());
		assertFalse(menu.getMniIdIncorrect().isEnabled());
		assertFalse(menu.getMniRemoveIncorrect().isEnabled());
		
		assertFalse(menu.getMniHint().isEnabled());
		assertFalse(menu.getMniTimer().isEnabled());
		assertTrue(menu.getMniLibInfo().isEnabled());
		
		assertTrue(menu.getMniRules().isEnabled());
		assertTrue(menu.getMniAbout().isEnabled());
		
		assertNotNull(menu.getFcFile());	
		assertNotNull(menu.getFcLib());
	
	}
	
	public void testWithLibrary()
	{
		KenKenPreference pref = new KenKenPreference();
		pref.clearPreferences();
		pref.setPuzzleLibraryLocation("puzzles/test/good/valid_library1.zip");
		pref.setPreferredDifficulty(Difficulty.EASY);
		pref.setPreferredSize(4);		
		
		gui = new KenKenGUI();
		gui.setPreference(pref);
		
		Menu menu = gui.getMenu();
		
		assertTrue(menu.getMniPref().isEnabled());
	}

	public void testNewGame()
	{
		KenKenPreference pref = new KenKenPreference();
		pref.clearPreferences();
		pref.setPuzzleLibraryLocation("puzzles/test/good/valid_library1.zip");
		pref.setPreferredDifficulty(Difficulty.EASY);
		pref.setPreferredSize(4);		
		
		gui = new KenKenGUI();
		gui.setPreference(pref);
		gui.buildNewGame(gui.getPuzzle());
		
		Menu menu = gui.getMenu();
		
		assertTrue(menu.getMniGiveUp().isEnabled());
		assertTrue(menu.getMniRestart().isEnabled());
		
		assertTrue(menu.getMniIdIncorrect().isEnabled());
		assertTrue(menu.getMniRemoveIncorrect().isEnabled());
		
		assertTrue(menu.getMniHint().isEnabled());
		assertTrue(menu.getMniTimer().isEnabled());		
	}
}
