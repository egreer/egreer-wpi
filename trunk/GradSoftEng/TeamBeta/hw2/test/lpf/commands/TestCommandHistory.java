package lpf.commands;

import java.io.File;

import junit.framework.TestCase;

import lpf.model.core.Cell;
import lpf.model.core.Location;
import lpf.model.core.Value;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.fileManagement.FileLoader;

public class TestCommandHistory extends TestCase {
	public void testCommandHistory() {
		CommandHistory hist = new CommandHistory();

		FileLoader fl = new FileLoader(new File(
				"puzzles/test/good/kenken_4x4_easy2.xml"));
		KenKenPuzzle puzzle = fl.getKenKenPuzzle();
		assertNotNull(puzzle);

		Location loc = new Location(2, 'B');

		Cell cell = puzzle.getPlayerGrid().getCellAtLocation(loc);

		Value val1 = new Value('2');
		Value val2 = new Value('3');
		Value val3 = new Value('4');
		Value val4 = new Value('7');

		assertEquals(cell.getMarks().size(), 0);

		hist.perform(new AddMarkCommand(cell, val1));
		assertEquals(cell.getMarks().size(), 1);
		assertTrue(cell.getMarks().contains(val1));

		hist.perform(new AddMarkCommand(cell, val2));
		assertEquals(cell.getMarks().size(), 2);
		assertTrue(cell.getMarks().contains(val1));
		assertTrue(cell.getMarks().contains(val2));

		hist.perform(new AddMarkCommand(cell, val3));
		assertEquals(cell.getMarks().size(), 3);
		assertTrue(cell.getMarks().contains(val1));
		assertTrue(cell.getMarks().contains(val2));
		assertTrue(cell.getMarks().contains(val3));

		hist.perform(new ClearMarksCommand(cell));
		assertEquals(cell.getMarks().size(), 0);

		hist.perform(new AddMarkCommand(cell, val4));
		assertEquals(cell.getMarks().size(), 1);
		assertTrue(cell.getMarks().contains(val4));

		assertFalse(hist.redo());

		assertTrue(hist.undo());
		assertEquals(cell.getMarks().size(), 0);

		assertTrue(hist.undo());
		assertEquals(cell.getMarks().size(), 3);
		assertTrue(cell.getMarks().contains(val1));
		assertTrue(cell.getMarks().contains(val2));
		assertTrue(cell.getMarks().contains(val3));

		assertTrue(hist.undo());
		assertEquals(cell.getMarks().size(), 2);
		assertTrue(cell.getMarks().contains(val1));
		assertTrue(cell.getMarks().contains(val2));

		assertTrue(hist.undo());
		assertEquals(cell.getMarks().size(), 1);
		assertTrue(cell.getMarks().contains(val1));

		assertTrue(hist.undo());
		assertEquals(cell.getMarks().size(), 0);

		assertFalse(hist.undo());

		assertTrue(hist.redo());
		assertEquals(cell.getMarks().size(), 1);
		assertTrue(cell.getMarks().contains(val1));

		assertTrue(hist.redo());
		assertEquals(cell.getMarks().size(), 2);
		assertTrue(cell.getMarks().contains(val1));
		assertTrue(cell.getMarks().contains(val2));

		assertTrue(hist.redo());
		assertEquals(cell.getMarks().size(), 3);
		assertTrue(cell.getMarks().contains(val1));
		assertTrue(cell.getMarks().contains(val2));
		assertTrue(cell.getMarks().contains(val3));

		assertTrue(hist.redo());
		assertEquals(cell.getMarks().size(), 0);

		assertTrue(hist.undo());
		assertEquals(cell.getMarks().size(), 3);
		assertTrue(cell.getMarks().contains(val1));
		assertTrue(cell.getMarks().contains(val2));
		assertTrue(cell.getMarks().contains(val3));

		assertTrue(hist.undo());
		assertEquals(cell.getMarks().size(), 2);
		assertTrue(cell.getMarks().contains(val1));
		assertTrue(cell.getMarks().contains(val2));

		assertTrue(hist.redo());
		assertEquals(cell.getMarks().size(), 3);
		assertTrue(cell.getMarks().contains(val1));
		assertTrue(cell.getMarks().contains(val2));
		assertTrue(cell.getMarks().contains(val3));

		hist.perform(new AddMarkCommand(cell, val4));
		assertFalse(hist.redo());
		assertEquals(cell.getMarks().size(), 4);
		assertTrue(cell.getMarks().contains(val1));
		assertTrue(cell.getMarks().contains(val2));
		assertTrue(cell.getMarks().contains(val3));
		assertTrue(cell.getMarks().contains(val4));
	}
}
