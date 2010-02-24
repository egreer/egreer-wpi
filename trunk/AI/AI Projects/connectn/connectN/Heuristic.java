package connectN;

/**
 * An object that represnet the Heuristic value of a board state of a game of connect n for an arbitrary value n.
 * 
 * @author egreer & shl
 *
 */
public class Heuristic implements Comparable<Heuristic>{
	int[] values;
	
	/**
	 * Heuristic requires a matrix of size 2n where n is the number of pieces in a row you need to get to win in order to work for a game.
	 * 
	 * @param v
	 */
	public Heuristic(int[] v){
		values = v;
	}
	
	/**
	 * Return the matrix that stores the Heuristic.
	 * 
	 * @return the matrix that stores the Heuristic
	 */
	public int[] getValues(){
		return values;
	}
	
	/**
	 * Sets the value at location i in the array equal to v.
	 * 
	 * @param i
	 * @param v
	 */
	public void setValues(int i, int v){
		values[i] = v;
	}
	
	/**
	 * Compares this Heuristic's value to that of another.
	 * 
	 * @param arg0	the Heuristic to compare to
	 * @return		1 if this object is greater, 0 if equal, -1 if smaller
	 */
	public int compareTo(Heuristic arg0) {
		int[] othervalues = arg0.getValues();
		for(int i = values.length - 1; i >= 0; i--){
			if(values[i] < othervalues[i]){
				return -1;
			}if(values[i] > othervalues[i]){
				return 1;
			}
		}
		return 0;
	}
	
	/**
	 * Adds this Heuristic's value to that of another and returns a new Heuristic as a result.
	 * 
	 * @param arg0	the Heuristic to add to
	 * @return		the new Heuristic
	 */
	public Heuristic add(Heuristic arg0) {
		int[] othervalues = arg0.getValues();
		Heuristic nh = new Heuristic(new int[values.length]);
		for(int i = values.length - 1; i >= 0; i--){
			nh.setValues(i, values[i] + othervalues[i]);
		}
		return nh;
	}
}
