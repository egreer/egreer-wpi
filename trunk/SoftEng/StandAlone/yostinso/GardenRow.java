package yostinso;

import ks.common.model.Column;


/**
 * GardenRow model element for the FlowerGarden solitaire variation
 * Creation date: (11/29/2001 11:15:26 AM)
 * @author: E.O. Stinson
 */
public class GardenRow extends Column {
	protected int lastSelectionPoint = 0;
	
	/**
	 * GardenRow constructor comment.
	 */
	public GardenRow() {
		super();
	}
	
	/**
	 * GardenRow constructor comment.
	 */
	public GardenRow(String s) {
		super(s);
	}
	
	/**
	 * Return the location of the last Selection point.
	 * @return
	 */
	public int getLastSelectionPoint() {
		return lastSelectionPoint;
	}

	/**
	 * Set where the last selection point was.
	 * @param newPoint
	 */	
	public void setLastSelectionPoint(int newPoint) {
		this.lastSelectionPoint = newPoint;
	}
}
