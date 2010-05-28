package hd.server.timesheet;

import hd.client.profile.Employee;
import hd.client.timesheet.Day;
import hd.client.timesheet.ScheduleDetails;
import hd.client.timesheet.Timesheet;
import hd.server.ServerFunctions;
import hd.server.db.PMF;
import hd.server.profile.UserServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

/** The server side class for building a Timesheet 
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class TimesheetBuilder {
	final static UserServiceImpl userService = new UserServiceImpl();
	final static TimesheetServiceImpl timeService = new TimesheetServiceImpl();

	/** Processes Shift information and puts in the database
	 * 
	 * @param firstname		The first name of the employee
	 * @param lastname		The last name of the employee
	 * @param date			The date of the shift 
	 * @param duration		The length of the shift
	 * @param day			The day of the shift
	 * @param importDate	The date of import
	 * @return
	 */
	public static boolean processShift(String firstname, String lastname, Date date, Double duration, Day day, Date importDate) {
		//validate parameters
		if (lastname == null || firstname == null || date == null || duration == null|| duration < 0 || duration > 24 || day == null) return false;

		//Get user from DB
		Employee emp = null;

		Iterator<Employee> empIter = userService.fetchAllEmployees().iterator();
		while (empIter.hasNext()){
			Employee next = empIter.next();
			if (lastname.equals(next.getLastName()) && firstname.equals(next.getFirstName())){
				emp = next;
			}
		}

		if (emp == null) return false; //If employee was not in the database don't create timesheet

		//Check to see if a time sheet for the period exits for the user
		Timesheet timesheet = timeService.retrieveTimesheet(emp, date);


		if (timesheet != null){
			return setDetail(timesheet, date, duration, day, importDate);

		}else{
			//Create new one, and all details
			timesheet = new Timesheet(emp.getEmployeeKey(), ServerFunctions.startDate(date), ServerFunctions.endDate(date));
			timesheet = saveTimesheet(timesheet);
			if (timesheet == null || !createDetails(timesheet, importDate)) return false; //If the timesheet couldn't be created don't create the details
			return setDetail(timesheet, date, duration, day, importDate);			
		}
	}

	/** Sets the details of a timesheet
	 * 
	 * @param timesheet		The timesheet to set the details of
	 * @param date			The date of the the details
	 * @param duration		The duration of the shift
	 * @param day			The day of the shift
	 * @param importDate	The import date
	 * @return				Returns true if the details were set, False if the details were not set
	 */
	static Boolean setDetail(Timesheet timesheet, Date date, double duration, Day day, Date importDate){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Boolean returner = Boolean.FALSE;

		//If the sheet exists then need to find any details that go with the schedule
		List<ScheduleDetails> details = timeService.retrieveScheduleDetails(timesheet);

		//If there are details modify the details with the latest from the XML
		Iterator<ScheduleDetails> detailIter = details.iterator();
		while(detailIter.hasNext()){
			ScheduleDetails detail = detailIter.next();
			if (detail.getDate().equals(date)){
				//TODO Import date doesn't do quite what we want
				detail.setHours(duration);
				detail.setDay(day);
				//Try to commit to database
				Transaction tx = pm.currentTransaction();
				try	{
					tx.begin();
					pm.makePersistent(detail);
					returner = Boolean.TRUE;
					tx.commit();
				} finally{
					if (tx.isActive())
					{
						tx.rollback();
						returner = Boolean.FALSE;
					}
				}
				pm.close();		
				return returner;
			}
		}
		//Else create details for date should never really get here
		ScheduleDetails newDetail = new ScheduleDetails(timesheet.getTimesheetKey(), date, day, duration, importDate);
		//Try to commit to database
		Transaction tx = pm.currentTransaction();
		try	{
			tx.begin();
			pm.makePersistent(newDetail);
			returner = Boolean.TRUE;
			tx.commit();
		} finally{
			if (tx.isActive())
			{
				tx.rollback();
				returner = Boolean.FALSE;
			}
		}
		pm.close();
		return returner;	
	}

	/** Saves the Timesheet to the database
	 * 
	 * @param timesheet		The Timesheet to save	
	 * @return				Null if the timesheet was not saved
	 */
	static Timesheet saveTimesheet(Timesheet timesheet){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		//Try to commit to database
		Transaction tx = pm.currentTransaction();
		try	{
			tx.begin();

			pm.makePersistent(timesheet);

			tx.commit();

		} finally{
			if (tx.isActive())
			{
				tx.rollback();
				timesheet = null;
			}
		}

		pm.close();
		return timesheet;
	}

	/** Creates all the details for a Timesheet between it's start date and the end date
	 * 
	 * @param timesheet		The Timesheet to generate the details for
	 * @param importDate	The date of import 
	 * @return				True if details were created, false if they were not 
	 */
	static Boolean createDetails(Timesheet timesheet, Date importDate){

		Boolean returner = Boolean.FALSE;

		Date temp = timesheet.getStartDate();
		Calendar epochCal = Calendar.getInstance();
		ArrayList<ScheduleDetails> details = new ArrayList<ScheduleDetails>();

		while (timesheet.inRange(temp)){
			Day dow = Day.whichDay(temp);
			ScheduleDetails newDetail = new ScheduleDetails(timesheet.getTimesheetKey(), temp, dow, 0, importDate);
			details.add(newDetail);

			//Advance date
			epochCal.setTime(temp);
			epochCal.add(Calendar.DAY_OF_MONTH, 1);
			temp = epochCal.getTime();
		}


		//Try to commit to database
		PersistenceManager pm = PMF.get().getPersistenceManager();
		/*		Transaction tx = pm.currentTransaction();
		try	{
			tx.begin();
		Collection<ScheduleDetails> retDetails =*/
			pm.makePersistentAll(details);
		returner = Boolean.TRUE;
		/*		tx.commit();

		}catch(Exception e){
				e.printStackTrace();

		} finally{
			if (tx.isActive())
			{
				tx.rollback();
				returner = Boolean.FALSE;
			}
		}
		 */
		pm.close();

		return returner;
	}
}
