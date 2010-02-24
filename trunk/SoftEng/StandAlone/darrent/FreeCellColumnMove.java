package darrent;

import ks.common.model.*;
import ks.common.games.Solitaire;

/**
 * Move from a FreeCell to a column
 * <p>
 * This move can never generate auto-moves since it doesn't change cards in play.
 * On the contrary, it may restrict cards. 
 */
public class FreeCellColumnMove	extends ks.common.model.Move {

	/** The destination. */
	private Column toColumn;
	
	/** The card being moved (or null if a programmed move). */
	private Card movingCard;
	
	/** The source. */
	private Pile freeCell;
	
	/**
	 * FreeCellColumnMove constructor comment.
	 */
	public FreeCellColumnMove(Pile freeCell, Card movingCard, Column toColumn) {
		super();

		this.freeCell = freeCell;
		this.movingCard = movingCard;
		this.toColumn = toColumn;
	}

	/**
	 * To undo this move, we move the cards from the toPile back to the fromPile
	 * @param theGame ks.games.Solitaire 
	 */
	public boolean undo(ks.common.games.Solitaire game) {
		// move back
		freeCell.add(toColumn.get());
		return true;
	}

	/* (non-Javadoc)
	 * @see ks.common.model.Move#doMove(ks.games.Solitaire)
	 */
	public boolean doMove(Solitaire game) {
		// VALIDATE
		if (!valid (game)) {
			return false;
		}

		if (movingCard == null) {
			toColumn.add(freeCell.get());
		} else {
			toColumn.add(movingCard);
		}

		return true;
	}

	/** 
	 * Validate move.
	 * @see ks.common.model.Move#valid(ks.games.Solitaire)
	 */
	public boolean valid(Solitaire game) {

		Card topCard = toColumn.peek();
		if (toColumn.empty()) {
			return true;
		}

		if (topCard.getRank() == (movingCard.getRank() + 1)
				&& topCard.oppositeColor(movingCard)) {
			toColumn.add(movingCard);
			return true;
		}

		return false;
	}
}
