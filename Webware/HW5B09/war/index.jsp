<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.*, hw5.*" %>

<!-- Authors: Eric Greer & Jason Codding -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>CS4233-B09 Homework 5</title>
	<link rel="stylesheet" type="text/css" href="HW5.css" title="HW5" />
	<script type="text/javascript" src="HW5.js"></script>
</head>

<body onload="initialize();">
<jsp:useBean id="picNames" class="hw5.PictureNameBean" scope="application" />
<h1>Homework Assignment #5</h1>
	<p>
		Kids today don't know what real cartoons are. My grandkids don't even know who
		Daffy Duck is! Cartoons need to have attitude and
		be funny. They <em>must</em> have great animation, great music, and a great story.
		It shouldn't surprise you to know that my vote for the best example of great cartoons is
		the Looney Tunes series.
	</p>
	
	<p>
		You can click on the thumbnails below to see some of the great Looney Tune
		characters produced by the Warner Bros. animators.
	</p>
	
	<div id="mainPic">
		<img src="http://www.cartoonspot.net/looney-tunes/images-looney-tunes/welcome.jpg"
		 	alt="Welcome"/>
		<a href="/"><button type="button">Reset</button></a>
		<br />
	</div>

	<hr />
	<div>		
		<c:forEach var="pic" items="${picNames.baseNames}">
			<span class="thumbnail" onclick="showPicture('${picNames.urlBase}', '${pic}');">
				<jsp:setProperty name="picNames" property="picture" value="${pic}"/>
				<img src="${picNames.icoURL}" alt="${pic}"/>
			</span>
		</c:forEach>
		
	</div>

</body>
</html>
