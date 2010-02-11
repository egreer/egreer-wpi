package lpf.commands;

import java.io.File;

import junit.framework.TestCase;

import lpf.model.core.Cell;
import lpf.model.core.Location;
import lpf.model.core.Value;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.fileManagement.FileLoader;

public class TestRemoveMarkCommand extends TestCase {
	public void testRemoveMarkCommand() {
		FileLoader fl;

		fl = new FileLoader(new File("puzzles/test/good/kenken_4x4_easy2.xml"));
		KenKenPuzzle puzzle = fl.getKenKenPuzzle();
		assertNotNull(puzzle);

		Location loc = new Location(2, 'B');
		Value val = new Value('2');
		Cell cell = puzzle.getPlayerGrid().getCellAtLocation(loc);
		cell.addMark(val);
		Command c = new RemoveMarkCommand(cell, val);

		c.perform();
		assertFalse(cell.getMarks().contains(val));

		c.unperform();
		assertTrue(cell.getMarks().contains(val));

		c.perform();
		assertFalse(cell.getMarks().contains(val));

		c.unperform();
		assertTrue(cell.getMarks().contains(val));

		c.unperform();
		assertTrue(cell.getMarks().contains(val));

		c.perform();
		assertFalse(cell.getMarks().contains(val));

		c.perform();
		assertFalse(cell.getMarks().contains(val));

		c.unperform();
		assertTrue(cell.getMarks().contains(val));

		c.perform();
		assertFalse(cell.getMarks().contains(val));
	}
}