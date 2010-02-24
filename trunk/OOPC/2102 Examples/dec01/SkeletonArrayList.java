package dec01;

/**
 * Show basics for how ArrayList will work.
 * 
 * @author heineman
 *
 */
public class SkeletonArrayList {

	/** Number of elements in Array. */
	int size;
	
	/** Elements. */
	Object [] elements;
	
	/**
	 * Default constructor, starting with an array of size 10.
	 *
	 */
	public SkeletonArrayList() {
		elements = new Object[10];
		size = 0;
	}
	
	/**
	 * Return the number of elements in the arraylist.
	 * 
	 * @return   number of elements in the arraylist.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Add item at the end of the arrayList.
	 * 
	 * @param o   object to add
	 */
	public void add (Object o) {
		ensureCapacity(size+1);
		elements[size] = o;
		
		size++;   //advance
	}
	
	 /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @throws    IndexOutOfBoundsException if index is out of range
     *		  <tt>(index &lt; 0 || index &gt; size())</tt>.
     */
    public void add(int index, Object element) {
    	if (index > size || index < 0) 
    		throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

    	ensureCapacity(size+1);
    	
    	// move [index,size] up to [index+1,size+1]
    	for (int i = size; i > index; i--) {
    		elements[i] = elements[i-1];
    	}
    	
    	elements[index] = element;
    	size++;
    }
	
	/**
	 * See if the object is equals to some object already in the array list.
	 * 
	 * @param o
	 * @return
	 */
	public boolean contains (Object o) {
		for (int i = 0; i < size; i++) {
			if (elements[i].equals(o)) {
				return true;
			}
		}
		
		return false;   // nope
	}
	
	/**
	 * String representation available.
	 */
	public String toString () {
		String ret = "[";
		for (int i = 0; i < size; i++) {
			ret += elements[i].toString();
			if (i < size-1) { ret += ","; }
		}
		
		return ret + "]";		
	}
	
	
	/**
	 * Ensure we have room for the given number of elements in the array.
	 * 
	 * @param newSize
	 */
	private void ensureCapacity(int minSize) {
		// already this big.
		if (minSize <= elements.length) {
			return;
		}
		
		// must Expand! Because we expanded once.
	    Object oldData[] = elements;
	    
	    // ask for 50% more than we need. This reduces the number of times we have 
	    // to keep on 'expanding'. The last thing we want to do is to perform this
	    // computation one byte at a time.
		int newCapacity = (elements.length * 3)/2 + 1;
	    if (newCapacity < minSize) {
			newCapacity = minSize;
	    }
	    
		Object newArray[] = new Object[newCapacity];
		
		// copy all old values.
		for (int i = 0; i < elements.length; i++) {
			newArray[i] = elements[i];
		}
		
		// Make this ours.
		elements = newArray;
	}
	
}
