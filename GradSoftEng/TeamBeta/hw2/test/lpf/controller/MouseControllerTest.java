package lpf.controller;


import java.awt.event.MouseEvent;

import junit.framework.TestCase;
import lpf.gui.KenKenGUI;
import lpf.model.core.Cell;
import lpf.model.core.Location;
import lpf.model.kenken.KenKenPuzzle;
import lpf.model.kenken.preferences.Difficulty;
import lpf.model.kenken.preferences.KenKenPreference;

import org.junit.Before;

public class MouseControllerTest extends TestCase {
	
	KenKenGUI gui;
	KenKenPuzzle puzzle;

	@Before
	public void setUp() throws Exception {
		KenKenPreference pref = new KenKenPreference();
		pref.clearPreferences();
		pref.setPuzzleLibraryLocation("puzzles/test/good/valid_library1.zip");
		pref.setPreferredDifficulty(Difficulty.EASY);
		pref.setPreferredSize(4);
		
		
		gui = new KenKenGUI();
		gui.setPreference(pref);
		
		puzzle = gui.getPuzzle();
		gui.buildNewGame(gui.getPuzzle());
		gui.setVisible(true);
	}
	
	public void test()
	{
		int dx = 0;
		int dy = 0;
		
		MouseController controller = new MouseController(gui);
		MouseEvent me = new MouseEvent(gui, MouseEvent.MOUSE_RELEASED,
                System.currentTimeMillis(), 0,
                gui.getX()+ dx, gui.getY()+ dy, 1, false);
		controller.mouseClicked(me);
		assertNull(gui.getCurrentCell());
		
		dx = 150;
		dy = 50;
		me = new MouseEvent(gui, MouseEvent.MOUSE_CLICKED,
                System.currentTimeMillis(), 0,
                gui.getX()+ dx, gui.getY()+ dy, 1, false);
		controller.mousePressed(me);
		
		Cell cell = new Cell(new Location(1, 'B'));
		assertNotNull(gui.getCurrentCell());
		assertEquals(gui.getCurrentCell(), cell);
		
		dx = 50;
		dy = 50;
		me = new MouseEvent(gui, MouseEvent.MOUSE_CLICKED,
                System.currentTimeMillis(), 0,
                gui.getX()+ dx, gui.getY()+ dy, 1, false);
		controller.mouseReleased(me);
		
		cell = new Cell(new Location(1, 'A'));
		assertNotNull(gui.getCurrentCell());
		assertEquals(gui.getCurrentCell(), cell);
		
			
		dx = 250;
		dy = 150;
		me = new MouseEvent(gui, MouseEvent.MOUSE_CLICKED,
                System.currentTimeMillis(), 0,
                gui.getX()+ dx, gui.getY()+ dy, 1, false);
		
		controller.mouseMoved(me);
		
		cell = new Cell(new Location(2, 'C'));
		assertNotNull(gui.getCurrentCell());
		assertEquals(gui.getCurrentCell(), cell);
		
		dx = 1600;
		dy = 1600;
		me = new MouseEvent(gui, MouseEvent.MOUSE_CLICKED,
                System.currentTimeMillis(), 0,
                gui.getX()+ dx, gui.getY()+ dy, 1, false);
		
		controller.mouseDragged(me);
		
		cell = new Cell(new Location(1, 'A'));
		assertNull(gui.getCurrentCell());
		
		controller.mouseEntered(me);
		assertNull(gui.getCurrentCell());
		
		controller.mouseExited(me);
		assertNull(gui.getCurrentCell());
		
	}
}
