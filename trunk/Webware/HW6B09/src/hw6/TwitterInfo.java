/*
 * Created on Dec 3, 2009
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu.
 * 
 * The program is provided under the terms and conditions of the Eclipse Public License Version 1.0
 * ("EPL"). A copy of the EPL is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package hw6;

import java.io.*;
import java.net.*;
import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
 
/** This class gets all Twitter information
 * 
 * 
 * @author egreer
 */
public class TwitterInfo
{
	static private enum OutputType {
		TEXT, HTML, XML
	};

	static private final OutputType outputType = OutputType.XML;
	static public String username;
	
	/** Retrieved the user's name, profile picture, username and number of followers
	 * from the Twitter API
	 * 
	 * @param userName		The username of the account
	 * @return				Returns the user info as a string
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public static String getUserInfo(String userName) throws IOException,
			ParserConfigurationException, SAXException
	{
		username = userName;
		
		StringBuffer userInfo = new StringBuffer();
		URL url = new URL("http://www.twitter.com/users/show/" + userName + ".xml");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(false);
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			// XML
			userInfo.append(getXmlUserInfo(connection));
			
		} else {
			// Server returned HTTP error code.
			userInfo.append(errorPage(connection.getResponseCode()));
		}

		return userInfo.toString();
	}

	/** Parses the received XML into HTML string
	 * 
	 * @param connection
	 * @return				Formatted HTML with user's information
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	private static StringBuffer getXmlUserInfo(HttpURLConnection connection) throws IOException,
			ParserConfigurationException, SAXException
	{
		StringBuffer xmlOutput = new StringBuffer();
		InputStream responseBody = connection.getInputStream();
		// Turn the response entity-body into an XML document.
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(responseBody);
		
		xmlOutput.append("<h1> User Profile </h1>");
		
		NodeList nodes = doc.getElementsByTagName("name");
		if (nodes.getLength() != 0) {
			Node n = nodes.item(0);
			String s = n.getTextContent();
			xmlOutput.append("<strong>Name: </strong>" + s);
			xmlOutput.append("<br />");
		}
		
		nodes = doc.getElementsByTagName("screen_name");
		if (nodes.getLength() != 0) {
			Node n = nodes.item(0);
			String s = n.getTextContent();
			xmlOutput.append("<strong>Screen Name: </strong>" + s);
			xmlOutput.append("<br />");
		}
		
		//NOTE: Every one has a default picture provided by twitter
		nodes = doc.getElementsByTagName("profile_image_url");
		if (nodes.getLength() != 0) {
			Node n = nodes.item(0);
			String s = n.getTextContent();
			xmlOutput.append("<img src=\"" + s + "\" alt=\"No User Image Was Found\" />");
			xmlOutput.append("<br />");
		}
		
		nodes = doc.getElementsByTagName("followers_count");
		if (nodes.getLength() != 0) {
			Node n = nodes.item(0);
			String s = n.getTextContent();
			xmlOutput.append("<strong>Followers: </strong><a href=\"./followers.jsp?userName="+ username + "\">" + s + "</a>");
			xmlOutput.append("<br />");
		}
		
		responseBody.close();
		return xmlOutput;
	}
	
	/** Creates the error page with the given error code 
	 * 
	 * @param errorCode	The error code to put on the page
	 * @return	The HTML code for the page 
	 */
	private static StringBuffer errorPage(int errorCode){
		StringBuffer errorPage = new StringBuffer();
		errorPage.append("<h1 class=\"error\"> Error </h1>");
		errorPage.append("<p class=\"error\"> A " + errorCode + " error occured accessing username: <strong>"+ username + "</strong> please return to the prior page.</p>");
		return errorPage;
		
	}
	
	/** Retrieves the user's followers from the twitter API 
	 * 
	 * @param userName	The username
	 * @return			The page of followers
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public static String getUserFollowers(String userName) throws IOException,
	ParserConfigurationException, SAXException
	{
		username = userName;
		StringBuffer userInfo = new StringBuffer();
		URL url = new URL("http://www.twitter.com/statuses/followers/" + userName + ".xml");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(false);

		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// XML
				userInfo.append(getXmlFollowers(connection));
		} else {
			// Server returned HTTP error code.
			userInfo.append(errorPage(connection.getResponseCode()));
		}
			
		return userInfo.toString();	
	}
	
	/** Retrieves the followers of the user from the XML and creates and HTML string
	 * 
	 * @param connection
	 * @return				Returns a formated HTML page
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	private static StringBuffer getXmlFollowers(HttpURLConnection connection) throws IOException, ParserConfigurationException, SAXException{
		StringBuffer xmlOutput = new StringBuffer();
		InputStream responseBody = connection.getInputStream();
		// Turn the response entity-body into an XML document.
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(responseBody);
		xmlOutput.append("<h1>"+ username +"'s Followers:</h1>");
		xmlOutput.append("<div class=\"list\">");
		xmlOutput.append("<ol>");
		String style = "visable";
				
		NodeList nodes = doc.getElementsByTagName("screen_name");
		NodeList names = doc.getElementsByTagName("name");
		if (nodes.getLength() != 0) {
			for(int i = 0; i < nodes.getLength() ; i++){
				if (i == 20) style = "invis";
				Node n = nodes.item(i);
				Node m = names.item(i);
				String s = n.getTextContent();
				String t = m.getTextContent();
				xmlOutput.append("<li id=\"" + i +"\" class=\""+ style + "\"><span>" + t + " (<a href=\"./userinfo.jsp?userName="+ s + "\">" + s + "</a>)</span></li>");
			}
		}
			
		xmlOutput.append("</ol>");
		xmlOutput.append("</div>");
		
		//Pagination
		xmlOutput.append("<div class=\"center\">");
		int next;
		int pgnum = 0;
		xmlOutput.append("<a id=\"first\" href=\"javascript:clickPage(1)\"><button class=\"navigation\" type=\"button\"> << </button></a>&nbsp;");
		xmlOutput.append("<a id=\"previous\" href=\"javascript:clickPage(1)\"><button class=\"navigation\" type=\"button\"> < </button></a>&nbsp;");
		for (int i = 0 ; i < nodes.getLength() ; i++){
			if (i + 20 < nodes.getLength()) next = i + 19;
			else next = nodes.getLength() - 1;
			if (i%20 == 0){
				pgnum++;
				xmlOutput.append("<a id=\"pg" + pgnum + "\"href=\"javascript:setVisable("+ pgnum +","+ i +"," + next +")\"><button class=\"navigation\" type=\"button\">" + pgnum + "</button></a>&nbsp;");
			}
		}
		if (pgnum > 1) xmlOutput.append("<a id=\"next\" href=\"javascript:clickPage(2)\"><button class=\"navigation\" type=\"button\"> > </button></a>&nbsp;");
		else xmlOutput.append("<a id=\"next\" href=\"javascript:clickPage(1)\"><button class=\"navigation\" type=\"button\"> > </button></a>&nbsp;");
		
		xmlOutput.append("<a id=\"last\" href=\"javascript:clickPage("+ pgnum +")\"><button class=\"navigation\" type=\"button\"> >> </button></a>&nbsp;");
		xmlOutput.append("<br>");
		xmlOutput.append("</div>");
		return xmlOutput;		
	}
}
