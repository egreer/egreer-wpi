package unix.tail;

import junit.framework.TestCase;

import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.IComponent;
import org.compunit.suiterunner.IJUnitSuite;

import unix.core.OutputDriver;
import unix.interfaces.IOutput;

/**
 * @author Han Wang
 *  This class is for test.
 *  We define various input, output as below 
 *     1. tail +4 : start to show from the 4th line
 *     2. tail -3 : show the last 3 lines
 *     3. tail +0 : show all lines
 *     4. tail -0 : show nothing
 *     5. tail -r : show lines in reverse order
 *     6. tail -r -3 : show first 3 lines of reversed input
 *     7. tail : show last 10 lines(by default)
 *     8. tail +13( abs(13) > # of lines of input) : show nothing
 *     9. tail -13( abs(-13) > # of lines of input) : show all lines
 *     10. tail -r -13( abs(-13) > # of lines of reversed input) : show all lines
 */

@Provide( { IOutput.class })
@Require( { IJUnitSuite.class, IOutput.class })
public class TailDriver extends OutputDriver {
	public TestCase getTest(IComponent unit) {
		Tail tail = (Tail)unit;
		if (tail.negative == false 
				&& tail.numberSpecified == true
				&& tail.reverse == false
				&& tail.startNumber != 0
				&& Math.abs(tail.startNumber) <= 12) {
			return new TestTail("testPositiveNumOnly", this);
		} else if (tail.negative == true
				&& tail.numberSpecified == true
				&& tail.reverse == false
				&& tail.startNumber != 0
				&& Math.abs(tail.startNumber) <= 12) {
			return new TestTail("testNegativeNumOnly", this);
		} else if (tail.negative == false 
				&& tail.numberSpecified == true
				&& tail.reverse == false
				&& tail.startNumber == 0){
			return new TestTail("testPositiveZero", this);
		} else if (tail.negative == true
				&& tail.numberSpecified == true
				&& tail.reverse == false
				&& tail.startNumber == 0) {
			return new TestTail("testNegativeZero", this);
		} else if (tail.reverse == true
				&& tail.numberSpecified == false) {
			return new TestTail("testReverseOnly", this);
		} else if (tail.reverse == true
				&& tail.numberSpecified == true
				&& Math.abs(tail.startNumber) <= 12) {
			return new TestTail("testReverseWithNum", this);
		} else if (tail.reverse == false
				&& tail.numberSpecified == false) {
			return new TestTail("testNoNumReverse", this);
		} else if (tail.negative == false 
				&& tail.numberSpecified == true
				&& tail.reverse == false
				&& tail.startNumber != 0
				&& Math.abs(tail.startNumber) > 12) {
			return new TestTail("testPositiveNumExt", this);
		} else if (tail.negative == true
				&& tail.numberSpecified == true
				&& tail.reverse == false
				&& tail.startNumber != 0
				&& Math.abs(tail.startNumber) > 12) {
			return new TestTail("testNegativeNumExt", this);
		} else {
			// same with "testReverseWithNum", 
			// difference is the startNumber exceeds the length of input lines
			return new TestTail("testReverseNumExt", this);
		}
	}
}
