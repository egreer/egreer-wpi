package lpf.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import junit.framework.TestCase;

import lpf.gui.KenKenGUI;
import lpf.model.core.Grid;
import lpf.model.core.Location;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.fileManagement.FileLoader;
import lpf.model.kenken.fileManagement.PuzzleLibrary;

public class UndoRedoListenerTest extends TestCase {
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

		gui.setPuzzle(puzzle);
		gui.initiateCommandHistory();
	}

	/*
	 * Test for 'actionPerformed(ActionEvent e)'
	 */
	public void testActionPerformed() {
		Grid g = gui.getPuzzle().getPlayerGrid();
		gui.setCurrentCell(g.getCellAtLocation(new Location(4, 'C')));

		ValueListener listener;
		UndoListener undo = new UndoListener(gui);
		RedoListener redo = new RedoListener(gui);

		Location loc1 = new Location(1, 'A');
		gui.setCurrentCell(g.getCellAtLocation(loc1));
		listener = new ValueListener(gui, false);
		listener.actionPerformed(new ActionEvent(this, 1, "4"));
		assertEquals('4', gui.getCurrentCell().getDigit().value);

		Location loc2 = new Location(2, 'B');
		gui.setCurrentCell(g.getCellAtLocation(loc2));
		listener = new ValueListener(gui, false);
		listener.actionPerformed(new ActionEvent(this, 1, "1"));
		assertEquals('1', gui.getCurrentCell().getDigit().value);

		Location loc3 = new Location(3, 'A');
		gui.setCurrentCell(g.getCellAtLocation(loc3));
		listener = new ValueListener(gui, false);
		listener.actionPerformed(new ActionEvent(this, 1, "1"));
		assertEquals('1', gui.getCurrentCell().getDigit().value);

		Location loc4 = new Location(4, 'D');
		gui.setCurrentCell(g.getCellAtLocation(loc4));
		listener = new ValueListener(gui, false);
		listener.actionPerformed(new ActionEvent(this, 1, "2"));
		assertEquals('2', gui.getCurrentCell().getDigit().value);

		undo.actionPerformed(null);
		assertNull(gui.getPuzzle().getPlayerGrid().getCellAtLocation(loc4).getDigit());

		undo.actionPerformed(null);
		assertNull(gui.getPuzzle().getPlayerGrid().getCellAtLocation(loc4).getDigit());
		assertNull(gui.getPuzzle().getPlayerGrid().getCellAtLocation(loc3).getDigit());

		redo.actionPerformed(null);
		assertNull(gui.getPuzzle().getPlayerGrid().getCellAtLocation(loc4).getDigit());
		assertNotNull(gui.getPuzzle().getPlayerGrid().getCellAtLocation(loc3)
				.getDigit());

		RemoveIncorrectListener rmListener = new RemoveIncorrectListener(gui);
		rmListener.actionPerformed(null);

		// because remove incorrect was called, the undo and redo stacks
		// should be cleared
		redo.actionPerformed(null);
		assertNull(gui.getPuzzle().getPlayerGrid().getCellAtLocation(loc4).getDigit());
		assertNotNull(gui.getPuzzle().getPlayerGrid().getCellAtLocation(loc3).getDigit());
		
		undo.actionPerformed(null);
		assertNull(gui.getPuzzle().getPlayerGrid().getCellAtLocation(loc4).getDigit());
		assertNotNull(gui.getPuzzle().getPlayerGrid().getCellAtLocation(loc3)
				.getDigit());
		
		gui.setCurrentCell(g.getCellAtLocation(loc4));
		listener = new ValueListener(gui, false);
		listener.actionPerformed(new ActionEvent(this, 1, "2"));
		assertEquals('2', gui.getCurrentCell().getDigit().value);
	}
}
