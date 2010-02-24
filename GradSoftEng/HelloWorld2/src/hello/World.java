package hello;

import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.ICommandLine;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.IResourceRetriever;
import org.compunit.interfaces.IShutdown;

@Provide({ICommandLine.class})
@Require({IShutdown.class})
public class World implements IComponent, ICommandLine{

	IShutdown shutdown;
	
	@Override
	public boolean activate(IResourceRetriever handler) throws Exception {
		if (shutdown == null){
			System.err.println("Unable to activate World component without shutdown.");
			return false;
		}
		return true;
	}

	@Override
	public boolean connect(IComponent unit, String interfaceName)
			throws Exception {
		if (interfaceName.equals(IShutdown.class.getName())){
			shutdown = (IShutdown) unit;
			return true;
		}
		return false;
	}

	@Override
	public void deactivate() throws Exception {
	}

	@Override
	public void main(String[] args) {
		System.out.println("Hello, World!");
		shutdown.shutdown();
	}

}
