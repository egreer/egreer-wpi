package yostinso;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;


/**
 * Move GardenColumn - SuitPile class (for undo purposes)
 * Creation date: (11/29/2001 8:20:28 PM)
 * @author: E.O. Stinson
 */
public class MoveGCSPMove extends Move {
	/** From. */
	protected Column from;

	/** Column being dragged. */
	protected Column movingColumn;

	/** Destination. */
	protected SuitPile to;
	public MoveGCSPMove(Column from, Column movingColumn, SuitPile to) {
		super();

		this.from = from;
		this.movingColumn = movingColumn;
		this.to = to;
	}
	public boolean doMove(Solitaire theGame) {
		// VALIDATE:
		if (valid(theGame) == false)
			return false;

		// EXECUTE:
		if (movingColumn == null) {
			to.add(from.get()); // nothing being dragged? Go to From for card
		} else {
			to.add(movingColumn.get()); // add the card
		}

		theGame.updateScore(+1); // and update the score
		return true;
	}
	/**
	 * Returns destination pile (calculated by valid method).
	 */
	public SuitPile getDestination() {
		return to;
	}
	public boolean undo(Solitaire game) {
		// VALIDATE:
		if (to.empty())
			return false;

		// UNDO:
		// move the card(s) back to the source.
		from.add(to.get());

		// signal that we were successful.
		game.updateScore(-1);
		return true;
	}
	public boolean valid(Solitaire theGame) {
		if (movingColumn.count() != 1)
			return false;

		// Determine which SuitPile should be the target
		if (to == null) {
			String key = Card.getSuitName (movingColumn.suit());
			to = (SuitPile) theGame.getModelElement (key);
			// A suitless card, fancy that
			if (to == null) return false;
		}

		// trying to place on wrong PileSuit
		if (to.getAssignedSuit() != movingColumn.suit())
			return false;

		// If the SuitPile we've found is empty, and the card we're trying
		// to move to it is an Ace, we have a valid move.
		if (to.empty()) {
			return (movingColumn.rank() == Card.ACE);
		} 

		// otherwise, if the SuitPile isn't empty, but the card we're
		// trying to move has a rank of one greater than the SuitPile's
		// top card, we have a valid move.
		if (movingColumn.rank() == to.rank() + 1) {
			return true;
		}

		return false;
	}
}
