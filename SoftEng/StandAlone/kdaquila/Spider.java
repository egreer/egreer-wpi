package kdaquila;

import java.awt.Dimension;

import ks.common.controller.SolitaireMouseMotionAdapter;
import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.model.BuildablePile;
import ks.common.model.Column;
import ks.common.model.Deck;
import ks.common.model.MultiDeck;
import ks.common.model.MutableInteger;
import ks.common.view.BuildablePileView;
import ks.common.view.CardImages;
import ks.common.view.DeckView;
import ks.common.view.IntegerView;
import ks.common.view.RowView;
import ks.common.games.SolitaireUndoAdapter;

/**
 * NAME OF SOLITAIRE VARIATION: Spider <br>
 * <p>
 * OVERVIEW:
 * The object of Spider Solitaire is to remove all of the cards from the ten stacks at <br>
 * the top of the window in the fewest number of moves. <br>
 * <p>
 * To remove cards from the ten stacks at the top of the window, move the cards from one <br>
 * column to another until you line up a suit of cards in order from king to ace. When <br>
 * you line up a complete suit, those cards are removed. <br>
 * <p>
 * Spider Solitaire is played with two decks of cards. When you begin a new game, ten <br>
 * stacks of cards, each with one card facing up, are dealt. The remaining cards are <br>
 * placed in five stacks in the bottom right corner of the window and are used when you <br>
 * deal a new row. <br>
 * <p>
 * Cards can be moved according to these rules: <br>
 * <p>
 * You can move a card from the top of a stack to an empty stack. <br>
 * You can move a card from the top of a stack to a card with the next highest value, <br>
 * regardless of suit or color. <br>
 * You can move a set of cards all of the same suit, and in order, as if they were one <br>
 * card. <br>
 * You can deal a new row of cards (one card face up on each of the ten stacks of cards). <br>
 * There must be at least one card in each stack before you can deal a new row of cards. <br>
 * <p>
 * The game is won by completing all eight stacks of 13 cards in order from king to ace. <br>
 * <p>
 * MODEL: <br>
 * <p>
 *   deck: a MultiDeck of 104 cards <br>
 *   pile1 - 10: ten BuildablePiles going from left to right <br>
 *   completeRow: completed bunches of cards are represented here, at the bottom left <br>
 *   dealDeck: a Deck to deal extra cards from, in the bottom right <br>
 *   score: a MutableInteger representing the score of the game <br>
 *   moves: a MutableInteger representing the number of moves the player has made <br>
 * <p>
 * INITIALIZATION: <br>
 * <p>
 *   Shuffle MultiDeck <br>
 *   Deal six cards face-down to the first four piles from the deck <br>
 *   Deal five cards face-down to the remaining six piles from the deck <br>
 *   Flip the top card of all ten piles <br>
 *   Put the remaining 50 cards in the dealDeck, face-down <br>
 *   NOTE: the completePiles start with no cards! <br>
 * <p>
 * @author: Kevin J. D'Aquila (kdaquila@wpi.edu)
 */

 //  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 // * NOTE: This plugin was written with the help of many resources provided by Professor Heineman  *
 // *      for WPI's CS3733 Software Engineering class.  In particular, the Narcotic Tutorial       *
 // *      plugin variations. See http://www.wpi.edu/~heineman/cs3733/ for course information and   *
 // *      http://www.wpi.edu/~heineman/cs3733/docs/ for KombatSolitaire documentation.             *
 //  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

public class Spider extends ks.common.games.Solitaire
{
	
	/** Each Game has a MultiDeck that is not seen. */
	protected MultiDeck deck;

	/** Ten BuildablePiles are for the majority of gameplay */
	protected BuildablePile pile1, pile2, pile3, pile4, pile5, pile6, pile7, pile8, pile9, pile10;

	/** A column will show the completed groups */
	protected Column completeRow;

	/** A separate deck is used for dealing extra cards during the game */
	protected Deck dealDeck;

	/** A couple of MutableIntegers to store the score and number of moves */
	protected MutableInteger score, moves;

	/** The View for the BuildablePiles */
	protected BuildablePileView pileView1, pileView2, pileView3, pileView4, pileView5, pileView6,
				    pileView7, pileView8, pileView9, pileView10; 

	/** View the completeColumn as a row */
	protected RowView completeRowView;

	/** The View for the dealDeck */
	protected DeckView dealDeckView;

	/** The View for score and moves */
	protected IntegerView scoreView, movesView;

