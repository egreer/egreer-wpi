package lpf.gameLibrary;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Extractor for a ZipFile
 *
 * @author Nik Deapen
 * @stolenFrom Eric Greer
 *
 */
public class ZipFileLoader {
	
	private int bufferSize = 2048;
	
	/**
	 * Extracts the files contained inside the specified ZipFile
	 * 			
	 * @param zf			the ZipFile containing the files
	 * @return				an ArrayList of files that were in the ZipFile
	 * @throws IOException
	 */
	public ArrayList<File> loadZipFile(ZipFile zf) throws IOException {
		Enumeration<? extends ZipEntry> entries = zf.entries();
		
		ArrayList<File> files = new ArrayList<File>();
		
		while(entries.hasMoreElements()){
			ZipEntry entry = entries.nextElement();
			BufferedInputStream in = new BufferedInputStream(zf.getInputStream(entry));
			
			// create and write to the file
			File f = new File(entry.getName());
			
			byte[] buffer = new byte[bufferSize];
			FileOutputStream fout = new FileOutputStream(f);
			BufferedOutputStream bout = new BufferedOutputStream(fout, bufferSize);			
			
			int count;
			while ((count = in.read(buffer,0,bufferSize)) != -1){
				bout.write(buffer,0,count);
			}
			
			// flush and close
			bout.flush();
			bout.close();
			in.close();
			
			// add the file to the array list
			files.add(f);
		}
		
		return files;
	}
}
