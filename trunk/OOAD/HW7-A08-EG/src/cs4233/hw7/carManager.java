package cs4233.hw7;

/** 
 * 
 * If a customer wants to search for and reserve cars, the carManager will do this by the following process:
 * 
 * 1. Search for cars.
 * 2. Select cars.
 * 3. Book cars.
 * 
 * The carManager is an abstract class because it contains default methods that can be overridden by subclasses.
 *
 */
public abstract class carManager {
	
	
	
	/**
	 * Default constructor for the carManager
	 */
	public carManager(){
		
	}
	
	/**
	 * Default Method for searching cars  
	 */
	public void searchCars(){
		
	}
	
	/**
	 * Default Method for selecting cars  
	 */
	public void selectCars(){
		
	}
	
	/**
	 * Default Method for booking Cars 
	 */
	public void bookCar(){
		
	}
}
