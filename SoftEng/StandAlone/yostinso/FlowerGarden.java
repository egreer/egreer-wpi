package yostinso;

import java.awt.Dimension;
import java.awt.Toolkit;

import ks.common.controller.SolitaireMouseMotionAdapter;
import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.games.Solitaire;
import ks.common.games.SolitaireUndoAdapter;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Deck;
import ks.common.model.MutableInteger;
import ks.common.view.CardImages;
import ks.common.view.IntegerView;


/**
 * FlowerGarden solitaire variation
 * Creation date: (11/29/2001 11:02:13 AM)
 * @author: E.O. Stinson
 */
public class FlowerGarden extends Solitaire {
	/** Are we awaiting loading of SuitPile images? */
	private boolean neverInitialized = true;

	// Create an object to hold the deck
	protected Deck deck = null;
	
	// Create objects to hold the GardenColumns
	protected Column gardenColumns[] = new Column[6];

	// Create objects to hold the four SuitPiles
	// CLUBS=1, SPADES=4
	protected SuitPile suitPiles[] = new SuitPile[5]; // 1 more to simplify algorithms
//	protected SuitPile clubs = null;
//	protected SuitPile diamonds = null;
//	protected SuitPile spades = null;

	// Create object to hold the GardenRow
	protected GardenRow bouquet = null;

	// Create the objects to hold the views
	protected GardenColumnView gardenColumnViews[] = new GardenColumnView[6];

	protected SuitPileView suitPileViews[] = new SuitPileView[5]; // 1 more to simplify algorithms
//	protected SuitPileView pileViewClubs = null;
//	protected SuitPileView pileViewDiamonds = null;
//	protected SuitPileView pileViewSpades = null;

	protected GardenRowView bouquetView = null;
	protected IntegerView scoreView = null;

	// Unique id numbers for the legal moves
	public static final int moveGCGCMove = 1;
	public static final int moveGRGCMove = 2;
	public static final int moveGCSPMove = 3;
	public static final int moveGRSPMove = 4;


/**
 * FlowerGarden constructor comment.
 */
public FlowerGarden() {
	super();
}
/**
 * Returns the number of empty GardenColumns *without counting* target
 * Note: pass in null if you just want to get a count of the number of
 * available columns. There are times we want to avoid a target column
 * to ensure we aren't making an illegal move.
 * Creation date: (12/3/2001 10:34:10 AM)
 * @return int
 */
//public int getEmptyColumnCount(Column target) {
//	int emptyColumns = 0;
//	for (int i = 0; i < gardenColumns.length; i++) {
//		// skip target.
//		if (gardenColumns[i] == target) continue;
//		if (gardenColumns[i].empty()) emptyColumns++;
//	}
//	
//	return emptyColumns;
//}

/**
 * Returns the number of empty GardenColumns *without counting* source
 * and target.
 * Note: pass in null if you just want to get a count of the number of
 * available columns. There are times we want to avoid a target column
 * to ensure we aren't making an illegal move.
 * Creation date: (12/3/2001 10:34:10 AM)
 * @return int
 */
public int getEmptyColumnCount(Column source, Column target) {
	int emptyColumns = 0;
	for (int i = 0; i < gardenColumns.length; i++) {
		// skip source && target.
		if (gardenColumns[i] == source) continue;
		if (gardenColumns[i] == target) continue;		
		if (gardenColumns[i].empty()) emptyColumns++;
	}
	
	return emptyColumns;
}

	public String getName() {
		return "FlowerGarden";
	}
/**
 * Determine win condition for FlowerGarden.
 * TODO: FIX ME!
 */
public boolean hasWon () {
	return false;
}

/**
 * Initializes FlowerGarden Plugin.
 */
public void initialize() {
	// only show splash screen first time.
	if (neverInitialized) {
		showSplashScreen();
		neverInitialized = false;
	}
	 
	initializeModel(getSeed());
	initializeView();
	initializeControllers();

	// Deal  Deal 6 cards from Deck d to each GardenColumn
	for (int i = 0; i < gardenColumns.length; i++) {
		for (int j = 0; j < 6; j++) {
			gardenColumns[i].add (deck.get());		
		}
	}
	
	// Put the rest of the cards in the row
	while (!deck.empty()) {
		bouquet.add (deck.get());
	}

}

/**
 * Initialize the controllers.
 */
private void initializeControllers() {

		
	for (int i = 0; i < gardenColumnViews.length; i++) {
		// Connect GardenColumnControllers to GardenColumnViews
		gardenColumnViews[i].setMouseAdapter(new GardenColumnController(this, gardenColumnViews[i]));

		// Connect drag handling adapter to GardenColumnViews
		gardenColumnViews[i].setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		
		// Connect undo adapter to GardenColumnViews
		gardenColumnViews[i].setUndoAdapter(new SolitaireUndoAdapter(this));
	}


	
	// Set up the container's mouse handling
	getContainer().setMouseMotionAdapter (new SolitaireMouseMotionAdapter (this));
	getContainer().setMouseAdapter (new SolitaireReleasedAdapter (this));
	getContainer().setUndoAdapter (new SolitaireUndoAdapter(this));

	// Set up the score's mouse handling
	scoreView.setMouseAdapter (new SolitaireReleasedAdapter(this));
	scoreView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter (this));
	scoreView.setUndoAdapter (new SolitaireUndoAdapter(this));

