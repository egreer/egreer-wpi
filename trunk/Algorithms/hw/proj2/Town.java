package proj2;

/** A town is made up of an ID, Name, State, Longitude, Latitude, and Elevation. 
 * 
 * 
 * @author Eric Greer
 * 
 */
public class Town {
	
	/** Contains the town ID number */
	public int id;
	
	/** Contains the town name*/
	public String name;
	
	/** Contains the state the town is in*/
	public String state;
	
	/** Contains the longitude of the town*/
	public double lon;
	
	/** Contains the latitude of the town*/
	public double lat;
	
	/** Contains the elevation of the town*/
	public int elevation;
	
	
	
	/** Constructor to create a Town
	 * 
	 * @param ID		This is the town ID
	 * @param Name		This is the town name
	 * @param State		This is the state the town is in 
	 * @param Lon		This is the longitude of the town
	 * @param Lat		This is the latitude of the town
	 * @param Elevation	This is the elevation of the town
	 */
	public Town(int ID, String Name, String State , double Lon, double Lat, int Elevation){
		this.id = ID;
		this.name = Name;
		this.state = State;
		this.lon = Lon;
		this.lat = Lat;
		this.elevation = Elevation;
	}

}
