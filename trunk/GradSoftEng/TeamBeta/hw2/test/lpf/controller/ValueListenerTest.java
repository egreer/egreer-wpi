package lpf.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import junit.framework.TestCase;
import lpf.gui.KenKenGUI;
import lpf.model.core.Grid;
import lpf.model.core.Location;
import lpf.model.core.Value;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.fileManagement.FileLoader;
import lpf.model.kenken.fileManagement.PuzzleLibrary;

public class ValueListenerTest extends TestCase {
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

		g.getCellAtLocation(new Location(1, 'A')).setDigit(new Value('4'));
		g.getCellAtLocation(new Location(1, 'B')).setDigit(new Value('3'));
		g.getCellAtLocation(new Location(1, 'C')).setDigit(new Value('2'));
		g.getCellAtLocation(new Location(1, 'D')).setDigit(new Value('1'));

		g.getCellAtLocation(new Location(2, 'A')).setDigit(new Value('2'));
		g.getCellAtLocation(new Location(2, 'B')).setDigit(new Value('1'));
		g.getCellAtLocation(new Location(2, 'C')).setDigit(new Value('3'));
		g.getCellAtLocation(new Location(2, 'D')).setDigit(new Value('4'));

		g.getCellAtLocation(new Location(3, 'A')).setDigit(new Value('1'));
		g.getCellAtLocation(new Location(3, 'B')).setDigit(new Value('2'));
		g.getCellAtLocation(new Location(3, 'C')).setDigit(new Value('4'));
		g.getCellAtLocation(new Location(3, 'D')).setDigit(new Value('3'));

		g.getCellAtLocation(new Location(4, 'A')).setDigit(new Value('3'));
		g.getCellAtLocation(new Location(4, 'B')).setDigit(new Value('4'));
		g.getCellAtLocation(new Location(4, 'C')).setDigit(new Value('1'));
		// missing value is 2 at (4,D)

		gui.setPuzzle(puzzle);
		gui.initiateCommandHistory();
	}

	/*
	 * Test for 'actionPerformed(ActionEvent e)'
	 */
	public void testActionPerformed() {
		Grid g = gui.getPuzzle().getPlayerGrid();
		gui.setCurrentCell(g.getCellAtLocation(new Location(4, 'C')));

		// remove value for (3,C)
		ValueListener listener = new ValueListener(gui, true);
		listener.actionPerformed(new ActionEvent(this, 1, ""));
		assertNull(gui.getCurrentCell().getDigit());
		
		// add value back for (3,C)
		listener = new ValueListener(gui, false);
		listener.actionPerformed(new ActionEvent(this, 1, "1"));
		assertEquals('1',gui.getCurrentCell().getDigit().value);
		
		// add final value and win the puzzle
		gui.setCurrentCell(g.getCellAtLocation(new Location(4, 'D')));
		listener = new ValueListener(gui, false);
		listener.actionPerformed(new ActionEvent(this, 1, "2"));
		assertEquals('2',gui.getCurrentCell().getDigit().value);
	}
}
