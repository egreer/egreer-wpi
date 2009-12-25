package hw7;

import java.io.IOException;
import javax.servlet.http.*;

/** This service retrieves a parameter from the get request, and 
 * outputs an XML document for the term in wich it was taken   
 * 
 * @author Eric Greer
 *
 */
@SuppressWarnings("serial")
public class DidITakeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String course = req.getParameter("course");
		if (course != null){
			resp.setContentType("text/XML");
			resp.getWriter().println(MyInfo.getInstance().DidITake(course));
		}
		else{
			resp.setContentType("text/HTML");
			resp.getWriter().println("Error, no paremeter \"course\" specified");
		}
	}
}
