package hd.client.timesheet;

import java.util.Date;

/** This enumeration is used to convert the WhenToWork day to the 
 * common name for a day of the week. W2W week begins on Monday number 0   
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
public enum Day{
	MONDAY(0),	TUESDAY(1),
	WEDNESDAY(2), THURSDAY(3),
	FRIDAY (4), SATURDAY(5), SUNDAY(6);

	private int day;

	Day(int day) {
		this.day = day;
	}

	@Override
	public String toString(){
		switch(day){
		case 0: return "Monday";
		case 1: return "Tuesday";
		case 2: return "Wednesday";
		case 3: return "Thursday";
		case 4: return "Friday";
		case 5: return "Saturday";
		case 6: return "Sunday";
		default: return null;
		}
	}

	public static Day whichDay(int i) {
		switch(i){
		case 0: return Day.MONDAY;
		case 1: return Day.TUESDAY;
		case 2: return Day.WEDNESDAY;
		case 3: return Day.THURSDAY;
		case 4: return Day.FRIDAY;
		case 5: return Day.SATURDAY;
		case 6: return Day.SUNDAY;
		default: return null;
		}
	}

	@SuppressWarnings("deprecation")
	public static Day whichDay(Date d) {
		//DateFormat sdf = new SimpleDateFormat("E");
		//String day = sdf.format(d);

		int day = d.getDay();
		switch(day){
		case 0:return Day.SUNDAY; 

		case 1: return Day.MONDAY;
		case 2: return Day.TUESDAY;
		case 3: return Day.WEDNESDAY;
		case 4:return Day.THURSDAY; 

		case 5: return Day.FRIDAY;
		case 6: return Day.SATURDAY;
		default: return null;
		}

		/*if (day.equals("Mon")) return Day.MONDAY;
		else if (day.equals("Tue")) return Day.TUESDAY;
		else if (day.equals("Wed")) return Day.WEDNESDAY;
		else if (day.equals("Thu")) return Day.THURSDAY;
		else if (day.equals("Fri")) return Day.FRIDAY;
		else if (day.equals("Sat")) return Day.SATURDAY;
		else if (day.equals("Sun")) return Day.SUNDAY;
		else return null;*/
	}


};