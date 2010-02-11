package unix;

import org.compunit.Require;
import org.compunit.interfaces.ICommandLine;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.IResourceRetriever;
import org.compunit.suiterunner.IJUnitSuite;

@Require({ICommandLine.class})
public class Starter implements IComponent {
	private ICommandLine main; 
	
	@Override
	public boolean activate(IResourceRetriever handler) throws Exception {
		if (main != null){
			main.main(null);
			return true;
		}
		return false;
	}

	@Override
	public boolean connect(IComponent unit, String iname)
			throws Exception {
		if (iname.equals (ICommandLine.class.getName())) {
			main = (ICommandLine) unit;
			return true;
		}
		
		return false;
	}

	@Override
	public void deactivate() throws Exception {
	}

}