	// Set up the SuitPiles' mouse handling
	for (int i = Card.CLUBS; i <= Card.SPADES; i++) {
		suitPileViews[i].setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		suitPileViews[i].setMouseAdapter(new SuitPileController(this, suitPileViews[i]));
		suitPileViews[i].setUndoAdapter (new SolitaireUndoAdapter(this));
	}

	// Set up the GardenRow's mouse handling
	bouquetView.setMouseAdapter(new GardenRowController(this, bouquetView));
	bouquetView.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
	bouquetView.setUndoAdapter(new SolitaireUndoAdapter(this));
}

/**
 * Initialize the model.
 * @param seed
 */
private void initializeModel(int seed) {
	// Create the deck
	deck = new Deck ("deck");
	deck.create(seed);
	
	// Create the GardenColumns
	for (int i = 0; i < gardenColumns.length; i++) {
		gardenColumns[i] = new Column ("garden" + (i+1));
		addModelElement (gardenColumns[i]);
	}

	// Create the SuitPiles
	for (int i = Card.CLUBS; i<= Card.SPADES; i++) {
		suitPiles[i] = new SuitPile(i, Card.getSuitName (i));
		addModelElement (suitPiles[i]);
	}
	
	// Create the bouquet GardenRow
	bouquet = new GardenRow("bouquet");
	addModelElement(bouquet);

}

/**
 * Initialize the views.
 */
private void initializeView() {
	// Get a CardImages so we can calculate widths and stuff
	CardImages ci = getCardImages();

	for (int i = 0; i < gardenColumnViews.length; i++) {
		// Create the views for the GardenColumns
		gardenColumnViews[i] = new GardenColumnView (gardenColumns[i]);

		// Put them in the right places on the screen
		gardenColumnViews[i].setBounds( 50 + 20*i + ((i+1) * ci.getWidth()), 40 + ci.getHeight(), ci.getWidth(), ci.getHeight() * 13);
		
		addViewWidget(gardenColumnViews[i]);		
	}


	// Create the views for the SuitPiles
	for (int i = Card.CLUBS ; i <= Card.SPADES; i++) {
		suitPileViews[i] = new SuitPileView (suitPiles[i]);
	}

	// Put them in the right places on the screen
	suitPileViews[Card.HEARTS].setBounds  (20,                        40 + ci.getHeight(), ci.getWidth(), ci.getHeight());
	suitPileViews[Card.DIAMONDS].setBounds(20,                        60 + (2*ci.getHeight()), ci.getWidth(), ci.getHeight());
	suitPileViews[Card.CLUBS].setBounds   (180 + (7 * ci.getWidth()), 40 + ci.getHeight(), ci.getWidth(), ci.getHeight());
	suitPileViews[Card.SPADES].setBounds  (180 + (7 * ci.getWidth()), 60 + (2*ci.getHeight()), ci.getWidth(), ci.getHeight());
	
	for (int i = Card.CLUBS; i <= Card.SPADES; i++) {
		addViewWidget(suitPileViews[i]);
	} 
	
	// Create the view for the GardenRow and place it
	bouquetView = new GardenRowView(bouquet);
	bouquetView.setBounds(20, 20, bouquetView.getDefaultWidth(ci), ci.getHeight());
	
	// Create the score view and place it
	scoreView = new IntegerView (getScore());
	scoreView.setBounds(30 + (ci.getOverlap()*20) + ci.getWidth(), 20, 160, 60);

	// Add remaining views to the container:
	addViewWidget(bouquetView);
	addViewWidget(scoreView);
}
/**
 * Show Splash screen (and one where all images are loaded first).
 * Creation date: (10/3/01 8:58:38 PM)
 */
public void showSplashScreen() {
	/* Calculate the screen size */
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	/* Create the splash screen */
	FGSplashScreen aFGSplashScreen = new FGSplashScreen(new java.awt.Frame());
	aFGSplashScreen.pack();

	/* Center splash screen */
	Dimension FGsplashScreenSize = aFGSplashScreen.getSize();
	if (FGsplashScreenSize.height > screenSize.height)
			FGsplashScreenSize.height = screenSize.height;
	if (FGsplashScreenSize.width > screenSize.width)
			FGsplashScreenSize.width = screenSize.width;
	aFGSplashScreen.setLocation( getContainer().getLocationOnScreen().x + ((getContainer().getSize().width - FGsplashScreenSize.width) / 2), getContainer().getLocationOnScreen().x + ((getContainer().getSize().height - FGsplashScreenSize.height) / 2));
	aFGSplashScreen.setVisible(true);

	// Load images
	aFGSplashScreen.setContentsText ("Loading SuitPile images...");
	SuitImages.getInstance(aFGSplashScreen).start();
	try {;
			Thread.sleep(2000);
	} catch (InterruptedException ie) {};
	aFGSplashScreen.setContentsText ("Starting game...");
	try {;
			Thread.sleep(100);
	} catch (InterruptedException ie) {};
	aFGSplashScreen.dispose();
	
}
}
