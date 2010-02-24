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
import ks.common.view.PileView;
import ks.common.view.Widget;


/**This object controls events that occur over a particular PileView object.
 * These events include mouseReleased
 */
public class GrandfatherPileController extends MouseAdapter{
	protected GrandfatherClock theGame;
	protected PileView source;
	
	public GrandfatherPileController(GrandfatherClock theGame, PileView source){
		super();
		this.theGame = theGame;
		this.source = source;
	}
	
	/**Completes a Drag event
	 */
	public void mouseReleased(MouseEvent me){
		Container cont = theGame.getContainer();
		
		//Retrieve the object being dragged
		Widget w = cont.getActiveDraggingObject();
		
		//If nothing is being dragged, exit
		if(w == Container.getNothingBeingDragged())
			return;
		
		//Extract the card being dragged
		CardView cardView = (CardView) w;
		Card c = (Card) cardView.getModelElement();
		
		//Find destination pile
		Pile destinationPile = (Pile) source.getModelElement();
		
		//Find the source column
		ColumnView cvSrc = (ColumnView) cont.getDragSource();
		Column sourceColumn = (Column) cvSrc.getModelElement();
		
		//Make the move
		Move m = new ColumnToPileMove(sourceColumn, c, destinationPile);
		if(m.doMove(theGame))
			theGame.pushMove(m);
		else
			cvSrc.returnWidget(cardView);
		
		//theGame.refreshWidgets();
		theGame.repaintAll(cardView.getBounds());
		theGame.refreshWidgets();
		
		cont.releaseDraggingObject();
	}
}