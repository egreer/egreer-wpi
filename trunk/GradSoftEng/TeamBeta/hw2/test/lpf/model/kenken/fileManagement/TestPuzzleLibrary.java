package lpf.model.kenken.fileManagement;

import java.io.File;
import java.util.Set;

import junit.framework.TestCase;
import lpf.model.kenken.preferences.Difficulty;

public class TestPuzzleLibrary extends TestCase {

	public void testLoadGoodLibraries() {
		// A clean library with only good files in the root directory
		PuzzleLibrary puzzleLib = new PuzzleLibrary(new File(
				"puzzles/test/good/valid_library1.zip"));
		assertTrue(puzzleLib.isValid());

		// test a dirty library with no good puzzles in the root directory,
		// and a bunch of bad files, and bad xml files representing bad
		// ken-ken puzzles (this is still valid)
		puzzleLib = new PuzzleLibrary(new File("puzzles/test/good/valid_library2.zip"));
		assertTrue(puzzleLib.isValid());
	}

	public void testLoadBadLibrary() {
		// Test a library with a valid puzzle file that doesn't end with .xml
		// and it ignored.
		PuzzleLibrary puzzleLib = new PuzzleLibrary(new File(
				"puzzles/test/bad/invalid_library1.zip"));
		assertFalse(puzzleLib.isValid());

		// Test a library with one xml file in it that is an invalid puzzle
		puzzleLib = new PuzzleLibrary(
				new File("puzzles/test/bad/invalid_library2.zip"));
		assertFalse(puzzleLib.isValid());

		// Test a library with no files in it
		puzzleLib = new PuzzleLibrary(
				new File("puzzles/test/bad/invalid_library3.zip"));
		assertFalse(puzzleLib.isValid());
	}

	public void testLibraryInfo1() {
		// A clean library with only good files in the root directory
		File libLocation = new File("puzzles/test/good/valid_library1.zip");
		PuzzleLibrary puzzleLib = new PuzzleLibrary(libLocation);
		assertTrue(puzzleLib.isValid());

		assertEquals(puzzleLib.totalPuzzles(), 7);

		assertEquals(puzzleLib.numPuzzlesOfDifficulty(Difficulty.EASY), 3);
		assertEquals(puzzleLib.numPuzzlesOfDifficulty(Difficulty.MEDIUM), 3);
		assertEquals(puzzleLib.numPuzzlesOfDifficulty(Difficulty.HARD), 1);

		assertEquals(puzzleLib.numPuzzlesOfSize(2), 1);
		assertEquals(puzzleLib.numPuzzlesOfSize(4), 4);
		assertEquals(puzzleLib.numPuzzlesOfSize(6), 2);
		assertEquals(puzzleLib.numPuzzlesOfSize(9), 0);

		Set<Difficulty> difficulties = puzzleLib.availableDifficulties();
		assertTrue(difficulties.contains(Difficulty.EASY));
		assertTrue(difficulties.contains(Difficulty.MEDIUM));
		assertTrue(difficulties.contains(Difficulty.HARD));

		Set<Integer> sizes = puzzleLib.availableSizesOfDifficulty(Difficulty.EASY);
		assertTrue(sizes.contains(2));
		assertTrue(sizes.contains(4));
		assertTrue(sizes.contains(6));
		assertEquals(sizes.size(), 3);

		sizes = puzzleLib.availableSizesOfDifficulty(Difficulty.MEDIUM);
		assertTrue(sizes.contains(4));
		assertEquals(sizes.size(), 1);

		sizes = puzzleLib.availableSizesOfDifficulty(Difficulty.HARD);
		assertTrue(sizes.contains(6));
		assertEquals(sizes.size(), 1);

		assertEquals(puzzleLib.numPuzzlesOfAttributes(Difficulty.EASY, 4), 1);
		assertEquals(puzzleLib.numPuzzlesOfAttributes(Difficulty.EASY, 6), 1);
		assertEquals(puzzleLib.numPuzzlesOfAttributes(Difficulty.MEDIUM, 4), 3);
		assertEquals(puzzleLib.numPuzzlesOfAttributes(Difficulty.HARD, 6), 1);

		assertEquals(puzzleLib.numPuzzlesOfAttributes(Difficulty.EASY, 5), 0);
		assertEquals(puzzleLib.numPuzzlesOfAttributes(Difficulty.EASY, 9), 0);
		assertEquals(puzzleLib.numPuzzlesOfAttributes(Difficulty.MEDIUM, 6), 0);
		assertEquals(puzzleLib.numPuzzlesOfAttributes(Difficulty.HARD, 4), 0);

		assertEquals(puzzleLib.getPath(), libLocation.getAbsolutePath());
	}

	public void testLibraryInfo2() {
		// A clean library with only good files in the root directory
		PuzzleLibrary puzzleLib = new PuzzleLibrary(new File(
				"puzzles/test/good/valid_library2.zip"));
		assertTrue(puzzleLib.isValid());

		assertEquals(puzzleLib.numPuzzlesOfDifficulty(Difficulty.EASY), 3);
		assertEquals(puzzleLib.numPuzzlesOfDifficulty(Difficulty.MEDIUM), 0);
		assertEquals(puzzleLib.numPuzzlesOfDifficulty(Difficulty.HARD), 1);

		Set<Difficulty> difficulties = puzzleLib.availableDifficulties();
		assertTrue(difficulties.contains(Difficulty.EASY));
		assertFalse(difficulties.contains(Difficulty.MEDIUM));
		assertTrue(difficulties.contains(Difficulty.HARD));
	}
}
