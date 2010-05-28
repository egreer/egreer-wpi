package hd.server.profile;

import hd.client.entities.Account;
import hd.client.entities.Permissions;
import hd.client.profile.Employee;
import hd.client.profile.Position;
import hd.server.LoginServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/** Tests the UserServiceImpl functions
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class UserServiceImplTest extends TestCase{

    private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	UserServiceImpl service = new UserServiceImpl();
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testUpdateProfile() {
		Employee e = new Employee();
		e.setFirstName("test1");
		e.setComments("Test Before");
		
		Employee returned = service.createProfile(e);
		
		if (returned == null) fail("Error Creating user");
		else{
			assertEquals(e.getComments(), returned.getComments());
			returned.setComments("Test After");
			
			Employee returnedTwo = service.updateProfile(returned);
			
			if (returnedTwo == null) fail("Error updating user");
			else{
				assertEquals(e.getFirstName(), returnedTwo.getFirstName());
				assertEquals(returned.getComments(), returnedTwo.getComments());
				assertEquals(returned.getEmployeeKey(), returnedTwo.getEmployeeKey());
			}
		}
		
	}

	@Test
	public void testFetchAllPositions() {
		Position p1 = new Position("Position 1", "Rop1", "Desc1");
		assertNotNull(service.createPosition(p1));
		Position p2 = new Position("Position 2", "Rop2", "Desc2");
		assertNotNull(service.createPosition(p2));
		
		Collection<Position> positions = service.fetchAllPositions();
		assertEquals(2, positions.size());
		
		Iterator<Position> iterator = positions.iterator();
		while (iterator.hasNext()){
			Position p = iterator.next();
			assertTrue(p.getTitle().equals(p1.getTitle()) || p.getTitle().equals(p2.getTitle()));
		}
	}
	
	@Test
	public void testFetchAllEmployees() {
		Employee e = new Employee();
		e.setFirstName("test1");
				
		Employee a = new Employee();
		a.setFirstName("test2");
		
		assertNotNull(service.createProfile(e));
		assertNotNull(service.createProfile(a));
		
		Collection<Employee> employeeList = service.fetchAllEmployees();
		
		assertEquals(2, employeeList.size());
		
		Iterator<Employee> iterator = employeeList.iterator();
		while (iterator.hasNext()){
			Employee temp = iterator.next();
			assertTrue(temp.getFirstName().equals(e.getFirstName()) || temp.getFirstName().equals(a.getFirstName()));
		}
	}

	@Test
	public void testFetchAllEmployeesByPosition() {
		//Create Employees
		Employee e = new Employee();
		e.setFirstName("test1");
		e.setComments("Test Before");
		e = service.createProfile(e);
		
		Employee emp2 = new Employee();
		emp2.setFirstName("test2");
		emp2.setComments("Test Before");
		emp2 = service.createProfile(emp2);
		
		Employee emp3 = new Employee();
		emp3.setFirstName("test2");
		emp3.setComments("Test Before");
		emp3 = service.createProfile(emp3);
		
		//Create positions
		Position pos = new Position("testTitle", "testROP", "testDescription");
		assertNotNull(service.createPosition(pos));
		
		Position pos2 = new Position("testTitle2", "testROP", "testDescription");
		assertNotNull(service.createPosition(pos2));
		
		//Add positions to employees
		assertTrue(service.addPositionToEmployee(e, pos));
		assertTrue(service.addPositionToEmployee(e, pos2));
		
		assertTrue(service.addPositionToEmployee(emp2, pos));
		assertTrue(service.addPositionToEmployee(emp2, pos2));
		
		assertTrue(service.addPositionToEmployee(emp3, pos2));
		
		//Test the function
		Collection<Employee> posEmps = service.fetchAllEmployeesByPosition(pos);
		Collection<Employee> pos2Emps = service.fetchAllEmployeesByPosition(pos2);
		Collection<Employee> pos3Emps = service.fetchAllEmployeesByPosition((Position) null);
		
		Collection<Employee> posEmps2 = service.fetchAllEmployeesByPosition(pos.getTitle());
		Collection<Employee> pos2Emps2 = service.fetchAllEmployeesByPosition(pos2.getTitle());
		Collection<Employee> pos3Emps2 = service.fetchAllEmployeesByPosition("Fail");
		
		//Assert correct amounts are returned
		assertEquals(2, posEmps.size());
		assertEquals(3, pos2Emps.size());
		assertEquals(0, pos3Emps.size());
		
		assertEquals(2, posEmps2.size());
		assertEquals(3, pos2Emps2.size());
		assertEquals(0, pos3Emps2.size());
	}

	@Test
	public void testFetchProfile() {
		Employee e = new Employee();
		e.setFirstName("test1");
		e.setComments("Test Before");
		
		Employee returned = service.createProfile(e);
		
		//Test Valid By ID
		if (returned == null) fail("Error creating profile");
		else{
			Employee fetched = service.fetchProfile(returned.getEmployeeKey());
			if (fetched == null) fail("Failed to retrieve employee");
			else{
				assertEquals(returned.getEmployeeKey(), fetched.getEmployeeKey());
				assertEquals(returned.getFirstName(), fetched.getFirstName());
			}
		}
		
		//Test Invalid By ID
		Employee fetched = service.fetchProfile(345678L);
		assertNull(fetched);
		
		//TODO Test by username
		if (!(new LoginServiceImpl()).createAccount("test", "test", "test", Permissions.EMPLOYEE)){
			fail("Error Creating account");
		}
		
		fetched = service.fetchProfile("test");
		assertNotNull(fetched);	
		
		fetched = service.fetchProfile("test2");
		assertNull(fetched);
		
	}

	@Test
	public void testCreateProfile() {
		Employee e = new Employee();
		e.setFirstName("test1");
		e.setComments("Test Before");
		
		//Test Valid Create
		Employee returned = service.createProfile(e);
		assertNotNull(returned);
		assertNotNull(returned.getEmployeeKey());
		
		//TODO Test invalid create?
	}

	@Test
	public void testGetPosition() {
		//Create the position
		Position pos = new Position("testTitle", "testROP", "testDescription");
		assertNotNull(service.createPosition(pos));
		
		//Get a valid position by title
		Position returned = service.getPosition("testTitle");
		assertEquals("testTitle", returned.getTitle());
		
		//Get a valid position by ID
		Position idReturned = service.getPosition(returned.getPositionKey());
		assertEquals(returned.getTitle(), idReturned.getTitle());
		assertEquals(returned.getPositionKey(), idReturned.getPositionKey());
		
		//Try to get an invalid position by title
		returned = service.getPosition("testFailTitle");
		assertNull(returned);
		
		//Try to get an invalid position by ID
		idReturned = service.getPosition(1234567890L);
		assertNull(idReturned);
	}
	
	@Test
	public void testRemovePosition() {
		//Test creating a position
		Position pos = new Position("testTitle", "testROP", "testDescription");
		assertNotNull(service.createPosition(pos));
		
		//Test Removing position by key
		assertTrue(service.removePosition(pos));
		
		assertFalse(service.removePosition(pos));
		
		//Add the position back
		assertNotNull(service.createPosition(pos));
		
		//Test Removing position by Title
		assertTrue(service.removePosition(pos.getTitle()));
		
		assertFalse(service.removePosition("Fail"));
	}

	@Test
	public void testUpdatePosition() {
		//Test creating a position
		Position pos = new Position("testTitle", "testROP", "testDescription");
		assertNotNull(service.createPosition(pos));
		
		Position retrieved = service.getPosition("testTitle");
		retrieved.setTitle("testTitle2");
		retrieved.setRateOfPay("$3.00");
		retrieved.setDescription("New Description");
		
		Position updated = service.updatePosition(retrieved);
		assertEquals("New Description", updated.getDescription());
		assertEquals("$3.00", updated.getRateOfPay());
		assertEquals("testTitle2", updated.getTitle());
		assertEquals(retrieved.getPositionKey(), updated.getPositionKey());
	}
	
	@Test
	public void testCreatePosition() {
		//Test creating a position
		Position pos = new Position("testTitle", "testROP", "testDescription");
		assertNotNull(service.createPosition(pos));
		
		//Test creating a position that already exists
		assertNull(service.createPosition(pos));
	}
	
	@Test
	public void testAssociateAccountToEmployee() {
		
		//Create Account in DB
		Boolean b = (new LoginServiceImpl()).createAccount("test1", "test1", "test1", Permissions.EMPLOYEE);
		if (!b) fail("Error Creating Account"); 
		Account a = new Account("test1", "test1");
		a = LoginServiceImpl.getAccount(a);
		
		//Create Employee in DB
		Employee e = new Employee();
		e.setFirstName("test1");
		e.setComments("Test Before");
		e = service.createProfile(e);
		
		if (a != null && e != null && service.associateAccountToEmployee(a, e)){
			Account testAcc = LoginServiceImpl.getAccount(a);
			assertEquals(e.getEmployeeKey(), testAcc.getEmployeeID());
			
		} else fail("Error retrieving account");
	}
	
	@Test
	public void testAddPositionToEmployee(){
		//Create Employee in DB
		Employee e = new Employee();
		e.setFirstName("test1");
		e.setComments("Test Before");
		e = service.createProfile(e);
		
		Position pos = new Position("testTitle", "testROP", "testDescription");
		assertNotNull(service.createPosition(pos));
		
		Position pos2 = new Position("testTitle2", "testROP", "testDescription");
		assertNotNull(service.createPosition(pos2));
		
		assertTrue(service.addPositionToEmployee(e, pos));
		assertTrue(service.addPositionToEmployee(e, pos2));
		
		Employee returned = service.fetchProfile(e.getEmployeeKey());
		assertTrue(returned.hasPosition(pos));
		assertTrue(returned.hasPosition(pos2));
		
	}
	
	@Test
	public void testAddAllPositionsToEmployee(){
		Employee e = new Employee();
		e.setFirstName("test1");
		e = service.createProfile(e);

		Position pos = new Position("testTitle", "testROP", "testDescription");
		pos = service.createPosition(pos);
		
		Position pos2 = new Position("testTitle2", "testROP", "testDescription");
		pos2 = service.createPosition(pos2);
		
		ArrayList<Long> positions = new ArrayList<Long>(); 
		positions.add(pos.getPositionKey());
		positions.add(pos2.getPositionKey());
		
		assertTrue(service.addAllPositionsToEmployee(e, (Collection<Long>)positions));
	}
	
	@Test
	public void testRemovePositionFromEmployee(){
		//Create Employee in DB
		Employee e = new Employee();
		e.setFirstName("test1");
		e.setComments("Test Before");
		e = service.createProfile(e);
		
		Position pos = new Position("testTitle", "testROP", "testDescription");
		assertNotNull(service.createPosition(pos));
		
		Position pos2 = new Position("testTitle2", "testROP", "testDescription");
		assertNotNull(service.createPosition(pos2));
		
		assertTrue(service.addPositionToEmployee(e, pos));
		assertTrue(service.addPositionToEmployee(e, pos2));
		
		Employee returned = service.fetchProfile(e.getEmployeeKey());
		assertTrue(returned.hasPosition(pos));
		assertTrue(returned.hasPosition(pos2));
		
		assertTrue(service.removePositionFromEmployee(returned, pos2));
		
		Employee returned2 = service.fetchProfile(e.getEmployeeKey());
		assertTrue(returned2.hasPosition(pos));
		assertFalse(returned2.hasPosition(pos2));
		
	}
}
