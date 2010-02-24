package cs4322.hw8;

import static org.junit.Assert.*;
import org.junit.*;

import java.io.*;




public class SecureReaderTest {

	@Test
	/**
	 * Tests the single character encrypter and decrypter methods
	 */
	public void characterTest() throws  IOException{
		String testb = "b";
		StringReader myReader = new StringReader (testb);
		
		SecureReader myTestReader = new SecureReader(myReader, true);
		char out = (char)myTestReader.read();
		assertFalse(out == 'b');
		
		StringReader myReader2 = new StringReader("" + out);
		SecureReader myTestReader2 = new SecureReader(myReader2, false);
		char out2 = (char)myTestReader2.read();
		assertEquals('b', out2);
		
		
		
	}
	
	@Test
	/**
	 * Tests the character array encrypter and decrypter methods
	 */
	public void characterArrayTest() throws IOException{

		char[] testChar = {'a'};
		char[] output = new char[1];
		CharArrayReader myReader = new CharArrayReader (testChar);
		
		SecureReader myTestReader = new SecureReader(myReader, true);
		myTestReader.read(output);
		assertFalse(output.equals(testChar));
		
		
		CharArrayReader myReader2 = new CharArrayReader(output);
		SecureReader myTestReader2 = new SecureReader(myReader2, false);
		myTestReader2.read(output);
		assertTrue(output[0] == testChar[0]);
		
		char[] testChar3 = {'a', 'b',};
		char[] output3 = new char[2];
		CharArrayReader myReader3 = new CharArrayReader (testChar3);
		
		SecureReader myTestReader3 = new SecureReader(myReader3, true);
		myTestReader3.read(output3);
		assertFalse(output3.equals(testChar3));
		
		
		CharArrayReader myReader4 = new CharArrayReader(output3);
		SecureReader myTestReader4 = new SecureReader(myReader4, false);
		myTestReader4.read(output3);
		assertTrue(output3[0] == testChar3[0]);
		assertTrue(output3[1] == testChar3[1]);
		
		
	}
	
	@Test
	/**
	 * Tests the character array encrypter and decrypter methods with the
	 * ability to specify offset and length
	 */
	public void characterArrayPlusTest() throws IOException{
		char[] testChar = {'a'};
		char[] output = new char[1];
		CharArrayReader myReader = new CharArrayReader (testChar);
		
		SecureReader myTestReader = new SecureReader(myReader, true);
		myTestReader.read(output, 0,1);
		assertFalse(output.equals(testChar));
		
		
		CharArrayReader myReader2 = new CharArrayReader(output);
		SecureReader myTestReader2 = new SecureReader(myReader2, false);
		myTestReader2.read(output,0,1);
		assertTrue(output[0] == testChar[0]);
		
		char[] testChar3 = {'a', 'b', 'c', 'd','e', 'f'};
		char[] output3 = new char[6];
		CharArrayReader myReader3 = new CharArrayReader (testChar3);
		
		SecureReader myTestReader3 = new SecureReader(myReader3, true);
		myTestReader3.read(output3,2,3);
		assertFalse(output3.equals(testChar3));
		
		
		CharArrayReader myReader4 = new CharArrayReader(output3);
		SecureReader myTestReader4 = new SecureReader(myReader4, false);
		myTestReader4.read(output3,2,3);
		assertTrue(output3[3] == testChar3[3]);
		assertTrue(output3[5] == testChar3[5]);
	}
	
	@Test
	/**
	 * Tests for multiple encryption and decryption
	 */
	public void characterTest2() throws  IOException{
		String testb = "b";
		StringReader myReader = new StringReader (testb);
		
		SecureReader myTestReader = new SecureReader(myReader, true);
		char out = (char)myTestReader.read();
		assertFalse(out == 'b');
		
		StringReader myReader2 = new StringReader("" + out);
		SecureReader myTestReader2 = new SecureReader(myReader2, true);
		char out2 = (char)myTestReader2.read();
		assertFalse(out == 'b');
		
		StringReader myReader3 = new StringReader("" + out2);
		SecureReader myTestReader3 = new SecureReader(myReader3, false);
		char out3 = (char)myTestReader3.read();
		assertFalse(out == 'b');
		
		StringReader myReader4 = new StringReader("" + out3);
		SecureReader myTestReader4 = new SecureReader(myReader4, false);
		char out4 = (char)myTestReader4.read();
		assertEquals('b', out4);

	}

}
