package lpf;

import lpf.gameLaunchers.SudokuAlpha;


/**
 * Provides an easy way to launch the game
 * @author Nik Deapen
 */
public class QuickLaunch {
	
	/**
	 * Runs the program
	 * @param args - nothing is read from args
	 */
	public static void main(String[] args){
		new SudokuAlpha().run();
	}
}