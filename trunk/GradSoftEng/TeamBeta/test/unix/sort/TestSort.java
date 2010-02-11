package unix.sort;

import junit.framework.TestCase;

public class TestSort extends TestCase {
	public static final String UNSORTED = "georgia\n" + "maine\n"
			+ "new hamshire\n" + "texas\n" + "massachusetts\n" + "california\n"
			+ "alaska\n" + "maine\n";

	public static final String SORTED = "alaska\n" + "california\n"
			+ "georgia\n" + "maine\n" + "maine\n" + "massachusetts\n"
			+ "new hamshire\n" + "texas\n";

	public static final String UNSORTED_NUM = "9223372036854775807\n"
			+ "5477\n" + "391\n" + "4775\n" + "8\n" + "88\n"
			+ "9223372036854775808\n";

	public static final String SORTED_NUM = "9223372036854775808\n" + "8\n"
			+ "88\n" + "391\n" + "4775\n" + "5477\n" + "9223372036854775807\n";

	private SortDriver driver;

	public TestSort(String test, SortDriver driver) {
		super(test);

		this.driver = driver;
	}

	public void testNormalSort() throws InterruptedException {
		driver.passOutput(UNSORTED);
		driver.terminate();

		assertEquals(SORTED, driver.getOutput(SORTED.length()));
	}

	public void testNumberedSort() throws InterruptedException {
		driver.passOutput(UNSORTED_NUM);
		driver.terminate();

		assertEquals(SORTED_NUM, driver.getOutput(SORTED_NUM.length()));
	}
}
