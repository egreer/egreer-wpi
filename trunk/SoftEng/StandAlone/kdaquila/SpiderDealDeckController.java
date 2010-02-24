package kdaquila;

import java.awt.event.MouseEvent;

import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.view.Container;
import ks.common.view.Widget;

/**
 * Controller for the dealDeck used in Spider Solitaire
 *
 * @author: Kevin J. D'Aquila (kdaquila@wpi.edu)
 */
public class SpiderDealDeckController extends java.awt.event.MouseAdapter
{
	/** The game instance. */
	protected Spider theGame = null;

	/** Constructor */
	public SpiderDealDeckController(Spider spiderGame) {
		super();

		// Set the game instance
		theGame = spiderGame;
	}
	/**
	 * A MousePressed event on the DeckView is a signal to deal the next
	 * set of cards (if the deck is nonempty).
	 *
	 * @param me java.awt.event.MouseEvent
	 */
	public void mouseClicked(java.awt.event.MouseEvent mouseE)
	{
		// Find the deck from our model
		Deck dealDeck = (Deck) theGame.getModelElement("dealDeck");

		if (!dealDeck.empty()) {
		// Deal ten cards and refresh affected Widgets
		// Deal four cards
		Move m = new DealDeckMove(theGame);
		if (m.doMove(theGame)) {
			// SUCCESS
			theGame.pushMove(m);

			// refresh all widgets that were affected by this move.
			theGame.refreshWidgets();
		}
		}

}
/**
 * Return Widgets!
 * @param mouseE java.awt.event.MouseEvent
 */
public void mouseReleased(MouseEvent mouseE)
{
	Container c = theGame.getContainer();

	// Return if there is no card being dragged chosen
	Widget draggingWidget = c.getActiveDraggingObject();
	if (draggingWidget == Container.getNothingBeingDragged()) return;
	
	// Recover the widget
	Widget fromWidget = c.getDragSource();
	if (fromWidget == null)
	{
		c.releaseDraggingObject();		
		return;
	}

	// Return the widget to where it came from
	fromWidget.returnWidget (draggingWidget);

	// Repaint everything and refresh widgets
	theGame.repaintAll (draggingWidget.getBounds());
	theGame.refreshWidgets();

	// Release the dragging object - container will reset dragSource
	c.releaseDraggingObject();		
}
}
