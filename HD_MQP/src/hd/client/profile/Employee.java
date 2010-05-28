package hd.client.profile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/** The Employee object contains employment, contact, and Positions
 * information about an employee.
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Employee implements Serializable{

	@Persistent
	private static final long serialVersionUID = 56L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long employeeKey;
	
	@Persistent
	String idnum;
	
	@Persistent
	String email;
	
	@Persistent
	String firstname;

	@Persistent
	String lastname;
	
	@Persistent
	boolean isWorkStudy;
	
	@Persistent
	Long[] positions = new Long[5];
	
	@Persistent
	Date hireDate;
	
	@Persistent
	String specialTraining;
	
	@Persistent
	String goals;
	
	@Persistent
	int ranking;
	
	@Persistent
	String comments;
	
	@Persistent
	Long applicationID;
	
	@Persistent
	String fiveStarTickets;

	@Persistent
	String nickname;

	@Persistent
	Date dateOfBirth;

	@Persistent
	String homePhone;
	
	@Persistent
	String cellPhone;	
	
	@Persistent
	Gender gender;

	@Persistent
	String localAddress;

	@Persistent
	String homeAddress;

	@Persistent
	String mailBox;

	@Persistent
	Size shirtSize;

	@Persistent
	String socialNetworks; 
	
	/**
	 * Constructor for an employee object.
	 *  Defaults 	gender to male
	 *  			work study status to federal funding
	 */
	public Employee(){
		gender = Gender.MALE;
		shirtSize = Size.U; 
		isWorkStudy = true;
	}
	
	/**
	 * @return True is federal funding, False is department funds
	 */
	public boolean isWorkStudy() {
		return isWorkStudy;
	}

	/**Sets the employees work study status.
	 * True is federal funding
	 * False is department funds
	 * 
	 * @param isWorkStudy 	A boolean value for funding source
	 */
	public void setWorkStudy(boolean isWorkStudy) {
		this.isWorkStudy = isWorkStudy;
	}


	/**
	 * @return Returns the hire date of the employee
	 */
	public Date getHireDate() {
		return hireDate;
	}

	/** Sets the hire date of the employee
	 * @param hireDate	The date of hire for the employee
	 */
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	/**
	 * @return Returns any special training the employee has
	 */
	public String getSpecialTraining() {
		return specialTraining;
	}

	/**Sets the special training for the employee 
	 * @param specialTraining	String value for the special training
	 */
	public void setSpecialTraining(String specialTraining) {
		this.specialTraining = specialTraining;
	}

	/**
	 * @return returns the goals of the employee
	 */
	public String getGoals() {
		return goals;
	}

	/** Sets the goals for the employee
	 * @param goals	String value of the goals for the employee
	 */
	public void setGoals(String goals) {
		this.goals = goals;
	}

	/**
	 * @return	Returns the individual employees ranking at the Helpdesk
	 */
	public int getRanking() {
		return ranking;
	}

	/** Sets the ranking of the for the employee 
	 * @param ranking	Sets the ranking of the employee at the Helpdesk
	 */
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	/**
	 * @return returns any comments about the employee
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments	String if comments about the employee 
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return Returns the application's key for the employee if any
	 */
	public Long getApplicationID() {
		return applicationID;
	}

	/** Sets the application ID for the employee
	 * @param applicationID	Sets the application's key for the employee if any
	 */
	public void setApplicationID(long applicationID) {
		this.applicationID = applicationID;
	}

	/**
	 * @return	Returns the five star ticket information for the employee if any
	 */
	public String getFiveStarTickets() {
		return fiveStarTickets;
	}

	/**Sets the five star ticket information for the employee 
	 * @param fiveStarTickets	a String of five star ticket information for the employee 
	 */
	public void setFiveStarTickets(String fiveStarTickets) {
		this.fiveStarTickets = fiveStarTickets;
	}

	/**
	 * @return	returns the Key of the employee object
	 */
	public Long getEmployeeKey() {
		return employeeKey;
	}

	/**
	 * @return	returns the first name of the employee
	 */
	public String getFirstName() {
		return firstname;
	}

	/** Sets the first name of the employee 
	 * @param firstname		A string representing the first name
	 */
	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	
	/**
	 * @return	returns the employee's last name
	 */
	public String getLastName() {
		return lastname;
	}
	
	/** Sets the employee's last name
	 * @param lastname	A string representing the last name	
	 */
	public void setLastName(String lastname) {
		this.lastname = lastname;
	}
	
	@Override
	public String toString() {
		return "ID: "+ this.employeeKey + " Name: " + firstname + " Hire Date: " + hireDate.toString();
	}
	
	/**
	 * @return	Returns the employee's nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/** Sets the employee's nickname
	 * @param nickname	A string representing the employee's nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return returns the birthday of the employee
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/** Sets the employee date of birth
	 * @param dateOfBirth	The employee's date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return Returns the gender of the employee
	 */
	public Gender getGender() {
		return gender;
	}

	/** Sets the employee's gender
	 * @param gender	A gender enum  
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return returns the local address of the employee
	 */
	public String getLocalAddress() {
		return localAddress;
	}

	/** Sets the local address of the employee
	 * @param localAddress	The local address of the employee
	 */
	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}
	
	/**
	 * @return returns the Home address of the employee
	 */
	public String getHomeAddress() {
		return homeAddress;
	}

	/** Sets the home address of the employee
	 *
	 * @param homeAddress	The home address of the employee
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	/**
	 * @return	Returns the employee's mailbox number on campus
	 */
	public String getMailBox() {
		return mailBox;
	}

	/** Sets the mailbox of the employee
	 * 
	 * @param mailBox	The mailbox of the employee 
	 */
	public void setMailBox(String mailBox) {
		this.mailBox = mailBox;
	}

	/**
	 * @return Returns the Shirt Size
	 */
	public Size getShirtSize() {
		return shirtSize;
	}

	/** Sets the shirt size of the employee
	 * 
	 * @param shirtSize	The shirt size of the employee
	 */
	public void setShirtSize(Size shirtSize) {
		this.shirtSize = shirtSize;
	}
	
	/**
	 * @return	Returns the home phone number 
	 */
	public String getHomePhone() {
		return homePhone;
	}

	/** Sets the employee's home phone number
	 * Is validated to be in the form of:
	 * 	1-999-585-4009 || 999-585-4009 || 1-585-4009 || 585-4009
	 * @param homePhone	The home phone number of the employee
	 * @return			Returns true if the home phone number was set 
	 */
	public boolean setHomePhone(String homePhone) {
		boolean returner = isPhoneValid(homePhone);
		if (returner){
			this.homePhone = homePhone;
		}
		return returner;
	}

	
	/**
	 * @return	Returns the employees cellphone
	 */
	public String getCellPhone() {
		return cellPhone;
	}
	
	/** Sets the employee's cell phone number
	 * Is validated to be in the form of:
	 * 	1-999-585-4009 || 999-585-4009 || 1-585-4009 || 585-4009
	 * @param homePhone	The cell phone number of the employee
	 * @return			Returns true if the home phone number was set 
	 */
	public boolean setCellPhone(String cellPhone) {
		boolean returner = isPhoneValid(cellPhone);
		if (returner){
			this.cellPhone = cellPhone;
		}
		return returner;		
	}

	/**
	 * @return	Returns information about the employee's social networks
	 */
	public String getSocialNetworks() {
		return socialNetworks;
	}

	/** Sets the social networks of the employee
	 * @param socialNetworks	The social networks of the employee
	 */
	public void setSocialNetworks(String socialNetworks) {
		this.socialNetworks = socialNetworks;
	}

	/**
	 * @return	Returns a list of the keys for positions
	 */
	public ArrayList<Long> getPositionsKeys() {
		ArrayList<Long> returner = new ArrayList<Long>();
		for(Long p : positions){
			if (p != null) returner.add(p);
		}
		return returner;
	}

	/** Sets the positions of employees
	 * @param positions	A collection of positions
	 */
	public void setPositions(Collection<Position> positions) {
		this.positions = new Long[10];
		Iterator<Position> iterator = positions.iterator();
		while (iterator.hasNext()){
			this.addPosition(iterator.next());
		}
	}
	
	/** Sets the positions of an employee
	 * @param positions	An array of positions	
	 */
	public void setPositions(Position[] positions) {
		this.positions = new Long[10];
		int j = 0;
		for(int i = 0; i < positions.length ; i++){
			if( positions[i] != null){
				this.addPosition(positions[i]);
				j++;
			}
		}
	}
	
	/**Adds an individual position to the employee 
	 * @param p		The position to add to the employee
	 * @return		Returns True if the position was added
	 */
	public boolean addPosition(Position p) {
		if (positions == null) positions = new Long[5];
		if (!this.hasPosition(p)){
			Long key = p.getPositionKey();
			
			for (int i = 0 ; i < positions.length ; i++ ){
				if (positions[i] == null){
					positions[i] = key;
					return true;
				}
			}
			//If the array is not long enough
			Long[] temp = new Long[positions.length*2];
			int j = 0;
			for (;j < positions.length ; j++){
				temp[j] = positions[j];
			}
			temp[j] = key;
			positions = temp;
			return true;
			
		}
		return true;
	}
	
	/** Removes a particular position of the employee 
	 * 
	 * @param p	The position to remove
	 * @return	Returns true if the position is removed, 
	 * 			false if not on the employee or if it could not be removed  
	 */
	public boolean removePosition(Position p) {
		if (this.hasPosition(p)){
			Long key = p.getPositionKey();
			for (int i = 0 ; i < positions.length ; i++ ){
				if (positions[i] == key){
					positions[i] = null;
					return true;
				}
			}
		}
		return false;
	}
	
	/** Returns the WPI ID number of the employee
	 * @return	Returns the employee number of the employee
	 */
	public String getIdNumber() {
		return idnum;
	}

	/**  Sets the WPI ID number of the employee
	 * @param idnum		The ID number of the employee 
	 */
	public void setIdNumber(String idnum) {
		this.idnum = idnum;
	}

	/**
	 * @return	 The employee's email address
	 */
	public String getEmail() {
		return email;
	}

	/** Sets the employee's email address
	 * @param email		The email address of the employee
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** Tells if the user has a particular position
	 * 
	 * @param p		The position to check
	 * @return		True if the user has that position, false otherwise;
	 */
	public boolean hasPosition(Position p) {
		if (p == null) return false;
		else {
			Long key = p.getPositionKey();
			for (int i = 0 ; i < positions.length ; i++ ){
				if (positions[i] == key) return true;
			}
			return false;
		}
	}
	
	/** Validates that number is one of these forms:   
	 * 1-999-585-4009 || 999-585-4009 || 1-585-4009 || 585-4009
	 * 
	 * @param phone	The phone number to validate 
	 * @return		true if the number is valid
	 */
	public static boolean isPhoneValid(String phone) {
	    String phoneNumberPattern = "(\\d-)?(\\d{3}-)?\\d{3}-\\d{4}";
	    return phone.matches(phoneNumberPattern);
	}
	
}
