package unix.stdin;

import java.io.File;
import java.util.*;
import org.compunit.*;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.ICustomizableComponent;
import org.compunit.interfaces.IResourceRetriever;
import unix.interfaces.IOutput;

/**
 * Represents stdin that initiates output. If customized
 * with a "File" string, then input is retrieved from there
 */
@Require({IOutput.class})
@Property({Stdin.fileProp})
public class Stdin implements ICustomizableComponent, Runnable {

	/** Properties */
	public static final String fileProp = "File";

	/** File to read input (or null if from System.in). */
	File inputFile = null;

	/** Default constructor Required by CompUnit */
	public Stdin () { }
	/** Next component in chain. */
	protected IOutput next;

	public void customize(Properties props) throws Exception {
		String fileName = props.getProperty(Stdin.fileProp);
		if (fileName == null) return;
		// ok: all input comes from this file.
		File f = new File (fileName);
		if (!f.exists()) {
			throw new java.io.FileNotFoundException (fileName + 
					": No such file or directory.");
		}
		inputFile = f;
	}

	/** Expect output to go to next component. */
	public boolean connect(IComponent unit, String iname) throws Exception {
		if (iname.equals (IOutput.class.getName())) {
			next = (IOutput) unit;
			return true;
		}

		// never heard of you
		return false;
	}

	public boolean activate(IResourceRetriever irr) throws Exception {
		new Thread(this).start();
		return true;
	}

	/** We are responsible for processing. */
	public void run () {
		Scanner sc;

		// either load from a File or take from System.in
		if (inputFile == null) {
			sc = new Scanner(System.in);
		} else {
			try {
				sc = new Scanner (inputFile);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

		// process sc until it is drained dry
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			if (next != null) {
				next.output (s + "\n");
			}
		}

		// all done!
		if (next != null) {
			next.terminate();
		}
	}

	public void deactivate() throws Exception { }
}

