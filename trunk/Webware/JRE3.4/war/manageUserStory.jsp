<?xml version="1.0" encoding="UTF-8"?>
<!-- JRE Planning Poker -->
<!-- Created by Jason Codding, Eric Greer, Matt Runkle -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="planningPoker.UserStoryManager" %>
<%@ page import="planningPoker.UserStory" %>
<%@ page import="planningPoker.UserManager" %>
<%@ page import="planningPoker.Estimate" %>
<% //Author: Matt Runkle %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	if( session.getAttribute("user") == null ) {
	// If the user is not logged in, redirect to index
%>
	<jsp:forward page="/login.jsp"></jsp:forward>
<%	} %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!--
Copyright: Daemon Pty Limited 2006, http://www.daemon.com.au
Community: Mollio http://www.mollio.org $
License: Released Under the "Common Public License 1.0", 
http://www.opensource.org/licenses/cpl.php
License: Released Under the "Creative Commons License", 
http://creativecommons.org/licenses/by/2.5/
License: Released Under the "GNU Creative Commons License", 
http://creativecommons.org/licenses/GPL/2.0/
-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>JRE Planning Poker | Manage User Stories</title>
	<link rel="stylesheet" type="text/css" href="css/main.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/print.css" media="print" />
	<!--[if lte IE 6]>
	<link rel="stylesheet" type="text/css" href="css/ie6_or_less.css" />
	<![endif]-->
	<script type="text/javascript" src="js/validate.js"></script>
</head>
<%
	// Process error message
	String errorMsg;
	if ( session.getAttribute("error") == null ) errorMsg = "";
	else errorMsg = session.getAttribute("error").toString();
	session.setAttribute("error", null);
	// Process success message
	String successMsg;
	if ( session.getAttribute("success") == null ) successMsg = "";
	else successMsg = session.getAttribute("success").toString();
	session.setAttribute("success", null);
	
	// Determine Create or Edit
	String pageType;
	UserStory currentStory = null;
	Collection<String> allAssignedUsers = new ArrayList<String>();
	Long usId = -1L;
	// Try to parse user story key
	if( request.getParameter("us") != null ) {
		try {
			usId = Long.parseLong( request.getParameter("us") );
		}
		catch (NumberFormatException e) {
			session.setAttribute("error", "Invalid User Story ID");
			response.sendRedirect("/userStories.jsp");
		}
	}
	if ( request.getParameter("type").equals("edit") ) {
		pageType = "Edit";
		currentStory = UserStoryManager.getUserStory(usId);
		allAssignedUsers = UserStoryManager.getAllStoryUsernames(usId);
	}
	else if ( request.getParameter("type").equals("delete") ) {
		pageType = "Delete";
		currentStory = UserStoryManager.getUserStory(usId);
		allAssignedUsers = UserStoryManager.getAllStoryUsernames(usId);
	}
	else
		pageType = "Create";
	
	// Check if the story exists
	if (currentStory == null)
		currentStory = new UserStory();
	
	// Check for Read Only
	String readOnly = "";
	if ( !currentStory.isEditable() || pageType.equals("Delete") )
			readOnly = "disabled=\"disabled\"";
	
	// Get a list of all users
	Collection<String> allUsers = UserManager.getAllUsernames();
	allUsers.remove( session.getAttribute("user").toString() );
%>
<body id="type-a">
	<div id="wrap">
	
		<!-- Header -->
		<div id="header">
			<div id="site-name">JRE Planning Poker</div>
			<ul id="nav">
				<li class="first"><a href="/userStories.jsp">User Stories</a></li>
				<li class="active"><a href="/manageUserStory.jsp?type=create">New User Story</a></li>
				<li><a href="/userManagement.jsp">User Management</a></li>
				<li><a href="/login?logout=true">Logout</a></li>
			</ul>
		</div>
		<!-- End Header -->
		
		<div id="content-wrap">
		
			<!-- Content Section -->
			<div id="content">
			
				<!-- User Story Form  -->
				<form action="/userStory" method="post" class="f-wrap-1" onsubmit="return checkUserStory(this);">
				
					<div class="req"><b>*</b> Indicates required field</div>
				
					<fieldset>
				
					<h3><%= pageType %> User Story</h3>
					
					<span class="errormsg"><b id="errorMsg"><%= errorMsg %></b></span>
					<span class="successmsg"><b id="successMsg"><%= successMsg %></b></span>
					
					<% if (currentStory.getFinalEstimate() != null) {%>
						<span class="totalEstimate"><b id="totalEstimateTop">Total Estimated Units: <%= currentStory.getFinalEstimate().toString() %></b></span>
					<%}%>
					
					<label for="title"><b><span class="req">*</span>Title:</b>
						<input <%= readOnly %> id="title" name="title" type="text" class="f-name" tabindex="1" value="<%= currentStory.getTitle() %>" /><br />
					</label>
					
					<label for="description"><b><span class="req">*</span>Description:</b>
						<textarea <%= readOnly %> id="description" name="description" class="f-comments" rows="6" cols="20" tabindex="2"><%= currentStory.getDescription() %></textarea><br />
					</label>
					
					<label for="testnotes"><b>Test Notes:</b>
						<textarea <%= readOnly %> id="testnotes" name="testnotes" class="f-comments" rows="6" cols="20" tabindex="3"><%= currentStory.getTestNotes() %></textarea><br />
					</label>
				
					<input id="mode" name="mode" type="hidden" value="<%= pageType %>" />
					<input id="key" name="key" type="hidden" value="<%= usId %>" />
					
					<% if( currentStory.isEditable() || pageType.equals("Delete") ) { %>
					<div class="f-submit-wrap">
						<input type="submit" value="<%= pageType %>" class="f-submit" tabindex="4" />
						<a href="/userStories.jsp"><button type="button" class="f-submit">Cancel</button></a><br />
					</div>
					<% } %>
					
					</fieldset>
					
					<div id="sidebar">
						
						<h3>Estimators:</h3>
						
						<fieldset>
						
						<% 
							// Iterate through each message, and print.
							Iterator<String> itr = allUsers.iterator();
							int tabcount = 4;
							if ( allUsers.size() == 0 ) {
								%>
								<p>There are no users available.</p>
								<%
							}
							else {
								while ( itr.hasNext() ) {
									tabcount++;
									String username = itr.next();
									String checked = "";
									String estimate = "";
									
									// Get the estimate per user
									if ( !pageType.equals("Create") ) {
										Estimate userEst = UserStoryManager.retrieveEstimate(usId, username);
										if (userEst != null)
											estimate = "(" + userEst.getEstimate().toString() + ")";
									}
									
									if ( allAssignedUsers.contains(username) )
										checked = "checked=\"checked\"";
									%>
									<label for="un<%= tabcount %>">
										<input <%= readOnly %> <%= checked %> id="un<%= tabcount %>" type="checkbox" name="usernames" value="<%= username %>" class="f-checkbox" tabindex="<%= tabcount %>" />
									<%= username %> <%= estimate %></label>
									<%
								}
							}
						%>

						</fieldset>
						
					</div>
				
				</form>
				
			</div>
		</div>

		<div id="poweredby"><a href="http://farcry.daemon.com.au/"><img src="wsimages/mollio.gif" alt="FarCry - Mollio" /></a></div>
		
		<jsp:include page="/footer.html" />
		
	</div>
</body>
</html>