<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account Information</title>
<link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>
	<h2>Account Information</h2>
	
		<fieldset>
			<legend>Login Information:</legend>
			Username:<br> ${sessionScope.identity.user} <br>
			Password:<br> ${sessionScope.identity.pass} <br>
			Email:<br> ${sessionScope.identity.email} <br>
		</fieldset>

	<form action="AccountServlet" method="post">
		<fieldset>
			<legend>User Information:</legend>
			First name:<br> <input type="text" name="firstname"
				value=${sessionScope.identity.firstName}> <br> Last name:<br> <input
				type="text" name="lastname" value=${sessionScope.identity.lastName}> <br>
		</fieldset>

		<fieldset>
			<legend>Shipping Information:</legend>
			Address:<br> <input type="text" name="address"
				value=${sessionScope.identity.address.street}> <br> City:<br> <input
				type="text" name="city" value=${sessionScope.identity.address.city}> <br>
			Postal Code:<br> <input type="text" name="postalCode"
				value=${sessionScope.identity.address.zip}> <br>
				Province:<br><input type="text" name="province" value=${sessionScope.identity.address.province}><br>
		</fieldset>
		
		<fieldset>
			<legend>Billing Information:</legend>
			Credit Card:<br> <input type="text" name="creditCard"
				value=${sessionScope.identity.creditCard} > <br> CVV:<br> <input
				type="text" name="cvv" value=${sessionScope.identity.cvv}> <br>
			Expiry Date:<br> <input type="text" name="expiryDate"
				value=${sessionScope.identity.expiry}> <br>
		</fieldset>
		<button class="profile-button" type="submit" name="todo" value="update"
						>Save</button>

	</form>
	<fieldset>
	<c:forEach items="${sessionScope.identity.orders}" var="one">
		Order: ${one.dateOfPurchase}
		<c:forEach items="${one.order}" var="item">
			Item: ${item.name} Amount: ${item.amount}
		</c:forEach>
		<br/>
	</c:forEach>
	</fieldset>
	
	<button class="profile-button"
						onclick="location.href='HomeServlet'">Home</button>

</body>
</html>