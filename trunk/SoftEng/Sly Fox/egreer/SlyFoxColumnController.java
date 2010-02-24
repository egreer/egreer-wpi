package egreer;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Move;
import ks.common.model.Deck;
import ks.common.model.Pile;
import ks.common.model.Column;
import ks.common.view.CardView;
import ks.common.view.Container;
import ks.common.view.ColumnView;
import ks.common.view.PileView;
import ks.common.view.Widget;

/** 
 * Controller for the reserve columns in Sly Fox  
 * 
 * @author Eric Greer
 */

public class SlyFoxColumnController extends java.awt.event.MouseAdapter{
	/** The current game and the column view being controlled */
	Solitaire theGame;
	ColumnView columnview;
	
	/** Constructor for SlyFoxColumnController
	 * 
	 * @param game	The current solitaire game
	 * @param columnview	The column view being controlled 
	 */
	public SlyFoxColumnController(Solitaire game, ColumnView columnview){
		this.theGame = game;
		this.columnview = columnview; 
	}
	
	
	
	/**
	 * Respond to mousePressed events by Dragging top Card
	 * @param me A low-level mouse event called a  press 
	 */
	public void mousePressed(java.awt.event.MouseEvent me){
		Pile reserve = (Pile) theGame.getModelElement("reserveDeck");
		
		// if the reserve pile is empty then cards can be moved on this column  
		if (reserve.empty()){
		CardView cardView = columnview.getCardViewForTopCard(me);

		// if there are no card on the top then nothing happens 
		if (cardView == null) { return; }
		
		
		// Have container track this object now. Record where it came from
		Container c = theGame.getContainer();
		c.setActiveDraggingObject (cardView, me);
		c.setDragSource(columnview);

		columnview.redraw();
		}
	}
	
	/**
	 * Respond to mouseClicked events by trying to do RestockReserveMove
	 * @param me a low level mouse event called a click
	 */
	public void mouseClicked(java.awt.event.MouseEvent me){

		//If the game is Sly Fox then recast game to a SlyFox  game
		if(theGame.getName().compareTo("Sly Fox") == 0){
			SlyFox theSFGame = (SlyFox) theGame;

			//Get the deck
			Deck theDeck = (Deck)theSFGame.getModelElement("deck");
			Column toColumn = (Column) columnview.getModelElement();
			
			// Get the phase
			int thePhase = theSFGame.getPhase();
			
			//Create move, and then do move
			Move m = new RestockEmptyReserveMove(theDeck, toColumn, thePhase);
            if (m.doMove(theGame)) {
                // SUCCESS
                theGame.pushMove (m);
                
                // redraw all piles
                theGame.refreshWidgets();
            }
		}
	}
	
	
	
	
	/**
	 * On mouse released, get card being dragged and execute
	 * (if valid) MoveReserveCardMove
	 * @param me A low-level mouse event called released 
	 */
	public void mouseReleased(java.awt.event.MouseEvent me) {
		Container c = theGame.getContainer();
		
		/** Return if there is no card being dragged chosen. */
		Widget w = c.getActiveDraggingObject();
		if (w == Container.getNothingBeingDragged()) return;
		
		/** Must be the CardView widget now. */
		CardView cardView = (CardView) w;
		Card theCard = (Card) cardView.getModelElement();
		
		/** Recover the From Pile */
		Widget fromView = (Widget) c.getDragSource();
		
		if(fromView.getModelElement().getName().compareTo("reserveDeck") == 0){
			
			/** Recover the From Pile (should be the reserveDeck)*/
			PileView fromPileView = (PileView) c.getDragSource();
			Pile fromPile = (Pile) fromPileView.getModelElement();
			
			// Determine the To Column
			Column toColumn = (Column) columnview.getModelElement();
			
			// Try to make the moveReserveCardMove
			Move m = new MoveReserveCardMove(fromPile, theCard, toColumn);
			
			if(m.doMove (theGame)){
				// SUCCESS
				theGame.pushMove (m);
				
			}else{
				// Invalid move! Return to the pile from whence it came.
				fromPileView.returnWidget (cardView);
			}
			
		}else{
			// Invalid move! Return to the pile from whence it came.
			fromView.returnWidget (cardView);
		}
		
		//refresh
		theGame.repaintAll (cardView.getBounds());   
		theGame.refreshWidgets(); 

		// release the dragging object since the move is now complete
		c.releaseDraggingObject();
		
	}

}
