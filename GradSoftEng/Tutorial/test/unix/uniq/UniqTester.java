package unix.uniq;

import java.util.ArrayList;

import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.IResourceRetriever;
import org.compunit.suiterunner.IJUnitSuite;

import unix.interfaces.IOutput;

@Require({IJUnitSuite.class, IOutput.class})
@Provide({IOutput.class})
public class UniqTester implements IComponent, IOutput {
	/* The testing suite*/
	private IJUnitSuite suite;
	
	/* The testing component*/
	protected IOutput component;
	
	protected ArrayList<String> output;
	
	@Override
	public boolean activate(IResourceRetriever handler) throws Exception {
		if(suite == null) return false;
		suite.addTest(new UniqTest(component, this, UniqTest.tUniq));
		suite.addTest(new UniqTest(component, this, UniqTest.tDelete));
		suite.addTest(new UniqTest(component, this, UniqTest.tOnly));
		suite.addTest(new UniqTest(component, this, UniqTest.tCount));
		suite.addTest(new UniqTest(component, this, UniqTest.tDeleteOnly));
		suite.addTest(new UniqTest(component, this, UniqTest.tCountOnly));
		suite.addTest(new UniqTest(component, this, UniqTest.tCountDelete));
		suite.addTest(new UniqTest(component, this, UniqTest.tAll));
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
			component = (Uniq) unit;
			return true;
		}

		// never heard of you
		return false;
	}

	@Override
	public void deactivate() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void output(String s) {
		output.add(s);	
	}

	@Override
	public void terminate() {
		// Does nothing ...
	}

}

