package lpf.gameLibrary;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.zip.ZipFile;

import lpf.gameLibrary.IPuzzleLibrary;
import lpf.gameLibrary.NoPuzzleAvailableException;
import lpf.gameLibrary.SudokuGameLibrary;
import lpf.gameLibrary.SudokuLibraryLoader;

import org.junit.Test;

public class SudokuLibraryLoaderTest {

	
	
	@Test
	public void testLoadLibrary() {
		IPuzzleLibrary lib;
		try {
			ZipFile testZip = new ZipFile("files/TestPuzzlePackage.zip");
			 lib = SudokuLibraryLoader.loadLibrary(testZip);
			 lib.getRandomPuzzle(2);
			 SudokuGameLibrary slib = (SudokuGameLibrary)lib;
			 assertEquals(slib.puzzles.get(2).size(), 1);
			 
		} catch (IOException e) {
			fail("File not found");
		} catch (NoPuzzleAvailableException e) {
			fail("Puzzle not in library");
		} 
	}

}
