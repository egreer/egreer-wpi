package nov13;

public class SectionNode {
	/** Section being maintained. */
	Section value;
	
	/** next in the list. */
	SectionNode next;
	
	public SectionNode (Section s) {
		value = s;
		next = null;
	}
}
