package unix.uniq;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.compunit.Property;
import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.ICustomizableComponent;

import unix.core.ThreadedComponent;
import unix.interfaces.IOutput;

/**
 * 
 * @author Eric Greer
 *   CountRepeat       True/False   (-c option)
 *   DeleteDup		   True/False   (-d option)
 *   Unique		       True/False   (-u option) 
 */
@Provide({IOutput.class})
@Require({IOutput.class})
@Property({Uniq.propCountRepeat,
	Uniq.propDeleteUniq,
	Uniq.propOnlyUnique})
	public class Uniq extends ThreadedComponent implements ICustomizableComponent {

	/* Define properties. */
	public static final String propCountRepeat = "CountRepeat";
	public static final String propDeleteUniq = "DeleteUniq";
	public static final String propOnlyUnique = "OnlyUnique";

	/* Customizations for uniq (with defaults). */
	private Boolean countRepeat = false;
	private Boolean deleteUniq = false;
	private Boolean onlyUniq = false;

	protected ArrayList<String> input = new ArrayList<String>();  
	protected ArrayList<Integer> count = new ArrayList<Integer>();

	@Override
	public void processOutput(String s) {
		
		/* check to see if the string exists and if so increment count*/
		if (input.contains(s)){
			int index = input.indexOf(s);
			Integer number = count.get(index);
			number++;
			count.set(index, number);

			/* if not add the string */
		}else{
			input.add(s);
			count.add(1);
		}
	}

	@Override
	public void processTerminate() {
		/* removes any strings based on flags */
		if (onlyUniq || deleteUniq){
			Iterator<Integer> iterator = count.iterator();

			for(int j = 0 ; iterator.hasNext() ; j++){
				Integer i = iterator.next();
				if ((deleteUniq && i.intValue() == 1) 		/* DeleteUniq removes any with count = 1 */
						|| (onlyUniq && i.intValue() > 1)){	/* OnlyUnique removes any with count > 1 */
					this.input.remove(j);
					iterator.remove(); //remove the count associated with that string
					j--; //index has shifted by removal
				}
			}
		}
		
		/* Modifies string with count*/
		if (countRepeat){
			for (int index = 0 ; index < input.size() ; index++){
				String s = input.get(index);
				Integer num = this.count.get(index);
				s = num.toString() + " " + s;
				input.set(index, s);
			}
		}

		/* Outputs total set */
		if (next != null) {
			Iterator<String> iterator = input.iterator();
			while(iterator.hasNext()){
				next.output(iterator.next());
			}
			next.terminate();
		}
	}

	@Override
	public void customize(Properties props) throws Exception {
		String s = props.getProperty(propCountRepeat);
		if (s != null) { countRepeat = Boolean.valueOf(s); }

		s = props.getProperty(propDeleteUniq);
		if (s != null) { deleteUniq = Boolean.valueOf(s); }

		s = props.getProperty(propOnlyUnique);
		if (s != null) { onlyUniq = Boolean.valueOf(s); }

	}

}
