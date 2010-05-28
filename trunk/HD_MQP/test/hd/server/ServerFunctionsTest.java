package hd.server;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** Tests the ServerFunctions
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class ServerFunctionsTest extends TestCase{

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStartDate() {
		Date date1 = ServerFunctions.ParseDate("20100314");
		Date start1 = ServerFunctions.ParseDate("20100314");
		
		Date date2 = ServerFunctions.ParseDate("20110422");
		Date start2 = ServerFunctions.ParseDate("20110410");
		
		Date date3 = ServerFunctions.ParseDate("20101120");
		Date start3 = ServerFunctions.ParseDate("20101107");
		
		assertEquals(start1, ServerFunctions.startDate(date1));
		assertEquals(start2, ServerFunctions.startDate(date2));
		assertEquals(start3, ServerFunctions.startDate(date3));
	}
	

	@Test
	public void testEndDate() {
		Date date1 = ServerFunctions.ParseDate("20100314");
		Date end1 = ServerFunctions.ParseDate("20100327");
		
		Date date2 = ServerFunctions.ParseDate("20110422");
		Date end2 = ServerFunctions.ParseDate("20110423");
		
		Date date3 = ServerFunctions.ParseDate("20101120");
		Date end3 = ServerFunctions.ParseDate("20101120");
		
		assertEquals(end1, ServerFunctions.endDate(date1));
		assertEquals(end2, ServerFunctions.endDate(date2));
		assertEquals(end3, ServerFunctions.endDate(date3));
	}

	@Test
	public void testDueDate() {
		Date date1 = ServerFunctions.ParseDate("20100314");
		Date due1 = ServerFunctions.ParseDate("20100325");
		
		Date date2 = ServerFunctions.ParseDate("20110422");
		Date due2 = ServerFunctions.ParseDate("20110421");
		
		Date date3 = ServerFunctions.ParseDate("20101120");
		Date due3 = ServerFunctions.ParseDate("20101118");
		
		assertEquals(due1, ServerFunctions.dueDate(date1));
		assertEquals(due2, ServerFunctions.dueDate(date2));
		assertEquals(due3, ServerFunctions.dueDate(date3));
	}
	
	
	@Test
	public void testParseDate() {
		assertNotNull( ServerFunctions.ParseDate("20100314"));
		assertNull( ServerFunctions.ParseDate("2010&03*&14"));
	}

}
