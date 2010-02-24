package egreer;

import ks.common.games.Solitaire;
import ks.common.model.*;

/**
 * Represents moving of a card from the reserve deck to a reserve column. 
 * 
 * @author Eric Greer
 */

public class MoveReserveCardMove extends ks.common.model.Move{
	
	/** From and To piles. */
	protected Pile from;
	protected Column to;
	
	/** The card being moved */
	protected Card movingCard;
	
	/** The number of the pile */
	protected int indexNumber;

	/** Constructor for MoveReserveCardMove 
	 * 
	 * @param from			The pile from which the card originates
	 * @param movingCard	The card that is moving
	 * @param to			The column that the card is placed on 
	 */
	public MoveReserveCardMove (Pile from, Card movingCard, Column to) {
		super();
		
		this.from = from;
		this.to = to;
		this.movingCard = movingCard;
		
		//Sets the indexNumber based off the last number in the column.
		String columnName = to.getName();
		String columnNumber = columnName.substring(4, columnName.length());
		this.indexNumber = Integer.parseInt(columnNumber);
	}
	
	
	/**
	 * Each move knows how to execute itself.
	 * 
	 * @param currentGame   The current solitaire game being played.
	 * @return boolean		True if move was successful.
	 */
	public boolean doMove (Solitaire currentGame) {
		// Checks the validity
		if (valid(currentGame) == false) {
			return false;
		}
		
		//Adds the card to the column and decrements number of cards left
		to.add (movingCard);
		currentGame.updateNumberCardsLeft(-1);
		
		//Adds the column to the list of dirty columns
		if (currentGame.getName() == "Sly Fox"){
			SlyFox sfGame = (SlyFox) currentGame;
			sfGame.addColumn(indexNumber);
		}
		
		return true;
	}
	
	/** This move can be undone by moving the placed card back to the reserve stock
	 * @param theGame The current solitaire game
	 */
	public boolean undo(Solitaire game) {
		// Make sure trying not to move nothing 
		if (to.empty()) return false; 

		// Move card back to original pile and updates the number of cards left
		from.add (to.get());
		game.updateNumberCardsLeft(1);
		
		//Removes column from the list of dirty columns
		if (game.getName() == "Sly Fox"){
			SlyFox sfGame = (SlyFox) game;
			sfGame.removeColumn(indexNumber);
		}
		return true;
	}

	
	/**
	 * Validates MoveReserveCardMove
	 * @param game The current solitaire game
	 */
	public boolean valid(Solitaire currentGame){
		
		// Default to a not valid move
		boolean isValid = false;
		// isClean represents whether the column is able to have a card move onto it.  
		boolean isClean = true;
		
		// Check to see if a card was put on this pile before
		if (currentGame.getName() == "Sly Fox"){
			SlyFox sfGame = (SlyFox) currentGame;
			isClean = sfGame.checkColumn(indexNumber);
		}
				
		// Validates that the card comes from the reserveDeck and can have a card placed on it
		if ((from == currentGame.getModelElement("reserveDeck")) && isClean) {
			isValid = true;
		}

		return isValid;
	}
	
}
