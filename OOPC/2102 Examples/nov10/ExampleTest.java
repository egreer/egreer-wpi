package nov10;

import junit.framework.TestCase;

public class ExampleTest extends TestCase {

	/**
	 * Goal is to create a StudentList containing three
	 * Students.
	 *
	 */
	public void testSmallExample() {
		Student st1 = new Student ("46008", "Ostreicher, Chris", "10", "ECE");
		Student st2 = new Student ("42009", "Myers, Kevin", "10", "CS");
		Student st3 = new Student ("10331", "Flaherty, Michelle", "10", "BB");

		StudentList list = new StudentList();
		list.prepend(st1);
		list.prepend(st2);
		list.prepend(st3);
		
		Student s = list.findById("46008");
		assertEquals (s.getID(), "46008");      // these three are.
		
		s = list.findById("42009");
		assertEquals (s.getID(), "42009");      // these three are.
		
		s = list.findById("10331");
		assertEquals (s.getID(), "10331");      // these three are.

		
		assertNull (list.findById("38234"));    // not in list
	}
}
