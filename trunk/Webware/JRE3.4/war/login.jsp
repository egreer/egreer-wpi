<?xml version="1.0" encoding="UTF-8"?>
<!-- JRE Planning Poker -->
<!-- Created by Jason Codding, Eric Greer, Matt Runkle -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% //Author: Matt Runkle %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
	<title>JRE Planning Poker | Login</title>
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
%>
<body id="type-a" onload="document.getElementById('username').focus();">
	<div id="wrap">
	
		<!-- Header -->
		<div id="header">
			<div id="site-name">JRE Planning Poker</div>
			<ul id="nav">
				<li class="first active"><a href="/login.jsp">Home</a></li>
				<li><a href="/register.jsp">Register</a></li>
			</ul>
		</div>
		<!-- End Header -->
		
		<!-- Content Section -->
		<div id="content-wrap">
		
			<div id="content">
				
				<!-- Register Form -->
				<form action="/login" method="post" class="f-wrap-1" onsubmit="return checkLogin(this);">
				
					<fieldset>
					
						<h3>Login</h3>
						
						<span class="errormsg"><b id="errorMsg"><%= errorMsg %></b></span>
						<span class="successmsg"><b id="successMsg"><%= successMsg %></b></span>
						
						<label for="username"><b>Username:</b>
							<input id="username" name="username" type="text" class="f-name" tabindex="1" /><br />
						</label>
						
						<label for="password"><b>Password:</b>
							<input id="password" name="password" type="password" class="f-name" tabindex="2" /><br />
						</label>
						
						<div class="f-submit-wrap">
							<input type="submit" value="Submit" class="f-submit" tabindex="3" /><br />
						</div>
						
						<label>
							<span>Don't have an account? <a href="/register.jsp">Sign Up!</a></span>
						</label>
					
					</fieldset>
				</form>
				<!-- End Register Form -->

				<div id="poweredby"><a href="http://farcry.daemon.com.au/"><img src="wsimages/mollio.gif" alt="FarCry - Mollio" /></a></div>
			
			</div>
		</div>
		<!-- End Content Section -->
		
		<jsp:include page="/footer.html" />
		
	</div>
</body>
</html>