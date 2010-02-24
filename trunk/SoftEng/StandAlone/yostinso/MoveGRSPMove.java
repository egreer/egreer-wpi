package yostinso;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;


/**
 * Represents the move of a card from the GardenRow to the SuitPile class.
 * <p>
 * Creation date: (11/29/2001 8:20:16 PM)
 * @author: E.O. Stinson (revised for KombatSolitaire V1.8 by heineman)
 */
public class MoveGRSPMove extends Move {
	/** Source. */
	protected GardenRow from;
	
	/** Column being dragged. */
	protected Column movingColumn;

	/** Destination. */	
	protected SuitPile to;

	/** Where user last selected. */
	protected int lastSelectionPoint;
/**
 * MoveGRSPMove constructor comment.
 */
public MoveGRSPMove(GardenRow from, Column movingColumn, SuitPile to, int lastSelectionPoint) {
	super();

	this.from = from;
	this.movingColumn = movingColumn;
	this.to = to;
	this.lastSelectionPoint = lastSelectionPoint;
}
public boolean doMove (Solitaire theGame) {
	// VALIDATE:
	if (valid (theGame) == false)
		return false;

	// EXECUTE:
	if (movingColumn == null) {
		to.add (from.get());         // extract card from FROM
	} else {
		to.add (movingColumn.get()); // add the card
	}

	theGame.updateScore (+1);	 // update score	

	return true;
}
/**
 * Returns destination pile (calculated by valid method).
 */
public SuitPile getDestination () {
	return to;
}
/**
 * Returns the card to the GardenRow by removing cards and inserting at proper place.
 */
public boolean undo(Solitaire game) {
	// Restore our model element's state, and put the card in the right place
	Column tempColumn = new Column();
	
	// Dig our way to the selection point
	for (int i = 0; i < (from.getLastSelectionPoint()-1); i++) {
		tempColumn.add(from.get());
	}
	
	// Insert our card
	from.add(to.get());
	
	// And put the cards back on
	while (!tempColumn.empty()) {
		from.add(tempColumn.get());
	}
	
	// signal that we were successful.
	game.updateScore(-1);
	return true;
}
/**
 * Validate the validity of this move.
 * <p>
 * Note: the moving column must be composed of only a single card.
 */
public boolean valid (Solitaire theGame) {
	if (movingColumn.count() != 1) return false;
	
	// Determine which SuitPile should be the target
	if (to == null) {
		if (movingColumn.suit() == Card.HEARTS) {
			to = (SuitPile) theGame.getModelElement ("hearts");
		} else if (movingColumn.suit() == Card.CLUBS) {
			to = (SuitPile) theGame.getModelElement ("clubs");
		} else if (movingColumn.suit() == Card.DIAMONDS) {
			to = (SuitPile) theGame.getModelElement ("diamonds");
		} else if (movingColumn.suit() == Card.SPADES) {
			to = (SuitPile) theGame.getModelElement ("spades");
		} else {
			// A suitless card, fancy that
			return false;
		}
	}

	// trying to place on wrong PileSuit
	if (to.getAssignedSuit() != movingColumn.suit()) return false;
	
	// If the SuitPile we've found is empty, and the card we're trying
	// to move to it is an Ace, this is a valid move.
	if (to.empty()) {
		return (movingColumn.rank() == Card.ACE);
	}
		
	// otherwise, if the SuitPile isn't empty, but the card we're
	// trying to move has a rank of one greater than the SuitPile's
	// top card
	if (movingColumn.rank() == to.rank() + 1) {
		return true;
	}

	return false;
}
}
