package ks.framework.common;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Wrapper class for an XML Document that conforms to a schema 
 * document configured at run-time.
 * <p>
 * The configuration is allowed only once and must be done before
 * any messages are constructed. 
 * <p>
 * The full set of commands, requests and adminCommands can be 
 * found at http://www.wpi.edu/~heineman/xml.
 * <p>
 * One could envision writing a series of subclasses, each representing
 * the different types of messages, requests and adminCommands. For now,
 * however, I am taking the lightweight approach and expecting that
 * the user of this class will be able to do the requisite XML
 * parsing to retrieve the desired information.
 * <p>
 * Every Message is from a specific user and is intended to be sent
 * to a specific user. If the message is to be broadcast, then it
 * can be so marked. A message can be marked as Local. This implies
 * that it will not be distributed over the network, but rather handled
 * by local processing.
 * <p>
 * A Message is serializable to ensure transport over the network.
 * 
 * @author George Heineman
 */
public class Message implements java.io.Serializable {
	/**
	 * Serialization ID for consistency through upgrades.
	 */
	private static final long serialVersionUID = 3759474912651841363L;

	/** Debug mode. */
	static boolean DEBUG = false;
	
	/** Tag used to validate the XML fragment. From ks.xsd schema. */
	public static final String xsiString = "xsi:noNamespaceSchemaLocation";
	
	/** Valid schema definition. */
	private static String xsdNameString;
	
	/** URL for this schema. */
	private static URL xsdURL = null;
	
	/** Parent URL for this schema (as a string). */
	private static String xsdParent = null;
	
	/** Version tag name. */
	public static final String versionTAG = "version";
	
	/** ID tag name. */
	public static final String idTAG = "id";
	
	/** 
	 * Singleton Schema to be used for all message processing.
	 * <p>
	 * Lazy evaluation. Only created when needed. */
	private static Schema schema; 
	
	/** Validator for XML documents. Constructed as needed. */
	private static Validator validator = null;
	
	/** Determine whether initialization must be performed (in lazy evaluation). */
	private static boolean initialized = false;
	
	/** Builder to use when constructing Message objects. */
	private static DocumentBuilder builder = null;
	
	/** Type of this message. */
	public final MessageType type;
	
	/** Contents of this message. */
	final Node contents;
	
	/** Name of this command, request of adminCommand. */
	public final String name; 
	
	/** Version or protocol being spoken by message. */
	public final String version;
	
	/** If no version, then value defaults to this one. */
	public final String versionUnknown = "0.0";
	
	/** If no id, then ID value defaults to this one. */
	public final String idUnknown = "0";
	
	/** 
	 * ID of message. While not unique globally, may be used within client
	 * to correlate a request with a command. 
	 */
	public final String id;
	
	/** Is this a broadcast message? */
	boolean broadcast = false;
	
	/** Is this a message to be handled locally? */
	boolean local = false;
	
	/** The originator of the message. */
	String originator = null; 
	
	/** The recipient of the message. */
	String recipient = null;
	
	/** 
	 * Configure Message class to know of XSD schema definition file.
	 * <p>
	 * If called multiple times, an {@link IllegalStateException} is raised.
	 * 
	 * @param name    name of xsd file
	 * @param xsd     URL to the xsd file 
	 */
	public static void configure (String name, URL xsd) throws IllegalStateException {
		if (xsdURL != null) {
			throw new IllegalStateException("Message already configured to use:" + xsdURL);
		}
		
		xsdNameString = name;
		String s = xsd.getPath();
		int idx = s.lastIndexOf('/');  // go back one level
		xsdParent = s.substring(0,idx);
		xsdURL = xsd;
	}
	
	/** Helper enumerated type to keep track of the types of messages. */
	public enum MessageType {
		Unknown("Unknown"),
		Command("Command"),
		Request("Request"),
		AdminCommand("Admin-Command");
		
		/** Record string information of the type, for toString(). */
		private String type;
		
		/** Record the type. */
		private MessageType (String type) {
			this.type = type;
		}
		
		/** Helper method to output String representation of type. */
		public String toString () {
			return type;
		}
		
		/** Method to easily compare against constants or other strings. */
		public boolean same (Object o) {
            if (o == null) { return false; }
            if (o instanceof MessageType) {
                return this == ((MessageType) o);
            }

            // compare strings?
            return (this.toString().equalsIgnoreCase(o.toString()));
        }
	}
	
	private static Schema compileSchema(URL schema) throws SAXException {
		//Get the SchemaFactory instance which understands W3C XML Schema language
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		return sf.newSchema(schema);
	}

	private static boolean initialize() {
		try {
			// parse schema first, see compileSchema function to see how Schema object is obtained.
			schema = compileSchema(xsdURL);
	
			// this "Schema" object is used to create "Validator" which
			// can be used to validate instance document against the schema
			// or set of schemas "Schema" object represents.
			validator = schema.newValidator();
			
			// set ErrorHandle on this validator
			validator.setErrorHandler(new MyErrorHandler());

            // now show how to access its information
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
           
			initialized = true;
		} catch (Exception e) {
			initialized = false;
		}
		
		return initialized;
	}
	
	/**
	 * If the contents node has an Element child, this fetches the first Node
	 * object and returns it.
	 * 
	 * The first child is the actual command, request or admin-command
	 * 
	 * @return  Node which represents command, request or admin-command.
	 */
	public Node contentsChild() {
		
        NodeList children = contents.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node n = children.item(i);

            if (n.getNodeType() == Node.ELEMENT_NODE) {
            	return n;
            }
        }
        
