package unix.uniq;

import unix.uniq.UniqDriver;
import junit.framework.TestCase;

public class TestUniq extends TestCase {
	
	public static final String ORIGINAL = 
		"bonnie\n" +
		"chuck\n" +
		"chuck\n" +		
		"davel\n" +
		"davel\n" +
		"davel\n" +
		"jeffy\n" +
		"jeffy\n" +
		"jones\n" +
		"mark\n" +
		"mark\n" +
		"mark\n";
	
	public static final int LENGTH = ORIGINAL.split("\\n").length;
	
	private UniqDriver driver;
	
	public TestUniq(String test, UniqDriver driver) {
		super(test);

		this.driver = driver;
	}
	
	public void testNotCountNotDuplicateNotUnique()
	{
		driver.passOutput(ORIGINAL);
		driver.terminate();
		
		String result =
			"bonnie\n" +
			"chuck\n" +					
			"davel\n" +
			"jeffy\n" +
			"jones\n" +
			"mark\n";
		
		assertEquals(result, driver.getOutput(LENGTH));
	}

	public void testCountNotDuplicateNotUnique()
	{
		driver.passOutput(ORIGINAL);
		driver.terminate();
	
		String result =
			"1 bonnie\n" +
			"2 chuck\n" +					
			"3 davel\n" +
			"2 jeffy\n" +
			"1 jones\n" +
			"3 mark\n";
		
		assertEquals(result, driver.getOutput(LENGTH));
	}

	public void testNotCountDuplicateNotUnique()
	{
		driver.passOutput(ORIGINAL);
		driver.terminate();
		
		String result =
			"chuck\n" +					
			"davel\n" +
			"jeffy\n" +			
			"mark\n";
		
		assertEquals(result, driver.getOutput(LENGTH));
	}

	public void testCountDuplicateNotUnique()
	{
		driver.passOutput(ORIGINAL);
		driver.terminate();
		
		String result =
			"2 chuck\n" +					
			"3 davel\n" +
			"2 jeffy\n" +			
			"3 mark\n";
		
		assertEquals(result, driver.getOutput(LENGTH));
	}
	
	public void testNotCountNotDuplicateUnique()
	{
		driver.passOutput(ORIGINAL);
		driver.terminate();
		
		String result =
			"bonnie\n" +
			"jones\n";		
		
		assertEquals(result, driver.getOutput(LENGTH));
	}

	public void testCountNotDuplicateUnique()
	{
		driver.passOutput(ORIGINAL);
		driver.terminate();
		
		String result =
			"1 bonnie\n" +
			"1 jones\n";	
		
		assertEquals(result, driver.getOutput(LENGTH));
	}

	public void testNotCountDuplicateUnique()
	{
		driver.passOutput(ORIGINAL);
		driver.terminate();
		
		String result = "";
		assertEquals(result, driver.getOutput(LENGTH));
	}

	public void testCountDuplicateUnique()
	{
		driver.passOutput(ORIGINAL);
		driver.terminate();
		
		String result = "";
		assertEquals(result, driver.getOutput(LENGTH));
	}
}
