package egreer;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.games.Solitaire;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.Pile;

/**
 * Controller for the DeckView widget in Sly Fox.
 * 
 * Extends 'SolitaireReleasedAdapter' to get the default behavior
 *
 * @author Eric Greer
 */
public class SlyFoxDeckController extends SolitaireReleasedAdapter {

	/** Sly Fox DeckController constructor
	 * @param game The current solitaire game 
	 */
	public SlyFoxDeckController(Solitaire game) {
		super(game);
	}


	/**
	 * A MousePressed event on the DeckView is a signal to deal the next
	 * set of cards if the reserve stock is empty, and the deck is nonempty.
	 *
	 * @param me     A low-level mouse event (press)
	 */
	public void mousePressed(java.awt.event.MouseEvent me) {

		// Find the deck, and location to deal to (reserveDeck)
		Deck deck = (Deck) theGame.getModelElement("deck");
		Pile reserve = (Pile) theGame.getModelElement("reserveDeck");


		// Deal 20 cards (or rest of the cards if there are < 20 in the deck) 
		Move m = new DealMove(deck, reserve);
		if (m.doMove(theGame)) {
			// SUCCESS: have solitaire game store this move
			theGame.pushMove(m);
			if (theGame.getName().compareTo("Sly Fox") == 0){
				SlyFox sfGame = (SlyFox)theGame;
				sfGame.setPhase(sfGame.getPhase() + 1);
			}

			// solitaire game refreshes widgets that were affected (Deck, and Reserve) 
			theGame.refreshWidgets();
		}

	}
}

