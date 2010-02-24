package brlandry;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.view.CardView;
import ks.common.view.ColumnView;
import ks.common.view.Container;
import ks.common.view.Widget;


/**Responds to mouse events over the ColumnView widget.  
 * These incluse mouseClicked, mousePressed, and mouseReleased.
 */
public class GrandfatherColumnController extends MouseAdapter {
	protected GrandfatherClock theGame;
	protected ColumnView source;

	public GrandfatherColumnController(GrandfatherClock theGame, ColumnView source) {
		super();
		this.theGame = theGame;
		this.source = source;
	}

	/**Attempts to move card to foundation
	 * @param me MouseEvent
	 */
	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount() > 1) {
			//System.out.println("GrandfatherColumnController:mouseClicked");
			Column from = (Column) source.getModelElement();

			//iterate over the foundations to see if the move is valid
			for (int f = 0; f < 12; f++) {
				Pile to = (Pile) theGame.getModelElement("pile" + f);
				Move m = new ColumnToPileMove(from, null, to);
				if (m.doMove(theGame)) {
					theGame.pushMove(m);

					theGame.refreshWidgets();
					break;
				}

			}
		}
	}

	/**Begin a Drag move
	 * @param me MouseEvent
	 */
	public void mousePressed(MouseEvent me) {
		//Retrieve the column associated with this ColumnView object
		Column c = (Column) source.getModelElement();

		//If the column is empty, we cannot make a move
		if (c.empty())
			return;

		//Take the top "card" off the column
		CardView cardView = source.getCardViewForTopCard(me);
		
		//This prevents a NullPointerException if the user clicks on a card
		//other than the top card.
		if (cardView == null)
			return;

		Container cont = theGame.getContainer();
		cont.setDragSource(source);
		cont.setActiveDraggingObject(cardView, me);

		theGame.refreshWidgets();
		cardView.refresh();   // redraw moving card for non-flicker behavior
	}

	/**Complete a Drag move
	 * @param me MouseEvent
	 */
	public void mouseReleased(MouseEvent me) {
		Container cont = theGame.getContainer();

		//Retrieve what is being dragged
		Widget w = cont.getActiveDraggingObject();
		//If nothing is being dragged, exit
		if (w == Container.getNothingBeingDragged())
			return;

		//Extract the card being dragged
		CardView cardView = (CardView) w;
		Card c = (Card) cardView.getModelElement();

		//Find destination column
		Column destinationColumn = (Column) source.getModelElement();

		//Find source column
		ColumnView cvSrc = (ColumnView) cont.getDragSource();
		Column sourceColumn = (Column) cvSrc.getModelElement();

		//Make the move
		Move m = new ColumnToColumnMove(sourceColumn, c, destinationColumn);
		if (m.doMove(theGame))
			theGame.pushMove(m);
		else
			cvSrc.returnWidget(cardView);

		theGame.refreshWidgets();
		theGame.repaintAll(cardView.getBounds());
		theGame.refreshWidgets();

		cont.releaseDraggingObject();
	}
}