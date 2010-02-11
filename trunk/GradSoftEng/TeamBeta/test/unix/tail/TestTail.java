package unix.tail;

import junit.framework.TestCase;

/**
 * @author Han Wang
 */

public class TestTail extends TestCase {
	private TailDriver driver;
	
	public static final String ORIGINAL =
			"ABC\n" + "BCD\n" + "CDE\n" + 
			"DEF\n" + "EFG\n" + "FGH\n" + 
			"GHI\n" + "HIJ\n" + "IJK\n" + 
			"JKL\n" + "KLM\n" + "LMN\n";
	
	public static final String REMAINING_10_LINES =
			"CDE\n" +
			"DEF\n" + "EFG\n" + "FGH\n" + 
			"GHI\n" + "HIJ\n" + "IJK\n" + 
			"JKL\n" + "KLM\n" + "LMN\n";
	
	public static final String REMAINING_9_LINES =
			"DEF\n" + "EFG\n" + "FGH\n" + 
			"GHI\n" + "HIJ\n" + "IJK\n" + 
			"JKL\n" + "KLM\n" + "LMN\n";
	
	public static final String ENDING_3_LINES = "JKL\n" + "KLM\n" + "LMN\n";
	
	public static final String REVERSE_ORIGINAL = 
			"LMN\n" + "KLM\n" + "JKL\n" + 
			"IJK\n" + "HIJ\n" + "GHI\n" + 
			"FGH\n" + "EFG\n" + "DEF\n" + 
			"CDE\n" + "BCD\n" + "ABC\n";
	
	public static final String REVERSE_ENDING_3_LINES = "LMN\n" + "KLM\n" + "JKL\n";
	
	public static final String EMPTY = "";

	public TestTail(String test, TailDriver driver) {
		super(test);

		this.driver = driver;
	}
	
	/*
	 * test for +4 input, show lines starting at line 
	 */
	public void testPositiveNumOnly() {
		driver.passOutput(ORIGINAL);
		driver.terminate();

		assertEquals(REMAINING_9_LINES, driver.getOutput(100));
	}

	/*
	 * test for -3 input, show last 3 lines
	 */
	public void testNegativeNumOnly() {
		driver.passOutput(ORIGINAL);
		driver.terminate();

		assertEquals(ENDING_3_LINES, driver.getOutput(100));
	}
	
	/*
	 * test for +0 input, show all the lines
	 */
	public void testPositiveZero() {
		driver.passOutput(ORIGINAL);
		driver.terminate();

		assertEquals(ORIGINAL, driver.getOutput(100));
	}
	
	/*
	 * test for -0 input, show nothing
	 */
	public void testNegativeZero() {
		driver.passOutput(ORIGINAL);
		driver.terminate();

		assertEquals(EMPTY, driver.getOutput(100));
	}
	
	/*
	 * test for reverse == true
	 */
	public void testReverseOnly() {
		driver.passOutput(ORIGINAL);
		driver.terminate();

		assertEquals(REVERSE_ORIGINAL, driver.getOutput(100));
	}
	
	/*
	 * test for reverse == true and number = -3
	 */
	public void testReverseWithNum() {
		driver.passOutput(ORIGINAL);
		driver.terminate();

		assertEquals(REVERSE_ENDING_3_LINES, driver.getOutput(100));
	}
	
	/*
	 * test for no number input, no reverse specified
	 */
	public void testNoNumReverse() {
		driver.passOutput(ORIGINAL);
		driver.terminate();

		assertEquals(REMAINING_10_LINES, driver.getOutput(100));
	}
	
	/*
	 * test for +13, which exceeds the length of the input lines
	 */
	public void testPositiveNumExt() {
		driver.passOutput(ORIGINAL);
		driver.terminate();

		assertEquals(EMPTY, driver.getOutput(100));
	}
	
	/*
	 * test for -13, which exceeds the length of the input lines
	 */
	public void testNegativeNumExt() {
		driver.passOutput(ORIGINAL);
		driver.terminate();

		assertEquals(ORIGINAL, driver.getOutput(100));
	}
	
	/*
	 * test for -13, which exceeds the length of the input lines.
	 * and also reverse == true
	 */
	public void testReverseNumExt() {
		driver.passOutput(ORIGINAL);
		driver.terminate();

		assertEquals(REVERSE_ORIGINAL, driver.getOutput(100));
	}
 }
