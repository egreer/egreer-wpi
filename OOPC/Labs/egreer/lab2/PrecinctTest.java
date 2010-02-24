/**
 * 
 */
package egreer.lab2;

import junit.framework.TestCase;
/**
 * @author egreer
 *
 */

public class PrecinctTest extends TestCase {
	/**
	 * Test cases
	 */
	// test add
	public void testAdd(){
		Precinct p = new Precinct();
		
		// start with a population of 100 and add 13
		p.population = 100;
		p.add(13);
		
		//validate that new population is updated
		assertEquals (p.population, 113);
	}
	
	
	// test remove
	public void testremove(){
		Precinct p = new Precinct();
		
		// start with a population of 100 and remove 13
		p.population = 100;
		p.remove(13);
		
		//validate that new population is updated
		assertEquals (p.population, 87);
	}
	
	
	//test toString
	public void testToString(){
		Precinct p = new Precinct();
		
		// Starting information  
		p.name = "KH202";
		p.address = "100 Institute Road";
		p.population = 100;
		
		
		//validate that information is correct
		assertEquals (p.toString(), "Precinct: KH202, Address: 100 Institute Road, Population: 100");
		
		
		//Change Name
		p.name = "AK116";
		
		//Validate name change
		assertEquals (p.toString(), "Precinct: AK116, Address: 100 Institute Road, Population: 100");
		
		
		//Change address
		p.address = "15 Park Ave.";
		
		//Validate name change
		assertEquals (p.toString(), "Precinct: AK116, Address: 15 Park Ave., Population: 100");
		
		
		//Change Population
		p.population = 180;
		
		//Validate name change
		assertEquals (p.toString(), "Precinct: AK116, Address: 15 Park Ave., Population: 180");
		
		
	}
}
