package egreer;

import ks.client.gamefactory.GameWindow;
import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.games.Solitaire;
import ks.common.view.*;
import ks.common.model.*;
import ks.launcher.Main;

/**
 * NAME OF SOLITAIRE VARIATION: Sly Fox
 * 
 * DESCRIPTION:
 * Use two packs. Take an Ace of each suit and lay them in a column down the left 
 * side of the board as foundations, and do likewise with Kings on the right.
 * Between the two columns deal twenty cards face up in four rows of five,
 * forming the reserve.
 * 
 * The object of the game is to build the Aces up in suit to the Kings and the 
 * Kings down in suit to the Aces.
 * 
 * Build from the reserve as far as you can, filling spaces immediately from stock. 
 * When stuck, deal twenty more cards, one on each place in the reserve in any order 
 * you please. Use top cards of the reserve to build again as far as you can. The 
 * play of a top card releases the one below it for building on foundations if 
 * possible, but from now on do not replace cards from stock until you cannot 
 * (or will not) build any further. When that happens, deal another twenty cards 
 * as before. Keep playing in this way, replenishing the reserve not piecemeal but 
 * always twenty at a time, and not building until the deal is completed. Of course, 
 * the last deal may comprise fewer than twenty cards. Cards may not be reversed from 
 * suite to suite, and there is no redeal.
 * 
 * MODEL:
 *   deck: Two 52-card decks of playing cards
 *   reserveDeck: The pile for dealing out cards 
 *   flpile1 - flpile4: The foundation piles for the aces on the left
 *   frpile1 - frpile4: The foundation piles for the kings on the right 
 *   pile1 - pile20: The reserve piles(column objects) starting in the top left
 *   score: An integer representing the score of the game
 *   numLeft: An integer representing the number of cards left in the deck
 *   phase: current phase of the game
 * 
 * VIEW:
 * 
 *   DeckView for the deck. PileView for the piles. ColumnView for the columns.
 *   IntegerView for the MutableIntegers.
 * 
 * CONTROLLERS:
 *   Deck controller to deal cards to reserve Pile. 
 *   Foundation controller to regulate cards released on the foundation piles
 *   Reserve Controller to manage cards being dragged from the pile
 *   Column Controller to regulate cards being released, and to enable cards being dragged from
 * 
 * INITIALIZATION:
 * 
 *   shuffle decks
 *   pull out aces and kings and place on foundation piles
 *   deal one card face up to each column (pile1 - pile 20)
 *
 * EXECUTION:
 *   Launch this class as an application.
 * 
 * 
 * @author Eric Greer
 */

public class SlyFox extends Solitaire{
	
	/** The 2 merged Decks */
	protected MultiDeck deck;
	
	/** The Reserve "Deck" is a pile*/ 
	protected Pile reserveDeck;
	
	/** This is a trash pile for setting up the initial game */ 
	protected Pile trashPile;
	
	/** Eight Foundation Piles. Four on left, four on right */
	protected Pile flpile1, flpile2, flpile3, flpile4, frpile1, frpile2, frpile3, frpile4;
	
	/** 20 Column Stacks (named piles for simplicity) */
	protected Column pile1, pile2, pile3, pile4, pile5, pile6, pile7, pile8, pile9, pile10, pile11, pile12, pile13, pile14, pile15, pile16, pile17, pile18, pile19, pile20;
	
	/** The Eight Foundation Pile Views */
	protected PileView flpv1, flpv2, flpv3, flpv4, frpv1, frpv2, frpv3, frpv4;
	
	/** The 20 Column Views */
	protected ColumnView cv1, cv2, cv3, cv4, cv5, cv6, cv7, cv8, cv9, cv10, cv11, cv12, cv13, cv14, cv15, cv16, cv17, cv18, cv19, cv20;
	
	/** The display for the deck. */
	protected DeckView deckView;
	
	/** The display for the reserve deck */
	protected PileView reserveView;
	
	/** The display for the score. */
	protected IntegerView scoreView;

	/** Views for the number of cards left in the deck */
	protected IntegerView totalLeftView;
	
	/** Holds the phase the game is currently in */
	protected int phase;
	
	/** Holds the status of the columns when a card has been moved onto them
	 * index 0 holds number of columns updated
	 * index 1-20 hold individual columns (1 being set (dirty), 0 being not)
	 */
	protected int[] columnStatus = new int[21];
	
