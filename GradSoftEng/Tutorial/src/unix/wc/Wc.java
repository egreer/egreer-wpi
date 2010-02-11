package unix.wc;

import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.IComponent;
import unix.core.ThreadedComponent;
import unix.interfaces.IOutput;

@Provide({IOutput.class})
@Require({IOutput.class})
public class Wc extends ThreadedComponent implements IComponent, IOutput {

	int numLines = 0;   /** Number of lines seen so far. */

	/** Process String input arrives from prior component. */
	@Override
	public void processOutput(String s) {
		int idx = -1;
		while ((idx = s.indexOf('\n', idx+1)) != -1) {
			numLines++;
		}
	}

	/** Process terminate from prior component. */
	@Override
	public void processTerminate() {
		if (next != null) {
			next.output ("" + numLines);
			next.terminate();
		}
	}
}

