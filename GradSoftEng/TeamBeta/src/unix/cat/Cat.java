package unix.cat;

import java.util.Properties;

import org.compunit.Property;
import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.ICustomizableComponent;

import unix.core.ThreadedComponent;
import unix.interfaces.IOutput;

@Require( { IOutput.class })
@Provide( { IOutput.class })
@Property( { Cat.propNumbered })
public class Cat extends ThreadedComponent implements ICustomizableComponent {
	public static final String propNumbered = "n";

	Boolean numbered = false;
	private int lineNumber;

	public void customize(Properties props) {
		String s = props.getProperty(propNumbered);
		if (s != null) {
			lineNumber = 1;
			numbered = Boolean.valueOf(s);
		}
	}

	/** Process String input arrives from prior component. */
	public void processOutput(String s) {
		if (next != null) {
			if (numbered) {
				String[] lines = s.split("\n");
				for (String line : lines) {
					next.output(lineNumber + " " + line + "\n");
					lineNumber++;
				}
			} else {
				next.output(s);
			}
		}
	}

	/** Process terminate from prior component. */
	public void processTerminate() {
		if (next != null) {
			next.terminate();
		}
	}
}
