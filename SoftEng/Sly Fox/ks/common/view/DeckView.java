package ks.common.view;

import ks.common.model.Deck;

/**
 * A Widget for displaying a Deck on the screen.
 * <p>
 * Note: <code>returnWidget</code> not implemented for DeckView because there
 * are no drag actions for removing cards from this DeckView.
 * <p>
 * @author George T. Heineman (heineman@cs.wpi.edu)
 */
 
public class DeckView extends Widget {
	/** Default numbering for DeckView names. */
	protected static int deckViewCounter = 1;
/**
 * DeckView constructor comment.
 */
public DeckView(Deck d) {
	super(d);

	setName (new String ("DeckView" + deckViewCounter++));
}
/**
 * An empty DeckView is drawn as a rectangle with a thick border. A non-empty DeckView
 * shows the reverse image of a card.
 */
public void redraw() {

	Deck d = (Deck) getModelElement();
	if (d.empty()) {
		// Create a black rectangle outline (with border = 4) where deck used to be once empty.
		int w = cards.getWidth();
		int h = cards.getHeight();
		java.awt.Image img = container.createImage(w, h);
		java.awt.Graphics g = img.getGraphics();

		java.awt.Color oldColor = g.getColor();   // Store old color.
		
		// Draw full rectangle
		g.setColor (java.awt.Color.black);
		g.fillRect (0, 0, w, h);

		// fill inner border with background colors.
		g.setColor (container.getBackground());
		g.fillRect (4,4, w-8, h-8);
		g.setColor (oldColor);
		setImage (img);
	} else {
		// This redraw simply overwrites with the image coming from the resource.
		setImage (cards.getCardReverse());
	}
}
}
