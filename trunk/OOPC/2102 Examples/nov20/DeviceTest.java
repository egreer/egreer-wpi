package nov20;

import junit.framework.TestCase;

public class DeviceTest extends TestCase {
	public void testSomething() {
		Device e = new Device();
		e.add(new Rot13Encryption());
		e.add(new ReverseEncryption());
			
		String s = e.confuseText("PARTY AT MIDNIGHT");
		assertEquals ("GUTVAQVZ-GN-LGENC", s);
	}
}
