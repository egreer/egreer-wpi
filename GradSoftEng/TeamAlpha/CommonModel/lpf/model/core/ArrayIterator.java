package lpf.model.core;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator of a one or two dimensional array, or any number of one dimensional arrays<br/>
 *  <b>Not More Than Two!!!</b>
 *  <br/>
 *  More than two arrays will come soon...
 * @author Nik Deapen
 * @param <T>
 */
public class ArrayIterator<T> implements Iterator<T>{
	
	T nextElement;
	int arrayCounter = 0;
	int elementCounter = 0;
	T[][] arrays;
	
	/**
	 * Constructs an iterator of all the elements in the given arrays
	 * Note: Does
	 * @param arrays the arrays or two dimensional array of T we want to iterate
	 * @pre arrays != null
	 * @pre arrays.length > 0
	 * @pre for 0 to arrays.length-1 -> arrays[i] != null
	 * @pre for 0 to arrays.length-1 -> arrays[i].length >= 0
	 */
	public ArrayIterator(T[] ... arrays){
		this.arrays = arrays;
		// check the pre conditions
		if (arrays == null)
			throw new IllegalArgumentException();
		if (arrays.length <= 0)
			throw new IllegalArgumentException();
		for (int i=0; i < arrays.length; i++){
			if (arrays[i] == null)
				throw new IllegalArgumentException();
			if (arrays[i].length <= 0)
				throw new IllegalArgumentException();
		}
		
		// set the first element in the array
		nextElement = arrays[0][0]; // there is guarunteed to be an object in this location with the preconditions
	}
	

	@Override
	public boolean hasNext() {
		return nextElement != null;
	}

	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();
		
		T myElement =  arrays[arrayCounter][elementCounter];
		
		// increment to the next element
		incrementElement();
		
		return myElement;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	private void incrementElement(){
		elementCounter++;
		if (elementCounter == arrays[arrayCounter].length){
			arrayCounter++;
			elementCounter = 0;
		}
		if (arrayCounter == arrays.length)
			nextElement = null;
		else
			nextElement = arrays[arrayCounter][elementCounter];
		return;
	}
}
