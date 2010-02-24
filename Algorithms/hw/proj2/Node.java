package proj2;

/** Nodes consists of a town and the number of edges from the town 
 * 
 * @author Eric Greer
 *
 */
public class Node {
	
	/** Contains a town */
	public Town theTown;
	
	/** Contains the number of edges from the town */
	public int degree;

	
	/** Constructor to create a node
	 * 
	 * @param aTown			This is the town 
	 * @param numberEdges	This is the number of edges comming from the town 
	 */
	public Node(Town aTown, int numberEdges){
		this.theTown = aTown;
		this.degree = numberEdges;
	}
}
