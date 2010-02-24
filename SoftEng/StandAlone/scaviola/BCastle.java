package scaviola;

import java.awt.event.MouseEvent;
import ks.common.games.SolitaireUndoAdapter;
import ks.common.model.*;
import ks.common.view.*;
import ks.common.controller.*;
import java.awt.Dimension;

/**
 *
 * NAME OF SOLITAIRE VARIATION: Beleaguered Castle
 * <p>
 * DESCRIPTION:
 * Place four aces in a center line and deal remaining cards creating four left-facing 
 * leaning rows on the left of the aces and four right-facing rows on the right of the aces. 
 * Pile all cards into thirteen-card sequences on the goal piles (at the center of the board), 
 * following suit and upward in sequence starting with the Aces.
 * Cards on the table stack downward in sequence, regardless of suit. 
 * Any card can be used to fill an empty row.  One card can move at a time.
 *
 * <p>
 * MODEL:
 * <p>
 *   deck: one 48-card deck of playing cards with aces removed.<br>
 *   pile1: topmost pile in middle.<br>
 *   pile2: second middle pile, directly under pile1<br>
 *   pile3: third middle pile<br>
 *   pile4: fourth middle pile<br>
 *   lrow1: topmost left-facing row, to the left of the piles<br>
 *   lrow2: second left-facing row, to the left of the piles, directly under the first.<br>
 *   lrow3: third left-facing row, to the left of the piles<br>
 *   lrow4: fourth left-facing row, to the left of the piles<br>
 *   rrow1: topmost right-facing row, to the right of the piles<br>
 *   rrow2: second right-facing row, to the right of the piles, directly under the first.<br>
 *   rrow3: third right-facing row, to the right of the piles<br>
 *   rrow4: fourth right-facing row, to the right of the piles<br>
 *   score: an integer representing the score of the game<br>
 * <p>
 * INITIALIZATION:
 * <p>
 *   shuffle deck<br>
 *   Put Aces on pile1, pile2, pile3, pile4<br>
 *   Deal remaining cards onto the rows<br>
 * <p>
 * CONTROLLER:
 * <p>
 * There are two actions:<br>
 *   1. Move a card from a row to a pile<br>
 *   2. Move a card from one row to another row<br>
 *
 *   A card can be moved to a pile if it is the same suit as the pile and has one 
 *   greater rank than the card on the top of the pile, starting with the ace.<br>
 *   A card can be moved between rows if the card has one less rank than the row
 *   it is moving to.<br>
 * <p>
 *    Note: When clicking and dragging on a widget, the widget produces 
 *    a new widget that can be dragged around on the screen. When asked,
 *    Pile will generate a CardView widget to manage the single card 
 *    that is being dragged.
 * <p>
 *
 * Creation date: (12/02/01 8:30 PM)
 * Cleaned up by Heineman (1/8/02 10:15 PM)
 * @author: Mike Scaviola (scaviola@wpi.edu)
 */
public class BCastle extends ks.common.games.Solitaire {
	/** 48 card deck with no ACES */
	protected BCastleDeck deck;

	/** The four piles  */
	protected Pile piles[] = new Pile[4];

	/** Four left-facing rows (0..3) and four right-facing (4..7)*/
	protected Column rows[] = new Column[8];

	/** The Piles */
	protected PileView pileViews[] = new PileView[4];

	/** The Rows. Four left-facing (0..3) and four right-facing (4..7) */
	protected RowView rowViews[] = new RowView[8];

	/** The display for the score. */
	protected IntegerView scoreView;

	/** Move Types. */
	protected static final int moveCard   = 1;

	  
	/** The constructor! */
	public BCastle() {
		super();
	}

/**
 * React to the mouse click (both a press and release) on a particular
 * RowView object.
 * <p>
 * Creation date: (10/4/01 5:35:42 PM)
 * @param cv ks.common.view.ColumnView
 */
public void clickCardController(RowView rv, MouseEvent me) {
	// Respond to DoubleClick events
	if (me.getClickCount() > 1) {
		Column theRow = (Column) rv.getModelElement();
		if (theRow == null) {
			throw new IllegalArgumentException ("BCastle::clickCardController() received invalid RowView.");
		}
	}
}

	/**
	 * Return the number of empty Beleaguered Rows.
	 */
	public int countEmpty () {
		int numEmpty = 0;
		for (int i = 0; i < rows.length; i++) {
			if (rows[i].empty()) numEmpty++;
		}
		
		return numEmpty;
	}
	
	/**
	 * Return the number of empty Beleaguered Rows *except* for given from/target.
	 */
	public int countEmptyExcept (Column from, Column target) {
		int numEmpty = 0;
		for (int i = 0; i < rows.length; i++) {
			if (rows[i] == target) continue;  // SKIP target column. 
			if (rows[i] == from) continue;  // SKIP from column.
			if (rows[i].empty()) numEmpty++;
		}
		
		return numEmpty;
	}
	
