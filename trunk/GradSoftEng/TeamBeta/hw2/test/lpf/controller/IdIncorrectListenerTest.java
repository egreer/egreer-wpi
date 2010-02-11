package lpf.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import junit.framework.TestCase;
import lpf.gui.KenKenGUI;
import lpf.model.core.Cell;
import lpf.model.core.Grid;
import lpf.model.core.Location;
import lpf.model.core.Value;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.fileManagement.FileLoader;
import lpf.model.kenken.fileManagement.PuzzleLibrary;

public class IdIncorrectListenerTest extends TestCase {
	ArrayList<Location> locs = new ArrayList<Location>();
	KenKenGUI gui;

	public void setUp() {
		gui = new KenKenGUI();
		AboutListener listener = new AboutListener(gui);
		assertNotNull(listener.gui);

		gui.setPuzzleLibrary(new PuzzleLibrary(new File(
				"puzzles/test/good/valid_library1.zip")));
		FileLoader fl = new FileLoader(new File(
				"puzzles/test/good/kenken_4x4_med2.xml"));
		KenKenPuzzle puzzle = fl.getKenKenPuzzle();

		Grid g = puzzle.getPlayerGrid();

		// Correct
		g.getCellAtLocation(new Location(1, 'A')).setDigit(new Value('4'));
		g.getCellAtLocation(new Location(2, 'B')).setDigit(new Value('1'));
		g.getCellAtLocation(new Location(3, 'A')).setDigit(new Value('1'));
		g.getCellAtLocation(new Location(4, 'D')).setDigit(new Value('2'));

		// Incorrect
		Location loc1 = new Location(1, 'C');
		Location loc2 = new Location(2, 'A');
		Location loc3 = new Location(3, 'D');
		Location loc4 = new Location(4, 'B');
		locs.add(loc1);
		locs.add(loc2);
		locs.add(loc3);
		locs.add(loc4);
		g.getCellAtLocation(loc1).setDigit(new Value('4'));
		g.getCellAtLocation(loc2).setDigit(new Value('3'));
		g.getCellAtLocation(loc3).setDigit(new Value('2'));
		g.getCellAtLocation(loc4).setDigit(new Value('1'));

		gui.setPuzzle(puzzle);
	}

	/*
	 * Test for 'actionPerformed(ActionEvent e)'
	 */
	public void testActionPerformed() {
		IdIncorrectListener listener = new IdIncorrectListener(gui);

		listener.actionPerformed(new ActionEvent(this, 1, ""));

		assertEquals(4, gui.getPuzzle().getIdentifiedIncorrectCells().size());
		for (Cell cell : gui.getPuzzle().getIdentifiedIncorrectCells()) {
			assertTrue(locs.contains(cell.loc));
		}
	}
}
