package goldbug.v3;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Represents the file to be decoded.
 * 
 * @author George Heineman
 */
public class Code {

	/** The code. */
	String code;
	
	/** Unknown character. */
	public static char unknown = '\001';
	
	/** Coded characters and their replacement. */
	Hashtable<Character,Character> key = new Hashtable<Character,Character>(); 
	
	/** Characters in decreasing frequency. */
	char chars[];
	
	/** And their frequency. */
	int freq[];
	
	public Code (String s) {
		this.code = s;
		
		process();
	}
	
	/** Compute frequence and build key table. */
	private void process() {
		for (int i = 0; i < code.length(); i++) {
			char ch = code.charAt(i);
			if (key.containsKey(ch)) continue;
			
			key.put(ch, unknown);
		}
		
		int numUnique = key.size();
		chars = new char[numUnique];
		freq = new int [numUnique];
		
		int idx = 0;
		for (Iterator<Character> it = key.keySet().iterator(); it.hasNext(); idx++) {
			char ch = it.next();
			chars[idx] = ch;
			for (int i = 0 ; i < code.length(); i++) {
				if (code.charAt(i) == ch) {
					freq[idx]++;
				}
			}
		}
		
		// now sort by frequency. Bubble sort, for goodness' sake!
		for (int i = 0; i < freq.length - 1; i++) {
			for (int j = i+1; j < freq.length; j++) {
				if (freq[i] < freq[j]) {
					int tmp = freq[i];
					freq[i] = freq[j];
					freq[j] = tmp;
					
					char tc = chars[i];
					chars[i] = chars[j];
					chars[j] = tc;
				}
			}
		}
	}
	
	/** Retrieve code. */
	public char retrieve (char ch) {
		if (key.containsKey(ch)) {
			return key.get(ch);
		}
		
		return unknown;
	}
	
	/** Assign decoded char. */
	public void assign (char ch, char decoded) {
		key.put(ch,	decoded);
	}
	
	/** Clear decoded char. */
	public void clear (char ch) {
		key.put(ch, unknown);
	}
	
	/** Return decoded string (using characters in key). */
	public String decoded () {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < code.length(); i++) {
			sb.append(key.get(code.charAt(i)));
		}
		
		return sb.toString();
	}
	
	/** Suitable toString method. */
	public String toString() {
		return code;
	}
	
	/** Return histogram of characters in order of frequency. */
	public Iterator<Character> chars() {
		ArrayList<Character> al = new ArrayList<Character>();
		for (char ch : chars) {
			al.add(ch);
		}
		return al.iterator();
	}

	/** Output current key. */
	public String showKey() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<Character> it = key.keySet().iterator(); it.hasNext(); ) {
			char ch = it.next();
			char dec = key.get(ch);
			if (dec != unknown) {
				sb.append (" ").append(ch).append("=").append(dec).append(" ");
			}
		}
		
		return sb.toString();
	}
	
}
