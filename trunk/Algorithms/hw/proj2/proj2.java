package proj2;

import java.io.*;
import java.util.*;

/** Call with the file name as an argument for the main. 
 *  Unless on a really fast machine, and using a huge amount data inputs there won't be enough nodes to generate the larger graphs.
 * 
 * @author Eric Greer
 *
 */

public class proj2 {

	/** Store the total elevation (absolute value) of the towns
	 * 
	 */
	public static double totalElevation = 0;

	/** Stores the total number of towns 
	 * 
	 */
	public static double totalTowns = 0;

	/** townListCreate takes a file and inputs it into a Node array and returns the array sorted by elevation 
	 * 
	 * @param inputFile					The file location, and name of the data
	 * @throws FileNotFoundException	Throws if file doesn't exist
	 * @throws IOException				Throws if there is an error in the file
	 * @return 							Returns the sorted array
	 */
	public static Node[] townListCreate(File inputFile) throws FileNotFoundException, IOException{

		//Variables

		/** uTownList is a unsoted list of all towns */
		Node[] uTownList = new Node[1700000];

		int tempElevation = 0; 

		int x = 0; 

		//Input Reader
		BufferedReader fileInput = new BufferedReader(new FileReader(inputFile));

		String line = fileInput.readLine();
		while (line != null){

			/** Splits the string into an array */
			int begin = 0;
			String[] ar = new String[6];

			for (int i=0 ; i<5 ; i++){
				int end = line.indexOf("\t" , begin);

				ar[i] = line.substring(begin , end);

				begin = end + 1;

			}
			ar[5] = line.substring(begin , line.length()).trim();
			if(ar[5].equals("")) tempElevation = 0;							
			else{
				tempElevation = Integer.valueOf(ar[5]).intValue();
			}


			int tempID = Integer.parseInt(ar[0]);
			String tempName = ar[1];
			String tempState = ar[2];
			double tempLat = Double.valueOf(ar[3]).doubleValue();
			double tempLon = Double.valueOf(ar[4]).doubleValue();


			Town temp = new Town(tempID, tempName, tempState, tempLat, tempLon, tempElevation);

			uTownList[x] = new Node(temp, 0);

			line = fileInput.readLine();

			//Increments
			totalElevation += Math.abs(tempElevation);
			totalTowns++;
			x++;
		}


		/** sTownList is a sorted list of all towns */
		Node[] sTownList = townListSort(uTownList);


		//Outputs the sorted list
		return sTownList;
	}

	/** townListSort takes in an unsorted town node list and returns the list sorted by elevation.
	 * 
	 * @param uTownList		Takes in an unsorted Town Node List
	 * @return				Returns the sorted Node Array
	 */
	public static Node[] townListSort(Node[] uTownList){

		Node[] sTownList = new Node[170000];

		//Goes through the array to sort by elevation.
		int x = 0;
		while(uTownList[x] != null){

			int t = 1;
			while(uTownList[t] !=null){
				Node tempNode = null;


				if (uTownList[t-1].theTown.elevation > uTownList[t].theTown.elevation){
					tempNode = uTownList[t];
					uTownList[t] = uTownList[t-1];
					uTownList[t-1] = tempNode;

				}

				t++;
			}


			x++;
		}


		sTownList = uTownList;

		//Outputs the sorted list
		return sTownList;
	}

	/** Tells if there is and edge in the graphs list of edges.
	 * 
	 * @param e		Takes in an Edge
	 * @param g		Takes in a graph
	 * @return		Returns true if the edge exists in the list
	 */
	public static boolean edgeThere(Edge e, Graph g){
		int i = 0;

		while (!(g.Edges[i] == null)){
			if ((g.Edges[i].firstNode.theTown.id == e.firstNode.theTown.id && g.Edges[i].secondNode.theTown.id == e.secondNode.theTown.id) || 
					(g.Edges[i].firstNode.theTown.id == e.secondNode.theTown.id && g.Edges[i].secondNode.theTown.id == e.firstNode.theTown.id)){
				return true;
			}
			else{
				i++;
			}
		}


		return false;

	}

	/**
	 * @param args	The file path, or file name of data to input.
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException{

		/** Creates the inputFile */
		File inputFile = new File("C:\\testdata"/*args[0]*/);

		// Variables
		Node[] sTownList;
		double interval;

		Random generator = new Random(19580427);

		//Creates the sorted town list
		sTownList = townListCreate(inputFile);


		//Graphs, and setting their numbers

