package amida;

/**
 * Represents the behavior of a class to be used in an Amida GUI.
 * 
 * Your class must implement this interface to complete the solution
 * for Homework 5
 *
 * Note: You cannot change this interface.
 * 
 * Vertical lines are identified by an integer >= 1 from left to right.
 * 
 * @author heineman
 * @version 1.0
 */
public interface AmidaInterface {

	/**
	 * Return the number of vertical lines on the board.
	 * 
	 * @return   the number of lines on the board.
	 */
	int numLines();
	
	/**
	 * Extend the Amida board to the right by adding one vertical line. 
	 */
	void addLine();
	
	/**
	 * Reduce the Amida board from the right by one vertical line. 
	 * 
	 * You must remove any horizontal edges that are connected to the right-most vertical
	 * line, which is being removed by this method invocation.
	 * 
	 * @return   true if there is at least one vertical line in the board.
	 */
	boolean removeLine();
	
	/**
	 * Construct an edge between the vertical line 'line' and the 
	 * neighboring (to the right) vertical line (line+1) at vertical 
	 * position 'verticalPos'.
	 * 
	 * Note that this method must ensure three things:
	 * 
	 *   1. The requested edge is not too close to a horizontal edge on the desired line. 
	 *   2. If there is a vertical line to the left, then this method must ensure that
	 *      there is no horizontal edge from that line coming in which is within 5 vertical
	 *      pixels of the requested edge
	 *   3. If there is a vertical line to the right, then this method must ensure that
	 *      there is no horizontal edge emanating from that line to the right which is
	 *      within 5 vertical pixels of the requested edge.
	 * 
	 * @param line              starting vertical line
	 * @param verticalPos       vertical position on which to draw.
	 * 
	 * @exception     on any error, exception contains error message
	 *                1) invalid vertical line, "invalid vertical line"
	 *                2) invalid verticalPos, "invalid vertical position"
	 *                3) edge too close, "line is too close to existing edge"
	 */
	void addEdge (int line, int verticalPos) throws AmidaException;
	
	/**
	 * Return the number of horizontal edges adjacent to this vertical line emanating 
	 * to the right.
	 * 
	 * If invoked on the right-most vertical line, you must return zero.
	 * 
	 * @param line    1 <= line < numLines()
	 * @return        number of adjacent horizontal edges to this line.
	 * 
	 * @exception     If designated vertical line does not exist on the board.
	 */
	int numEdges (int line) throws AmidaException;
	
	/**
	 * Return an Edge representing the nth horizontal edge emanating from the given 
	 * vertical line to the right.
	 * 
	 * Note that you can't call this method on the right-most line, since it is
	 * only capable of returning edges that emanate to the right.
	 * 
	 * @param line    Designated vertical line (1 <= line < numLines())
	 * @param num     Desired horizontal edge (1 <= num)
	 * @exception     If designated vertical line does not exist on the board.
	 *
	 * @return        Edge representing the horizontal edge or null if non-existent.
	 */
	Edge getEdge (int line, int num) throws AmidaException;
	
	/**
	 * Remove the desired horizontal edge on the board identified by the edge parameter.
	 * 
	 * @param edge   The edge representing line (n, n+1) and specific vertical position.
	 * 
	 * @return       true if the edge existed and it was removed; false otherwise.
	 */
	boolean removeEdge (Edge edge);
	
	/**
	 * Determine if a horizontal edge can be placed at the given vertical line to the right at the given
	 * vertical position.
	 * 
	 * This checks to see if the horizontal edge [line, line+1, verticalPos] is too close to an existing 
	 * horizontal edge. 
	 * 
	 * @param line           start of vertical line
	 * @param verticalPos    height at which to draw edge
	 * @exception            AmidaException if line is invalid.
	 * 
	 * @return               true if too close; false otherwise.
	 */
	boolean tooClose(int line, int verticalPos) throws AmidaException;	
	
	/**
	 * Remove all horizontal edges on the board.
	 */
	void removeAll();
	
	/**
	 * Determine where one exits the Amida board when entering the given vertical line.
	 * 
	 * Read the rules of Amida to understand how this method should work.
	 * 
	 * Do this one last! until you have written it, just have it 'return 0'; 
	 * 
	 * http://web.cs.wpi.edu/~heineman/html/teaching_/CS2102_2006/projects/amida.html
	 * 
	 * @param    vertical line number (greater than 0 and <= numLines())
	 * @return   line number where the exit line will be.
	 */
	int exitLine(int line);
}
