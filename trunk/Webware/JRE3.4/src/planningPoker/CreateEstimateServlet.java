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
 * The CreateEstimateServlet provides communication for the estimate for the user story be added
 *
 */
@SuppressWarnings("serial")
public class CreateEstimateServlet extends HttpServlet{
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		req.getSession().setAttribute("success", null);
		req.getSession().setAttribute("error", null);
		
		String username = (String) req.getSession().getAttribute("user");
		String storyKey = req.getParameter("key");
		String value = req.getParameter("estimate");
		
		
		if(username == null || username.trim().isEmpty()){
			req.getSession().setAttribute("error", "Invalid Username");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}else if(storyKey == null || storyKey.trim().isEmpty()){
			req.getSession().setAttribute("error", "Invalid Story Key");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}else if(value == null || value.trim().isEmpty()){
			req.getSession().setAttribute("error", "Please enter an Estimate");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}
		
		Long key = -1L;
		Double estimate = -1D;
		
		try{
			key = Long.parseLong(storyKey);

		}catch (NumberFormatException e){
			req.getSession().setAttribute("error", "Invalid value for key");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}
		
		try {
			estimate = Double.parseDouble(value);
			if(estimate <= 0){
				req.getSession().setAttribute("error", "Invalid value for estimate");
				resp.sendRedirect(req.getHeader("referer"));
				return;
			}
		}catch (NumberFormatException e){
			req.getSession().setAttribute("error", "Invalid value for estimate");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}
		
		
		Boolean returned = UserStoryManager.createEstimate(key, username, estimate);
		if (returned == false){
			req.getSession().setAttribute("error", "Could not create estimate, Please try again");
			resp.sendRedirect(req.getHeader("referer"));
			return;
		}

		else{
			req.getSession().setAttribute("success", "Sucessfully saved estimate");
			resp.sendRedirect("/userStories.jsp");
			return;
		}
	}
}
