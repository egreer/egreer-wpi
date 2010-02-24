package ebc.model;

import java.util.ArrayList;

/**
 * Represents a piece of integer information.
 * 
 * Value can only be incremented and decremented from its initial value.
 * 
 * In this design we arrange so that the model element announces to its listeners
 * whenever it changes. An alternate arrangement would be for the controller to take
 * on this responsibility as well.
 * 
 * @author George Heineman
 */
public class Value implements IValue {

	/** Viewers. */
	ArrayList<IModelUpdated> listeners = new ArrayList<IModelUpdated>();
	
	/** smallest value allowed. */
	int min;
	
	/** largest value allowed. */
	int max;
	
	/** current value. */
	int value;
	
	/** 
	 * Construct entity capable of retaining value in [min,max] range.
	 * 
	 * Initial value is given.
	 * @param min    min value of the allowed range
	 * @param max    max value of the allowed range
	 * @param v      current value
	 */
	public Value (int min, int max, int v) {
		this.min = min;
		this.max = max;
		this.value = v;
	}
	
	/** 
	 * Construct entity capable of retaining value in [min,max] range.
	 * 
	 * Initial value is minimum in the range.
	 * 
	 * @param min    min value of the allowed range
	 * @param max    max value of the allowed range
	 */
	public Value (int min, int max) {
		this (min, max, min);
	}
	
	/** Reduce value up until minimum. */
	public void decrement () throws RuntimeException {
		if (value == min) { throw new RuntimeException ("Value cannot be decreased!"); }
		
		value--;
		updateViewers();
	}
	
	/** Increase value up until minimum. */
	public void increment () throws RuntimeException {
		if (value == max) { throw new RuntimeException ("Value cannot be increased!"); }
		
		value++;
		updateViewers();
	}
	
	/** Return current value. */
	public int getValue() { return value; }
	
	/** Return minimum allowed value. */
	public int getMinimum () { return min; }
	
	/** Return maximum allowed value. */
	public int getMaximum () { return max; }
	
	/** Add a listener to changes from this model element. */
	public void addListener (IModelUpdated ivu) {
		listeners.add(ivu);
	}
	
	/** Remove a listener to changes from this model element. */
	public void removeListener (IModelUpdated ivu) {
		listeners.remove(ivu);
	}
	
	/**
	 * Notify viewers.
	 * 
	 * Note that the value must be internally changed before we send this announcement
	 * out to the listeners.
	 */ 
	private void updateViewers() {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).modelChanged();
		}
	}
}







