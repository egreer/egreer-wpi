package planningPoker;
/**
 * JRE Planning Poker Rev 1.0
 * @author Jason Codding
 * @author Eric Greer
 * @author Matt Runkle
 */

import java.util.ArrayList;
import java.util.Iterator;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
/**
 * The user story class is used for the storage and manipulation of the use story object
 */
public class UserStory {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long key;
	
	@Persistent
	String title;
	
	@Persistent
	String description;
	
	@Persistent
	String testNotes;
	
	@Persistent
	Long creator;
	

	@Persistent
	//ArrayList<Long> users;
	String users;
	
	@Persistent(mappedBy = "story")
	ArrayList<Estimate> estimate;
	
	@Persistent
	private int estimateNumber; 
	
	@Persistent
	private double totalNumber; 
	
	@Persistent
	private boolean editable;

	@Persistent
	Double finalEstimate; 	//Final estimate null until complete
	
	/**
	 * Default constructor for user story creates a user story with empty string arguments
	 */
	public UserStory() {
		this("","","");
	}
	
	/**
	 * Constructor for user story
	 */
	public UserStory(String title, String description, String testNotes) {
		this.title = title;
		this.description = description;
		this.testNotes= testNotes;
		this.editable = true;
		this.finalEstimate = null;
		//this.users = new ArrayList<Long>();
		this.users = "";
		this.estimate = new ArrayList<Estimate>();
	}
	
	/**
	 * New Constructor for user story
	 */
	public UserStory(String title, String description, String testNotes, Long creator) {
		this.title = title;
		this.description = description;
		this.testNotes= testNotes;
		this.editable = true;
		this.finalEstimate = null;
		//this.users = new ArrayList<Long>();
		this.users = "";
		this.estimate = new ArrayList<Estimate>();
		this.creator = creator;
	}
	
	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title
	 * @return
	 */
	public boolean setTitle(String title) {
		if (this.isEditable()){
			this.title = title;
			return true;
		}else return false;
	}

	/**
	 * @return
	 */
	public String getTestNotes() {
		return testNotes;
	}

	/**
	 * @param testNotes
	 * @return
	 */
	public boolean setTestNotes(String testNotes) {
		if(this.isEditable()){
			this.testNotes = testNotes;
			return true;
		
		}else return false;
	}

	/**
	 * @return
	 */
	public ArrayList<Long> getUsers() {
		//XXX Remove hack
		ArrayList<Long> returner = new ArrayList<Long>();
		String copy = "";
		if (this.users.equals("")) return returner;
		if (users.charAt(users.length()-1) == ' '){
			copy = users.substring(0, users.length()-1);
		}else{
			copy = users;
		}
		if (copy.contains(" ")){
			String[] names = copy.split(" ");
			for(int i = 0 ; i < names.length ; i++){
				returner.add(Long.parseLong(names[i]));
			}
		}else{
			returner.add(Long.parseLong(copy));
		}
		
		
		
		
		return returner;
	}

	/** 
	 * @param user	The user to add to the user story
	 * @return		Returns true if the user 
	 */
	public boolean addUser(User user) {
		if (this.isEditable()){
			this.users += user.getKey().longValue() + " "; //XXX Remove hack
			return true;
		
		}else return false;
	}
	
	/** 
	 * @return		Returns true if the users  
	 */
	public boolean clearUsers() {
		if (this.isEditable()){
			//this.users = new ArrayList<Long>(); XXX remove hack
			this.users = "";
			return true;
		
		}else return false;
	}

	/** 
	 * @param user	The user to add to the user story
	 * @return		Returns true if the user 
	 */
	public boolean removeUser(User user) {
		if (this.isEditable()){
			//return this.users.remove(user.getKey());
			//XXX remove hack
			String copy = "";
			if (this.users.equals("")) return false;
			if (users.charAt(users.length()-1) == ' '){
				copy = users.substring(0, users.length()-1);
			}else{
				copy = users;
			}
				
			
			String[] arrayUsers = copy.split(" ");
			this.users = "";
			boolean returner = false;
			for (int i =0 ; i < arrayUsers.length ; i++ ){
				if(Long.parseLong(arrayUsers[i]) == user.getKey()){
					returner = true;
					continue;
				}
				else this.users += arrayUsers[i];
			}
			
			return returner;		
		}else return false;
	}
	
	/**
	 * @deprecated
	 * @return	Returns a list of all estimate
	 */
	public ArrayList<Estimate> getEstimates() {
		return estimate;
	}
	
	/** Returns a particular estimate in the user story based on a given user 
	 * @deprecated
	 * @param user	The user to retrieve the estimate for
	 * @return		Returns the Estimate for the given user, null if not found.
	 */
	public Estimate getEstimate(User user) {
		Iterator<Estimate> iterator = estimate.iterator();
		while(iterator.hasNext()){
			Estimate temp = iterator.next();
			if (temp.getUserID() == user.getKey()) return temp;
		}
		return null;
	}
	

	/** Adds an estimate to the user story. A user story can only be added if the 
	 * user is assigned to the story 
	 * 
	 * @param estimate The estimate to add to the user story
	 * @return	Returns true if the estimate was added to the user story
	 */
	public boolean addEstimate(Estimate estimate) {
		boolean returner = false;
		//If the given estimate is for a user in the story then add the estimate  
		if (this.getUsers().contains(estimate.getUserID())){
			if(estimate.getEstimate() <= 0) return false;
			//TODO If the user already has an estimate overwrite their estimate
			returner = this.estimate.add(estimate);
			if(returner){ 
				this.estimateNumber++; 
				this.totalNumber += estimate.getEstimate();
			}
			
			//If this was editable before it no longer is 
			if(this.isEditable()) this.editable = false;
			
			//If all estimates have been added then generate final estimate
			String copy = "";
			if (users.charAt(users.length()-1) == ' '){
				copy = users.substring(0, users.length()-1);
			}else{
				copy = users;
			}
			int size = 1;
			if (copy.contains(" ")) size = copy.split(" ").length;
					
			if(size == this.estimateNumber) this.generateEstimate(); //XXX remove hack
			
			return returner;
		
		}else{
			return returner;
		}
	}

	/**
	 * @return	If there have been no estimates then the story is editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @return	The database key for the User Story
	 */
	public Long getKey(){
		return key; 
	}

	/**
	 * @return	The description of the user story
	 */
	public String getDescription() {
		return description;
	}


	/** Sets the description if the user story is editable
	 * 
	 * @param description the description to set for the user story
	 * @return
	 */
	public boolean setDescription(String description) {
		if(this.isEditable()){
			this.description = description;
			return true;
		
		}else return false;
	}

	/**
	 * @return	Returns null if the story is not done, else returns the final estimate
	 */
	public Double getFinalEstimate() {
		return finalEstimate;
	}
	
	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}
	
	/**
	 * @param	User to see if they can fill out an evaluation 
	 * @return	Returns null if the story is not done, else returns the final estimate
	 */
	public boolean containsUser(User user) {
		if (user == null) return false;
		return this.getUsers().contains(user.getKey());
	}
	
	private void generateEstimate(){
		Double tempEstimate = 0D;
		tempEstimate = this.totalNumber;
		/* TOD get relationship right so this works.
		 * Iterator<Estimate> iterator = estimate.iterator();
		while (iterator.hasNext()){
			Double temp = iterator.next().getEstimate();
			tempEstimate += temp;
		}
		*/
		finalEstimate =  tempEstimate / (1.0 * estimate.size());
	}
	
}
