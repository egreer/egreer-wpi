package proj1;

import java.io.*;

public class proj1 {
	
	/** Displays the towns that have the shortest, and longest names.
	 *  Displays the northernmost, and southernmost town.
	 *  Also displays the average distance to the the northernmost, and southernmost towns.
	 * 
	 * 
	 * @author Eric Greer
	 * @email egreer@wpi.edu 
	 *  
	 * @param args						Accepts a file of the lists of Town
	 * @throws FileNotFoundException	Throws if file doeesn't exist
	 * @throws IOException				Throws if line input is incorect
	 */
	public static void main(String args[])throws FileNotFoundException, IOException{
				
		// Variables
		/**Creates the inputFile*/
		File inputFile = new File(args[0]); 
				
		/** Longest Named Town*/
		String longestName = "", longestNameState = "";
		int longestNameID = 0, longestNameLength = -1;
				
		/** Shortest Named Town*/
		String shortestName = "", shortestNameState = "";
		int shortestNameID = 0, shortestNameLength = 50;
		
		/** Northern Most Town*/
		String northernTownName = "", northernTownState = "";
		double northernTownLat = 0, northernTownLon = 0;
		
		/** Southern Most Town*/ 
		String southernTownName = "", southernTownState = "";
		double southernTownLat = 90000, southernTownLon = 0;
		
		//Constants
		final int earthsRadius = 3900;
				
		//Scanner
		BufferedReader fileInput = new BufferedReader(new FileReader(/**"C:\\projdata0"*/inputFile));
		
		
		
		//Part A
		String line = fileInput.readLine();
		while (line != null){
			
			/** Splits the string into an array */
			int begin = 0;
			String[] ar = new String[5];
			
			for (int i=0 ; i<5 ; i++){
				int end = line.indexOf("\t" , begin);
				
				ar[i] = line.substring(begin , end);
				
				begin = end + 1;
				
			}
			
			/**Removes the information from the arrays, and places them in variables*/
			int tempID = Integer.parseInt(ar[0]);
			String tempName = ar[1];
			String tempState = ar[2];
			int tempNameLength = tempName.length();
			double tempLat = Double.valueOf(ar[3]).doubleValue();
			double tempLon = Double.valueOf(ar[4]).doubleValue();
						
			/** Checks to see if the name is as long as the current longest */ 
			if (longestNameLength == tempNameLength){
				if (tempID > longestNameID){
					longestNameLength = tempNameLength;
					longestNameID = tempID;
					longestName = tempName;
					longestNameState = tempState;
				}
			
			/** Checks to see if the name is larger then the largest */
			}else if (longestNameLength < tempNameLength){
				longestNameLength = tempNameLength;
				longestNameID = tempID;
				longestName = tempName;
				longestNameState = tempState;
				
			/** Checks to see if the name is as short as the shortest*/	
			}if (shortestNameLength == tempNameLength){
				if (tempID < shortestNameID){
					shortestNameLength = tempNameLength;
					shortestNameID = tempID;
					shortestName = tempName;
					shortestNameState = tempState;
				}
						
			/** Checks to see if the name is smaller then the smallest */
			}else if (shortestNameLength > tempNameLength){
				shortestNameLength = tempNameLength;
				shortestNameID = tempID;
				shortestName = tempName;
				shortestNameState = tempState;
													
			// Northernmost and Southernmost most cities
				
			/** Checks to see if the city is more north then the current town*/
			}if (tempLat > northernTownLat){
				northernTownName = tempName;
				northernTownState = tempState;
				northernTownLon = tempLon;
				northernTownLat = tempLat;
		
			/** Checks to see if the city is more south then the current town*/
			}if (southernTownLat > tempLat){
				southernTownName = tempName;
				southernTownState = tempState;
				southernTownLon = tempLon;
				southernTownLat = tempLat;
			}
			
			line = fileInput.readLine();
		}
				
		//Output the largest and smallest named cities
		System.out.println(longestName + ", " + longestNameState + " has the longest name");
		System.out.println(shortestName + ", " + shortestNameState + " has the shortest name");
		
		//Output the northern most, and southern most cities
		System.out.println(northernTownName + ", " + northernTownState + " is the northernmost town");
		System.out.println(southernTownName + ", " + southernTownState + " is the southernmost town");
		
		
		
		//Part B
		
		double northernTotal = 0, southernTotal = 0;
		int totalTowns = -1;
		
		BufferedReader fileInput2 = new BufferedReader(new FileReader(/**"C:\\projdata0"*/inputFile));
		String line2 = fileInput2.readLine();
		while (line2 != null){
			
			/** Splits the string into an array */
			int begin = 0;
			String[] ar = new String[5];
			
			for (int i=0 ; i<5 ; i++){
				int end = line2.indexOf("\t" , begin);
				
				ar[i] = line2.substring(begin , end);
				
				begin = end + 1;
				
			}
			
			/**Removes the information from the arrays, and places them in variables*/
			double tempLat = Double.valueOf(ar[3]).doubleValue();;
			double tempLon = Double.valueOf(ar[4]).doubleValue();;
			
			
			//Distance to Northern Town
			double distanceToNorth = Math.acos(
					Math.cos(northernTownLat) * Math.cos(tempLat) * Math.cos(northernTownLon) * Math.cos(tempLon) 
							+ Math.cos(northernTownLat) * Math.sin(tempLat) * Math.cos(northernTownLon) * Math.sin(tempLon)
							+ Math.sin(northernTownLat) * Math.sin(northernTownLon))
							/360 * 2 * Math.PI * earthsRadius;
			
			//Distance to Southern Town
			double distanceToSouth = Math.acos(
					Math.cos(southernTownLat) * Math.cos(tempLat) * Math.cos(southernTownLon) * Math.cos(tempLon) 
							+ Math.cos(southernTownLat) * Math.sin(tempLat) * Math.cos(southernTownLon) * Math.sin(tempLon)
							+ Math.sin(southernTownLat) * Math.sin(southernTownLon))/360 * 2 * Math.PI * earthsRadius;
		
			
			//Advancing the Loop
			northernTotal += distanceToNorth;
			southernTotal += distanceToSouth;
			totalTowns++;
			line2 = fileInput2.readLine();
		
		}
		fileInput2.close();
		
		//Compute the Means
		double northernMean = northernTotal / totalTowns;
		double southernMean = southernTotal / totalTowns;
		
		
		//Outputs the Mean Distance to Northernmost, and Southernmost Towns  
		System.out.println("Mean distance to "+ northernTownName + ", " + northernTownState + " is " + northernMean + " miles.");
		System.out.println("Mean distance to "+ southernTownName + ", " + southernTownState + " is " + southernMean + " miles.");	
	
		}
}
	
