package cs4233.hw7;

/**The CustomerStatus is a class that identifies the customer's status with TravelWeenie. 
 * CustomerStatus implements the state pattern which allows for changing individual states without affecting others
 *  It is an abstract class instead of an interface so that any default behaviour can be implemented. 
 
 * Other Statuses SubClass this class and create their own methods that would override these defaults like: Member, Previous_Customer, Has_Profile
 * CustomerStatus probably has other methods that are currently not part of the system to change state and store other information.
 */
public abstract class CustomerStatus {

	protected String name;
	
	/**
	 * Constructor for creating a default CustomerStatus 
	 */
	protected CustomerStatus(){
		name = "default";
	}

	/** Returns the name of the status.
	 * 
	 */
	public String getName(){
		return name;
	}
	
}