	/** Return the name of this solitaire variation. */
	public String getName() {
		return "Sly Fox";
	}

	/** Returns the current phase of this game. */
	public int getPhase(){
		return phase;
	}
	
	
	/** sets the current phase of the game */
	void setPhase(int t){
		phase = t;
	}

	
	/** Checks the status of the columns when adding a card 
	 * 
	 * @param	colnumber	the number of the column to be checked
	 * @return True means able to add card to column  
	 */
	public boolean checkColumn(int colNumber){
		boolean status = true;
		int t = columnStatus[colNumber];
		if (t != 0){
			status = false;
		}
		return status;
	}
	
	/** Adds the column to the list of dirty columns
	 * 
	 * @param	colnumber	the number of the column to be added
	 */
	public void addColumn(int colNumber){
		columnStatus[colNumber] = 1;
		columnStatus[0] = columnStatus[0] + 1;
	}
	
	/** removes the column to the list of dirty columns for undo
	 * 
	 * @param	colnumber	the number of the column to be removed
	 */
	public void removeColumn(int colNumber){
		columnStatus[colNumber] = 0;
		columnStatus[0] = columnStatus[0] - 1;
	}
	
	/** resets the list of dirty columns */
	public void resetColumnList(){
		for (int i = 0; i < 21 ; i++){ 
		columnStatus[i] = 0;
		}
		return;
	}
	
	/** saves the list of dirty columns */
	public int[] saveColumnList(){
		int[] returnArray = new int[21];
		for (int i = 0; i < 21 ; i++){ 
			returnArray[i] = columnStatus[i];
		}
		return returnArray;		
	}

	/** restores the list of dirty columns */
	public void  restoreColumnList(int[] wasSaved){
		columnStatus = wasSaved;
	}
	
	
	@Override
	public String getDeckType() {
		return "tiny";
	}

	/** Initializes the Foundations */
	private void initializeFoundations(){

		// Check and finds the cardsfor the foundations  
		while(deck.count() != 0){
			
			//Aces are in order of suit Club, diamond, heart, spade,
			//Kings are also in order of suit
			String topCard= deck.peek().toString();
		
			if (0 == topCard.compareTo("AC")){
				flpile1.add(deck.get());
			
			}else if (0 == topCard.compareTo("AD")){
				flpile2.add(deck.get());
				
			}else if (0 == topCard.compareTo("AH")){
				flpile3.add(deck.get());
			
			}else if (0 == topCard.compareTo("AS")){ 
				flpile4.add(deck.get());
				
			}else if (0 == topCard.compareTo("KC")){
				frpile1.add(deck.get());
				
			}else if (0 == topCard.compareTo("KD")){
				frpile2.add(deck.get());
				
			}else if (0 == topCard.compareTo("KH")){
				frpile3.add(deck.get());
				
			}else if (0 == topCard.compareTo("KS")){
				frpile4.add(deck.get());
			
			}else {
				trashPile.add(deck.get());
			}
		}
		
		//Put cards back on deck
		while(trashPile.count() != 0){
			deck.add(trashPile.get());
		}
	}
	
	/** Initializes the reserve piles */
	private void initializeReserve(){
		//Deal cards from deck
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
		pile11.add(deck.get());
		pile12.add(deck.get());
		pile13.add(deck.get());
		pile14.add(deck.get());
		pile15.add(deck.get());
		pile16.add(deck.get());
		pile17.add(deck.get());
		pile18.add(deck.get());
		pile19.add(deck.get());
		pile20.add(deck.get());
	}

