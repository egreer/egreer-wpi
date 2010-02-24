package nov13;


public class SectionList {

	/** Head of the list.*/
	SectionNode  head;
	
	/**
	 * Default constructor.
	 * 
	 * @param c
	 */
	public SectionList () {
		head = null;
	}
	
	/** Output the List to the screen. */
	public void output () {
		// visit every element in the list
		SectionNode   node = head;
		while (node != null) {
			Section sec = node.value;
			sec.output();
			
			node = node.next;  // ADVANCE
		}
	}
	
	/**
	 * Prepend a new Node to the beginning of the list.
	 * 
	 * @param   s    Section to be added.
	 */
	public void prepend (Section s) {
		SectionNode node = new SectionNode(s);   // create the new node to be added
		
		if (head == null) {
			head = node;             // then we are the first one in the list
		} else {
			node.next = head;        // have our new node point to the old list
			head = node;             // and now our new node becomes the first one.
		}
	}

	/**
	 * Return the section with the given section number.
	 * @param secNum
	 * @return
	 */
	public Section findSection(String secNum) {
		
		SectionNode node = head;   // initialize
		while (node != null) {     // boolean guard expression
			
			// See if matches student id in the list.
			Section s = node.value;
			if (secNum.equals(s.number)) {
				return s;           
			}
			
			node = node.next;      // ADVANCE 
		}
		
		return null;               // nope: not found.		

	}
}
