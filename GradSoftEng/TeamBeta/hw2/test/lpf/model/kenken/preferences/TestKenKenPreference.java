package lpf.model.kenken.preferences;

import junit.framework.TestCase;

/**
 * Tests the KenKenPreference class. This will test the effects of having one
 * and two preference objects.
 * 
 * @author Peter Kalauskas
 */
public class TestKenKenPreference extends TestCase {
	public void testSettersAndGetters() {
		KenKenPreference pref = new KenKenPreference();

		pref.clearPreferences();

		assertNull(pref.getPreferredDifficulty());
		assertNull(pref.getPreferredSize());
		assertNull(pref.getPuzzleLibraryLocation());

		Difficulty diff = Difficulty.MEDIUM;
		int size = 4;
		String libLoc = "/library/location/lib.zip";

		pref.setPreferredDifficulty(diff);
		pref.setPreferredSize(size);
		pref.setPuzzleLibraryLocation(libLoc);

		assertNull(pref.getPreferredDifficulty());
		assertNull(pref.getPreferredSize());
		assertEquals(pref.getPuzzleLibraryLocation(), libLoc);

		pref.clearPreferences();
	}

	public void testTwoPreferenceObjects() {
		KenKenPreference pref1 = new KenKenPreference();
		KenKenPreference pref2 = new KenKenPreference();

		pref1.clearPreferences();
		pref2.clearPreferences();

		Difficulty diff = Difficulty.MEDIUM;
		int size = 4;
		String libLoc = "/library/location/lib.zip";

		pref1.setPreferredDifficulty(diff);
		pref1.setPreferredSize(size);
		pref1.setPuzzleLibraryLocation(libLoc);

		assertNull(pref2.getPreferredDifficulty());
		assertNull(pref2.getPreferredSize());
		assertEquals(pref2.getPuzzleLibraryLocation(), libLoc);

		pref2.clearPreferences();

		assertNull(pref1.getPreferredDifficulty());
		assertNull(pref1.getPreferredSize());
		assertNull(pref1.getPuzzleLibraryLocation());
	}
}
