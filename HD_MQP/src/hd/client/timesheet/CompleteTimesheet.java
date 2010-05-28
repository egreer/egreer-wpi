package hd.client.timesheet;

import java.io.Serializable;
import java.util.Date;

/** This class is a wrapper class generated to be passed to the client as a 
 * complete record of Timesheet information used in rendering grids, and lists.
 * 
 * @author Eric Greer
 *
 */
public class CompleteTimesheet implements Serializable{

	/** Auto Generated Serial number */
	private static final long serialVersionUID = 1483328457748012837L;
	
	Long timesheetKey;
	Date startDate;
	Date endDate;
	Status status;
	Boolean printed;

	double totalHours;
	
	Long employeeKey;
	String employeeFirstName;
	String employeeLastName;
		
	Long[] scheduleDetailsKeys;

	/** Default Constructor for Serializable */
	public CompleteTimesheet() {}
	
	/** Constructor for a CompleteTimesheet  
	 * @param timesheetKey	The key for the Timesheet that is the base of the complete timesheet 
	 */
	public CompleteTimesheet(Long timesheetKey) {
		this.timesheetKey = timesheetKey;
	}

	/**
	 * @return	Returns the start date of the Timesheet 
	 */
	public Date getStartDate() {
		return startDate;
	}

	/** Sets the start date of the Timesheet 
	 * @param startDate	The start date of the Timesheet
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return	Returns the End date of the Timesheet
	 */
	public Date getEndDate() {
		return endDate;
	}

	/** Sets the Timesheet end date
	 * @param endDate	The end date of the Timesheet
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/** The total number of hours the employee worked during the Timesheet 
	 * @return	Returns the total number of hours
	 */
	public double getTotalHours() {
		return totalHours;
	}

	/** Sets total number of hours the employee worked during the Timesheet
	 * @param totalHours	The total number of hours 
	 */
	public void setTotalHours(double totalHours) {
		this.totalHours = totalHours;
	}

	/**
	 * @return	The employee key for the Timesheet
	 */
	public Long getEmployeeKey() {
		return employeeKey;
	}

	/** Sets the employee key for the timesheet
	 * @param employeeKey	The key for the employee
	 */
	public void setEmployeeKey(Long employeeKey) {
		this.employeeKey = employeeKey;
	}

	/**
	 * @return	returns the employee first name	
	 */
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	/** Sets the Employee first name
	 * @param employeeFirstName		The employee first name
	 */
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	/**
	 * @return	Returns the employee's last name
	 */
	public String getEmployeeLastName() {
		return employeeLastName;
	}

	/** Sets the eployee's  last name
	 * @param employeeLastName	The employee's last name
	 */
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	/**
	 * @return	Returns the keys of the schedule details associated with the Timesheet
	 */
	public Long[] getScheduleDetailsKeys() {
		return scheduleDetailsKeys;
	}

	/** Sets the schedule details of the associate timesheet 
	 * @param scheduleDetailsKeys	The keys of the schedule details 
	 */
	public void setScheduleDetailsKeys(Long[] scheduleDetailsKeys) {
		this.scheduleDetailsKeys = scheduleDetailsKeys;
	}

	/**
	 * @return	The key of the Timesheet
	 */
	public Long getTimesheetKey() {
		return timesheetKey;
	}
	
	/**
	 * @return	The status of the Timesheet
	 */
	public Status getStatus() {
		return status;
	}

	/** Sets the status of the Timesheet
	 * @param status	The status of the Timesheet
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * @return	Returns whether the timesheet has been printed 
	 */
	public Boolean getPrinted() {
		return printed;
	}

	/** Sets wether the tiesheet has been printed
	 * @param printed	True: Timesheet was printed - False Timesheet was not printed 
	 */
	public void setPrinted(Boolean printed) {
		this.printed = printed;
	}
}
