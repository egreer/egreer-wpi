/*
 * Created on Aug 23, 2008
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu. 
 *
 * The program is provided under the terms and conditions of
 * the Eclipse Public License Version 1.0 ("EPL"). A copy of the EPL
 * is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package cs4233.hw3;

/**
 * A simple runtime exception that is used in homework assignment 3.
 * 
 * @author gpollice
 * @version 23-Aug-2008
 */
public class HW3Exception extends RuntimeException
{
	/**
	 * Sole constructor that takes a message describing the exception.
	 * 
	 * @param message the exceptions message
	 */
	public HW3Exception(String message)
	{
		super(message);
	}
}
