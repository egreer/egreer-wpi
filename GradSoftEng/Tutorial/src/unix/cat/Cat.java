package unix.cat;

import java.util.Scanner;

import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.IResourceRetriever;

import unix.core.ThreadedComponent;
import unix.interfaces.IOutput;

@Require ({IOutput.class})
@Provide ({IOutput.class})
public class Cat extends ThreadedComponent implements IComponent, IOutput {

	  /** Process String input arrives from prior component. */
	  @Override
	  public void processOutput(String s) {
	    if (next != null) {
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

}
