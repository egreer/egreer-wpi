package cs4322.hw8;
import java.io.*;


public class SecureReader extends java.io.Reader {
	java.io.Reader Reader;
	boolean encrypt;
	
	
	/**
	 * Constructor.
	 * @param Reader in - reader to be encrypted or decrypted
	 * @param Boolean encrypt, if true encrypt reader, if false decrypt reader
	 */
	public SecureReader(Reader in, boolean encrypt) throws IOException{
		super();
		this.encrypt = encrypt;
		this.Reader = in;
		
	}
	
	/**
	 * Reads the buffer of a single character.  
	 * If encrypt has been set it will encrypt, else it will decrypt it
	 * @return temp - the number of characters read (1)
	 */
	public int read()  throws IOException{	
		
	int temp;
	
	if (encrypt) {
		temp = encrypter(this.Reader.read());	
	}else{
		temp = decrypter(this.Reader.read());
	}
		
	return temp;
	}
	
	/**
	 * Reads the buffer of a character array.  
	 * If encrypt has been set it will encrypt, else it will decrypt it
	 * @param buffer[] - the array of characters to be read
	 * @return temp - the number of characters read
	 */

	public int read(char buffer[]) throws IOException{
		int temp = this.Reader.read(buffer);
		
		if (encrypt) {
			buffer = (char[])encrypterBuffer(buffer);	
		}else{
			buffer = (char[])decrypterBuffer(buffer);
		}
		
	return temp;
	}
	
	/**
	 * Reads the buffer of a character array.  
	 * If encrypt has been set it will encrypt, else it will decrypt it
	 * @param buffer - the array of characters to be read
	 * @param offset - the location in the array buffer to start encrypting or decrypting
	 * @param length - the length of characters to encrypt or decrypt
	 * @return temp - the number of characters read
	 */
	public int read(char buffer[], int offset, int length)  throws IOException{	
		int temp = this.Reader.read(buffer);
		
		if (encrypt) {
			buffer = (char[])encrypterBufferPlus(buffer, offset, length);	
		}else{
			buffer = (char[])decrypterBufferPlus(buffer, offset, length);
		}
		
	return temp;
	}
	
	/**
	 * Closes the reader from reading
	 */
	public void close() throws IOException{
	Reader.close();
	}
	

/**
 * encrypter, encryptes a single character
 * @param mychar - the character to be encrypted
 * @return the encrypted character
 */	
	public int encrypter(int mychar){
		
		mychar = mychar + 26;
		return mychar;
	}
	
	/**
	 * decrypter, decryptes a single character
	 * @param mychar - the character to be decrypted
	 * @return the decrypted character
	 */	
	public int decrypter(int mychar){
		
		mychar = mychar - 26;
		return mychar;
	}
	
	/**
	 * encrypter, encryptes a character array
	 * @param mychar - the character array to be encrypted
	 * @return the encrypted character array
	 */	
	public char[] encrypterBuffer(char[] mychar){
		
		int mylength = mychar.length;
		
		while (mylength > 0){
			mychar[mylength - 1] = (char)(mychar[mylength - 1] + 26);
			mylength--;
		}
		
		return mychar;
	}
	
	/**
	 * decrypter, decryptes a character array
	 * @param mychar - the character array to be decrypted
	 * @return the decrypted character array
	 */	
	public char[] decrypterBuffer(char[] mychar){
		
		int mylength = mychar.length;
		
		while (mylength > 0){
			mychar[mylength - 1] = (char)(mychar[mylength - 1] - 26);
			mylength--;
		}
		
		return mychar;
	}
	
	/**
	 * encrypter, encryptes a character array starting from an offset and of a given length
	 * @param mychar - the character array to be encrypted
	 * @param offset - the location at which to start encrypting
	 * @param length - the length of characters to encrypt
	 * @return the encrypted character array
	 */
	public char[] encrypterBufferPlus(char[] mychar, int offset, int length){
		
		int mylength = offset + length - 2;
		
		while (mylength > offset){
			mychar[mylength -1] = (char)(mychar[mylength -1] + 26);
			mylength--;
		}
		
		return mychar;
	}
	
	/**
	 * decrypter, decryptes a character array starting from an offset and of a given length
	 * @param mychar - the character array to be decrypted
	 * @param offset - the location at which to start decrypting
	 * @param length - the length of characters to decrypt
	 * @return the decrypted character array
	 */
	public char[] decrypterBufferPlus(char[] mychar, int offset, int length){
		
		int mylength = offset + length - 2;
		
		while (mylength > offset){
			mychar[mylength -1] = (char)(mychar[mylength -1] - 26);
			mylength--;
		}
		
		return mychar;
	}
	
	
}