	/** Return the name of this solitaire variation. */
	public String getName() {
		return "Beleaguered Castle";
	}

	/**
	 * Adjust the default window size to accomodate the BCastle game
	 */
	public Dimension getPreferredSize() {
		return new Dimension (850, 700);
	}

	/** Determines whether solitaire plugin has won. */
	public boolean hasWon () {
		return getScore().getValue() == 52;
	}
	
	/** Initialize solitaire variation. */
	public void initialize() {
		// Initialize model, view, and controllers.
		initializeModel(getSeed());
		initializeView();
		initializeController();
	
		// Prepare game AFTER all controllers are set up.
		// each column gets a card from the deck.
		for (int i=0, suit=Card.CLUBS; i < piles.length; i++, suit++) {
			piles[i].add (new Card (Card.ACE, suit)); 
		}
		
		for (int i=0; i<8; i++) {
			for (int j =0; j < 6; j++) {
				rows[i].add (deck.get());
			}		
		}
	}
/**
 * Prepare the controllers.
 * <p>
 * Creation date: (10/3/01 9:34:34 PM)
 */
private void initializeController() {
	
	// other controllers will need to be developed also...
	/** For quick reference to these ColumnViews. */

	// Now for each pile.
	for (int i = 0; i < pileViews.length; i++) {
		pileViews[i].setMouseAdapter (new BCastlePileController (this, pileViews[i]));
		pileViews[i].setMouseMotionAdapter (new SolitaireMouseMotionAdapter (this));
		pileViews[i].setUndoAdapter (new SolitaireUndoAdapter(this));
	}

	for (int i = 0; i < rowViews.length; i++) {
		rowViews[i].setMouseAdapter (new BCastleRowController (this, rowViews[i]));
		rowViews[i].setMouseMotionAdapter (new SolitaireMouseMotionAdapter (this));
		rowViews[i].setUndoAdapter (new SolitaireUndoAdapter(this));
	}

	// default controller for ScoreView
	scoreView.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	scoreView.setMouseAdapter (new SolitaireReleasedAdapter(this));
	scoreView.setUndoAdapter (new SolitaireUndoAdapter(this));

	// Finally, cover the Container for any events not handled by a widget:
	getContainer().setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	getContainer().setMouseAdapter (new SolitaireReleasedAdapter(this));
	getContainer().setUndoAdapter (new SolitaireUndoAdapter(this));
}
/**
 * Initialize the Model for BCastle.
 * <p>
 * Initialize the deck, add all the piles and rows and set the score to zero.<br>
 *
 * Creation date: (12/3/01 8:30 PM)
 * @param seed int
 */
private void initializeModel(int seed) {
	deck = new BCastleDeck("deck");
	deck.create(seed);

	// add to our model (as defined within our superclass).
	addModelElement (deck);
	
	for (int i = 0; i < piles.length; i++) {
		piles[i] = new Pile ("pile" + (i+1));
		addModelElement (piles[i]);
	}

	for (int i = 0; i < rows.length; i++) {
		rows[i] = new Column ("row" + (i+1));
		addModelElement (rows[i]);
	}
	
	

}
/**
 * Prepare the view.
 * <p>
 * Sets the positions and attributes of all View objects.
 * Creation date: (10/3/01 9:34:34 PM)
 */
private void initializeView() {
	// Get the card artwork to be used. This is needed for the dimensions.
	CardImages ci = getCardImages();
	
	int ch = ci.getHeight();
	int cw = ci.getWidth();
	for (int i = 0; i < 4; i++) {
		pileViews[i] = new PileView (piles[i]);
		pileViews[i].setBounds (380, 10 + 15*i + ch*i, cw, ch);
		addViewWidget (pileViews[i]);
	}

	scoreView = new IntegerView (getScore());
	scoreView.setBounds (370, 55 + 4*ch, 120, 70);
	addViewWidget (scoreView);

	for (int i = 0; i < 4; i++) {
		rowViews[i] = new RowView (rows[i]);
		rowViews[i].setBounds (380 - 5*cw - 20, 10 + 15*i + i*ch, 5*cw, ch);
		rowViews[i].setDirection (0);
		rowViews[i].setJustification (1);
		addViewWidget (rowViews[i]);		 
	}

	for (int i = 0; i<4; i++) {
		rowViews[i+4] = new RowView (rows[i+4]);
		rowViews[i+4].setBounds (380 + cw + 20, 10 + 15*i + i*ch, 5*cw, ch);
		rowViews[i+4].setDirection (1);
		addViewWidget (rowViews[i+4]);
		
	}
}
}
