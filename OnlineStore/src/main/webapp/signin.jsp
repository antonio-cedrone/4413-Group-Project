<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign In</title>
<link rel="stylesheet" type="text/css" href="css/login.css">

</head>
<body>

<div class="container">
   <div class="imgcontainer">
       <img src="images/tempLogo.png" alt="logo" class="logo">
   </div>
   <form action="LoginServlet">
       <input type="text" placeholder="Username" name="username" required>
       <input type="password" placeholder="Password" name="password" required>
       <button type="submit">Sign In</button>
   </form>
   <p>Don't have an account? <a href="signup.jsp">Sign Up</a></p>
</div>

</body>
</html>