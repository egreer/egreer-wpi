package hw7;

/** A course object
 * 
 * @author Eric Greer
 *
 */
public class Course {
	public String name;
	public String courseNumber;
	public String term;
	
	/** The constructor for a course
	 * @param name		The name of the course
	 * @param courseNumber	The number of the course
	 * @param term			The term the course was taken
	 */
	public Course(String name, String courseNumber, String term){
		this.name = name;
		this.courseNumber = courseNumber;
		this.term = term;
	}

}
