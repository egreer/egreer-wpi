package nov10;

/**
 * Represents a course at a university
 * 
 * @author heineman
 *
 */
public class Course {

	/** Course Name. */
	private String name;

	/** Professor. */
	private String professor;
	
	/**
	 * Default constructor
	 * 
	 * @param name
	 * @param professor
	 */
	public Course (String name, String professor) {
		this.name = name;
		this.professor = professor;
	}
	
	/** Return the name of this course. */
	public String getName() {
		return name;
	}
	
	/** Return the professor for this course. */
	public String getProfessor() {
		return professor;
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
		return name.hashCode() + professor.hashCode();		
	}
}