package unix;

import org.compunit.Require;
import org.compunit.interfaces.ICommandLine;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.IResourceRetriever;

/** Starts the JSuite Runner
 * 
 * @author Eric Greer
 *
 */
@Require({ICommandLine.class})
public class Starter implements IComponent {
	private ICommandLine main; 
	
	@Override
	public boolean activate(IResourceRetriever handler) throws Exception {
		if (main != null){
			main.main(null);
			return false;	//This line causes an exception to be thrown, but will not terminate otherwise
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
