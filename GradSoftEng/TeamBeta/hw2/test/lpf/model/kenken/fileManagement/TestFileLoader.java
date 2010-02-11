package lpf.model.kenken.fileManagement;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import junit.framework.TestCase;

public class TestFileLoader extends TestCase {
	public void testGoodPuzzlesFromArchive() throws IOException {
		// valid_library.zip contains only files that represent valid ken-ken
		// puzzles. In reality, valid libraries may contain other files, as well
		// as invalid library files - this is tested in TestPuzzleLibrary
		// For now we'll just test FileLoader's constructor.

		ZipFile lib = new ZipFile("puzzles/test/good/valid_library1.zip");
		FileLoader fl;

		Enumeration<? extends ZipEntry> puzzles = lib.entries();
		while (puzzles.hasMoreElements()) {
			fl = new FileLoader(lib, puzzles.nextElement());
			assertNotNull(fl.getKenKenPuzzle());
		}
	}

	public void testGoodPuzzlesFromFile() {
		FileLoader fl;

		fl = new FileLoader(new File("puzzles/test/good/kenken_4x4_easy2.xml"));
		assertNotNull(fl.getKenKenPuzzle());

		fl = new FileLoader(new File("puzzles/test/good/kenken_4x4_med1.xml"));
		assertNotNull(fl.getKenKenPuzzle());

		fl = new FileLoader(new File("puzzles/test/good/kenken_4x4_med2.xml"));
		assertNotNull(fl.getKenKenPuzzle());

		fl = new FileLoader(new File("puzzles/test/good/kenken_4x4_med3.xml"));
		assertNotNull(fl.getKenKenPuzzle());

		fl = new FileLoader(new File("puzzles/test/good/kenken_6x6_easy3.xml"));
		assertNotNull(fl.getKenKenPuzzle());

		fl = new FileLoader(new File("puzzles/test/good/kenken_6x6_hard1.xml"));
		assertNotNull(fl.getKenKenPuzzle());
	}

	public void testDoesNotMatchSchema() throws IOException {
		FileLoader fl = new FileLoader(new File(
				"puzzles/test/bad/does_not_match_schema.xml"));
		assertNull(fl.getKenKenPuzzle());
	}

	public void testBadPuzzleType() throws IOException {
		FileLoader fl = new FileLoader(
				new File("puzzles/test/bad/bad_puzzle_type.xml"));
		assertNull(fl.getKenKenPuzzle());
	}

	public void testBadSize() throws IOException {
		FileLoader fl;
		
		// Puzzle isn't square
		fl = new FileLoader(new File("puzzles/test/bad/bad_size1.xml"));
		assertNull(fl.getKenKenPuzzle());

		// Puzzle is square, but wrong size
		fl = new FileLoader(new File("puzzles/test/bad/bad_size2.xml"));
		assertNull(fl.getKenKenPuzzle());
	}
	
	public void testNoCages() throws IOException {
		FileLoader fl = new FileLoader(new File("puzzles/test/bad/no_cages_given.xml"));
		assertNull(fl.getKenKenPuzzle());
	}

	public void testTooManySolutions() throws IOException {
		FileLoader fl = new FileLoader(new File("puzzles/test/bad/too_many_solutions.xml"));
		assertNull(fl.getKenKenPuzzle());
	}

	public void testNoSolutions() throws IOException {
		FileLoader fl = new FileLoader(new File("puzzles/test/bad/no_solutions.xml"));
		assertNull(fl.getKenKenPuzzle());
	}

	public void testRowsNotAllTheSameLength() throws IOException {
		FileLoader fl = new FileLoader(new File("puzzles/test/bad/not_all_rows_the_same_length.xml"));
		assertNull(fl.getKenKenPuzzle());
	}
}
