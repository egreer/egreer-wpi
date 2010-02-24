package bagPacker;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.tree.DefaultMutableTreeNode;

/** CSPSolver is the main program for searching through the CSP for a solution
 * 
 * @author Eric Greer
 */
public class CSPSolver {
	
	/** Solves the Constraint Satisfaction problem recursively.
	 * 	
	 * @param treeNode	the node to start searching from
	 * @return			Returns true if a solution was found,
	 * 					Returns false if an error occurred, a constraint was violated or if no solution was found  
	 */
	public boolean Solve(DefaultMutableTreeNode treeNode){
		ArrayList<ArrayList<Object>> backup = new ArrayList<ArrayList<Object>>();
		Item currentItem = (Item) treeNode.getUserObject();
		Robber.log("Searching from Item: " + currentItem.getName());

		/* Aligns domains of all items based on size left in each bag */
		Iterator<Item> items = Robber.allItems.iterator();
		Robber.log("Checking Domain Constraint Problems");
		while(items.hasNext()){
			ArrayList<Object> backer = new ArrayList<Object>();
			Item nextItem = items.next();
			LinkedList<Bag> clonedlist = (LinkedList<Bag>)nextItem.bags.clone();
			Iterator<Bag> bags = clonedlist.iterator();
			
			while(!nextItem.isInBag() && bags.hasNext()){
				Bag nextBag = bags.next();
				if(nextBag.weightLeft() < nextItem.weight || (nextBag.maxItems < nextBag.getCurrentItemCount() + 1)){
					if(nextItem.removeBag(nextBag)){
						backer.add(nextItem);
						backer.add(nextBag);
						Robber.log("Item: " + nextItem.getName() + " Removed Bag: " + nextBag.getName() );
					}
				}
			}
			if(!backer.isEmpty()){
				backer.trimToSize();
				backup.add(backer);
			}
		}
		
		/* Aligns Domains on all types of binary constraints 
		 * The Beginning of the AC-3 algorithm. 
		 * Does not do the actual setting of Items so that it does not have to 
		 * iterate over the list more then once in an item 
		 */
		Iterator<BinaryConstraint> constraints = Robber.allBCs.iterator();
		Robber.log("Checking All Binary Constraints");
		while(constraints.hasNext()){
			ArrayList<Object> backer = alignDomains(constraints.next());
			backup.add(backer);
		}
		
		
		items = Robber.allItems.iterator();
		while(items.hasNext()){
			Item nextItem = items.next();		
			
			// Looks for Items that have a domain of 0, no Item can be left behind
			if(nextItem.bags.size() <= 0){
				backTrack(backup);
				return false;
			}
			
			// Processes Any Items that only have one bag.
			if(nextItem.bags.size() == 1 && !nextItem.isInBag()){
				if(!nextItem.placeInBag(nextItem.bags.element())){
					backTrack(backup);
					return false;
				}
			}	
		}

		if(areWeDone() == true) return true;
		
		/* Checks for children, and populates the tree if the problem is not solved*/
		if (treeNode.getChildCount() == 0 && !areWeDone()){
			LinkedList<DefaultMutableTreeNode> adder = new LinkedList<DefaultMutableTreeNode>();
			//Create nodes
			items = Robber.allItems.iterator();
			while(items.hasNext()){
				Item nextItem = items.next();
				if(!nextItem.isInBag() && !currentItem.equals(nextItem)){
					DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(nextItem);
					//Places child in correct order in the list of children
					if(adder.isEmpty()) adder.add(newChild);
					else{
						Iterator<DefaultMutableTreeNode> adderit = ((LinkedList<DefaultMutableTreeNode>)adder.clone()).listIterator();
						int t = 0;

						while(adderit.hasNext()){
							// Call MRV Hueristic
							if(MRV(newChild, adderit.next()) > 0){
								adder.add(t, newChild);
								break;
							}
							if(!adderit.hasNext())adder.addLast(newChild); //If the new node is the worst add it to tail
							t ++;
						}

					}
				}
			}

			// Populate tree
			Iterator<DefaultMutableTreeNode> adderit = adder.listIterator();
			while(adderit.hasNext()){
				treeNode.add(adderit.next());
			}
		}

		//Initial setting up for looping through possible bag combinations
		LinkedList<Bag> cloner = (LinkedList<Bag>)currentItem.bags.clone();
		Iterator<Bag> possibleBags = cloner.iterator(); //Try this.
		ArrayList<ArrayList<Object>> assignBackup = new ArrayList<ArrayList<Object>>();
		
		if(!currentItem.isInBag()){
			ArrayList<Object> backer = new ArrayList<Object>();
			backer.add(currentItem);
			if(!possibleBags.hasNext()){
				backTrack(backup);
				return false;
			}
			Bag pBag = possibleBags.next();
			backer.addAll(currentItem.removeAllBagsExcept(pBag));
			assignBackup.add(backer);
			currentItem.placeInBag(pBag);
			Robber.log("Checking Item: " + currentItem.getName() + " Assigned Bag: " + pBag.getName() );
		}
			

		/* check all children and one path returns true then an answer has been found */ 
		Enumeration<DefaultMutableTreeNode> e = (Enumeration<DefaultMutableTreeNode>) treeNode.children();
		
		while(e.hasMoreElements()){
			boolean check = Solve(e.nextElement());
			if (check == true) return check;
			Robber.log("Try different bag");
			backTrack(assignBackup);
			//Loops through possible bag combinations
			if (possibleBags.hasNext()){
				ArrayList<Object> backer = new ArrayList<Object>();
				Bag pBag = possibleBags.next();
				e = (Enumeration<DefaultMutableTreeNode>) treeNode.children();
				backer.addAll(currentItem.removeAllBagsExcept(pBag));
				assignBackup.add(backer);
				currentItem.placeInBag(pBag);
				Robber.log("Checking Item: " + currentItem.getName() + " Assigned Bag: " + pBag.getName() );
			}
		}
		if(areWeDone())return true;
		
		backTrack(backup);
		return false;
	}

