package lpf.model.kenken.preferences;

import junit.framework.TestCase;

/**
 * Tests the Difficulty enumerated type.
 * 
 * @author Peter Kalauskas
 */
public class TestDifficulty extends TestCase {
	public void testGetDifficulty() {
		try {
			assertEquals(Difficulty.getDifficulty(1), Difficulty.EASY);
			assertEquals(Difficulty.getDifficulty(5), Difficulty.MEDIUM);
			assertEquals(Difficulty.getDifficulty(10), Difficulty.HARD);
		} catch (InvalidDifficultyException e) {
		}

		// an exception is expected
		try {
			Difficulty.getDifficulty(0);
		} catch (InvalidDifficultyException e) {
		}

		try {
			Difficulty.getDifficulty(11);
		} catch (InvalidDifficultyException e) {
		}
	}

	public void testGetInt() {
		assertEquals(Difficulty.EASY.toInt(), 1);
		assertEquals(Difficulty.MEDIUM.toInt(), 4);
		assertEquals(Difficulty.HARD.toInt(), 7);
	}
}
