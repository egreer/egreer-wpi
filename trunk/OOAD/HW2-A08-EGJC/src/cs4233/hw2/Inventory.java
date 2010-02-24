package cs4233.hw2;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.io.*;

/**
 * This class represents Rick's guitar inventory from chapter 1 of HFOOAD.
 * 
 * @author Eric Greer  		(egreer)
 * @author Jason Codding  	(jcodding)
 * @author gpollice
 * @date 09-10-08
 * CS 4233-A08 HW2
 */
public class Inventory
{
	private List<Guitar> guitars;

	/**
	 * Default constructor. Initializes the inventory.
	 */
	public Inventory()
	{
		guitars = new LinkedList<Guitar>();
	}

	/**
	 * Add a guitar to the inventory
	 * 
	 * @param serialNumber the guitar's serial number. It must have a unique value.
	 * @param price the guitar's price
	 * @param spec the GuitarSpec containing the remaining guitar properties. These 
	 * 	must all have values
	 * @throws RuntimeException if there are any errors with the parameters
	 */
	public void addGuitar(String serialNumber, double price, GuitarSpec spec)
	{
		if (serialNumber == null || serialNumber.matches("^\\s*$") ||
				getGuitar(serialNumber) != null) {
			throw new RuntimeException("Invalid or duplicate serial number");
		}
		Guitar guitar = new Guitar(serialNumber, price, spec);
		guitars.add(guitar);
	}
	
	/**
	 * Remove the guitar with the specified serial number from the inventory.
	 * 
	 * @param serialNumber the serial number of the guitar to remove
	 * @return true if the guitar is removed, false otherwise
	 */
	public boolean removeGuitar(String serialNumber)
	{
		Guitar guitar = this.getGuitar(serialNumber);
		if (guitar != null) {
			guitars.remove(guitar);
			return true;
		}
		return false;
	}

	/**
	 * Retrieve a guitar from the inventory.
	 * 
	 * @param serialNumber the serial number of the guitar to retrieve
	 * @return the guitar or null if there is none in inventory with the
	 *   specified serial number
	 */
	public Guitar getGuitar(String serialNumber)
	{
		for (Iterator<Guitar> i = guitars.iterator(); i.hasNext();) {
			Guitar guitar = (Guitar) i.next();
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
		if (searchSpec == null) {
			throw new RuntimeException("Null search spec when searching inventory");
		}
		List<Guitar> matchingGuitars = new LinkedList<Guitar>();
		for (Iterator<Guitar> i = guitars.iterator(); i.hasNext();) {
			Guitar guitar = (Guitar) i.next();
			if (guitar.getSpec().matches(searchSpec))
				matchingGuitars.add(guitar);
		}
		return matchingGuitars;
	}
	
	/** initialize sets the guitar list to the Object in the ObjectInputStream 
	 * so long as the classes are the same.
	 * 
	 * @param inventoryInput Is an objectStream of type LinkedList<Guitar>
	 * @throws Exception	Throws an exception if the inventory is not empty, or if 
	 * 						there is an error reading from the inventoryInput 
	 */
	
	public void initialize(ObjectInputStream inventoryInput) throws Exception{
		if (!this.guitars.isEmpty()) throw new RuntimeException ("Inventory is not empty"); 
		Object o = inventoryInput.readObject();
		
		if (o.getClass() == this.guitars.getClass()){
			this.guitars= (LinkedList<Guitar>) o;
		
		}else throw new RuntimeException ("Not a valid Guitar List");
		
		return;
	}
	
	/** save saves the LinkedList of guitars from the inventory into an ObjectOutputStream
	 * 
	 * @param inventoryOutput The object that guitars will be written to
	 * @throws IOException	Throws and IOException if there is an error writing the 
	 * 						inventoryOutput object
	 */
	public void save(ObjectOutputStream inventoryOutput) throws IOException{
		
		inventoryOutput.writeObject(this.guitars);
		
		return;
	}
}
