package unix.head;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import unix.Tester;
import unix.interfaces.IOutput;

public class HeadTest extends TestCase {
	
	
		private Head component;
		private Tester tester;
		
				
		
		public HeadTest(IOutput component, Tester tester, String testName) throws InvalidClassException {
			super(testName);
			if(component instanceof Head && tester != null){
				this.component = (Head) component;
				this.tester = tester;
			}else{
				throw new InvalidClassException("Test suite was not given a Uniq component");
			}
		}
		
		@Before 
		public void setUp() throws Exception{
			tester.output = new ArrayList<String>();
		}
		
		@After 
		public void tearDown() throws Exception{
			tester.output.clear();
		}
		
		@Test
		public void testAll(){
			
			// set the property
			Properties props = new Properties();
			props.setProperty(Head.PROP_NUM_LINES, "15");
			
			try {
				component.customize(props);
			} catch (Exception e) {
				fail("Error Customizing");
			}
			
			for (int i=0; i < 100; i++){
				String s = "" + i;
				component.processOutput(s);
			}
			
			component.processTerminate();
			
			assertEquals(tester.output.size(),15);
			
			System.out.println("end of testAll");
		}
}
