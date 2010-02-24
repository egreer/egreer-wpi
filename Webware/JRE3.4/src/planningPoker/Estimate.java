package planningPoker;
/**
 * JRE Planning Poker Rev 1.0
 * @author Jason Codding
 * @author Eric Greer
 * @author Matt Runkle
 */

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
/**
 * The estimate class is used to manipulate and store the estimate object that is associated with each user story
 */
public class Estimate {

	public Estimate(Long userID, Double estimate) {
		this.userID = userID;
		this.estimate = estimate;
	}

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private UserStory story;


	public UserStory getStory() {
		return story;
	}

	@Persistent
	Long userID;

	@Persistent
	Double estimate;


	/**
	 * @return	Returns the user ID for this estimate
	 */
	public Long getUserID() {
		return userID;
	}


	/**
	 * @param user	The user to set for the estimate 
	 */
	public boolean setUser(User user) {
		this.userID = user.getKey();
		return true;
	}


	/**
	 * @return	Returns the numerical value of the ATC
	 */
	public Double getEstimate() {
		return estimate;
	}


	/**
	 * @param estimate	The numerical value to set for this estimate
	 * @return			Returns true if the estimate is set
	 */
	public boolean setEstimate(Double estimate) {
		this.estimate = estimate;
		return true;
	}

	/**
	 * @return	The database key for the Estimate
	 */
	public Key getKey(){
		return key; 
	}

}
