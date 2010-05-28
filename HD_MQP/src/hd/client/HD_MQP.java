package hd.client;

import hd.client.entities.Permissions;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
public class HD_MQP implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	
	
	
	/*private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	 */
	static String username;
	static Permissions permission;
	
		
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	//private final LoginServiceAsync loginService = GWT.create(LoginService.class);

	private final static LoginForm theLoginForm = new LoginForm();
	private static EmployeeProfileForm theEmployeeProfileForm = new EmployeeProfileForm(username);
	private static EmployeeTabLayout theEmployeeTabLayout = new EmployeeTabLayout(theEmployeeProfileForm);
	private static ManagerTabLayout theManagerTabLayout = new ManagerTabLayout(/*theEmployeeProfileForm*/);
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel.get("loginPanel").add(theLoginForm);		
	}

	/** Method executes on a User's login */
	public static void loginUser(){
		RootPanel.get("loginPanel").remove(theLoginForm);
		
		theEmployeeProfileForm = new EmployeeProfileForm(username);
		
		if (permission.equals(Permissions.EMPLOYEE)){
			theEmployeeTabLayout = new EmployeeTabLayout(theEmployeeProfileForm);
			RootPanel.get("loginPanel").add(theEmployeeTabLayout);
			Info.display("Create Tabbed Layout", "Employee");
		}else if (permission.equals(Permissions.MANAGER)){
			theManagerTabLayout = new ManagerTabLayout();
			RootPanel.get("loginPanel").add(theManagerTabLayout);
			Info.display("Create Tabbed Layout", "Manager");
		} else{
			theEmployeeTabLayout = new EmployeeTabLayout(theEmployeeProfileForm);
			RootPanel.get("loginPanel").add(theEmployeeTabLayout);
			Info.display("Create Tabbed Layout", "Else");
		}
	}

	/** Part of the logout clears the variable */
	public static void clearVars(){
		username = null;
		permission = null;
	}
	
	/** Retrieves the username of the currently logged in user*/
	public static String getUsername(){
		return username;
	}
	
	/** Returns the permissions of the currently logged in Employee*/
	public static Permissions getPermission(){
		return permission;
	}
}

