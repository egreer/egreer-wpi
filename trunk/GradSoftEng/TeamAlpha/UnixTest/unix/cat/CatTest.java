package unix.cat;


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
 * Tests for Cat component
 * 
 * @author Andrew Yee
 *
 */
public class CatTest extends TestCase {

	
	/** Strings of test names for Tester **/
	public static final String tLineNumbers = "testLineNumbers";
	public static final String tNoLineNumbers = "testNoLineNumbers";
	
	/** Component under test **/
	private Cat component;
	
	/** Tester being used **/
	private Tester tester;
	
	/** Property set for component **/
	Properties props = new Properties();
	
	/** Strings used for testing **/
	private final String one = "one\n";
	private final String two = "two\n";
	private final String three = "three\n";
	private final String withLineNumbers = "1. one\n" + 
										   "2. two\n" + 
										   "3. three\n";
	private final String withoutLineNumbers = "one\n" + 
	   										  "two\n" + 
	   										  "three\n";
	
	/**
	 * Constructor for CatTest for use by the tester
	 * 
	 * @param component
	 * @param tester
	 * @param testName
	 * @throws InvalidClassException
	 */
	public CatTest(Cat component, Tester tester, String testName) throws InvalidClassException {
		super(testName);
		if(component != null && tester != null){
			this.component = (Cat) component;
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
		props.setProperty(Cat.PROP_LINES, "false");
		component.customize(props);
		
	}

	/**
	 * Clear the tester's output and reset the Cat
	 * component's line count after each test
	 */
	@After
	public void tearDown() throws Exception {
		tester.output.clear();
		component.cur = 1;
	}
	
	/**
	 * Test the Cat component with the -n option set to true
	 */
	@Test
	public void testLineNumbers() {
		props.setProperty(Cat.PROP_LINES, "true");
		try {
			component.customize(props);
		} catch (Exception e) {
			fail("Shouldn't throw an exception");
		}
		this.process();
		
		component.processTerminate();
		assertEquals(withLineNumbers, convertListToString(tester.output));
		
	}
	
	/**
	 * Tests the Cat component with the -n option set to false
	 */
	@Test
	public void testNoLineNumbers() {
		this.process();
		component.processTerminate();
		assertEquals(withoutLineNumbers, convertListToString(tester.output));
		
	}
	
	/**
	 * Give input to the component under test
	 */
	void process() {
		component.processOutput(one);
		component.processOutput(two);
		component.processOutput(three);
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
