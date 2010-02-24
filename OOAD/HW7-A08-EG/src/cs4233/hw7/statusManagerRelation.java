package cs4233.hw7;

import java.util.Iterator;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/** 
 * statusManagerRelation contains tables that connect customerStatus to the Managers for each type of search. 
 * This helps to eliminate the abundance of dependencies between each status and its relationships.
 * This implements the singleton pattern to make sure that there is only one set of tables.
 */
public class statusManagerRelation
{
	private statusManagerRelation smr = new statusManagerRelation();
	protected Hashtable flight = new Hashtable();
	protected Hashtable car = new Hashtable();
	protected Hashtable hotel = new Hashtable();

	/**
	 * protected constructor to implement the singleton pattern
	 */
	private statusManagerRelation()
	{

	}

	/** Returns the SMR to implements the singleton pattern.
	 */
	public statusManagerRelation getInstance(){
		return smr;
	}

	/** Adds a new customerStatus to the tables with default managers
	 * 
	 */
	public boolean add(Customer cs)
	{
		return false;
	}

	/**Adds a new customerStatus to the flight table with given manager
	 * 
	 */
	public boolean update(CustomerStatus cs, flightManager fm){
		return false;

	}

	/**Adds a new customerStatus to the car table with given manager
	 * 
	 */
	public boolean update(CustomerStatus cs, carManager cm)
	{
		return false;

	}
	/**Adds a new customerStatus to the hotel table with given manager
	 * 
	 */
	public boolean update(CustomerStatus cs, hotelManager hm)
	{
		return false;

	}

	/** Removes the CustomerStatus from all tables
	 * 
	 */
	public boolean remove(CustomerStatus cs){
		return false;

	}

	/** Returns the managers for that Status
	 * 
	 */
	public Iterator getManagers(CustomerStatus cs)
	{
		return null;

	}
}