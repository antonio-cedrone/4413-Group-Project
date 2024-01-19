<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Sign Up</title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>

<div class="container">
   <div class="imgcontainer">
       <img src="images/tempLogo.png" alt="logo" class="logo">
   </div>
   <form action="SignUpServlet">
       <input type="text" placeholder="Username" name="username" required>
       <input type="text" placeholder="Email" name="email" required>
       <input type="password" placeholder="Password" name="password" required>
       <input type="password" placeholder="Re-enter Password" name="repassword" required>
       <button type="submit">Sign Up</button>
   </form>
</div>

</body>
</html>
