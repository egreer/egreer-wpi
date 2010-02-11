package lpf.gameLibrary;

import java.io.*;
import java.util.Iterator;
import java.util.zip.*;

import lpf.model.core.Puzzle;

/**
 * 
 * A loader for a sudoku game library
 * 
 * @author Eric Greer
 *
 */
public class SudokuLibraryLoader {

	/**
	 * Loads a sudoku game library from the specified ZipFile
	 * 
	 * @param zipFileName	The file name of the zip file library
	 * @throws IOException	Throws exception if file does not exist,
	 * @update - changed method to get a zip-file instead of a string (ND)
	 */
	public static IPuzzleLibrary loadLibrary(ZipFile zipfile) throws IOException{

		//Get/create library to load
		SudokuGameLibrary lib = new SudokuGameLibrary();

		//Open Zip file
		ZipFileLoader zfl = new ZipFileLoader();
		Iterator<File> files = zfl.loadZipFile(zipfile).iterator();
		
		while(files.hasNext()){
			//parse xml file into a puzzle
			File tempFile = files.next();
			Puzzle temp = SudokuXMLParser.createSodukuPuzzle(tempFile);

			tempFile.delete();
			//Add Puzzle to Library
			if (temp != null) lib.addPuzzle(temp);
		}
		
		//Return Library
		return lib;
	}


}
