package hd.client.timesheet;

import hd.client.profile.Employee;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/** Interface for the Timesheet Service 
 *    
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
@RemoteServiceRelativePath("timesheet")
public interface TimesheetService extends RemoteService{
	
	/** Retrieves a Timesheet for the given username for the current pay period  
	 * @param username	The employee username
	 * @return			The specified Timesheet
	 */
	Timesheet retrieveCurrentTimesheet(String username);
	
	/** Retrieves a Timesheet for the given Employee for the current pay period  
	 * @param employee	The employee object (Must have a key)
	 * @return			The specified Timesheet
	 */
	Timesheet retrieveCurrentTimesheet(Employee employee);
	
	/** Retrieves a Timesheet for the given Employee for the pay period in 
	 * which the specified date falls 
	 * 
	 * @param employee	The employee object (Must have a key)
	 * @param date		The date to find the Timesheet for
	 * @return			The specified Timesheet
	 */
	Timesheet retrieveTimesheet(Employee employee, Date date);
	
	/** Retrieves a specific Timesheet based on the key
	 * @param key	The key of the Timesheet
	 * @return		The specified Timesheet		
	 */
	Timesheet retrieveTimesheet(Long key);
	
	/** Retrieves a list of ScheduleDetails for a given Timesheet
	 * 
	 * @param timesheet	The Timesheet to retrieve details for
	 * @return			Returns list of schedule details for the specific position  
	 */
	List<ScheduleDetails> retrieveScheduleDetails(Timesheet timesheet);
	
	/** Updates a ScheduleDetails with new information
	 * 
	 * @param details	The ScheduleDetails containing the information to update
	 * @return			Returns the ScheduleDetails updated with the updates
	 */
	ScheduleDetails updateScheduleDetails(ScheduleDetails details);
	
	/** Retrieves a ScheduleDetails using the given key
	 * 
	 * @param key	The key of the detail to retrieve
	 * @return		Returns the ScheduleDetails
	 */
	ScheduleDetails getScheduleDetail(Long key);
	
	/** Changes a timesheet's status 
	 * 
	 * @param timesheet	The Timesheet to change status of.
	 * @param status	The new status for the timesheet
	 * @return			True if the status was changed, False if not
	 */
	Boolean changeTimesheetStatus(Timesheet timesheet, Status status);
	
	/** Changes the print status of the Timesheet
	 * 
	 * @param timesheet		The Timesheet to change the status of
	 * @param status		True means the sheet is printed, False the sheet is not printed 
	 * @return				True if the status was changed, False if it was not
	 */
	Boolean changeTimesheetPrintStatus(Timesheet timesheet, Boolean status);
	
	//Boolean uploadW2Work(File schedule);
	
	/** Returns a list of all Timesheets in the database */
	List<Timesheet> retrieveAllTimesheets();
	
	/** Returns a list of all timesheets of a given ststus
	 * 
	 * @param status	 The status to find timesheets of 
	 * @return			The list of Timesheets
	 */
	List<Timesheet> retrieveAllTimesheets(Status status);
	
	/** Returns a list of all timesheets of a given employee
	 * 
	 * @param e		The Employee to find timesheets for 
	 * @return		The list of all Timesheets for the employee  
	 */
	List<Timesheet> retrieveAllTimesheets(Employee e);
	
	/** Imports data from a WhenToWork schedule XMl export
	 * 
	 * @param xml	The XML string
	 * @return		Returns status of the import 
	 */
	String uploadXML(String xml);
	
	/** The list of all timesheets with more detailed information   
	 * 
	 * @return	Returns a list of all CompleteTimesheet
	 */
	List<CompleteTimesheet> retrieveAllCompleteTimesheets();
	
	/** Retrieves the CompleteTimesheet for a given Timesheet
	 * 
	 * @param timesheetKey	The key of the Timesheet	
	 * @return				The CompleteTimesheet for the key
	 */
	CompleteTimesheet retrieveCompleteTimesheet(Long timesheetKey);
	
	/** Retrieves the CompleteTimesheet 
	 * 
	 * @param username	The username to get the sheets for 
	 * @return			The list of of CompleteTimesheets for the username
	 */
	List<CompleteTimesheet> retrieveCompleteTimesheets(String username);
}
