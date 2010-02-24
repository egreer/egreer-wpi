package cs4233.hw7;

/** 
 * Customer remains basically the same just has a new variable called status for the currentCustomerStatus. 
 * Customer has other methods relating to the customer but are not important to the structure of this system.
 *
 */
public class Customer {

	protected CustomerStatus status;
	
	/** 
	 * Changes the current Status to a new CustomerStatus if needed.
	 */
	protected boolean changeState(CustomerStatus status){
		this.status = status;
		return this.equals(status); 
	}
}
