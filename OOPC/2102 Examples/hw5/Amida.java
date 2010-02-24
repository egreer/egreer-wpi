package hw5;

import amida.AmidaException;
import amida.AmidaInterface;
import amida.Edge;

/**
 * Homework 5.
 * 
 * Amida board has a fixed height > 0.
 * 
 * No edge can be within five pixels (vertically) from any other edge between
 * the same start and end line.
 * 
 * @author heineman
 */
public class Amida implements AmidaInterface {

	/** 
	 * Number of vertical lines. 
	 * 
	 * Make sure that numLines = length of the list headed by 'head'. 
	 */
	int numLines = 0;
	
	/** Minimum separation. */
	int minSeparation;
	
	/** First of the lines in the board. */
	LineNode head;
	
	/**
	 * Create an initial Board with zero lines.
	 */
	public Amida()  {
		
	}
	
	/** 
	 * Extend the Amida board to the right by one vertical line. 
	 *
	 * Note that Lines are numbered 1 through numLines
	 */
	public void addLine() {
		numLines++;
		
		// append to the end
		if (head == null) {
			head = new LineNode(numLines);
		} else {
			LineNode node = head;
			while (node.next != null) {
				// advance
				node = node.next;
			}
			
			// append at the end.
			node.next = new LineNode(numLines);
		}
	}
	
	/**
	 * Return the number of vertical lines on the board.
	 *  
	 * @return   # of lines.
	 */
	public int numLines() {
		return numLines;
	}
	
	/** 
	 * Remove the rightmost line.
	 * 
	 * Must also update all internal information.
	 * 
	 */
	public boolean removeLine ()  {
		if (numLines == 0) {
			return false;
		}

		// down to one? Remove it entirely
		if (numLines == 1) {
			head = null;
			numLines = 0;
			return true;
		}
		
		// go through the list until we get to the last one.
		LineNode prev = head;
		LineNode node = head.next;
		while (node.next != null) {
			// advance
			node = node.next;
			prev = prev.next;
		}
		
		// remove the last one.
		prev.removeAll();  // remove all edges.
		prev.next = null;
		numLines--;
		return true;		
	}
	
	/**
	 * Return the nth line of the board, from left to right.
	 * 
	 * @param   line    the desired vertical line for whom we wish to return the LineNode.
	 * @return  LineNode representing the nth line from the left or null.
	 */
	private LineNode getNthLine(int line) {
		LineNode node = head;
		int numToSkip = line-1;
		while (numToSkip != 0) {  // find the right one
			if (node == null) {
				return null;
			}
			
			numToSkip--;
			node = node.next;  // ADVANCE
		}
		
		return node;		
	}

	/**
	 * Construct an edge between the vertical line 'line' and the 
	 * neighboring (to the right) vertical line 'line+1' at vertical 
	 * position 'verticalPos'.
	 * 
	 * @param line              starting vertical line
	 * @param verticalPos       vertical position on which to draw.
	 * 
	 * @exception     on any error, exception contains error message
	 *                1) invalid line, "invalid vertical line"
	 *                2) invalid verticalPos, "invalid vertical position"
	 *                3) edge too close, "line is too close to existing edge"
	 */
	public void addEdge (int line, int verticalPos) throws AmidaException {
		if (line < 1 || line >= numLines()) {
			// should be Invalid vertical line to add edges
			throw new AmidaException ("invalid vertical line");
		}
		
		if (verticalPos < 0) {
			throw new AmidaException ("invalid vertical position");
		}
		
		if (tooClose (line, verticalPos)) {
			throw new AmidaException ("line is too close to existing edge");
		}
		
		if (line - 1 >= 1) {
			if (tooClose(line-1, verticalPos)) {
				throw new AmidaException ("line is too close to existing edge");
			}
		}
		
		if (line + 1 <= numLines()) {
			if (tooClose(line+1, verticalPos)) {
				throw new AmidaException ("line is too close to existing edge");
			}
		}
		
		// aha! Now can add it.
		LineNode node = getNthLine (line);
		if (node == null) {
			throw new AmidaException ("Invalid vertical line to add edges"); // shouldn't get here
		}
		
		// when we get here, node points to the right one. 
		node.add(verticalPos);
	}
	
	/**
	 * Determine if a horizontal edge already exists emanating to the right of the 
	 * specified line within 5 pixels of the specified vertical position.
	 * 
	 * If there is already a horizontal edge (on either line or line+1) within 5 pixels
	 * of the desired verticalPosition, then it is too close.
	 * 
	 * @param line           start of vertical line
	 * @param verticalPos    height at which to draw edge
	 * 
	 * @return               true if too close; false otherwise.
	 */
	public boolean tooClose(int line, int verticalPos) {
		LineNode node = getNthLine (line);
		if (node == null) {
			return false;
		}
		
		// when we get here, node points to the right one.
		return node.tooClose(verticalPos);
	}

