package tutorial.mar21;

import java.awt.Color;
import ks.common.games.Solitaire;

/**
 * Represents the moving of a card from one pile to another.  
 * 
 * @author George Heineman
 */
public class UpdateColorMove extends ks.common.model.Move {
	/** Old color. */
	Color color;
	
	/** Affected element. */
	RectangleElement rect;
	
	/** UpdateColorMove needs to know target rectangle. */
	public UpdateColorMove (RectangleElement re) {
		super();
		this.rect = re;
	}
	
	/**
	 * Execute by altering the color randomly
	 */
	public boolean doMove (Solitaire theGame) {
		// VALIDATE:
		if (valid (theGame) == false) {
			return false;
		}

		// EXECUTE:
		int r1 = (int) (Math.random()*256);
		int r2 = (int) (Math.random()*256);
		int r3 = (int) (Math.random()*256);
		
		color = rect.getColor();
		rect.setColor(new Color(r1,r2,r3));
		
		return true;
	}
	
	/**
	 * To undo this move, we move the cards from the toPile back to the fromPile
	 * @param theGame ks.common.games.Solitaire
	 */
	public boolean undo(Solitaire game) {
		rect.setColor(color);
		return true;
	}
	
	/** Validate MoveCardMove. */
	public boolean valid (Solitaire game) {
		return true;
	}
}