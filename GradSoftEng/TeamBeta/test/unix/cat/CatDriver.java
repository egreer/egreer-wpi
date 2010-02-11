package unix.cat;

import junit.framework.TestCase;

import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.IComponent;
import org.compunit.suiterunner.IJUnitSuite;

import unix.core.OutputDriver;
import unix.interfaces.IOutput;

/**
 * A singleton class for driving the testing on the Cat component.
 * 
 * @author pkalauskas
 */

@Provide( { IOutput.class })
@Require( { IJUnitSuite.class, IOutput.class })
public class CatDriver extends OutputDriver implements IComponent, IOutput {
	public TestCase getTest(IComponent unit) {
		if(((Cat) unit).numbered) {
			return new TestCat("testNumbered",this);
		} else {
			return new TestCat("testUnnumbered",this);
		}
	}
}
