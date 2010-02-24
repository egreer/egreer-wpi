package yostinso;

import java.awt.event.MouseEvent;

import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.ElementListener;
import ks.common.view.ColumnView;
import ks.common.view.Widget;


/**
 * Controller object for the GardenColumn
 * Creation date: (11/29/2001 11:28:17 AM)
 * @author: E.O. Stinson
 */
public class GardenColumnView extends ColumnView implements ElementListener {
	public GardenColumnView(Column gcol) {
		super(gcol);
	}

	public ColumnView getColumnViewForSelectedCards(
		java.awt.event.MouseEvent me) {

		setSelectedCards(me);
		Column theColumn = (Column) getModelElement();
		int numCards = theColumn.count();
		if (numCards == 0)
			return null; // no chance in an empty Column!
		if (setSelectedCards(me) == 0) {
			return null;
		} // No card selected 

		// Create a ColumnView widget for this card.
		Column theDraggingColumn = new Column(); // Create an empty column

		theDraggingColumn.push(theColumn.getSelected());
		// Add the chosen card(s) to it

		ColumnView cv = new ColumnView(theDraggingColumn); // Make a view

		// Set Bounds of this cardView widget to the bounds of the Pile from our own coordinates.
		int theColumnHeight = (cards.getOverlap()	* (theColumn.count() + (theDraggingColumn.count() - 1)));

		int theDraggingColumnHeight = (cards.getOverlap() * (theDraggingColumn.count() - 1));

		cv.setBounds(
			new java.awt.Rectangle(
				x,
				y + (theColumnHeight - theDraggingColumnHeight),
				cards.getWidth(),
				theDraggingColumnHeight + cards.getHeight()));

		// use the same peer. NOTE: we must do this because we aren't adding this widget. As a dynamic
		// widget, I feel it would be wrong to add this to the static list of widgets for this container.
		// Note that this means that this widget has no recourse to mouse events.
		cv.setContainer(container);

		return cv; // all set.
	}
	
	
	public boolean returnWidget(Widget w) {
		if (w instanceof ColumnView) {
			ColumnView colView = (ColumnView) w;
			Column theCol = (Column) colView.getModelElement();
			if (theCol == null) {
				System.err.println(
					"ColumnView::returnWidget(): somehow ColumnView model element is null.");
				return false;
			}

			Column src = (Column) getModelElement();
			if (src == null) {
				System.err.println(
					"ColumnView::returnWidget(): somehow ColumnView has no underlying Column element.");
				return false;
			}

			// Restore our model element's state, one card at a time.
			for (int i = 0; i < theCol.count(); i++) {
				Card c = theCol.peek(i);
				src.add(c);
			}

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Based on the given mouse event, set the selected cards in the View.
	 * Creation date: (11/29/2001 6:00:45 PM)
	 * @return int
	 * @param me java.awt.event.MouseEvent
	 */
	public int setSelectedCards(MouseEvent me) {
		Column theColumn = (Column) getModelElement();
		double numToSelect = 0;
		int columnHeight = (cards.getOverlap() * (theColumn.count() - 1)) + cards.getHeight();
		numToSelect = Math.floor((me.getY() - this.y) / cards.getOverlap());
		
		// Find the card they clicked on
		numToSelect = Math.min(numToSelect, theColumn.count());
		
		// Whichever is less
		if (theColumn.count() >= numToSelect) {
			// Now find how many cards need to be selected:
			// Note that this matches against overlap, so only the top of the
			// last card picks it up
			numToSelect = (theColumn.count()) - numToSelect;
			// Now, check if they clicked somewhere else on the last card
			if (numToSelect == 0 && (me.getY() - this.y) < columnHeight) {
				numToSelect = 1;
			}
			theColumn.select((int) numToSelect);
			return (int) numToSelect;
		} else {
			return 0;
		}
	}
}
