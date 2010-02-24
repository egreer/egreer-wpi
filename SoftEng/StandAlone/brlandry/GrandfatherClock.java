package brlandry;

import java.awt.Dimension;

import ks.common.controller.SolitaireMouseMotionAdapter;
import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.games.Solitaire;
import ks.common.games.SolitaireUndoAdapter;
import ks.common.model.Column;
import ks.common.model.Pile;
import ks.common.view.CardImages;
import ks.common.view.ColumnView;
import ks.common.view.IntegerView;
import ks.common.view.PileView;

/**Grandfather's Clock:
 * <P>This game requires a 1024x768 monitor
 * 
 * <P>Foundations (12 piles: the "Clock". Complete these piles to win the game)
 * <P>Base Cards
 * <BR>12:	9 clubs
 * <BR>1:	10 hearts
 * <BR>2:	Jack spades
 * <BR>3:	Queen diamonds
 * <BR>4:	King clubs
 * <BR>5:	2 hearts
 * <BR>6:	3 spades
 * <BR>7:	4 diamonds
 * <BR>8:	5 clubs
 * <BR>9:	6 hearts
 * <BR>10:	7 spades
 * <BR>11:	8 diamonds
 * 
 * <P>Build up in suit from the base card until the top card of each foundation 
 * shows the number corresponding to its position on a clock face (the "hour" 
 * in a clock in that position). For example, the 3 of spades at the bottom 
 * would build up to the 6 of spades (6 o'clock).
 * <P>Jack equals 11 o'clock and Queen equals 12 o'clock.
 * <P>Ranking of cards is continuous: build Aces after Kings when necessary.
 * <P>All the piles will require 3 cards, except the Ten, Jack, Queen and King, 
 * which will require 4 cards each.
 * 
 * <P>Tableau (8 columns of 5 cards each)
 * 
 * <P>Randomly dealt from remaining cards.
 * 
 * <P>Build down regardless of suit (for example, a 5 can be played on any 6).
 * <P>The top card of each pile is available for play to another tableau pile 
 * or to the foundations.
 * <P>Only one card at a time can be moved.
 * <P>Spaces may be filled with any available card.
 * 
 * @author Brian Landry
 * @version 1.0
 */
public class GrandfatherClock extends Solitaire {
	protected GrandfatherDeck gDeck;
	protected Column[] columns = new Column[8];
	protected Pile[] piles = new Pile[12];
	protected ColumnView[] cv = new ColumnView[8];
	protected PileView[] pv = new PileView[12];
	protected IntegerView scoreView;
	
	/**Constant used to identify moves
	 */
	public static final int columnToPile = 1;
	/**Contant used to identify moves
	 */
	public static final int columnToColumn = 2;
	
	/**Version
	 */
	protected String version = "1.0";
	
	/**Initializes games: creates entity, boundary, and view objects.  Deals cards.
	 */
	public void initialize(){
		//call helper functions
		initializeModel(seed);
		initializeView();
		initializeControllers();
		
		//System.out.println("initialize: Dealing to foundations");
		
		//GrandfatherDeck was revised to eliminate the need to create the cards
		//on the bottom of each foundation.  Now, they are placed in a particular
		//order on the top of the deck.  Here, we deal them to the foundations.
		for(int i=0; i<12; i++)
			piles[i].add(gDeck.get());
		
		//System.out.println("initialize: Dealing cards to tablaeu");
		for(int i=0; i<5; i++)
			for(int j=0; j<columns.length; j++)
				columns[j].add(gDeck.get());
		
		//System.out.println("initialize: complete");
	}
	
	/**Initializes entity objects
	 */
	protected void initializeModel(int seed){
	
		//create deck
		gDeck = new GrandfatherDeck("gDeck");
		gDeck.create(seed);
		this.addModelElement(gDeck);
		
		int i;
		//create tableau
		for(i=0; i<columns.length; i++){
			columns[i] = new Column("col" + i);
			this.addModelElement(columns[i]);
		}
		
		//create foundations
		for(i=0; i<piles.length; i++){
			piles[i] = new Pile("pile" + i);
			this.addModelElement(piles[i]);
		}
		
		//Set score to 8
		updateScore(8);
	}
	
