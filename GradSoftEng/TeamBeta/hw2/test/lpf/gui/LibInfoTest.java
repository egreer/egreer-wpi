package lpf.gui;

import junit.framework.TestCase;
import lpf.model.kenken.preferences.Difficulty;
import lpf.model.kenken.preferences.KenKenPreference;

public class LibInfoTest extends TestCase {

	
	public void testNoLibrary()
	{
		KenKenPreference pref = new KenKenPreference();
		pref.clearPreferences();		
		
		new LibInfo(new KenKenGUI());
	}
	
	public void testWithLibrary()
	{
		KenKenPreference pref = new KenKenPreference();
		pref.clearPreferences();
		pref.setPuzzleLibraryLocation("puzzles/test/good/valid_library1.zip");
		pref.setPreferredDifficulty(Difficulty.EASY);
		pref.setPreferredSize(4);	
		
		KenKenGUI gui = new KenKenGUI();
		gui.setPreference(pref);
		
		new LibInfo(gui);
		
	}

}