	public static final int dealDeckMove = 1;
	public static final int moveCardMove = 2;
	public static final int moveBunchMove = 3;
	public static final int completeStackMove = 4;

/** Spider Constructor */
public Spider()
{
	super();
}
/** Used by Spider to return the moves value */
protected int getMovesValue()
{
	return moves.getValue();
}
/** Return the name of this solitaire variation. */
public String getName()
{
	return "Spider";
}
/** Override the standard table size to make it bigger */
public java.awt.Dimension getPreferredSize()
{
	Dimension d = new Dimension(804, 710);
	return d;
}
/** Return the version of this solitaire variation. */
public String getVersion()
{
	return "1.0";
}
/** Determine whether game has been won. */
public boolean hasWon()
{
	return (completeRow.count() == 8);
}
/** Initialize Spider */

public void initialize()
{
try {
	// Initialize everything.
	initializeModel(getSeed());
	initializeView();
	initializeController();

	// Add four cards to all piles, face-down
	for (int i = 0; i < 4; i++)
	{
		pile1.add(deck.get());
		pile2.add(deck.get());
		pile3.add(deck.get());
		pile4.add(deck.get());
		pile5.add(deck.get());
		pile6.add(deck.get());
		pile7.add(deck.get());
		pile8.add(deck.get());
		pile9.add(deck.get());
		pile10.add(deck.get());
		pile1.flipCard();
		pile2.flipCard();
		pile3.flipCard();
		pile4.flipCard();
		pile5.flipCard();
		pile6.flipCard();
		pile7.flipCard();
		pile8.flipCard();
		pile9.flipCard();
		pile10.flipCard();
	}
	
	// Add a fifth card to the first four piles, face-down
	pile1.add(deck.get());
	pile2.add(deck.get());
	pile3.add(deck.get());
	pile4.add(deck.get());
	pile1.flipCard();
	pile2.flipCard();
	pile3.flipCard();
	pile4.flipCard();

	// Add one more card to each pile, face-up.
	pile1.add(deck.get());
	pile2.add(deck.get());
	pile3.add(deck.get());
	pile4.add(deck.get());
	pile5.add(deck.get());
	pile6.add(deck.get());
	pile7.add(deck.get());
	pile8.add(deck.get());
	pile9.add(deck.get());
	pile10.add(deck.get());
	
	// Add remaining 50 cards to the dealDeck
	for (int i = 0; i < 50; i++) {
		dealDeck.add(deck.get());
	}


} catch (Exception e) {
	System.out.println(e);
}


}
/** Prepare the controllers. */
private void initializeController() {

	// Adapters for the dealDeckView
	dealDeckView.setMouseAdapter(new SpiderDealDeckController(this));
	dealDeckView.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	dealDeckView.setUndoAdapter (new SolitaireUndoAdapter(this));

	// Adapters for the Piles
	pileView1.setMouseAdapter(new SpiderBuildablePileController(this, pileView1));
	pileView2.setMouseAdapter(new SpiderBuildablePileController(this, pileView2));
	pileView3.setMouseAdapter(new SpiderBuildablePileController(this, pileView3));
	pileView4.setMouseAdapter(new SpiderBuildablePileController(this, pileView4));
	pileView5.setMouseAdapter(new SpiderBuildablePileController(this, pileView5));
	pileView6.setMouseAdapter(new SpiderBuildablePileController(this, pileView6));
	pileView7.setMouseAdapter(new SpiderBuildablePileController(this, pileView7));
	pileView8.setMouseAdapter(new SpiderBuildablePileController(this, pileView8));
	pileView9.setMouseAdapter(new SpiderBuildablePileController(this, pileView9));
	pileView10.setMouseAdapter(new SpiderBuildablePileController(this, pileView10));
	pileView1.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	pileView2.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	pileView3.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	pileView4.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	pileView5.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	pileView6.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	pileView7.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	pileView8.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	pileView9.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	pileView10.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	pileView1.setUndoAdapter(new SolitaireUndoAdapter(this));
	pileView2.setUndoAdapter(new SolitaireUndoAdapter(this));
	pileView3.setUndoAdapter(new SolitaireUndoAdapter(this));
	pileView4.setUndoAdapter(new SolitaireUndoAdapter(this));
	pileView5.setUndoAdapter(new SolitaireUndoAdapter(this));
	pileView6.setUndoAdapter(new SolitaireUndoAdapter(this));
	pileView7.setUndoAdapter(new SolitaireUndoAdapter(this));
	pileView8.setUndoAdapter(new SolitaireUndoAdapter(this));
	pileView9.setUndoAdapter(new SolitaireUndoAdapter(this));
	pileView10.setUndoAdapter(new SolitaireUndoAdapter(this));

	// Adapters for the IntegerViews
	scoreView.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	scoreView.setMouseAdapter(new SolitaireReleasedAdapter(this));
	scoreView.setUndoAdapter(new SolitaireUndoAdapter(this));
	movesView.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	movesView.setMouseAdapter(new SolitaireReleasedAdapter(this));
	movesView.setUndoAdapter(new SolitaireUndoAdapter(this));

	completeRowView.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	completeRowView.setMouseAdapter(new SolitaireReleasedAdapter(this));
	completeRowView.setUndoAdapter(new SolitaireUndoAdapter(this));

	// Finally, cover the Container for any events not handled by a widget:
	getContainer().setMouseAdapter(new SolitaireReleasedAdapter(this));
	getContainer().setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	getContainer().setUndoAdapter(new SolitaireUndoAdapter(this));
}
/**
 * Initialize the Model for Spider.
 * @param seed int
 */
private void initializeModel(int seed)
{
try {

	deck = new MultiDeck("deck", 2); // Start a deck with double the cards (104)
	deck.create(seed);               // create the deck
	addModelElement(deck);			 // Add it
	dealDeck = new Deck("dealDeck"); // Start the dealDeck (no cards for now) 
	addModelElement(dealDeck);       // Add it

	// Make all ten BuildablePiles
	pile1 = new BuildablePile("pile1");
	pile2 = new BuildablePile("pile2");
	pile3 = new BuildablePile("pile3");
	pile4 = new BuildablePile("pile4");
	pile5 = new BuildablePile("pile5");
	pile6 = new BuildablePile("pile6");
	pile7 = new BuildablePile("pile7");
	pile8 = new BuildablePile("pile8");
	pile9 = new BuildablePile("pile9");
	pile10 = new BuildablePile("pile10");

	// Make the completeRow
	completeRow = new Column("completeRow");

	// Add them to our model
	addModelElement(pile1);
	addModelElement(pile2);
	addModelElement(pile3);
	addModelElement(pile4);
	addModelElement(pile5);
	addModelElement(pile6);
	addModelElement(pile7);
	addModelElement(pile8);
	addModelElement(pile9);
	addModelElement(pile10);

	addModelElement(completeRow);

	// Initial score is set to 500, not zero!
	// Initial moves is set to zero.
	// NOTE: Score will be added to the model by solitaire Base Class.
	//       Moves will be added by Spider's setMoves method.
	updateScore (500);
	setMoves(new MutableInteger(0));

} catch (Exception e) {
System.out.println(e);
}

}
/** Prepare the boundary objects. */
private void initializeView()
{
	// Get the card artwork to be used. This is needed for the dimensions.
	CardImages ci = getCardImages();

	// Variables used for formulas below
	int cw = ci.getWidth();
	int ch = ci.getHeight();
	int space = (800 - (10 * cw)) / 11;

	// Create all our view objects
	pileView1 = new BuildablePileView(pile1);
	pileView2 = new BuildablePileView(pile2);
	pileView3 = new BuildablePileView(pile3);
	pileView4 = new BuildablePileView(pile4);
	pileView5 = new BuildablePileView(pile5);
	pileView6 = new BuildablePileView(pile6);
	pileView7 = new BuildablePileView(pile7);
	pileView8 = new BuildablePileView(pile8);
	pileView9 = new BuildablePileView(pile9);
	pileView10 = new BuildablePileView(pile10);
	dealDeckView = new DeckView(dealDeck);
	completeRowView = new RowView(completeRow);
	scoreView = new IntegerView(getScore());
	movesView = new IntegerView(moves);

	// Set the place on the screen
	pileView1.setBounds(space * 0 + (cw * 0) + space, 10, cw, 408);
	pileView2.setBounds(space * 1 + (cw * 1) + space, 10, cw, 408);
	pileView3.setBounds(space * 2 + (cw * 2) + space, 10, cw, 408);
	pileView4.setBounds(space * 3 + (cw * 3) + space, 10, cw, 408);
	pileView5.setBounds(space * 4 + (cw * 4) + space, 10, cw, 408);
	pileView6.setBounds(space * 5 + (cw * 5) + space, 10, cw, 408);
	pileView7.setBounds(space * 6 + (cw * 6) + space, 10, cw, 408);
	pileView8.setBounds(space * 7 + (cw * 7) + space, 10, cw, 408);
	pileView9.setBounds(space * 8 + (cw * 8) + space, 10, cw, 408);
	pileView10.setBounds(space * 9 + (cw * 9) + space, 10, cw, 408);
	dealDeckView.setBounds(800 - cw - 15, 420, cw, ch);
	completeRowView.setBounds(15, 420, (int)(cw + (2.4 * cw)), ch);

	scoreView.setFontSize(24);
	movesView.setFontSize(24);
	scoreView.setBounds(370, 440, 100, 35);
	movesView.setBounds(370, 480, 100, 35);

	// Add all our view objects

	addViewWidget(pileView1);
	addViewWidget(pileView2);
	addViewWidget(pileView3);
	addViewWidget(pileView4);
	addViewWidget(pileView5);
	addViewWidget(pileView6);
	addViewWidget(pileView7);
	addViewWidget(pileView8);
	addViewWidget(pileView9);
	addViewWidget(pileView10);
	addViewWidget(dealDeckView);
	addViewWidget(completeRowView);	
	addViewWidget(scoreView);
	addViewWidget(movesView);
}
/**
 * Used by Spider exclusively to set up moves for the game
 * <p>
 * If this method is called multiple times, only the value is transferred
 * to the existing model element <code>moves</code>.
 * @param miMoves    A MutableInteger representing the moves of the game.
 */
protected void setMoves(ks.common.model.MutableInteger miMoves) {
	if (miMoves == null) {
		throw new IllegalArgumentException ("Spider::setMoves() received null parameter.");
	}

	// add to our model as a moves object
	miMoves.setName("moves");
	if (model.addElement (miMoves) == false) {
	// we already have moves, so transfer value.  This will generate a stateChange event.
		moves.setValue(miMoves.getValue());
	} else {
		moves = miMoves;
	}
}
/** Used by Spider to update the moves in the game */
protected void updateMoves(int delta)
{
	moves.setValue(moves.getValue() + delta);
}
}
