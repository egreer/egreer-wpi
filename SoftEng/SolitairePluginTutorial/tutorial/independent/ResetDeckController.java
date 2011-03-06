package tutorial.independent;

import java.awt.event.MouseAdapter;

import tutorial.vfinal.ResetDeckMove;
import ks.common.games.Solitaire;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.Pile;

/**
 * Chain Controllers together.
 */
public class ResetDeckController extends ControllerDecorator {
    public ResetDeckController (Solitaire game, MouseAdapter controller) {
        super(game, controller);
    }
 
    public ResetDeckController (ControllerDecorator controller) {
        super(controller);
    }
    
    // Specialized logic

    /**
     * A MousePressed event on the DeckView is a signal to deal the next
     * set of cards (if the deck is nonempty).
     *
     * @param me     low-level mouse event
     */
    public void mousePressed(java.awt.event.MouseEvent me) {

        // Find the deck from our model
        Deck d = (Deck) game.getModelElement("deck");
        Pile p1 = (Pile) game.getModelElement("pile1");
        Pile p2 = (Pile) game.getModelElement("pile2");
        Pile p3 = (Pile) game.getModelElement("pile3");
        Pile p4 = (Pile) game.getModelElement("pile4");

        if (d.empty()) {
        	// Must be a request to reset the deck.
            Move m = new ResetDeckMove (d, p1, p2, p3, p4);
            if (m.doMove(game)) {
                // SUCCESS
            	game.pushMove (m);

                // refresh all widgets that were affected by this move.
            	game.refreshWidgets();
            }
            
            return;
        }
        
        // not for us! Try the next in the chain pass along
        decoratedController.mousePressed(me);
    }
}
