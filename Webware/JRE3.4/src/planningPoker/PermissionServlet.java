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
/**
 * 
 * The PermissionServlet provides the communication channel for permission changes between admins and users
 */
@SuppressWarnings("serial")
public class PermissionServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException{
		req.getSession().setAttribute("success", null);
		req.getSession().setAttribute("error", null);
		
		String username = req.getParameter("username");
		String permission = req.getParameter("permission");
		
		String userPermissions = (String) req.getSession().getAttribute("permissions");
		
		//Validate input
		if (username == null || username.trim().isEmpty()){
			req.getSession().setAttribute("error", "Please enter a valid username.");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		} else if(permission == null || permission.trim().isEmpty()  || !(permission.equals("admin" ) || permission.equals("user"))){
			req.getSession().setAttribute("error", "Please enter valid permissions: admin or user");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		} else if(userPermissions == null || !userPermissions.equals("admin")){
			req.getSession().setAttribute("error", "Error you have insufficent permissions to perform this task.");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}
		
		//Set permissions
		boolean sucess = UserManager.setPermissions(username, permission);
		
		if (sucess){
			req.getSession().setAttribute("success", "Sucesssfully changed permissions to: " + permission + " for: " +  username);
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}else{
			req.getSession().setAttribute("error", "Please enter valid username or permissions.");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}
		
	}
}
