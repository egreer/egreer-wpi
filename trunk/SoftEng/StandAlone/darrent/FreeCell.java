package darrent;

import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Hashtable;

import dijordan.PyramidGame;

import ks.client.gamefactory.GameWindow;
import ks.common.games.*;
import ks.common.model.*;
import ks.common.view.*;
import ks.common.controller.*;
import ks.launcher.Main;

/**
 * FreeCell game, developed for class cs3733 at WPI.
 * @author: Darren Torpey
 * @author: George Heineman [significant extensions]
 */
public class FreeCell extends ks.common.games.Solitaire implements SolvableSolitaire {
	
	// shuffled deals
	Hashtable<Integer,String> deals;
	
	/**
	 * Initializing Model Variables
	 */
	protected Deck deck;
	protected Column columns[] = new Column[8];
	protected Pile freeCells[] = new Pile[4];
	protected Pile baseCells[] = new Pile[4];

	
	/**
	 * Initializing View Variables
	 */
	protected ColumnView columnViews[] = new ColumnView[8];
	protected PileView freeCellViews[] = new PileView[4];
	protected PileView baseCellViews[] = new PileView[4];	

	protected IntegerView scoreView;

	//Move Types
	protected static final int columnColumn = 1;
	protected static final int columnFreeCell = 2;
	protected static final int freeCellFreeCell = 3;
	protected static final int freeCellColumn = 4;
	protected static final int freeCellSuitHigh = 5;
	protected static final int columnSuitHigh = 6;
	
	/** Entity names. */
	public static final String freeCellPrefix = "FreeCell";
	public static final String baseCellPrefix = "BaseCell";
	public static final String columnCellPrefix = "Column";
	
	/**
	 * FreeCell constructor comment.
	 */
	public FreeCell() {
		super();
	}
	
	/**
	 * getName method comment.
	 */
	public String getName() {
		return "FreeCell";
	}
	
