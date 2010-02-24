package yostinso;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;


/**
 * Move GardenRow - GardenColumn class (for undo purposes)
 * Creation date: (11/29/2001 8:19:57 PM)
 * @author: E.O. Stinson
 */
public class MoveGRGCMove extends Move {
	protected GardenRow from;
	protected Column movingColumn;
	protected Column to;
	protected int lastSelected;
	
	public MoveGRGCMove(GardenRow from, Column movingColumn, Column to, int lastSelectionPoint) {
		super();

		this.from = from;
		this.movingColumn = movingColumn;
		this.to = to;
		this.lastSelected = lastSelectionPoint;
	}
	
	public boolean doMove(Solitaire theGame) {
		// VALIDATE:
		if (valid(theGame) == false)
			return false;

		// EXECUTE:
		if (movingColumn != null) {
			to.push(movingColumn); // add the column!
		} else {
			to.add(from.get());
			// otherwise Get! THIS IS WRONG BECAUSE WE HAVE TO get nth card, so must provide programmatic interface for this to happen...
		}
		return true;
	}
	
	public boolean undo(Solitaire game) {
		// VALIDATE:
		if (from.getLastSelectionPoint() > from.count())
			return false; // IS THIS RIGHT? or even necessary?

		// UNDO:
		// Restore our model element's state, and put the card in the right place
		Column tempColumn = new Column();
		// Dig our way to the selection point
		for (int i = 0; i < (from.getLastSelectionPoint() - 1); i++) {
			tempColumn.add(from.get());
		}

		// Insert our card
		from.add(to.get());

		// And put the cards back on
		while (!tempColumn.empty()) {
			from.add(tempColumn.get());
		}

		// signal that we were successful.
		return true;
	}
	/**
	 * Validate move.
	 */
	public boolean valid(Solitaire theGame) {
		if (movingColumn != null) {
			if (movingColumn.empty())
				return false;

			// If the destination is empty we have a move
			if (to.empty())
				return true;

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
			return false;
		}
	}
}
