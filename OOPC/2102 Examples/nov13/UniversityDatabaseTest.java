package nov13;

import java.io.File;
import java.io.FileNotFoundException;

import junit.framework.TestCase;

public class UniversityDatabaseTest extends TestCase {

	public void testLoad () throws FileNotFoundException {
		// INPUT 
		UniversityDatabase.loadTable(new File ("nov13\\enrollment.txt"));
		
		CourseList list = UniversityDatabase.getDatabase();
		
		// OUTPUT
		list.output();
	}
}
