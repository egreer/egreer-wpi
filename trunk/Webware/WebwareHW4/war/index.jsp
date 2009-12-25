<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Authors Eric Greer, and Jason Codding -->

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Webware HW4</title>
		<link rel="stylesheet" type="text/css" href="default.css" title="Default Style" />

		<script language="JavaScript" type="text/javascript">

function checklogin ( form )
{
  if (form.username.value == null || form.password.value == null || form.username.value == "" || form.password.value == "") {
    alert( "Please enter your username and password." );
    form.username.focus();
    return false ;
  }
	
  return true ;
}

</script>
		
  </head>

  <body>
    <h1>Webware HW4</h1>
	
		<div class="colored">
		<form class="colored" method="post" action="validate" onsubmit="return checklogin(this);">
			<table>
					<tr>
						<td class="bold">User Name:</td>
						<td><input type="text" name="username" value="" size="20" maxlength="20" /></td>
					</tr>
					<tr>
						<td class="bold">Password:</td>
						<td><input type="password" name="password" value="" size="20" maxlength="20" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Login"/></td>
					</tr>
					
					<%
						String login = (String)session.getAttribute("login");
						if (login != null && login.equals("no") ) {
							out.println("<tr class=\"error\"><td><td>Invalid Username or Password</td></tr>");
						}
					%>
			 </table>
		</form>
		</div>
		<p class="footer">Created by: Eric Greer & Jason Codding <br /> WPI 2009 </p>
  </body>
</html>
