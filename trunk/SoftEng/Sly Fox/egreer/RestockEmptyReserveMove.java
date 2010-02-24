package egreer;

import ks.common.games.Solitaire;
import ks.common.model.*;

/**
 * Represents moving of a card from the Deck to an empty reserve pile in phase 1. 
 * 
 * @author Eric Greer
 *
 */

public class RestockEmptyReserveMove extends ks.common.model.Move{

	/** From and To piles. Phase of the game*/
	protected Deck from = null;
	protected Column to = null;
	protected int phase = -1;
	
	/** Constructor for RestockEmptyReserveMove 
	 * 
	 * @param from	The deck which the card is dealt from 
	 * @param to	The Empty pile to fill
	 * @param phase	Gives the current phase of the game, and stores it
	 */
	
	public RestockEmptyReserveMove (Deck from, Column to, int phase) {
		super();
		
		this.from = from;
		this.to = to;
		this.phase = phase;
	}
	
	
	/**
	 * Each move knows how to execute itself.
	 * 
	 * @param ks.common.game.Solitaire   the current game being played.
	 * @return boolean
	 */
	public boolean doMove (Solitaire currentGame) {
		// Checks the validity
		if (valid(currentGame) == false) {
			return false;
		}
		
		// Does move
		to.add (from.get());
		currentGame.updateNumberCardsLeft(-1);
		
		return true;
	}
	
	/** This move can be undone by moving the placed card back to the deck
	 * @param theGame ks.common.games.Solitaire
	 */
	public boolean undo(Solitaire game) {
		// Make sure trying not to move nothing 
		if (to.empty()) return false;

		// move card back
		from.add (to.get());
		game.updateNumberCardsLeft(1);
		return true;
	}

	
	/**
	 * Validates RestockEmptyReserveMove
	 * @param game 
	 */
	public boolean valid(Solitaire currentGame){
		// Default to not valid move
		boolean isValid = false;
		
		if ((phase == 1) && to.empty() && !from.empty() ) {
			isValid = true;
		}

		return isValid;
	}
	
}
