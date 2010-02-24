package darrent;

import ks.common.model.*;
import ks.common.games.Solitaire;

/**
 * Move a card between free cells.
 * <p>
 * This move can never generate auto-moves since it doesn't change cards in play. 
 */
public class FreeCellFreeCellMove extends ks.common.model.Move {
	/** Destination. */
	private Pile toPile;
	
	/** Card being moved (or null if programmed move). */
	private Card movingCard;
	
	/** Source. */
	private Pile fromPile;
	
	/**
	 * FreeCellFreeCellMove constructor comment.
	 */
	public FreeCellFreeCellMove(Pile fromPile, Card movingCard, Pile toPile) {
		super();

		this.fromPile = fromPile;
		this.movingCard = movingCard;
		this.toPile = toPile;
	}

	/**
	 * To undo this move, we move the cards from the toPile back to the fromPile
	 * @param theGame ks.games.Solitaire 
	 */
	public boolean undo(ks.common.games.Solitaire game) {
		fromPile.add(toPile.get());
		return true;
	}
	
	/**
	 * Execute move.
	 * @see ks.common.model.Move#doMove(ks.games.Solitaire)
	 */
	public boolean doMove(Solitaire game) {
		if (!valid (game)) {
			return false;
		}
		
		// EXECUTE
		if (movingCard != null) {
			toPile.add(movingCard);
		} else {
			toPile.add (fromPile.get());
		}
		return true;
	}
	
	/**
	 * Validate move
	 * @see ks.common.model.Move#valid(ks.games.Solitaire)
	 */
	public boolean valid(Solitaire game) {
		if (movingCard != null) {
			return toPile.empty() && fromPile.empty();
		} else {
			return toPile.empty() && !fromPile.empty();
		}
	}
}
