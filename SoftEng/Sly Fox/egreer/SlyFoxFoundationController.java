package egreer;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.model.Column;
import ks.common.view.CardView;
import ks.common.view.Container;
import ks.common.view.ColumnView;
import ks.common.view.PileView;
import ks.common.view.Widget;

/** 
 * Controller for the Foundation piles in Sly Fox 
 * 
 * @author Eric Greer
 */

public class SlyFoxFoundationController extends java.awt.event.MouseAdapter{

	/** The Sly Fox instance. */
	protected Solitaire theGame = null;

	/** The PileView widget being controlled. */
	protected PileView pileview = null;
	
	 
	/**
	 * SlyFoxFoundationController Constructor 
	 * 
	 * @param game		The current game
	 * @param pileview	The pileView that the controller covers   
	 */ 
	public SlyFoxFoundationController(Solitaire game, PileView pileview) {
		super();

		theGame = game;
		this.pileview = pileview;
	}


	/**
	 * On mouse released, get card being dragged and execute
	 * (if valid) MoveCard Right, or MoveCardLeft
	 * 
	 * @param me Is a low level mouse event that is a release on the pile
	 */
	public void mouseReleased(java.awt.event.MouseEvent me) {
		Container c = theGame.getContainer();

		/** Return if there is no card being dragged chosen. */
		Widget w = c.getActiveDraggingObject();
		if (w == Container.getNothingBeingDragged()) return;
		

		/** Must be the CardView widget now. */
		CardView cardView = (CardView) w;
		Card theCard = (Card) cardView.getModelElement();

		/** Return if trying to move a card while reserve is not empty **/
		Pile reservedeck = (Pile)theGame.getModelElement("reserveDeck");
		if(!reservedeck.empty()){
			PileView fromReserveView = (PileView) c.getDragSource();
			fromReserveView.returnWidget (cardView);
			//refresh
			theGame.repaintAll (cardView.getBounds());   
			theGame.refreshWidgets(); 

			// release the dragging object since the move is now complete
			c.releaseDraggingObject();
			return;
		}

		/** Recover the From Pile */
		ColumnView fromColumnView = (ColumnView) c.getDragSource();
		Column fromColumn = (Column) fromColumnView.getModelElement();
		
		// Determine the To Pile (foundation)
		Pile toPile = (Pile) pileview.getModelElement();

		// Try to make the proper move
		Move m; 
		
		//If the pile is to the left of the column try the MoveCardLeftMove
		if (pileview.getX()< fromColumnView.getX()) {
			m = new MoveCardLeftMove (fromColumn, theCard, toPile);
			if(m.doMove (theGame)){
				// SUCCESS
				theGame.pushMove (m);
			}else {
				// invalid move! Return to the pile from whence it came.
				fromColumnView.returnWidget (cardView);
			}

		// If the pile is to the right of the column try the MoveCardRightMove			
		}else if (pileview.getX() > fromColumnView.getX()){ 
			m = new MoveCardRightMove (fromColumn, theCard, toPile);
			if(m.doMove (theGame)){
				// SUCCESS
				theGame.pushMove (m);
			}else {
				// invalid move! Return to the pile from whence it came.
				fromColumnView.returnWidget (cardView);
			}
		
		} else {
			// invalid move! Return to the pile from whence it came.
			fromColumnView.returnWidget (cardView);
		}

		//refresh
		theGame.repaintAll (cardView.getBounds());   
		theGame.refreshWidgets(); 

		// release the dragging object since the move is now complete
		c.releaseDraggingObject();
	}
	
}
