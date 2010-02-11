package lpf.model.core.valueSets;

import lpf.model.core.Value;

/**
 * 
 * Abstract class for a valid character set for a logic puzzle
 * 
 * @author Nik Deapen
 *
 */
public abstract class CharacterSet {

	private Value[] vals;
	
	/**
	 * Gets the Values contained in this CharacterSet
	 * 
	 * @return vals				the array of accepted Values
	 */
	public Value[] getValues(){
		return vals;
	}
	
	/**
	 * Sets the Values contained in this CharacterSet
	 * 
	 * @param vals				the array of Values that should be accepted
	 */
	protected void setValues(Value[] vals){
		this.vals = vals;
	}
	
	/**
	 * Get the number of values in this CharacterSet
	 * 
	 * @return vals.length		the number of Values accepted
	 */
	public int getNumValues(){
		return vals.length;
	}
	
	/**
	 * Check whether the given Value is contained in this CharacterSet
	 * 
	 * @param 	v				the Value being checked
	 * @return	true			if v is in this CharacterSet
	 * 			false			if v is not in this CharacterSet
	 */
	public boolean contains(Value v){
		for (int i=0; i < vals.length; i++){
			if (v.equals(vals[i]))
				return true;;
		}
		return false;
	}
}
