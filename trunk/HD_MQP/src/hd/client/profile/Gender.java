package hd.client.profile;

/** Gender enumeration for Employees
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
public enum Gender{
	MALE("Male"), FEMALE("Female");
	private String gender;
	
	Gender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return gender;
	}

};
