package lpf.GUIs;

/**
 * When an interactive test case executes, it may either succeed or fail.
 * 
 * @author George
 */
public interface InteractiveTester {
	/** Test case failed. */
	void testFails();
	
	/** Test case succeeded. */
	void testSucceeds();
}
