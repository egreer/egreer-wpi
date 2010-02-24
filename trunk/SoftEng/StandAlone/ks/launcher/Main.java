package ks.launcher;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ks.client.gamefactory.GameWindow;
import ks.common.games.Solitaire;

public class Main {
	
	/** Temporary function to be able to launch Solitaire window. */
	public static GameWindow generateWindow(Solitaire theGame, int seed) {
		final GameWindow itg = new GameWindow();

		itg.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	itg.setVisible(false);
		    	itg.dispose();
		    }
		});
		
		// make visible (to enable downloading of images?)
		itg.setVisible(true);
		itg.setTitle(theGame.getName());

		// initialize our game within this game window.
		theGame.setSeed(seed);
		theGame.setIncrement(117);

		// prepare game (note: initially the game is inactive waiting for all
		// users to join). Note that the game is not going to be really active
		// until all users have joined the table.
		itg.initialize(theGame);
		
		// ONLY INITIALIZE ONCE GAME IS REALLY READY TO PROCEED!
		return itg;
	}

}
