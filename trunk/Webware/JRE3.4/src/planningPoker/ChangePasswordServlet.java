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
 * The changePasswordServlet is responsible for communicating the data required to change a users password.
 */
public class ChangePasswordServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
	
		req.getSession().setAttribute("success", null);
		req.getSession().setAttribute("error", null);
		
		String username = (String) req.getSession().getAttribute("user");
		String password = req.getParameter("password");
		String passwordConfirm = req.getParameter("pwconfirm");
		String oldPassword = req.getParameter("oldpassword");

		
		//Ensure valid input
		if (username == null || username.trim().isEmpty()){
			req.getSession().setAttribute("error", "Invalid input for User Name, cannot be blank");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}else if (password == null || password.isEmpty()){
			req.getSession().setAttribute("error", "Invalid input for Password, cannot be blank");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}else if (passwordConfirm == null || passwordConfirm.isEmpty()){
			req.getSession().setAttribute("error", "Invalid input for Password Confirmation, cannot be blank");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}else if (!passwordConfirm.equals(password)){
			req.getSession().setAttribute("error", "Passwords do not match.");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}else if (oldPassword == null || oldPassword.isEmpty()){
			req.getSession().setAttribute("error", "Invalid old Password");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}
		
		Boolean success = UserManager.updatePassword(username, password, oldPassword);
		
		if (!success){
			req.getSession().setAttribute("error", "Password was not changed");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}else{
			req.getSession().setAttribute("success", "Password was succesfully changed to the new password");
			resp.sendRedirect("/userManagement.jsp");
			return;
		}
		
		
	}
	

}

