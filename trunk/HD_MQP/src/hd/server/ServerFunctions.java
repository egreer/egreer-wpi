package hd.server;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** Common functions for the Server
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class ServerFunctions {
	
	public static final Date startDateEpoch = ParseDate("20090816");
	public static final Date endDateEpoch = ParseDate("20090829");
	
	/** Returns the start date of the Timesheet for a given date 
	 * 
	 * @param date	The date to find the timesheet of
	 * @return		The start date of the Timesheet
	 */
	public static Date startDate(Date date){
		Calendar epochCal = Calendar.getInstance();
		epochCal.setTime(startDateEpoch);
		
		Calendar dateCal = Calendar.getInstance();
		dateCal.setTime(date);
		
		double periods = diffDayPeriods(epochCal, dateCal) / 14.0;
		
		epochCal.add(Calendar.DATE, (int) (Math.floor(periods) * 14));
		
		return epochCal.getTime();
	}
	
	/**Returns the end date of the Timesheet for a given date
	 * 
	 * @param date	The date to find the Timesheet of
	 * @return		The end date of a Timesheet
	 */
	public static Date endDate(Date date){
		Calendar epochCal = Calendar.getInstance();
		epochCal.setTime(endDateEpoch);
		
		Calendar dateCal = Calendar.getInstance();
		dateCal.setTime(date);
		
		double periods = diffDayPeriods(epochCal, dateCal) / 14.0;
		
		epochCal.add(Calendar.DATE, (int) (Math.ceil(periods) * 14));
		
		return epochCal.getTime();
	}
	
	/** Returns the Thursday due date of the Timesheet  
	 * 
	 * @param date	The date to find the due date of
	 * @return		The due date of the Timesheet
	 */
	public static Date dueDate(Date date){
		Calendar epochCal = Calendar.getInstance();
		epochCal.setTime(endDateEpoch);
		
		Calendar dateCal = Calendar.getInstance();
		dateCal.setTime(date);
		
		double periods = diffDayPeriods(epochCal, dateCal) / 14.0;
		
		epochCal.add(Calendar.DATE, (int) ((Math.ceil(periods) * 14)-2));
		
		return epochCal.getTime();
	}
	
	/** Calculates the difference between two Calendar dates
	 * 
	 * @param start	The start date 
	 * @param end	The end date
	 * @return		The number of whole days between them			
	 */
    public static long diffDayPeriods(Calendar start, Calendar end) {
        long endL   =  end.getTimeInMillis() +  end.getTimeZone().getOffset(  end.getTimeInMillis() );
        long startL = start.getTimeInMillis() + start.getTimeZone().getOffset( start.getTimeInMillis() );
        return (endL - startL) / (24*60*60*1000);
    }
	
	/** Parses a string date in the form yyyyMMdd and returns the Date object
	 * 
	 * @param date 	In the form yyyyMMdd
	 * @return
	 */
	public static Date ParseDate(String date){
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		try
		{
			return df.parse(date);           
		} catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
