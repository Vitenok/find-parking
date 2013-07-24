<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="css/bootstrap.css" rel="stylesheet">
	<script type="text/javascript">
		<jsp:include page="js/jquery-2.0.3.min.js" />
		<jsp:include page="js/bootstrap.js" />
	</script>

	<style>
		.style-form {
				height:400px;
             	width:400px;
            	position: absolute;
             	left: 45%;
             	top: 55%;
             	margin: -200px 0 0 -200px;
		}
	</style>
</head>

<body>
	<div class="style-form">
    	<form class="form-horizontal" method="POST" action="${action}" >
		<div class="control-group">
		<label class="control-label" for="inputLogin">Login:</label>
		<div class="controls">
	 		<input type="text" name="login" id="inputLogin" placeholder="Login">
		</div>
	</div>
	<div class="control-group">
		 <label class="control-label" for="inputPassword">Password:</label>
		 <div class="controls">
  	         <input type="password" name="password" id="inputPassword" placeholder="Password" >
      	 </div>
   </div>

	<div class="control-group">
		<div class="controls">				
		<button type="submit" class="btn">Sign in</button>
			</div>
		</div>
	</form>
	
	</div>	
</body>
</html>