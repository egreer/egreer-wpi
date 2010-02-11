package unix.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.ICustomizableComponent;

import unix.core.ThreadedComponent;
import unix.interfaces.IOutput;

/**
 * 
 * Unix Sort component
 * 
 * Sorts in ascending order
 * 
 * Default: sort alphabetically
 * SortByNumber property = true: sort numerically
 * 
 * @author Andrew Yee
 *
 */
@Provide({IOutput.class})
@Require({IOutput.class})
public class Sort extends ThreadedComponent implements IOutput,
		ICustomizableComponent {

	/** String representing option **/
	public static final String sortNumerically = "SortByNumber";
	
	ArrayList<String> itemlist = new ArrayList<String>();
	boolean numericalSort = false;

	
	/**
	 * Process the output by adding it to the itemlist
	 * and then re-sort the list
	 * 
	 * @param s
	 */
	@Override
	public void processOutput(String s) {
		
		int newline = s.indexOf('\n');
		if (newline != -1) {
			itemlist.add(s.substring(0, s.indexOf('\n')));
		} else {
			itemlist.add(s);
		}
		
		if (numericalSort) {
			/** sort numerically **/
			Collections.sort(itemlist, new NumberStringComparator());
		} else {
			/** sort alphabetically **/
			Collections.sort(itemlist);
		}

	}

	/**
	 * Output the contents of itemlist
	 */
	@Override
	public void processTerminate() {
		if (next != null) {
			for (String item : itemlist) {
				next.output(item + "\n");
			}
			next.terminate();
		}

	}

	/**
	 * Set flag for customization
	 * Looks for SortByNumber property only (unix -n)
	 */
	@Override
	public void customize(Properties properties) throws Exception {
		String prop = properties.getProperty(sortNumerically);
		if (prop != null) { numericalSort = Boolean.valueOf(prop); }

	}

}
