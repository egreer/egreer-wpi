package yostinso;

import java.awt.event.MouseEvent;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.model.Column;
import ks.common.model.Move;
import ks.common.view.ColumnView;
import ks.common.view.Container;
import ks.common.view.Widget;


/**
 * Controller object for the GardenRowView element
 * Creation date: (11/29/2001 8:36:21 PM)
 * @author: E.O. Stinson
 */
public class GardenRowController extends SolitaireReleasedAdapter {
	protected GardenRowView src;
	protected FlowerGarden theGame;
	protected int selectionPoint;
	/**
	 * GardenRowController constructor comment.
	 */
	public GardenRowController(FlowerGarden theGame, GardenRowView bpv) {
		super(theGame);
		this.theGame = theGame;
		this.src = bpv;
	}
	public void mouseClicked(MouseEvent me) {
		// If the user doubleclicks on a card
		if (me.getClickCount() > 1) {
			// Get the container
			Container c = theGame.getContainer(); // Get the container

			// Get the GardenColumn model element for the GardenColumnView we clicked on
			GardenRow theRow = (GardenRow) src.getModelElement();

			// Make sure the GardenColumn isn't empty
			if (theRow.count() == 0)
				return;

			// Get the selected cards from the Column
			ColumnView columnView = src.getColumnViewForSelectedCards(me);
			// Get the card

			// Make sure that some cards were selected
			if (columnView == null)
				return;

			// have move identify the proper location (pass in null here). Move the card
			Move m = new MoveGRSPMove((GardenRow) src.getModelElement(), (Column) columnView.getModelElement(), null, theRow.getLastSelectionPoint());
			if (m.doMove(theGame)) {
				// Success! Store the move, for undoing purposes
				theGame.pushMove(m);
			} else {
				// otherwise, put the card back where it came from
				src.returnWidget(columnView);
			}

			theGame.repaintAll(columnView.getBounds());
			// first refresh the last known position of the card being dragged.
			theGame.refreshWidgets(); // then the widgets.
		}
	}
	public void mousePressed(MouseEvent me) {

		// The container manages several critical pieces of information; namely, it
		// is responsible for the draggingObject; in our case, this would be a CardView
		// Widget managing the card we are trying to drag between two piles.
		Container c = theGame.getContainer();

		/** Return if there is no card to be chosen. */
		GardenRow theRow = (GardenRow) src.getModelElement();
		if (theRow.count() == 0) {
			c.releaseDraggingObject();
			return;
		}

		// Getthe top card from the row. Note that this method will alter the model
		int selectedCard = src.setSelectedCards(me);
		theRow.setLastSelectionPoint(selectedCard);

		ColumnView columnView = src.getColumnViewForSelectedCards(me);
		
		// an invalid selection (no card selected)
		if (columnView == null) {
			c.releaseDraggingObject();
			return;
		}

		// If we get here, then the user has indeed clicked on the top card in the GardenColumn and
		// we are able to now move it on the screen at will. For smooth action, the bounds for the
		// cardView widget reflect the original card location on the screen. (maybe)
		Widget w = c.getActiveDraggingObject();
		if (w != Container.getNothingBeingDragged()) {
			System.err.println("GardenRowController::mousePressed(): Unexpectedly encountered a Dragging Object during a Mouse press.");
			return;
		}

		// Tell container which object is being dragged, and where in that widget the user clicked.
		c.setActiveDraggingObject(columnView, me);

		// Have container keep track of source widget that initiated the drag.
		c.setDragSource(src);

		// note: detailed way of avoiding flicker
		if (selectedCard != 1) {
			// Since we are selecting from within the widget (i.e., not top card), 
			// we don't have the luxury of simply using redraw (which assumes cards 
			// are pulled from the end of a row or column). We are forced to repaint
			// everything. 
			src.paint();
			
			// lastly, draw the dragging card on top. To avoid flicker, we need
			// to skip this step (when selectedCard = 1 for topmost)
			columnView.paint();
		} else {
			src.redraw();
		}

	}

}
