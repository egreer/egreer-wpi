package unix.tail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import org.compunit.Property;
import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.ICustomizableComponent;

import unix.Stub;
import unix.core.ThreadedComponent;
import unix.interfaces.IOutput;

/**
 * 
 * @author Nik Deapen
 * 
 * @property PROP_POS_N "+n" Show up to and including the last n lines of output only
 * @property PROP_NEG_N "-n" Start showing input n lines into the output
 * @property PROP_REVERSE "-r" When the output is displayed show it in reverse order
 */
@Property({Tail.PROP_POS_N, Tail.PROP_NEG_N, Tail.PROP_REVERSE})
@Provide({IOutput.class})
@Require({IOutput.class})
public class Tail extends ThreadedComponent implements ICustomizableComponent {

	public static final String PROP_POS_N = "+n";
	public static final String PROP_NEG_N = "-n";
	public static final String PROP_REVERSE = "-r";
	
	/**
	 * Number of lines to output.
	 */
	int number = 10;
	
	/**
	 * Whether to output after the nth line.
	 * 	true = output after the nth line
	 *  false = output the last n lines
	 */
	boolean pos = true;
	
	/**
	 * Whether to reverse the output
	 */
	boolean rev = false;
	
	
	/**
	 * Customize the properties and check the state of the properties
	 */
	@Override
	public void customize(Properties properties) throws Exception {
		String s;
		
		if ((s = properties.getProperty(Tail.PROP_NEG_N))!= null){
			if (properties.getProperty(Tail.PROP_POS_N) != null)
				throw new IllegalStateException("Cannot have -n and +n properties at the same time.");
			this.pos = false;
			this.number = Integer.parseInt(s);
		}
		
		if ((s = properties.getProperty(Tail.PROP_POS_N))!= null){
			this.pos = true;
			this.number = Integer.parseInt(s);
		}
		
		if ((s = properties.getProperty(Tail.PROP_REVERSE))!= null)
			this.rev = Boolean.parseBoolean(s);
		
		if (number < 0)
			throw new IllegalArgumentException("The number of lines is less than 0");
	}

	
	ArrayList<String> input = new ArrayList<String>();
	
	/**
	 * Add the output
	 */
	@Override
	public void processOutput(String s) {
		this.input.add(s);
	}
	

	/**
	 * Terminate and send off output
	 */
	@Override
	public void processTerminate() {
		if (this.number == 0){
			if (pos){
				if (rev)
					Collections.reverse(input);
				for (String s:input)
					this.next.output(s);
			}
			this.next.terminate();
			return;
		}
		
		ArrayList<String> output = new ArrayList<String>();
		
		if (pos){ // get the output in indices (n..size)
			for (int i=this.number-1; i < this.input.size(); i++)
				output.add(input.get(i));
		}
		else { // get the indices (size-n..size)
			int index = input.size()-this.number;
			int i = (index < 0)? 0 : index;
			for (; i < input.size(); i++)
				output.add(input.get(i));
		}
		if (rev) // reverse the output 
			Collections.reverse(output);
		
		// reset
		this.input.clear();
		
		for (String s: output)
			this.next.output(s);
		
		this.next.terminate();
	}
}
