<?xml version="1.0" encoding="UTF-8"?>
<!-- JRE Planning Poker -->
<!-- Created by Jason Codding, Eric Greer, Matt Runkle -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="planningPoker.UserStoryManager" %>
<%@ page import="planningPoker.UserStory" %>
<%@ page isELIgnored="false" %>
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
	<title>JRE Planning Poker | User Stories</title>
	<link rel="stylesheet" type="text/css" href="css/main.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/print.css" media="print" />
	<!--[if lte IE 6]>
	<link rel="stylesheet" type="text/css" href="css/ie6_or_less.css" />
	<![endif]-->
	<script type="text/javascript">
		function updateTopTotal() {
			var topTotal = document.getElementById("totalEstimateTop");
			var bottomTotal = document.getElementById("totalEstimateBottom");
			
			var total = bottomTotal.innerHTML;
			
			topTotal.innerHTML = total;
		}
	</script>
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
	
	// Set default User permission level
	boolean isOwner = false;
	
	String manageURI = "/manageUserStory.jsp";
	String estimateURI = "/estimateUserStory.jsp";
	Double totalEstimate = 0.0;
	
	// Get all user stories
	String display = request.getParameter("display");
	String filter = request.getParameter("filter");
	Collection<UserStory> allUserStories;
	if (display != null && display.equals("owned"))
		allUserStories = UserStoryManager.retrieveOwnedUserStories( session.getAttribute("user").toString() );
	else if ((display != null && display.equals("assigned")) || (filter != null && filter.equals("my")))
		allUserStories = UserStoryManager.retrieveAllUserStories( session.getAttribute("user").toString() );
	else
		allUserStories = UserStoryManager.retrieveAllUserStories();
%>
<body id="type-a" onload="updateTopTotal();">
	<div id="wrap">
	
		<!-- Header -->
		<div id="header">
			<div id="site-name">JRE Planning Poker</div>
			<ul id="nav">
				<li class="first active"><a href="/userStories.jsp">User Stories</a></li>
				<li><a href="<%= manageURI %>?type=create">New User Story</a></li>
				<li><a href="/userManagement.jsp">User Management</a></li>
				<li><a href="/login?logout=true">Logout</a></li>
			</ul>
		</div>
		<!-- End Header -->
		
		<!-- Content Section -->
		<div id="content-wrap">
		
			<div id="content">
				
				<!-- User Story List -->
				<h1>User Stories</h1>
				
				<span class="errormsg"><b id="errorMsg"><%= errorMsg %></b></span>
				<span class="successmsg"><b id="successMsg"><%= successMsg %></b></span>
			
				<div id="resultslist-wrap">
					<div class="breadcrumb">
						<strong>Filter by:&nbsp;&nbsp;&nbsp;</strong><a href="/userStories.jsp">All</a> | 
						<a href="/userStories.jsp?display=owned">Owned</a> | 
						<a href="/userStories.jsp?display=assigned">Assigned</a> | 
						<a href="/userStories.jsp?display=open">Pending</a> | 
						<a href="/userStories.jsp?display=closed">Completed</a>
					</div>
					<% if (display != null && (display.equals("open") || display.equals("closed"))) { %>
					<div class="breadcrumb" style="margin-left: 55px;">
						<a href="/userStories.jsp?display=<%= display.toString() %>&filter=all">All Stories</a> | 
						<a href="/userStories.jsp?display=<%= display.toString() %>&filter=my">My Stories</a>
					</div>
					<%}%>
					<span class="totalEstimate"><b id="totalEstimateTop">Total Estimated Units: <%= totalEstimate %></b></span>
					<%
						// Iterate through each User Story and print.
						Iterator<UserStory> itr = allUserStories.iterator();
						UserStory story;
						if ( allUserStories.size() == 0 ) {
							%>
							<p>There are no User Stories.</p>
							<%
						}
						else {
						%>
						<ol>
						<%
							while ( itr.hasNext() ) {
								story = itr.next();
								
								// Implement filter
								if ( display != null && display.equals("open") ) {
									//Display only PENDING stories
									if( story.getFinalEstimate() != null )
										continue;
								}
								else if ( display != null && display.equals("closed") ) {
									//Display only COMPLETED stories
									if( story.getFinalEstimate() == null )
										continue;
								}
								
								// Get an truncate (is necessary) the story description
								String desc = story.getDescription();
								if (desc.length() > 200)
									desc = desc.substring(0, 200);
								String editURI = estimateURI + "?";
								
								// Determine if the current user "owns" this story
								if ( UserStoryManager.isCreator(story, session.getAttribute("user").toString()) ) {
									isOwner = true;
									editURI = manageURI + "?type=edit&amp;";
								} else {
									isOwner = false;
								}
						%>
								<li>
									<dl>
										<dt><a href="<%= editURI %>us=<%= story.getKey() %>"><%= story.getTitle() %></a></dt>
										<dd class="desc"><%= desc %></dd>
										<%
											String finalEstimate = "";
											Double estimateValue = 0.0;
											if ( story.getFinalEstimate() != null ) {
												estimateValue = story.getFinalEstimate();
												totalEstimate += estimateValue;
												finalEstimate = estimateValue.toString();
												%>
													<dd class="desc">Final Estimate: <%= finalEstimate %></dd>
												<%
											}
										%>
										<dd class="filetype"><% if( finalEstimate.isEmpty() ) {%> Pending <%} else {%> Completed <%}%></dd>
										<% if(isOwner) { %>
										<dd class="date"><% if(story.isEditable()) {%> editable <%} else {%> readonly <%}%></dd>
										<dd class="date"><a href="<%= manageURI %>?type=delete&amp;us=<%= story.getKey() %>">delete</a></dd>
										<%}%>
										
									</dl>
								</li>
								<%
							}
						%>
						</ol>
						<span class="totalEstimate"><b id="totalEstimateBottom">Total Estimated Units: <%= totalEstimate %></b></span>
						<%
						}
					%>
				</div>
				<!-- User Story List -->

				<div id="poweredby"><a href="http://farcry.daemon.com.au/"><img src="wsimages/mollio.gif" alt="FarCry - Mollio" /></a></div>
			
			</div>
		</div>
		<!-- End Content Section -->
		
		<jsp:include page="/footer.html" />
		
	</div>
</body>
</html>