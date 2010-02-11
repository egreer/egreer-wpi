package lpf.model.core;

import java.util.Iterator;



/**
 * @author Nik Deapen
 * @since 1.0.0
 */
public class Grid {
	
	/**
	 * Two Dimensional Array of Cells
	 * cell[1][2] = [row][column] = [2,C|c] 
	 */
	Cell[][] cells;
	
	/**
	 * The width of the grid in Cell's
	 */
	public final int width;
	
	/**
	 * The height of the grid in Cell's
	 */
	public final int height;
	
	
	/**
	 * Constructs a Grid with a given height and width
	 * @param height The height of the Grid
	 * @param width The width of the Grid
	 * @pre height and width are both positive nonzero values
	 * @pre there is enough memory to hold the grid in the JVM  << found this out the hard way
	 * @throws IllegalArgumentException if preconditions are not met
	 */
	public Grid(int height, int width) {
		if (height < 1 || width < 1)
			throw new IllegalArgumentException();
		this.width = width;
		this.height = height;
		cells = new Cell[height][width];
		
		// create the cells with their given location
		for (int h=0; h< cells.length; h++){
			for (int w=0; w < cells[h].length; w++){
				cells[h][w] = new Cell(new Location(h+1, (char)(w + 'a')));
			}
		}
		
	}
	
	/**
	 * Gets a cell at a given location
	 * @param loc the location of the cell
	 * @return the cell at the location
	 * @pre loc.row is a valid row
	 * @pre loc.col is a valid col
	 * @throws IllegalArgumentException if preconditions are not met
	 */
	public Cell getCellAtLocation(Location loc) {
		return this.cells[getRowNumber(loc.row)][getColNumber(loc.column)];
	}
	
	/**
	 * Gets the row number
	 * 	validates and returns row-1 (because arrays start at 0 and we start at 1)
	 * @param row the row number requested
	 * @return the index of the row
	 * @pre row is in the range of rows available
	 */
	private int getRowNumber(int row) {
		if (row < 1 || row > this.height)
			throw new IllegalArgumentException();
		return row-1;
	}

	/**
	 * Returns the column given a character
	 * @param column - the column requested
	 * @return the index of the requested column
	 * @pre {col is in a-z or A-Z}
	 * @pre {column is in reach of the width of the grid}
	 * @throws IllegalArgumentException if preconditions are not met
	 */
	private int getColNumber(char c) {
		int num;
		Character col = Character.toLowerCase(c);
		if (col >= 'a' && col <= 'z'){
			num = col-'a';
		}
		else 
			throw new IllegalArgumentException("Illegal Column Character"); // should never happen
		if (num >= this.width)
			throw new IllegalArgumentException("Column out of reach of Grid"); // should never happen
		return num;
	}

	/**
	 * Gets an Iterator of all the cells
	 * @return an iterator of all the cells
	 */
	public Iterator<Cell> iterator() {
		return new ArrayIterator<Cell>(this.cells);
	}

}