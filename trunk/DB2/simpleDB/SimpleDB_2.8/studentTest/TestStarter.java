package studentTest;

import junit.framework.TestCase;

/** Starts both test cases
 * @author Eric Greer
 * @since	CS4432-Project3
 */
public class TestStarter extends TestCase {

	public void testGlassBox() {
		TxTest.test();
	}
	

	public void testBlackBox() {
		BlackboxTest.test();
	}
}
