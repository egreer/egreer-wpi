package lab3;

/**
 * Represents a Node in a list of Functions that are to be applied to a given domain x.
 * 
 * @author heineman
 */
public class FunctionNode {

	/* Function being captured. */
	Function   value;
	
	/* next in the list. */
	FunctionNode next;
	
	/** Constructor for FunctionNode. */
	public FunctionNode (Function f) {
		value = f;
		next = null;
	}
}
