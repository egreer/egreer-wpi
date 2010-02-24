package brlandry;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;
import ks.common.model.Pile;

public class ColumnToPileMove extends Move {
	protected Column from;
	protected Pile to;
	protected Card movingCard;

	public ColumnToPileMove(Column from, Card movingCard, Pile to) {
		super();

		this.from = from;
		this.movingCard = movingCard;
		this.to = to;
	}

	/**
	 * Moves can determine their validity.
	 * Helper functions are used to make code more readable
	 * @param theGame   the game being played
	 */
	public boolean valid(Solitaire theGame) {
		//double click
		if (movingCard == null) {
			//If the source column is empty, we cannot take a card from it
			if (from.empty())
				return false;

			int rank = from.rank();
			int suit = from.suit();

			if (sameSuit(suit) && (oneMore(rank) || aceOverKing(rank))) {
				return true;
			} else
				return false;
		}
		//mouse drag
		else if (sameSuit(movingCard.getSuit()) && (oneMore(movingCard.getRank()) || aceOverKing(movingCard.getRank())))
			return true;
		else
			return false;
	}

	/**
	 * Helper function to determine a "hypothetical card" is of the same suit
	 * as the to pile.  This is called on a double click or solve attempt.
	 * @param int suit - the constant for the suit of the "hypothetical card"
	 */
	protected boolean sameSuit(int suit) {
		if (to.suit() == suit)
			return true;
		else
			return false;
	}

	/**Helper function to determine a "hypothetical card" has a rank of one
	 * greater than the top card on the "to" pile.  
	 * This is called on a double click or solve attempt.
	 * @param int rank - the rank of the "hypothetical card"
	 */
	protected boolean oneMore(int rank) {
		if ((rank - to.rank()) == 1)
			return true;
		else
			return false;
	}

	/**Helper function to determine a "hypothetical card" is an Ace and if the
	 * top card on the "to" pile is a King.  
	 * This is called on a double click or solve attempt.
	 */
	protected boolean aceOverKing(int rank) {
		if ((rank == 1) && (to.rank() == 13))
			return true;
		else
			return false;
	}

	/**
	 * Execute the move
	 * @param theGame    the game currently being played
	 */
	public boolean doMove(Solitaire theGame) {
		// VALIDATE
		if (!valid(theGame))
			return false;
			
		// EXECUTE
		if (movingCard == null)
			to.add(from.get());
		else
			to.add(movingCard);

		theGame.updateScore(5);
		return true;
	}

	/** 
	 * Undo move (and score)
	 */
	public boolean undo(Solitaire theGame) {
		from.add(to.get());

		theGame.updateScore(-5);

		return true;
	}

}