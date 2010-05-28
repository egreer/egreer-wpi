package hd;

import hd.server.LoginServiceImplTest;
import hd.server.ServerFunctionsTest;
import hd.server.profile.UserServiceImplTest;
import hd.server.timesheet.TimesheetServiceImplTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/** Runs all tests in one go
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for hd.server");
		//$JUnit-BEGIN$
		suite.addTestSuite(LoginServiceImplTest.class);
		suite.addTestSuite(UserServiceImplTest.class);
		suite.addTestSuite(TimesheetServiceImplTest.class);
		suite.addTestSuite(ServerFunctionsTest.class);
		//$JUnit-END$
		return suite;
	}

}
