package planner.design2;

import java.util.*;

public class Meeting {

	/** Times. */
	ArrayList<TimeSlot> times = new ArrayList<TimeSlot>();
	
	/**
	 * Location
	 */
	String roomLocation;
	
	/**
	 * Single-day meeting constructor
	 */
	public Meeting (TimeSlot time, String roomLocation) {
		times.add(time);
		this.roomLocation = roomLocation;
	}
	
	/**
	 * Multi-day meeting constructor
	 */
	public Meeting (ArrayList<TimeSlot> times, String roomLocation) {
		for (Iterator<TimeSlot> it = times.iterator(); it.hasNext(); ) {
			times.add (it.next());
		}
		
		this.roomLocation = roomLocation;
	}

}
