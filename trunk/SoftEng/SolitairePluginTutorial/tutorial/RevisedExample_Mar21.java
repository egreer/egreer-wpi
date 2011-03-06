package tutorial;

import java.awt.Color;

import ks.client.gamefactory.GameWindow;
import ks.common.games.Solitaire;
import ks.launcher.Main;

import tutorial.mar21.*;

/**
 * Smallest meaningful solitaire variation. 
 * <p>
 * Launch this class as an application.
 * 
 * @author George Heineman 
 */
public class RevisedExample_Mar21 extends Solitaire {

	RectangleElement rectangle;	
	RectangleView rectangleView;
	
	/** Return the name of this solitaire variation. */
	public String getName() {
		return "InClassExample";
	}

	/**
	 * Initialize the Model for Narcotic.
	 *
	 * @param seed    used to shuffle identical decks.
	 */
	private void initializeModel(int seed) {
		rectangle = new RectangleElement ("default", Color.red);
		addModelElement(rectangle);
	}

	/**
	 * Prepare the views
	 */
	private void initializeView() {
		rectangleView = new RectangleView (rectangle);
		rectangleView.setBounds(20, 20, 300, 300);
		addViewWidget(rectangleView);		
	}

	/** Determine whether game has been won. */
	public boolean hasWon() {
		return false; // not ready for this yet.
	}

	/** Initialize solitaire variation. */
	public void initialize() {
		// Initialize model, view, and controllers.
		initializeModel(getSeed());
		initializeView();
		initializeController();
	}
	
	private void initializeController() {
		rectangleView.setMouseAdapter(new RectangleController(this, rectangleView));
	}

	/** Code to launch solitaire variation. */
	public static void main (String []args) {
		// Seed is to ensure we get the same initial cards every time.
		GameWindow gw = Main.generateWindow(new RevisedExample_Mar21(), 117);
		gw.setVisible(true);
	}
}
