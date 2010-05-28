package hd.server.timesheet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/** The W2W Servelet is an HTML service that processes importing WhenToWork XML 
 * data in a HTML post  
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
public class W2WServlet extends HttpServlet {
	final static String encoding = "Unicode";

	/** Generated ID for Serialization */
	private static final long serialVersionUID = 5603164623659235024L;

	/** Posts the XML File */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			ServletFileUpload upload = new ServletFileUpload();
			//	DefaultFileItemFactory factory =  new DefaultFileItemFactory();
			//	factory.setSizeThreshold(200);
			//	upload.setFileItemFactory(factory);
			res.setContentType("text/plain");
			String file = "";

			//StringBuffer file = new StringBuffer();

			/*
			List<FileItem> items = upload.parseRequest(req);

			Iterator<FileItem> iterator = items.iterator();
			while(iterator.hasNext()){
				FileItem item = iterator.next();
				if (item.isFormField()){
					//Skip for now
				}
				else{
					file = item.getString("windows-1252");
					res.getWriter().append("Uploaded File Name is:" + item.getName() + "\n<hr>" + W2WXMLParser.createTimesheets(item.getName(), file) + "File contents" + file);
				}
			 */
			/* Works Right now, except it doesn't deal with the encoding */

			FileItemIterator iterator = upload.getItemIterator(req);

			while (iterator.hasNext()) {
				FileItemStream item = iterator.next();
				InputStream stream = item.openStream();

				if (item.isFormField()) {
					// log.warning("Got a form field: " + item.getFieldName());
					//res.getWriter().append("Item is a form field");
				} else {
					/*  log.warning("Got an uploaded file: " + item.getFieldName() +
			                      ", name = " + item.getName());
					 */
					res.getWriter().append("Begining to write bytes: ...");
					int numBytes = 0;

					int len;			          //Read in from stream and write to the file string
					byte[] buffer = new byte[4096];
					while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
						numBytes += len;
						file += new String(buffer, 0, len, encoding);
						res.getWriter().append("Wrote bytes..." + numBytes);

					}
					res.getWriter().append("End file String length:" + file.length());  
					//  item.getName();

					res.getWriter().append("Uploaded File Name is:" + item.getName() + "\n<hr>" + /*W2WXMLParser.createTimesheets(item.getName(), file) +*/ "File contents" + file);

				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
}
