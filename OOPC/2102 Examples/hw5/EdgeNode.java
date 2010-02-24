package hw5;

import amida.Edge;

/**
 * Represents the edge connecting two vertical lines.
 * 
 * @author heineman
 */
public class EdgeNode {

	/** An edge. */
	Edge   edge;
	
	/** Refers to the next node in the sequence of edges down the line. */
	EdgeNode   next;
	
	/**
	 * Represents one link in the chain of Edge Nodes on the Line.
	 * 
	 * @param e
	 */
	public EdgeNode (Edge e) {
		this.edge = e;
		this.next = null;
	}
}
