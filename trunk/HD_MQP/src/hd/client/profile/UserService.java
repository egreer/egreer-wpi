package hd.client.profile;

import hd.client.entities.Account;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/** Services used for managing the user profile
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {
	
	/** Adds the employee to the database 
	 * 
	 * @param e		The employee to add to the database
	 * @return		Returns the employee, or null if the employee could not be added 
	 */
	public Employee createProfile(Employee e);
	
	/** Retrieves the profile associated with given username
	 * 
	 * @param username	The username to search for
	 * @return			Returns the employee object, null if there is no employee 
	 */
	public Employee fetchProfile(String username);
	
	/** Retrieves the profile associated with given Key
	 * 
	 * @param key	The key for the employee
	 * @return		Returns the employee object, null if there is no employee 
	 */
	public Employee fetchProfile(Long Key);
	
	/** Updates the associated employee in the database
	 * 
	 * @param e		The employee to update (must have the key from the DB)
	 * @return		Returns the employee object with changes, 
	 * 				or null if the changes were not accepted 
	 */
	public Employee updateProfile(Employee e);

	/** Associates an account with a employee 
	 * 
	 * @param p		The account
	 * @param e		The employee (Must contain the ID) 
	 * @return		True if the account was associated, False othervise 
	 */
	public Boolean associateAccountToEmployee(Account p, Employee e);
	
	/** Returns all employees in the database
	 * 
	 * @return 	Returns a collection of all employees 
	 */
	public Collection<Employee> fetchAllEmployees();
	
	/** Returns all positions in the database
	 * 
	 * @return 	Returns a collection of all positions 
	 */
	public Collection<Position> fetchAllPositions();
	
	/** Returns all employees in the database by position
	 * 
	 * @param title		The title of the position to select employees from
	 * @return			Returns a collection of employees with that position
	 * 					Returns an empty collection if position could not be found,
	 * 					or if no employees have that position
	 */
	public Collection<Employee> fetchAllEmployeesByPosition(String title);
	
	/** Returns all employees in the database by position
	 * 
	 * @param position	The position to select employees from (must have key)
	 * @return			Returns a collection of employees with that position
	 * 					Returns an empty collection if position could not be found,
	 * 					or if no employees have that position
	 */
	public Collection<Employee> fetchAllEmployeesByPosition(Position p);
	
	/** Creates the given position in the database
	 * 
	 * @param pos	The position to create
	 * @return		The Position if the position was added to the database,
	 * 				null if the position's title already exists or it 
	 * 					was not added to the database
	 */
	public Position createPosition(Position pos);
	
	/** Retrieves position associated with given title
	 * 
	 * @param title		The title to search for  
	 * @return			Returns the position object, null if there is no position
	 */
	public Position getPosition(String title);
	
	/** Retrieves position associated with given key
	 * 
	 * @param key	The key of the position
	 * @return		Returns the position object with that key, null if it does not exist
	 */
	public Position getPosition(Long key);
	
	/** Updates the position information.
	 * 
	 * @param p		The position with updated information
	 * @return		Returns the updated information
	 */
	public Position updatePosition(Position p);
	
	/** Removes position with the given title
	 * 
	 * @param title		The title of the position to remove
	 * @return			True if the positions was removed
	 * 					False if the position was not found or could not be removed
	 */
	public Boolean removePosition(String title);
	
	/** Removes the given position (must have a key associated with the position)
	 * 
	 * @param p		The position to remove
	 * @return		True if the positions was removed
	 * 				False if the position was not found or could not be removed
	 */
	public Boolean removePosition(Position p);
	
	/** Adds all positions in the collection to the employee 
	 * 
	 * @param e		The employee to add the positions to (must have a Key) 
	 * @param positions		A collection of the keys of the positions to add to the employee 
	 * @return			True if they were added
	 * 					False if they could not be found or could not be added
	 */
	public Boolean addAllPositionsToEmployee(Employee e, Collection<Long> positions);
	
	/** Adds a position to the employee
	 * 
	 * @param e		The employee to add the position to (must have a key)
	 * @param p		The position to add to the employee	(must have a key)
	 * @return		True if the position was added false if could not be found or was not added
	 */
	public Boolean addPositionToEmployee(Employee e, Position p);
	
	/** Removes a position form an employee
	 * 
	 * @param e		The employee to remove the position from (must have a key) 
	 * @param p		The position to remove(must have a key)
	 * @return		True if the position was removed, false if could not be found
	 */
	public Boolean removePositionFromEmployee(Employee e, Position p);
}
