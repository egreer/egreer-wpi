package hd.server;

import java.util.Iterator;
import java.util.List;

import hd.client.entities.Account;
import hd.client.entities.Permissions;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/** Tests all the LoginService functions
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class LoginServiceImplTest extends TestCase{
	
    private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
    LoginServiceImpl service = new LoginServiceImpl();
    
	@Before
	public void setUp() throws Exception {
		helper.setUp();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}
	
	@Test
	public void testValidateLogin() {
		//TEST valid login
		assertTrue(service.createAccount("test", "test", "test", Permissions.EMPLOYEE));
		Account a = new Account("test", "test");
		assertTrue(service.validateLogin(a));
		
		//TEST invalid username
		a = new Account("tester", "test");
		assertFalse(service.validateLogin(a));
		
		//TEST invalid password
		a = new Account("test", "tested");
		assertFalse(service.validateLogin(a));
	}

	@Test
	public void testChangePassword() {
		
		//TEST valid change password
		assertTrue(service.createAccount("test", "test", "test", Permissions.EMPLOYEE));
		Account a = new Account("test", "test");
		assertTrue(service.changePassword(a, "newpw", "newpw"));
		
		//TEST invalid old password
		assertFalse(service.changePassword(a, "newpw", "newpw"));
		
		//TEST invalid new password
		a.changePassword("test", "newpw");
		assertFalse(service.changePassword(a, "newpw2", "newpw"));
		
		//TEST invalid Account
		Account b = new Account("Test2", "test");
		assertFalse(service.changePassword(b, "newpw", "newpw"));
		
	}

	@Test
	public void testGetAccount() {
		assertTrue(service.createAccount("test", "test", "test", Permissions.EMPLOYEE));
		Account a = new Account("test", "");
		Account returned = LoginServiceImpl.getAccount(a);
		assertNotNull(returned);
		if (returned != null){
			assertEquals("test", returned.getUsername());
			assertEquals("test", returned.getPassword());
		}
		
		//Account that doesn't exist
		Account f = new Account("tes2", "");
		returned = LoginServiceImpl.getAccount(f);
		assertNull(returned);
	
	}

	@Test
	public void testCreateAccount() {
		assertTrue(service.createAccount("test", "test", "test", Permissions.EMPLOYEE));
		assertFalse(service.createAccount("test", "test", "test1", Permissions.EMPLOYEE));
		assertFalse(service.createAccount("test", "test", "test", Permissions.EMPLOYEE));
	}
	
	@Test
	public void testPermissionRetrieval(){
		assertTrue(service.createAccount("test", "pw", "pw", Permissions.EMPLOYEE));
		Permissions p = service.getPermissions("test");
		assertEquals(p, Permissions.EMPLOYEE);
		
		assertNull(service.getPermissions("test2"));
	}
	
	@Test
	public void testChangePermissions(){
		assertTrue(service.createAccount("test", "pw", "pw", Permissions.EMPLOYEE));
		assertTrue(service.createAccount("test2", "pw", "pw", Permissions.MANAGER));
		assertEquals(Permissions.EMPLOYEE, service.getPermissions("test"));
		
		//Employee Change Manager's Permissions
		assertFalse(service.changePermissions("test", "test2", Permissions.MANAGER));
		assertEquals(Permissions.EMPLOYEE, service.getPermissions("test"));
		
		//Manage Change Employee's Permissions
		assertTrue(service.changePermissions("test2", "test", Permissions.MANAGER));
		assertEquals(Permissions.MANAGER, service.getPermissions("test"));
	}
	
	public void testRetrieveAllAccounts(){
		assertTrue(service.createAccount("test", "pw", "pw", Permissions.EMPLOYEE));
		assertTrue(service.createAccount("test2", "pw", "pw", Permissions.MANAGER));
		List<Account> accounts= service.retrieveAllAccounts();
		
		Iterator<Account> iterator = accounts.iterator();
		while (iterator.hasNext()){
			Account a = iterator.next();
			assertTrue(a.getUsername().equals("test") || a.getUsername().equals("test2"));
			assertEquals("", a.getPassword());
		}
	}
	

}
