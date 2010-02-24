package nov20;

import junit.framework.TestCase;

public class ReverseEncryptionTest extends TestCase {
	public void testCase() {
		ReverseEncryption re = new ReverseEncryption();
		
		String plain = "GEORGE";
		String encrypted = re.encrypt(plain);
		assertEquals ("EGROEG", encrypted);
		
		plain = "";
		encrypted = re.encrypt(plain);
		assertEquals ("", encrypted);
		
		plain = "A";
		encrypted = re.encrypt(plain);
		assertEquals ("A", encrypted);
		
		plain = "AB";
		encrypted = re.encrypt(plain);
		assertEquals ("BA", encrypted);
	}
}
