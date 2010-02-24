package nov10;

/**
 * Represents a list of StudentNode objects.
 * 
 * @author heineman
 *
 */
public class StudentList {

	/** Need to store the head of the list. */
	StudentNode head;

	/**
	 * When constructing a list, it is initially empty, so declare that 
	 * head is null (meaning, there is no first node to be pointed to).
	 */
	public StudentList () {
		head = null;
	}
	
	// There are numerous operations that one could consider to perform on a list.
	// We will cover each of them in turn. For now, let us start simply with
	//    * prepend (Student s)    -- add a student to the front of the list
	//    * findById (String id)   -- find a student by id.
	
	/**
	 * Prepend a new Node to the beginning of the list.
	 * 
	 * @param   st    Student to be added.
	 */
	public void prepend (Student st) {
		StudentNode node = new StudentNode(st);   // create the new node to be added
		
		if (head == null) {
			head = node;             // then we are the first one in the list
		} else {
			node.next = head;        // have our new node point to the old list
			head = node;             // and now our new node becomes the first one.
		}
	}
	
	/**
	 * Locate a Student object by unique id.
	 * 
	 * @param id         target Student to locate.
	 * @return           the first Student object in the list with id, or null if not found.
	 */
	public Student findById (String id) {
		StudentNode node = head;   // initialize
		while (node != null) {     // boolean guard expression
			// See if ID matches student id in the list.
			Student st = node.getValue();
			if (st.getID().equals(id)) {
				return st;           
			}
			
			node = node.next;      // ADVANCE 
		}
		
		return null;               // nope: not found.		
	}
 
}
