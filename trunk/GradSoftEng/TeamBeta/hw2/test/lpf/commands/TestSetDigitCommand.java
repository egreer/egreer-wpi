package lpf.commands;

import java.io.File;

import junit.framework.TestCase;

import lpf.model.core.Cell;
import lpf.model.core.Location;
import lpf.model.core.Value;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.fileManagement.FileLoader;

public class TestSetDigitCommand extends TestCase {
	public void testSetDigitCommand() {
		FileLoader fl;

		fl = new FileLoader(new File("puzzles/test/good/kenken_4x4_easy2.xml"));
		KenKenPuzzle puzzle = fl.getKenKenPuzzle();
		assertNotNull(puzzle);

		Location loc = new Location(2, 'B');
		Value val = new Value('2');
		Cell cell = puzzle.getPlayerGrid().getCellAtLocation(loc);
		Command c = new SetDigitCommand(cell, val);

		c.perform();
		assertEquals(cell.getDigit(), val);

		c.unperform();
		assertNull(cell.getDigit());

		c.perform();
		assertEquals(cell.getDigit(), val);

		c.unperform();
		assertNull(cell.getDigit());

		c.unperform();
		assertNull(cell.getDigit());

		c.perform();
		assertEquals(cell.getDigit(), val);

		c.perform();
		assertEquals(cell.getDigit(), val);

		c.unperform();
		assertNull(cell.getDigit());

		c.perform();
		assertEquals(cell.getDigit(), val);
	}
}