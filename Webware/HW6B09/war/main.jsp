<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<!--Eric Greer-->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<title>CS4241-B05 Homework 6</title>

<link rel="stylesheet" type="text/css" href="default.css" title="Default Style" />
</head>


<body>
	<h1>Twitter Lookup</h1>
	<form action="/userinfo.jsp" method="get">
		<label>Enter user name:
			<input type="text" name="userName"  />
		</label>
		<br />
		<input type="submit" value="Get Info" />
	</form>
	<p class="footer">Created by: Eric Greer<br /> WPI 2009 </p>
</body>
</html>