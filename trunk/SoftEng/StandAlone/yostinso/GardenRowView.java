package yostinso;

import java.awt.event.MouseEvent;

import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.view.CardImages;
import ks.common.view.ColumnView;
import ks.common.view.RowView;
import ks.common.view.Widget;


  /**
 * GardenRowView widget/entity object for the FlowerGarden solitaire variation
 * Creation date: (11/29/2001 11:45:05 AM)
 * @author: 
 */
public class GardenRowView extends RowView {
/**
 * GardenRowView constructor comment.
 */
public GardenRowView(GardenRow grow) {
	super(grow);
	setDirection(RowView.RIGHT);
}
public ColumnView getColumnViewForSelectedCards(java.awt.event.MouseEvent me) {
	
	Column theColumn = (Column) getModelElement();
	int numCards = theColumn.count();
	if (numCards == 0) return null;  // no chance in an empty Column!
	if (setSelectedCards(me) == 0) { return null; } // No card selected 
	
	// Create a ColumnView widget for this card.
	Column theDraggingColumn = new Column();  // Create an empty column

	theDraggingColumn.push(theColumn.getSelected());   // Add the chosen card(s) to it
	// Okay, since this is for a RowView, we only _really_ want 1 card, so:
	int theDraggingColumnWidth = (cards.getOverlap() * (theDraggingColumn.count()-1));
	if (theDraggingColumn.count() > 1) {
		// Get the card we want
		Card theCard = new Card(theDraggingColumn.peek(0));
		for(int i=1;i < theDraggingColumn.count(); i++) {
			theColumn.add(theDraggingColumn.peek(i));
		}
		theDraggingColumn = new Column(); // Clear the DraggingColumn, just in case
		theDraggingColumn.add(theCard);
	}
	ColumnView cv = new ColumnView(theDraggingColumn); // Make a view

	// Set Bounds of this cardView widget to the bounds of the Pile from our own coordinates.
	int theColumnWidth = (cards.getOverlap() * (theColumn.count() + (theDraggingColumn.count()-1)));

	cv.setBounds (new java.awt.Rectangle (x+ (theColumnWidth-theDraggingColumnWidth),y, cards.getWidth(), cards.getHeight()));

	// use the same peer. NOTE: we must do this because we aren't adding this widget. As a dynamic
	// widget, I feel it would be wrong to add this to the static list of widgets for this container.
	// Note that this means that this widget has no recourse to mouse events.
	cv.setContainer (container);
	return cv;  // all set.
}
public int getDefaultWidth(CardImages ci) {
	return  ci.getWidth() + 16 * ci.getOverlap();
}
public boolean returnWidget(Widget w) {
	if (w instanceof ColumnView) {
		ColumnView colView = (ColumnView) w;
		Column theCol = (Column) colView.getModelElement();
		if (theCol == null) {
			System.err.println ("GardenRowView::returnWidget(): somehow GardenRowView model element is null.");
			return false;
		}

		GardenRow src = (GardenRow) getModelElement();
		if (src == null) {
			System.err.println ("GardenRowView::returnWidget(): somehow GardenRowView has no underlying GardenRow element.");
			return false;
		}
		
		// Restore our model element's state, and put the card in the right place
		Column tempColumn = new Column();
		// Dig our way to the selection point
		for (int i = 0; i < (src.getLastSelectionPoint()-1); i++) {
			tempColumn.add(src.get());
		}
		
		// Insert our card
		src.add(theCol.get());
		
		// And put the cards back on
		while (!tempColumn.empty()) {
			src.add(tempColumn.get());
		}
		refresh();
		return true;
	} else { 
		return false;
	}
}

/**
 * This selects all the cards down the the one clicked upon.
 * 
 * @param me
 * @return
 */
public int setSelectedCards(MouseEvent me) {
	GardenRow theRow = (GardenRow) getModelElement();
	double whichToSelect = 0;
	int rowWidth = (cards.getOverlap() * (theRow.count()-1)) + cards.getWidth();

	// Did they click the last card?
	if ((me.getX()-this.x) > (cards.getOverlap() * (theRow.count()-1)) && (me.getX()-this.x) < (cards.getOverlap() * (theRow.count()-1)) + cards.getWidth()) {
		whichToSelect = 1;
	} else {
	whichToSelect = Math.floor((me.getX()-this.x) / cards.getOverlap()); // Find the card (unless they clicked past the overlap width on the fully visible card)
	whichToSelect = Math.min(whichToSelect, theRow.count()); // Don't want the green stuff
	whichToSelect = theRow.count() - whichToSelect;
	}
	if (theRow.count() >= whichToSelect) { // As it should be from the last line
		theRow.select((int) whichToSelect);
		return (int) whichToSelect;
	} else {
		return 0;
	}
}
}
