package nov20;

/**
 * Rotate13 the text (A -> N, B -> O, C -> P, ..., M -> Z, N -> A, ...)
 *
 * @author heineman
 */
public class Rot13Encryption extends Encryption {

	@Override
	String encrypt(String plainText) {
		String enc = "";
		
		for (int i = 0; i < plainText.length(); i++) {
			char c = plainText.charAt(i);
			
			// 'A' = 64   'Z' = 90
			int letter = c - 'A';   // letter is 0..25
			letter += 13;
			letter = letter % 26;   // wrap around
			
			c = (char) ('A' + letter);
			enc = enc + c;
		}
		
		return enc;
	}

}
