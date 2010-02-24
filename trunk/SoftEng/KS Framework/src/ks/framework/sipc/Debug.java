package ks.framework.sipc;

/**
 * Centralized class to manage debugging messages during execution.
 */
public class Debug {

	private static boolean debuggingOn = true;

	/**
	 * Set debugging status.
	 * 
	 * @param b
	 */
	public void setDebugging (boolean b) {
		debuggingOn = b;
	}
	
	public static void print(String s) {
		if (debuggingOn)
			System.out.print(s);
	}

	public static void println(String s) {
		if (debuggingOn) {
			System.out.println(s);
			System.out.flush();
		}
	}
}