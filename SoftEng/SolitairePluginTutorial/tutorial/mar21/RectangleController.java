package tutorial.mar21;

import ks.common.games.Solitaire;
import ks.common.model.Move;

/**
 * RectangleView controller.
 * 
 * @author George T. Heineman (heineman@cs.wpi.edu)
 */
public class RectangleController extends java.awt.event.MouseAdapter {
	/** The RectangleView widget being controlled. */
	RectangleView rectView;
	
	/** Solitaire game being played. */
	Solitaire theGame;

	/** RectangleController constructor comment. */
	public RectangleController(Solitaire game, RectangleView rectView) {
		super();

		this.theGame = game;
		this.rectView = rectView;
	}

	/**
	 * On mouse pressed, switch color.
	 */
	public void mousePressed(java.awt.event.MouseEvent me) {
		/** Recover RectangleElement. */
		RectangleElement rect = (RectangleElement) rectView.getModelElement();

		// Try to make the move
		Move m = new UpdateColorMove (rect);
		if (m.doMove (theGame)) {
			// SUCCESS
			theGame.pushMove (m);
		} 

		theGame.refreshWidgets(); 
	}

}
