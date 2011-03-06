package tutorial.independent;

import java.awt.event.MouseAdapter;

import ks.common.games.Solitaire;

/**
 * Base class defines relationship to controller that is
 * being 'decorated' with additional capabilities
 * 
 * @author George Heineman
 */
public abstract class ControllerDecorator extends MouseAdapter {
	/** The controller being decorated. */
	protected MouseAdapter decoratedController;

	/** The underlying solitaire game. */
	protected Solitaire game;
	
	/** Record the controller being decorated for game. */
	public ControllerDecorator (Solitaire game, MouseAdapter decoratedController) {
		this.decoratedController = decoratedController;
		this.game = game;
	}
	
	/** Record controller decorator from which the game can be computed. */
	public ControllerDecorator (ControllerDecorator decorator) {
		this.decoratedController = decorator;
		this.game = decorator.game;
	}
}