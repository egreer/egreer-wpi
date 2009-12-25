package hw3;

import java.io.PrintWriter;

/** This class deals with the error page
 * 
 * @author Eric Greer
 *
 */
public class ErrorMessage {
	//Error number to use for any invalid condition that is not input
	public static final int serverErrorNumber = -314; 
	
	/** Writes the error with the system error number   
	 * 
	 * @param output		The writer to write the file to.
	 */
	public static void writeErrorPage(PrintWriter output){
		writeErrorPage(output, serverErrorNumber);
	}
	
	/** Writes the error with the system error with the given group number
	 * 
	 * @param output		The writer to write the file to.
	 * @param groupNumber	The group number that was entered
	 */
	public static void writeErrorPage(PrintWriter output, int groupNumber){
		output.println("<html><head><title>CS4241-B09</title>");
		output.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"hw3.css\" title=\"Default Style\"/></head>");
		output.println("<body>");
		output.println("<!-- Eric Greer -->");
		output.println("<h1 class=\"error\">Error</h1>");
		    
		if(groupNumber == serverErrorNumber) {
			output.println("<p>The server cannot parse your input, make sure that you are using a number between 1 and 17</p>");
		}else{
			output.println("<p>Invalid group number: " + groupNumber + ", please enter a number between 1 and 17</p>");
		}
		
		output.println("<p><a href=\"..\">Return to the Main Page</a>");
		output.println("</body></html>");
	}
}
