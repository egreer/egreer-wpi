package unix;

import java.util.ArrayList;

import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.IResourceRetriever;
import org.compunit.suiterunner.IJUnitSuite;

import unix.cat.Cat;
import unix.cat.CatTest;
import unix.head.Head;
import unix.head.HeadTest;
import unix.interfaces.IOutput;
import unix.sort.Sort;
import unix.sort.SortTest;
import unix.tail.Tail;
import unix.tail.TailTest;
import unix.uniq.Uniq;
import unix.uniq.UniqTest;

/** Tester is responsible for running all the test cases, 
 * and acting as an interface for those tests to talk with 
 * the component under test
 * 
 * @author Eric Greer
 *
 */
@Require({IJUnitSuite.class, IOutput.class})
@Provide({IOutput.class})
public class Tester implements IComponent, IOutput {
	/* The testing suite*/
	private IJUnitSuite suite;
	
	/* The testing component*/
	protected Uniq uniq = null;
	protected Sort sort = null;
	protected Head head = null;
	protected Tail tail = null;
	protected Cat cat = null;
	
	public ArrayList<String> output;
	
	/**
	 * Sets the tests on the suite runner 
	 */
	@Override
	public boolean activate(IResourceRetriever handler) throws Exception {
		if(suite == null) return false;
		
		if (uniq != null){
			suite.addTest(new UniqTest(uniq, this, UniqTest.tUniq));
			suite.addTest(new UniqTest(uniq, this, UniqTest.tDelete));
			suite.addTest(new UniqTest(uniq, this, UniqTest.tOnly));
			suite.addTest(new UniqTest(uniq, this, UniqTest.tCount));
			suite.addTest(new UniqTest(uniq, this, UniqTest.tDeleteOnly));
			suite.addTest(new UniqTest(uniq, this, UniqTest.tCountOnly));
			suite.addTest(new UniqTest(uniq, this, UniqTest.tCountDelete));
			suite.addTest(new UniqTest(uniq, this, UniqTest.tAll));
		}
		
		if (sort != null){
			suite.addTest(new SortTest(sort, this, SortTest.tAlphaAddrSort));
			suite.addTest(new SortTest(sort, this, SortTest.tAlphaNumSort));
			suite.addTest(new SortTest(sort, this, SortTest.tNumberAddrSort));
			suite.addTest(new SortTest(sort, this, SortTest.tNumberNumSort));
		}
		
		if (head != null){
			suite.addTest(new HeadTest(head, this, "testAll"));
		}
		
		if (tail != null){
			suite.addTest(new TailTest(tail, this, "testDefault"));
			suite.addTest(new TailTest(tail, this, "testTooFew"));
			suite.addTest(new TailTest(tail, this, "testHighNumber"));
			suite.addTest(new TailTest(tail, this, "testZero"));
			suite.addTest(new TailTest(tail, this, "testReverse"));
			suite.addTest(new TailTest(tail, this, "testConfig1"));
			suite.addTest(new TailTest(tail, this, "testLessThan0"));
			suite.addTest(new TailTest(tail, this, "testReverse2"));
			suite.addTest(new TailTest(tail, this, "testNeg"));
			suite.addTest(new TailTest(tail, this, "testNegAgainForAHalfLineOfCode"));
			
		}
		
		if (cat != null){
			suite.addTest(new CatTest(cat, this, CatTest.tLineNumbers));
			suite.addTest(new CatTest(cat, this, CatTest.tNoLineNumbers));
		}
		return true;
	}

	@Override
	public boolean connect(IComponent unit, String iname)
			throws Exception {
		if (iname.equals (IJUnitSuite.class.getName())) {
			suite = (IJUnitSuite) unit;
			return true;
		}
		
		if (iname.equals (IOutput.class.getName())) {
			return this.assignVar(unit);
		}

		// never heard of you
		return false;
	}

	@Override
	public void deactivate() throws Exception {
		if (uniq != null) uniq.deactivate();
		// TODO Auto-generated method stub
	}

	@Override
	public void output(String s) {
		output.add(s);	
	}

	@Override
	public void terminate(){
		// Does nothing ...
	}
	
	/** As part of the activation sets the components to their 
	 * proper variables
	 * 
	 * @param unit	The component
	 * @return		The result of setting that component
	 */
	private boolean assignVar(IComponent unit){
		if (unit instanceof Uniq) uniq = (Uniq) unit;
		else if (unit instanceof Sort) sort = (Sort) unit;
		else if (unit instanceof Head) head = (Head) unit;
		else if (unit instanceof Tail) tail = (Tail) unit;
		else if (unit instanceof Cat) cat = (Cat) unit;
		else return false;
		return true;
	}

}

