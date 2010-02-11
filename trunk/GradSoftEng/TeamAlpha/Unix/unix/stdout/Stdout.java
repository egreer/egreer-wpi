package unix.stdout;

import java.io.*;
import java.util.*;

import org.compunit.Property;
import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.ICustomizableComponent;
import org.compunit.interfaces.IResourceRetriever;
import org.compunit.interfaces.IShutdown;

import unix.interfaces.IOutput;

/**
 * Represents stdout that posts to System.out or to a file.
 */
@Provide({IOutput.class})
@Require({IShutdown.class})
@Property({Stdout.fileProp})
public class Stdout implements ICustomizableComponent, IOutput {
  /** Properties */
  public static final String fileProp = "File";

  /** Output interface (Assume to System.out). */
  PrintWriter pw = new PrintWriter (System.out);

  /** Output to stdout? or to a file? */
  boolean useSystemOut = true;

  /** Shutdown agent. */
  IShutdown ourShutdown = null;

  /** Default constructor is required. */
  public Stdout () { }

  public void customize(Properties props) throws Exception {
    String fileName = props.getProperty(fileProp);
    if (fileName != null) {
      // ok: all output goes into this file.
      File outputFile = new File (fileName);
      pw = new PrintWriter (outputFile);
      useSystemOut = false;
    }		
  }

  /** Print available output. Already has <cr>. */
  public void output(String s) {
    pw.print (s);
  }

  /** Close output. */
  public void terminate() {
    pw.flush();
    if (!useSystemOut) { pw.close(); }  

    if (ourShutdown != null) {
      ourShutdown.shutdown();
    }
  }

  public boolean activate(IResourceRetriever irr) throws Exception {
    return true;
  }

  public void deactivate() throws Exception { }

  @Override
  public boolean connect(IComponent unit, String iname) throws Exception {
    if (iname.equals(IShutdown.class.getName())) {
      ourShutdown = (IShutdown) unit;
      return true;
    }

    return false;
  }
}
