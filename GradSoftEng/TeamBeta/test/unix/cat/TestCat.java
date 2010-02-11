package unix.cat;

import junit.framework.TestCase;

public class TestCat extends TestCase {
	public static final String VERSE = "Tiger, tiger, burning bright\n"
			+ "In the forests of the night,\n" + "What immortal hand or eye\n"
			+ "Could frame thy fearful symmetry?";

	public static final String N_VERSE = "1 Tiger, tiger, burning bright\n"
			+ "2 In the forests of the night,\n"
			+ "3 What immortal hand or eye\n"
			+ "4 Could frame thy fearful symmetry?\n";

	private CatDriver driver;

	public TestCat(String test, CatDriver driver) {
		super(test);

		this.driver = driver;
	}

	public void testUnnumbered() {
		driver.passOutput(VERSE);
		driver.terminate();

		assertEquals(VERSE, driver.getOutput(100));
	}

	public void testNumbered() {
		driver.passOutput(VERSE);
		driver.terminate();

		assertEquals(N_VERSE, driver.getOutput(100));
	}
}
