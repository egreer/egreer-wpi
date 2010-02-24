package yostinso;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;


/**
 * Move GardenColumn - GardenColumn class (for undo purposes)
 * Creation date: (11/29/2001 7:47:27 PM)
 * @author: 
 */
public class MoveGCGCMove extends Move {
	protected Column from;
	protected Column movingColumn;
	protected Column to;
	protected int numCards;
	/**
	 * MoveGCGCMove constructor comment.
	 */
	public MoveGCGCMove(
		Column from, Column movingColumn, Column to, int numCards) {
		super();

		this.from = from;
		this.movingColumn = movingColumn;
		this.to = to;
		this.numCards = numCards;
	}
	public boolean doMove(Solitaire game) {
		// VALIDATE:
		if (valid(game) == false) {
			return false;
		}

		// EXECUTE:
		if (movingColumn != null) {
			to.push(movingColumn);
		} else {
			from.select(numCards);
			to.push(from.getSelected());
		}

		// signal that we were successful.
		return true;
	}

	public boolean undo(Solitaire game) {
		// VALIDATE:
		if (numCards <= 0)
			return false;

		// UNDO::
		// move the card(s) back to the source.
		to.select(numCards);
		from.push(to.getSelected());

		// signal that we were successful.
		return true;
	}
	public boolean valid(Solitaire theGame) {

		if (movingColumn != null) {
			// Make sure the column isn't empty
			if (movingColumn.empty())
				return false;

			if (from == to)
				return false; // no point staying on same GardenColumn.

			int extraCardCount = movingColumn.count() - 1;
			
			//int emptyColumns = getEmptyColumnCount(theGame);
			// count the empty columns (ignoring from and to since these are locked up already)
			int emptyColumns = ((FlowerGarden)theGame).getEmptyColumnCount(from, to);
			
			// If there's not enough free columns (one for every extra card) return false
			if (emptyColumns < extraCardCount)
				return false;

			// If the destination is empty we are good to go.		
			if (to.empty())
				return true;

			// otherwise, if the destination isn't empty

			int toRank = to.rank();
			// get the rank of the top card of the target
			Card bottomCard = movingColumn.peek(0);
			// then get the bottom card of the source
			int fromRank = bottomCard.getRank(); // and its rank

			// If the rank of the target is one more than the rank of the
			// card in col, (and the col is descending, though it shouldn't
			// matter), then
			return (toRank == 1 + fromRank && movingColumn.descending());
		} else {
			// this is the more challenging case only to be used by solitaire solvers...
			return false;
		}
	}
}
