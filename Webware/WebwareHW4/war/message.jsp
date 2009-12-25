<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Authors Eric Greer, and Jason Codding -->

<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<title>Webware HW4</title>
		<link rel="stylesheet" type="text/css" href="default.css" title="Default Style" />
		<%
			String login = (String)session.getAttribute("login");
			if (login == null || login.equals("no") ) {
				response.sendRedirect("index.jsp");
			}
		%>

		</head>

	<body>
		<h1>Webware HW4</h1>
		<div class="colored">

		<ol>
		<% 
				String submitType = request.getParameter("submit");
				String messages = (String) session.getAttribute("messages");
				
				if (submitType != null && submitType.equals("Save")){
					String m = request.getParameter("message");
					
					if (messages == null) messages = m;
					else messages += "\n" + m;
				}
								
				if (messages != null && !messages.equals("") ) {
					String[] mess = messages.split("\n");
				
					for (int i = 0; i < mess.length; i++){
						out.println("<li>" + mess[i] + "</li>");
					}  
					
					session.setAttribute("messages", messages);
				}
		%>
		
		
		</ol>
		
		<form method="link" action="newMessage.jsp">
		<input type="submit" value="New Message">
		</form>
		</div>
		<p class="footer">Created by: Eric Greer & Jason Codding <br /> WPI 2009 </p>
  </body>
</html>