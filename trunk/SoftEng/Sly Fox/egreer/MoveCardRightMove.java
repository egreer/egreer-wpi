package egreer;

import ks.common.games.Solitaire;
import ks.common.model.*;

/**
 * Represents moving of a card from a reserve column to one of the right foundation piles.  
 * 
 * @author Eric Greer
 */

public class MoveCardRightMove extends ks.common.model.Move{

	/** From and To piles. */
	protected Column from;
	protected Pile to;

	/** The card being moved */
	protected Card movingCard;
	
	
	/**
	 * MoveCardRightMove constructor.
	 * 
	 * @param from         The source column from which the move begins
	 * @param movingCard   while the move is in progress this is the card being dragged 
	 * @param to           The target Foundation Pile for the move.
	 */
	public MoveCardRightMove (Column from, Card movingCard, Pile to) {
		super();

		this.from = from;
		this.movingCard = movingCard;
		this.to = to;
	}
	
	
	/**
	 * Each move knows how to execute itself.
	 * 
	 * @param currentGame  The current game being played.
	 * @return boolean
	 */
	public boolean doMove (Solitaire currentGame) {
		// Checks the validity
		if (valid(currentGame) == false) {
			return false;
		}
		
		// Does move
		to.add (movingCard);
		currentGame.updateScore(1);
		
		return true;
	}
	
	
	/** This move can be undone by moving the top card of the to Pile back to the from Column
	 * @param theGame	The current game being played.
	 */
	public boolean undo(Solitaire game) {
		// Make sure trying not to move nothing 
		if (to.empty()) return false;

		// Move card back to from pile set score back 1
		from.add (to.get());
		game.updateScore(-1);
		return true;
	}
	
	/**
	 * Validates MoveCardRightMove
	 * @param game ks.common.games.Solitaire
	 */
	public boolean valid(Solitaire currentGame){
		// Default to not valid move
		boolean isValid = false;
		
		//Makes sure the suits are the same, also make sure the moving card is directly less then the foundation 
		if ((to.suit() == movingCard.getSuit()) && (to.peek().compareTo(movingCard) == 1 )) {
			isValid = true;
		}

		return isValid;
	}
}
