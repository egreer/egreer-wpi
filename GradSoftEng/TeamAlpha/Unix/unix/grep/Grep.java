package unix.grep;

import java.util.Properties;
import java.util.regex.*;
import org.compunit.*;
import org.compunit.interfaces.ICustomizableComponent;

import unix.core.ThreadedComponent;
import unix.interfaces.IOutput;

/**
 * Process Input based upon regular expressions.
 *   CountOnly       True/False   (-c option)
 *   CaseSensitive   True/False   (-i option)
 *   Negative        True/False   (-v option)
 *   Expression      e            (-e option) 
 */
@Provide({IOutput.class})
@Require({IOutput.class})
@Property({Grep.propCountOnly,
	Grep.propNegative,
	Grep.propCaseSensitive,
	Grep.propExpression})
	public class Grep extends ThreadedComponent implements ICustomizableComponent {

	/* Define properties. */
	public static final String propCountOnly = "CountOnly";
	public static final String propNegative = "Negative";
	public static final String propCaseSensitive = "CaseSensitive";
	public static final String propExpression = "Expression";

	/** Customizations for grep (with defaults). */
	private Boolean countOnly = false;
	private Boolean caseSensitive = true;
	private Boolean negative = false;

	/** Expression to search. */
	Pattern expression;

	/** Count if countOnly used. */
	private long count = 0;

	/** Customize from metadata stored with CA file. */
	public void customize(Properties props) throws Exception {
		String s = props.getProperty(propCountOnly);
		if (s != null) { countOnly = Boolean.valueOf(s); }

		s = props.getProperty(propNegative);
		if (s != null) { negative = Boolean.valueOf(s); }

		s = props.getProperty(propCaseSensitive);
		if (s != null) { caseSensitive = Boolean.valueOf(s); }

		s = props.getProperty(propExpression);
		if (s != null) {
			if (caseSensitive) {
				expression = Pattern.compile(s);				
			} else {
				expression = Pattern.compile(s, Pattern.CASE_INSENSITIVE);
			}
		}
	}

	@Override
	public void processOutput(String s) {
		if (next == null) return;

		// if we don't match, leave now...
		Matcher m = expression.matcher(s);

		// deal with NEGATIVE options.
		if (negative) {
			if (m.find()) return;
		} else {
			if (!m.find()) return;
		}	

		// if we get here, we match regular expression.
		// if tally only, do so. Otherwise, output.
		if (countOnly) {
			count++;
			return;
		}
		if (next != null) {
			next.output (s);
		}
	}

	@Override
	public void processTerminate() {
		// if only tallying, then output here.
		if (countOnly) {
			if (next != null) {
				next.output("" + count);
			}
		}

		if (next != null) {
			next.terminate();
		}
	}
} 

