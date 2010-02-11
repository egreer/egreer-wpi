package lpf.commands;

import java.io.File;

import junit.framework.TestCase;

import lpf.model.core.Cell;
import lpf.model.core.Location;
import lpf.model.core.Value;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.fileManagement.FileLoader;

public class TestAddMarkCommand extends TestCase {
	public void testAddMarkCommand() {
		FileLoader fl;

		fl = new FileLoader(new File("puzzles/test/good/kenken_4x4_easy2.xml"));
		KenKenPuzzle puzzle = fl.getKenKenPuzzle();
		assertNotNull(puzzle);

		Location loc = new Location(2, 'B');
		Value val = new Value('2');
		Cell cell = puzzle.getPlayerGrid().getCellAtLocation(loc);
		Command c = new AddMarkCommand(cell, val);

		c.perform();
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val));

		c.unperform();
		assertFalse(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val));

		c.perform();
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val));

		c.unperform();
		assertFalse(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val));

		c.unperform();
		assertFalse(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val));

		c.perform();
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val));

		c.perform();
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val));

		c.unperform();
		assertFalse(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val));

		c.perform();
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val));
	}
}
