package tutorial.v4;

import tutorial.v2.DealFourMove;
import ks.common.games.Solitaire;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.view.Container;
import ks.common.view.Widget;

/**
 * Controller for the DeckView widgets used in Narcotic.
 * 
 * By extending 'SolitaireReleasedAdapter' we take advantage of the 
 * default behavior 
 *
 * @author George T. Heineman (heineman@cs.wpi.edu)
 */
public class NarcoticDeckController  extends java.awt.event.MouseAdapter {
    /** The narcotic instance. */
    protected Solitaire narcoticGame = null;

    /** NarcoticDeckController constructor comment. */
    public NarcoticDeckController(Solitaire game) {
        super();

        narcoticGame = game;
    }
    
    public void mouseReleased(java.awt.event.MouseEvent me) {
		Container c = narcoticGame.getContainer();

		/** Return if there is no card being dragged chosen. */
		Widget draggingWidget = c.getActiveDraggingObject();
		if (draggingWidget == Container.getNothingBeingDragged()) return;

		/** Recover from wherever it came. */
		Widget fromWidget = c.getDragSource();
		if (fromWidget == null) {
			c.releaseDraggingObject();		
			return;
		}

		// Simply return the widget to where it came from.
		fromWidget.returnWidget (draggingWidget);

		// Since we could be released over a widget, or over the container, 
		// we must repaintAll() clipped to the region we are concerned about.
		// first refresh the last known position of the card being dragged.
		// then the widgets.
		narcoticGame.repaintAll (draggingWidget.getBounds());   
		narcoticGame.refreshWidgets(); 

		// release the dragging object since the move is now complete (this 
		// will reset container's dragSource).
		c.releaseDraggingObject();
	}
    
    /**
     * A MousePressed event on the DeckView is a signal to deal the next
     * set of cards (if the deck is nonempty).
     *
     * @param me     low-level mouse event
     */
    public void mousePressed(java.awt.event.MouseEvent me) {

        // Find the deck from our model
        Deck d = (Deck) narcoticGame.getModelElement("deck");
        Pile p1 = (Pile) narcoticGame.getModelElement("pile1");
        Pile p2 = (Pile) narcoticGame.getModelElement("pile2");
        Pile p3 = (Pile) narcoticGame.getModelElement("pile3");
        Pile p4 = (Pile) narcoticGame.getModelElement("pile4");

        if (!d.empty()) {
            // Deal four cards
            Move m = new DealFourMove(d, p1, p2, p3, p4);
            if (m.doMove(narcoticGame)) {
                // SUCCESS: have solitaire game store this move
                narcoticGame.pushMove(m);

                // have solitaire game refresh widgets that were affected 
                narcoticGame.refreshWidgets();
            }
        }
    }
}
