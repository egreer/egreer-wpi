package egreer;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.games.Solitaire;
import ks.common.view.CardView;
import ks.common.view.PileView;
import ks.common.view.Container;

/**
 * Controller for the pileView widget that represents the reservePile in SlyFox.
 * 
 * Extends 'SolitaireReleasedAdapter' to get the default behavior
 * 
 * @author Eric Greer
 */

public class SlyFoxReserveController extends SolitaireReleasedAdapter{
	
	/** Constructor for SlyFoxReserveController
	 * @param game	The current solitaire game
	 */
	
	public SlyFoxReserveController (Solitaire game){
		super(game);
	}

	/**
	 * Respond to mousePressed events by dragging top Card
	 * @param me	A low level mouse event that is a press 
	 */
	public void mousePressed(java.awt.event.MouseEvent me){
		PileView pileView = (PileView) theGame.getContainer().getWidget(me);
		CardView cardView = pileView.getCardViewForTopCard(me);

		// no card on the top
		if (cardView == null) { return; }
		
		// Have container track this object now. Record where it came from
		Container c = theGame.getContainer();
		c.setActiveDraggingObject (cardView, me);
		c.setDragSource(pileView);

		//refresh
		pileView.redraw();
	}
}
