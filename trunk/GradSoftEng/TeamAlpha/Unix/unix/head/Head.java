package unix.head;

import java.util.Properties;

import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.ICustomizableComponent;

import unix.core.ThreadedComponent;
import unix.interfaces.IOutput;
import org.compunit.Property;

/**
 * 
 * @author Nik Deapen
 * 
 * @property PROP_NUM_LINES "-n" (Integer) Show up to and including the first n lines of output only [default 10]
 *
 */
@Property({Head.PROP_NUM_LINES})
@Provide({IOutput.class})
@Require({IOutput.class})
public class Head extends ThreadedComponent implements ICustomizableComponent {

	public static final String PROP_NUM_LINES = "-n";
	
	/**
	 * Number of lines to output
	 */
	int numLines = 10;
	
	/**
	 * Current Line
	 */
	int curLine = 0;
	
	/**
	 * Output the first n lines then don't do anything
	 */
	@Override
	public void processOutput(String s) {
		if (curLine++ < numLines){
			if (this.next != null)
				this.next.output(s);	
		}
	}

	/**
	 * Reset and tell the next to terminate
	 */
	@Override
	public void processTerminate() {
		this.curLine = 0;
		if (next!= null)
			this.next.terminate();
	}

	/**
	 * Customize the number of lines
	 */
	@Override
	public void customize(Properties properties) throws Exception {
		String s;
		if ((s = properties.getProperty(Head.PROP_NUM_LINES))!= null)
			this.numLines = Integer.parseInt(s);
	}
	
}
