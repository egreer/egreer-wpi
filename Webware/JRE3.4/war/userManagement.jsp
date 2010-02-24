<?xml version="1.0" encoding="UTF-8"?>
<!-- JRE Planning Poker -->
<!-- Created by Jason Codding, Eric Greer, Matt Runkle -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="planningPoker.UserManager" %>
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
	<title>JRE Planning Poker | User Management</title>
	<link rel="stylesheet" type="text/css" href="css/main.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/print.css" media="print" />
	<!--[if lte IE 6]>
	<link rel="stylesheet" type="text/css" href="css/ie6_or_less.css" />
	<![endif]-->
	<script type="text/javascript" src="js/validate.js"></script>
	<script type="text/javascript" src="js/sha512-min.js"></script>
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
	
	String manageURI = "/manageUserStory.jsp";
%>
<body id="type-a">
	<div id="wrap">
	
		<!-- Header -->
		<div id="header">
			<div id="site-name">JRE Planning Poker</div>
			<ul id="nav">
				<li class="first"><a href="/userStories.jsp">User Stories</a></li>
				<li><a href="<%= manageURI %>?type=create">New User Story</a></li>
				<li class="active"><a href="/userManagement.jsp">User Management</a></li>
				<li><a href="/login?logout=true">Logout</a></li>
			</ul>
		</div>
		<!-- End Header -->
		
		<!-- Content Section -->
		<div id="content-wrap">
		
			<div id="content">
				
				<!-- Change Password Form -->
				<form action="/changePassword" method="post" class="f-wrap-1" onsubmit="return checkChangePassword(this);">
				
					<fieldset>
					
						<h3>Change Password</h3>
						
						<span class="errormsg"><b id="errorMsg"><%= errorMsg %></b></span>
						<span class="successmsg"><b id="successMsg"><%= successMsg %></b></span>
						
						<label for="oldpassword"><b>Current Password:</b>
							<input id="oldpassword" name="oldpassword" type="password" class="f-name" tabindex="1" /><br />
						</label>
						
						<label for="password"><b>New Password:</b>
							<input id="password" name="password" type="password" class="f-name" tabindex="2" /><br />
						</label>
						
						<label for="pwconfirm"><b>Confirm Password:</b>
							<input id="pwconfirm" name="pwconfirm" type="password" class="f-name" tabindex="3" /><br />
						</label>
						
						<div class="f-submit-wrap">
							<input type="submit" value="Change Password" class="f-submit" tabindex="4" /><br />
						</div>
					
					</fieldset>
				</form>
				<!-- End Change Password Form -->
		
				<div id="poweredby"><a href="http://farcry.daemon.com.au/"><img src="wsimages/mollio.gif" alt="FarCry - Mollio" /></a></div>
			
			</div>
		</div>
		<!-- End Content Section -->
		
		<jsp:include page="/footer.html" />
		
	</div>
</body>
</html>