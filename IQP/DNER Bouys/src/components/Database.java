package components;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** The Database Class contains the methods for establishing connections to the database.
 * Also contains methods for updating and inserting values into the database. 
 * 
 * @authors Eric Greer, Ryan LaSante, Brittany McNally, Greg Coffey <br>
 * 
 * This program was created for use by the Departamento de Recursos Naturales y Ambientales,
 *  by students of WPI, and can be modified to fit their needs.     
 */
public class Database {

	/* Database connection information */
	String address = "10.2.99.42:5432"; 	//Change the address and port of the database here
	Connection db;      			// A connection to the database
	Statement sql; 					// Statement to run queries with
	DatabaseMetaData dbmd;      	// Info the driver delivers about the DB

	String database;	// The database name
	String username;	// The Username for the Database
	String password;	// The Password for the Database 

	/** Constructor for database object
	 * 
	 * @param argv		database, username, password	The database names, and username & password to connect to that database 
	 * @throws ClassNotFoundException	Throws if the PostGres driver cannot be found
	 * @throws SQLException				Throws if there is an error connecting to the database with the given input 
	 */
	Database(String database, String username, String password) throws ClassNotFoundException, SQLException{
		this.database = database;
		this.username = username;
		this.password = password;
		this.initializeDB();
	}

	/** Initializes the database connection and stores it in the db variable.
	 * 
	 * @throws ClassNotFoundException	Throws if the PostGres driver cannot be found
	 * @throws SQLException				Throws if there is an error connecting to the database with the given input 
	 */
	void initializeDB() throws ClassNotFoundException, SQLException{
		Class.forName("org.postgresql.Driver"); //load the driver
		db = DriverManager.getConnection("jdbc:postgresql://"+ this.address +"/"+this.database,
				this.username,
				this.password); //connect to the db
		dbmd = db.getMetaData(); //get MetaData to confirm connection
		System.out.println("Connection to "+dbmd.getDatabaseProductName()+" "+
				dbmd.getDatabaseProductVersion()+" successful.\n");
		sql = db.createStatement(); //create a statement that we can use later
	}

	/** Runs an insert Query on the database
	 * 
	 * @param type	The type of data being inserted (table name)
	 * @param pkey	The primary key of the object being inserted (buoy number, vessel increment)
	 * @param data	The data to insert, comma separated 
	 * @return		Returns the success of the query. False if there was an exception in the SQL, or if a row was not inserted
	 */
	boolean insertDB(String type, int pkey, String data){
		boolean returner = false;

		String[] split = data.split(","); 
		formatNull(split);
		String recombined = pkey + ",";

		//Search for the date field and then format it and recombine the data
		for(int i = 0; i< split.length ; i++){
			if(split[i].length() == 12 && split[i].matches("'\\d\\d\\d\\d/\\d\\d/\\d\\d'")){
				split[i]= formatDate(split[i]);
			}
			recombined += split[i] + ","; 
		}
		System.out.println(recombined);

		try {
			int rows = sql.executeUpdate("INSERT INTO "+ type + " VALUES (" + recombined + "null)");
			if (rows >= 1){
				returner = true;
			}
		} catch (SQLException e) {
			System.out.println("Error Executing Query: \"INSERT INTO "+ type + " VALUES (" + recombined + "null)\"");
			System.out.println("Error Message: "+ e.getLocalizedMessage()); 
			e.printStackTrace();
		}
		return returner;
	}


