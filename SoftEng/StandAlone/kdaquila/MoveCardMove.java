package kdaquila;

import ks.common.model.BuildablePile;
import ks.common.model.Card;
/**
 * Handles moving a card between piles
 * Creation date: (4/11/2002 12:00:22 PM)
 * @author: Kevin J. D'Aquila (kdaquila@wpi.edu
 */
public class MoveCardMove extends ks.common.model.Move
{
	protected BuildablePile fromBuildablePile, toBuildablePile;
	protected Card movingCard;
	protected boolean flipped = false;
/**
 * MoveCardMove constructor comment.
 * @param BuildablePile from, Card card, BuildablePile to
 */
protected MoveCardMove(BuildablePile from, Card card, BuildablePile to) {
	super();

	// Get all our elements internally
	this.fromBuildablePile = from;
	this.toBuildablePile = to;
	this.movingCard = card;
}
/**
 * This will perform the move of a card between piles
 */
public boolean doMove(ks.common.games.Solitaire theSolitaireGame)
{
	Spider theGame = (Spider)theSolitaireGame;
	
	// VALIDATE:
	if (!valid(theGame))
		return false;

	// Execute move
	
	if ((toBuildablePile.empty() == true) || (toBuildablePile.rank() == (movingCard.getRank() + 1)))
	{
		toBuildablePile.add(movingCard);
		if (!fromBuildablePile.faceUp())
		{
			fromBuildablePile.flipCard();
			flipped = true;
		}

		theGame.updateScore(-1);
		theGame.updateMoves(1);
		
		return true;
	}

	return false;
}
/**
 * undo method comment.
 */
public boolean undo(ks.common.games.Solitaire theSolitaireGame) {

	Spider theGame = (Spider)theSolitaireGame;
	
	// VALIDATE:
	// ??
	
	// UNDO:
	// carefully reverse order of operations

	if (flipped) fromBuildablePile.flipCard();
	fromBuildablePile.add(toBuildablePile.get());

	theGame.updateScore(1);
	theGame.updateMoves(-1);

	return true;
}
/**
 * valid method comment.
 */
public boolean valid(ks.common.games.Solitaire game) {

	boolean validation = true;

	// VALIDATE:
	//    the deck can't be empty, and neither can the piles

	return validation;
}
}
