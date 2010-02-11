package unix.core;

import java.util.concurrent.*;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.IResourceRetriever;
import unix.interfaces.IOutput;

/**
 * Forms basis for any threaded CompUnit component. Subclasses must implement
 * processOutput () and processTerminate ()
 */
public abstract class ThreadedComponent implements IComponent, IOutput {

	protected IOutput next;
	/** Component that comes next in the chain. */
	boolean active;
	/** Has this component become active yet? */
	Thread thread;
	/** Our active thread. */

	/** Buffer where input is stored until it is processed. */
	private final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

	/** Expect output to go to next component. */
	public boolean connect(IComponent unit, String iName) throws Exception {
		if (iName.equals(IOutput.class.getName())) {
			next = (IOutput) unit;
			return true;
		}
		return false; // never heard of you
	}

	/** Determine how to process each line of output. */
	public abstract void processOutput(String s);

	/** Determine action to take when no more input appears (i.e., EOF). */
	public abstract void processTerminate();

	/** Activating the component sets thread to go. */
	public boolean activate(IResourceRetriever irr) {
		active = true;
		thread = new Thread() {
			public void run() {
				while (active) { // while active, process from queue.
					String s;
					try {
						s = queue.take();
					} catch (InterruptedException e) {
						active = false;
						break;
					}
					processOutput(s); // Process output
				}
				processTerminate(); // Deal with terminate.
				if (next != null) {
					next.terminate();
				}
			}
		};
		thread.start();
		return true;
	}

	/** Buffer all output that comes into this component. */
	public void output(String s) {
		try {
			queue.put(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/** Disable and terminate thread. */
	public void deactivate() {
		// wait until empty, before passing along the termination.
		while (queue.size() > 0) {
			Thread.yield();
		}
		active = false;
		synchronized (this) {
			notifyAll();
		}
		if (thread != null) {
			thread.interrupt(); // We may be stuck in run()
			thread = null;
		}
	}

	/** Respond to terminate request. */
	public void terminate() {
		try {
			deactivate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
