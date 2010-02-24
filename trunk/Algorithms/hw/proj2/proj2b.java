package proj2;
/** To run this, run main in the class proj2, with args of the file for inputs
 * 
 * @author Eric Greer
 *
 */
public class proj2b {
	
	/** Keeps track of the total number of comparisons made in the sortEdge Method 
	 * 
	 */
	public static int totalSortEdgeComparisons = 0;
	
	/** Sorts an edge array by weight
	 * 
	 * @param e 	Takes in an Edge array to be sorted
	 * @return		Returns the sorted array.
	 */
	public static Edge[] sortEdges(Edge[] e){
		Edge[] sEdges = e;
		totalSortEdgeComparisons = 0;
		
		//Goes through the array to sort by weight.
		int x = 0;
		while(sEdges[x] != null){

			int t = 1;
			while(sEdges[t] != null){
				Edge tempEdge = null;


				if (sEdges[t-1].weight > sEdges[t].weight){
					tempEdge = sEdges[t];
					sEdges[t] = sEdges[t-1];
					sEdges[t-1] = tempEdge;

				}
				totalSortEdgeComparisons++;
				t++;
			}


			x++;
		}
		
		
		
		return sEdges;
	}

	/** Tells if the town is already in a town array
	 * 
	 * @param c		Takes an array of checked towns
	 * @param t		The town checking for in the array
	 * @return		true is contained, false otherwise.
	 */
	public static boolean inCheck(Town[] c, Town t){
		
		int q = 0;
		while(c[q] != null){
			if(t.id == c[q].id)return true;
				
			q++;	
		}
		
	
		
		return false;
	}
	
	/** Creates MST using Prim's Algorithm. 
	 * 
	 * @param g		Takes in a graph
	 * @return		Returns a string output of a MST
	 */
	public static String Prims(Graph g){
		Edge[] edgeArray = sortEdges(g.Edges);
		int numberOfEdges = edgeArray.length;
		Town[] checked = new Town[g.totalTowns];
		String output = ""; 
		String firstTownName = edgeArray[0].firstNode.theTown.name + ", " + edgeArray[0].firstNode.theTown.state;
		double totalWeight = 0; 
		int totalComparisons = totalSortEdgeComparisons;
		
		
		int x = 0;
		for (int i = 1; i <= numberOfEdges; i++){
						
			int n = 0;
			while (n < numberOfEdges){
				double min = 999999999999999.9;
				
				double townID = edgeArray[n].firstNode.theTown.id;

				if(!(inCheck(checked, edgeArray[n].firstNode.theTown))){
					for (int j = 0 ; j < numberOfEdges; j++){
						if (townID == edgeArray[n].firstNode.theTown.id ||
								townID == edgeArray[n].secondNode.theTown.id){
							if(edgeArray[j].weight < min){
								min = edgeArray[j].weight;
								totalComparisons++;
							}
							totalComparisons++;
						}
					}
				
					totalWeight = edgeArray[n].weight;
					checked[x] = edgeArray[n].firstNode.theTown;
					x++;
				}
			n++;
			}
		}
				
		output = "For graph #" + g.graphNumber + " The first town was " + firstTownName 
		+ ". The weight of the MST was " + totalWeight + ". The total number of weight comaprisons is " + 
		totalComparisons + ".\n";
		
		return output;
	}

	public static String Dijkstra(Graph g){
		Edge[] edgeArray = sortEdges(g.Edges);
		int numberOfEdges = edgeArray.length;
		
		String output = ""; 
		String firstTownName = edgeArray[0].firstNode.theTown.name + ", " + edgeArray[0].firstNode.theTown.state;
		String lastTownAdded = "";
		double distanceToSourceNode = 0;
		
		
		for (int i = 1; i <= numberOfEdges; i++){
			if (! inCheck(checkedTowns, edgeArray[i].firstNode.theTown)){
				Edge minEdge = filterEdges(edgeArray, edgeArray[i].firstNode.theTown);
				lastTownAdded = minEdge.secondNode.theTown.name + ", " + minEdge.secondNode.theTown.state;
			}
			
		}		
		
		output = "For graph #" + g.graphNumber +" the tree grew from " + firstTownName + ". The last town added was" +
			lastTownAdded + ". The distance from the source node is " + distanceToSourceNode;
		
		return output;
	}
	/** Filters an Edge list, and returns min.
	 * 
	 * @param e		the edge list
	 * @param t		the town that all edges lead to 
	 * @return		the new edge list
	 */
	public static Edge filterEdges(Edge[] e, Town t){
		Edge minEdge = new Edge(null, null, 90999999999.9999);
		Edge finalEdge = null;
				int x = 0;
		while (e[x] != null){
			if (e[x].firstNode.theTown.id == t.id || e[x].secondNode.theTown.id == t.id){
				if(minEdge.weight < e[x].weight){
					minEdge = e[x];
				}
				
			}
			
		
		Edge y = filterEdges(e, minEdge.secondNode.theTown); 
		if  (minEdge.weight < y.weight){
			minEdge = y;
			finalEdge = new Edge(e[x].firstNode, y.secondNode, y.weight + e[x].weight);
			}
		
		x++;
		}
		
		return finalEdge;
		
	}

	/** Contains the checked towns
	 * 
	 */
	public static Town[] checkedTowns = new Town[9999999];
}