	/** Initializes the controllers */	
	private void initializeController() {
		// Sets the deck, and reserve deck controllers 
		deckView.setMouseAdapter(new SlyFoxDeckController (this));
		reserveView.setMouseAdapter(new SlyFoxReserveController (this));
		
		//Sets the Left foundation controllers
		flpv1.setMouseAdapter(new SlyFoxFoundationController(this, flpv1));
		flpv2.setMouseAdapter(new SlyFoxFoundationController(this, flpv2));
		flpv3.setMouseAdapter(new SlyFoxFoundationController(this, flpv3));
		flpv4.setMouseAdapter(new SlyFoxFoundationController(this, flpv4));
		
		//Sets the Right foundation controllers
		frpv1.setMouseAdapter(new SlyFoxFoundationController(this, frpv1));
		frpv2.setMouseAdapter(new SlyFoxFoundationController(this, frpv2));
		frpv3.setMouseAdapter(new SlyFoxFoundationController(this, frpv3));
		frpv4.setMouseAdapter(new SlyFoxFoundationController(this, frpv4));
		
		//Sets the reserve controllers
		cv1.setMouseAdapter(new SlyFoxColumnController(this, cv1));
		cv2.setMouseAdapter(new SlyFoxColumnController(this, cv2));
		cv3.setMouseAdapter(new SlyFoxColumnController(this, cv3));
		cv4.setMouseAdapter(new SlyFoxColumnController(this, cv4));
		cv5.setMouseAdapter(new SlyFoxColumnController(this, cv5));
		cv6.setMouseAdapter(new SlyFoxColumnController(this, cv6));
		cv7.setMouseAdapter(new SlyFoxColumnController(this, cv7));
		cv8.setMouseAdapter(new SlyFoxColumnController(this, cv8));
		cv9.setMouseAdapter(new SlyFoxColumnController(this, cv9));
		cv10.setMouseAdapter(new SlyFoxColumnController(this, cv10));
		cv11.setMouseAdapter(new SlyFoxColumnController(this, cv11));
		cv12.setMouseAdapter(new SlyFoxColumnController(this, cv12));
		cv13.setMouseAdapter(new SlyFoxColumnController(this, cv13));
		cv14.setMouseAdapter(new SlyFoxColumnController(this, cv14));
		cv15.setMouseAdapter(new SlyFoxColumnController(this, cv15));
		cv16.setMouseAdapter(new SlyFoxColumnController(this, cv16));
		cv17.setMouseAdapter(new SlyFoxColumnController(this, cv17));
		cv18.setMouseAdapter(new SlyFoxColumnController(this, cv18));
		cv19.setMouseAdapter(new SlyFoxColumnController(this, cv19));
		cv20.setMouseAdapter(new SlyFoxColumnController(this, cv20));
		
		//Sets the Default Controllers with the stuff that doesn't expect any thing
		scoreView.setMouseAdapter (new SolitaireReleasedAdapter(this));
		totalLeftView.setMouseAdapter (new SolitaireReleasedAdapter(this));
		
		return;
	}

	/** Initializes the Models */
	private void initializeModel(int seed) {
		// initial score is set to 8
		// and there are 96 cards left in the deck.

		numLeft = getNumLeft();
		numLeft.setValue(96);
		score = getScore();
		score.setValue(8);
		
		// add to our model a deck 
		deck = new MultiDeck("deck", 2);
		deck.create(seed);
		addModelElement(deck);
		
		// add the reserve to the model
		reserveDeck = new Pile("reserveDeck");
		addModelElement(reserveDeck);
		
		//Create the Columns				
		pile1 = new Column("pile1");
		pile2 = new Column("pile2");
		pile3 = new Column("pile3");
		pile4 = new Column("pile4");
		pile5 = new Column("pile5");
		pile6 = new Column("pile6");
		pile7 = new Column("pile7");
		pile8 = new Column("pile8");
		pile9 = new Column("pile9");
		pile10 = new Column("pile10");
		pile11 = new Column("pile11");
		pile12 = new Column("pile12");
		pile13 = new Column("pile13");
		pile14 = new Column("pile14");
		pile15 = new Column("pile15");
		pile16 = new Column("pile16");
		pile17 = new Column("pile17");
		pile18 = new Column("pile18");
		pile19 = new Column("pile19");
		pile20 = new Column("pile20");
		
		// The Columns to the model
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
		addModelElement(pile11);
		addModelElement(pile12);
		addModelElement(pile13);
		addModelElement(pile14);
		addModelElement(pile15);
		addModelElement(pile16);
		addModelElement(pile17);
		addModelElement(pile18);
		addModelElement(pile19);
		addModelElement(pile20);
		
		//Create the Foundation Piles
		flpile1 = new Pile("flpile1");
		flpile2 = new Pile("flpile2");
		flpile3 = new Pile("flpile3");
		flpile4 = new Pile("flpile4");
		
		frpile1 = new Pile("frpile1");
		frpile2 = new Pile("frpile2");
		frpile3 = new Pile("frpile3");
		frpile4 = new Pile("frpile4");
		
		//Add the Foundation Piles
		addModelElement(flpile1);
		addModelElement(flpile2);
		addModelElement(flpile3);
		addModelElement(flpile4);
		
		addModelElement(frpile1);
		addModelElement(frpile2);
		addModelElement(frpile3);
		addModelElement(frpile4);
		
		//Adds the trash pile for setting the game up
		trashPile = new Pile("trashPile");
		addModelElement(trashPile);
	}

