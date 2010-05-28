package hd.client.timesheet;

import java.io.Serializable;
import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/** The ScheduleDetails class contains a reference to a Timesheet
 * It contains details about the number of hours worked, which day of 
 * the week, and any comments about the hours worked. 
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ScheduleDetails implements Serializable{

	@Persistent
	private static final long serialVersionUID = 62L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long scheduleDetailsKey;
	
	@Persistent
	Long timesheetKey; 
	
	@Persistent
	Date date;
	
	/*Day of the Week*/
	@Persistent
	Day day;
	
	@Persistent
	Double hours;

	@Persistent
	Double specialCase;
	
	@Persistent
	String Comments;
	
	@Persistent
	Date importDate;
	

	/** Default constructor for serialization */
	public ScheduleDetails(){
	}
	
	/** Constructor for the Schedule details
	 * 
	 * @param timesheet		The key for the Timesheet
	 * @param date			The date for the details
	 * @param day			The day of the week the detail occurs
	 * @param hours			The normal number of hours worked
	 * @param importDate	The date of import for the schedule details
	 */
	public ScheduleDetails(Long timesheet, Date date, Day day, double hours, Date importDate){
		this.timesheetKey = timesheet;
		this.date = date;
		this.day = day;
		this.hours = hours; //TODO Should be verified before being set.
		this.importDate = importDate; 
	}
	
	/**
	 * @return	Returns the Timesheet key associated with these details
	 */
	public Long getTimesheetKey() {
		return timesheetKey;
	}

	/** Sets the Timesheet key for the schedule details  
	 * @param timesheet		The Timesheet to associate the details with
	 */
	public void setTimesheetKey(Timesheet timesheet) {
		timesheetKey = timesheet.getTimesheetKey();
	}

	/**
	 * @return	Returns the Day of the week for the details
	 */
	public Day getDay() {
		return day;
	}

	/**	Sets the day of the week for the details 
	 * @param day	The day to set for the details
	 */
	public void setDay(Day day) {
		this.day = day;
	}

	/**
	 * @return returns the normal hours a person worked  
	 */
	public Double getNormalHours() {
		return hours;
	}
	
	/** Returns the normal hours worked, or if there are special hours it returns those
	 * 
	 * @return 	Returns the current number of hours worked for this detail 
	 */
	public Double getCurrentHours(){
		if (this.getSpecialCase() != null){
			return this.getSpecialCase();
		}else return this.getNormalHours();
	}

	/** Sets the normal hours worked
	 * 	Must be between 0 and 24 hours
	 * 
	 * @param hours		the number of hours worked
	 * @return			True if hours were set 
	 */
	public boolean setHours(double hours) {
		if (0 < hours && hours <= 24){
			this.hours = hours;
			return true;
			
		}else return false;
		
	}

	/**
	 * @return Returns the Key for the details
	 */
	public Long getScheduleDetailsKey() {
		return scheduleDetailsKey;
	}
	
	
	/**
	 * @return	Returns the hours worked if it has been modified by an employee
	 */
	public Double getSpecialCase() {
		return specialCase;
	}

	/** Set the hours worked that are different then the normal hours from WhenToWork
	 *  must be between 0 and 24 hours
	 * @param hours		The SpecialCase hours worked
	 * @return			True if the hours were set, False if not
	 */
	public boolean setSpecialCase(double hours) {
		if (0 < hours && hours <= 24){
			specialCase = hours;
			return true;
			
		}else return false;
	}

	/**
	 * @return	Returns comments associated with this detail
	 */
	public String getComments() {
		return Comments;
	}
	
	/** Sets the comments for this detail  
	 * @param comments	The comments to set for this detail
	 * @return 			True if the comments were set, False otherwise
	 */
	public boolean setComments(String comments) {
		Comments = comments;
		return true;
	}

	/**
	 * @return	Returns the date of the detail
	 */
	public Date getDate() {
		return date;
	}

	/**Sets the date of the details
	 * @param date	The date for the details
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * @return	Returns the date the details were imported
	 */
	public Date getImportDate() {
		return importDate;
	}
	
	/** Set the date of import of the details
	 * @param importDate	The date of import
	 */
	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}
}
