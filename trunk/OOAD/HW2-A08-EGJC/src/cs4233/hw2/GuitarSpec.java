package cs4233.hw2;


import java.io.Serializable;

/**
 * This class encapsulates all of the information for a single guitar in
 * the inventory.
 * 
 * @author Eric Greer  		(egreer)
 * @author Jason Codding  	(jcodding)
 * @author gpollice
 * @date 09-10-08
 * CS 4233-A08 HW2
 */
public class GuitarSpec implements Serializable
{
	private Builder builder;
	private String model;
	private Type type;
	private int numStrings;
	private Wood backWood;
	private Wood topWood;

	/**
	 * Constructor. No checks are made on the fields since the GuitarSpec
	 * can be used for matches and can have fields with no values to
	 * represent wildcards.
	 * 
	 * @param builder the manufacturer
	 * @param model the manufacturer's model
	 * @param type the guitar type
	 * @param numStrings number of strings, usually 6 or 12
	 * @param backWood the guitar's back wood
	 * @param topWood the guitar's top wood
	 */
	public GuitarSpec(Builder builder, String model, Type type, int numStrings, Wood backWood,
			Wood topWood)
	{
		this.builder = builder;
		this.model = model;
		this.type = type;
		this.numStrings = numStrings;
		this.backWood = backWood;
		this.topWood = topWood;
	}

	public Builder getBuilder()
	{
		return builder;
	}

	public String getModel()
	{
		return model;
	}

	public Type getType()
	{
		return type;
	}

	public int getNumStrings()
	{
		return numStrings;
	}

	public Wood getBackWood()
	{
		return backWood;
	}

	public Wood getTopWood()
	{
		return topWood;
	}

	/**
	 * Determine if this spec matches another spec. The other spec may have
	 * fields that do not have values.
	 * 
	 * @param otherSpec the other specification to match with this one
	 * @return true if the specs match
	 */
	public boolean matches(GuitarSpec otherSpec)
	{
		if (otherSpec == null)
			return false;
		if (otherSpec.builder != null && builder != otherSpec.builder) 
			return false;
		if (otherSpec.model != null && !otherSpec.model.matches("^\\s*$")
				&& (!model.toLowerCase().equals(otherSpec.model.toLowerCase())))
			return false;
		if (otherSpec.type != null && type != otherSpec.type)
			return false;
		if (otherSpec.numStrings > 0 && numStrings != otherSpec.numStrings)
			return false;
		if (otherSpec.backWood != null && backWood != otherSpec.backWood)
			return false;
		if (otherSpec.topWood != null && topWood != otherSpec.topWood)
			return false;
		return true;
	}
}