	/** Initializes the Views */
	private void initializeView() {
		// Get the card artwork to be used. This is needed for the dimensions.
		CardImages ci = getCardImages();

		//Sets the heights of the rows
		int heightrow1 = 100 + ci.getHeight();
		int heightrow2 = 140 + 2 * ci.getHeight();
		int heightrow3 = 180 + 3 * ci.getHeight();
		int heightrow4 = 220 + 4 * ci.getHeight();
		
		//Sets the width position of the foundation columns
		int widthflrow = 20;
		int widthfrrow = 400 + 6 * ci.getWidth();
		
		//Sets the width position of the columns
		int widthrow1 = 100 + ci.getWidth();
		int widthrow2 = 150 + 2 * ci.getWidth();
		int widthrow3 = 200 + 3 * ci.getWidth();
		int widthrow4 = 250 + 4 * ci.getWidth();
		int widthrow5 = 300 + 5 * ci.getWidth();
		
		//Column widget height
		int cwheight = ci.getHeight() + 30;
				
		//Set DeckView
		deckView = new DeckView(deck);
		deckView.setBounds(20, 20, ci.getWidth(), ci.getHeight());
		addViewWidget(deckView);
		
		//Set reserve Deck view
		reserveView = new PileView(reserveDeck);
		reserveView.setBounds(40 + ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		addViewWidget(reserveView);
		
		//Set ScoreView
		scoreView = new IntegerView(score);
		scoreView.setBounds(60 + 2 * ci.getWidth(), 10, 100, 60);
		addViewWidget(scoreView);

		//Set total number view left for deck
		totalLeftView = new IntegerView(numLeft);
		totalLeftView.setFontSize(24);
		totalLeftView.setBounds(10,  20 + ci.getHeight(), 50, 40);
		addViewWidget(totalLeftView);

		//Set Left Foundation Views
		flpv1 = new PileView(flpile1);
		flpv1.setBounds(widthflrow , heightrow1, ci.getWidth(), ci.getHeight());
		addViewWidget(flpv1);
	
		flpv2 = new PileView(flpile2);
		flpv2.setBounds(widthflrow , heightrow2, ci.getWidth(), ci.getHeight());
		addViewWidget(flpv2);
		
		flpv3 = new PileView(flpile3);
		flpv3.setBounds(widthflrow , heightrow3, ci.getWidth(), ci.getHeight());
		addViewWidget(flpv3);
		
		flpv4 = new PileView(flpile4);
		flpv4.setBounds(widthflrow , heightrow4, ci.getWidth(), ci.getHeight());
		addViewWidget(flpv4);
		

		//Set top row of columns 
		cv1 = new ColumnView(pile1);
		cv1.setBounds(widthrow1, heightrow1, ci.getWidth(), cwheight);
		addViewWidget(cv1);

		cv2 = new ColumnView(pile2);
		cv2.setBounds(widthrow2, heightrow1, ci.getWidth(), cwheight);
		addViewWidget(cv2);
		
		cv3 = new ColumnView(pile3);
		cv3.setBounds(widthrow3, heightrow1, ci.getWidth(), cwheight);
		addViewWidget(cv3);
		
		cv4 = new ColumnView(pile4);
		cv4.setBounds(widthrow4, heightrow1, ci.getWidth(), cwheight);
		addViewWidget(cv4);
		
		cv5 = new ColumnView(pile5);
		cv5.setBounds(widthrow5, heightrow1, ci.getWidth(), cwheight);
		addViewWidget(cv5);
		
		//Set Right Foundation Views
		frpv1 = new PileView(frpile1);
		frpv1.setBounds(widthfrrow, heightrow1, ci.getWidth(), ci.getHeight());
		addViewWidget(frpv1);
		
		frpv2 = new PileView(frpile2);
		frpv2.setBounds(widthfrrow, heightrow2, ci.getWidth(), ci.getHeight());
		addViewWidget(frpv2);
		
		frpv3 = new PileView(frpile3);
		frpv3.setBounds(widthfrrow, heightrow3, ci.getWidth(), ci.getHeight());
		addViewWidget(frpv3);
		
		frpv4 = new PileView(frpile4);
		frpv4.setBounds(widthfrrow, heightrow4, ci.getWidth(), ci.getHeight());
		addViewWidget(frpv4);
		
		//Set 2nd row of columns 
		cv6 = new ColumnView(pile6);
		cv6.setBounds(widthrow1, heightrow2, ci.getWidth(), cwheight);
		addViewWidget(cv6);

		cv7 = new ColumnView(pile7);
		cv7.setBounds(widthrow2, heightrow2, ci.getWidth(), cwheight);
		addViewWidget(cv7);
		
		cv8 = new ColumnView(pile8);
		cv8.setBounds(widthrow3, heightrow2, ci.getWidth(), cwheight);
		addViewWidget(cv8);
		
		cv9 = new ColumnView(pile9);
		cv9.setBounds(widthrow4, heightrow2, ci.getWidth(), cwheight);
		addViewWidget(cv9);
		
		cv10 = new ColumnView(pile10);
		cv10.setBounds(widthrow5, heightrow2, ci.getWidth(), cwheight);
		addViewWidget(cv10);
	
		//Set 3rd row of columns 
		cv11 = new ColumnView(pile11);
		cv11.setBounds(widthrow1, heightrow3, ci.getWidth(), cwheight);
		addViewWidget(cv11);

		cv12 = new ColumnView(pile12);
		cv12.setBounds(widthrow2, heightrow3, ci.getWidth(), cwheight);
		addViewWidget(cv12);
		
		cv13 = new ColumnView(pile13);
		cv13.setBounds(widthrow3, heightrow3, ci.getWidth(), cwheight);
		addViewWidget(cv13);
		
		cv14 = new ColumnView(pile14);
		cv14.setBounds(widthrow4, heightrow3, ci.getWidth(), cwheight);
		addViewWidget(cv14);
		
		cv15 = new ColumnView(pile15);
		cv15.setBounds(widthrow5, heightrow3, ci.getWidth(), cwheight);
		addViewWidget(cv15);
		
		//Set 4th row of columns 
		cv16 = new ColumnView(pile16);
		cv16.setBounds(widthrow1, heightrow4, ci.getWidth(), cwheight);
		addViewWidget(cv16);

		cv17 = new ColumnView(pile17);
		cv17.setBounds(widthrow2, heightrow4, ci.getWidth(), cwheight);
		addViewWidget(cv17);
		
		cv18 = new ColumnView(pile18);
		cv18.setBounds(widthrow3, heightrow4, ci.getWidth(), cwheight);
		addViewWidget(cv18);
		
		cv19 = new ColumnView(pile19);
		cv19.setBounds(widthrow4, heightrow4, ci.getWidth(), cwheight);
		addViewWidget(cv19);
		
		cv20 = new ColumnView(pile20);
		cv20.setBounds(widthrow5, heightrow4, ci.getWidth(), cwheight);
		addViewWidget(cv20);
		
	}

	/** Determine whether game has been won.
	 * @return 	True if there are 13 cards on each foundation and they are ascending for the left
	 * 			and descending if on the right.     
	 */
	public boolean hasWon() {
		if(flpile1.ascending() && flpile2.ascending() && 
				flpile3.ascending() && flpile4.ascending() &&
				frpile1.descending() && frpile2.descending() &&
				frpile3.descending() && frpile4.descending() &&
				deck.empty() && reserveDeck.empty() && 
				(flpile1.count() == 13) && (flpile2.count() == 13) &&
				(flpile3.count() == 13) && (flpile4.count() == 13) &&
				(frpile1.count() == 13) && (frpile2.count() == 13) &&
				(frpile3.count() == 13) && (frpile4.count() == 13))
		{
			return true;
		}
		
		return false;
	}
	
	/** Initialize the solitaire variation. */
	public void initialize() {
		// Initialize model, view, and controllers.
		initializeModel(getSeed());
		initializeView();
		initializeController();

		//initialize the phase
		setPhase(1);

		// we have Twenty Eight cards to deal.
		initializeFoundations();
		initializeReserve();
		updateNumberCardsLeft (-28);
	}
	
	
	/** Code to launch solitaire variation. */
	public static void main (String []args) {
		// Seed is to ensure we get the same initial cards every time.
		GameWindow gw = Main.generateWindow(new SlyFox(), 117);
		gw.setVisible(true);
	}
}
