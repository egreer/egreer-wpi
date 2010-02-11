package lpf.GUIs;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * 
 * Views a given file
 * 
 * @author Nik Deapen
 *
 */
public class FileViewer {
	
	/**
	 * Opens a file in the default setting
	 * @param file - the file to open by the users operating system
	 * @throws IOException - if the file cannot be opened
	 */
	public void viewWebPage(File file) throws IOException{
		// get the desktop
		Desktop d = Desktop.getDesktop();
		
		// get and open the file
		d.browse(file.toURI());
		d.open(file);
	}
}
