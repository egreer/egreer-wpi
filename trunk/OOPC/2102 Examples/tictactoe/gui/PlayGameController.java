package tictactoe.gui;


import java.awt.*;
import java.awt.event.*;

import tictactoe.Space;
import tictactoe.TicTacToe;

/**
 * Controller that responds to "PlayButton" press.
 */
public class PlayGameController implements ActionListener, MouseListener {
	/** Remember the applet so we can get information from the GUI. */
	TicTacToeApplet applet;
	
	/** Game started? */
	boolean started = false;

	/** Know the drawer. */
	Drawer drawer;

	/** The game to play. */
	private TicTacToe game;
	
	/** 
	 * Starts the game.
	 */
	public void startGame() {
		game = new TicTacToe();
		applet.output(game.getCurrentPlayer() + " Make your Move.");
		started = true;
	}	
	/**
	 * Controller will need applet to carry out its duties.
	 * 
	 * @param applet
	 */
	public PlayGameController (TicTacToeApplet applet) {
		this.applet = applet;
		this.drawer = new Drawer();
	}
	
	
	/**
	 * Respond to when user pressed on the PlayButton.
	 * @param e    the Event that initiates the play of a game.
	 */
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		String gameType = applet.getSelectedGameType();

		startGame();
		
		// refresh view
		applet.repaint();
	}

	
    /** Draw the board. */
    public void drawBoard(Graphics g) {
    	drawer.drawBoard(g, game);
    }
    
	/**
	 * React only to MouseClicked Events (where user presses and
	 * releases).
	 * 
	 * Can't be called until after the mousePlayer has been properly
	 * initialized.
	 * 
	 * @param  e    the MouseEvent created by user.
	 */
	public void mouseClicked(MouseEvent e) {
		if (!started) {
			Toolkit.getDefaultToolkit().beep();
			return;
		}
		
		int x = e.getX();
		int y = e.getY();

		// invert the calculations from drawSpot()
		Space space = drawer.interpretXY (x, y);
		if (!game.makeMove(space.getRow(), space.getColumn())) {
			applet.output ("Illegal move. Try again!");
			return;
		}
		
		// refresh to ensure that move is visible again.
		applet.repaint();
		
		if (game.isGameOver()) {
			applet.output (game.getFinalStatus());
			started = false;  // done with game.
		} else {
			applet.output(game.getCurrentPlayer() + " Make your Move.");
		}
	}

	/** Ignore the following since they are not part of the solution. */
	/** They need to be here BECAUSE we are implementing an interface. */
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
