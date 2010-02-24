package nov13;

/**
 * Represents a student in a course.
 *
 * Each student has a unique ID, and some basic info (name, yearOfGraduation, major)
 * 
 * Note that this class is not responsible for ensuring the uniqueness of the identifier.
 * All instance variables are package-protected to ensure integrity.
 * 
 * @author heineman
 */
public class Student {

	// Instance Variables are defined as follows:
	/** Unique id. */
	String id;
	
	/** Student Name. */
	String name;
	
	/** Year of graduation. */
	String yearOfGrad;
	
	/** Major. */
	String major;
	
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

	/** Return the identifier for the student. */
	public String getID() { 
		return id;
	}
	
	/** Return the student name. */
	public String getName() {
		return name;
	}
	
	/** Return the student year of graduation. */
	public String getYearOfGrad() {
		return yearOfGrad;
	}
	
	/** Return the student major. */
	public String getMajor() {
		return major;
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
	
	/**
	 * Returns the HashCode value for a Student object.
	 * 
	 * This method returns an int with the following properties:
	 * 
	 *    If s1 and s2 refer to two Student objects, and s1.equals (s2) then
	 *    s1.hashCode() == s2.hashCode()
	 *    
	 * Note, if it so happens that two objects, s3 and s4, each return the same
	 * value with s3.hashCode() and s4.hashCode(), it is not required that s3.equals(s4).
	 * 
	 * On Tuesday I can explain why I know for sure that the String class has the hashCode
	 * method of its own.
	 */
	public int hashCode() {
		// compose our hashCode by computing a value based upon our name and id.
		return id.hashCode() + name.hashCode();		
	}
	
	/**
	 * Return a String representation of a Student. 
	 */
	public String toString () {
		return name + " \'" + yearOfGrad + " (" + major + ")";
	}
}
