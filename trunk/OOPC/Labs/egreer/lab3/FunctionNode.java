package egreer.lab3;

/**
 * Represents a Node in a list of Functions that are to be applied to a given domain x.
 * 
 * @author egreer
 */
public class FunctionNode {
	
	/** The function information */
	Function value;
	
	/** The next function */
	FunctionNode next;
	
	/**
	 * Constructs a FunctionNode that references the Function 
	 * 
	 * @param f		Function the node is created for
	 */
	public FunctionNode (Function f){
		this.value = f;
		this.next = null;
	}
}