	/**
	 * initialize method comment.
	 */
	public void initialize() {
	
		initializeModel(getSeed());
		initializeView();
		initializeController();
	
		//Now add the cards into their spots
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 7; i++) {
				columns[j].add(deck.get());
			}
		}
		for (int j = 4; j < 8; j++) {
			for (int i = 0; i < 6; i++) {
				columns[j].add(deck.get());
			}
		}
		
	}
	
	private void initializeController() {

		for (int i = 0; i < 4; i++) {
			freeCellViews[i].setMouseAdapter (new FreeCellFreeCellController (this, freeCellViews[i]));
			freeCellViews[i].setMouseMotionAdapter (new SolitaireMouseMotionAdapter (this));
			freeCellViews[i].setUndoAdapter (new SolitaireUndoAdapter (this));
		}

	
		for (int i = 0; i < 4; i++) {
			baseCellViews[i].setMouseAdapter (new FreeCellBasePileController (this, baseCellViews[i]));
			baseCellViews[i].setMouseMotionAdapter (new SolitaireMouseMotionAdapter (this));
			baseCellViews[i].setUndoAdapter (new SolitaireUndoAdapter (this));
		}
		
		for (int i = 0; i < 8; i++) {
			columnViews[i].setMouseAdapter (new FreeCellColumnController (this, columnViews[i]));
			columnViews[i].setMouseMotionAdapter (new SolitaireMouseMotionAdapter (this));
			columnViews[i].setUndoAdapter (new SolitaireUndoAdapter (this));
		}
			
		//Setup the containers' controllers
	    getContainer().setMouseMotionAdapter (new SolitaireMouseMotionAdapter (this));
		getContainer().setMouseAdapter (new SolitaireReleasedAdapter (this));
		getContainer().setUndoAdapter (new SolitaireUndoAdapter (this));

		//Setup the scoreViews' controllers
		scoreView.setMouseMotionAdapter(new SolitaireMouseMotionAdapter (this));
		scoreView.setMouseAdapter(new SolitaireReleasedAdapter (this));
		scoreView.setUndoAdapter(new SolitaireUndoAdapter (this));
	}
		
	// shuffle accordingly.
	private void freeCellShuffle (Deck deck, int seed) throws Exception {
		if (deals == null) {
			URL u = this.getClass().getResource ("/darrent/shuffles.hash");
			ObjectInputStream ois = new ObjectInputStream (u.openStream());
			deals = (Hashtable<Integer, String>) ois.readObject();
			ois.close();
		}
		
		// Gives cards AC - KC, AD - KD, AH - KH, AS - KS
		Card cards[] = new Card[52];
		CardEnumeration ce = new CardEnumeration();
		int idx = 0;
		while (ce.hasMoreElements()) {
			cards[idx] = ce.nextElement();
			idx+=4;
			if (idx >= 52) { idx = idx - 51; }
		}
		
		// now process the deal number
		String s = deals.get(seed);
		Card dealt[][] = new Card[21][9];
		int wLeft = 52;
		for (int i = 0; i < s.length(); i++) {
			int j = s.charAt(i) - '!';
			
			dealt[(i%8)+1][i/8] = cards[j];
			cards[j] = cards[--wLeft];
		}			
		
		//pull off each columns
		for (int c = 8; c >= 1; c--) {
			for (int r = 8; r >= 0; r--) {
				if (dealt[c][r] == null) continue;
				deck.add(dealt[c][r]);
				dealt[c][r] = null;
			}
		}
	}
	
	private void initializeModel (int seed) {
		deck = new Deck ("deck");
		try {
			freeCellShuffle(deck, seed);
		} catch (Exception e) {
			// default
			deck.shuffle(seed);
		}

		addModelElement (deck);

		for (int i = 0; i < columns.length; i++) {
			columns[i] = new Column(columnCellPrefix + (i+1));
			addModelElement (columns[i]);			
		}
		
		for (int i = 0 ; i < freeCells.length; i++) {
			freeCells[i] = new Pile (freeCellPrefix + (i+1));
			addModelElement (freeCells[i]);
		}

		//Named for purposes of determining suit being handled
		for (int i = 0 ; i < baseCells.length; i++) {
			baseCells[i] = new Pile (baseCellPrefix + (i+1));
			addModelElement (baseCells[i]);
		}
		
	}
	
	private void initializeView () {
		// to get the height and width of the CardView Widgets
		CardImages ci = getCardImages();

		
		int cw = ci.getWidth();
		int ch = ci.getHeight();

		//Setup each freeCellView, giving its bounds and puting it in its place
		for (int i = 0; i < freeCellViews.length; i++) {
			freeCellViews[i] = new PileView (freeCells[i]);
			freeCellViews[i].setBounds (10+15*i+i*cw, 20, cw, ch);
			addViewWidget (freeCellViews[i]);
		}

		//Setup each baseCellView
		for (int i = 0; i < baseCellViews.length; i++) {
			baseCellViews[i] = new PileView (baseCells[i]);
			baseCellViews[i].setBounds (125+15*i+(i+4)*cw, 20, cw, ch);
			addViewWidget (baseCellViews[i]);
		}		
		
		//Setup the scoreView
		scoreView  = new IntegerView(score);
		scoreView.setFontSize (24);
		scoreView.setBounds (75 + 4*cw, 30, 30, 30);
		addViewWidget (scoreView);

		//gets the proper dimensions by which to bound our collumns
		int colH = 13*ch; // allow up to 13 cards (will extend as necessary).

		for (int i = 0; i < 8; i++) {
			columnViews[i] = new ColumnView (columns[i]);
			columnViews[i].setBounds (45+15*i+i*cw, 40 + ch, cw, colH);
			addViewWidget (columnViews[i]);
		}
	}

	/**
	 * Returns the number of vacant freeCells *and* columns
	 */ 
	public int numberVacant () {
		int numEmpty = 0;
		for (int f = 0; f < freeCells.length; f++) {
			if (freeCells[f].empty()) numEmpty++;
		}
		
		// now count columns
		for (int c = 0; c < columns.length; c++) {
			if (columns[c].empty()) numEmpty++;
		}
		
		return numEmpty;		
	}

	/**
	 * Returns a Move object that can be auto-played in the given context,
	 * based on the logic that "A card is unneeded when no lower-rank cards of 
	 * the opposite color remain in the playing area". If no auto-moves are available,
	 * then null is returned.
	 */ 
	public Move autoMoveAvailable () {
		// 1. First see if any columnBaseMove allowed.
		for (int c = 0; c < columns.length; c++) {
			if (columns[c].empty()) continue;
			
			if (columns[c].rank() == Card.ACE) {
				
				// find the empty destination pile
				Pile emptyDest = null;
				for (int i = 0; i < baseCells.length; i++) {
					if (baseCells[i].empty()) {
						emptyDest = baseCells[i];
					}
				}
				
				// SANITY CHECK.
				if (emptyDest == null) {
					System.err.println ("FreeCell: impossible situation -- ACE is available to play but no open destination piles.");
					throw new IllegalStateException ("ACE is available to play but no open destination piles.");
				}
				
				return new ColumnBaseMove (columns[c], null, emptyDest);
			}
			
			Card cc = columns[c].peek();
			
			// try to find a destination it goes to.
			Move theMove = null;
			boolean foundMove = false;
			for (int b = 0; b<baseCells.length; b++) {
				theMove = new ColumnBaseMove (columns[c], null, baseCells[b]);
				if (theMove.valid (this)) {
					foundMove = true;
					break;
				}
			}
			
			// see if cards of next lower rank and opposite color are both played
			// in the foundation; we have to do this *two* levels since we need 
			// to know that all four suits are taken care of. Consider the decision
			// to place a 4H into a base pile; we need to know that both 3C and 3S
			// have been placed. We also need to know that the 2D has been played
			// (note: for a valid move we know that the 2H has been played).
			if (foundMove) {
				if (unneeded (cc.getRank(), cc.getSuit())) {
					int otherSuit = cc.getSuit();
					if ((otherSuit == Card.CLUBS) || (otherSuit == Card.SPADES)) {
						otherSuit = Card.HEARTS;  // arbitrary RED
					} else {
						otherSuit = Card.CLUBS; // arbitrary BLACK
					}
				
					// now go down one more level
					if (unneeded (cc.getRank()-1, otherSuit)) {
						return theMove;
					}
				}
			}
		}
		
		// 2. Second see if any FreeCellBaseMove allowed.
		Move theMove = null;
		boolean foundMove = false;
		Card bc = null;
		for (int f = 0; f < freeCells.length; f++) {
			if (freeCells[f].empty()) continue;
			
			// try to find a destination it goes to.
			for (int b = 0; b<baseCells.length; b++) {
				theMove = new FreeCellBaseMove (freeCells[f], null, baseCells[b]);
				if (theMove.valid (this)) {
					bc = freeCells[f].peek();
					foundMove = true;
					break;
				}
			}

			if (foundMove) {
				if (unneeded(bc.getRank(), bc.getSuit())) {
					int otherSuit = bc.getSuit();
					if ((otherSuit == Card.CLUBS)
						|| (otherSuit == Card.SPADES)) {
						otherSuit = Card.HEARTS; // arbitrary RED
					} else {
						otherSuit = Card.CLUBS; // arbitrary BLACK
					}

					// ACEs can be moved immediately...
					if (bc.getRank() == Card.ACE) {
						return theMove;
					}
					
					// now go down one more level
					if (unneeded(bc.getRank() - 1, otherSuit)) {
						return theMove;
					}
				}
				
				// no move allowed.
				return null;
			}
		}
		
		// if nothing found, stop
		if (!foundMove) {
			theMove = null;
		}
		
		return theMove;		
	}

	/**
	 * A card is unneeded when no lower-rank cards of the opposite color remain 
     * in the playing area. 
     * <p>
     * Returns TRUE if cards of (rank-1) and opposite colored suit have both
     * already been played to the foundation.
     * <p>
     * Note that true is returned if an ACE is passed in.
     */
	protected boolean unneeded(int rank, int suit) {
		// error situation.
		if (rank == Card.ACE) return true;
		
		// see if cards of next lower rank and opposite color are both played
		// in the foundation.
		int countOppositeColorLowerRank = 0;
		for (int b = 0; b < baseCells.length; b++) {
			if (baseCells[b].empty()) continue;
					
			Card bc = baseCells[b].peek();
			if (bc.oppositeColor (suit) && bc.getRank() >= rank-1) { 
				countOppositeColorLowerRank++;	
			}
		}
			
		// determine validity
		return (countOppositeColorLowerRank == 2);
	}

	/** 
	 * Determine when game is over.
	 * @see ks.games.Solitaire#hasWon()
	 */
	public boolean hasWon() {
		return (getScore().getValue() == 52);
	}

	/**
	 * Return set of available moves.
	 */
	public java.util.Enumeration<Move> availableMoves() {
		// TODO: Implement
		java.util.Vector<Move> v = new java.util.Vector<Move>();
		
		// 1. Try to move from free cells to foundation
		// 2. Try to move from column to foundation
		// 3. Try to move from column to column [
		// 4. Try to move from column to base
		return v.elements();
	}

	/**
	 * Make as many auto moves as possible.
	 */
	public void tryAutoMoves() {
		// TODO Auto-generated method stub
		Move m;
		do {
			m = autoMoveAvailable();
			if (m!= null) {
				if (m.doMove (this)) {
					pushMove (m);
					refreshWidgets();
				} else {
					// ERROR. Break now!
					break;
				}
			}
		} while (m != null);
	}
	
	/** Code to launch solitaire variation. */
	public static void main (String []args) {
		// Seed is to ensure we get the same initial cards every time.
		GameWindow gw = Main.generateWindow(new FreeCell(), 117);
		gw.setVisible(true);
	}
}
