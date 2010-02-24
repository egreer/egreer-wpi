package egreer.lab2;

/**
 * Represents a voting Precinct 
 * @author egreer
 */

public class Precinct {
	// Instacnce Variables are defined as follows:
	
	/** Precinct Name */
	public String name;
	
	/** Precinct Voting Address */
	public String address;
	
	/** Precinct Population */
	public int population;
	
	public String toString(){
		return "Precinct: " + name + ", Address: " + address + ", Population: " + population;
	}
	
	public void add(int num){
		population += num; 
	}
	
	public void remove(int num){
		population -= num;
	}
}
