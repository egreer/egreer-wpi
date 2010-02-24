package egreer;

import ks.common.games.Solitaire;
import ks.common.model.*;

/**
 * Represents moving of 20 cards from the deck to the reserve deck. 
 * 
 * @author Eric Greer
 *
 */
public class DealMove extends ks.common.model.Move{

	/** From and To piles. */
	protected Deck from;
	protected Pile to;
	
	/** Amount of cards moved */ 
	protected int numberMoved;
	
	/** Stores the current columns status*/
	protected int[] columnListStatus;
	
	/**
	 * DealMove constructor.
	 * 
	 * @param from         The source Deck that begins the move
	 * @param to           The target pile for the move.
	 */
	public DealMove (Deck from, Pile to) {
		super();

		this.from = from;
		this.to = to;
		this.numberMoved = 0;
	}

	
	/**
	 * Each move knows how to execute itself.
	 *
	 * @param ks.common.game.Solitaire   the current game being played.
	 * @return boolean
	 */
	public boolean doMove (Solitaire currentGame) {
		// Checks the validity of the game
		if (valid(currentGame) == false) {
			return false;
		}
		
		// Moves 20 cards or less if there aren't 20 cards
		int i = 0;
		while( (i < 20) && (from.peek() != null)){
		to.add (from.get());
		numberMoved++;
		i++;
		}
		
		//Resets the list of dirty columns, and saves it to the move for undo
		if (currentGame.getName() == "Sly Fox"){
			SlyFox sfGame = (SlyFox) currentGame;
			this.columnListStatus = sfGame.saveColumnList();
			sfGame.resetColumnList();
		}
		
		return true;
	}
	
	/** This move can be undone by moving the cards of the Reserve to the Deck
	 * @param theGame ks.common.games.Solitaire
	 */
	public boolean undo(Solitaire game) {
		// Make sure trying not to move less then what was moved before 
		if (to.count() != numberMoved) return false;

		// Move card back to the from pile 		
		for (int u = 0; u < numberMoved; u++){
		from.add (to.get());
		}
		
		//Restores list of dirty columns for the next set of undo
		if (game.getName() == "Sly Fox"){
			SlyFox sfGame = (SlyFox) game;
			sfGame.restoreColumnList(this.columnListStatus);
		}
		
		return true;
	}
	
	/**
	 * Validates DealMove
	 * @param game 
	 */
	public boolean valid(Solitaire currentGame){
		
		// Default to not valid move
		boolean isValid = false;
		
		//If the piles are empty no moves
		if ( (to.empty()) && !(from.empty()) ){
			isValid = true;
		}

		return isValid;
	}	
}