        return null;
	}
	
	/**
	 * Return Document object that contains the Message as scanned.
	 * <p>
	 * Make method synchronized since parse() is not thread safe.
	 * Thus we ensure serialized access to the parsing routines.
	 * 
	 * @param    contents   the XML fragment to be encapsulated within a Message. 
	 * @return   Document object representing the Message.
	 */
	public static synchronized Document construct (String contents) {
		if (DEBUG) {
			System.out.println ("constructing:" + contents);
		}
		
		if (!initialized) { 
			if (!initialize()) { return null; }
		}
		
		// HACK: TODO: FIX HERE
		
		// construct the Document.
		String fragHeader = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<message xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n" +
			"xsi:schemaLocation='" + xsdParent + " \n" + xsdURL + "'\n" +
            "xsi:noNamespaceSchemaLocation='" + xsdNameString + "'>";	
		
		String fragTrailer = "</message>";
		
		try {
			String combined = fragHeader + contents + fragTrailer;
			InputSource is = new InputSource (new StringReader (combined));
			validator.validate (new SAXSource (new InputSource (new StringReader (combined))));
			return builder.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Validates on construction.
	 * 
	 * @param doc
	 * @exception if doc isn't an XML fragment from the ks.xsd schema
	 */
	public Message (Document doc)  {
		try {
			Element msg = doc.getDocumentElement();
	        NamedNodeMap nnm = msg.getAttributes();
	        
	        Node xsi = nnm.getNamedItem(xsiString);
	
	        // must be ks.xsd
	        String s = xsi.getNodeValue();
	        if (!s.equals (xsdNameString)) {
	        	throw new RuntimeException ("Message received XML fragment not validated by" + xsdNameString);
	        }
	        
            // since we have done some work, record the type of message
            MessageType t = MessageType.Unknown;
            Node c = null;
            NodeList children = msg.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node n = children.item(i);

                String name = n.getNodeName();
                 if (MessageType.Command.same(name)) {
                    t = MessageType.Command;
                    c = n;
                } else if (MessageType.Request.same(name)) {
                    t = MessageType.Request;
                    c = n;
                } else if (MessageType.AdminCommand.same(name)) {
                    t = MessageType.AdminCommand;
                    c = n;
                }
            }

            if (t == MessageType.Unknown) {
                throw new RuntimeException ("Message is still of unknown type somehow.");
            }
	        
            // get version and ID for message
            NamedNodeMap nnmc = c.getAttributes();
            Node nv = nnmc.getNamedItem(versionTAG);
            if (nv != null) {
            	this.version = nv.getNodeValue();
            } else {
            	this.version = versionUnknown;
            }
            Node ni = nnmc.getNamedItem(idTAG);
            if (ni != null) {
            	this.id = ni.getNodeValue();
            } else {
            	this.id = idUnknown;
            }
            
	        // Finally! Retrieve the name of the enclosed content type.
	        children = c.getChildNodes();
	        String name = null;
	        Node n = null;
	        for (int i = 0; i < children.getLength(); i++) {
	        	n = children.item(i);
	        	
	        	// found it! There shall be only one
	        	if (n.getNodeType() == Node.ELEMENT_NODE) {
	        		name = n.getNodeName();
	        		break;
	        	}
	        }
	        
	        // set all final fields.
	        this.type = t;
	        this.name = name;
	        this.contents = n;
		} catch (Exception e) {
			throw new RuntimeException ("Message received XML fragment not validated by" + xsdNameString);
		}
	}
	
	/** Return the contents of this message. */
	public Node contents() {
		return contents;
	}
	
	/** Return the name of command, request or adminCommand. */
	public String getName() {
		return name;
	}
	
	/**
	 * Is this a command message?
	 */
	public boolean isCommand() { 
		return (type == MessageType.Command);		
	}
	
	/**
	 * Is this a command message?
	 */
	public boolean isRequest() { 
		return (type == MessageType.Request);
	}
	
	/**
	 * Is this a command message?
	 */
	public boolean isAdminCommand() { 
		return (type == MessageType.AdminCommand);	
	}
	
	/** Determine the originator of the message. */
	public void setOriginator (String name) {
		this.originator = name;
	}
	
	/** Retrieve the originator of the message. */
	public String getOriginator () {
		return originator;
	}
	
	/**
	 * Determine the intended recipient of the message.
	 * <p>
	 * If one wishes a message to be broadcast, use the 
	 * setBroadcast() message.
	 */
	public void setRecipient (String name) {
		this.recipient = name;
		broadcast = false;
	}
	
	/**
	 * Assign the message to be broadcast.
	 * <p>
	 * If one wishes a message to be sent to a single recipient, use
	 * the setRecipient(String) method.
	 */
	public void setBroadcast() {
		this.recipient = null;
		broadcast = true;
	}
	
	/** Is this message to be broadcast? */
	public boolean isBroadcast() {
		return broadcast;
	}

	/** Designate message to be handled locally. */
	public void setLocal() {
		this.local = true;
	}
	
	/** Is this message to be handled locally? */
	public boolean isLocal() {
		return local;
	}
	
	/** Return the recipient (or null if a broadcast message). */
	public String getRecipient() {
		return recipient;
	}
	
	public String toString () {
		return "Message[" + type + "] " + name;
	}
}
