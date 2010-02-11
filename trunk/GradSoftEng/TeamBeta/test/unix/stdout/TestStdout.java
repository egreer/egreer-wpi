package unix.stdout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import junit.framework.TestCase;

public class TestStdout extends TestCase {
	public static String TEST_STRING = "The whisper of wind\n"
			+ "Here today, here tomorrow\n" + "Always Everywhere\n";
	StdoutDriver driver;

	public TestStdout(String test, StdoutDriver driver) {
		super(test);
		this.driver = driver;
	}

	public void testTerminalOutput() {
		driver.passOutput(TEST_STRING);
		driver.terminate();
		
		int n = JOptionPane.showConfirmDialog(new JFrame(),
				"Did you see the haiku in the terminal?", "Terminal Output Test",
				JOptionPane.YES_NO_OPTION);
		
		// assert that the user clicked 'Yes'
		assertEquals(0,n);
	}

	public void testFileOutput() {
		driver.passOutput(TEST_STRING);
		driver.terminate();
		
		int n = JOptionPane.showConfirmDialog(new JFrame(),
				"Open CompUnitRelease/haiku.txt.  Do you see a haiku", "File Output Test",
				JOptionPane.YES_NO_OPTION);
		
		// assert that the user clicked 'Yes'
		assertEquals(0,n);
	}
}
