package cs4233.hw2;

import java.io.Serializable;

/**
 * This class encapsulates all of the information for a single guitar in the inventory.
 * 
 * @author Eric Greer  		(egreer)
 * @author Jason Codding  	(jcodding)
 * @author gpollice
 * @date 09-10-08
 * CS 4233-A08 HW2
 */
public class Guitar implements Serializable
{

	private String serialNumber;
	private double price;
	GuitarSpec spec;

	/**
	 * Constructor. Checks the validity of the parameters and creates the Guitar.
	 * 
	 * @param serialNumber
	 *            a non-empty string with the mfgr's serial number
	 * @param price
	 *            a positive price
	 * @param spec
	 *            the object containing the remainder of the properties
	 * @throws a
	 *             RuntimeException if the fields are invalid
	 */
	public Guitar(String serialNumber, double price, GuitarSpec spec)
	{
		if (serialNumber == null) {
			throw new RuntimeException("null serial number");
		}
		this.serialNumber = serialNumber;
		checkPrice(price);
		this.price = price;
		checkSpec(spec);
		this.spec = spec;
	}

	public String getSerialNumber()
	{
		return serialNumber;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double newPrice)
	{
		checkPrice(newPrice);
		this.price = newPrice;
	}

	public GuitarSpec getSpec()
	{
		return spec;
	}

	/**
	 * Ensure the price is a positive number.
	 * 
	 * @param price
	 *            the value of the guitar's price
	 * @throw RuntimeException if the price value is invalid
	 */
	private void checkPrice(double price)
	{
		if (price <= 0) {
			throw new RuntimeException("price must be positive");
		}
	}

	private void checkSpec(GuitarSpec spec)
	{
		if (spec.getBuilder() == null || spec.getType() == null || spec.getBackWood() == null
				|| spec.getTopWood() == null || spec.getModel() == null
				|| spec.getModel().matches("^\\s*$") || spec.getNumStrings() <= 0) {
			throw new RuntimeException("Invalid value in GuitarSpec constructor");
		}
	}
}