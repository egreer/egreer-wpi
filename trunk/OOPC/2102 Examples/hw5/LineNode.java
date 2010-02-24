package hw5;

import amida.AmidaException;
import amida.Edge;

/**
 * Represents a vertical line on the Amida Board. 
 * 
 * All horizontal edges emanate to the right.
 * 
 * @author heineman
 */
public class LineNode {
	/** Which line. */
	int line;
	
	/** First edge that is connected (vertically) to this line. */
	EdgeNode  head;
	
	/** Next one. */
	LineNode   next;
	
	/** Minimum distance to the next nearest edge. */
	public static final int MIN = 5;
	
	/** 
	 * Construct a line node for the given vertical line.
	 */
	public LineNode (int line) {
		this.line = line;
	}

	/** 
	 * Add an edge to the neighboring line to the right.
	 * 
	 * @param verticalPos  vertical position at which to add line.
	 * 
	 * @return  false if given line is not a neighbor, or verticalPos is invalid
	 * @throws AmidaException 
	 */ 
	public boolean add (int verticalPos) throws AmidaException {

		if (verticalPos < 0) {
			return false;
		}
		
		/** Add edge emanating from this.line to the directed line. */
		insert (verticalPos);
		return true;
	}

	/** 
	 * Add a horizontal edge (this.line, line) at verticalPos.
	 * 
	 * @param verticalPos     specified height
	 * @exception             AmidaException
	 * @return
	 */
	private void insert(int verticalPos) throws AmidaException {
		Edge e = new Edge (this.line, verticalPos);
		
		if (head == null) {
			head = new EdgeNode(e);
			return;
		}
		
		// Insert in order
		Edge e1 = head.edge;
		if (e1.getVerticalPosition() > verticalPos) {
			EdgeNode first = new EdgeNode (e);
			first.next = head;
			head = first;
			return;
		}
		
		// OK. Need to move through the list.
		EdgeNode prev = head;
		EdgeNode next = head.next;
		
		while (next != null) {
			// insert at proper location
			if (next.edge.getVerticalPosition() > verticalPos) {
				prev.next = new EdgeNode (e);
				prev.next.next = next;
				return;
			}			
			
			// ADVANCE
			prev = prev.next;
			next = next.next;
		}
		
		// if we get here, then we have gone too far! Add here
		prev.next = new EdgeNode(e);
	}

	/**
	 * Remove all edges.
	 */
	public void removeAll() {
		head = null;
	}

	/**
	 * Remove the edge to the right for this given position.
	 * 
	 * @param vpos
	 */
	public boolean remove(int vpos) {
		if (head == null) {
			return false;
		}
		
		// Remove first, if that is it
		if (head.edge.getVerticalPosition() == vpos) {
			head = head.next;
			return true;
		}
		
		// OK. Need to move through the list.
		EdgeNode prev = head;
		EdgeNode next = head.next;
		
		while (next != null) {
			// insert at proper location
			if (next.edge.getVerticalPosition() == vpos) {
				prev.next = next.next;
				return true;
			}			
			
			// ADVANCE
			prev = prev.next;
			next = next.next;
		}
		
		// nothing doing!
		return false;
	}

	/**
	 * Are we too close, that is, within MIN distance in terms of pixels.
	 *  
	 * @param verticalPos
	 * @return
	 */
	public boolean tooClose(int verticalPos) {
		EdgeNode node = head;
		while (node != null) {
			if (Math.abs(verticalPos - node.edge.getVerticalPosition()) <= 5) {
				return true;
			}
			
			node = node.next;  // ADVANCE
		}
		
		// must be ok!
		return false;
	}

	/**
	 * Return the number of horizontal edges to the right of this line.
	 * 
	 * @return
	 */
	public int numEdges() {
		int numEdges = 0;
		
		EdgeNode node = head;
		while (node != null) {
			numEdges++;
			
			node = node.next;
		}
		
		return numEdges;
	}

	
	/**
	 * Return the nth edge (1 <= n <= numEdges()\
	 * 
	 * @param num
	 * @return
	 */
	public Edge getEdge(int num) {
		int numEdges = 0;
		
		EdgeNode node = head;
		while (node != null) {
			numEdges++;
			
			if (numEdges == num) {
				return node.edge;
			}
			
			node = node.next;
		}
		
		// gone too far!
		return null;
	}

	/** 
	 * Returns Edge for next horizontal edge after this pos (or null if none exists).
	 * 
	 * @param vpos  height at which to start checking.
	 * @return      next horizontal edge to the right after this vertical position, or null.
	 */
	public Edge getEdgeAfter(int vpos) {
		EdgeNode node = head;
		while (node != null) {
			if (node.edge.getVerticalPosition() > vpos) {
				return node.edge;
			}
			
			node = node.next;
		}
		
		// gone too far!
		return null;
	}
}
