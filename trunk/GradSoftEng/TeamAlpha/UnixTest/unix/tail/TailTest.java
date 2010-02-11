package unix.tail;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import unix.Tester;
import unix.interfaces.IOutput;

public class TailTest extends TestCase {


	private Tail tail;
	private Tester stub;
	
	public TailTest(IOutput component, Tester tester, String testName) throws InvalidClassException {
		super(testName);
		if(component instanceof Tail && tester != null){
			this.tail = (Tail) component;
			this.stub = tester;
		}else{
			throw new InvalidClassException("Test suite was not given a Uniq component");
		}
	}
	
	@Before 
	public void setUp() throws Exception{
		stub.output = new ArrayList<String>();
		
		// customize to defaults
		Properties p = new Properties();
		p.setProperty(Tail.PROP_POS_N, "10");
		p.setProperty(Tail.PROP_REVERSE, "false");
		tail.customize(p);
	}

	

	@Test
	public void testDefault() {
		for (int i=0; i < 20; i++)
			tail.processOutput("" + i);
		tail.processTerminate();
			
		for (int i=0; i < 10; i++)
			assertEquals(i + 9 + "", stub.output.get(i));	
	}
	
	@Test
	public void testTooFew(){
		for (int i=0; i < 5; i++)
			tail.processOutput("" + i);
		tail.processTerminate();
		
		assertEquals(0,stub.output.size());
	}
	
	@Test
	public void testHighNumber(){
		Properties props = new Properties();
		props.setProperty("+n", "1000");
		try {
			tail.customize(props);
		} catch (Exception e) {
			fail();
		}
		
		for (int i=0; i < 1000; i++)
			tail.processOutput("" + i);
		tail.processTerminate();
		
		assertEquals(1,stub.output.size());
	}
	
	@Test
	public void testZero(){
		Properties props = new Properties();
		props.setProperty("-n", "0");
		try {
			tail.customize(props);
		} catch (Exception e) {
			fail();
		}
		
		for (int i=0; i < 1000; i++)
			tail.processOutput("" + i);
		tail.processTerminate();
		
		assertEquals(0,stub.output.size());
	}
	
	@Test
	public void testReverse(){
		Properties props = new Properties();
		props.setProperty("+n", "0");
		try {
			props.setProperty(Tail.PROP_REVERSE, "true");
		}catch(Exception e){fail();}
		try {
			tail.customize(props);
		} catch (Exception e) {
			fail();
		}
		
		for (int i=0; i < 10; i++)
			tail.processOutput("" + i);
		tail.processTerminate();
		
		assertEquals(10,stub.output.size());
		
		for (int i=0; i < stub.output.size(); i++)
			assertEquals(9-i+"",stub.output.get(i));
	}
	
	@Test
	public void testConfig1(){
		Properties props = new Properties();
		props.setProperty("+n", "0");
		props.setProperty("-n", "0");
		try {
			tail.customize(props);
		} catch (Exception e) {return;}
		fail();
	}


	
	@Test
	public void testLessThan0(){
		Properties props = new Properties();
		props.setProperty("+n", "-1");
		try {
			tail.customize(props);
		} catch (Exception e) {return;}
		fail();
	}
	

	@Test
	public void testReverse2(){
		Properties props = new Properties();
		props.setProperty("+n", "1");
		try {
			props.setProperty(Tail.PROP_REVERSE, "true");
		}catch(Exception e){fail();}
		try {
			tail.customize(props);
		} catch (Exception e) {
			fail();
		}
		
		for (int i=0; i < 10; i++)
			tail.processOutput("" + i);
		tail.processTerminate();
		
		assertEquals(10,stub.output.size());
		
		for (int i=0; i < stub.output.size(); i++)
			assertEquals(9-i+"",stub.output.get(i));
	}
	
	@Test 
	public void testNeg(){
		Properties props = new Properties();
		props.setProperty("-n", "4");
		try {
			tail.customize(props);
		} catch (Exception e) {return;}
		
		for (int i=0; i < 10; i++)
			tail.processOutput("" + i);
		tail.processTerminate();
		
		assertEquals(4,tail.number);
		assertEquals(4,stub.output.size());
		
		for (int i=0; i < stub.output.size(); i++)
			assertEquals(i + 6 +"",stub.output.get(i));
	}
	
	public void testNegAgainForAHalfLineOfCode(){
		Properties props = new Properties();
		props.setProperty("-n", "12");
		try {
			tail.customize(props);
		} catch (Exception e) {return;}
		
		for (int i=0; i < 10; i++)
			tail.processOutput("" + i);
		tail.processTerminate();
		
		assertEquals(12,tail.number);
		assertEquals(10,stub.output.size());
		
		for (int i=0; i < stub.output.size(); i++)
			assertEquals(i + "",stub.output.get(i));
	}

}
