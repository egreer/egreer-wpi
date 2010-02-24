package goldbug.v2;

import java.util.Iterator;

public class Controller implements IGoldbug {

	/** Code being manipulated by controller. */
	Code code;
	
	/** Knows about the Code model. */
	Controller (Code c) {
		this.code = c;
	}
	
	public void requestAssign(char ch1, char ch2) {
		code.assign(ch1,ch2);
	}

	public void requestClear(char ch) {
		code.clear(ch);
	}

	public void requestFrequency() {
		for (Iterator<Character> it = code.chars(); it.hasNext(); ) {
			System.out.print (it.next());
			System.out.print (" ");
		}
	}

	public void requestQuit() {
		System.exit(0);
	}

}
