package unix.uniq;

import junit.framework.TestCase;

import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.IComponent;
import org.compunit.suiterunner.IJUnitSuite;

import unix.core.OutputDriver;
import unix.interfaces.IOutput;

/**
 * A singleton class for driving the testing on the Uniq component.
 * 
 * @author Nam Do
 */

@Provide( { IOutput.class })
@Require( { IJUnitSuite.class, IOutput.class })
public class UniqDriver extends OutputDriver implements IComponent, IOutput {
	public TestCase getTest(IComponent unit) {
		Uniq uniq = (Uniq) unit;

		if (uniq.isCount && uniq.isDuplicate && uniq.isUnique) {
			return new TestUniq("testCountDuplicateUnique", this);
		} else if (!uniq.isCount && uniq.isDuplicate && uniq.isUnique) {
			return new TestUniq("testNotCountDuplicateUnique", this);
		} else if (uniq.isCount && !uniq.isDuplicate && uniq.isUnique) {
			return new TestUniq("testCountNotDuplicateUnique", this);
		} else if (!uniq.isCount && !uniq.isDuplicate && uniq.isUnique) {
			return new TestUniq("testNotCountNotDuplicateUnique", this);
		} else if (uniq.isCount && uniq.isDuplicate && !uniq.isUnique) {
			return new TestUniq("testCountDuplicateNotUnique", this);
		} else if (!uniq.isCount && uniq.isDuplicate && !uniq.isUnique) {
			return new TestUniq("testNotCountDuplicateNotUnique", this);
		} else if (uniq.isCount && !uniq.isDuplicate && !uniq.isUnique) {
			return new TestUniq("testCountNotDuplicateNotUnique", this);
		} else {
			return new TestUniq("testNotCountNotDuplicateNotUnique", this);
		}

	}
}
