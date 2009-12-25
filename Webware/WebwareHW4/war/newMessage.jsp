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
			
		<script language="JavaScript" type="text/javascript">

		function CountLeft(field, count, max) {

		if (field.value.length > max) field.value = field.value.substring(0, max);
		else count.value = max - field.value.length;

		}
		</script>
	</head>

	<body>
		 <h1>Webware HW4</h1>
		<div class="colored">
		<form name=sample action="message.jsp" method="post">

			<input type="text" name="message" size="50"
				onKeyDown="CountLeft(this.form.message,this.form.left,100);"
				onKeyUp="CountLeft(this.form.message,this.form.left,100);">
			<input readonly type="text" name="left" size=3 maxlength=3 value="100">
			<p>
			
			<input type="submit" name="submit" value="Save"/>
			<input type="submit" name="submit" value="Cancel"/>
		
			</p>
		</form>
		</div>
		<p class="footer">Created by: Eric Greer & Jason Codding <br /> WPI 2009 </p>
  </body>
</html>