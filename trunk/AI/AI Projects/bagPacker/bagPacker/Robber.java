package bagPacker;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.tree.DefaultMutableTreeNode;

/** Robber is the primary class for parsing input and output.
 * 
 * @author Eric Greer
 */
//NOTE ANYWHERE FINDX(X) IS USED NEEDS NULL CHECKING, NOW ASSUMING ALL VALID INPUT
public class Robber {
	static String delimiter = "#####"; 
	static LinkedList<Item> allItems = new LinkedList<Item>();
	static LinkedList<Bag> allBags  = new LinkedList<Bag>();
	static LinkedList<BinaryConstraint> allBCs  = new LinkedList<BinaryConstraint>();
	static DefaultMutableTreeNode tree;
	static FileOutputStream outlog;
	static PrintStream fileprint; 
	
	/**
	 * @param args 	First parameter must be a file location and name 
	 * @throws IOException	If there is an error reading or opening the file 
	 */
	public static void main(String[] args) throws IOException{
		//Sets up output file
		outlog = new FileOutputStream("CSP.log");
		fileprint = new PrintStream(outlog);

		
		BufferedReader inputFile = new BufferedReader(new FileReader(args[0])); 
		String buffer = inputFile.readLine();
		
		//Checks for the start of the first section
		while (!buffer.contains(delimiter)){
			buffer = inputFile.readLine();
		}
		
		buffer = inputFile.readLine(); //advance to first item
		
		//Sets the Items
		while(!buffer.contains(delimiter)){
			String[] itemValues = buffer.split(" ");
			allItems.add(new Item(itemValues[0], Integer.valueOf(itemValues[1])));
			buffer = inputFile.readLine(); //advance to next item
		}
		buffer = inputFile.readLine(); //advance to next item
		
		//Sets the Bags
		while(!buffer.contains(delimiter)){
			String[] bagValues = buffer.split(" ");
			allBags.add(new Bag(bagValues[0], Integer.valueOf(bagValues[1])));
			buffer = inputFile.readLine(); //advance to next item
		}
		buffer = inputFile.readLine(); //advance to next item
		
		//Sets the fits Limit
		while(!buffer.contains(delimiter)){
			String[] bagValues = buffer.split(" ");
			setLimits(Integer.valueOf(bagValues[0]), Integer.valueOf(bagValues[1]));
			buffer = inputFile.readLine(); //advance to next item
		}
		buffer = inputFile.readLine(); //advance to next item
		
		//sets the items on the bags
		setItemsBags();
		
		//Processes unary inclusion
		while(!buffer.contains(delimiter)){
			String[] constraintValues = buffer.split(" ");
			Item t = findItem(constraintValues[0]);
			Bag[] keeper = new Bag[constraintValues.length - 1];
			for(int i = 1; i < constraintValues.length ; i++){
				keeper[i-1] = findBag(constraintValues[i]);
			}
			t.removeAllBagsExcept(keeper);
			buffer = inputFile.readLine(); //advance to next item
		}
		buffer = inputFile.readLine(); //advance to next item
		
		//Processes unary exclusion
		while(!buffer.contains(delimiter)){
			String[] constraintValues = buffer.split(" ");
			Item t = findItem(constraintValues[0]);
			Bag[] remover = new Bag[constraintValues.length - 1];
			for(int i = 1; i < constraintValues.length ; i++){
				remover[i-1] = findBag(constraintValues[i]);
			}
			t.removeBags(remover);
			buffer = inputFile.readLine(); //advance to next item
		}
		buffer = inputFile.readLine(); //advance to next item
		
		//Processes binary constraints equals
		while(!buffer.contains(delimiter)){
			String[] constraintValues = buffer.split(" ");
			Item one = findItem(constraintValues[0]);
			Item two = findItem(constraintValues[1]);
			BinaryConstraint temp = new BinaryConstraint(one, two, 1);
			one.addConstraint(temp);
			two.addConstraint(temp);
			allBCs.add(temp);
			buffer = inputFile.readLine(); //advance to next item
		}
		buffer = inputFile.readLine(); //advance to next item
		
		//Processes binary constraints not equals
		while(!buffer.contains(delimiter)){
			String[] constraintValues = buffer.split(" ");
			Item one = findItem(constraintValues[0]);
			Item two = findItem(constraintValues[1]);
			BinaryConstraint temp = new BinaryConstraint(one, two, 2);
			one.addConstraint(temp);
			two.addConstraint(temp);
			allBCs.add(temp);
			buffer = inputFile.readLine(); //advance to next item
		}
		buffer = inputFile.readLine(); //advance to next item
		
		//Processes binary constraints mutual inclusion
		while(buffer != null && !buffer.contains(delimiter)){
			String[] constraintValues = buffer.split(" ");
			Item ione = findItem(constraintValues[0]);
			Item itwo = findItem(constraintValues[1]);
			Bag bone = findBag(constraintValues[2]);
			Bag btwo = findBag(constraintValues[3]);
			BinaryConstraint temp = new BinaryConstraint(ione, itwo, bone, btwo, 3);
			ione.addConstraint(temp);
			itwo.addConstraint(temp);
			allBCs.add(temp);
			
			buffer = inputFile.readLine(); //advance to next item
		}
		if(!allItems.isEmpty() && !allBags.isEmpty()){
			tree = new DefaultMutableTreeNode(allItems.element());
			CSPSolver solver = new CSPSolver();
			if(solver.Solve(tree)) output();
			else System.out.println("No such assignment is possible");
		}else{
			System.out.println("Invalid Input Data");
		}
		fileprint.close();
	}
	
