package hd.client.profile;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/** The position object contains information about positions at the helpdesk 
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Position implements Serializable{

	@Persistent
	private static final long serialVersionUID = 58L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long positionKey;
	
	@Persistent
	String title;
	
	@Persistent
	String rateOfPay;
	
	@Persistent
	String description;

	/** Needed for serialization*/
	public Position(){
	}
	/** Constructor with just title, defaults other values to empty strings
	 * 
	 * @param title		The title of the position.
	 */
	public Position(String title){
		new Position(title, "", "");
	}
	
	/** Full constructor for position
	 * 
	 * @param title			the Title of the position
	 * @param rateOfPay		the rate of Pay
	 * @param description	the description
	 */
	public Position(String title, String rateOfPay, String description){
		this.title = title;
		this.rateOfPay = rateOfPay;
		this.description = description;
	}
	
	/**
	 * @return	Returns the title of the position
	 */
	public String getTitle() {
		return title;
	}

	/** Sets the title for the position 
	 * @param title		The string title to set for the position
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return	Returns the rate of pay for the position	
	 */
	public String getRateOfPay() {
		return rateOfPay;
	}

	/** Sets the rate of pay for the employee
	 * @param rateOfPay		The string for the rate of pay
	 */
	public void setRateOfPay(String rateOfPay) {
		this.rateOfPay = rateOfPay;
	}
	
	/**
	 * @return	Returns the description of the position
	 */
	public String getDescription() {
		return description;
	}

	/**	Sets the description of the Position
	 * @param description	A string for the description of the Position
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return	returns the key of the position
	 */
	public Long getPositionKey() {
		return positionKey;
	}
}
