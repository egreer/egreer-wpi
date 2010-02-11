package lpf.GUIs.GridView;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import lpf.GUIs.GridView.controllers.ChangeSelectedLocationController;
import lpf.GUIs.GridView.controllers.CharacterEnteredController;
import lpf.GUIs.GridView.controllers.DeleteController;
import lpf.GUIs.GridView.controllers.GridClickedController;
import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.controllers.HintController;
import lpf.controllers.ProcessFailedException;
import lpf.controllers.RedoController;
import lpf.controllers.UndoController;
import lpf.model.core.Value;

/**
 * 
 * An event handler for the Grid
 * 
 * @author Nik Deapen
 *
 */
public class GridEventHandler implements MouseListener, KeyListener, FocusListener {

	SudokuAlphaGameConfiguration config = SudokuAlphaGameConfiguration.getInstance();
	PuzzleManager gridManager;
	GridView gridView;
	
	/**
	 * Creates a new GridEventHandler
	 * 
	 * @param gridManager			the grid's manager
	 * @param gridView				the grid's view
	 */
	public GridEventHandler(PuzzleManager gridManager, GridView gridView) {
		this.gridManager = gridManager;
		this.gridView = gridView;
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	/**
	 * Create a GridClickedController for when a Cell is clicked on the Grid
	 * 
	 * @param mouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		// request Focus
		gridView.requestFocus();
		
		// handle the event by sending it to a controller
		try {
			new GridClickedController(gridManager, mouseEvent).process();
		} catch (ProcessFailedException e) {
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
 
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	
	}

	/**
	 * Gets the keyboard input, while providing specific maps for Ctrl+key
	 * 
	 * @param ke
	 */
	@Override
	public void keyPressed(KeyEvent ke) {
	
		int keyCode = ke.getKeyCode();
		char keyChar = ke.getKeyChar();
		
		// handle control key 
		if (ke.getModifiers() == 2) {
			int key = ke.getKeyCode();
			switch (key) {
				case 77: // ctrl m
					gridManager.setMarkMode(true);
					break;
				case 68: // ctrl d
					gridManager.setMarkMode(false);
					break;
				case 72: // ctrl h
					try {
						// give a hint
						new HintController(config.getCurrentPuzzle()).process();
					} catch (ProcessFailedException e) {}
					break;
				case 90: // ctrl z
					try {
						new UndoController().process();
					} catch (ProcessFailedException e) {}
					break;
				case 89: // ctrl y
					try {
						new RedoController().process();
					} catch (ProcessFailedException e) {}
			}
			
			gridView.repaint();
			return;
		}
		
		// take in the character set
		if (config.getCharacterSet().contains(new Value(keyChar))){
			try {
				new CharacterEnteredController(gridManager,new Value(keyChar)).process();
			} catch (ProcessFailedException e) {
				
			}
		}
			
		// take the change location 
		try {
			switch(keyCode){
				case 37: // left
					new ChangeSelectedLocationController(gridManager,ChangeSelectedLocationController.LEFT).process();
					break;
				case 38: // up
					new ChangeSelectedLocationController(gridManager,ChangeSelectedLocationController.ABOVE).process();
					break;
				case 39: // right
					new ChangeSelectedLocationController(gridManager,ChangeSelectedLocationController.RIGHT).process();
					break;
				case 40: // down
					new ChangeSelectedLocationController(gridManager,ChangeSelectedLocationController.BELOW).process();
					break;
			}
		}
		catch (Exception e){
		}
		
		// handle deletions
		switch (keyCode){
			case 127: // delete
			case 8: // backspace
				new DeleteController(gridManager).process();
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {}

	@Override
	public void focusLost(FocusEvent arg0) {
		this.gridView.requestFocus();
	}

}
