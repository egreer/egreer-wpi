package ks.framework.common;

import java.net.URL;
import java.util.Properties;

import ks.framework.interfaces.IProcessMessage;
import ks.framework.sipc.Server;

import org.compunit.Property;
import org.compunit.Provide;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.ICustomizableComponent;
import org.compunit.interfaces.IResourceRetriever;

/**
 * Pre-packages the set of library code needed to support other 
 * Framework components.
 * 
 * This component has no functionality and is used, instead, to 
 * wrap up all "common" classes so they can be retrieved and loaded
 * for use by the respective components.
 * 
 * If general resources were needed, they could also be encapsulated
 * within this component, too.
 * 
 * A hack? Perhaps.
 * 
 * @author George T. Heineman (heineman@cs.wpi.edu)
 */
@Provide({IProcessMessage.class})
@Property({Common.urlVariable,Common.xsdName})
public class Common implements ICustomizableComponent, IProcessMessage {
	
	/** Property for KSD_URL. */
	public static final String urlVariable = "url";
	
	/** Property for XSD name. */ 
	public static final String xsdName = "xsd";
	
	/**
	 * Every CompUnit component must have a no-argument constructor.
	 */
	public Common() { }

	/** This component has no required elements. */
	public boolean connect(IComponent block, String interfaceName) throws Exception {
		return false;
	}

	/** Nothing to do here. */
	public void deactivate() throws Exception {	}

	/** Activate has no functionality either. */
	public boolean activate(IResourceRetriever handler) throws Exception {
		return true;
	}

	// HACK: this is here solely because PACKAGER has problems with packaging components
	// that have no req or prov interfaces.
	public void process(Message m) {
		System.out.println ("Nothing to process");
		
	}

	/**
	 * Properties come here.
	 */
	public void customize(Properties properties) throws Exception {
		// must supply both urlVariable and xsdName to work
		String urlString = properties.getProperty (urlVariable);
		if (urlString != null) {
			// configure underlying code to use this schema
			URL u = new URL (urlString);
			String name = properties.getProperty (xsdName);
			Message.configure (name, u);
		}
	}	
}