		Graph graph1 = new Graph(1, null, null, 0, 0, 0, 0);
		Graph graph2 = new Graph(2, null, null, 0, 0, 0, 0);
		Graph graph3 = new Graph(3, null, null, 0, 0, 0, 0);
		Graph graph4 = new Graph(4, null, null, 0, 0, 0, 0);
		Graph graph5 = new Graph(5, null, null, 0, 0, 0, 0);
		Graph graph6 = new Graph(6, null, null, 0, 0, 0, 0);
		Graph graph7 = new Graph(7, null, null, 0, 0, 0, 0);
		Graph graph8 = new Graph(8, null, null, 0, 0, 0, 0);
		Graph graph9 = new Graph(9, null, null, 0, 0, 0, 0);
		//Always contains last graph
		Graph graph10 = new Graph(10, null, null, 0, 0, 0, 0);



		//Creates the interval of the graphs 
		interval = totalElevation / totalTowns;

		//Sets the Towns arrays for the graphs, and the total number of towns, and the starting, and ending elevation points
		int x = 0;
		int y = 0;
		int startValue = sTownList[x + y].theTown.elevation;
		int tempTowns = 0;

		Node[] theTowns = new Node[20000];
		while (sTownList[x + y] != null){
			
			if (sTownList[x].theTown.elevation <= startValue + interval){
				theTowns[x] = new Node( sTownList[x + y].theTown, sTownList[x + y].degree);
				
				tempTowns++;

			}else{
				if (graph1.Towns == null){
					graph1.Towns = theTowns;
					graph1.totalTowns = tempTowns;
					graph1.startPoint = startValue;
					graph1.endPoint = sTownList[x + y].theTown.elevation;

				}else if (graph2.Towns == null){
					graph2.Towns = theTowns;
					graph2.totalTowns = tempTowns;
					graph2.startPoint = startValue;
					graph2.endPoint = sTownList[x + y].theTown.elevation;

				}else if (graph3.Towns == null){
					graph3.Towns = theTowns;
					graph3.totalTowns = tempTowns;
					graph3.startPoint = startValue;
					graph3.endPoint = sTownList[x + y].theTown.elevation;

				}else if (graph4.Towns == null){
					graph4.Towns = theTowns;
					graph4.totalTowns = tempTowns;
					graph4.startPoint = startValue;
					graph4.endPoint = sTownList[x + y].theTown.elevation;

				}else if (graph5.Towns == null){
					graph5.Towns = theTowns;
					graph5.totalTowns = tempTowns;
					graph5.startPoint = startValue;
					graph5.endPoint = sTownList[x + y].theTown.elevation;

				}else if (graph6.Towns == null){
					graph6.Towns = theTowns;
					graph6.totalTowns = tempTowns;
					graph6.startPoint = startValue;
					graph6.endPoint = sTownList[x + y].theTown.elevation;

				}else if (graph7.Towns == null){
					graph7.Towns = theTowns;
					graph7.totalTowns = tempTowns;
					graph7.startPoint = startValue;
					graph7.endPoint = sTownList[x + y].theTown.elevation;

				}else if (graph8.Towns == null){
					graph8.Towns = theTowns;
					graph8.totalTowns = tempTowns;
					graph8.startPoint = startValue;
					graph8.endPoint = sTownList[x + y].theTown.elevation;

				}else if (graph9.Towns == null){
					graph9.Towns = theTowns;
					graph9.totalTowns = tempTowns;
					graph9.startPoint = startValue;
					graph9.endPoint = sTownList[x + y].theTown.elevation;

				}


				startValue = sTownList[x + y].theTown.elevation;
				tempTowns = 0;
				y = x-1;
				x = 0;


			}



			//Always contains the last graph
			graph10.Towns = theTowns;
			graph10.totalTowns = tempTowns;
			graph10.startPoint = startValue;
			graph10.endPoint = sTownList[x + y].theTown.elevation;

			x++;

		}



		//Creates edges for Graph1
		graph1.Edges = new Edge[100*graph1.totalTowns*2];

		int i = 0;
		for (int t = 0 ; t < graph1.totalTowns ; t++){

			while(graph1.Towns[t].degree <= 100){
				int randVar = generator.nextInt(graph1.totalTowns);


				if ( graph1.Towns[randVar].degree == 100 || edgeThere(new Edge(graph1.Towns[t], graph1.Towns[randVar], 0.0) , graph1));

				else{
					double tempDistance = 3960 * Math.acos(Math.sin(graph1.Towns[t].theTown.lat) * Math.sin(graph1.Towns[randVar].theTown.lat)
							+ (Math.cos(graph1.Towns[t].theTown.lat) * Math.cos(graph1.Towns[randVar].theTown.lat) * 
									Math.cos(graph1.Towns[t].theTown.lon - graph1.Towns[randVar].theTown.lat))
					);


					graph1.Edges[i] = new Edge(graph1.Towns[t], graph1.Towns[randVar], tempDistance);
					graph1.Edges[i+1] = new Edge(graph1.Towns[randVar], graph1.Towns[t], tempDistance);

					graph1.Towns[t].degree++;
					graph1.Towns[randVar].degree++;
					graph1.totalEdges++;
					i += 2;
				}

			}

		}
		
