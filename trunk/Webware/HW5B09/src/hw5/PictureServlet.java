package hw5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author Eric Greer
 * @author Jason Codding
 *
 */
@SuppressWarnings("serial")
public class PictureServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String pic = req.getParameter("pic");
		if (pic == null || pic.isEmpty()){}
		
		else{
			PictureNameBean bean = new PictureNameBean();
			bean.setPicture(pic);
			String url = bean.getLargeURL();
			
			resp.setContentType("text/xml");
			PrintWriter writer = resp.getWriter();		
			writeXML(writer, url);
		}
	}
	
	public void writeXML(PrintWriter writer, String url){
		String xml = "<?xml version=\"1.0\"?>";
		xml += "<picture url=\"" + url + "\" />";
		
		writer.write(xml);
	}
}
