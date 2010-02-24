package nov17;

public class MoveNode {

	/** move to make. */
	Move  value;
	
	/** next one. */
	MoveNode next;
	
	public MoveNode (Move m) {
		this.value = m;
		next = null;
	}
}
