package amida;

/**
 * Represents an edge between two neighboring lines at a specific position.
 * 
 * Can be used to traverse from one vertical line to another at a
 * specific position.
 * 
 * Note that once constructed, an Edge object cannot be changed.
 * 
 * @author heineman
 * @version 1.0
 */
public class Edge {

	/** Edge starts on this vertical line. */
	final int startLine;
	
	/** Edge ends on this vertical line. */
	final int endLine;

	/** Remember vertical position. */
	final int verticalPos;
	
	/**
	 * Construct an edge at the given vertical position between the 
	 * neighboring lines [line, line+1]
	 * 
	 * @param line             starting vertical line (greater than zero).
	 * @param verticalPos      vertical position of the edge (greater than zero).
	 * @exception              AmidaException thrown on illegal input parameters
	 */
	public Edge (int line, int verticalPos) throws AmidaException {
		if (line <= 0) {
			throw new AmidaException ("Vertical lines identified by numbers greater than zero.");
		}
		if (verticalPos <= 0) {
			throw new AmidaException ("Edges can only located at vertical positions greater than zero.");
		}
		
		this.startLine = line;
		this.endLine = line+1;
		this.verticalPos = verticalPos;
	}
	
	/**
	 * Return String representation of object.
	 */
	public String toString () {
		return "[" + startLine + "," + endLine + " @ " + verticalPos + "]";
	}
	
	/**
	 * Determine equals() comparsion for Edge objects.
	 * 
	 * @param o     Object of class Edge against which this object is compared
	 * @return      true if edges represent the same edge.
	 */
	public boolean equals (Object o) {
		if (o == null) {
			return false;  // never possible
		}
		
		// same class? compare instance variables
		if (o.getClass() == getClass()) {
			Edge e = (Edge) o;
			return (e.startLine == startLine) && (e.endLine == endLine) && (e.verticalPos == verticalPos);
		}
		
		return false; // nope
	}
	
	/**
	 * The dreaded hashcode.
	 * 
	 * Note that none of these values can be zero.
	 */
	public int hashCode() {
		return startLine * endLine * verticalPos;
	}
	
	
	/**
	 * Return the number of the vertical line at the left side of this Edge.
	 * 
	 * @return  the number of the vertical line at the left side of this Edge, which is
	 *          greater than zero.
	 */
	public int getStartLine() {
		return startLine;
	}
	
	/**
	 * Return the number of the vertical line at the right side of this Edge.
	 * 
	 * @return  the number of the vertical line at the right side of this Edge, which is
	 *          greater than zero.
	 */
	public int getEndLine() {
		return endLine;
	}
	
	/**
	 * Return the verticalPosition of this edge.
	 * 
	 * @return  the vertical position of this edge, which is greater than zero.
	 */
	public int getVerticalPosition() {
		return verticalPos;
	}
	
}
