package unix;

import java.util.ArrayList;

import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.IResourceRetriever;

import unix.interfaces.IOutput;

public class Stub implements IOutput, IComponent {

	public ArrayList<String> output = new ArrayList<String>();

	@Override
	public void output(String s) {
		output.add(s);
	}

	@Override
	public void terminate() {

	}

	@Override
	public boolean activate(IResourceRetriever handler) throws Exception {
		return false;
	}

	@Override
	public boolean connect(IComponent unit, String interfaceName)
			throws Exception {
		return false;
	}

	@Override
	public void deactivate() throws Exception {

	}

}
