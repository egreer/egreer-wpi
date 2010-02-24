package planner.design2;

public class TimeSlot {

	/** start. */
	int startingTime;
	
	/** end */
	int endingTime;
	
	/** Day. */
	String day;
	
	/**
	 * Constructor.
	 * 
	 * @param start
	 * @param end
	 * @param day
	 */
	public TimeSlot (int start, int end, String day) {
		this.startingTime = start;
		this.endingTime = end;
		this.day = day;
	}

}
