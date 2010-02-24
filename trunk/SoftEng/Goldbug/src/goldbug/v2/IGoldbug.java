package goldbug.v2;

public interface IGoldbug {
	/** Assign code (ch1 = ch2). */
	void requestAssign(char ch1, char ch2);
	
	/** Clear code for character. */
	void requestClear(char ch);
	
	/** See frequency. */
	void requestFrequency();
	
	/** Quit. */
	void requestQuit();
}
