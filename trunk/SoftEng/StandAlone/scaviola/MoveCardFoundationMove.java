package scaviola;

import ks.common.model.Card;
import ks.common.model.Pile;
import ks.common.model.Column;

/**
 * Represents the moving of a card from one row to another, or from a row to a pile.
 * <p>
 * Creation date: (12/04/01 7:50 PM)
 * @author: Mike Scaviola (scaviola@wpi.edu)
 */
public class MoveCardFoundationMove extends ks.common.model.Move {
	/** From Column. */
	protected Column from;
	
	/** Column being dragged. */
	protected Column draggedColumn;

	/** To Pile. */
	protected Pile to;

	/** Number of cards being moved. */
	protected int numToMove;
public MoveCardFoundationMove (Column from, Column draggedColumn, Pile to, int numToMove) {
	super();

	this.from = from;
	this.draggedColumn = draggedColumn;
	this.to = to;
	this.numToMove = numToMove;
}
/**
 * Execute the move.
 * @param theGame ks.games.Solitaire 
 */
public boolean doMove(ks.common.games.Solitaire game) {
	// VALIDATE
	if (valid (game) == false)
		return false;

	// EXECUTE:
	if (draggedColumn == null) {

		// extract cards.
		from.select (numToMove);
		draggedColumn = new Column();
		draggedColumn.push (from.getSelected());
		
		// update score
		game.updateScore(draggedColumn.count());

		// transfer cards.
		to.push (draggedColumn);
	} else {
		// update score
		game.updateScore(draggedColumn.count());

		// transfer cards. Can't push because we must go in reverse
		// order
		for (int i = numToMove-1; i >= 0; i--)
			to.add (draggedColumn.peek(i));
	}
	return true;

}
/**
 * To undo this move, we move the cards back where they came from. 
 * Doesn't matter whether they're coming from a Row or Pile.
 * <br>Score is decremented when a move is undone from a Pile.
 * @param theGame ks.games.Solitaire 
 */
public boolean undo(ks.common.games.Solitaire game) {
	// VALIDATE:
	if (draggedColumn == null) return false;

	// Decrement the score
	game.updateScore(-draggedColumn.count());
	
	// move back and remove from foundation
	int size = draggedColumn.count();
	from.push (draggedColumn);
	for (int i = 0; i < size; i++) { to.get(); }
	
	draggedColumn = null;
	return true;
}
/**
 * Validate the move.
 * @param theGame ks.games.Solitaire 
 */
public boolean valid(ks.common.games.Solitaire game) {
	boolean validation = false;

	// VALIDATE
	if (draggedColumn != null) {
		if (draggedColumn.descending() && draggedColumn.sameSuit() && !draggedColumn.empty()) {
			if (to.empty()) {
				// should never happen in practice, but technically correct
				if (draggedColumn.peek(0).getSuit() == Card.ACE)
					validation = true;
			} else {
				// MoveCard (from, draggedColumn, to, n) : from.count() > n, 
				if ((draggedColumn.rank() == to.rank() + 1) &&
					(draggedColumn.suit() == to.suit())) {
					validation = true;
				}
			}
		}
	} else {
		// MoveCard (from, null, to, n) : from.count() > n, 
		if ((numToMove < from.count()) && (numToMove > 0)) {
			if (to.empty()) {
				if (from.peek (from.count() - numToMove).getRank() == Card.ACE) 
					validation = true;
			} else {
				// verify that all cards from from.peek (from.count() - numToMove)
				// are in descending order, starting with to.rank() - 1, and have same suit
				int next = to.rank() - 1;
				boolean descending = true;
				boolean sameSuit = true;
				for (int i = 1; i <= numToMove; i++) {
					Card c = from.peek (from.count() - numToMove);
					if (c.getRank() != next--) { descending = false; break; }
					if (c.getSuit() != to.suit()) { sameSuit = false; break; }
				}
				if (descending && sameSuit) {
					validation = true;
				}
			}
		}
	}

	return validation;
}
}
