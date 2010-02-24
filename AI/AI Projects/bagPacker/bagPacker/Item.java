package bagPacker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/** The Item is the variable of the constraint packing problem
 *  Has a domain of Bags and a list of constraints as well as other information
 *  pertaining to its characteristics and current state. 
 * @author Eric Greer
 */
public class Item {
	String name;
	int weight;
	LinkedList<Bag> bags;
	LinkedList<BinaryConstraint> bcs;
	boolean inBag;
	Bag inBagBag;
	
	/** Constructor for an Item object
	 * 
	 * @param name 		the name of the item  
	 * @param weight	the weight of the item
	 */
	public Item(String name, int weight){
		this.name = name;
		this.weight = weight;
		this.bcs = new LinkedList<BinaryConstraint>();
		this.inBag = false;
	}
	
	/** Initializes the list of possible bags
	 * 
	 * @param bags	a LinkedList of all possible bags the item can be put into 
	 */
	void initializeBags(LinkedList<Bag> bags){
		this.bags = bags;
	}
	
	/** Adds a bag to the list of possible bags 
	 * 
	 * @param adder		The bag to add
	 * @return			Returns whether the bag was added
	 */
	public boolean addBag(Bag adder){
		if (this.bags.add(adder)){
			if (this.isInBag()){
				this.removeFromBag();
			}
			return true;
		}
		return false;
	}
	
	/** Removes a bag from the list of possible bags
	 * 
	 * @param remover	The bag to remove
	 * @return 			Returns whether the bag was removed
	 */
	public boolean removeBag(Bag remover){
		boolean returner = false;
		if(this.bags.contains(remover) && (this.bags.size() - 1) >= 0){
			returner = this.bags.remove(remover);
		}
		return returner;
	}

	/** Removes a list of bags from the list of possible bags if it is
	 *  not possible to remove all bags then none will be removed. 
	 * 
	 * @param remover	The bags to remove
	 * @return 			False = Not all bags were able to be removed.
	 */
	public boolean removeBags(Bag[] remover){
		boolean returner = true;
		for(int i = 0; i < remover.length; i++){
			returner = this.removeBag(remover[i]) && returner;
			if(!returner){
				while (i >= 0){
					this.addBag(remover[i]);
					i--;
				}
				break;
			}
		}	
		return returner;
		
	}
	
	/** Removes all bags on the list except the given bag
	 * 
	 * @param keeper	The bag to keep
	 * @return			The list of bags removed.
	 */
	public ArrayList<Bag> removeAllBagsExcept(Bag keeper){
		Bag[]temp = {keeper};
		return removeAllBagsExcept(temp);
	}
	
	/** Removes all bags on the list except the given bags
	 * 
	 * @param keeper	The bags to keep
	 * @return			The list of bags removed.
	 */
	public ArrayList<Bag> removeAllBagsExcept(Bag[] keeper){
		Iterator<Bag> bagi = bags.iterator();
		ArrayList<Bag> returner = new ArrayList<Bag>();
		while(bagi.hasNext()){
			Bag temp = bagi.next();
			boolean remove = true;
			for(int i = 0; i < keeper.length; i++){
				if(temp == keeper[i]){
					remove = false;
				}
			}
			if(remove){
				bagi.remove();
				returner.add(temp);
			}
		}
		return returner;
	}
		
	/** Adds a Binary Constraint to the list of constraints 
	 * 
	 * @param adder	The bag to add
	 * @return	Returns whether the bag was added
	 */
	public boolean addConstraint(BinaryConstraint adder){
		return this.bcs.add(adder);
	}


	/**
	 *	Sets the Items state of being in a bag to true 
	 *	@param name	the Bag to place Item in
	 */
	public boolean placeInBag(Bag container){
		if (container.addItemToBag(this)){
			this.inBag = true;
			this.inBagBag = container;
			return true;
		}
		else return false;
	}
	

	/** Removes the Items from given bag
	 * 
	 * @return		True: the Item was removed False: the Item could not be removed 
	 */
	public boolean removeFromBag(){
		if(this.inBagBag.removeItemFromBag(this)){
			this.inBag = false;
			this.inBagBag = null;
			return true;
		}
		else return false;
		
	}
	
	
	/**
	 * @return	Returns the item's name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * @return	Returns the item's weight
	 */
	public int getWeight(){
		return this.weight;
	}
	
	/**
	 * @return	Returns if Item is assigned to a bag
	 */
	public boolean isInBag(){
		return this.inBag;
	}
}
