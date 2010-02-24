package nov20;

/**
 * Reverse encryption which reverse the text.
 * 
 * @author heineman
 */
public class ReverseEncryption extends Encryption {

	/**
	 * Overrides the base behavior.
	 */
	@Override
	String encrypt(String plainText) {
		String rev = "";

		for (int i = 0; i < plainText.length(); i++) {
			rev = plainText.charAt(i) + rev;
		}
		
		return rev;
	}

}
