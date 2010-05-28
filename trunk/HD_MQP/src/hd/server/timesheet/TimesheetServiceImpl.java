package hd.server.timesheet;

import hd.client.profile.Employee;
import hd.client.timesheet.CompleteTimesheet;
import hd.client.timesheet.ScheduleDetails;
import hd.client.timesheet.Status;
import hd.client.timesheet.Timesheet;
import hd.client.timesheet.TimesheetService;
import hd.server.db.PMF;
import hd.server.profile.UserServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/** The server implementation of TimesheetService
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class TimesheetServiceImpl extends RemoteServiceServlet implements TimesheetService {

	/** Auto generated Serial key */
	private static final long serialVersionUID = -2899313895023534804L;

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#changeTimesheetStatus(hd.client.timesheet.Timesheet, hd.client.timesheet.Status)
	 */
	@Override
	public Boolean changeTimesheetStatus(Timesheet timesheet, Status status) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Boolean returner = Boolean.FALSE;
		Timesheet temp = null;

		try{
			temp = pm.getObjectById(Timesheet.class, timesheet.getTimesheetKey());
		}catch (JDOObjectNotFoundException ex){
			//Handled above
		}

		if (temp != null){
			temp.setStatus(status);

			//Try to commit to database
			Transaction tx = pm.currentTransaction();
			try
			{
				tx.begin();

				pm.makePersistent(temp);
				returner = Boolean.TRUE;
				tx.commit();
			}
			finally
			{
				if (tx.isActive())
				{
					tx.rollback();
					returner = Boolean.FALSE;
				}
			}
		}
		pm.close();
		return returner;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#retrieveCurrentTimesheet(java.lang.String)
	 */
	@Override
	public Timesheet retrieveCurrentTimesheet(String username) {
		Employee e = new UserServiceImpl().fetchProfile(username);
		return this.retrieveCurrentTimesheet(e);
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#retrieveCurrentTimesheet(hd.client.profile.Employee)
	 */
	@Override
	public Timesheet retrieveCurrentTimesheet(Employee employee) {
		if (employee != null){
			return this.retrieveTimesheet(employee, Calendar.getInstance().getTime());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#retrieveTimesheet(hd.client.profile.Employee, java.util.Date)
	 */
	@Override
	public Timesheet retrieveTimesheet(Employee employee, Date date) {
		if (employee != null && date != null){
			List<Timesheet> timesheets = this.retrieveAllTimesheets(employee);
			Iterator<Timesheet> iterator = timesheets.iterator();

			while(iterator.hasNext()){
				Timesheet temp = iterator.next();
				if (temp.inRange(date)) return temp;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#retrieveScheduleDetails(hd.client.timesheet.Timesheet)
	 */
	@Override
	public List<ScheduleDetails> retrieveScheduleDetails(Timesheet timesheet) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<ScheduleDetails> returner = new ArrayList<ScheduleDetails>();
		Extent<ScheduleDetails> ScheduleDetails = pm.getExtent(ScheduleDetails.class);

		Iterator<ScheduleDetails> iterator = ScheduleDetails.iterator();
		while (iterator.hasNext()){
			ScheduleDetails temp = iterator.next();

			if(temp.getTimesheetKey().equals(timesheet.getTimesheetKey())) returner.add(temp);
		}

		pm.close();
		return returner;

	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#updateScheduleDetails(hd.client.timesheet.ScheduleDetails)
	 */
	@Override
	public ScheduleDetails updateScheduleDetails(ScheduleDetails details) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ScheduleDetails temp = null;

		try{
			temp = pm.getObjectById(ScheduleDetails.class, details.getScheduleDetailsKey());
		}catch (JDOObjectNotFoundException ex){
			//Handled above
		}

		if (temp != null && details != null){

			temp.setSpecialCase(details.getSpecialCase());
			temp.setComments(details.getComments());


			//Try to commit to database
			Transaction tx = pm.currentTransaction();
			try
			{
				tx.begin();
				pm.makePersistent(temp);
				tx.commit();
			}
			finally
			{
				if (tx.isActive())
				{
					tx.rollback();
					temp = null;
				}
			}

		}
		pm.close();
		return temp;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#retrieveAllTimesheets()
	 */
	@Override
	public List<Timesheet> retrieveAllTimesheets() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Timesheet> returner = new ArrayList<Timesheet>();
		Extent<Timesheet> timesheets = pm.getExtent(Timesheet.class);

		Iterator<Timesheet> iterator = timesheets.iterator();
		while (iterator.hasNext()){
			returner.add(iterator.next());
		}

		return returner;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#retrieveAllTimesheets(hd.client.profile.Employee)
	 */
	@Override
	public List<Timesheet> retrieveAllTimesheets(Employee e) {
		List<Timesheet> all = this.retrieveAllTimesheets();
		List<Timesheet> returner = new ArrayList<Timesheet>();

		if (e == null) return returner;

		Iterator<Timesheet> iterator = all.iterator();

		while (iterator.hasNext()){
			Timesheet temp = iterator.next();
			if (temp.getEmployeeKey().equals(e.getEmployeeKey())) returner.add(temp);
		}

		return returner;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#retrieveAllTimesheets(hd.client.timesheet.Status)
	 */
	@Override
	public List<Timesheet> retrieveAllTimesheets(Status status) {
		List<Timesheet> all = this.retrieveAllTimesheets();
		List<Timesheet> returner = new ArrayList<Timesheet>();

		Iterator<Timesheet> iterator = all.iterator();

		while (iterator.hasNext()){
			Timesheet temp = iterator.next();
			if (temp.getStatus().equals(status)) returner.add(temp);
		}

		return returner;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#getScheduleDetail(java.lang.Long)
	 */
	@Override
	public ScheduleDetails getScheduleDetail(Long key) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ScheduleDetails temp = null;

		try{
			temp = pm.getObjectById(ScheduleDetails.class, key);
		}catch (JDOObjectNotFoundException e){
			//Handled above
		}

		pm.close();
		return temp;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#changeTimesheetPrintStatus(hd.client.timesheet.Timesheet, java.lang.Boolean)
	 */
	@Override
	public Boolean changeTimesheetPrintStatus(Timesheet timesheet,
			Boolean status) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#uploadXML(java.lang.String)
	 */
	@Override
	public String uploadXML(String xml) {
		//XXX temporary method until can upload files
		return W2WXMLParser.createTimesheets("string.xml", xml);
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#retrieveTimesheet(java.lang.Long)
	 */
	@Override
	public Timesheet retrieveTimesheet(Long key) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Timesheet temp = null;

		try{
			temp = pm.getObjectById(Timesheet.class, key);
		}catch (JDOObjectNotFoundException e){
			//Handled above
		}

		pm.close();
		return temp;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#retrieveAllCompleteTimesheets()
	 */
	@Override
	public List<CompleteTimesheet> retrieveAllCompleteTimesheets() {
		List<Timesheet> sheets = this.retrieveAllTimesheets();
		List<CompleteTimesheet> returner = new ArrayList<CompleteTimesheet>();
		Iterator<Timesheet> iterator = sheets.iterator();

		while(iterator.hasNext())
			returner.add(this.retrieveCompleteTimesheet(iterator.next().getTimesheetKey()));

		return returner;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#retrieveCompleteTimesheet(java.lang.Long)
	 */
	@Override
	public CompleteTimesheet retrieveCompleteTimesheet(Long timesheetKey) {
		Timesheet sheet = this.retrieveTimesheet(timesheetKey);

		if (sheet == null) return null;

		CompleteTimesheet returner = new CompleteTimesheet(timesheetKey);
		returner.setStartDate(sheet.getStartDate());
		returner.setEndDate(sheet.getEndDate());
		returner.setStatus(sheet.getStatus());
		returner.setPrinted(sheet.getPrinted());

		Employee e = (new UserServiceImpl()).fetchProfile(sheet.getEmployeeKey());
		if (e != null){
			returner.setEmployeeKey(e.getEmployeeKey());
			returner.setEmployeeLastName(e.getLastName());
			returner.setEmployeeFirstName(e.getFirstName());
		}

		List<ScheduleDetails> details = retrieveScheduleDetails(sheet);

		Long[] keys = new Long[details.size()];
		double totalTime = 0;


		for(int i = 0 ; i < details.size() ; i++){
			ScheduleDetails temp = details.get(i);
			System.out.println("Curent Hours: " + temp.getCurrentHours() );
			System.out.println("Normal Hours: " + temp.getNormalHours() );
			System.out.println("Special Hours: " + temp.getSpecialCase() );
			totalTime += temp.getCurrentHours();
			keys[i] = temp.getScheduleDetailsKey();
		}
		System.out.println("End Hours: " + totalTime + "\n");

		returner.setScheduleDetailsKeys(keys);
		returner.setTotalHours(totalTime);

		return returner;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.timesheet.TimesheetService#retrieveCompleteTimesheets(java.lang.String)
	 */
	@Override
	public List<CompleteTimesheet> retrieveCompleteTimesheets(String username) {
		Employee e = (new UserServiceImpl()).fetchProfile(username);
		List<Timesheet> sheets = this.retrieveAllTimesheets(e);
		List<CompleteTimesheet> returner = new ArrayList<CompleteTimesheet>();
		Iterator<Timesheet> iterator = sheets.iterator();

		while(iterator.hasNext())
			returner.add(this.retrieveCompleteTimesheet(iterator.next().getTimesheetKey()));

		return returner;
	}
}
