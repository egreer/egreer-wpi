package unix.head;

import java.util.ArrayList;
import java.util.Properties;

import org.compunit.Property;
import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.ICustomizableComponent;

import unix.core.ThreadedComponent;
import unix.interfaces.IOutput;

@Require( { IOutput.class })
@Provide( { IOutput.class })
@Property( { Head.propNumber })
public class Head extends ThreadedComponent implements ICustomizableComponent {
	/* Define properties. */
	public static final String propNumber = "n";

	private int endNumber = 10;
	private Boolean numberSpecified = false;
	private ArrayList<String> LineList = new ArrayList<String>();

	@Override
	public void processOutput(String s) {
		if (next == null)
			return;

		String[] lines = s.split("\n");
		for (String line : lines) {
			LineList.add(line);
		}
	}

	@Override
	public void processTerminate() {

		int endIdx;

		if (next != null) {
			if (numberSpecified == true) {

				if (Math.abs(endNumber) > LineList.size()) {
					endIdx = LineList.size();
					for (int i = 0; i < endIdx; i++) {
						next.output(LineList.get(i) + "\n");
					}
				} else {
					endIdx = Math.abs(endNumber);
					for (int i = 0; i < endIdx; i++) {
						next.output(LineList.get(i) + "\n");
					}
				}
			} else {
				if (10 > LineList.size()) {
					endIdx = LineList.size();
					for (int i = 0; i < endIdx; i++) {
						next.output(LineList.get(i) + "\n");
					}
				} else {
					endIdx = 10;
					for (int i = 0; i < endIdx; i++) {
						next.output(LineList.get(i) + "\n");
					}

				}
			}
		}
		next.terminate();
	}

	@Override
	public void customize(Properties props) throws Exception {
		String s = props.getProperty(propNumber);
		if (s != null) {
			endNumber = Integer.valueOf(s);
			numberSpecified = true;
		}

	}

}
