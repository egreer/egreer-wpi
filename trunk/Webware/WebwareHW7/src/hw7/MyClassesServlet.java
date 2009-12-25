package hw7;

import java.io.IOException;
import javax.servlet.http.*;

/** Get Method to retrieve the courses I have taken as an XML
 * 
 * @author Eric Greer
 *
 */
@SuppressWarnings("serial")
public class MyClassesServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/XML");
		resp.getWriter().println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		resp.getWriter().println(MyInfo.getInstance().ExportMyCourses());
	}
}
