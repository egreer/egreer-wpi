package hd.client.timesheet;

import hd.client.profile.Employee;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/** Asynchronous Interface for the Timesheet Service 
 *  TODO: Implementation
 * @see TimesheetService
 *   
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
public interface TimesheetServiceAsync {
	void retrieveCurrentTimesheet(String username, AsyncCallback<Timesheet> callback);
	void retrieveCurrentTimesheet(Employee employee, AsyncCallback<Timesheet> callback);
	void retrieveTimesheet(Employee employee, Date date, AsyncCallback<Timesheet> callback);
	void retrieveScheduleDetails(Timesheet timesheet, AsyncCallback<List<ScheduleDetails>> callback);
	void updateScheduleDetails(ScheduleDetails details, AsyncCallback<ScheduleDetails> callback);
	void changeTimesheetStatus(Timesheet timesheet, Status status, AsyncCallback<Boolean> callback);
	//void uploadW2Work(File schedule, AsyncCallback<Boolean> callback);
	void retrieveAllTimesheets(AsyncCallback<List<Timesheet>> callback);
	void retrieveAllTimesheets(Status status, AsyncCallback<List<Timesheet>> callback);
	void retrieveAllTimesheets(Employee e, AsyncCallback<List<Timesheet>> callback);
	void getScheduleDetail(Long key, AsyncCallback<ScheduleDetails> callback);
	void changeTimesheetPrintStatus(Timesheet timesheet, Boolean status, AsyncCallback<Boolean> callback);
	void uploadXML(String xml, AsyncCallback<String> asyncCallback);
	void retrieveTimesheet(Long key, AsyncCallback<Timesheet> asyncCallback);
	void retrieveAllCompleteTimesheets(AsyncCallback<List<CompleteTimesheet>> callback);
	void retrieveCompleteTimesheet(Long timesheetKey, AsyncCallback<CompleteTimesheet> callback);
	void retrieveCompleteTimesheets(String username, AsyncCallback<List<CompleteTimesheet>> callback);
}
