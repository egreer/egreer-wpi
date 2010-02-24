package kdaquila;

import ks.common.model.BuildablePile;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.games.Solitaire;

/**
 * Represents a Move in Spider Solitaire
 * @author: Kevin J. D'Aquila (kdaquila@wpi.edu)
 */
public class DealDeckMove extends Move
{
	/** Deck */
	protected Deck dealDeck;
	/** BuildablePiles */
	protected BuildablePile pile1, pile2, pile3, pile4, pile5, pile6, pile7, pile8, pile9, pile10;

	/** Class representing the action of dealing a card to each pile */
	public DealDeckMove(Solitaire theGame)
	{
		super();

		/** Store all parameters with the Move Object. */
		this.dealDeck = (Deck) theGame.getModelElement("dealDeck");
		this.pile1 = (BuildablePile) theGame.getModelElement("pile1");
		this.pile2 = (BuildablePile) theGame.getModelElement("pile2");
		this.pile3 = (BuildablePile) theGame.getModelElement("pile3");
		this.pile4 = (BuildablePile) theGame.getModelElement("pile4");
		this.pile5 = (BuildablePile) theGame.getModelElement("pile5");
		this.pile6 = (BuildablePile) theGame.getModelElement("pile6");
		this.pile7 = (BuildablePile) theGame.getModelElement("pile7");
		this.pile8 = (BuildablePile) theGame.getModelElement("pile8");
		this.pile9 = (BuildablePile) theGame.getModelElement("pile9");
		this.pile10 = (BuildablePile) theGame.getModelElement("pile10");
	}
	/**
	 * Each move should knows how to execute itself.
	 * <p>
	 * @param theGame ks.game.Solitaire The game being played.
	 * @return boolean
	 */
	public boolean doMove(Solitaire theGame)
	{
		// VALIDATE:
		if (valid(theGame) == false)
			return false;

		// EXECUTE:
		pile1.add(dealDeck.get());
		pile2.add(dealDeck.get());
		pile3.add(dealDeck.get());
		pile4.add(dealDeck.get());
		pile5.add(dealDeck.get());
		pile6.add(dealDeck.get());
		pile7.add(dealDeck.get());
		pile8.add(dealDeck.get());
		pile9.add(dealDeck.get());
		pile10.add(dealDeck.get());

		return true;
	}
	/**
	 * To undo this move, we move the cards from the piles back into the Deck
	 * in the order they were played.
	 * @param theGame ks.games.Solitaire 
	 */
	public boolean undo(Solitaire theGame)
	{
		// VALIDATE:
		if (pile1.empty() || pile2.empty() || pile3.empty() || pile4.empty() || pile5.empty())
			return false;
		if (pile6.empty() || pile7.empty() || pile8.empty() || pile9.empty() || pile10.empty())
			return false;

		// UNDO:
		// carefully reverse order of operations
		dealDeck.add(pile10.get());
		dealDeck.add(pile9.get());
		dealDeck.add(pile8.get());
		dealDeck.add(pile7.get());
		dealDeck.add(pile6.get());
		dealDeck.add(pile5.get());
		dealDeck.add(pile4.get());
		dealDeck.add(pile3.get());
		dealDeck.add(pile2.get());
		dealDeck.add(pile1.get());

		return true;
	}
	/**
	 * Validate DealDeckMove.
	 * @param theGame ks.games.Solitaire
	 */
	public boolean valid(Solitaire theGame)
	{
		boolean validation = true;

		// VALIDATE:
		//    the deck can't be empty, and neither can the piles
		if (dealDeck.empty())
			validation = false;

		if (pile1.empty() || pile2.empty() || pile3.empty() || pile4.empty() || pile5.empty())
			validation = false;
		if (pile6.empty() || pile7.empty() || pile8.empty() || pile9.empty() || pile10.empty())
			validation = false;

		return validation;
	}
}
