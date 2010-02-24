package darrent;

import ks.common.model.*;
import ks.common.games.Solitaire;

/**
 * Represents a move from a column to a base.
 * <p>
 * This move might uncover a card that can be auto-played. 
 */
public class ColumnBaseMove extends ks.common.model.Move {

	/** Destination Base. */
	private Pile basePile;

	/** Must contain only a single card. */
	private Column movingColumn;
	
	/** Source. */
	private Column fromCol;
	
	/**
	 * ColumnBaseMove constructor comment.
	 */
	public ColumnBaseMove(Column fromCol, Column movingColumn, Pile basePile) {
		super();

		this.fromCol = fromCol;
		this.basePile = basePile;
		this.movingColumn = movingColumn;
	}

	/**
	 * To undo this move, we move the cards from the toPile back to the fromPile
	 * @param theGame ks.games.Solitaire 
	 */
	public boolean undo(ks.common.games.Solitaire game) {
		//update score
		game.updateScore(-1);

		// move back
		fromCol.add(basePile.get());
		return true;
	}

	/** Execute the move
	 * @see ks.common.model.Move#doMove(ks.games.Solitaire)
	 */
	public boolean doMove(Solitaire game) {
		// Validate
		if (!valid(game)) {
			return false;
		}

		// EXECUTE
		if (movingColumn == null) {
			basePile.add(fromCol.get());
		} else {
			basePile.push(movingColumn);
		}

		game.updateScore(+1);
		return true;
	}

	/** Validate the move.
	 * @see ks.common.model.Move#valid(ks.games.Solitaire)
	 */
	public boolean valid(Solitaire game) {
		// VALIDATE
		Card baseCard = basePile.peek();

		// programmed move will have empty movingColumn
		Card checkCard;
		if (movingColumn == null) {
			checkCard = fromCol.peek();
		} else {
			checkCard = movingColumn.peek();
		}

		// final sanity check
		if (checkCard == null) return false;
		
		// ACEs can move to empty basePiles
		if (basePile.empty()) {
			return (checkCard.getRank() == Card.ACE);
		}
		
		if (baseCard.getRank() == (checkCard.getRank() - 1)
			&& baseCard.sameSuit(checkCard)) {
				return true;
		}

		return false;
	}
}
