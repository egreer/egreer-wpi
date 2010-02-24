package proj2;

/** Edges are between two towns, each edge carries a weight that is the distance between them
 * 
 * @author Eric Greer 
 */
public class Edge {
	
	/** Contains the First Node */
	public Node firstNode;
	
	/** Contains the Second Node */
	public Node secondNode;
	
	/** Contains the weight, or distance, of the edge */
	public double weight; 
	
	
	
	/** Construtor to create an Edge
	 * 
	 * @param town1		This is the node of the first town
	 * @param town2		This is the node of the second town
	 * @param distance	This is the distance between the two towns
	 */
	public Edge(Node town1, Node town2, double distance) {
		
		this.firstNode = town1;
		this.secondNode = town2;
		this.weight = distance;
	}
}
