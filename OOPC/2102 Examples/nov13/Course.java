package nov13;

/**
 * Represents a course at a university
 * 
 * A course has a Term (A/B/C/D), a Department (CS, ECE) and a number 
 * 
 * @author heineman
 *
 */
public class Course {

	/** Course Number. */
	private String number;

	/** Professor. */
	private String dept;
	
	/** Term. */
	private String term;
	
	/** Sections. */
	SectionList list;
	
	/**
	 * Default constructor
	 * 
	 * @param dept     department of course
	 * @param number   number for course
	 * @param term     term for course
	 */
	public Course (String dept, String number, String term) {
		this.number = number;
		this.dept = dept;
		this.term = term;
		
		list = new SectionList();
	}
	
	/**
	 * Locate a Section object, by number
	 * 
	 * @return           the first Section that matches
	 */
	public Section findSection (String secNum) {
		return list.findSection (secNum);
	}
	
	/**
	 * Add Section to course, if not already there.
	 * 
	 * @param   s    Section to be added.
	 */
	public void add (Section s) {
		if (list.findSection(s.number) == null) {
			list.prepend(s);
		}
	}
	
	/**
	 * Output the course to the screen.
	 * 
	 * Output information, then ask list to output self to screen.
	 */
	public void output () {
		System.out.println (toString());
		list.output();
	}
	
	/** Return the number of this course. */
	public String getNumber() {
		return number;
	}
	
	/** Return the term of this course. */
	public String getTerm() {
		return term;
	}
	
	/** Return the department for this course. */
	public String getDepartment() {
		return dept;
	}
	
	/** Suitable representation of a course. */
	public String toString () {
		return dept + number + " " + term + " term";
	}
	
	/**
	 * Return whether equivalent.
	 * 
	 * Note that equals() is not the same as '=='
	 * 
	 * @param c  course being compared against.
	 * @return
	 */
	public boolean equals (Course c) {
		if (!term.equals(c.term)) { return false; }
		if (!number.equals(c.number)) { return false; }
		if (!dept.equals(c.dept)) { return false; }
		
		return true;  // must be the same		
	}
	
	/**
	 * Returns the HashCode value for a Course object.
	 * 
	 * This method returns an int with the following properties:
	 * 
	 *    If c1 and c2 refer to two Course objects, and c1.equals (c2) then
	 *    c1.hashCode() == c2.hashCode()
	 *    
	 * Note, if it so happens that two objects, c3 and c4, each return the same
	 * value with c3.hashCode() and c4.hashCode(), it is not required that s3.equals(s4).
	 * 
	 * On Tuesday I can explain why I know for sure that the String class has the hashCode
	 * method of its own.
	 */
	public int hashCode() {
		// compose our hashCode by computing a value based upon our name and id.
		return number.hashCode() + dept.hashCode();		
	}
}