	/**Initializes view objects
	 */
	protected void initializeView(){
	
		//load card images and extract dimensions for easier reference
		CardImages ci = this.getCardImages();
		int cWidth = ci.getWidth();
		int cHeight = ci.getHeight();
		
		//create boundary objects for tableau and foundations
		int i;
		for(i=0; i<cv.length; i++)
			cv[i] = new ColumnView(columns[i]);
		
		for(i=0; i<pv.length; i++)
			pv[i] = new PileView(piles[i]);
		
		//boundary object for score
		scoreView = new IntegerView(score);
		
		//System.out.println("initializeView: Setting bounds for tableau");
		//set bounds for tableau
		for(i=0; i<4; i++){
			cv[i].setBounds(20+i*(cWidth+10), 20, cWidth, cHeight+160);
			cv[i+4].setBounds(20+i*(cWidth+10), 300, cWidth, cHeight+160);
		}
		
		//set bounds for foundations
		pv[0].setBounds(120+7*cWidth, 20, cWidth, cHeight);
		pv[1].setBounds(130+8*cWidth, 50, cWidth, cHeight);
		pv[2].setBounds(140+9*cWidth, 90, cWidth, cHeight);
		pv[3].setBounds((int)(140+9.5*cWidth), 110+cHeight, cWidth, cHeight);
		pv[4].setBounds(140+9*cWidth, 130+2*cHeight, cWidth, cHeight);
		pv[5].setBounds(130+8*cWidth, 170+2*cHeight, cWidth, cHeight);
		pv[6].setBounds(120+7*cWidth, 200+2*cHeight, cWidth, cHeight);
		pv[7].setBounds(110+6*cWidth, 170+2*cHeight, cWidth, cHeight);
		pv[8].setBounds(100+5*cWidth, 130+2*cHeight, cWidth, cHeight);
		pv[9].setBounds((int)(100+4.5*cWidth), 110+cHeight, cWidth, cHeight);
		pv[10].setBounds(100+5*cWidth, 90, cWidth, cHeight);
		pv[11].setBounds(110+6*cWidth, 50, cWidth, cHeight);
		
		scoreView.setBounds(610, 210, 100, 65);
		
		//Add widgets to game
		for(i=0; i<cv.length; i++)
			this.addViewWidget(cv[i]);
		
		for(i=0; i<pv.length; i++)
			this.addViewWidget(pv[i]);
		
		this.addViewWidget(scoreView);
	
		return;
	}
	
	/**Initialize boundary objects
	 */
	protected void initializeControllers(){
		
		for(int i=0; i<cv.length; i++){
			cv[i].setMouseAdapter(new GrandfatherColumnController(this, cv[i]));
			cv[i].setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
			cv[i].setUndoAdapter(new SolitaireUndoAdapter(this));
		}
		
		for(int i=0; i<pv.length; i++){
			pv[i].setMouseAdapter(new GrandfatherPileController(this, pv[i]));
			pv[i].setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
			pv[i].setUndoAdapter(new SolitaireUndoAdapter(this));
		}
		
		//Allows MouseEvents over the container or score to be handled
		scoreView.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		//scoreView.setMouseAdapter(new GrandfatherReleasedAdapter(this));
		scoreView.setMouseAdapter(new SolitaireReleasedAdapter(this));		
		scoreView.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		getContainer().setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		//getContainer().setMouseAdapter(new GrandfatherReleasedAdapter(this));
		getContainer().setMouseAdapter(new SolitaireReleasedAdapter(this));		
		getContainer().setUndoAdapter(new SolitaireUndoAdapter(this));
	}
	
	/**Examines the score to determine if player has won
	 */
	public boolean hasWon(){
		return (getScoreValue()==208);
	}
	
	/**Returns name of game
	 */
	public String getName() {
		return "GrandfatherClock";
	}
	
	/**Returns version
	 */
	public String getVersion(){
		return version;
	}
	
	/**Overrides function from Solitaire to initialize the screen to a larger size.
	 * Again, note that this plugin requires a monitor with 1024x768 resolution.
	 */
	public Dimension getPreferredSize(){
		Dimension d = new Dimension(1024, 750);
		return d;
	}
	
	
	/**A function that generates random integers 0<=x<max
	 * @param max int the maximum value for the random integer
	 */
	protected static int randomNumber(int max){
		double rand = max*Math.random();
		int random = (int) rand;
		return random;
	}
}