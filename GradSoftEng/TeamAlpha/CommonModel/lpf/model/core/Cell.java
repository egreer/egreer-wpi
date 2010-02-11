package lpf.model.core;

import java.util.ArrayList;
import java.util.Collection;


/**
 * 
 * The Cell class for a grid game that can contain
 * a Value and a number of marks for use in a logic
 * puzzle.
 * 
 * @author Andrew Yee
 *
 */
public class Cell {
	
	ArrayList<Value> marks;
	Value digit;
	
	public final Location loc;

	/**
	 * Creates a new Cell object with the given location
	 * 
	 * @param loc	The location of the Cell
	 */
	public Cell(Location loc) {
		marks = new ArrayList<Value>();
		digit = null;
		this.loc = loc;
	}
	
	/**
	 * Gets the digit contained in the Cell
	 * 
	 * @return digit
	 */
	public Value getDigit() {
		return digit;
	}
	
	/**
	 * Gets the list of marks contained in the Cell
	 * 
	 * @return marks
	 */
	public Collection<Value> getMarks() {
		return marks;
	}
	
	/**
	 * Sets the digit to the specified Value.
	 * 
	 * @param v		The new Value for the digit
	 */
	public void setDigit(Value v) {
		digit = v;
	}
	
	/**
	 * Clears all marks contained by the Cell
	 * 
	 */
	public void clearMarks() {
		marks.clear();
	}
	
	/**
	 * Clears the specified mark from the Cell
	 * 	  
	 * @param v		The mark to be cleared
	 */
	public void clearMark(Value v) {
		marks.remove(v);
	}
	
	/**
	 * Adds the specified mark to the Cell
	 * 
	 * @param v		The mark to be added to the Cell
	 */
	public void addMark(Value v) {
		if (!this.marks.contains(v))
			marks.add(v);
	}
	
	/**
	 * Compares this Cell's digit with the specified
	 * Object's digit if the Object if the Object is a
	 * Cell.
	 * 
	 * @param o				Object to be compared
	 * @return	true		if specified Object is a Cell and
	 * 						specified Cell's digit matches this
	 * 						Cell's digit
	 * 			false		if specified Object is not a Cell or
	 * 						specified Object is a Cell that has
	 * 						a digit that does not match this
	 * 						Cell's digit
	 * 
	 */
	public boolean equals(Object o) {
		if (o instanceof Cell){
			if (digit == null || ((Cell) o).digit == null) {
				if(digit == null && ((Cell) o).digit == null) return true;
				return false;
			}
			if (digit.equals(((Cell) o).digit)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
			
	}
	
	/**
	 * Returns if there is a digit in the Cell 
	 * 
	 * @return	true		if digit is not null
	 * 			false		if digit is null
	 */
	public boolean isOccupied() {
		return digit != null;
	}
	
	/**
	 * Clears the digit from the cell
	 * 
	 */
	public void clearDigit() {
		digit = null;
	}
	
	
	public String toString(){
		return this.loc.toString();
	}
}
