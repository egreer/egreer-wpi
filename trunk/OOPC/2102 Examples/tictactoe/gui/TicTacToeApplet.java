package tictactoe.gui;


import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.List;
import java.awt.TextArea;

/**
 * GUI for TicTacToe.
 */
public class TicTacToeApplet extends Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3544825863116215467L;

	/** Controller for managing the game play. */
	PlayGameController controller;
	
	/** GUI information. */
	List gameTypes;
	TextArea output;
	
	/**
	 * Every applet has an init() method to initialize itself
	 */
	public void init() {
		// Tell Applet that we will take full responsibility for layout
		setLayout (null);
		
		Label l = new Label ("Select Game Type:");
		l.setBounds (220, 5, 140, 20);
		add (l);
		
		gameTypes = new List (5);
		populateGames (gameTypes);
		gameTypes.setBounds(220,30,140,90);
		add (gameTypes);
		
		// Output information for status, scores, etc....
		output = new TextArea (6,30);
		output.setBounds (10, 230, 200, 120);
		add (output);
		
		// Take Action
		Button playButton = new Button ("New Game");
		playButton.setBounds (255, 280, 93, 20);
		add (playButton);
		
		// Tell Play about the proper controller
		controller = new PlayGameController (this);
		playButton.addActionListener(controller);
		addMouseListener (controller);
		
		// set our size.
		setSize (400,400);
	}
	
	/**
	 * Update List to contain all game types.
	 * @param gameTypes
	 */
	private void populateGames(List gameTypes) {
		//gameTypes.add("Annihilate");
		//gameTypes.add("Slide");
		gameTypes.add("Straight");
	}

	/**
	 * Every applet has a paint(g) method in which it can paint
	 * things.
	 * 
	 * We paint the current board state by appealing to the controller.
	 * 
	 * @param g   Graphics object into which to draw.
	 */
	public void paint (Graphics g) {
		controller.drawBoard(g);
	}

	/**
	 * Returns the game type that was selected.
	 * 
	 * @return  String representing the current game type selected (or null of none).
	 */
	public String getSelectedGameType() {
		return gameTypes.getSelectedItem();
	}

	/**
	 * Appends output to the output text area.
	 * 
	 * @param string    string to be appended to the text area.
	 */
	public void output(String string) {
		output.append(string + "\n");		
	}

}
