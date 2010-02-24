package nov13;

/**
 * Represents a node in a linked list of Student Information.
 * 
 * This class seems to do very little. It is essentially controlled
 * by the StudentList class. And since it is not needed outside of the
 * way we implement our list, we mark this class as 'package-protected'. Note that
 * it is not declared as being public.
 * 
 * @author heineman
 */
class StudentNode {

	 /** The Student information. Access is package-protected */
    Student      value;
    
    /** The next in the chain. NOTE: this is not an object of type Student. Access is package-protected. */
    StudentNode  next;
    
    /**
     * Construct a StudentNode object that references the given Student object.
     * 
     * @param s     student for which a StudentNode is being created.
     */
    public StudentNode (Student s) {
    	this.value = s;
    	this.next = null;    // reasonable to assume (for now) we don't point anywhere.
    }
    
    /** Set the Student stored for this node. A mutator method for this class. */
    public void setValue (Student s) { value = s; }
    
    /** Return the Student stored for this node. An accessor method for this class. */
    public Student getValue () { return value; }
}