		System.out.print(proj2b.Dijkstra(graph1));
		System.out.print(proj2b.Prims(graph1));
		
		//Creates edges for Graph2
		graph2.Edges = new Edge[200*graph2.totalTowns*2];

		i = 0;
		for (int t = 0 ; t < graph2.totalTowns ; t++){

			while(graph2.Towns[t].degree <= 200){
				int randVar = generator.nextInt(graph2.totalTowns);


				if ( graph2.Towns[randVar].degree == 200 || edgeThere(new Edge(graph2.Towns[t], graph2.Towns[randVar], 0.0) , graph2)){


				}else{
					double tempDistance = 3960 * Math.acos(Math.sin(graph2.Towns[t].theTown.lat) * Math.sin(graph2.Towns[randVar].theTown.lat)
							+ (Math.cos(graph2.Towns[t].theTown.lat) * Math.cos(graph2.Towns[randVar].theTown.lat) * 
									Math.cos(graph2.Towns[t].theTown.lon - graph2.Towns[randVar].theTown.lat))
					);


					graph2.Edges[i] = new Edge(graph2.Towns[t], graph2.Towns[randVar], tempDistance);
					graph2.Edges[i+1] = new Edge(graph2.Towns[randVar], graph2.Towns[t], tempDistance);

					graph2.Towns[t].degree++;
					graph2.Towns[randVar].degree++;
					graph2.totalEdges++;
					i += 2;
				}

			}

		}

		System.out.print(proj2b.Dijkstra(graph2));
		System.out.print(proj2b.Prims(graph2));
		
//		Creates edges for Graph3
		graph3.Edges = new Edge[300*graph3.totalTowns*2];

		i = 0;
		for (int t = 0 ; t < graph3.totalTowns ; t++){

			while(graph3.Towns[t].degree <= 300){
				int randVar = generator.nextInt(graph3.totalTowns);


				if ( graph3.Towns[randVar].degree == 300 || edgeThere(new Edge(graph3.Towns[t], graph3.Towns[randVar], 0.0) , graph3)){


				}else{
					double tempDistance = 3960 * Math.acos(Math.sin(graph3.Towns[t].theTown.lat) * Math.sin(graph3.Towns[randVar].theTown.lat)
							+ (Math.cos(graph3.Towns[t].theTown.lat) * Math.cos(graph3.Towns[randVar].theTown.lat) * 
									Math.cos(graph3.Towns[t].theTown.lon - graph3.Towns[randVar].theTown.lat))
					);


					graph3.Edges[i] = new Edge(graph3.Towns[t], graph3.Towns[randVar], tempDistance);
					graph3.Edges[i+1] = new Edge(graph3.Towns[randVar], graph3.Towns[t], tempDistance);

					graph3.Towns[t].degree++;
					graph3.Towns[randVar].degree++;
					graph3.totalEdges++;
					i += 2;
				}

			}

		}

		System.out.print(proj2b.Dijkstra(graph3));
		System.out.print(proj2b.Prims(graph3));
		
//		Creates edges for Graph4
		graph4.Edges = new Edge[400*graph4.totalTowns*2];

		i = 0;
		for (int t = 0 ; t < graph4.totalTowns ; t++){

			while(graph4.Towns[t].degree <= 400){
				int randVar = generator.nextInt(graph4.totalTowns);


				if ( graph4.Towns[randVar].degree == 400 || edgeThere(new Edge(graph4.Towns[t], graph4.Towns[randVar], 0.0) , graph4)){

				}else{
					double tempDistance = 3960 * Math.acos(Math.sin(graph4.Towns[t].theTown.lat) * Math.sin(graph4.Towns[randVar].theTown.lat)
							+ (Math.cos(graph4.Towns[t].theTown.lat) * Math.cos(graph4.Towns[randVar].theTown.lat) * 
									Math.cos(graph4.Towns[t].theTown.lon - graph4.Towns[randVar].theTown.lat))
					);


					graph4.Edges[i] = new Edge(graph4.Towns[t], graph4.Towns[randVar], tempDistance);
					graph4.Edges[i+1] = new Edge(graph4.Towns[randVar], graph4.Towns[t], tempDistance);

					graph4.Towns[t].degree++;
					graph4.Towns[randVar].degree++;
					graph4.totalEdges++;
					i += 2;
				}

			}

		}
		
