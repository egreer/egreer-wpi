package amida;

import amida.gui.AmidaFrame;

/**
 * Use this class to Launch the GUI application.
 * 
 * Note that the information contained within this class is not required knowledge for CS2102
 * 
 * The only line you need to modify is the line marked "MODIFY HERE"
 * 
 * @author heineman
 */
public class LaunchGUI {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// The only line you need to modify. Replace "solution.Amida" with the name of
		// your class and its defining package. Again, let me repeat that you do not 
		// need to know any of this information. I am providing this GUI code to you
		// to help motivate you to complete the assignment, knowing this is a great
		// way to try your code out.
		AmidaInterface board = new hw5.Amida();
		
		// create Frame.
		AmidaFrame frame = new AmidaFrame (board);
		frame.setSize(600, 600);
		frame.setVisible(true);		
	}

}
