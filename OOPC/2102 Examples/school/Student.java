package school;

/**
 * Represents a student in a course.
 *
 * Each student has a unique ID, and some basic info (name, yearOfGraduation, major)
 * 
 * Note that this class is not responsible for ensuring the uniqueness of the identifier.
 * 
 * @author heineman
 */
public class Student {

	// Instance Variables are defined as follows:
	/** Unique id. */
	private String id;
	
	/** Student Name. */
	private  String name;
	
	/** Year of graduation. */
	private  String yearOfGrad;
	
	/** Major. */
	private  String major;
	
	/**
	 * Constructor for a Student object
	 * 
	 * @param ident      Unique identifier
	 * @param name       Student name
	 * @param yog        year of graduation
	 * @param maj        student major
	 */
	public Student (String ident, String name, String yog, String maj) {
		id = ident;
		
		// note that parameter name 'masks' instance variable. "this." is 
		// essential
		this.name = name;    
		
		this.yearOfGrad = yog;   // the "this." is not really needed.
		major = maj;             // shorthand notation when 
	}
	
	
	/**
	 * Return a String representation of a Student. 
	 */
	public String toString () {
		return name + " \'" + yearOfGrad + " (" + major + ")";
	}
	
	/**
	 * Two students are equivalent ONLY if their id's are the same.
	 * 
	 * @param other     The student being compared against.
	 * 
	 * @return          true if same; false otherwise.
	 */
	public boolean equals (Student other) {
		return (id.equals(other.id));
	}
	
}
