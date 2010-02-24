package nov13;


public class CourseList {

	/** Head of the list.*/
	CourseNode  head;
	
	/**
	 * Default constructor.
	 * 
	 * @param c
	 */
	public CourseList () {
		head = null;
	}
	
	/** Output the List to the screen. */
	public void output () {
		// visit every element in the list, and output each one.
		CourseNode   node = head;
		while (node != null) {
			Course c = node.value;
			c.output();
			
			node = node.next;  // ADVANCE
		}
	}
	
	/**
	 * Locate a Course object
	 * 
	 * @return           the first Coursethat matches
	 */
	public Course findCourse (String dept, String num, String term) {
		CourseNode node = head;   // initialize
		while (node != null) {     // boolean guard expression
			
			// See if matches student id in the list.
			Course c = node.value;
			if (dept.equals(c.getDepartment()) && num.equals(c.getNumber()) && term.equals (c.getTerm())) {
				return c;           
			}
			
			node = node.next;      // ADVANCE 
		}
		
		return null;               // nope: not found.		
	}
	
	/**
	 * Prepend a new Node to the beginning of the list.
	 * 
	 * @param   c    Course to be added.
	 */
	public void prepend (Course c) {
		CourseNode node = new CourseNode(c);   // create the new node to be added
		
		if (head == null) {
			head = node;             // then we are the first one in the list
		} else {
			node.next = head;        // have our new node point to the old list
			head = node;             // and now our new node becomes the first one.
		}
	}

}
