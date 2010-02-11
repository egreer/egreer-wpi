package unix.interfaces;

public interface IOutput {
	/** Output a string on a line by itself. */
	void output(String s);

	/** Terminate output (an effective End-of-File). */
	void terminate();
}
