package proj2;

/** A graph is a collection of nodes (or towns), between two elevations and edges that connect them.   
 * 
 * @author Eric Greer
 *
 */
public class Graph {
	
	/** The name/number of the graph */
	public int graphNumber;
	
	/** The array of towns in the graph */
	public Node[] Towns;
	
	/** The edges in the graph */
	public Edge[] Edges;
	
	/** The total number of edges in the graph */
	public int totalEdges;
	
	/** The total number of towns in the graph */
	public int totalTowns;
	
	/** The starting elevation of the graph*/
	public int startPoint;
	
	/** The ending elevation of the graph*/
	public int endPoint;
	
	
	
	/** Constructor to create a graph
	 * 
	 * @param number	The number of the graph (1-10) 
	 * @param tArray	An array of towns in the graph
	 * @param eArray	An array of edges in the graph
	 * @param tEdges	The total number of edges 
	 * @param tTowns	The total number of towns 
	 * @param start		The starting elevation of towns in the graph 
	 * @param end		The ending elevation of towns in the graph
	 */
	public Graph(int number, Node[] tArray, Edge[] eArray, int tEdges, int tTowns, int start, int end){
		this.graphNumber = number;
		this.Towns = tArray;
		this.Edges = eArray;
		this.totalEdges = tEdges;
		this.totalTowns = tTowns;
		this.startPoint = start;
		this.endPoint = end;
}
	
		
	
	/** The toString method outputs the number of towns in each graph,
	 *  the elevation cut-off points used, and the number of edges in each graph.
	 * 
	 * @return Outputs information about the graph to a string 
	 */
	public String toString () {
		return "For graph #" + graphNumber 
		 + "\nThe total number of towns in the graph are " + totalTowns 
		 + "\nThe elevation cut-off points are " + startPoint + " and " + endPoint 
		 + "\nThe total number of edges in the graph are " + totalEdges +"\n\n";
	}
	
	/** Sets the graph number
	 * 
	 * @param	Takes in a number to be the name of the graph
	 * @return 	Nothing
	 */
	public void setGraph(int t){
		this.graphNumber = t;
	}
	
}
