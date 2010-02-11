package lpf.configuration;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.zip.ZipFile;

import lpf.gameLibrary.IPuzzleLibrary;
import lpf.gameLibrary.SudokuGameLibrary;
import lpf.gameLibrary.SudokuLibraryLoader;
import lpf.model.core.Grid;
import lpf.model.core.Puzzle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameConfigurationTest {

	SudokuAlphaGameConfiguration config;
	
	@Before
	public void setUp() throws Exception {
		config = SudokuAlphaGameConfiguration.getInstance(); 
	}

	@After
	public void tearDown() throws Exception {
		config.difficulty = 2;
		config.curPuzzle = null;
	}

	@Test
	public void testGetPuzzleLibrary() {
		IPuzzleLibrary testLibrary = config.getPuzzleLibrary();
		assertSame(config.puzzleLibrary, testLibrary);
	}

	@Test
	public void testSetPuzzleLibrary() {
		SudokuGameLibrary testLib = new SudokuGameLibrary();
		config.setPuzzleLibrary(testLib);
		assertSame(testLib, config.puzzleLibrary);
		
		try{
			config.setPuzzleLibrary(null);
			fail("SetPuzzleLibrary should have thrown exception");
		} catch (IllegalArgumentException e){
			assertSame(testLib, config.puzzleLibrary);
		}
		
		//Reset the configuration
		try {
			SudokuAlphaGameConfiguration.getInstance().setPuzzleLibrary(SudokuLibraryLoader.loadLibrary(new ZipFile("files/sudokuLibrary.zip")));
		} catch (IOException e) {
			fail("should not have thrown exception");
		}
	}

	@Test
	public void testGetDifficulty() {
		assertEquals(config.difficulty, config.getDifficulty());
	}

	@Test
	public void testSetDifficulty() {
		config.setDifficulty(6);
		assertEquals(6, config.difficulty);
		
		try{
			config.setDifficulty(0);
			fail("SetDifficulty should have thrown exception for difficulty 0");
		} catch (IllegalArgumentException e){
			assertEquals(6, config.difficulty);
		}
		
		try{
			config.setDifficulty(11);
			fail("SetDifficulty should have thrown exception for difficulty 11");
		}catch (IllegalArgumentException e){
			assertEquals(6, config.difficulty);
		}
	}

	@Test
	public void testGetInstance() {
		assertNotNull(config.puzzleLibrary);
		assertEquals(2, config.difficulty);
		assertNull(config.curPuzzle);
		assertSame(config, SudokuAlphaGameConfiguration.myInstance);
		assertSame(config, SudokuAlphaGameConfiguration.getInstance());
	}

	@Test
	public void testGetCurrentPuzzle() {
		Puzzle p = new Puzzle(new Grid(2,2));
		config.setPuzzle(p);
		assertSame(config.curPuzzle, config.getCurrentPuzzle());
	}

	@Test
	public void testSetPuzzle() {
		//Test valid puzzle
		Puzzle p = new Puzzle(new Grid(2,2));
		config.setPuzzle(p);
		assertSame(p, config.curPuzzle);
	}


	@Test
	public void testGetCharacterSet() {
		assertSame(config.characterSet, config.getCharacterSet());
	}
}