	/** Runs an update Query on the database. Can only update buoys since vessels have no unique qualities to match on.
	 * 
	 * @param type	the type of data being inserted (table name)
	 * @param pkey	the primary key of the object to update. (buoy number)
	 * @param data	the data for the update, comma separated
	 * @return		Returns the success of the query 
	 */
	boolean updateDB(String type, int pkey,  String data){
		boolean returner = false;
		String query = "";

		//Code to execute if the type is a buoy
		if(type.compareTo("buoy") ==0){
			String[] split = data.split(","); //Should split to 27 different fields 
			formatNull(split);

			System.out.println(formatDate(split[23])); //CHANGE if there is a change to the number of fields in the CSV

			//Assembles the Query
			query = "UPDATE buoy SET " + 
			" \"Inspectors\"=" + split[0] +
			", \"Type\"=" + split[1] +
			", \"Buoy\"=" + split[2] +
			", \"Pickup_Lin\"=" + split[3] +
			", \"Reflective\"=" + split[4] +
			", \"Thru_Line\"=" + split[5] +
			", \"Lead_Weigh\"=" + split[6] +
			", \"Downline\"=" + split[7] +
			", \"Shaffing_T\"=" + split[8] +
			", \"Swivel\"=" + split[9] +
			", \"Anchorage\"=" + split[10] +
			", \"Clean\"=" + split[11] +
			", \"Other_Obse\"=" + split[12] +
			", \"Habitat_Ty\"=" + split[13] +
			", \"Local_Anch\"=" + split[14] +
			", \"Amount_of_\"=" + split[15] +
			", \"Scrape\"=" + split[16] +
			", \"Scar\"=" + split[17] +
			", \"Blow_Hole\"=" + split[18] +
			", \"Fragement\"=" + split[19] +
			", \"Pulverized\"=" + split[20] +
			", \"Damage_Des\"=" + split[21] +
			", \"Other_Obs2\"=" + split[22] +
			", \"Date\"=" + formatDate(split[23]) +
			", \"Time\"=" + split[24];

			//Checks to see if there is updated Lat/Long coordinates to input
			//CHANGE 27 to the appropriate number if the columns in the database change
			if (split.length == 27){
				query += ", \"Latitude\"=" + split[25] + ", \"Longitude\"=" + split[26];		
			}

			query += " WHERE \"Buoy_Numbe\"=" + pkey;
		}

		try {
			//Execute the query if the type is a buoy
			if(type.compareTo("buoy") == 0){
				sql.executeUpdate(query);
				returner = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return returner;
	}

	/** Checks for the buoy in the database based off the buoy number
	 * 
	 * @param number 	The buoy number
	 * @return			True if the buoy exists, false if it does not exist 
	 * @throws SQLException 	Throws if there is an error in the SELECT query
	 */
	boolean checkForBuoy(int number) throws SQLException{
		boolean returner = false;

		ResultSet results = sql.executeQuery("SELECT * FROM buoy WHERE \"Buoy_Numbe\"=" + number);

		if (results.next())
		{
			returner = true;
		}
		results.close();
		return returner;
	}


	/** Formats the date from 'YYYY/MM/DD' to 'YYYYMMDD' including apostrophes 
	 * 
	 * @param	date	The unformatted date
	 * @return			Returns the corrected date. If invalid date or null was given returns the original value truncated to 10 characters  
	 */
	String formatDate(String date){
		//Checks for valid length 
		if (date.length() != 12){
			if(date.length() <= 10)return date;	
			else return date.substring(0, 10);
		}
		return date.substring(0, 5) + date.substring(6, 8) + date.substring(9); 
	}

	/** Checks for nulls strings ('') and replaces with (null) 
	 * 
	 * @string	check	The array of strings to check	
	 * @return			The array containing the updated data 
	 */
	String[] formatNull(String[] check){
		for(int i =0; i < check.length ; i++){

			if(check[i].length() == 2){
				if(check[i].charAt(0) == '\'' && check[i].charAt(1) == '\''){
					check[i] = "null";
				}
			}
		}
		return check;
	}

	/** Obtains the largest Vessel GID from the database
	 * 
	 * @return	The GID number that is the largest in the database, returns -1 if no GID's were found
	 */
	int largestVesselGID(){
		int returner = -1;
		String resultingValue;

		try {
			ResultSet results = sql.executeQuery("SELECT MAX(gid) FROM vessel");
			results.next();
			resultingValue = results.getString(1);

			if(resultingValue != null){
				returner = Integer.parseInt(resultingValue);
			}

			System.out.println("Largest Vessel GID is:" + returner);

		} catch (SQLException e) {
			System.out.println("Error Selecting the Largest Vessel GID: ");
			e.printStackTrace();
		}
		return returner;
	}

	/** Finds the largest Buoy GID from the database
	 * 
	 * @return	The GID number that is the largest in the database, returns -1 if no GID's were found
	 */
	int largestBuoyGID(){
		int returner = -1;
		String resultingValue;

		try {
			ResultSet results = sql.executeQuery("SELECT MAX(gid) FROM buoy");
			results.next();
			resultingValue = results.getString(1);

			if(resultingValue != null){
				returner = Integer.parseInt(resultingValue);
			}

			System.out.println("Largest Buoy GID is:" + returner);
		} catch (SQLException e) {
			System.out.println("Error Selecting the Largest Buoy GID: ");
			e.printStackTrace();
		}
		return returner;
	}
}
