package kdaquila;

import ks.common.model.BuildablePile;
import ks.common.model.Card;
import ks.common.model.Column;
/**
 * Handles moving a card between piles
 * Creation date: (4/11/2002 6:10:43 PM)
 * @author: Kevin J. D'Aquila (kdaquila@wpi.edu
 */
public class MoveBunchMove extends ks.common.model.Move
{
	protected BuildablePile fromBuildablePile, toBuildablePile;
	protected Column movingColumn;
	protected boolean flipped = false;
/**
 * MoveBunchMove constructor comment.
 * @param BuildablePile from, Column column, BuildablePile to
 */
protected MoveBunchMove(BuildablePile from, Column column, BuildablePile to) {
	super();

	// Get all our elements internally
	this.fromBuildablePile = from;
	this.toBuildablePile = to;
	this.movingColumn = column;
}
/**
 * This will perform the move of a bunch of cards between piles
 */
public boolean doMove(ks.common.games.Solitaire theSolitaireGame) {

	Spider theGame = (Spider)theSolitaireGame;

	// VALIDATE:
	if (!valid(theGame))
		return false;

	// Execute move
	Card tempCard = movingColumn.peek(0);
	if ((toBuildablePile.empty() == true) || (toBuildablePile.rank() == (tempCard.getRank() + 1)))
	{
		toBuildablePile.push(movingColumn);
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
	toBuildablePile.select(movingColumn.count());
	fromBuildablePile.push(toBuildablePile.getSelected());

	theGame.updateScore(1);
	theGame.updateMoves(-1);

	return true;
}
/**
 * valid method comment.
 */
public boolean valid(ks.common.games.Solitaire game)
{
	boolean validation = true;

	// VALIDATE:
	//    does not apply here, handled 

	return validation;
	
}
}
