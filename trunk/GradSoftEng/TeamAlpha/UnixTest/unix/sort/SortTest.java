package unix.sort;


import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unix.Tester;

/**
 * 
 * Tests for Sort component
 * 
 * @author Andrew Yee
 *
 */
public class SortTest extends TestCase {

	/** Strings of test names for Tester **/
	public static final String tAlphaNumSort = "testAlphabeticalNumberSort";
	public static final String tAlphaAddrSort = "testAlphabeticalAddressSort";
	public static final String tNumberNumSort = "testNumericalNumberSort";
	public static final String tNumberAddrSort = "testNumericalAddressSort";
	
	/** Component under test **/
	private Sort component;
	
	/** Tester being used **/
	private Tester tester;
	
	/** Property set for component **/
	Properties props = new Properties();
	
	/** Set one of test strings **/
	final String one = "1";
	final String hundred = "100";	
	final String fifty = "50";
	final String fiftyfive = "55";
	final String fortyfour = "44";
	final String three = "3";
	final String fortyn = "40\n";
	final String sortedAlphaNumList = "1\n" + 
									  "100\n" + 
									  "3\n" +
									  "40\n" + 
									  "44\n" + 
									  "50\n" + 
									  "55\n";
	final String sortedNumerNumList = "1\n" + 
	  								  "3\n" + 
	  								  "40\n" + 
	  								  "44\n" + 
	  								  "50\n" + 
	  								  "55\n" + 
	  								  "100\n";
	
	/** Set two of test strings **/
	final String oneInstRd = "1 Institute Rd";
	final String hundredInstRoad = "100 Institute Road";
	final String hundredInst = "100 Institute";
	final String oneInst = "1 Institute";
	final String eastHall = "East Hall";
	final String eightInst = "8 Institute Rd";
	final String eightBoyn = "8 Boynton St";
	final String sortedAlphaAddrList = "1 Institute\n" + 
									   "1 Institute Rd\n" + 
									   "100 Institute\n" +  	
									   "100 Institute Road\n" +
									   "100 Institute Road\n" +
									   "8 Boynton St\n" + 
									   "8 Institute Rd\n" +
									   "East Hall\n";
	final String sortedNumerAddrList = "1 Institute\n" + 
	  							 	   "1 Institute Rd\n" + 
	  							 	   "8 Boynton St\n" + 
	  							 	   "8 Institute Rd\n" +
	  							 	   "100 Institute\n" + 
	  							 	   "100 Institute Road\n" +
	  							 	   "100 Institute Road\n" +
	  							 	   "East Hall\n";
	
	/**
	 * Constructor for SortTest for use by the tester
	 * 
	 * @param component
	 * @param tester
	 * @param testName
	 * @throws InvalidClassException
	 */
	public SortTest(Sort component, Tester tester, String testName) throws InvalidClassException {
		super(testName);
		if(component != null && tester != null){
			this.component = (Sort) component;
			this.tester = tester;
		}else{
			throw new InvalidClassException("Test suite was not given a Uniq component");
		}
	}
	
	/**
	 * Set up tester and properties for each test
	 */
	@Before
	public void setUp() throws Exception {
		tester.output = new ArrayList<String>();
		props.setProperty(Sort.sortNumerically, "False");
		component.customize(props);
	}

	/**
	 * Clear the tester's output and reset the Sort
	 * component's itemlist after each test
	 */
	@After
	public void tearDown() throws Exception {
		tester.output.clear();
		component.itemlist.clear();
	}
	
	/**
	 * Tests the Sort component with the SortByNumber option 
	 * set to false on a set of numbers
	 */
	@Test
	public void testAlphabeticalNumberSort() {
		this.processNumbers();
		component.processTerminate();
		
		assertEquals(sortedAlphaNumList, convertListToString(tester.output));
	}
	
	/**
	 * Tests the Sort component with the SortByNumber option 
	 * set to false on a set of addresses
	 */
	@Test
	public void testAlphabeticalAddressSort() {
		this.processAddresses();
		component.processTerminate();
		
		assertEquals(sortedAlphaAddrList, convertListToString(tester.output));
	}
	
	/**
	 * Tests the Sort component with the SortByNumber option 
	 * set to true on a set of numbers
	 */
	@Test
	public void testNumericalNumberSort() {
		props.setProperty(Sort.sortNumerically, "true");
		try {
			component.customize(props);
		} catch (Exception e) {
			fail("Shouldn't be getting an exception.");
		}
		this.processNumbers();
		component.processTerminate();
		
		assertEquals(sortedNumerNumList, convertListToString(tester.output));
		
		
	}
	
	/**
	 * Tests the sort component with the SortByNumber option 
	 * set to true on a set of addresses
	 */
	@Test
	public void testNumericalAddressSort() {
		props.setProperty(Sort.sortNumerically, "true");
		try {
			component.customize(props);
		} catch (Exception e) {
			fail("Shouldn't be getting an exception.");
		}
		this.processAddresses();
		component.processTerminate();
		
		assertEquals(sortedNumerAddrList, convertListToString(tester.output));
		
		
	}

	/**
	 * Give number input to the component under test
	 */
	private void processNumbers() {
		component.processOutput(one);
		component.processOutput(hundred);
		component.processOutput(fifty);
		component.processOutput(fiftyfive);
		component.processOutput(fortyfour);
		component.processOutput(three);
		component.processOutput(fortyn);
		
	}

	/**
	 * GIve address input to the component under test
	 */
	private void processAddresses() {
		component.processOutput(oneInstRd);
		component.processOutput(hundredInstRoad);
		component.processOutput(hundredInst);
		component.processOutput(oneInst);
		component.processOutput(eastHall);
		component.processOutput(eightInst);
		component.processOutput(eightBoyn);
		component.processOutput(hundredInstRoad);
		
	}
	
	/**
	 * Converts the contents of a List of Strings to a
	 * single String
	 * 
	 * @param list
	 * @return
	 */
	private String convertListToString(List<String> list) {
		String liststring = "";
		
		for (String s : list) {
			liststring += s;
		}
		
		return liststring;
	}
}
