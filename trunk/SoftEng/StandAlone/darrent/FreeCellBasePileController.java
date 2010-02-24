package darrent;

import java.awt.event.MouseEvent;
import ks.common.model.*;
import ks.common.view.*;

/**
 * Controller for Base Piles.
 */
public class FreeCellBasePileController extends java.awt.event.MouseAdapter {

	/** The game. */
	protected FreeCell theGame;
	
	/** The PileView being controll.ed */
	protected PileView src;

	/**
	 * FreeCellFreeCellController constructor comment.
	 */
	public FreeCellBasePileController(FreeCell theGame, PileView src) {

		super();

		this.src = src;
		this.theGame = theGame;
	}
	
	// Ignore mouse pressed events..
	public void mousePressed(MouseEvent me) {
		// no behavior here.
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

		// try auto moves
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
	 * @return
	 */
	private boolean processDraggingColumnView(ColumnView columnView) {
		Column column = (Column) columnView.getModelElement();

		//Safety Check
		if (column == null) {
			System.err.println("FreeCellBasePileController::mouseReleased(): somehow CardView model element is null.");
			return false;
		}

		//Get sourceWidget for card being dragged
		Container c = theGame.getContainer();
		Widget sourceWidget = c.getDragSource();

		//Safety Check
		if (sourceWidget == null) {
			System.err.println("FreeCellBasePileController::mouseReleased(): somehow dragSource for container is null.");
			return false;
		}

		Pile toPile = (Pile) src.getModelElement();

		//Case: Card is coming from a Column
		Column sourceCol = (Column) sourceWidget.getModelElement();			
		Move m = new ColumnBaseMove (sourceCol, column, toPile);
		if (m.valid(theGame)) {
			m.doMove(theGame);
			theGame.pushMove (m);
		} else {
			sourceWidget.returnWidget (columnView);
		}	
		
		return true;
	}

	/**
	 * @param view
	 * @return
	 */
	private boolean processDraggingCardView(CardView cardView) {
		Card theCard = (Card) cardView.getModelElement();

		//Safety Check
		if (theCard == null) {
			System.err.println("FreeCellBasePileController::mouseReleased(): somehow CardView model element is null.");
			return false;
		}

		//Get sourceWidget for card being dragged
		Container c = theGame.getContainer();
		Widget sourceWidget = c.getDragSource();

		//Safety Check
		if (sourceWidget == null) {
			System.err.println("FreeCellBasePileController::mouseReleased(): somehow dragSource for container is null.");
			return false;
		}

		Pile toPile = (Pile) src.getModelElement();

		//Case: Card is coming from a Pile (FreeCell)
		Pile sourcePile = (Pile) sourceWidget.getModelElement();
		Move m = new FreeCellBaseMove (sourcePile, theCard, toPile);
		if (m.valid (theGame)) {
			m.doMove (theGame);
			theGame.pushMove (m);
		} else {
			sourceWidget.returnWidget(cardView);
		}
		
		return true;
	}
}
