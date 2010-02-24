package scaviola;

/**
 * Models a deck of cards for BCastle.
 * A BCastleDeck has only 48 cards; there are no Aces.
 *
 * Note: Cards are only removed from one end of the deck.
 *
 * Creation date: (9/30/01 10:42:44 PM)
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 * Modify date: (12/03/01 11:30 PM)
 * Modified by Mike Scaviola (scaviola@wpi.edu)
 */

 import ks.common.model.*;
 
public class BCastleDeck extends ks.common.model.Deck {
	/** Used to uniquely identify named Decks (if no name is assigned). */
	private static int deckNameCounter = 1;

	/**
	 * Construct a deck with unique generated name.
	 */
	public BCastleDeck() {
		this (new String ("Deck" + deckNameCounter++));
	}
	
	/**
	 * Construct a deck object with the given name
	 */
	public BCastleDeck(String name) {
		super(name);
	}


	/**
	 * Set deck to be in order from TWO -> King, Club -> Spade
	 */
	protected void initialize() {
		numCards = 0;
		cards = new Card [48];
		
		// FORALL ranks
		for (int r = Card.TWO; r <= Card.KING; r++) {
			// FORALL suits
			for (int s = Card.CLUBS; s <= Card.SPADES; s++) {
				Card c = new Card (r, s);
				cards[numCards++] = c;
			}
		}
	}
}
