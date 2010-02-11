package unix.core;

import junit.framework.TestCase;

import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.IResourceRetriever;
import org.compunit.suiterunner.IJUnitSuite;

import unix.interfaces.IOutput;

public abstract class OutputDriver implements IComponent, IOutput {
	/**
	 * The component that we will be performing the testing on
	 */
	protected IOutput componentUnderTest;

	/**
	 * The IJUnitSuite that we will give the tests to
	 */
	private IJUnitSuite suite;

	/**
	 * The buffer used to collect output from the component under test
	 */
	private String outputBuffer = "";

	/**
	 * Used to determine when this component has been terminated
	 */
	volatile boolean active;

	public boolean activate(IResourceRetriever handler) throws Exception {
		active = true;
		return true;
	}

	public void deactivate() throws Exception {
	}

	public boolean connect(IComponent unit, String name) throws Exception {
		if (name.equals(IOutput.class.getName())) {
			componentUnderTest = (IOutput) unit;
			return true;
		} else if (name.equals(IJUnitSuite.class.getName())) {
			suite = (IJUnitSuite) unit;
			suite.addTest(getTest((IComponent) componentUnderTest));
			return true;
		}

		return false;
	}

	/**
	 * @return the Test to be used for testing the component
	 */
	public abstract TestCase getTest(IComponent unit);

	public void terminate() {
		if (componentUnderTest != null) {
			componentUnderTest.terminate();

			synchronized (componentUnderTest) {
				try {
					// There's a good chance that we'll call this before the
					// component calls notifyAll(), so we should have a timeout.
					// No reasonable operation should take longer than 250ms
					componentUnderTest.wait(250);
				} catch (InterruptedException e) {
				}
			}
		}

		synchronized (this) {
			active = false;
			notifyAll();
		}
	}
	
	public boolean isActive() {
		return active;
	}

	/**
	 * Passes output along to the component being tested.
	 * 
	 * @param s
	 */
	public void passOutput(String s) {
		componentUnderTest.output(s);
	}

	/**
	 * Adds the string to the output buffer.
	 */
	public void output(String s) {
		if (s != null) {
			outputBuffer += s;
		}
	}

	/**
	 * Check if there are at least n lines in the output buffer.
	 * 
	 * @param n
	 *            number of lines to check for
	 * @return true if there are at least n lines in the output buffer.
	 */
	public boolean hasOutput(int n) {
		return outputBuffer.split("\\n").length >= n;
	}

	/**
	 * Gets and removes n lines of output from the buffer. The last line will be
	 * a new line.
	 * 
	 * If n < outputBuffer.split('\n').length, the whole output buffer will be
	 * returned and erased
	 * 
	 * @param n
	 *            the number of lines to be returned and removed
	 * @return the whole buffer if n < outputBuffer.split('\n').length or the
	 *         first n lines (including the last newline).
	 */
	public String getOutput(int n) {
		int nthIndex = 0;
		for (int i = 0; i < n; i++) {
			nthIndex = outputBuffer.indexOf('\n', nthIndex + 1);
			if (nthIndex == -1) {
				String tmp = outputBuffer;
				outputBuffer = "";
				return tmp;
			}
		}
		String beginning = outputBuffer.substring(0, nthIndex + 1);
		outputBuffer = outputBuffer.substring(nthIndex + 1);
		return beginning;
	}
}
