package unix.cat;

import java.util.Properties;

import org.compunit.Property;
import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.ICustomizableComponent;

import unix.core.ThreadedComponent;
import unix.interfaces.IOutput;

/**
 * 
 * @author Murad Kaplan
 *
 */
@Require ({IOutput.class})
@Provide ({IOutput.class})
@Property ({Cat.PROP_LINES})
public class Cat extends ThreadedComponent implements ICustomizableComponent, IOutput {
		
	public static final String PROP_LINES  = "-n";
		int cur = 1;
		boolean lines = false;
		
	  /** Process String input arrives from prior component. */
	  @Override
	  public void processOutput(String s) {
	    if (next != null) {
	    	if (lines)
	    		s = cur++ +  ". " + s;
	    	next.output (s);
	    }
	  }

	  /** Process terminate from prior component. */
	  @Override
	  public void processTerminate() {
	    if (next != null) {
	      next.terminate();

	    }
	  }

	@Override
	public void customize(Properties properties) throws Exception {
		// TODO Auto-generated method stub
		String s = properties.getProperty(Cat.PROP_LINES);
		if (s != null) lines = Boolean.valueOf(s);
	}

}
