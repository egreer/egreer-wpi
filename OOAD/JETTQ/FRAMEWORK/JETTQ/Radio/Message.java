/*
 * Created on Oct 10, 2008
 * 
 * JETTQ's framework for Sun Microsystem's SPOT (Small Programmable Object Technology).
 * Built for CS4233 in A-term of 2008.
 *
 * Copyright (C) 2008, Tim Navien, Eric Greer, Jason Codding, 
 * Qian Wei, Tyler Flaherty, Worcester Polytechnic Institute, JETTQ@wpi.edu.
 * 
 * The program is provided under the terms and conditions of the Eclipse Public License Version 1.0
 * ("EPL"). A copy of the EPL is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package JETTQ.Radio;

import JETTQ.JETTQManager;

/**
 * This class represents a message that is sent from one SPOT to another.
 * It has to and from fields, as well as a String for the content and a type
 * which is represented by an int (User defines it's meaning).
 * @author JETTQ
 *
 */
public class Message {
	private String fromAddr;
	private String toAddr;
	private int msgType;
	private String msgContents;
	
	/**
	 * Default public constructor
	 * @param to IEEE address of the SPOT the message is being sent to
	 * @param type Type of the message.
	 * @param contents String content of the message
	 */
	public Message(String to, int type, String contents) {
		this(JETTQManager.getIEEEAddress(),to,type,contents);
	}
	
	Message(String from, String to, int type, String contents) {
		JETTQManager.decho("Creating Message");
		this.fromAddr = from;
		this.toAddr = ((to == null) || to.equals("")) ? "broadcast" : to;
		this.msgType = type;
		this.msgContents = contents;
		JETTQManager.decho("Message Created:\n" + this.toString());
	}
	
	/**
	 * Sets the from address
	 * @param addr IEEE address of the SPOT sending the message.
	 */
	void setFrom(String addr) {
		this.fromAddr = addr;
	}
	
	/**
	 * Get the from address
	 * @return IEEE address of the SPOT sending the message
	 */
	public String getFrom() {
		return this.fromAddr;
	}
	
	/**
	 * Get the to address
	 * @return IEEE address of the SPOT the message is being sent to
	 */
	public String getTo() {
		return this.toAddr;
	}
	
	/**
	 * Get the type of the message
	 * @return Type of message
	 */
	public int getType() {
		return this.msgType;
	}
	
	/**
	 * Get the contents of the message
	 * @return String representing the contents
	 */
	public String getContents() {
		return this.msgContents;
	}
	
	/**
	 * Prints the message in the following form:
	 * =====MSG=====
	 * To: [toAddr]
	 * From: [fromAddr]
	 * Type: [type]
	 * Contents: [msgContents]
	 * =====END=====
	 */
	public String toString() {
		return "=====MSG=====\nTo: " + this.getTo() + "\nFrom: " + this.getFrom() + "\nType: " + this.getType() + "\nContents: " + this.getContents() + "\n=====END=====";
	}
}
