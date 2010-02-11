package unix.stdout;

import junit.framework.TestCase;

import org.compunit.Require;
import org.compunit.interfaces.IComponent;
import org.compunit.suiterunner.IJUnitSuite;

import unix.core.OutputDriver;
import unix.interfaces.IOutput;

@Require( { IJUnitSuite.class, IOutput.class })
public class StdoutDriver extends OutputDriver {
	public TestCase getTest(IComponent unit) {
		Stdout stdout = (Stdout) unit;
		if (stdout.useSystemOut) {
			return new TestStdout("testTerminalOutput", this);
		} else {
			return new TestStdout("testFileOutput", this);
		}
	}
}
