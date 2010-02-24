package darrent;

import ks.common.model.*;
import ks.common.games.Solitaire;

/**
 * Represents the move of a card from FreeCell to the Base.
 * <p>
 * This move might trigger auto-generated moves
 */
public class FreeCellBaseMove extends ks.common.model.Move {

	/** Destination. */
	private Pile basePile;

	/** Card being moved (or null if a programmed move). */
	private Card movingCard;

	/** Source. */
	private Pile fromPile;

	/**
	 * FreeCellBaseMove constructor comment.
	 */
	public FreeCellBaseMove(Pile fromPile, Card movingCard, Pile basePile) {
		super();

		this.fromPile = fromPile;
		this.movingCard = movingCard;
		this.basePile = basePile;
	}

	/**
	 * To undo this move, we move the cards from the toPile back to the fromPile
	 * @param theGame ks.games.Solitaire 
	 */
	public boolean undo(ks.common.games.Solitaire game) {
		//update score
		game.updateScore(-1);

		// move back
		fromPile.add(basePile.get());

		return true;
	}
	
	/**
	 * Execute the move
	 * @see ks.common.model.Move#doMove(ks.games.Solitaire)
	 */
	public boolean doMove(Solitaire game) {
		if (!valid (game)) {
			return false;
		}
		
		// EXECUTE
		if (movingCard == null) {
			basePile.add(fromPile.get());	
		} else {
			basePile.add(movingCard);
		}
		game.updateScore (+1);
		return true;

	}
	
	/**
	 * Validate the move.
	 * @see ks.common.model.Move#valid(ks.games.Solitaire)
	 */
	public boolean valid(Solitaire game) {
		// get the moving card, regardless of whether active or programmed.
		Card checkCard = movingCard;
		if (checkCard == null) {
			checkCard = fromPile.peek();
		}
		// final sanity check
		if (checkCard == null) return false;

		Card baseCard = basePile.peek();



		if (basePile.empty()) {
			if (checkCard.getRank() == Card.ACE) {
				return true;
			}
		} else if (baseCard.getRank() == (checkCard.getRank() - 1)
				&& baseCard.sameSuit(checkCard)) {
			return true;
		}
		return false;
	}
}
