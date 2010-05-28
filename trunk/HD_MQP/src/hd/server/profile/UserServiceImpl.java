package hd.server.profile;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import hd.client.entities.Account;
import hd.client.profile.Employee;
import hd.client.profile.Position;
import hd.client.profile.UserService;
import hd.server.LoginServiceImpl;
import hd.server.db.PMF;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/** Server Implementation of the UserService 
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class UserServiceImpl extends RemoteServiceServlet implements UserService{

	/** Auto generated Serial key */
	private static final long serialVersionUID = -5036468268667792269L;

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#updateProfile(hd.client.profile.Employee)
	 */
	@Override
	public Employee updateProfile(Employee e) {
		//TODO authorization
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Employee temp = null;

		try{
			temp = pm.getObjectById(Employee.class, e.getEmployeeKey());
		}catch (JDOObjectNotFoundException ex){
			//Handled above
		}

		//TODO There has to be a better way
		if (temp != null){
			if (e.getApplicationID() != null) temp.setApplicationID(e.getApplicationID());
			if (e.getIdNumber() != null)temp.setIdNumber(e.getIdNumber());
			if (e.getFirstName() != null)temp.setFirstName(e.getFirstName());
			if (e.getLastName() != null)temp.setLastName(e.getLastName());
			if (e.getNickname() != null)temp.setNickname(e.getNickname());
			if (e.getDateOfBirth() != null)temp.setDateOfBirth(e.getDateOfBirth());
			if (e.getGender() != null)temp.setGender(e.getGender());
			if (e.getShirtSize() != null)temp.setShirtSize(e.getShirtSize());
			if (e.getEmail() != null)temp.setEmail(e.getEmail());
			if (e.getCellPhone() != null)temp.setCellPhone(e.getCellPhone());
			if (e.getMailBox() != null)temp.setMailBox(e.getMailBox());
			if (e.getLocalAddress() != null)temp.setLocalAddress(e.getLocalAddress());
			if (e.getHomePhone() != null)temp.setHomePhone(e.getHomePhone());
			if (e.getHomeAddress() != null)temp.setHomeAddress(e.getHomeAddress());
			if (e.getSocialNetworks() != null)temp.setSocialNetworks(e.getSocialNetworks());

			if (e.getHireDate() != null)temp.setHireDate(e.getHireDate());
			temp.setWorkStudy(e.isWorkStudy());
			if (e.getSpecialTraining() != null)temp.setSpecialTraining(e.getSpecialTraining());
			if (e.getGoals() != null)temp.setGoals(e.getGoals());
			if (e.getRanking() > 0) temp.setRanking(e.getRanking());
			if (e.getComments() != null)temp.setComments(e.getComments());
			if (e.getFiveStarTickets() != null)temp.setFiveStarTickets(e.getFiveStarTickets());

			//Try to commit to database
			Transaction tx = pm.currentTransaction();
			try	{
				tx.begin();

				pm.makePersistent(temp);

				tx.commit();
			}
			finally	{
				if (tx.isActive()){
					tx.rollback();
					temp = null;
				}
			}


		}

		pm.close();

		return temp;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#fetchAllPositions()
	 */
	@Override
	public Collection<Position> fetchAllPositions() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<Position> returner = new ArrayList<Position>();
		Extent<Position> positions = pm.getExtent(Position.class);

		Iterator<Position> iterator = positions.iterator();
		while (iterator.hasNext()){
			returner.add(iterator.next());
		}

		return (Collection<Position>) returner;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#fetchAllEmployees()
	 */
	@Override
	public Collection<Employee> fetchAllEmployees() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<Employee> returner = new ArrayList<Employee>();
		Extent<Employee> employees = pm.getExtent(Employee.class);

		Iterator<Employee> iterator = employees.iterator();
		while (iterator.hasNext()){
			returner.add(iterator.next());
		}
		pm.close();
		return (Collection<Employee>) returner;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#fetchAllEmployeesByPosition(java.lang.String)
	 */
	@Override
	public Collection<Employee> fetchAllEmployeesByPosition(String title) {
		Position p = getPosition(title);
		if (p != null){
			Collection<Employee> allEmps = fetchAllEmployees();
			Iterator<Employee> iterator = allEmps.iterator();
			while(iterator.hasNext()){
				Employee e = iterator.next();
				if (e.hasPosition(p))continue;
				else iterator.remove();
			}
			return allEmps;
		}else return (Collection<Employee>) new ArrayList<Employee>();
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#fetchAllEmployeesByPosition(hd.client.profile.Position)
	 */
	@Override
	public Collection<Employee> fetchAllEmployeesByPosition(Position p) {
		if (p != null){
			Collection<Employee> allEmps = fetchAllEmployees();
			Iterator<Employee> iterator = allEmps.iterator();
			while(iterator.hasNext()){
				Employee e = iterator.next();
				if (e.hasPosition(p))continue;
				else iterator.remove();
			}
			return allEmps;
		}else return (Collection<Employee>) new ArrayList<Employee>();
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#fetchProfile(java.lang.String)
	 */
	@Override
	public Employee fetchProfile(String username) {
		Account a = new Account(username, null); 
		System.out.println("Fetching profile");
		Account returnedAccount = LoginServiceImpl.getAccount(a);

		if (returnedAccount == null){
			System.out.println("No Account Found");
			return null;
		}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Employee temp = null;

		try{
			temp = pm.getObjectById(Employee.class, returnedAccount.getEmployeeID());
		}catch (JDOObjectNotFoundException e){
			//Handled above
		}

		pm.close();
		System.out.println("Returning: " + temp.getEmployeeKey() + temp.getFirstName() + temp.getNickname() + temp.getCellPhone());
		return temp;
	}
	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#fetchProfile(java.lang.Long)
	 */
	@Override
	public Employee fetchProfile(Long Key) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Employee temp = null;

		try{
			temp = pm.getObjectById(Employee.class, Key);
		}catch (JDOObjectNotFoundException e){
			//Handled above
		}

		pm.close();
		return temp;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#createProfile(hd.client.profile.Employee)
	 */
	@Override
	public Employee createProfile(Employee e) {
		//TODO Authorization for action
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		//Try to add to database
		try	{
			tx.begin();

			pm.makePersistent(e);

			tx.commit();
		}
		finally	{
			if (tx.isActive()) {
				tx.rollback();
				e = null;
			}
		}

		pm.close();
		return e;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#getPosition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Position getPosition(String title) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Position.class,
		"title == titleParam");
		query.declareParameters("String titleParam");

		List<Position> results = (List<Position>) query.execute(title);

		Position temp = null;

		if (!results.isEmpty()){
			temp = results.get(0);
		}

		pm.close();

		return temp;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#getPosition(java.lang.Long)
	 */
	@Override
	public Position getPosition(Long key) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Position temp = null;

		try{
			temp = pm.getObjectById(Position.class, key);
		}catch (JDOObjectNotFoundException e){
			//Handled above
		}

		pm.close();
		return temp;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#removePosition(java.lang.String)
	 */
	@Override
	public Boolean removePosition(String title) {
		//TODO Authorization
		Position retrieved = this.getPosition(title);

		if (retrieved != null){
			return this.removePosition(retrieved);
		}
		else return false;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#removePosition(hd.client.profile.Position)
	 */
	@Override
	public Boolean removePosition(Position p) {
		//TODO Authorization
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Position retrieved = null;

		try{
			retrieved = pm.getObjectById(Position.class, p.getPositionKey());
		}catch(JDOObjectNotFoundException e){
			//Handled above
		}

		if (retrieved != null){
			pm.deletePersistent(retrieved);
			return true;
		}
		else return false;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#updatePosition(hd.client.profile.Position)
	 */
	@Override
	public Position updatePosition(Position p) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		Position temp = this.getPosition(p.getPositionKey());


		if (temp != null){
			//update information
			//TODO there has to be a better way
			temp.setTitle(p.getTitle());
			temp.setRateOfPay(p.getRateOfPay());
			temp.setDescription(p.getDescription());

			//Try to add to database
			try	{
				tx.begin();
				pm.makePersistent(temp);
				tx.commit();
			}
			finally	{
				if (tx.isActive())	{
					tx.rollback();
					temp = null;
				}
			}
		}
		pm.close();

		return temp;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#createPosition(hd.client.profile.Position)
	 */
	@Override
	public Position createPosition(Position pos) {
		// TODO Authorization for action

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Position returner = null;

		//Check to make sure the title doesn't exist
		if ( this.getPosition(pos.getTitle()) == null){

			Transaction tx = pm.currentTransaction();

			//Try to add to database
			try {
				tx.begin();

				returner = pm.makePersistent(pos);

				tx.commit();
			}
			finally	{
				if (tx.isActive()) {
					tx.rollback();
					returner = null;
				}
			}
		}
		pm.close();
		return returner;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#addPositionToEmployee(hd.client.profile.Employee, hd.client.profile.Position)
	 */
	@Override
	public Boolean addPositionToEmployee(Employee e, Position p) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Boolean returner = Boolean.FALSE;

		Employee returnedEmp = this.fetchProfile(e.getEmployeeKey());
		Position returnedPos = this.getPosition(p.getPositionKey());

		if (returnedEmp != null && returnedPos != null){
			boolean b = returnedEmp.addPosition(returnedPos);
			if (b){
				//Try to add to database
				try	{
					tx.begin();
					pm.makePersistent(returnedEmp);
					returner = Boolean.TRUE;
					tx.commit();
				}
				finally	{
					if (tx.isActive())	{
						tx.rollback();
						returner = Boolean.FALSE;
					}
				}
			}
		}
		pm.close();
		return returner;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#removePositionFromEmployee(hd.client.profile.Employee, hd.client.profile.Position)
	 */
	@Override
	public Boolean removePositionFromEmployee(Employee e, Position p) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Boolean returner = Boolean.FALSE;

		Employee returnedEmp = this.fetchProfile(e.getEmployeeKey());
		Position returnedPos = this.getPosition(p.getPositionKey());

		if (returnedEmp != null && returnedPos != null){
			boolean b = returnedEmp.removePosition(returnedPos);
			if (b){
				//Try to add to database
				try
				{
					tx.begin();
					pm.makePersistent(returnedEmp);
					returner = Boolean.TRUE;
					tx.commit();
				}
				finally	{
					if (tx.isActive())	{
						tx.rollback();
						returner = Boolean.FALSE;
					}
				}
			}
		}
		pm.close();
		return returner;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#associateAccountToEmployee(hd.client.entities.Account, hd.client.profile.Employee)
	 */
	@Override
	public Boolean associateAccountToEmployee(Account p, Employee e) {
		Boolean returner = Boolean.FALSE;

		Account a = LoginServiceImpl.getAccount(p);

		Employee returnEmp = fetchProfile(e.getEmployeeKey());

		if (returnEmp != null && a != null){
			a.setEmployeeID(e.getEmployeeKey());
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Transaction tx = pm.currentTransaction();

			//Try to add to database
			try	{
				tx.begin();
				pm.makePersistent(a);
				returner = Boolean.TRUE;
				tx.commit();
			}
			finally	{
				if (tx.isActive()) {
					tx.rollback();
					returner = Boolean.FALSE;
				}
			}
			pm.close();
		}

		return returner;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.profile.UserService#addAllPositionsToEmployee(hd.client.profile.Employee, java.util.Collection)
	 */
	@Override
	public Boolean addAllPositionsToEmployee(Employee e,
			Collection<Long> positions) {
		Boolean returner = Boolean.FALSE;
		Employee returnEmp = fetchProfile(e.getEmployeeKey());
		if (returnEmp != null){
			ArrayList<Position> p = new ArrayList<Position>();

			Iterator<Long> iterator = positions.iterator();
			while (iterator.hasNext()){
				Position t = getPosition(iterator.next());
				if (t != null){
					p.add(t);
				}
			}

			returnEmp.setPositions(p);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Transaction tx = pm.currentTransaction();

			//Try to add to database
			try	{
				tx.begin();
				pm.makePersistent(returnEmp);
				returner = Boolean.TRUE;
				tx.commit();
			}
			finally	{
				if (tx.isActive())	{
					tx.rollback();
					returner = Boolean.FALSE;
				}
			}
			pm.close();

		}
		return returner;
	}

}
