package unix.sort;

import junit.framework.TestCase;

import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.IComponent;
import org.compunit.suiterunner.IJUnitSuite;

import unix.core.OutputDriver;
import unix.interfaces.IOutput;

@Provide( { IOutput.class })
@Require( { IJUnitSuite.class, IOutput.class })
public class SortDriver extends OutputDriver {
	public TestCase getTest(IComponent unit) {
		Sort sort = (Sort)unit;
		if(sort.sortNumbered) {
			return new TestSort("testNumberedSort",this);
		} else {
			return new TestSort("testNormalSort", this);
		}
	}
}