		System.out.print(proj2b.Dijkstra(graph4));
		System.out.print(proj2b.Prims(graph4));
		
//		Creates edges for Graph5
		graph5.Edges = new Edge[500*graph5.totalTowns*2];

		i = 0;
		for (int t = 0 ; t < graph5.totalTowns ; t++){

			while(graph5.Towns[t].degree <= 500){
				int randVar = generator.nextInt(graph5.totalTowns);


				if ( graph5.Towns[randVar].degree == 500 || edgeThere(new Edge(graph5.Towns[t], graph5.Towns[randVar], 0.0) , graph5)){


				}else{
					double tempDistance = 3960 * Math.acos(Math.sin(graph5.Towns[t].theTown.lat) * Math.sin(graph5.Towns[randVar].theTown.lat)
							+ (Math.cos(graph5.Towns[t].theTown.lat) * Math.cos(graph5.Towns[randVar].theTown.lat) * 
									Math.cos(graph5.Towns[t].theTown.lon - graph5.Towns[randVar].theTown.lat))
					);


					graph5.Edges[i] = new Edge(graph5.Towns[t], graph5.Towns[randVar], tempDistance);
					graph5.Edges[i+1] = new Edge(graph5.Towns[randVar], graph5.Towns[t], tempDistance);

					graph5.Towns[t].degree++;
					graph5.Towns[randVar].degree++;
					graph5.totalEdges++;
					i += 2;
				}

			}

		}

		System.out.print(proj2b.Dijkstra(graph5));
		System.out.print(proj2b.Prims(graph5));
		
//		Creates edges for Graph6
		graph6.Edges = new Edge[600*graph6.totalTowns*2];

		i = 0;
		for (int t = 0 ; t < graph6.totalTowns ; t++){

			while(graph6.Towns[t].degree <= 600){
				int randVar = generator.nextInt(graph6.totalTowns);


				if ( graph6.Towns[randVar].degree == 600 || edgeThere(new Edge(graph6.Towns[t], graph6.Towns[randVar], 0.0) , graph6)){

				}else{
					double tempDistance = 3960 * Math.acos(Math.sin(graph6.Towns[t].theTown.lat) * Math.sin(graph6.Towns[randVar].theTown.lat)
							+ (Math.cos(graph6.Towns[t].theTown.lat) * Math.cos(graph6.Towns[randVar].theTown.lat) * 
									Math.cos(graph6.Towns[t].theTown.lon - graph6.Towns[randVar].theTown.lat))
					);


					graph6.Edges[i] = new Edge(graph6.Towns[t], graph6.Towns[randVar], tempDistance);
					graph6.Edges[i+1] = new Edge(graph6.Towns[randVar], graph6.Towns[t], tempDistance);

					graph6.Towns[t].degree++;
					graph6.Towns[randVar].degree++;
					graph6.totalEdges++;
					i += 2;
				}

			}

		}

		System.out.print(proj2b.Dijkstra(graph6));
		System.out.print(proj2b.Prims(graph6));
		
//		Creates edges for Graph7
		graph7.Edges = new Edge[700*graph7.totalTowns*2];

		i = 0;
		for (int t = 0 ; t < graph7.totalTowns ; t++){

			while(graph7.Towns[t].degree <= 700){
				int randVar = generator.nextInt(graph7.totalTowns);


				if ( graph7.Towns[randVar].degree == 700 || edgeThere(new Edge(graph7.Towns[t], graph7.Towns[randVar], 0.0) , graph7)){


				}else{
					double tempDistance = 3960 * Math.acos(Math.sin(graph7.Towns[t].theTown.lat) * Math.sin(graph7.Towns[randVar].theTown.lat)
							+ (Math.cos(graph7.Towns[t].theTown.lat) * Math.cos(graph7.Towns[randVar].theTown.lat) * 
									Math.cos(graph7.Towns[t].theTown.lon - graph7.Towns[randVar].theTown.lat))
					);


					graph7.Edges[i] = new Edge(graph7.Towns[t], graph7.Towns[randVar], tempDistance);
					graph7.Edges[i+1] = new Edge(graph7.Towns[randVar], graph7.Towns[t], tempDistance);

					graph7.Towns[t].degree++;
					graph7.Towns[randVar].degree++;
					graph7.totalEdges++;
					i += 2;
				}

			}

		}

		
		System.out.print(proj2b.Dijkstra(graph7));
		System.out.print(proj2b.Prims(graph7));
		
//		Creates edges for Graph8
		graph8.Edges = new Edge[800*graph8.totalTowns*2];

