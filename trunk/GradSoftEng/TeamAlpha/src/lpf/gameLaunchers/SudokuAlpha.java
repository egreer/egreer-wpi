package lpf.gameLaunchers;


import lpf.GUIs.SudokuAlphaMainGUI;
import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.controllers.NewGameController;
import lpf.controllers.ProcessFailedException;

/**
 * This class launches the SudokuAlpha Version of a Grid Game.
 * @author Nik Deapen
 * @since 1.0.0
 */
public class SudokuAlpha extends GridGame {

	public static final String name = "Sudoku Alpha"; 
	SudokuAlphaGameConfiguration config = SudokuAlphaGameConfiguration.getInstance();
	
	public SudokuAlpha() {
		super(name);
	}

	
	/**
	 * Launches the GUI with a new game
	 */
	@Override
	public void run() {
		// launch the gui
		
		SudokuAlphaMainGUI gui = new SudokuAlphaMainGUI();	
		SudokuAlphaGameConfiguration.getInstance().setGUI(gui);
		
		// launch a new game
		try {
			new NewGameController().process();
		} catch (ProcessFailedException e) {
			
		}
			
	}

}
