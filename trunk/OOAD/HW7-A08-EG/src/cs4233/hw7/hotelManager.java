package cs4233.hw7;

/** 
 * 
 * If a customer wants to search for and reserve hotels, the hotelManager will do this by the following process:
 * 
 * 1. Search for hotels.
 * 2. Select hotels.
 * 3. Book hotels.
 * 
 * The hotelManager is an abstract class because it contains default methods that can be overridden by subclasses.
 *
 */
public abstract class hotelManager {
	
	/**
	 * Default constructor for the hotelManager
	 */
	public hotelManager(){
		
	}
	
	/**
	 * Default Method for searching hotels  
	 */
	public void searchHotels(){
	}
	
	/**
	 * Default Method for selecting hotels  
	 */
	public void selectHotels(){
	}
	
	/**
	 * Default Method for booking hotels 
	 */
	public void bookHotel(){
		
	}
}
