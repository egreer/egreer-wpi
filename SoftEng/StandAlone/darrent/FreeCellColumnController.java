package darrent;

import java.awt.event.MouseEvent;
import ks.common.model.*;
import ks.common.view.*;

/**
 * Controls the behavior of mouse events over column views.
 */
public class FreeCellColumnController extends java.awt.event.MouseAdapter {

	protected FreeCell theGame;
	protected ColumnView src;

	/**
	 * FreeCellFreeCellController constructor comment.
	 */
	public FreeCellColumnController(FreeCell theGame, ColumnView src) {
		super();

		this.src = src;
		this.theGame = theGame;
	}
	
	/**
	 * Try to play from column to (a) Foundation; (b) Free
	 *
	 * @param me java.awt.event.MouseEvent
	 */
	public void mouseClicked(MouseEvent me) {

		if (me.getClickCount() > 1) {

			// Point to our underlying model element.
			Column column = (Column) src.getModelElement();
			if (column.empty()) return;  // nothing to do on empty Column.
		
			// See if we can move this one card.
			boolean moveMade = false;
			for (int f = 1; f <=4; f++) {
				Pile fp = (Pile) theGame.getModelElement (FreeCell.baseCellPrefix + f);
				Move m = new ColumnBaseMove (column, null, fp);
				if (m.doMove(theGame)) {

					// Success! Add this move to our history.
					theGame.pushMove (m);
				
					moveMade = true;
					theGame.refreshWidgets();
					break;
				}
			}
			
			// try to move to empty FreeCell.
			if (!moveMade) {
				for (int f = 1; f <=4; f++) {
					Pile fp = (Pile) theGame.getModelElement (FreeCell.freeCellPrefix + f);
					Move m = new ColumnFreeCellMove (column, null, fp);
					if (m.doMove(theGame)) {

						// Success! Add this move to our history.
						theGame.pushMove (m);
				
						moveMade = true;
						theGame.refreshWidgets();
						break;
					}
				}				
			}
			
			// see if auto moves available...
			if (moveMade) {
				((FreeCell)theGame).tryAutoMoves();
			}
		}
	}	
	
	//To Deal with MousePressed events
	public void mousePressed(MouseEvent me) {

		//Get the items we need
		Container c = theGame.getContainer(); //the game's container
		Column srcCol = (Column) src.getModelElement();
		//the column on which the mouse has pressed

		//Return in the case that the column clicked on is empty
		if (srcCol.count() == 0) {
			c.releaseDraggingObject();
			return;
		}

		// To get the view Widget for the column being moved itself.
		// Note: This method will alter the model for columnView if the condition is met.	
		//CardView cardView = src.getCardViewForTopCard(me);
		ColumnView columnView = src.getColumnView (me);

		// Safety Check
		if (columnView == null) {
			return;
		}

		// Another Safety Check
		Widget w = c.getActiveDraggingObject();
		if (w != Container.getNothingBeingDragged()) {
			System.err.println("FreeCellColumnController::pressCardController(): Unexpectedly encountered a Dragging Object during a Mouse press.");
			return;
		}

		// We tell the container what item is being dragged (and where in the Widget it was clicked)...
		c.setActiveDraggingObject(columnView, me);

		// and where it came from
		c.setDragSource(src);

		src.redraw();
	}
	
	public void mouseReleased(MouseEvent me) {
		Container c = theGame.getContainer();

		//Safety Check
		Widget w = c.getActiveDraggingObject();
		if (w == Container.getNothingBeingDragged())
			return;

		boolean changed = false;
		if (w instanceof CardView) {
			changed = processDraggingCardView ((CardView) w);
		} else if (w instanceof ColumnView) {
			changed = processDraggingColumnView ((ColumnView) w);
		}

		// seek auto moves
		if (changed) {
			((FreeCell)theGame).tryAutoMoves();
		}

		if (changed) {
			// Since we could be released over a widget, or over the container, we must repaintAll() clipped to
			// the region we are concerned about.
			theGame.repaintAll(w.getBounds());
			
			// first refresh the last known position of the card being dragged.
			theGame.refreshWidgets(); // then the widgets.
		}
		
		// release the dragging object (this will reset container's dragSource).
		c.releaseDraggingObject();
	}

	/**
	 * @param view
	 */
	private boolean processDraggingColumnView (ColumnView columnView) {
		Column column = (Column) columnView.getModelElement();

		//Safety Check
		if (column == null) {
			return false;
		}
		
		//Get sourceWidget for card being dragged
		Container c = theGame.getContainer();
		Widget sourceWidget = c.getDragSource();
		
		//Safety Check
		if (sourceWidget == null) {
			System.err.println("FreeCellColumnController::mouseReleased(): somehow dragSource for container is null.");
			return false;
		}
		
		Column toCol = (Column) src.getModelElement();
		
		Column sourceCol = (Column) sourceWidget.getModelElement();
		Move m = new ColumnColumnMove(sourceCol, column, column.count(), toCol);
		if (m.valid(theGame)) {
			m.doMove(theGame);
			theGame.pushMove(m);
		} else {
			sourceWidget.returnWidget(columnView);
		}
		
		return true;
	}

	/**
	 * Dragging a CardView. Must come from FreeCell
	 * @param view
	 */
	private boolean processDraggingCardView (CardView cardView) {
		Card theCard = (Card) cardView.getModelElement();

		//Safety Check
		if (theCard == null) {
			System.err.println("FreeCellColumnController::mouseReleased(): somehow CardView model element is null.");
			return false;
		}

		//Get sourceWidget for card being dragged
		Container c = theGame.getContainer();
		Widget sourceWidget = c.getDragSource();

		//Safety Check
		if (sourceWidget == null) {
			System.err.println("FreeCellColumnController::mouseReleased(): somehow dragSource for container is null.");
			return false;
		}

		Column toCol = (Column) src.getModelElement();

		//Case: Card is coming from a Pile (FreeCell)
		Pile sourcePile = (Pile) sourceWidget.getModelElement();
		Column toColumn = (Column) src.getModelElement();
		Move m = new FreeCellColumnMove(sourcePile, theCard, toColumn);
		
		if (m.valid(theGame)) {
			m.doMove(theGame);
			theGame.pushMove(m);
		} else {
			sourceWidget.returnWidget(cardView);
		}

		return true;
	}
}
