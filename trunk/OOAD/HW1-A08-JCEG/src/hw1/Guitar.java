package hw1;

/**
 * @author Eric Greer	 (egreer)
 * @author Jason Codding (jcodding)
 * @date 09/04/08
 * CS 4233-A08 HW1
 */
public class Guitar
{
	private String serialNumber;
	private double price;
	GuitarSpec spec;

	/** Constructor for a guitar object
	 * 
	 * @param serialNumber	A unique identifier for a guitar
	 * @param price			The cost of the Guitar
	 * @param spec			The GuitarSpec for this guitar
	 */
	public Guitar(String serialNumber, double price, GuitarSpec spec)
	{

		if (serialNumber == null || serialNumber.trim() == "") throw new RuntimeException ("Invalid Serial Number"); 
		if (price <=0) throw new RuntimeException ("Nothing is Free Invalid Price");
		if (!spec.isValid()) throw new RuntimeException ("Invalid Specs");
		this.serialNumber = serialNumber;
		this.price = price;
		this.spec = spec;
	}

	/** getSerialNumber returns the serialNumber of the guitar  
	 * 
	 * @return The serialNumber of the Guitar 
	 */
	public String getSerialNumber()
	{
		return serialNumber;
	}

	/** getPrice returns the price of the guitar  
	 * 
	 * @return The price of the Guitar 
	 */
	public double getPrice()
	{
		return price;
	}

	/** setPrice allows the price to be changed on the guitar  
	 * 
	 * @param newPrice The price to change to, cannot be negative or zero  
	 */
	public void setPrice(double newPrice)
	{
		if(newPrice <= 0) throw new RuntimeException ("Invalid Price");
		this.price = newPrice;
	}

	/** getSpec returns the specifications of the guitar  
	 * 
	 * @return The guitarSpec object for this guitar
	 */
	public GuitarSpec getSpec()
	{
		return spec;
	}
	
}