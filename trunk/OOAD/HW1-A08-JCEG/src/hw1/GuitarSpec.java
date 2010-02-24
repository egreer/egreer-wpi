package hw1;

/**
 * @author Eric Greer	 (egreer)
 * @author Jason Codding (jcodding)
 * @date 09/04/08
 * CS 4233-A08 HW1
 */
public class GuitarSpec
{

	private Builder builder;
	private String model;
	private Type type;
	private int numStrings;
	private Wood backWood;
	private Wood topWood;

	/** The constructor for the GuitaSpec object
	 * 
	 * @param builder		The builder of the guitar 
	 * @param model			The model of the guitar
	 * @param type			The type of guitar
	 * @param numStrings	The number of strings on the guitar
	 * @param backWood		The wood on the back of the guitar
	 * @param topWood		The wood on the top of the guitar
	 */
	public GuitarSpec(Builder builder, String model, Type type, int numStrings, Wood backWood,
			Wood topWood)
	{
		//Initializes the object
		this.builder = builder;
		this.model = model;
		this.type = type;
		this.numStrings = numStrings;
		this.backWood = backWood;
		this.topWood = topWood;
		
	}

	/** getBuilder returns the builder of the guitar 
	 * 
	 * @return The builder of the guitar 
	 */
	public Builder getBuilder()
	{
		return builder;
	}

	/** getModel returns the model of the guitar 
	 * 
	 * @return The model of the guitar 
	 */
	public String getModel()
	{
		return model;
	}

	/** getType returns the type of the guitar 
	 * 
	 * @return The type of the guitar 
	 */
	public Type getType()
	{
		return type;
	}

	/** getNumStrings returns the number of strings on the guitar 
	 * 
	 * @return The number of strings on the guitar
	 */
	public int getNumStrings()
	{
		return numStrings;
	}

	/** getBackWood returns the Back Wood of the guitar 
	 * 
	 * @return The backWood of the guitar
	 */
	public Wood getBackWood()
	{
		return backWood;
	}

	/** getTopWood returns the Back Wood of the guitar 
	 * 
	 * @return The topWood of the guitar
	 */
	public Wood getTopWood()
	{
		return topWood;
	}

	/** matches checks the GuitarSpecs from the object passed in with the object 
	 *  Any null fields will act as wild cards.
	 * 
	 * @param otherSpec	Is a GuitarSpec object containing the
	 * @return	true means the guitar matches the specs given 
	 */
	public boolean matches(GuitarSpec otherSpec)
	{
		if (otherSpec.builder != null){
			if (builder != otherSpec.builder)
				return false;
		
		}if (otherSpec.model != null && otherSpec.model.trim() == ""){
			if ((model != null) && (!model.equals(""))
					&& (!model.toLowerCase().equals(otherSpec.model.toLowerCase())))
				return false;
		
		}if(otherSpec.type != null){
			if (type != otherSpec.type)
				return false;
		
		}if (otherSpec.numStrings != 0){
			if (numStrings != otherSpec.numStrings)
				return false;
		
		}if (otherSpec.backWood != null){
			if (backWood != otherSpec.backWood)
				return false;
			
		}if (otherSpec.topWood != null){		
			if (topWood != otherSpec.topWood)
				return false;
		}
		
		return true;
	}
	
	
	/** isValid checks the validity of each field in the guitarSpec.
	 * A valid GuitarSpec has no, non-null, non-empty or blank strings fields 
	 * 
	 * @return True guitarSpec is valid 
	 */
	public boolean isValid(){
		if (this.builder == null) return false;
		if (this.model == null || this.model.trim() == "") return false;
		if (this.type == null) return false;
		if (this.numStrings <= 0) return false;
		if (this.backWood == null) return false;
		if (this.topWood == null) return false;
 		return true;
	}
}
