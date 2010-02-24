package hw1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Eric Greer	 (egreer)
 * @author Jason Codding (jcodding)
 * @date 09/04/08
 * CS 4233-A08 HW1
 */
public class Inventory
{
	private List<Guitar> guitars;

	/** Constructor for an inventory object*/
	public Inventory()
	{
		guitars = new LinkedList<Guitar>();
	}
	
	/** Adds a new guitar to the inventory.
	 * 
	 * @param serialNumber	The serial number of the guitar, must be unique 
	 * @param price			The price of the guitar 
	 * @param spec			The GuitarSpec of the guitar
	 */
	public void addGuitar(String serialNumber, double price, GuitarSpec spec)
	{
		Guitar guitar = new Guitar(serialNumber, price, spec);
			
		if (getGuitar(serialNumber) != null) throw new RuntimeException ("Serial number must be unique");
		
		guitars.add(guitar);
	}

	/** getGuitar searches for a guitar in the inventory based on the serial number 
	 * 
	 * @param serialNumber
	 * @return Returns the guitar that matches the serial number given
	 * 			null means that the guitar was not found.
	 */
	public Guitar getGuitar(String serialNumber)
	{
		for (Iterator<Guitar> i = guitars.iterator(); i.hasNext();) {
			Guitar guitar = i.next();
			if (guitar.getSerialNumber().equals(serialNumber)) {
				return guitar;
			}
		}
		return null;
	}
	
	/** Search looks through the inventory for any guitars that 
	 * match the specifications in the parameter.  
	 * 
	 * @param searchSpec is the GuitarSpec of the parameters to search for 
	 * @return	Returns a list of matching guitars 
	 */
	public List<Guitar> search(GuitarSpec searchSpec)
	{
		List<Guitar> matchingGuitars = new LinkedList<Guitar>();
		for (Iterator<Guitar> i = guitars.iterator(); i.hasNext();) {
			Guitar guitar = i.next();
			if (guitar.getSpec().matches(searchSpec))
				matchingGuitars.add(guitar);
		}
		return matchingGuitars;
	}
	
	/** removeGuitar removes a guitar based on the serial number. 
	 * 
	 * @param serialNumber	is the string ID of the guitar to be removed
	 * @return	Returns True if the guitar is removed from the inventory
	 * 			Returns False if serialNumber is Null, empty string or the
	 * 			guitar was not found,
	 */
	public boolean removeGuitar(String serialNumber){
		
		if(serialNumber == null || serialNumber.trim() == "") return false;
		
		Guitar removable= getGuitar(serialNumber);
		if(removable != null){
			guitars.remove(removable);
			return true;
		}
		return false;
	}
}
