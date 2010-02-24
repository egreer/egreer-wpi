package egreer.lab5;

import java.util.*;

public class LabStack {
	/** Store all information using an ArrayList. */
	ArrayList info;

	/** Define the constructor. */
	public LabStack() {
		info = new ArrayList();
		info.add(new Integer(0));
	}

	/**
	 * Determines if stack is empty.
	 * @return    true if the stack contains no information; false otherwise.
	 */
	public boolean empty() {
		if(info.isEmpty()){
			return true;
		}
		return false;
	}

	/**
	 * Add an object onto the stack so that it will become the next 'top' of the stack.
	 * @param o   Object to be placed on the top of the stack.
	 */
	public void push(Object o) {
		this.info.add(0,o);
	}

	/**
	 * Pop an object from the top of stack, reducing the size of the stack by one.
	 * @return    Object that had been previously at the top of the stack.
	 * @exception java.util.EmptyStackException    if stack had been empty.
	 */
	public Object pop() throws EmptyStackException{
		if(this.empty()){
			throw new EmptyStackException();
		}
		Object temp = info.get(0);
		this.info.remove(0);
		
		return temp;
	}

	/**
	 * Without altering the stack, return the top-most element on the stack.
	 * @return    Object that is at the top of the stack.
	 * @exception java.util.EmptyStackException    if stack is empty.
	 */
	public Object top() throws EmptyStackException{
		if(this.empty()){
			throw new EmptyStackException();
		}
		
		return this.info.get(0);
	}
	
}

