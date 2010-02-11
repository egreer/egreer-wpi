package unix.stdin;

import junit.framework.TestCase;

import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.IComponent;
import org.compunit.suiterunner.IJUnitSuite;

import unix.core.OutputDriver;
import unix.interfaces.IOutput;

@Provide( { IOutput.class })
@Require( { IJUnitSuite.class })
public class StdinDriver extends OutputDriver {
	public static StdinDriver instance = null;

	public TestCase getTest(IComponent unit) {
		return new TestStdin("testTerminalInput", this);
	}
}