	public int numEdges(int line) throws AmidaException {
		LineNode node = getNthLine (line);
		if (node == null) {
			throw new AmidaException ("No such line:" + line);
		}

		
		// when we get here, node points to the right one.
		return node.numEdges();
	}

	public Edge getEdge(int line, int num) throws AmidaException {
		LineNode node = getNthLine (line);
		if (node == null) {
			throw new AmidaException ("No such line:" + line);
		}
		
		// when we get here, node points to the right one.
		return node.getEdge(num);
	}

	/**
	 * Remove the given edge (line, line+1, vpos)
	 * 
	 */
	public boolean removeEdge(Edge e) {
		int line = e.getStartLine();
		int vpos = e.getVerticalPosition();

		LineNode node = getNthLine (line);
		if (node == null) {
			return false;
		}
		
		// when we get here, node points to the right one.
		return node.remove(vpos);
	}

	/**
	 * Remove all horizontal edges on all lines.
	 */
	public void removeAll() {
		LineNode node = head;
		while (node != null) {
			node.removeAll();			
			node = node.next;  // ADVANCE
		}		
	}

	/**
	 * Compute the exit point for the given line. 
	 * 
	 * To use recursive solution, replace method implementation with
	 * 
	 *     return exitLine(line, 0);
	 * 
	 * Note that the vertical position will never be zero, so we can safely start there.
	 */
	public int exitLine(int line) {
		
		int vPos = 0;
		while (true) {
			// We want to walk along these points. Must check [line-1,line] and [line,line+1]
			LineNode node = getNthLine (line);
			LineNode prev = getNthLine(line-1); // may be null
			
			Edge leftEdge = null;
			if (prev != null) {
				leftEdge = prev.getEdgeAfter(vPos);
			}
			Edge rightEdge = node.getEdgeAfter(vPos);

			// multiple cases. Last one? Then we are out!
			if (leftEdge == null && rightEdge == null) {
				return line;
			} else if (leftEdge != null && rightEdge != null) {
				// both present? pick CLOSER
				int diff = leftEdge.getVerticalPosition() - vPos;
				int diff2 = rightEdge.getVerticalPosition() - vPos;
				if (diff < diff2) {
					line = line-1;
					vPos = leftEdge.getVerticalPosition();
				} else {
					line = line+1;
					vPos = rightEdge.getVerticalPosition();
				}
			} else if (leftEdge != null) {
				// either one? Just go with it
				line = line-1;
				vPos = leftEdge.getVerticalPosition();
			} else {
				// only remaining case is that rightEdge != null
				line = line+1;
				vPos = rightEdge.getVerticalPosition();
			}
		}
	}
	
	/**
	 * Find the next nearest horizontal edge vertically, either on the line to the 
	 * left (identified by line-1) or on the current line (identified by line).
	 *  
	 * Recursive Solution. To use this, replace the exitLine(int) method with
	 * 
	 *    public int exitLine (int line) {
	 *       return exitLine (line, 0);
	 *    }
	 * 
	 * @param line
	 * @param vPos
	 * @return
	 */
	private int exitLine (int line, int vPos) {
		LineNode node = getNthLine (line);
		if (node == null) {
			return 0;
		}
		
		// We want to walk along these points. Must check [line-1,line] and [line,line+1]
		LineNode prev = getNthLine(line-1); // may be null
		Edge leftEdge = null;
		Edge rightEdge = null;
		
		if (prev != null) {
			leftEdge = prev.getEdgeAfter(vPos);
		}
		rightEdge = node.getEdgeAfter(vPos);

		// multiple cases. Last one? Then we are out!
		if (leftEdge == null && rightEdge == null) {
			return line;
		}
		
		// both present? pick CLOSER
		if (leftEdge != null && rightEdge != null) {
			int diff = leftEdge.getVerticalPosition() - vPos;
			int diff2 = rightEdge.getVerticalPosition() - vPos;
			if (diff < diff2) {
				return exitLine (line-1, leftEdge.getVerticalPosition());
			} else {
				return exitLine (line+1, rightEdge.getVerticalPosition());
			}
		}
		
		// either one? Just go with it
		if (leftEdge != null) {
			return exitLine (line-1, leftEdge.getVerticalPosition());
		} 
		
		// The only case LEFT is the one where rightEdge != null
		return exitLine (line+1, rightEdge.getVerticalPosition());
	}
	
}
