package hd.server.timesheet;

import hd.client.timesheet.Day;
import hd.server.ServerFunctions;

import java.io.StringReader;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Parses the XML schema for WhenToWork 
 * 
 * @author Eric Greer
 *
 */
public class W2WXMLParser {
	//TODO Validate versus a schema
	//public static final String schemaURL = "http://users.wpi.edu/~heineman/lpf/lpf.xsd";

	/**
	 * Creates a Puzzle from the given XML file following the online schema
	 * 
	 * @param fileName 	The file's name (must be a .xml || .XML file) 
	 * @param file		A String representation of the file's contents 
	 * @return			Returns Information about which timesheets were created
	 */
	public static String createTimesheets(String fileName, String file){
		
		String returner = "";

		//Verify File is an XML File
		if (fileName.endsWith(".xml") || fileName.endsWith(".XML")){

			//If an exception is thrown then the puzzle is not valid and null object is returned
			try {
				//Validate XML matches schema. Taken from the Examples project 
				//	SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				//	Schema schema = sf.newSchema(new URL(schemaURL));
				//	Validator validator = schema.newValidator();
				//	validator.validate(new StreamSource(p));
				
				InputSource is = new InputSource(new StringReader(file));
				//Parse the XML
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				
				DocumentBuilder db = dbf.newDocumentBuilder();
				
				Document doc = db.parse(is);
				
				doc.getDocumentElement().normalize();

				Element recordsNode = (Element) doc.getElementsByTagName("RECORDS").item(0);

				NodeList record = recordsNode.getElementsByTagName("ROW");
				returner += "There are " + record.getLength() + " Records in the XML file\n";
				
				int shifts = 0; 
				Date impdate = new Date(); 
				for(int i = 0 ; i < record.getLength() ; i++){
					Element recordElement = (Element) record.item(i);
					String firstname = recordElement.getAttribute("Employee_First_Name");
					String lastname = recordElement.getAttribute("Employee_Last_Name");
					Date date = ServerFunctions.ParseDate(recordElement.getAttribute("Date").trim());
					Double duration = Double.valueOf(recordElement.getAttribute("Duration").trim());
					Day day = Day.whichDay(Integer.valueOf(recordElement.getAttribute("Day_Of_Week").trim()));
					boolean created = TimesheetBuilder.processShift(firstname, lastname, date, duration, day, impdate);
					if (!created) returner += "ERROR shift not created: Shift " + i + ": Name=" + lastname + ", " + firstname + " Date=" + date.toString() + " Duration=" + duration + " Day=" + day + "\n";
					else shifts++;
				}
				
				returner += shifts + " were sucessfully created.\n";
				
			}catch (Exception e){
				/* TODO DO SOMETHING TO RETURN THE ERROR CONDITION 
				 * DO NOTHING: By catching all exceptions we allow the program 
				 * to fail softly. Then return false indicating a failure
				 */
				returner += "Exception Thrown: \n " + e.getMessage() + "\n";
				e.printStackTrace();
			}
		}

		return returner;
	}

}
