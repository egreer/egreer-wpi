package unix.uniq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.compunit.Property;
import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.ICustomizableComponent;
import unix.core.ThreadedComponent;
import unix.interfaces.IOutput;

/**
 * 
 * @author Nam Do
 *
 */
@Require( { IOutput.class })
@Provide( { IOutput.class })
@Property( { Uniq.propCount, Uniq.propDuplicate, Uniq.propUnique })
public class Uniq extends ThreadedComponent implements ICustomizableComponent {

	/** Properties */
	public static final String propCount = "c";
	public static final String propDuplicate = "d";
	public static final String propUnique = "u";

	Boolean isCount = false;
	Boolean isDuplicate = false;
	Boolean isUnique = false;

	private ArrayList<String> lines = new ArrayList<String>();
	private Integer[] count;

	@Override
	public void customize(Properties props) throws Exception {
		String s = props.getProperty(propCount);
		if (s != null) {
			isCount = Boolean.valueOf(s);
		}

		s = props.getProperty(propDuplicate);
		if (s != null) {
			isDuplicate = Boolean.valueOf(s);
		}

		s = props.getProperty(propUnique);
		if (s != null) {
			isUnique = Boolean.valueOf(s);
		}
	}

	@Override
	public void processOutput(String s) {
		if (next != null) {
			String[] text = s.split("\n");
			for (String line : text) {
				lines.add(line);
			}
		}
	}

	@Override
	public void processTerminate() {
		if (next != null) {
			count = new Integer[lines.size()];			
			Arrays.fill(count, 0);
			
			int j = 0;
			count[0] = 1;
			
			for (int i = 1; i < lines.size(); i++)
			{
				if (lines.get(i).compareTo(lines.get(i-1)) == 0)
				{
					count[j]++;
				}
				else
				{
					j = i;
					count[j] = 1;
				}
			}
			
			if (isDuplicate && isUnique)
			{
				next.terminate();
			}
			else if (isDuplicate)
			{
				for (int i = 0; i < lines.size(); i++)
				{
					if (count[i] != 0 && count[i] != 1)
					{
						output(i);
					}
				}
			}
			else if (isUnique)
			{
				for (int i = 0; i < lines.size(); i++)
				{
					if (count[i] == 1)
					{
						output(i);
					}
				}
				
			}
			else
			{
				for (int i = 0; i < lines.size(); i++)
				{
					if (count[i] != 0)
					{
						output(i);
					}
				}				
			}
			next.terminate();
		}
	}
	
	/**
	 * Output a line
	 * @param i	line number
	 */
	private void output(int i)
	{
		if (isCount)
		{
			next.output(count[i] +" " + lines.get(i) + "\n");	
		}
		else
		{
			next.output(lines.get(i) + "\n");
		}
		
	}
}
