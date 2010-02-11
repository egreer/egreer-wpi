package lpf.gui;

import javax.swing.JOptionPane;

import junit.framework.TestCase;
import lpf.model.kenken.preferences.Difficulty;
import lpf.model.kenken.preferences.KenKenPreference;

import org.junit.Before;
import org.junit.Test;

public class PreferenceGUITest extends TestCase {

	PreferenceGUI preference;
	KenKenPreference pref;
	KenKenGUI gui;
	
	@Before
	public void setUp(){
		
		pref = new KenKenPreference();
		pref.clearPreferences();
		pref.setPuzzleLibraryLocation("puzzles/test/good/valid_library1.zip");
		pref.setPreferredDifficulty(Difficulty.EASY);
		pref.setPreferredSize(4);	
		
		gui = new KenKenGUI();
		gui.setPreference(pref);
		
	}
	
	@Test
	public void testPreferenceEasyEmpty() {		
		JOptionPane.showMessageDialog(null, "Select Easy and empty size");
		preference = new PreferenceGUI(gui, true);
		
		
		assertNotNull(pref.getPreferredDifficulty());
		assertEquals(pref.getPreferredDifficulty(), Difficulty.EASY);
		assertNull(pref.getPreferredSize());
		
	}
	
	public void testPreferenceMedium4()
	{
		JOptionPane.showMessageDialog(null, "Select Medium and size 4");
		preference = new PreferenceGUI(gui, true);
		
		// Select Medium and size 4
		assertEquals(pref.getPreferredDifficulty(), Difficulty.MEDIUM);
		assertEquals(pref.getPreferredSize(), new Integer(4));
		assertNotNull(pref.getPreferredSize());
	}
	


}
