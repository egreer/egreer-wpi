package planningPoker;
/**
 * JRE Planning Poker Rev 1.0
 * @author Jason Codding
 * @author Eric Greer
 * @author Matt Runkle
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
/**
 * The user class story manager class is used for the manipulation of user story objects
 */
public class UserStoryManager {	

	/**
	 * User Story Creator
	 * @param title the title of the user story
	 * @param description the description of the user story
	 * @param testNotes the optional test notes provided with the user story
	 * @param usernames the usernames of the people that should be edited
	 * @return the newly created user story object
	 * @deprecated no longer needed
	 */
	public static UserStory createUserStory(String title, String description, String testNotes, String[] usernames){
		UserStory newUserStory = new UserStory(title, description, testNotes);

		//add usernames
		int numberOfUsers = usernames.length;
		while (numberOfUsers > 0){
			User newUser = UserManager.getUser(usernames[numberOfUsers -1]);
			newUserStory.addUser(newUser);
			numberOfUsers--;
		}

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try
		{
			tx.begin();
			pm.makePersistent(newUserStory);
			tx.commit();
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
		}
		//pm.detachCopy(newUserStory);
		pm.close();
		return newUserStory;
	}

	/** 
	 * User Story Creator 2
	 * Version 2.0
	 * @param title the title of the user story
	 * @param description the description of the user story
	 * @param testNotes the optional test notes provided with the user story
	 * @param usernames the usernames of the people that should be edited
	 * @param creator the username of the creator
	 * @return the newly created user story object
	 */
	public static UserStory createUserStory(String title, String description, String testNotes, String[] usernames, String creator){
		User theCreator = UserManager.getUser(creator);
		if (theCreator == null) return null;
		UserStory newUserStory = new UserStory(title, description, testNotes, theCreator.getKey());

		//add usernames
		if (usernames != null){
			
		int numberOfUsers = usernames.length;
		while (numberOfUsers > 0){
			User newUser = UserManager.getUser(usernames[numberOfUsers -1]);
			newUserStory.addUser(newUser);
			numberOfUsers--;
		}
		}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try
		{
			tx.begin();
			pm.makePersistent(newUserStory);
			tx.commit();
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
		}
		//pm.detachCopy(newUserStory);
		pm.close();
		return newUserStory;
	}


	/**
	 * User Story Editor
	 * @param key user story that identifier for user story being edited
	 * @param title new title of user story
	 * @param description new description of user story
	 * @param testNotes new test notes for user story
	 * @param usernames new usernames associated with user story
	 * @return the edited the user story object
	 * @deprecated no longer needed
	 */
	public static boolean editUserStory(Long key, String title, String description, String testNotes, String[] usernames){
		boolean returner = true;
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Transaction tx = pm.currentTransaction();
		UserStory theUserStory;

		try {
			tx.begin();
			theUserStory = getUserStory(key);
			if (theUserStory.isEditable()){
				theUserStory.setTitle(title);
				theUserStory.setDescription(description);
				theUserStory.setTestNotes(testNotes);
				theUserStory.clearUsers();

				int numberOfUsers = usernames.length;
				while (numberOfUsers > 0){
					User newUser = UserManager.getUser(usernames[numberOfUsers-1]);
					theUserStory.addUser(newUser);
					numberOfUsers--;
				}
			}
			pm.makePersistent(theUserStory);
			tx.commit();
		}finally{
			if (tx.isActive()){
				tx.rollback();
				returner = false;
			}
		}

		pm.close();
		return returner;

	}

	/**
	 * User Story Editor 2
	 * Version 2.0
	 * @param key user story that identifier for user story being edited
	 * @param title new title of user story
	 * @param description new description of user story
	 * @param testNotes new test notes for user story
	 * @param usernames new usernames associated with user story
	 * @param editor the username of the editor (should be the same as the creator of the user story)
	 * @return 	true if edits were valid and successful
	 * 			false if edits were not valid / or person was not the creator
	 */
	public static boolean editUserStory(Long key, String title, String description, String testNotes, String[] usernames, String editor){
		boolean returner = true;
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Transaction tx = pm.currentTransaction();
		UserStory theUserStory;
		User theEditor = UserManager.getUser(editor);
		if (theEditor == null){
			returner = false;
		}else{
			try {
				tx.begin();
				theUserStory = getUserStory(key);
				if (theUserStory != null && theUserStory.isEditable() && theEditor.getKey().equals(theUserStory.getCreator())){
					theUserStory.setTitle(title);
					theUserStory.setDescription(description);
					theUserStory.setTestNotes(testNotes);
					theUserStory.clearUsers();
					
					if (usernames == null){
						returner &= theUserStory.clearUsers();
					}else{
					int numberOfUsers = usernames.length;
					while (numberOfUsers > 0){
						User newUser = UserManager.getUser(usernames[numberOfUsers-1]);
						theUserStory.addUser(newUser);
						numberOfUsers--;
					}
					}
					pm.makePersistent(theUserStory);
				}else{
					returner = false;
				}
				
				tx.commit();
			}finally{
				if (tx.isActive()){
					tx.rollback();
					returner = false;
				}
			}
		}		
		pm.close();
		return returner;

	}


	@SuppressWarnings("unchecked")
	/**
	 * Retriever all user stories
	 * @return a collection of user stories
	 */
	public static Collection<UserStory> retrieveAllUserStories(){

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(UserStory.class);
		List<UserStory> results = (List<UserStory>) query.execute();


		if (results == null){
			return new ArrayList<UserStory>();
		}	

		pm.detachCopyAll(results);	
		pm.close();
		return results;

	}


