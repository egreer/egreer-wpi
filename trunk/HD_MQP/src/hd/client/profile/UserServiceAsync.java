package hd.client.profile;

import hd.client.entities.Account;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;

/** Asycronous interface for the UserService
 * @see UserService
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
public interface UserServiceAsync {
	public void createProfile(Employee e, AsyncCallback<Employee> callback);
	public void fetchProfile(String username, AsyncCallback<Employee> callback);
	public void fetchProfile(Long Key, AsyncCallback<Employee> callback);
	public void updateProfile(Employee e, AsyncCallback<Employee> callback);
	public void associateAccountToEmployee(Account p, Employee e, AsyncCallback<Boolean> callback);
	public void fetchAllPositions(AsyncCallback<Collection<Position>> callback);
	public void fetchAllEmployees(AsyncCallback<Collection<Employee>> callback);
	public void fetchAllEmployeesByPosition(String Position, AsyncCallback<Collection<Employee>> callback);
	public void fetchAllEmployeesByPosition(Position p, AsyncCallback<Collection<Employee>> callback);
	public void createPosition(Position pos, AsyncCallback<Position> callback);
	public void getPosition(String title, AsyncCallback<Position> callback);
	public void getPosition(Long key, AsyncCallback<Position> callback);
	public void updatePosition(Position p, AsyncCallback<Position> callback);
	public void removePosition(String title, AsyncCallback<Boolean> callback);
	public void removePosition(Position p, AsyncCallback<Boolean> callback);
	public void addAllPositionsToEmployee(Employee e, Collection<Long> positions, AsyncCallback<Boolean> callback);
	public void addPositionToEmployee(Employee e, Position p, AsyncCallback<Boolean> callback);
	public void removePositionFromEmployee(Employee e, Position p, AsyncCallback<Boolean> callback);
}
