package nov13;

public class PointNode {

	/** Point being represented. */
	Point   value;
	
	/** Next point in the list. */
	PointNode  next;
	
	/**
	 * Return node representing given point.
	 * 
	 * @param p   Point in the list
	 */
	public PointNode (Point p) {
		value = p;
		next = null;
	}

}
