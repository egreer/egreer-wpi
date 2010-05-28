package hd.client.timesheet;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/** A Timesheet is specific to an individual employee, contains start date, 
 * end date, and status information
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Timesheet implements Serializable {

	@Persistent
	private static final long serialVersionUID = 61L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long timesheetKey;

	@Persistent
	Long employeeKey;

	@Persistent
	Status status;
	
	@Persistent
	Date startDate;
	
	@Persistent
	Date endDate;

	@Persistent
	Boolean printed;
	
	/** Default constructor for serialization */
	public Timesheet(){
		this.status = Status.Assigned;
		this.printed = Boolean.FALSE;
	}

	/** Constructor for Timesheet. Defaults printed to false, and status to assigned
	 * 
	 * @param employee	The employee to associate with the employee
	 * @param startDate	The start date of the Timesheet
	 * @param endDate	The end date of the Timesheet
	 */
	public Timesheet(Long employee, Date startDate, Date endDate){
		this.employeeKey = employee;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = Status.Assigned;
		this.printed = Boolean.FALSE;
	}
	
	/**
	 * @return Gets the Status of the Timesheet
	 */
	public Status getStatus() {
		return status;
	}

	/** Sets the status of the timesheet
	 * 
	 * @param status	The new Status for the Timesheet
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return Retrieves the key for the Timesheet
	 */
	public Long getTimesheetKey() {
		return timesheetKey;
	}

	/**
	 * @return	Returns the key of the employee for the Timesheet 
	 */
	public Long getEmployeeKey() {
		return employeeKey;
	}

	/** Checks to see if the date given is between the start and end date of the timesheet 
	 * 
	 * @param date	The date to check
	 * @return 		True if in date range covered by time sheet (inclusive of start and end dates)
	 */
	public boolean inRange(Date date){
		return ((date.after(startDate) && date.before(endDate)) || date.equals(startDate) || date.equals(endDate));
	}

	/**
	 * @return	The start date of the Timesheet 
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/** Sets the start date of the Timesheet
	 * 
	 * @param startDate	The start date of the Timesheet
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return	Returns the end date of the Timesheet
	 */
	public Date getEndDate() {
		return endDate;
	}

	/** Sets the end date of the Timesheet
	 * 
	 * @param endDate	The end date of the Timesheet
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * @return	Returns whether the Timesheet is printed
	 */
	public Boolean getPrinted() {
		return printed;
	}

	/**Sets the printed status of the Timesheet
	 * @param printed	True if the Timesheet is printed, and false if not
	 */
	public void setPrinted(Boolean printed) {
		this.printed = printed;
	}
}
