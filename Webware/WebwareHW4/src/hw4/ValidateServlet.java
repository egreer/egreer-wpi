package hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.http.*;

/**
 * 
 * @author Eric Greer
 * @author Jason Codding
 */
@SuppressWarnings("serial")
public class ValidateServlet extends HttpServlet {
	
	//Login
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		
		Boolean returner= false;
		
		try{
			returner = login(username, password);
		}catch (FileNotFoundException e){
			throw new IOException(e.getMessage());// Can't open file
		}
		
		if(returner){
			//Forward to new page
	        HttpSession session = req.getSession();
	        session.setAttribute("login", "yes");
	        session.setAttribute("messages", null);
	        resp.sendRedirect("message.jsp");
		}else{
			//TODO return Popup
			HttpSession session = req.getSession();
	        session.setAttribute("login", "no");
	        resp.sendRedirect("index.jsp");
		}
	}
	
	//Validate Username and password
	public boolean login(String username, String password) throws FileNotFoundException{
		Scanner sc = new Scanner(new File("WEB-INF/users.txt"));
		
		while(sc.hasNextLine()){
			String user = sc.next();
			String pw = sc.next();
			
			if(username.equals(user) && password.equals(pw)) return true;
		}
		
		return false;
	}
}
