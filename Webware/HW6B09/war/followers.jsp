<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.*, hw6.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<!--Eric Greer-->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<title>CS4241-B05 Homework 6</title>

<link rel="stylesheet" type="text/css" href="default.css" title="Default Style" />

<script>
//Sets the visable followers
function setVisable(pgnum, first, last){
		var list = document.getElementsByTagName("li");
		
		for(var i = 0; i < list.length; i++){
				var e = document.getElementById(i);
				
			if(first <= i && i <= last)	e.setAttribute("class", "visable");
      else e.setAttribute("class", "invis");
    }
		
		changeNavigation(pgnum);
}

//Clicks on a page indicator for pagination
function clickPage(page) {
		var linkobj = document.getElementById("pg" + page);
     if (linkobj.getAttribute('onclick') == null) {
          if (linkobj.getAttribute('href')) document.location = linkobj.getAttribute('href');
     }
     else linkobj.onclick();
}

//Change's the navigation forward and back to the next page
function changeNavigation(currpage) {
	var next = document.getElementById("next");
	var prev = document.getElementById("previous");
	
	var first = document.getElementById("first");
	var last = document.getElementById("last");
	
	var firstpg = parseInt(first.getAttribute('href').substring(21,22));
	var lastpg = parseInt(last.getAttribute('href').substring(21,22));
	
	if ((currpage - 1) < firstpg) prev.setAttribute('href', "javascript:clickPage("+firstpg+")");
	else	prev.setAttribute('href', "javascript:clickPage("+ (currpage - 1) +")");
	
	if ((currpage + 1) > lastpg) next.setAttribute('href', "javascript:clickPage("+lastpg+")");
	else next.setAttribute('href', "javascript:clickPage("+ (currpage + 1) +")");
}
</script>
</head>

<body>
	<%= TwitterInfo.getUserFollowers(request.getParameter("userName"))%>
	<div class="center">
	<a href="javascript:history.back()"><button class="navigation" type="button">Back</button></a>
	<a href="/main.jsp"><button class="navigation" type="button">Home</button> </a>
	</div>
	<p class="footer">Created by: Eric Greer<br /> WPI 2009 </p>
</body>
</html>