		i = 0;
		for (int t = 0 ; t < graph8.totalTowns ; t++){

			while(graph8.Towns[t].degree <= 800){
				int randVar = generator.nextInt(graph8.totalTowns);


				if ( graph8.Towns[randVar].degree == 800 || edgeThere(new Edge(graph8.Towns[t], graph8.Towns[randVar], 0.0) , graph8)){


				}else{
					double tempDistance = 3960 * Math.acos(Math.sin(graph8.Towns[t].theTown.lat) * Math.sin(graph8.Towns[randVar].theTown.lat)
							+ (Math.cos(graph8.Towns[t].theTown.lat) * Math.cos(graph8.Towns[randVar].theTown.lat) * 
									Math.cos(graph8.Towns[t].theTown.lon - graph8.Towns[randVar].theTown.lat))
					);


					graph8.Edges[i] = new Edge(graph8.Towns[t], graph8.Towns[randVar], tempDistance);
					graph8.Edges[i+1] = new Edge(graph8.Towns[randVar], graph8.Towns[t], tempDistance);

					graph8.Towns[t].degree++;
					graph8.Towns[randVar].degree++;
					graph8.totalEdges++;
					i += 2;
				}

			}

		}

		System.out.print(proj2b.Dijkstra(graph8));
		System.out.print(proj2b.Prims(graph8));
		
//		Creates edges for Graph9
		graph9.Edges = new Edge[900*graph9.totalTowns*2];

		i = 0;
		for (int t = 0 ; t < graph9.totalTowns ; t++){

			while(graph9.Towns[t].degree <= 900){
				int randVar = generator.nextInt(graph9.totalTowns);


				if ( graph9.Towns[randVar].degree == 900 || edgeThere(new Edge(graph9.Towns[t], graph9.Towns[randVar], 0.0) , graph9)){


				}else{
					double tempDistance = 3960 * Math.acos(Math.sin(graph9.Towns[t].theTown.lat) * Math.sin(graph9.Towns[randVar].theTown.lat)
							+ (Math.cos(graph9.Towns[t].theTown.lat) * Math.cos(graph9.Towns[randVar].theTown.lat) * 
									Math.cos(graph9.Towns[t].theTown.lon - graph9.Towns[randVar].theTown.lat))
					);


					graph9.Edges[i] = new Edge(graph9.Towns[t], graph9.Towns[randVar], tempDistance);
					graph9.Edges[i+1] = new Edge(graph9.Towns[randVar], graph9.Towns[t], tempDistance);

					graph9.Towns[t].degree++;
					graph9.Towns[randVar].degree++;
					graph9.totalEdges++;
					i += 2;
				}

			}

		}

		System.out.print(proj2b.Dijkstra(graph9));
		System.out.print(proj2b.Prims(graph9));
		
//		Creates edges for Graph10
		graph10.Edges = new Edge[1000*graph10.totalTowns*2];

		i = 0;
		for (int t = 0 ; t < graph10.totalTowns ; t++){

			while(graph10.Towns[t].degree <= 1000){
				int randVar = generator.nextInt(graph10.totalTowns);


				if ( graph10.Towns[randVar].degree == 1000 || edgeThere(new Edge(graph10.Towns[t], graph10.Towns[randVar], 0.0) , graph10)){


				}else{
					double tempDistance = 3960 * Math.acos(Math.sin(graph10.Towns[t].theTown.lat) * Math.sin(graph10.Towns[randVar].theTown.lat)
							+ (Math.cos(graph10.Towns[t].theTown.lat) * Math.cos(graph10.Towns[randVar].theTown.lat) * 
									Math.cos(graph10.Towns[t].theTown.lon - graph10.Towns[randVar].theTown.lat))
					);


					graph10.Edges[i] = new Edge(graph10.Towns[t], graph10.Towns[randVar], tempDistance);
					graph10.Edges[i+1] = new Edge(graph10.Towns[randVar], graph10.Towns[t], tempDistance);

					graph10.Towns[t].degree++;
					graph10.Towns[randVar].degree++;
					graph10.totalEdges++;
					i += 2;
				}

			}

		}

		System.out.print(proj2b.Dijkstra(graph10));
		System.out.print(proj2b.Prims(graph10));
	}

}
