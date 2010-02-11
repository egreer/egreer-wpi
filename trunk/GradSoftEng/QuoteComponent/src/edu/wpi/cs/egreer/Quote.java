package edu.wpi.cs.egreer;

import org.compunit.Provide;
import org.compunit.examples.quote.IQuote;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.IResourceRetriever;

/**
 * 
 * @author Eric Greer
 *
 */
@Provide({IQuote.class})
public class Quote implements IComponent, IQuote{

	@Override
	public boolean activate(IResourceRetriever handler) throws Exception {
		return true;
	}

	@Override
	public boolean connect(IComponent unit, String interfaceName)
			throws Exception {
		return true;
	}

	@Override
	public void deactivate() throws Exception {
		
	}

	@Override
	public String getQuote() {
		return "The only thing more frightening than a programmer with a screwdriver or a hardware engineer with a program, is a user with a pair of wire cutters and the root password. ~Elizabeth Zwicky";
	}

}
