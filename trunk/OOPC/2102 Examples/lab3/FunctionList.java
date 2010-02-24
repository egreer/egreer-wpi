package lab3;

/**
 * Represents a compositional function.
 * 
 * Maintains a linked list of Function objects F1, F2, F3, ... , Fn  where the first one
 * in the list is F1, and the last one in the list is Fn.
 * 
 * The result of the compute function must be:
 * 
 *    (Fn o Fn-1 o ... o F3 o F2 o F1) (x).
 *
 * @author USERNAME
 */
public class FunctionList {

	/** List needs to know the first element in the list. */
	FunctionNode head;
	
	/** Default constructor. */
	public FunctionList () {
		head = null;
	}
	
	/**
	 * Adds the function to be at the end of the list.
	 * 
	 * See example from nov13 (Inventory) for an example of how
	 */
	public void add (Function f) {
		// 1. Is the list empty? This is the case where head == null
		// 2. The list is not empty and so I need to find the last one in the list
		//     which is the FunctionNode whose 'next' reference is null.
		
		if (head == null) {
			head = new FunctionNode (f);
		} else {
			
			FunctionNode node = head;
			while (node.next != null) {
				// processing? None needed
				
				node = node.next;    // advance
			}
			
			node.next = new FunctionNode (f);			
		}
	}
	
	/** 
	 * Computes the composed function (Fn o Fn-1 o ... o F3 o F2 o F1) (x).
	 * 
	 * @return  value of composed function.
	 */
	public int compute (int x) {
		FunctionNode node = head;   // INIT
		while (node != null) {
			// process: Apply some function to x.   y = F(x).
			int y = node.value.compute(x);
			
			node = node.next;   // ADVANCE
			x = y;              // prepare x for the next time, evaluating next function
		}
		
		// return the final value computed.
		return x;
	}
}
