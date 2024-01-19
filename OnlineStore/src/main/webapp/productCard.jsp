<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
.product-card {
	border: 1px solid #ccc;
	padding: 10px;
	margin: 10px;
	width: 200px;
	flex: 1 0 calc(20% - 20px);
    margin: 10px;
    display: flex;
    flex-direction: column;
}

.product-image {
	width: auto;  
    height: 200px; 
    object-fit: cover;
    display: block;  
    margin: auto
}

.product-name {
	font-size: 18px;
	margin: 10px 0;
}

.product-price {
	color: #b12704;
	font-size: 16px;
}

.product-desc {
	color: black;
	font-size: 16px;
}

.add-to-cart-btn {
	background-color: #f0c14b;
	border: 1px solid #a88734;
	padding: 5px 10px;
	text-align: center;
	margin-top: 10px;
	cursor: pointer;
}

.quantity-input {
	width: 30px;
	height: 20px;
	margin-right: 10px;
	text-align: center;
}

.product-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: start;
}

</style>

</head>
<body>
<div class="product-container">
	<c:forEach items="${sessionScope.sessionCatalog.catalog}" var="e">
	<div class="product-card">
		<img src="ImageRender/${e.id}" alt="Product Image"
			class="product-image">
		<h2 class="product-name">${e.name}</h2>
		<p class="product-desc">${e.desc}</p>
		<c:forEach items="${applicationScope.catalog.catalog}" var="g">
			<c:if test="${e.id == g.id}"><p class="product-desc">Available: ${g.qty}</p></c:if>
		</c:forEach>
		<p class="product-price">${e.price}</p>
		Qty:<input type="text" class="quantity-input" name="qty${e.id}" value="1">
		<button class="add-to-cart-btn" name="wishlist" value="${e.id}">Add
			to Cart</button>
	</div>
	</c:forEach>
	</div>

</body>
</html>
