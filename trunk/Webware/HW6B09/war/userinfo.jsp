<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.*, hw6.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<!--Eric Greer -->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<title>CS4241-B05 Homework 6</title>

<link rel="stylesheet" type="text/css" href="default.css" title="Default Style" />
</head>

<body>
	<div class="center" class="large">
	<%= TwitterInfo.getUserInfo(request.getParameter("userName"))	%>
	<a href="javascript:history.back()"><button class="navigation" type="button">Back</button></a>
	<a href="/main.jsp"><button class="navigation" type="button">Home</button> </a>
	</div>
	<p class="footer">Created by: Eric Greer<br /> WPI 2009 </p>
</body>
</html>