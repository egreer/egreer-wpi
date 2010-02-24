package darrent;

import ks.common.model.*;
import ks.common.games.Solitaire;

/**
 * Represents the move of a card from a column to a free cell
 * <p>
 * This move might trigger auto-generated moves
 */
public class ColumnFreeCellMove extends ks.common.model.Move {

	/** Destination Free Cell. */
	private Pile freeCell;

	/** Must contain only a single card. */
	private Column movingColumn;

	/** Source column. */
	private Column fromCol;

	/**
	 * ColumnFreeCellMove constructor comment.
	 */
	public ColumnFreeCellMove(Column fromCol, Column movingColumn, Pile freeCell) {
		super();

		this.fromCol = fromCol;
		this.movingColumn = movingColumn;
		this.freeCell = freeCell;
	}

	/**
	 * To undo this move, we move the cards from the toPile back to the fromPile
	 * @param theGame ks.games.Solitaire 
	 */
	public boolean undo(ks.common.games.Solitaire game) {
		// move back
		fromCol.add(freeCell.get());
		return true;
	}

	/**
	 * Do move.
	 * @see ks.common.model.Move#doMove(ks.games.Solitaire)
	 */
	public boolean doMove(Solitaire game) {
		// VALIDATE
		if (!valid(game)) {
			return false;
		}

		// EXECUTE
		if (movingColumn != null) {
			freeCell.push (movingColumn);
		} else {
			freeCell.add(fromCol.get());
		}
		return true;
	}

	/**
	 * Validate move.
	 * @see ks.common.model.Move#valid(ks.games.Solitaire)
	 */
	public boolean valid(Solitaire game) {
		// If we are a calculated move, then we must resort to considering
		// the source of the move; otherwise we work on the column being moved.
		if (movingColumn == null) {
			if (fromCol.empty())
				return false;
		} else {
			// can only be moving a column of size 1 to a FreeCell.
			if (movingColumn.count() != 1)
				return false;

		}

		// moves a card from a column to a freecell
		return (freeCell.empty());
	}
}
