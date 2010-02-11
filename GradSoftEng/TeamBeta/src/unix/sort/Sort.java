package unix.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import org.compunit.Property;
import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.ICustomizableComponent;

import unix.core.ThreadedComponent;
import unix.interfaces.IOutput;

@Require( { IOutput.class })
@Provide( { IOutput.class })
@Property( { Sort.propSortNumbered })
public class Sort extends ThreadedComponent implements ICustomizableComponent {
	public static final String propSortNumbered = "n";
	Boolean sortNumbered = false;
	
	private ArrayList<Long> longList = new ArrayList<Long>();
	private ArrayList<String> stringList = new ArrayList<String>();

	public void customize(Properties props) {
		String s = props.getProperty(propSortNumbered);
		if (s != null) {
			sortNumbered = Boolean.valueOf(s);
		}
	}

	public void processOutput(String s) {
		String[] lines = s.split("\n");
		for (String line : lines) {
			if (sortNumbered) {
				try {
					Long n = Long.parseLong(line);
					longList.add(n);
				} catch (NumberFormatException e) {
					stringList.add(line);
				}
			} else {
				stringList.add(line);
			}
		}
	}

	public void processTerminate() {
		if (next != null) {
			Collections.sort(stringList);

			for (String s : stringList) {
				next.output(s + "\n");
			}

			if (sortNumbered) {
				Collections.sort(longList);

				for (Long n : longList) {
					next.output(n.toString() + "\n");
				}
			}
			
			stringList = new ArrayList<String>();
			longList = new ArrayList<Long>();
		}
	}
}
