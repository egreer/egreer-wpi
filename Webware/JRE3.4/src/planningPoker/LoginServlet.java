package planningPoker;
/**
 * JRE Planning Poker Rev 1.0
 * @author Jason Codding
 * @author Eric Greer
 * @author Matt Runkle
 */

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
/**
 * The Login Servlet provides the communication channel for processing application login and authentication
 */
public class LoginServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().setAttribute("success", null);
		req.getSession().setAttribute("error", null);
		
		String logout = req.getParameter("logout");
		
		if (logout == null || logout == ""){
			req.getSession().setAttribute("error", "Please logout correctly");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}
		
		if (logout.equals("true")){
			req.getSession().setAttribute("user", null);
			req.getSession().setAttribute("sucess", "You have sucessfully logged out.");
			resp.sendRedirect("/login.jsp");
			return;
		}else{
			req.getSession().setAttribute("error", "Please logout correctly");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		req.getSession().setAttribute("success", null);
		req.getSession().setAttribute("error", null);
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if (username == null || username.trim().isEmpty()){
			req.getSession().setAttribute("error", "Please enter a username.");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		} else if(password == null || password.trim().isEmpty()){
			req.getSession().setAttribute("error", "Please enter a password.");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}
		
		User user = isValid(username, password);
		
		if (user == null){
			req.getSession().setAttribute("error", "Invalid username or password.");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}else{
			req.getSession().setAttribute("user", user.getUserName());
			req.getSession().setAttribute("permissions", user.getPermissions());
			resp.sendRedirect("/userStories.jsp");
			return;
		}
		
	}
	
	public static User isValid(String userName, String password){
		
		User theUser = UserManager.getUser(userName);
		
		if(theUser == null ) return null;
		
		String storedPassword = theUser.getPassword();
		
		if (storedPassword.equals(password)) return theUser;
		else return null;
	}

}
