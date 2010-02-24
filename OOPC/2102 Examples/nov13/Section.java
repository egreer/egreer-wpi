package nov13;

/**
 * Represents a section for a course.
 * 
 * @author heineman
 */
public class Section {

	/** Section number. */
	String number;

	/** List of students. */
	StudentList students;
	
	/**
	 * Represents a section.
	 * 
	 * Initialize student list to an empty list.
	 * 
	 * @param number
	 */
	public Section (String number) {
		this.number = number;
		
		students = new StudentList();
	}
	
	/** Return Representation of a Section. */
	public String toString () {
		return "Section " + number;
	}
	
	/**
	 * Returns the HashCode value for a Section object.
	 * 
	 * This method returns an int with the following properties:
	 * 
	 *    If s1 and s2 refer to two Section objects, and s1.equals (s2) then
	 *    s1.hashCode() == s2.hashCode()
	 *    
	 * Note, if it so happens that two objects, s3 and s4, each return the same
	 * value with s3.hashCode() and s4.hashCode(), it is not required that s3.equals(s4).
	 * 
	 */
	public int hashCode() {
		// compose our hashCode by returning the hashCode for our section number.
		return number.hashCode();
	}

	/**
	 * Output Section by outputing student list.
	 *
	 */
	public void output () {
		System.out.println (toString());
		students.output();
	}

	/** 
	 * Return student record in section by id.
	 * @param id  target id
	 */
	public Student findStudent(String id) {
		return students.findById(id);
	}
	
	/** 
	 * Add student to section.
	 * 
	 * @param id  target id
	 */
	public void add (Student s) {
		if (findStudent(s.id) == null) {
			students.prepend(s);
		}
	}
	
}
