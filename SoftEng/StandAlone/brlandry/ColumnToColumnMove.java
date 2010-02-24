package brlandry;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;

/**This class represents moves between Columns.  
 * It supports moves initiated by mousedrags or by the computer to solve the
 * game.
 * @author Brian Landry (brlandry@wpi.edu)
 */
public class ColumnToColumnMove extends Move {
	protected Column from;
	protected Column to;
	protected Card movingCard;

	public ColumnToColumnMove(Column from, Card movingCard, Column to) {
		super();

		this.from = from;
		this.movingCard = movingCard;
		this.to = to;
	}

	/**Moves can determine their validity.  Helper functions have been used
	 * to improve the readability of the code.
	 * @param theGame    the game being played
	 * @return whether the move is legal
	 */
	public boolean valid(Solitaire theGame) {

		//If movingCard is null, this is a computer generated move.  We need to
		//extract the card from source column
		if (movingCard == null) {
			//if the "from" column is empty, we cannot take a card from it
			if (from.empty())
				return false;

			//if the destination column is empty, we can move any card to it
			if (to.empty())
				return true;

			int rank = from.rank();

			if (oneLess(rank) || kingOverAce(rank))
				return true;
			else
				return false;
		}
		//move originated by dragging card
		else {
			//if the destination column is empty, we can move any card to it
			if (to.empty())
				return true;

			if (oneLess(movingCard.getRank()) || kingOverAce(movingCard.getRank()))
				return true;
		}
		return false;
	}

	/**Helper function to be used when movingCard is null
	 */
	protected boolean oneLess(int rank) {
		if ((rank - to.rank()) == -1)
			return true;
		else
			return false;
	}

	/**Helper function to determine if a King is being placed on an Ace.
	 * Called when movingCard is null. 
	 */
	protected boolean kingOverAce(int rank) {
		if ((rank == 13) && (to.rank() == 1))
			return true;
		else
			return false;
	}

	/**Moves can execute themselves
	 * @param theGame      the game being played
	 * @return boolean whether or not the move was completed
	 */
	public boolean doMove(Solitaire theGame) {
		// VALIDATE
		if (!valid(theGame)) {
			return false;
		}

		// EXECUTE
		if (movingCard == null)
			to.add(from.get());
		else
			to.add(movingCard);

		return true;
	}

	/**
	 * Undo
	 */
	public boolean undo(Solitaire theGame) {
		from.add(to.get());

		return true;
	}

}