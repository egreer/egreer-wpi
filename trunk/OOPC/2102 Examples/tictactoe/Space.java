package tictactoe;

/**
 * Represents a space on the grid.
 * 
 * @author heineman
 */
public class Space {

	/** Row. */
	int row;
	
	/** Column. */
	int column;
	
	/**
	 * Constructor for a Space.
	 * 
	 * @param row
	 * @param col
	 */
	public Space (int row, int col) {
		this.row = row;
		this.column = col;
	}
	
	/**
	 * Return row for this space.
	 * 
	 * @return  row for space.
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Return column for this space.
	 * 
	 * @return  column for space.
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Determine if two Space objects are equal.
	 * @param o   object for comparison
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		if (o.getClass() == getClass()) {
			Space s = (Space) o;
			return row == s.row && column == s.column;
		}
		
		return false;  // nope;
	}
	
	/**
	 * Return string representation.
	 */
	public String toString () {
		return "<" + row + ',' + column + ">";
	}

}
