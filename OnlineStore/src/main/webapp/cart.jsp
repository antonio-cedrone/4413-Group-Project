<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Store - Shopping Cart</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #232f3e;
	margin: 0;
	padding: 0;
}

.container {
	/* max-width: 960px; */
	width: 80%;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
	font-size: 24px;
	margin-bottom: 20px;
	color: #333;
}

.cart-items {
	width: 100%;
	border-collapse: collapse;
}

.cart-items th, .cart-items td {
	padding: 12px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}

.cart-items th {
	/* background-color: #3b4d61; */
}

.cart-items tr:last-child td {
	border-bottom: none;
}


.product-info {
	display: flex;
	align-items: center;
	text-align: left;
}

.product-info img {
	width: 80px;
	height: 80px;
	margin-right: 10px;
}

.total-price {
	margin-top: 20px;
	text-align: right;
	font-size: 18px;
	font-weight: bold;
	color: black;
}

.buttons {
	margin-top: 20px;
	display: flex;
	justify-content: flex-end;
}

.back-button, .checkout-button {
	background-color: #79c879;
	color: #fff;
	border: none;
	padding: 12px 20px;
	font-size: 16px;
	font-weight: bold;
	cursor: pointer;
	margin-left: 10px;
}

.back-button:hover, .checkout-button:hover {
	background-color: #5b9a5b;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Shopping Cart</h1>
		<table class="cart-items">
			<thead>
				<tr>
					<th>Item Name</th>
					<th>Description</th>
					<th>Price($)</th>
					<th>Quantity</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.cart.cart}" var="e">
					<tr>
						<td class="product-info"><img src="ImageRender/${e.id}"
							alt="Product Image">
							<div>${e.name}</div></td>
						<td>${e.desc}</td>
						<td>${e.price}</td>
						<td><form action="CartServlet">
								<input type="text" name="newQty" value="${e.qty}"> <input
									type="hidden" name="id" value="${e.id}"> <input
									type="submit" name="todo" value="Update">
							</form></td>
						<td>
							<form action="CartServlet" method="post">
								<input type="hidden" name="id" value="${e.id}"> <input
									type="submit" name="todo" value="Delete">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="total-price">Total Price: ${sessionScope.total}</div>
		<div class="buttons">
			<form action="HomeServlet">
				<button type="submit" class="back-button">Go back home</button>
			</form>
			<form action="CheckoutServlet" method="get">
				<button class="checkout-button" type="submit" name="todo"
					value="checkout">Proceed to Checkout</button>
			</form>
		</div>
	</div>
</body>
</html>