	/**
	 * Retrieves all user stories for a given username
	 * @param username the username for which the associted user stories will be returned
	 * @return the collection of user stories associated with the given username
	 */
	public static Collection <UserStory> retrieveAllUserStories(String username){
		User user = UserManager.getUser(username);

		Collection<UserStory> all = retrieveAllUserStories();
		Collection<UserStory> results = new ArrayList<UserStory>();

		Iterator<UserStory> iterator = all.iterator(); 

		while (iterator.hasNext()){
			UserStory temp = iterator.next();
			if (temp.containsUser(user)) results.add(temp);
		}	

		return results;

	}	

	/**
	 * Retrieves all user stories owned by a given username
	 * @param username the username for which the associted user stories will be returned
	 * @return the collection of user stories associated with the given username
	 */
	public static Collection <UserStory> retrieveOwnedUserStories(String username){
		User user = UserManager.getUser(username);

		Collection<UserStory> all = retrieveAllUserStories();
		Collection<UserStory> results = new ArrayList<UserStory>();

		Iterator<UserStory> iterator = all.iterator(); 

		while (iterator.hasNext()){
			UserStory temp = iterator.next();
			if (temp.getCreator().equals(user.getKey())) results.add(temp);
		}	

		return results;

	}	

	

	@SuppressWarnings("unchecked")
	/**
	 * @param key user story associated with estimate
	 * @param username username associated with estimate
	 * @return Estimate an estimate object associated with given username and story key
	 */
	public static Estimate retrieveEstimate(Long key, String username){
		User theUser = UserManager.getUser(username);
		UserStory theUserStory = getUserStory(key);

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query query = pm.newQuery(Estimate.class);
		List<Estimate> results = (List<Estimate>) query.execute();
		Estimate returner = null;

		if (results == null) return returner;
		Iterator<Estimate> iterator = results.iterator();
		while(iterator.hasNext()){
			Estimate temp = iterator.next(); 
			if (temp.getStory().getKey() == theUserStory.getKey() && temp.getUserID() == theUser.getKey()){
				returner = temp;
				break;
			}
		}

		pm.detachCopy(returner);
		pm.close();

		return returner;
	}


	/**
	 * Creates an estimate object
	 * @param storyKey the story the estimate is for
	 * @param username the username of the user making the estimate
	 * @param value the value of the estimate being made
	 * @return boolean of success
	 */
	public static boolean createEstimate(Long storyKey, String username, Double value){
		boolean returner = false;
		User theUser = UserManager.getUser(username);
		Long userKey = theUser.getKey();

		Estimate newEstimate = new Estimate (userKey, value);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserStory theUserStory = pm.getObjectById(UserStory.class, storyKey);


		if (theUserStory == null || retrieveEstimate(storyKey,username) == null){
			returner = theUserStory.addEstimate(newEstimate);
			pm.makePersistent(newEstimate);
			pm.makePersistent(theUserStory);
		}

		pm.close();

		return returner;

	}

	/**
	 * Removes a user story
	 * @param key the user story to be removed
	 * @return true when delete completed
	 * @deprecated no longer needed
	 */
	public static boolean removeUserStory(Long key){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserStory theStory = pm.getObjectById(UserStory.class, key);
		pm.deletePersistent(theStory);
		pm.close();
		return true;
	}

	/**
	 * Removes a user story 2
	 * Version 2.0
	 * @param key the user story to be removed
	 * @param deletor the person requesting the delete
	 * @return true when delete completed
	 */
	public static boolean removeUserStory(Long key, String deletor){
		boolean returner = false;
		User theDeletor = UserManager.getUser(deletor);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserStory theStory = pm.getObjectById(UserStory.class, key);
		
		if(theDeletor != null && theStory != null && theStory.getCreator().equals(theDeletor.getKey())){
			pm.deletePersistent(theStory);
			returner = true;
		}
		pm.close();
		return returner;
	}	

	/**
	 * Retrieves a user story
	 * @param key the key of the desired user story
	 * @return the story object that was requested
	 */
	public static UserStory getUserStory(Long key){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserStory theStory = pm.getObjectById(UserStory.class, key);
		pm.detachCopy(theStory);
		pm.close();
		return  theStory;

	}


	/**
	 * Gets all story usernames
	 * @param key the story for which the usernames are being requested
	 * @return a string collection with the desired usernames
	 */
	public static Collection<String> getAllStoryUsernames(Long key){
		ArrayList<String> returnUsers = new ArrayList<String>();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserStory theStory = getUserStory(key);
		ArrayList<Long> theUsers = null;

		if(theStory != null){
			theUsers = theStory.getUsers();


			Iterator<Long> userKeys = theUsers.iterator();

			while (userKeys.hasNext()){
				User temp = pm.getObjectById(User.class, userKeys.next());
				if (temp != null) returnUsers.add(temp.getUserName());	
			}
		}

		pm.close();	

		return returnUsers;
	}	
	
	/**
	 * Verifies if a given username is the user on the provided story
	 * @param theStory the user story to check the creator
	 * @param username the username used to check if it is the creator
	 * @return true if it is creator, false if not
	 */
	public static Boolean isCreator(UserStory theStory, String username){
		User theUser = UserManager.getUser(username);
		if (theUser != null && theStory.getCreator().equals(theUser.getKey())){
			return true;
			
		}else return false;
	}

	/**
	 * Checks if a a given user is an estimator for that story
	 * @param theStory the story being estimated
	 * @param username the user making the estimater
	 * @return true if if an estimater, false if not
	 */
	public static Boolean isEstimator(UserStory theStory, String username){
		User theUser = UserManager.getUser(username);
		if (theUser != null && theStory.containsUser(theUser)){
			return true;
			
		}else return false;
	}


}