package lpf.commands;

import java.io.File;

import junit.framework.TestCase;

import lpf.model.core.Cell;
import lpf.model.core.Location;
import lpf.model.core.Value;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.fileManagement.FileLoader;

public class TestClearMarksCommand extends TestCase {
	public void testClearMarksCommand() {
		FileLoader fl;

		fl = new FileLoader(new File("puzzles/test/good/kenken_4x4_easy2.xml"));
		KenKenPuzzle puzzle = fl.getKenKenPuzzle();
		assertNotNull(puzzle);

		Location loc = new Location(2, 'B');
		Value val1 = new Value('3');
		Value val2 = new Value('7');
		Value val3 = new Value('1');
		
		Cell cell = puzzle.getPlayerGrid().getCellAtLocation(loc);

		cell.addMark(val1);
		cell.addMark(val2);
		cell.addMark(val3);

		Command c = new ClearMarksCommand(cell);

		c.perform();
		assertEquals(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.size(), 0);

		c.unperform();
		assertEquals(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.size(), 3);
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val1));
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val2));
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val3));

		c.perform();
		assertEquals(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.size(), 0);

		c.unperform();
		assertEquals(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.size(), 3);
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val1));
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val2));
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val3));

		c.unperform();
		assertEquals(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.size(), 3);
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val1));
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val2));
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val3));

		c.perform();
		assertEquals(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.size(), 0);

		c.perform();
		assertEquals(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.size(), 0);

		c.unperform();
		assertEquals(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.size(), 3);
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val1));
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val2));
		assertTrue(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.contains(val3));

		c.perform();
		assertEquals(puzzle.getPlayerGrid().getCellAtLocation(loc).getMarks()
				.size(), 0);
	}
}
