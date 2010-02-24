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
 * The CreateUserServlet provides the communication channel for creating a user object
 */
public class CreateUserServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		req.getSession().setAttribute("success", null);
		req.getSession().setAttribute("error", null);

		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String passwordConfirm = req.getParameter("pwconfirm");

		//Ensure valid input
		if (firstName == null || firstName.trim().isEmpty()){
			req.getSession().setAttribute("error", "Invalid input for First Name, cannot be blank");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}else if ( lastName == null || lastName.trim().isEmpty()){
			req.getSession().setAttribute("error", "Invalid input for Last Name, cannot be blank");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}else if (username == null || username.trim().isEmpty()){
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
		}

		//Strip HTML
		firstName = removeHTMLTags(firstName);
		lastName = removeHTMLTags(lastName);

		User newUser = UserManager.createUser(firstName, lastName, username, password);

		if (newUser == null){
			req.getSession().setAttribute("error", "Username: " + username + " already exists, please choose a new username");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}else{
			req.getSession().setAttribute("username", newUser.getUserName());
			req.getSession().setAttribute("success", "User: " + newUser.getUserName() + " was sucessfully created");
			resp.sendRedirect("/login.jsp");
			return;
		}


	}

	/**
	 * This method will clean a string by removing all HTML tags.
	 * Found at: http://snippets.dzone.com/posts/show/4018
	 * 
	 * @param dirtyString The string from which to remove HTML tags
	 * @return A cleaned string that contains no HTML tags
	 */
	private String removeHTMLTags(String dirtyString) {

		return dirtyString.replaceAll("\\<.*?\\>", "");
	}
}