	/** Finds the Item object in the list of all bags
	 * 
	 * @param name	The string name of the Item
	 * @return	Returns the Item, or null if no Item was found.
	 */
	public static Item findItem(String name){
		Item returner = null;
		Iterator<Item> items = allItems.iterator();
		Item temp;
		while(items.hasNext()){
			temp = (Item) items.next();
			if (temp.getName().equals(name)){
				returner = temp;
				break;
			}
		}
		return returner;
	}
	
	/** Finds the bag object in the list of all bags
	 * 
	 * @param name	The string name of the bag
	 * @return	Returns the Bag, or null if no Bag was found.
	 */
	public static Bag findBag(String name){
		Bag returner = null;
		Iterator<Bag> bags = allBags.iterator();
		Bag temp;
		while(bags.hasNext()){
			temp = bags.next();
			if (temp.getName().equals(name)){
				returner = temp;
				break;
			}
		}
		return returner;  
	}

	/**Sets the Item limits on all bags to the max and min 
	 * 
	 * @param min	the minimum number of items in all bags
	 * @param max	the maximum number of items in all bags 
	 */
	public static void setLimits(int min, int max){
		Iterator<Bag> bags = allBags.iterator();
		while(bags.hasNext()){
			bags.next().setMinMax(min, max);
		}
	}
	
	/**
	 * Sets all the Items to have all the possible bags
	 */
	public static void setItemsBags(){
		Iterator<Item> items = allItems.iterator();
		while(items.hasNext()){
			items.next().initializeBags((LinkedList<Bag>)allBags.clone());
		}
	}
	
	/**
	 * Outputs the information if the problem is solved
	 */
	public static void output(){
		Iterator<Bag> baglist = allBags.iterator();
		int totalWasted = 0;
		while(baglist.hasNext()){
			Bag temp = baglist.next();
			totalWasted += temp.weightLeft();
			System.out.print("Bag: " + temp.getName() + " Total Items: " + temp.getCurrentItemCount());
			System.out.println(" Total Weight: " + temp.getWeight());
			
			Iterator<Item> itemlist = temp.items.iterator();
			int i = 1;
			while(itemlist.hasNext()){
				Item tempItem = itemlist.next();
				System.out.print("Item " + i + ": " + tempItem.name + "\n");
				i++;
			}
			System.out.println();
		}
		System.out.println("Total Wasted Capacity " + totalWasted);
	}
	
	/** Method for logging process to the file.
	 * 
	 * @param output	The string to output
	 */
	public static void log(String output){
		fileprint.println(output);
	}
}

