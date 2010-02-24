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
	<title>JRE Planning Poker | Estimate User Story</title>
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
	UserStory currentStory = null;
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
	} else {
		session.setAttribute("error", "Invalid User Story ID");
		response.sendRedirect("/userStories.jsp");
	}
	
	currentStory = UserStoryManager.getUserStory(usId);
	
	// Check if the story exists
	if (currentStory == null) {
		session.setAttribute("error", "User Story could not be found.");
		response.sendRedirect("/userStories.jsp");
	}
	
	// Check id user has estimated before
	String readOnly = "";
	Estimate userEstimate = UserStoryManager.retrieveEstimate(usId, session.getAttribute("user").toString());
	String estimateValue = "";
	if ( userEstimate != null ) {
		readOnly = "disabled=\"disabled\"";
		estimateValue = userEstimate.getEstimate().toString();
	}
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
			
				<!-- Estimate Form  -->
				<form action="/createEstimate" method="post" class="f-wrap-1" onsubmit="return checkEstimate(this);">
				
					<div class="req"><b>*</b> Indicates required field</div>
				
					<fieldset>
				
					<h3>Estimate User Story</h3>
					
					<span class="errormsg"><b id="errorMsg"><%= errorMsg %></b></span>
					<span class="successmsg"><b id="successMsg"><%= successMsg %></b></span>
				
					<% if (currentStory.getFinalEstimate() != null) {%>
						<span class="totalEstimate"><b id="totalEstimateTop">Total Estimated Units: <%= currentStory.getFinalEstimate().toString() %></b></span>
					<%}%>
					
					<label for="title"><b>Title:</b>
						<input readonly="readonly" id="title" name="title" type="text" class="f-name" tabindex="1" value="<%= currentStory.getTitle() %>" /><br />
					</label>
					
					<label for="description"><b>Description:</b>
						<textarea readonly="readonly" id="description" name="description" class="f-comments" rows="6" cols="40" tabindex="2"><%= currentStory.getDescription() %></textarea><br />
					</label>
					
					<label for="testnotes"><b>Test Notes:</b>
						<textarea readonly="readonly" id="testnotes" name="testnotes" class="f-comments" rows="6" cols="40" tabindex="3"><%= currentStory.getTestNotes() %></textarea><br />
					</label>
					
					<%
						//Only show estimate field if the user is assigned
						if ( UserStoryManager.isEstimator( currentStory, session.getAttribute("user").toString()) ) {
					%>
					
					<label for="estimate"><b><span class="req">*</span>Estimate:</b>
						<input <%= readOnly %> id="estimate" name="estimate" type="text" class="f-name" tabindex="1" size="4" value="<%= estimateValue %>" /><br />
					</label>
				
					<input id="key" name="key" type="hidden" value="<%= usId %>" />
					
					<% if( userEstimate == null ) { %>
					<div class="f-submit-wrap">
						<input type="submit" value="Estimate" class="f-submit" tabindex="2" />
						<a href="/userStories.jsp"><button type="button" class="f-submit">Cancel</button></a><br />
					</div>
					<% }
					}%>
					
					</fieldset>
				
				</form>
				<!-- End Estimate Form -->
				
			</div>
			<!-- End Content Section -->
			
		</div>

		<div id="poweredby"><a href="http://farcry.daemon.com.au/"><img src="wsimages/mollio.gif" alt="FarCry - Mollio" /></a></div>
		
		<jsp:include page="/footer.html" />
		
	</div>
</body>
</html>