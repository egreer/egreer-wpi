package darrent;

import java.awt.event.MouseEvent;
import ks.common.model.*;
import ks.common.view.*;

/**
 * Controls events over the Free Cells.
 */
public class FreeCellFreeCellController extends java.awt.event.MouseAdapter {

	protected FreeCell theGame;
	protected PileView src;

	/**
	 * FreeCellFreeCellController constructor comment.
	 */
	public FreeCellFreeCellController(FreeCell theGame, PileView src) {

		super();

		this.src = src;
		this.theGame = theGame;
	}
	
	/**
	 * Try to play the faceup card directly to the foundation.
	 *
	 * @param me java.awt.event.MouseEvent
	 */
	public void mouseClicked(MouseEvent me) {

		if (me.getClickCount() > 1) {

			// Point to our underlying model element.
			Pile pile = (Pile) src.getModelElement();
			if (pile.empty()) return;  // nothing to do on empty pile.
		
			// See if we can move this one card.
			boolean moveMade = false;
			for (int f = 1; f <=4; f++) {
				Pile fp = (Pile) theGame.getModelElement (FreeCell.baseCellPrefix + f);
				Move m = new FreeCellBaseMove (pile, null, fp);
				if (m.doMove(theGame)) {

					// Success! Add this move to our history.
					theGame.pushMove (m);
				
					moveMade = true;
					theGame.refreshWidgets();
					break;
				}
			}
		}
	}
	
	
	//To Deal with MousePressed events
	public void mousePressed(MouseEvent me) {

		//java.awt.Toolkit.getDefaultToolkit().beep();
		//Get the items we need
		Container c = theGame.getContainer();
		Pile srcPile = (Pile) src.getModelElement();

		//Return in the case that the pile clicked on is empty
		if (srcPile.count() == 0) {
			c.releaseDraggingObject();
			return;
		}

		// To get the top card in the pile itself.
		// Note: This method will alter the model for columnView if the condition is met.	
		CardView cardView = src.getCardViewForTopCard(me);

		// Safety Check
		if (cardView == null) {
			System.err.println("FreeCellFreeCellController::pressCardController(): Unexpectedly encountered an empty pile.");
			return;
		}

		// Another Safety Check
		Widget w = c.getActiveDraggingObject();
		if (w != Container.getNothingBeingDragged()) {
			System.err.println("FreeCellFreeCellController::pressCardController(): Unexpectedly encountered a Dragging Object during a Mouse press.");
			return;
		}

		// We tell the container what item is being dragged (and where in the Widget it was clicked)...
		c.setActiveDraggingObject(cardView, me);

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
			System.err.println("FreeCellFreeCellController::mouseReleased(): somehow CardView model element is null.");
			return false;
		}
		
		//Get sourceWidget for card being dragged
		Container c = theGame.getContainer();
		Widget sourceWidget = c.getDragSource();
		
		//Safety Check
		if (sourceWidget == null) {
			System.err.println("FreeCellFreeCellController::mouseReleased(): somehow dragSource for container is null.");
			return false;
		}
		
		Pile toPile = (Pile) src.getModelElement();
		
		//if (toPile.empty()) java.awt.Toolkit.getDefaultToolkit().beep();
		
		Column sourceColumn = (Column) sourceWidget.getModelElement();
		Move m = new ColumnFreeCellMove(sourceColumn, column, toPile);
		
		if (m.valid(theGame)) {
			m.doMove(theGame);
			theGame.pushMove(m);
		} else {
			sourceWidget.returnWidget(columnView);
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
			System.err.println("FreeCellFreeCellController::mouseReleased(): somehow CardView model element is null.");
			return false;
		}
		
		//Get sourceWidget for card being dragged
		Container c = theGame.getContainer();
		Widget sourceWidget = c.getDragSource();
		
		//Safety Check
		if (sourceWidget == null) {
			System.err.println("FreeCellFreeCellController::mouseReleased(): somehow dragSource for container is null.");
			return false;
		}
		
		Pile toPile = (Pile) src.getModelElement();
		
		//if (toPile.empty()) java.awt.Toolkit.getDefaultToolkit().beep();
		
		Pile sourcePile = (Pile) sourceWidget.getModelElement();
		Move m = new FreeCellFreeCellMove(sourcePile, theCard, toPile);
		
		if (m.valid(theGame)) {
			m.doMove(theGame);
			theGame.pushMove(m);
		} else {
			sourceWidget.returnWidget(cardView);
		}
		
		return true;
	}
}
