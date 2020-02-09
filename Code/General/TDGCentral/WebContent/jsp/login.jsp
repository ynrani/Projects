<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>TDG Central</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<script src="js/html5.js"></script>
</head>

<body>
	<script src="include/loginHeader.js"></script>

	<div class="login-bg2">
		<div class="container">
			<div class="login-container">
				<div class="top-blue-line"></div>
				<img src="images/login-icon.png" width="114" height="115" alt="" />
				<h2>Login</h2>

				<div align="center">
					<% if(request.getParameter("auth")!=null && request.getParameter("auth").equals("fail")){
			    	out.println("Invalid Username or Password");
			 }		
			  else if(request.getParameter("logout")!=null && request.getParameter("logout").equals("true")){
					out.println("Logout Successfully");
			 }
			  else if(request.getParameter("session")!=null && request.getParameter("session").equals("expired")){
					out.println("Session Expired");
			 }else if(request.getParameter("session")!=null && request.getParameter("session").equals("alreadyLogged")){
					out.println("User Already Logged In");
			 }
			%>
				</div>

				<form:form action="${pageContext.request.contextPath}/tesdaLogin"
					method="post">
					<input type="text" value="" placeholder="User ID" name="userid">
					<input type="password" value="" placeholder="Password"
						name="password">
					<input type="checkbox" value="" checked>
					<label>Remember Me</label>
					<a href="#" class="forgot-password"> <!-- Forgot Password? -->
					</a>
					<input type="submit" value="Login" name="submit" id="submit"
						class="btn-primarylog btn-celllog">
				</form:form>

			</div>
			<div class="clearfloat">&nbsp;</div>
		</div>
	</div>


	<script type="text/javascript"> 
 
$(function() { 
$('[placeholder]').focus(function() { 
  var input = $(this); 
  if (input.val() == input.attr('placeholder')) { 
    input.val(''); 
    input.removeClass('placeholder'); 
  } 
}).blur(function() { 
  var input = $(this); 
  if (input.val() == '' || input.val() == input.attr('placeholder')) { 
    input.addClass('placeholder'); 
    input.val(input.attr('placeholder')); 
  } 
}).blur().parents('form').submit(function() { 
  $(this).find('[placeholder]').each(function() { 
    var input = $(this); 
    if (input.val() == input.attr('placeholder')) { 
      input.val(''); 
    } 
  }) 
}); 
}); 


</script>
</body>
</html>
