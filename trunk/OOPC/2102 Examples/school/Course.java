package school;

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
}