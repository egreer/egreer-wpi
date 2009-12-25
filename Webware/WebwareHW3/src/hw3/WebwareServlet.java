package hw3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

/** The main servlet
 * 
 * @author Eric Greer
 *
 */
@SuppressWarnings("serial")
public class WebwareServlet extends HttpServlet {
	
	/**
	 * Handles the post method
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		resp.setContentType("text/html");
		PrintWriter responseWrite = resp.getWriter();
		int group;
		
		//If there is invalid input generate the general server error page 
		try{
			String groupNumber = req.getParameter("groupnumber");
			group = Integer.valueOf(groupNumber);
			
		}catch (Exception e){
			ErrorMessage.writeErrorPage(responseWrite);
			return;
		}
		
		//If there is invalid input generate the error page with that group number
		if (1 > group || group > 17){
			ErrorMessage.writeErrorPage(responseWrite, group); 
			return;
		}
		
		//Generate the output for that group
		GroupMembers.writeMembersPage(responseWrite, group);
	}
}
