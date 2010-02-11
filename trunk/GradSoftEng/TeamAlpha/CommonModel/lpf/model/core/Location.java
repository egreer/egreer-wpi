package lpf.model.core;

/**
 * 
 * Change History
 *  - changed equals to compare lower and upper case  (ND)
 */
public class Location {
	public final int row;
	public final char column;
	
	
	public Location(int row, char col) {
		this.row = row;
		this.column = col; 
	}
	
	public boolean equals(Object o) {
		if (o instanceof Location){
			Location l = (Location)o;
			if (l.row == this.row)
				if (Character.toLowerCase(this.column) == Character.toLowerCase(l.column))
					return true;
		}
		return false;
	}
	
	public String toString(){
		return "[" + row + "," + column + "]";
	}
}
