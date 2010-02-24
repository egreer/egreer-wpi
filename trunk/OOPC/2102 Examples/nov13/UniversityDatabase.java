package nov13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Load up information from an enrollment.txt file and produce a report
 * on all enrollment information, by course and section.
 * 
 * @author heineman
 */
public class UniversityDatabase {
	
	/** Information about the existing courses. A Static variable */
	static CourseList database;
	
	/**
	 * Return the database of known courses loaded up.
	 * 
	 * @return
	 */
	public static CourseList getDatabase() {
		return database;
	}
	
	/**
	 * Load up the information found in the enrollment data and then
	 * make it available for whomever wants it.
	 * 
	 * @param f    File containing enrollment information.
	 * @throws FileNotFoundException 
	 */
	public static void loadTable (File f) throws FileNotFoundException {
		// INPUT the information
		Scanner sc = new Scanner (f);
		
		// PROCESS: each of line of the table contains enrollment information
		database = new CourseList();
		while (sc.hasNext()) {
			String info = sc.nextLine();
			
			processLine(info);
		}
	}

	/**
	 * Process each enrollment line of the form:
	 *     0123456789-123456789-123456789-123456789-123456
	 *    "MA1021 A01 Ostreicher, Chris        46008 10ECE"
	 *    COURSE  SECT  STUDENT INFORMATION
	 *    
	 * @param info
	 */
	private static void processLine(String info) {
		// PARSE and extract Course, Section, Student
		String dept = info.substring(0,2);   
		String num  = info.substring(2,6);  // Whoops. In video this was 3
		String term = info.substring(7,8);
		
		String sect = info.substring(8,10);
		
		String name = info.substring(11,36);
		String id   = info.substring(36,41);  // skip space
		String yog  = info.substring(42,44);
		String major= info.substring(44);     // from here to the end of the line
		
		//Student s   = new Student (id, name, yog, major);
		//Section sec = new Section (sect);
		//Course  c   = new Course (dept, num, term);
		
		// See if this course information already exists within the database. If not
		// create it, and add to the database.
		Course c = database.findCourse(dept, num, term);
		if (c == null) {
			c   = new Course (dept, num, term);
			database.prepend(c);
		}
		
		// See if this section already exists for this course in the database. If not
		// then create it.
		Section sec = c.findSection (sect);
		if (sec == null) {
			sec = new Section (sect);
			c.add(sec);
		}
		
		// See if this stuedent already exists within this section (shouldn't happen. This
		// is a sanity check). if not, then add to the section.
		Student s = sec.findStudent (id);
		if (s == null) {
			s = new Student (id, name, yog, major);
			sec.add(s);
		}
		
	}

}
