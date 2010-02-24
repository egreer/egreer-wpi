package nov07;

/**
 * Represents a student in CS2102
 * 
 * @author heineman
 *
 */
public class Student {

	// Instance Variables are defined as follows:
	
	/** Student Name. */
	public String name;
	
	/** Year of graduation. */
	public String yog;
	
	/** Major. */
	public String major;
	
	/** Section (like "B08"). */
	public String section;
	
	// Methods are defined as follows:
	
	/** Determine if a student is in an afternoon section [B06/B07/B08] */
	public boolean isAfternoon() {
		if (section.equals("B06")) { return true; }
		if (section.equals("B07")) { return true; }
		if (section.equals("B08")) { return true; }
		
		return false;
	}
	
	/**
	 * Return a String representation of a Student. 
	 */
	public String toString () {
		return name + " \'" + yog + " (" + major + ")";
	}
}
