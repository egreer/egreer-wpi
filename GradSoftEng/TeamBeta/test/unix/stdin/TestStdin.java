package unix.stdin;

import unix.core.OutputDriver;
import junit.framework.TestCase;

public class TestStdin extends TestCase {
	public static String FILE_CONTENTS = "The whisper of wind\n"
			+ "Here today, here tomorrow\n" + "Always Everywhere\n";

	OutputDriver driver;

	public TestStdin(String test, OutputDriver driver) {
		super(test);
		this.driver = driver;
	}

	public void testTerminalInput() {
		System.out
				.println("Type the following haiku, press enter, and then press ctrl-z\n"
						+ FILE_CONTENTS);

		synchronized (driver) {
			try {
				driver.wait();
			} catch (InterruptedException e) {
			}
		}

		assertEquals(FILE_CONTENTS, driver.getOutput(10));
	}

	public void testFileInput() {
		System.out
				.println("Type the following haiku, press enter, and then press ctrl-z\n"
						+ FILE_CONTENTS);

		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
		}

		assertEquals(FILE_CONTENTS, driver.getOutput(10));
	}
}
