package nov13;

public class CourseNode {
	/** Course being maintained. */
	Course value;
	
	/** next in the list. */
	CourseNode next;
	
	public CourseNode (Course c) {
		value = c;
		next = null;
	}
}
