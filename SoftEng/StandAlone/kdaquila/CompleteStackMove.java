package kdaquila;

import ks.common.model.BuildablePile;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.MultiDeck;
import ks.common.model.Stack;

/**
 * Handles completing a stack
 * @author: Kevin J. D'Aquila (kdaquila@wpi.edu
 */
public class CompleteStackMove extends ks.common.model.Move
{
	protected BuildablePile fromBuildablePile;
	protected Stack tempStack;
	protected MultiDeck deck;
	protected Column completeRow;
	protected boolean flipped = false;
/**
 * MoveCardMove constructor comment.
 * @param BuildablePile from, Card card, BuildablePile to
 */
protected CompleteStackMove(Stack from, BuildablePile fromBP, ks.common.games.Solitaire theGame) {
	super();

	// Get all our elements internally
	this.tempStack = from;
	this.fromBuildablePile = fromBP;
	this.deck = (MultiDeck)theGame.getModelElement("deck");
	this.completeRow = (Column)theGame.getModelElement("completeRow");	
}
/**
 * This will perform the move of a card between piles
 */
public boolean doMove(ks.common.games.Solitaire theSolitaireGame)
{
	Spider theGame = (Spider)theSolitaireGame;
	
	// VALIDATE:
	if (!valid(theGame)) return false;

	// Execute move
	completeRow.add(new Card(Card.KING, tempStack.rank()));
	tempStack.select(13);
	deck.push(tempStack.getSelected());
	if (!fromBuildablePile.empty() && !fromBuildablePile.faceUp())
	{
		fromBuildablePile.flipCard();
		flipped = true;
	}

	theGame.updateScore(100);
	return true;
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
	completeRow.get();
	if(flipped) fromBuildablePile.flipCard();
	fromBuildablePile.push(deck);
	deck.removeAll();
	theGame.updateScore(-100);

	// Must undo previous move as well!
	theGame.undoMove();

	return true;	
	

}
/**
 * valid method comment.
 */
public boolean valid(ks.common.games.Solitaire game) {

	boolean validation = true;

	// VALIDATE:
	//    Validation handled by controllers

	return validation;
}
}