	/** Compares the values(Items) of the input and returns an int for the one that is more
	 * constrained first using MRV Heuristic. Ties are broken with the Degree Heuristic,
	 * but if that fails then it is settled by defaulting to node2. 
	 * 
	 * @param node1	The first node to compare
	 * @param node2	The second node to compare
	 * @return	1: 	If node1 is more constrained
	 * 			0:	If there is an error with the comparison nodes (never returns in this version)
	 * 			-1: if node2 is more constrained 
	 */
	static int MRV(DefaultMutableTreeNode node1, DefaultMutableTreeNode node2){
		Item ione = (Item)node1.getUserObject();
		Item itwo = (Item)node2.getUserObject();
		
		if(ione.bags.size() < itwo.bags.size())	return 1;
		
		else if(ione.bags.size() > itwo.bags.size()) return -1;
		
		//Degree Heuristic
		else if(ione.bags.size() == itwo.bags.size()){
			if(ione.bcs.size() > itwo.bcs.size()) return 1;
			else if(ione.bcs.size() < itwo.bcs.size()) return -1;
			else return -1;
		}
		return 1;
	}
	/**
	 * backTracks restores previous state when an Illegal state is discovered, or a invalid solution set.
	 * @param backup	The List of lists of bags removed from items
	 */
	static void backTrack(ArrayList<ArrayList<Object>> backup){
		Robber.log("Back Tracking");
		backup.trimToSize();
		Iterator<ArrayList <Object>> backupiter =(Iterator<ArrayList <Object>>) backup.iterator();
		while(backupiter.hasNext()){
			
			//Iterates over each element for this backup
			ListIterator<Object> objectiter = (ListIterator<Object>) backupiter.next().listIterator();
			while(objectiter.hasNext()){
				Object restoreitem = objectiter.next();
				
				//If the first object is an Item then restore its bags
				if(restoreitem instanceof Item){
					while(objectiter.hasNext()){
						Object restorebag = objectiter.next();
						
						//If the object is a Bag then restore it to the Item
						if(restorebag instanceof Bag){
							((Item)restoreitem).addBag((Bag) restorebag); 
						}
						//If the object is a Item go back and assign it to restoreItem
						if(restorebag instanceof Item){
							objectiter.previous();
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * Good solution: All Items are in bags and All bags are between min, and max number of items, and less then their max weight 
	 * @return	Returns true if the above statement is true.
	 */
	public static boolean areWeDone(){
		boolean returner = true;
		Iterator<Item> itemlist = Robber.allItems.iterator();
		
		while(itemlist.hasNext() && returner){
			returner = returner && itemlist.next().isInBag();
		}	
		
		Iterator<Bag> bagslist = Robber.allBags.iterator();
		
		while(bagslist.hasNext() && returner){
			returner = returner && bagslist.next().isBagReady();
		}	
		
		return returner;
	}
	
	/** Maintains the domain on all Items that have binary constraints 
	 * Part of the AC-3 Algorithm for Maintaining the Arc Consistency,
	 * 
	 * @param constraint	The constraint to check the domain of
	 * @return		Returns an ArrayList of Items, and the Bags removed for backTracking 
	 */
	public static ArrayList<Object> alignDomains(BinaryConstraint constraint){
		ArrayList<Object> returner = new ArrayList<Object>();
		LinkedList<Bag> clonedlist1 = (LinkedList<Bag>) constraint.item1.bags.clone();
		LinkedList<Bag> clonedlist2 = (LinkedList<Bag>) constraint.item2.bags.clone();
		Iterator<Bag> list1bags = clonedlist1.iterator();
		Iterator<Bag> list2bags = clonedlist2.iterator();
		
		/* Process possible domains on binary equals 
		 * By comparing one item's domain to another's
		 */
		if(constraint.type == 1){
			
			//If bags are all ready assigned then limit the domain on the other
			if(constraint.item2.isInBag() && constraint.item1.bags.contains(constraint.item2.inBagBag)){
				returner.add(constraint.item1);
				returner.addAll(constraint.item1.removeAllBagsExcept(constraint.item2.inBagBag));
			}
			if(constraint.item1.isInBag() && constraint.item2.bags.contains(constraint.item1.inBagBag)){
				returner.add(constraint.item2);
				returner.addAll(constraint.item2.removeAllBagsExcept(constraint.item1.inBagBag));
			}
			
			returner.add(constraint.item1);
			while(list1bags.hasNext()){
				Bag temp = list1bags.next();
				if (!constraint.item2.bags.contains(temp)){
					if(constraint.item1.removeBag(temp)){
						returner.add(temp);
					}
				}
			}
			
			returner.add(constraint.item2);
			while(list2bags.hasNext()){
				Bag temp = list2bags.next();
				if (!constraint.item1.bags.contains(temp)){
					if(constraint.item2.removeBag(temp)){
						returner.add(temp);
					}
				}
			}
			returner.trimToSize();
		 return returner;
		}
		
		/* Processes possible domains on binary not equals
		 *  Compares for any assigned items and eliminates it on the counterpart
		 */
		else if(constraint.type == 2){
			returner.add(constraint.item1);
			while(list1bags.hasNext()){
				Bag temp = list1bags.next();
				if (constraint.item2.isInBag() && constraint.item2.bags.size() == 1  && constraint.item2.bags.contains(temp)){
					constraint.item1.bags.remove(temp);
					returner.add(temp);
				}
			}
			
			returner.add(constraint.item2);
			while(list2bags.hasNext()){
				Bag temp = list2bags.next();
				if (constraint.item1.isInBag() && constraint.item1.bags.size() == 1  && constraint.item1.bags.contains(temp)){
					constraint.item2.removeBag(temp);
					returner.add(temp);
				}
			}
			returner.trimToSize();
		 return returner;
		}
		
		/* Processes possible domains on binary mutual inclusion
		 * Compares for any assigned items to a bag and assigns opposite of it on the counterpart
		 * Or if one is assigned to another bag, then eliminate those two bags on the other.
		 */
		else if(constraint.type == 3){
			Bag[] consBags = {constraint.bag1, constraint.bag2};
			
			//If item2 in in Bag1 then set domain of item1 = bag2 
			if (constraint.item2.isInBag() && constraint.item2.bags.contains(constraint.bag1)){
				returner.add(constraint.item1);
				returner.addAll(constraint.item1.removeAllBagsExcept(constraint.bag2));
			}
			
			//If item2 in in Bag2 then set domain of item1 = bag1 
			else if (constraint.item2.isInBag() && constraint.item2.bags.contains(constraint.bag2)){
				returner.add(constraint.item1);
				returner.addAll(constraint.item1.removeAllBagsExcept(constraint.bag1));
			}
			
			//If item1 in in Bag1 then set domain of item2 = bag2 
			else if (constraint.item1.isInBag() && constraint.item1.bags.contains(constraint.bag1)){
				returner.add(constraint.item2);
				returner.addAll(constraint.item2.removeAllBagsExcept(constraint.bag2));
			}
			
			//If item1 in in Bag2 then set domain of item2 = bag1 
			else if (constraint.item1.isInBag() && constraint.item1.bags.contains(constraint.bag2)){
				returner.add(constraint.item2);
				returner.addAll(constraint.item2.removeAllBagsExcept(constraint.bag1));
			}
			
			//If Item1 is assigned to a bag that is not bag1, or bag2 then remove those bags from item2's domain
			else if (constraint.item1.isInBag() && constraint.item1.bags.size() == 1  && !constraint.item1.bags.contains(consBags)){
				if(constraint.item2.removeBags(consBags)){
					returner.add(constraint.item2);
					returner.add(consBags[0]);
					returner.add(consBags[1]);
				}
			}

			//If Item2 is assigned to a bag that is not bag1, or bag2 then remove those bags from item1's domain
			else if (constraint.item2.isInBag() && constraint.item2.bags.size() == 1  && !constraint.item2.bags.contains(consBags)){
				if(constraint.item1.removeBags(consBags)){
					returner.add(constraint.item1);
					returner.add(consBags[0]);
					returner.add(consBags[1]);
				}
			}
			
			returner.trimToSize();
			return returner;
			}
		
		return null; //Should never get here. Was passed an invalid constraint 
	}

}
