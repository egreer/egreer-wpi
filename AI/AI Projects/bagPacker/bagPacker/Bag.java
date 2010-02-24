package bagPacker;

import java.util.LinkedList;
/** A Bag is an object in a domain  of the constraint packing problem
 * It also stores what variables are assigned to it, 
 * as well as constraints on the amount of variables and the max capacity. 
 * 
 * @author Eric Greer
 */
public class Bag {
	String name;
	int maxWeight;
	int minItems;
	int maxItems;
	int currentWeight;
	LinkedList<Item> items;
	
	/** Constructor for a bag object
	 * 
	 * @param name	The name of the bag
	 * @param maxWeight	the max weight it can hold
	 */
	public Bag(String name, int maxWeight){
		this.name = name;
		this.maxWeight = maxWeight;
		this.currentWeight = 0;
		this.items = new LinkedList<Item>();
		this.minItems = 0;
		this.maxItems = Integer.MAX_VALUE;
	}
	
	/** Sets the minimum and maximum number of items
	 * 
	 * @param min	The minimum number of items
	 * @param max	The maximum number of items
	 */
	public void setMinMax(int min, int max){
		this.minItems = min;
		this.maxItems = max;
	}
	
	/**
	 * @return	Returns the bag's name
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * @return	Returns the bag's current weight
	 */
	public int getWeight(){
		return this.currentWeight;
	}
	
	/**
	 * @return	Returns the bag's current number of Items
	 */
	public int getCurrentItemCount(){
		return this.items.size();
	}
	
	
	/** Checks to see how much more weight the can hold 
	 * @return	The weight left the bag can hold 
	 */
	public int weightLeft(){
		return (this.maxWeight - this.currentWeight);
	}
	
	/** Checks to see if the Bag's number of items is between min and max,
	 *  and weight is less then or equal to the max
	 *  
	 * @return	True = Bag is full enough for the robber 
	 */
	public boolean isBagReady(){
		return ((this.currentWeight <= this.maxWeight) && (this.items.size() <= this.maxItems) && (this.items.size() >= this.minItems));		 
	}

	/** Adds Items to the Bag
	 * 
	 * @param adder The Item to add to the Bag
	 * @return True if Item was added, false if not able to;
	 */
	public boolean addItemToBag(Item adder){
		if(adder.getWeight() + this.currentWeight > this.maxWeight || this.items.size() + 1 > this.maxItems){
			return false;
		
		}else{
			if( this.items.add(adder)){
				this.currentWeight += adder.getWeight();
				return true;
			}
			return false;
		}
	}
	
	/** Removes Items from the Bag
	 * 
	 * @param remover the Item to remove from the Bag
	 * @return	True if Item was removed, false if not or not in Bag;
	 */
	public boolean removeItemFromBag(Item remover){
		if(this.items.remove(remover)){
			this.currentWeight -= remover.getWeight();
			return true;
		}
		else return false;
	}
	
	
	/** Check to see if a given item is in the bag
	 * 
	 * @return True = yes the Item exists
	 */
	public boolean isItemInBag(Item find){
		return this.items.contains(find);
	}
	
}
