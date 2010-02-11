package unix.tail;

import java.util.ArrayList;
import java.util.Properties;

import org.compunit.Property;
import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.ICustomizableComponent;

import unix.core.ThreadedComponent;
import unix.interfaces.IOutput;

@Provide( { IOutput.class })
@Require( { IOutput.class })
@Property( { Tail.propNumber, Tail.propReverse })
public class Tail extends ThreadedComponent implements ICustomizableComponent {
	/* Define properties. */
	public static final String propNumber = "n";
	public static final String propReverse = "r";
	
	/** Customizations for tail (with defaults). */
	Boolean negative = true;
	Boolean numberSpecified = false;
	Boolean reverse = false;
	int startNumber = -10;
	private ArrayList<String> LineList = new ArrayList<String>();

	@Override
	public void processOutput(String s) {
		if (next != null) {
			String[] lines = s.split("\n");
			for (String line : lines) {
				LineList.add(line);
			}
		}
	}

	@Override
	public void processTerminate() {
		int startIdx;
		int endIdx;
		
		if (next != null) {
			if (reverse == true) {
				// If reverse display is true
				if (numberSpecified == true) {
					endIdx = Math.abs(startNumber) < LineList.size()?
								LineList.size() - Math.abs(startNumber) : 0;
				} else {
					endIdx = 0;
				}
				
				for (int i = LineList.size() - 1; i >= endIdx; i--) {
					next.output(LineList.get(i) + "\n");
				}
				
			} else {
				if (negative == false) {
					// display from beginning
					startIdx = startNumber > LineList.size()?
							LineList.size() : startNumber - 1;
							
					if (startIdx < 0) { 
						startIdx = 0;
					}
				} else {
					// display from end
					startIdx = Math.abs(startNumber) > LineList.size()?
							0 : LineList.size() - Math.abs(startNumber);
					
				}

				for (int i = startIdx; i < LineList.size(); i++) {
					next.output(LineList.get(i) + "\n");
				}
			}
			
			next.terminate();
		}
	}

	@Override
	public void customize(Properties props) {
		String s = props.getProperty(propNumber);
		if (s != null) {
			if (s.startsWith("+")) {
				negative = false;
				s = s.substring(1);
			}
			startNumber = Integer.valueOf(s);
			numberSpecified = true;
		}
		
		s = props.getProperty(propReverse);
		if (s != null) {
			reverse = Boolean.valueOf(s);
		}
	}

}
