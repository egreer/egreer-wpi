package planningPoker;
/**
 * JRE Planning Poker Rev 1.0
 * @author Jason Codding
 * @author Eric Greer
 * @author Matt Runkle
 */

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
/**
 * The UserStoryServlet provides the communication channel for creating, updating, and deleting user story's
 */
public class UserStoryServlet extends HttpServlet {


	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		req.getSession().setAttribute("success", null);
		req.getSession().setAttribute("error", null);


		String keytext = req.getParameter("key");
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String testNotes = req.getParameter("testnotes");
		String[] users = req.getParameterValues("usernames"); 
		String mode = req.getParameter("mode");
		String currUser = (String) req.getSession().getAttribute("user");
		
		if(mode == null){
			req.getSession().setAttribute("error", "Invalid Mode");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		} else if (currUser == null || currUser.trim().isEmpty()){
			req.getSession().setAttribute("error", "Invalid logged in User, stop hacking");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}

		if(!mode.equals("Delete")){
			if(title == null || title.trim().isEmpty()){
				req.getSession().setAttribute("error", "Please enter a title.");
				resp.sendRedirect(req.getHeader("referer"));
				return;
			}else if(description == null || description.trim().isEmpty()){
				req.getSession().setAttribute("error", "Please enter a description.");
				resp.sendRedirect(req.getHeader("referer"));
				return;
			}else if(testNotes == null){
				req.getSession().setAttribute("error", "Invalid Test Notes.");
				resp.sendRedirect(req.getHeader("referer"));
				return;
			}else if(keytext == null){
				req.getSession().setAttribute("error", "Invalid Key");
				resp.sendRedirect(req.getHeader("referer"));
				return;
			}
			
			//Strip HTML
			title = removeHTMLTags(title);
			description = removeHTMLTags(description);
			testNotes = removeHTMLTags(testNotes);
		}

		//Make sure key is a valid long only if it was passed in
		Long key = -1L;

		if(!mode.equals("Create")){
			try{
				key = Long.parseLong(keytext);
			}catch (NumberFormatException e){
				req.getSession().setAttribute("error", "Invalid Key");
				resp.sendRedirect(req.getHeader("referer"));
				return;
			}
		}


		
		
		//Hidden field for edit, delete, add
		if(mode.equals("Create")){
			UserStory story = UserStoryManager.createUserStory(title, description, testNotes, users, currUser);
			if (story == null){
				req.getSession().setAttribute("error", "Could not create user story ");
				resp.sendRedirect(req.getHeader("referer"));
				return;
			}
			else {
				req.getSession().setAttribute("success", "Created user story: " + story.getTitle());
				resp.sendRedirect("/userStories.jsp");
				return;
			}

		}else if (mode.equals("Edit")){
			boolean story = UserStoryManager.editUserStory(key, title, description, testNotes, users, currUser);
			if (!story){
				req.getSession().setAttribute("error", "Could not edit User Story. Make sure you are the creator.");
				resp.sendRedirect(req.getHeader("referer"));
				return;
			}

			else {
				req.getSession().setAttribute("success", "Edited user story: " + title);
				resp.sendRedirect("/userStories.jsp");
				return;
			}

		}else if(mode.equals("Delete")){
			boolean b = UserStoryManager.removeUserStory(key, currUser);
			
			if(!b){
				req.getSession().setAttribute("error", "Could not delete User Story, you are not the creator");
				resp.sendRedirect(req.getHeader("referer"));
				return;
			}
			else {
				req.getSession().setAttribute("success", "Deleted user story");
				resp.sendRedirect("/userStories.jsp");
				return;
			}
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
