package egreer.lab3;

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
 * @author egreer
 */
public class FunctionList {

	/** Instance variable head; Stores first node on list */
	FunctionNode head;

	/** default constructor*/
	public FunctionList () {
		head = null;
	}
	
	/**
	 * Adds the function to be at the end of the list.
	 * 
	 * See example from nov13 (Inventory) for an example of how
	 */
	public void add (Function f) {
		
		if (head == null) {
			head = new FunctionNode(f);
			return;
		} 
		
		
		FunctionNode node = head;
		while (node.next != null) {
					
			node = node.next;   			
		}
		
		node.next = new FunctionNode(f);
				
	}
	
	/** 
	 * Computes the composed function (Fn o Fn-1 o ... o F3 o F2 o F1) (x).
	 * 
	 * @return  value of composed function.
	 */
	public int compute (int x) {
		int answer = x;
		
		while (head != null){
			answer = head.value.compute(answer);
		}
		
		return answer;
	}
}
