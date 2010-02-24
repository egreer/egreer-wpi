package cs4233.hw8;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;


/** Tests for SecureReader 
 * 
 * @author Eric Greer
 * CS4233-A08-HW8
 */
public class SecureReaderTests {

	char[] test = new char[]{'a', 'b', 'c', 'd', '1', '2', '3'}; 
	char[] out = new char[7];
	char[] dout;
	CharArrayReader tester = new CharArrayReader(test);
	CharArrayReader dtester;
	
	@Test
	public void testEncryptDecryptChar() throws IOException{
		SecureReader encrypting = new SecureReader(new StringReader("" + 'a') , true);
		char a = (char) encrypting.read();
		assertTrue(a == 'Â');
		SecureReader decrypting = new SecureReader(new StringReader("" + a) , false);
		char b = (char) decrypting.read();
		assertTrue(b == 'a');
	}
	
		
	@Test
	public void testEncryptDecryptArrayOffset() throws IOException{
		SecureReader encrypting = new SecureReader(tester, true);
		encrypting.read(out, 1, 6);
		assertTrue(out[1] == 'Â');
		
		dout = out.clone();
		dtester = new CharArrayReader(dout);
		char[] q = new char[7];
		
		SecureReader decrypting = new SecureReader(dtester, false);
		decrypting.read(q, 1, 6);
		assertTrue(q[2] == 'a');
	}
	
	@Test
	public void testEncryptDecryptPatterns() throws IOException{
		char[] testy = new char[7];
		tester = new CharArrayReader(test);
		//test {e,e,d} != same
		SecureReader eed = new SecureReader(new SecureReader(new SecureReader(tester, true), true), false);
		eed.read(testy);
		assertFalse(testy[1] == test[1]);
		
		testy = new char[7];
		tester = new CharArrayReader(test);
		//test {e,d,d} != same
		SecureReader edd = new SecureReader(new SecureReader(new SecureReader(tester, true), false), false);
		edd.read(testy);
		assertFalse(testy[1] == test[1]);
				
		testy = new char[7];
		tester = new CharArrayReader(test);
		//test {e,e,d,d} == same
		SecureReader eedd = new SecureReader(new SecureReader(new SecureReader(new SecureReader(tester, true), true), false), false);
		eedd.read(testy);
		assertTrue(testy[1] == test[1]);
		
		testy = new char[7];
		tester = new CharArrayReader(test);
		//test {e,d,e,d} == same
		SecureReader eded = new SecureReader(new SecureReader(new SecureReader(new SecureReader(tester, true), false), true), false);
		eded.read(testy);
		assertTrue(testy[1] == test[1]);
		
		testy = new char[7];
		tester = new CharArrayReader(test);
		//Test {d, e,} == same
		SecureReader de = new SecureReader(new SecureReader(tester, false), true);
		de.read(testy);
		assertTrue(testy[1] == test[1]);
	}
	
}
