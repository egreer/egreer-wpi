package lpf.configuration;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import lpf.GUIs.SudokuAlphaMainGUI;
import lpf.GUIs.GridView.GridView;
import lpf.gameLibrary.IPuzzleLibrary;
import lpf.gameLibrary.SudokuLibraryLoader;
import lpf.model.core.Puzzle;
import lpf.model.core.valueSets.CharacterSet;
import lpf.model.core.valueSets.OneToNineCharacterSet;

/**
 * 
 * The configuration for the current sudoku game
 * 
 * @author Nik Deapen
 *
 */
public class SudokuAlphaGameConfiguration {
	
	IPuzzleLibrary puzzleLibrary;
	int difficulty = 2;
	Puzzle curPuzzle = null;
	CharacterSet characterSet = new OneToNineCharacterSet();
	
	/**
	 * Gets the game's puzzle library
	 * 
	 * @return the current puzzle library
	 */
	public IPuzzleLibrary getPuzzleLibrary(){
		return puzzleLibrary;
	}
	
	/**
	 * Sets the current puzzle library
	 * 
	 * @param l - the new puzzle library
	 * @pre l != null
	 */
	public void setPuzzleLibrary(IPuzzleLibrary l){
		if (l == null)
			throw new IllegalArgumentException();
		this.puzzleLibrary = l;
	}
	
	/**
	 * Gets the game's difficulty
	 * 
	 * @return the difficutly
	 */
	public int getDifficulty(){
		return difficulty;
	}
	
	/**
	 * Sets the difficulty
	 * @param d - the new difficulty
	 * @pre d is in {1..10}
	 */
	public void setDifficulty(int d){
		if (d < 1 || d > 10)
			throw new IllegalArgumentException();
		this.difficulty = d;
	}
	
	/**
	 * Makes the SudokuAlphaGameConfiguration a Singleton
	 */
	static SudokuAlphaGameConfiguration myInstance;
	private SudokuAlphaGameConfiguration(){}
	public static SudokuAlphaGameConfiguration getInstance() {
		if (myInstance == null){
			myInstance = new SudokuAlphaGameConfiguration();
			try {
				String libraryLocation = "files/sudokuLibrary.zip";
				ZipFile zipfile = new ZipFile(libraryLocation);
				IPuzzleLibrary l = SudokuLibraryLoader.loadLibrary(zipfile);
				myInstance.setPuzzleLibrary(l);
			}
			catch (IOException ioe) {
				System.out.println("Could Not Load Library");
			}
		}
		
		return myInstance;
	}
	
	/**
	 * Gets the game's current puzzle
	 * 
	 * @return the current puzzle
	 */
	public Puzzle getCurrentPuzzle(){
		return this.curPuzzle;
	}
	
	/**
	 * Sets the game's current puzzle
	 * 
	 * @param p the new puzzle
	 */
	public void setPuzzle(Puzzle p){
		this.curPuzzle = p;
	}

	/**
	 * Gets the current Character Set
	 * 
	 * @return	the valid character set for this game
	 */
	public CharacterSet getCharacterSet() {
		return this.characterSet;
	}

	SudokuAlphaMainGUI gui;
	
	/**
	 * Set the GUI's GridView
	 * 
	 * @param gv		the GUI's new GridView
	 */
	public void setGridView(GridView gv) {
		this.gui.setGridView(gv);
	}
	
	/**
	 * Set the game's GUI
	 * 
	 * @param gui		the game's new GUI
	 */
	public void setGUI(SudokuAlphaMainGUI gui){
		this.gui = gui;
	}

	/**
	 * Repaints this game's GUI's grid
	 */
	public void repaintGrid() {
		this.gui.repaintGrid();
	}
	
	/**
	 * Gets this game's GUI
	 * 
	 * @return	the current GUI
	 */
	public SudokuAlphaMainGUI getGUI() {
		return gui;
	}
	
	/**
	 * Changes the current library for this game, selectable from
	 * a user's local location
	 */
	public void changeLibrary(){
		JFileChooser fileChooser = getFileChooser();
		int r = fileChooser.showOpenDialog(gui);
		
		if (r == JFileChooser.APPROVE_OPTION) {
			ZipFile zf = null;
			try {
				zf = new ZipFile(fileChooser.getSelectedFile().getAbsoluteFile());
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error Loading Library", "Error Loading Library", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				setPuzzleLibrary(SudokuLibraryLoader.loadLibrary(zf));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error Loading Library", "Error Loading Library", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Creates a file chooser
	 * 
	 * -- written after looking at team beta
	 * @return the FileChooser
	 */
	public JFileChooser getFileChooser(){
		JFileChooser fileChooser = new JFileChooser();		
		fileChooser.addChoosableFileFilter(new FileFilter(){
			@Override
			public boolean accept(File f) {
				if(f.isDirectory()) return true;
				return f.getName().toLowerCase().endsWith(".zip");					
			}

			@Override
			public String getDescription() {
				return "Sudoku Zip File Library";
			}
		});
			
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(false);
		
		return fileChooser;
	}
}
