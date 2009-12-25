package components;


import java.sql.SQLException;
import java.util.*;

/** The Logic class contains the methods used to parse data from a file and upload it to the proper table. 
 * 
 * @author Eric Greer, Ryan LaSante, Brittany McNally, Greg Coffey <br>
 * 
 * This program was created for use by the Departamento de Recursos Naturales y Ambientales,
 *  by students of WPI, and can be modified to fit their needs.     
 */

public class Logic {

	String defaultDB = "recursos_marinos"; //CHANGE if need to change the default database name


	/** Executes the logic on the given file with the default database
	 * 
	 * @param inputfile				A scanner of the CSV file 
	 * @param username				The username to connect to the database
	 * @param password				the password to connect to the database
	 * @throws SQLException			Throws if there is a error connecting to the database with the given username or password
	 * @throws ClassNotFoundException	Throws if the Postgres database driver is not found
	 */
	void execute (Scanner inputfile , String username, String password) throws SQLException, ClassNotFoundException{
		this.execute(inputfile, username, password, defaultDB);
	}


	/** Executes the logic on the given file with the specified database
	 * 
	 * @param inputfile				A scanner of the CSV file 
	 * @param username				The username to connect to the database
	 * @param password				the password to connect to the database
	 * @param database				The database name to connect at the address specified in database.java 
	 * @throws SQLException			Throws if there is a error connecting to the database with the given username or password
	 * @throws ClassNotFoundException	Throws if the Postgres database driver is not found
	 */
	void execute (Scanner inputfile , String username, String password, String database) throws SQLException, ClassNotFoundException {

		//Establish Database (DB)
		Database db;
		db = new Database(database, username, password);


		//  Read Line by Line from File
		while (inputfile.hasNextLine()){

			String temp = inputfile.nextLine();
			String[] input = temp.split(",");

			//  Create the Data String
			int i = 2;
			if (input[0].compareTo("'Buoy'") == 0){
				i = 3;
			}
			String data = "";
			for(; i < input.length - 1 ;i++){
				data += input[i] + ",";
			}
			data += input[input.length - 1];


			//  If line is a buoy, check to see if buoy is in DB
			if (input[0].compareToIgnoreCase("'Buoy'") == 0){
				try {
					boolean result = db.checkForBuoy(Integer.parseInt(input[2]));

					//  If it is in DB then update the buoy that already exists
					if (result){
						System.out.println("Found buoy!");
						result = db.updateDB("buoy", Integer.parseInt(input[2]), data);

						if(result){
							System.out.println("Updated buoy!");
						}else {
							System.out.println("Failed to Updated buoy!");
						}

					//  Else insert into DB
					}else{
						System.out.println("Could not find buoy, inserting it!");
						result = db.insertDB("buoy", db.largestBuoyGID()+1, Integer.parseInt(input[2]) + "," + data); //Adds the buoy number to the data before passing
						if(result){
							System.out.println("Inserted buoy!");
						}else {
							System.out.println("Failed to insert buoy!");
						}

					}

				} catch (SQLException e) {
					System.out.println("Error Finding Buoy: " + e.getLocalizedMessage());
					e.printStackTrace();
				}
			}

			// If line is a vessel, insert it into database
			else if (input[0].compareTo("'Vessel'") == 0){

				Boolean result = db.insertDB("vessel", db.largestVesselGID()+1, data);
				if(result){
					System.out.println("Inserted vessel!");
				}else {
					System.out.println("Failed to insert vessel!");
				}
			}
		}

		// Closes the database connection
		try{
			db.db.close();
		} catch (SQLException e){
			System.out.println("Error closing the database:");
			e.printStackTrace();
		}
	}
}
