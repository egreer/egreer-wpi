package yostinso;

import ks.common.model.Pile;


/**
 * SuitPile model element for the FlowerGarden solitaire variation
 * Creation date: (11/29/2001 11:11:28 AM)
 * @author: E.O. Stinson
 */
public class SuitPile extends Pile {
	protected int suit;
/**
 * SuitPile constructor comment.
 */
public SuitPile(int suit) {
	super();
	this.suit = suit;
}
/**
 * SuitPile constructor comment.
 */
public SuitPile(int suit, String s) {
	super(s);
	this.suit = suit;
}
/**
 * Return the suit assigned to this SuitPile.
 * Creation date: (11/29/2001 11:41:24 AM)
 */
public int getAssignedSuit() { return suit; }
}
