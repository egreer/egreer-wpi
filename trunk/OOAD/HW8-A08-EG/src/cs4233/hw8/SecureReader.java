package cs4233.hw8;

import java.io.IOException;
import java.io.Reader;

/** SecureReader is a Reader that encrypts, and decrypts data in another reader.  
 * 
 * @author Eric Greer
 * CS4233-A08-HW8
 */
public class SecureReader extends Reader {
	Reader in; 
	boolean encrypt;
	
	/**
	 * Constructor. Takes another reader and a boolean that if true encrypts the data in the in reader if encrypt is true;
	 * otherwise decrypts the data.
	 * 
	 * @param in		Reader input to encrypt
	 * @param encrypt 	true encrypts the data in the in reader, false decrypts 
	 */
	public SecureReader(Reader in, boolean encrypt){
		super();
		this.in = in;
		this.encrypt = encrypt; 
	}
	
	
	/**
	 * Closes the Reader Stream
	 */
	public void close() throws IOException {
		in.close();
	}


	/** Reads the next char in the reader, and encrypts it if encrypt was true. 
	 * 
	 * @return int 	the char read, returns -1 if reached end of stream  
	 */
	public int	read() throws IOException{
		 char read = (char)this.in.read();
		 if(read == -1) return -1;
		 else if(encrypt) return encrypt(read);
		 else return decrypt(read);
	 }

	
	/** Reads the next chars into the cbuf until full, and encrypts it if encrypt was true. 
	 * @param	 cbuf	An array of chars to be filled.
	 * @return 	int 	number of bytes read in, returns -1 if reached end of stream
	 */
	 public int read(char[] cbuf) throws IOException{
		 return read(cbuf, 0, cbuf.length);
	 }
     

	/** Reads the next chars into the cbuf beginning at off until len chars have been read in, and encrypts it if encrypt was true. 
	 * @param	cbuf	An array of chars to be filled.
	 * @param	off		The index to begin filling the array from  
	 * @param	len		Number of chars to read into the array
	 * @return 	int 	number of bytes read in, returns -1 if reached end of stream
	 */
	public int read(char[] cbuf, int off, int len) throws IOException {
		 char[] temp = cbuf;
		 int read = in.read(temp, off, len);
		 if(read == -1) return -1;
		 else if(encrypt) System.arraycopy(encrypt(temp, off, len), 0, cbuf, 0, cbuf.length);
		 else System.arraycopy(decrypt(temp, off, len), 0, cbuf, 0, cbuf.length);;
		 
		 return read;
	}

	/** Encrypts a single char
	 * 
	 * @param e	the char to be encrypted
	 * @return	the encrypted char
	 */
	private static char encrypt(char e){
		char d = (char) Integer.rotateLeft(e, 1); 
		return d;
	}
	

	/** encrypts a char array from the offset by len chars
	 * 
	 * @param d		The char [] to encrypts
	 * @param off	the starting position
	 * @param len	the number of char's to encrypt
	 * @return		the encrypted array.
	 */
	private static char[] encrypt (char[] e, int off, int len){
		char[] encrypted = new char[e.length];
		for (int i = off ; i < (off + len) ; i++){
			encrypted[i] = encrypt(e[i]);
		}
		return encrypted;
	}
	
	
	/** decrypts a single char
	 * 
	 * @param e	the char to be decrypted
	 * @return	the decrypted char
	 */
	private static char decrypt(char d){
		char e = (char) Integer.rotateRight(d, 1); 
		return e;
	}
	
	
	/** decrypts a char array from the offset by len chars
	 * 
	 * @param d		The char [] to decrypt
	 * @param off	the starting position
	 * @param len	the number of char's to decrypt
	 * @return		the decypted array.
	 */
	private static char[] decrypt (char[] d, int off, int len){
		char[] decrypted = new char[d.length];
		for (int i = off ; i < (off + len) ; i++){
			decrypted[i] = decrypt(d[i]);
		}
		return decrypted;
	}
	
}
