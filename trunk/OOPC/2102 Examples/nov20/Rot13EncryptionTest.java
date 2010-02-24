package nov20;

import junit.framework.TestCase;

public class Rot13EncryptionTest extends TestCase {
	public void testCase() {
		Rot13Encryption re = new Rot13Encryption();
		assertEquals ("NOP", re.encrypt("ABC"));
		assertEquals ("ZAB", re.encrypt("MNO"));
		
		// test whole thing
		String plain = "THE PARTY";
		assertEquals ("GUR-CNEGL", re.encrypt(plain));

	}
}
