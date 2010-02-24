package scaviola;

import ks.common.model.Card;
import ks.common.model.Column;

/**
 * Represents the moving of a card from one row to another
 * <p>
 * Creation date: (12/04/01 7:50 PM)
 * @author: Mike Scaviola (scaviola@wpi.edu)
 */
public class MoveColumnMove extends ks.common.model.Move {
	/** From Column. */
	protected Column from;

	/** Column being moved. */
	protected Column draggedColumn;
	
	/** Size of column to move. */
	protected int numToMove;
	
	/** To Column. */
	protected Column to;	
	
	/**
	 * Move Column from one column to another.
	 * @param from            the source column
	 * @param draggedColumn   the column being dragged (or null if move hasn't been made yet)
	 * @param to              the target column for the cards
	 * @param numToMove       the number of cards to move (or equal to draggedColumn.count())
	 */
	public MoveColumnMove (Column from, Column draggedColumn, Column to, int numToMove) {
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
			
			// transfer cards.
			to.push (draggedColumn);
		} else {
			// transfer cards.
			to.push (draggedColumn);
		}
		return true;
	}
	
	/**
	 * To undo this move, we move the cards back where they came from. 
	 * Doesn't matter whether they're coming from a Row or Pile.
	 * @param theGame ks.games.Solitaire 
	 */
	public boolean undo(ks.common.games.Solitaire game) {
	
		// VALIDATE:
		if (draggedColumn == null) return false;
	
		// move back and remove from the target 'to' row
		from.push (draggedColumn);
		for (int i = 0; i < numToMove; i++) { to.get(); }
		
		draggedColumn = null;
		return true;
	}
	
	/**
	 * Validate the move.
	 * @param theGame ks.games.Solitaire 
	 */
	public boolean valid(ks.common.games.Solitaire game) {
		boolean validation = false;
	
		// VALIDATE: VERIFY THAT EMPTY COLUMN IS NOT TARGET!!!
		int numEmpty = ((BCastle) game).countEmptyExcept(from,to);	
		if (draggedColumn != null) {
			if (draggedColumn.count() - 1 <= numEmpty) {
				if (draggedColumn.descending() && !draggedColumn.empty()) {
					if (to.empty())
						validation = true;
					else {
						// MoveCard (from, draggedColumn, to, n) : from.count() > n,
						Card c = draggedColumn.peek(0);
						if (c.getRank() == to.rank() - 1) {
							validation = true;
						}
					}
				}
			}
		} else {
			// MoveCard (from, null, to, n) : from.count() > n, 
			if ((numToMove < from.count()) && (numToMove > 0)) {
				if (numToMove - 1 <= numEmpty) {
					if (to.empty())
						validation = true;
					else {
						// verify that all cards from from.peek (from.count() - numToMove)
						// are in descending order, starting with to.rank() - 1.
						// TODO: Convert to new Stack.descending (start, end)
						int next = to.rank() - 1;
						boolean descending = true;
						for (int i = 1; i <= numToMove; i++) {
							Card c = from.peek (from.count() - numToMove);
							if (c.getRank() != next--) { descending = false; break; }
						}
						if (descending) {
							validation = true;
						}
					}
				}
			}
		}
	
		return validation;
	}
}
