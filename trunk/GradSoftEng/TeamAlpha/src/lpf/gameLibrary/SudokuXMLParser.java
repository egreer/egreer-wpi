package lpf.gameLibrary;

import java.io.File;
import java.net.URL;

import javax.naming.directory.SchemaViolationException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import lpf.model.core.Grid;
import lpf.model.core.Location;
import lpf.model.core.Puzzle;
import lpf.model.core.Value;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Parses the XML schema for a logic puzzle game pertaining to sudoku
 * 
 * @author Eric Greer
 *
 */
public class SudokuXMLParser {
	public static final String schemaURL = "http://users.wpi.edu/~heineman/lpf/lpf.xsd";

	/**
	 * Creates a Puzzle from the given XML file following the online schema
	 * 
	 * @param p	The files to create the puzzle from (must be a .xml file) 
	 * @return	Returns a puzzle created from the file, returns null if the xml file passed is not a 
	 * 			valid Soduku Puzzle, or if there are errors in the XML. 
	 */
	public static Puzzle createSodukuPuzzle(File p){
		Puzzle returner = null;
		
		
		//Verify File is an XML File
		if (p.getName().endsWith(".xml") || p.getName().endsWith(".XML")){

			//If an exception is thrown then the puzzle is not valid and null object is returned
			try {
				//Validate XML matches schema. Taken from the Examples project 
				SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				Schema schema = sf.newSchema(new URL(schemaURL));
				Validator validator = schema.newValidator();
				validator.validate(new StreamSource(p));

				//Parse the XML
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(p);

				//verify that XML it is a Sudoku puzzle
				doc.getDocumentElement().normalize();
								
				Element puzzleNode = (Element) doc.getElementsByTagName("puzzle").item(0);
			
				if( puzzleNode.getAttribute("type").equals("sudoku")){
					returner = createPuzzle((Element) puzzleNode);
				}

			}catch (Exception e){
				/* DO NOTHING: By catching all exceptions we allow the program 
				 * to fail softly. The return puzzle is a null object if there
				 * is a failure.
				 */
			}
		}

		return returner;

	}


	/**
	 * Creates a puzzle from the XML element puzzle
	 * 
	 * @param puzzle	The puzzle element that contains the information to make a puzzle
	 * @return			Returns a Puzzle object if the puzzle was able to be created, null otherwise
	 * @throws SchemaViolationException		Throws Exception if the XML tags do not match the provided information in the attributes
	 */
	private static Puzzle createPuzzle(Element puzzle) throws SchemaViolationException{
		//obtain Puzzle attributes
		int height = Integer.parseInt(puzzle.getAttribute("height"));
		int width = Integer.parseInt(puzzle.getAttribute("width"));
		int difficulty = Integer.parseInt(puzzle.getAttribute("difficulty"));
		NodeList boards = puzzle.getElementsByTagName("board");

		//read XML to Create puzzle's initial grid, and solution grid
		Grid sol = createGrid(getNamedBoard(boards, "solution"), height, width);
		Grid init = createGrid(getNamedBoard(boards, "initial"), height, width);

		//Create puzzle, and set initial grid, and difficulty
		Puzzle returner = new Puzzle(sol);
		returner.setInitialGrid(init);
		returner.setDifficulty(difficulty);

		return returner;
	}

	/** 
	 * Creates a grid from the rows contained in the XML grid tags
	 * 
	 * @param grid		The element containing the row tags 
	 * @param height	The height of the grid 
	 * @param width		The width of the grid
	 * @return			returns a Grid of the information contained in the grid element
	 * @throws SchemaViolationException		throws Exception if the details of the row tags don't match given height and width.
	 */
	private static Grid createGrid(Element grid, int height, int width)throws SchemaViolationException{
		NodeList rows = grid.getElementsByTagName("row");
		if(rows.getLength() != height) throw new SchemaViolationException("Invalid Number of Rows: " + rows.getLength() + " for given Height:" + height);

		Grid returner = new Grid(height, width);

		//Parse the rows
		for(int i = 0 ; i < height ; i++){
			char currColumn = 'A';
			String row = ((Element) rows.item(i)).getAttribute("contents");
			if( row.length() != width) throw new SchemaViolationException("Invalid Row Width: " + row.length() + " for given Width:" + width); 

			//Parse values and add to grid
			for (int j = 0 ; j < width ; j++){
				char value = row.charAt(j);
				if (value != '-') returner.getCellAtLocation(new Location(i+1, currColumn)).setDigit(new Value(value));
				currColumn++;
			}
		}

		return returner;
	}

	/**
	 * Retrieves the given board from the provided list of boards 
	 * 
	 * @param boards	The list of boards to iterate over
	 * @param name		the name of the board to search for
	 * @return			the correct board element 
	 * @throws SchemaViolationException		throws Exception if the board is not found 
	 */
	private static Element getNamedBoard(NodeList boards, String name) throws SchemaViolationException{
		for(int i = 0; i < boards.getLength(); i++){
			Element test =(Element) boards.item(i);
			if(test.getAttribute("name").equals(name)){
				return test;
			}
		}
		throw new SchemaViolationException("Board: "+ name + " was not found in XML");
	}
}
