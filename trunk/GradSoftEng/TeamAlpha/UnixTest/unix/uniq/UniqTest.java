package unix.uniq;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unix.Tester;

/** Tests the Uniq Component
 * 
 * @author Eric Greer
 *
 */
public class UniqTest extends TestCase{
	
	/* Test Names */
	public static final String tUniq = "testUniq";
	public static final String tDelete = "testDelete";
	public static final String tOnly = "testOnly";
	public static final String tCount = "testCount";
	public static final String tDeleteOnly = "testDeleteOnly";
	public static final String tCountOnly = "testCountOnly";
	public static final String tCountDelete = "testCountDelete";
	public static final String tAll = "testAll";
	
	private Uniq component;
	private Tester tester;
	
	Properties props = new Properties();
	final String one = "one";
	final String two = "two";
	final String three = "three";
	final String four = "four";
	final String five = "five";
	
	final List<String> uniqStrings =  Arrays.asList(one, five);
	final List<String> multStrings =  Arrays.asList(two, three, four);
	
	/** Constructor for the Test Cases
	 * 
	 * @param component		The uniq component under test
	 * @param tester		The tester component
	 * @param testName		The name of the test to run
	 * @throws InvalidClassException
	 */
	public UniqTest(Uniq component, Tester tester, String testName) throws InvalidClassException {
		super(testName);
		if(component != null && tester != null){
			this.component = (Uniq) component;
			this.tester = tester;
		}else{
			throw new InvalidClassException("Test suite was not given a Uniq component");
		}
	}
	
	@Before
	public void setUp() throws Exception {
		tester.output = new ArrayList<String>();
		
		//SET Properties to defaults
		props.setProperty(Uniq.propCountRepeat, "False");
		props.setProperty(Uniq.propDeleteUniq, "False");
		props.setProperty(Uniq.propOnlyUnique, "False");
		component.customize(props);
		this.process();
	}

	@After
	public void tearDown() throws Exception {
		tester.output.clear();
		component.input.clear();
		component.count.clear();
	}
	
	/**
	 * Tests Uniq with not flags
	 */
	@Test
	public void testUniq(){
		
		component.processTerminate();
		
		assertEquals(5, tester.output.size());
		assertTrue(tester.output.containsAll(uniqStrings));
		assertTrue(tester.output.containsAll(multStrings));
	}
	
	/**
	 * Tests Uniq with Delete Unique flag  
	 */
	@Test
	public void testDelete() {
		props.setProperty(Uniq.propDeleteUniq, "True");
		
		try {
			component.customize(props);
		} catch (Exception e) {
			fail("Should not have thrown exception");
		}
		
		component.processTerminate();
		
		assertEquals(3, tester.output.size());
		assertTrue(tester.output.containsAll(multStrings));
		assertFalse(tester.output.containsAll(uniqStrings));
	}

	/**
	 * Tests Uniq with Only Unique flags  
	 */
	@Test
	public void testOnly() {
		props.setProperty(Uniq.propOnlyUnique, "True");
		try {
			component.customize(props);
		} catch (Exception e) {
			fail("Should not have thrown exception");
		}
		component.processTerminate();
		
		assertEquals(2, tester.output.size());
		assertTrue(tester.output.containsAll(uniqStrings));
		assertFalse(tester.output.containsAll(multStrings));
	}

	/**
	 * Tests Uniq with Count flag  
	 */
	@Test
	public void testCount() {
		props.setProperty(Uniq.propCountRepeat, "True");
		try {
			component.customize(props);
		} catch (Exception e) {
			fail("Should not have thrown exception");
		}
		component.processTerminate();
		
		assertEquals(5, tester.output.size());
		assertEquals("1 " + one, tester.output.get(0));
		assertEquals("2 " + two, tester.output.get(1));
		assertEquals("3 " + three, tester.output.get(2));
		assertEquals("2 " + four, tester.output.get(3));
		assertEquals("1 " + five, tester.output.get(4));
	}
	
	/**
	 * Test uniq with Delete Unique and Only Unique Flags
	 */
	@Test
	public void testDeleteOnly() {
		props.setProperty(Uniq.propDeleteUniq, "True");
		props.setProperty(Uniq.propOnlyUnique, "True");
		try {
			component.customize(props);
		} catch (Exception e) {
			fail("Should not have thrown exception");
		}
		component.processTerminate();
		
		assertEquals(0, tester.output.size());
	}
	
	/**
	 * Test Count & Only Unique flags
	 */
	@Test
	public void testCountOnly() {
		props.setProperty(Uniq.propCountRepeat, "True");
		props.setProperty(Uniq.propOnlyUnique, "True");
		try {
			component.customize(props);
		} catch (Exception e) {
			fail("Should not have thrown exception");
		}
		component.processTerminate();
		
		assertEquals(2, tester.output.size());
		assertEquals("1 " + one, tester.output.get(0));
		assertEquals("1 " + five, tester.output.get(1));
	}
	
	/**
	 * Test Count & Delete Unique flags
	 */
	@Test
	public void testCountDelete() {
		props.setProperty(Uniq.propCountRepeat, "True");
		props.setProperty(Uniq.propDeleteUniq, "True");
		try {
			component.customize(props);
		} catch (Exception e) {
			fail("Should not have thrown exception");
		}
		component.processTerminate();
		
		assertEquals(3, tester.output.size());
		assertEquals("2 " + two, tester.output.get(0));
		assertEquals("3 " + three, tester.output.get(1));
		assertEquals("2 " + four, tester.output.get(2));
	}

	/**
	 * Tests all flags
	 */
	@Test
	public void testAll() {
		props.setProperty(Uniq.propCountRepeat, "True");
		props.setProperty(Uniq.propDeleteUniq, "True");
		props.setProperty(Uniq.propOnlyUnique, "True");
		try {
			component.customize(props);
		} catch (Exception e) {
			fail("Should not have thrown exception");
		}
		component.processTerminate();
		
		assertEquals(0, tester.output.size());
	}
	
	
	/**
	 * Sets up the processing of inputs
	 */
	public void process(){
		component.processOutput(one);
		component.processOutput(two);
		component.processOutput(two);
		component.processOutput(three);
		component.processOutput(three);
		component.processOutput(three);
		component.processOutput(four);
		component.processOutput(four);
		component.processOutput(five);
	}
	
}
