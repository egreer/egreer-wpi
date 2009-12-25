package hw7;

import java.io.IOException;
import javax.servlet.http.*;

/** Returns information about me in an XML document from a Get request
 * 
 * @author Eric Greer
 *
 */
@SuppressWarnings("serial")
public class AboutMeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/xml");
		resp.getWriter().println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		resp.getWriter().println("<student><name>Eric Greer</name><major>Computer Science</major><graduates>2010</graduates></student>");
	}
}
