package lpf.gameLibrary;

import static org.junit.Assert.*;

import java.io.File;

import lpf.gameLibrary.SudokuXMLParser;
import lpf.model.core.Location;
import lpf.model.core.Puzzle;

import org.junit.Test;


/** Tests for the SudokuXMLParserClass
 * 
 * @author Eric Greer
 *
 */
public class SudokuXMLParserTest {
	File testXML = new File("files/puzzles/SamplePuzzle.xml");
	File badHeightTestXML = new File("files/puzzles/BadHeightSamplePuzzle.xml");
	File badWidthTestXML = new File("files/puzzles/BadWidthSamplePuzzle.xml");
	File badBoardNameTestXML = new File("files/puzzles/BadBoardNamingSamplePuzzle.xml");
	File notXMLTest = new File("files/Sample.txt");
	

	@Test
	public void testCreateSodukuPuzzle() {
		//Test a valid XML Board
		Puzzle p = SudokuXMLParser.createSodukuPuzzle(testXML);
		assertNotNull(p);
		
		// Test the created puzzles properties for accuracy
		assertEquals(p.getDifficulty(), 2);
		assertEquals(p.getInitialGrid().height, 9);
		assertEquals(p.getInitialGrid().width, 9);
		assertEquals(p.getInitialGrid().getCellAtLocation(new Location(5, 'H')).getDigit().value, '9');
		assertEquals(p.getInitialGrid().getCellAtLocation(new Location(5, 'I')).getDigit(), null);
		
		//Test number of row tags doesn't equal height given
		assertNull(SudokuXMLParser.createSodukuPuzzle(badHeightTestXML));
		
		//Test number of row tags doesn't equal height given
		assertNull(SudokuXMLParser.createSodukuPuzzle(badWidthTestXML));
		
		//Test a board that violates the schema
		assertNull(SudokuXMLParser.createSodukuPuzzle(badBoardNameTestXML));
		
		//Test file that is not xml
		assertNull(SudokuXMLParser.createSodukuPuzzle(notXMLTest));
		
		
	}

}
