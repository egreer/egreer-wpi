package heineman.idiot;


import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Stack;
import ks.common.model.Column;
import ks.common.model.Move;

/** 
 * Action for Idiot Game: Move card from one column to another.
 * <p>
 * SIGNATURE: MoveCard (Column from, Card moving, Column to)<br>
 * VALIDATION: to.empty()<br>
 * ACTION: to.add (moving);<br>
 * UNDO: from.add (to.get());<br>
 * <p>
 * Note that the <code>from</code> column has already been modified when the
 * drag is present.
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class MoveCardsMove extends Move {
	/** From and To columns. */
	protected Column from;
	protected Column to;

	/** Moving card. */
	protected Column movingCol;
/**
 * MoveCardMove constructor .
 * @param moveType int
 */
public MoveCardsMove (Column from, Column moving, Column to) {
	super ();

	this.from = from;
	this.movingCol = moving;
	this.to = to;
}
/**
 * doMove method comment.
 */
public boolean doMove(Solitaire theGame) {
	// VALIDATE:
	if (valid (theGame) == false)
		return false;
		
	// EXECUTE
	Stack st = new Stack();
	while (!movingCol.empty()) {
		st.add(movingCol.get());
	}
	while (!st.empty()) {
		to.add(st.get());
	}
	return true;
}
/**
 * undo move.
 */
public boolean undo(Solitaire theGame) {
	// VALIDATE
	if (to.empty()) return false;
	
	// UNDO
	// I'll let you figure out how to deal with undo. Hint, perhaps
	// you could keep track of how many cards were moved?
	
	return false;
}
/**
 * validate move.
 */
public boolean valid (Solitaire theGame) {
	// VALIDATION:
	return true;
}
}
