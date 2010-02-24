package yostinso;

import java.awt.Graphics;
import java.awt.Image;

import ks.common.model.Card;
import ks.common.model.Element;
import ks.common.view.PileView;

/**
 * The boundary/widget object for a SuitPile
 * Creation date: (11/29/2001 11:36:06 AM)
 * @author: E.O. Stinson
 */
public class SuitPileView extends PileView {
	protected int suit;
/**
 * SuitPileView constructor comment.
 */
public SuitPileView(Element mepile, int suit) {
	super(mepile);
	this.suit = suit;
}
/**
 * SuitPileView constructor comment.
 */
public SuitPileView(SuitPile asuitpile) {
	super(asuitpile);
	this.suit = asuitpile.suit;
}
/**
 * redraw method comment.
 * @since V1.5.1 an empty pile has a thin rectangle border; also properly handles faceup/facedown issues
 * a SuitPile also displays text indicating the assigned suit of the associated model element
 */
public void redraw() {
	SuitPile thePile = (SuitPile) getModelElement();

	SuitImages si = SuitImages.getInstance(getContainer());

	// create offscreen Image if not already done so.
	if (offscreenImage == null) {
		if (getContainer() == null) {
			System.err.println ("PileView::redraw(). Invalid request to draw a Widget that is not part of a container.");
			return;
		}
		// Create image with width x height of CardImages
		offscreenImage = container.createImage (cards.getWidth(), cards.getHeight());
	}

	// clear to the background color of the viewing peer.
	java.awt.Graphics g = offscreenImage.getGraphics();
	g.setColor (container.getBackground());
	g.fillRect (0, 0, width, height);

	// Peek at the top card from the pile
	Card c = thePile.peek();
	if (c != null) {
		Image img = null;
		if (c.isFaceUp()) {
			img = cards.getCardImage(c);
		} else {
			img = cards.getCardReverse();
		}

			
		g.drawImage (img, 0, 0, container);
	} else {
		Image suitimg = null;
		// Create a black rectangle outline (with border = 4) where deck used to be once empty.
		int w = cards.getWidth();
		int h = cards.getHeight();

		// Draw full rectangle
		g.setColor (java.awt.Color.black);
		g.drawRect (0, 0, w, h);
		// Get the suit image
		
		suitimg = si.getSuitImage(thePile);
	  if (suitimg != null) { // Make sure the resources exist
		g.drawImage(suitimg, 0, 0, container);
	  } else {
		// Text-only mode (if missing images)
		if (thePile.getAssignedSuit() == Card.HEARTS) {
			g.drawString(Card.HEARTSname, 10, cards.getHeight()/2-5);
		} else if (thePile.getAssignedSuit() == Card.CLUBS) {
			g.drawString(Card.CLUBSname, 10, cards.getHeight()/2-5);
		} else if (thePile.getAssignedSuit() == Card.DIAMONDS) {
			g.drawString(Card.DIAMONDSname, 10, cards.getHeight()/2-5);
		} else if (thePile.getAssignedSuit() == Card.SPADES) {
			g.drawString(Card.SPADESname, 10, cards.getHeight()/2-5);
		}
	  }
	}
	
	// transfer image once done.
	if (getImage() == null) {
		// first time create image
		setImage (container.createImage (cards.getWidth(), cards.getHeight()));
	}
	
	Graphics lc = getImage().getGraphics();
	if (lc != null) {
		lc.drawImage (offscreenImage, 0, 0, container);
	}	
}
}
