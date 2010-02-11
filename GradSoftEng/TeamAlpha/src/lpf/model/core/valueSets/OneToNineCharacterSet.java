package lpf.model.core.valueSets;

import lpf.model.core.Value;

/**
 * 
 * A CharacterSet consisting of the Values 1 to 9
 * 
 * @author Nik Deapen
 *
 */
public class OneToNineCharacterSet extends CharacterSet {

	/**
	 * Creates a new OneToNineCharacterSet
	 */
	public OneToNineCharacterSet() {
		this.setValues(createValues());
	}

	/**
	 * Creates the values for the OnetoNineCharacterSet
	 * 
	 * @return values			the list of Values that contain '1' to '9'
	 */
	private Value[] createValues() {
		char[] vals = {'1','2','3','4','5','6','7','8','9'};
		Value[] values = new Value[vals.length];
		for (int i = 0; i < vals.length; i++){
			values[i] = new Value(vals[i]);
		}
		return values;
	}

}
