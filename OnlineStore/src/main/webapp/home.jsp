<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Store</title>
<link rel="stylesheet" type="text/css" href="css/home.css">

<script>
	function incrementQuantity(e) {
		var quantityInput = e.previousElementSibling;
		quantityInput.value = parseInt(quantityInput.value) + 1;
	}
</script>

</head>
<body>
	<header>
		<div class="logo">
			<img src="images/tempLogo.png" alt="Store Logo"> <span>Shop for Style!</span>
		</div>

		<form action="HomeServlet">
			<button type="submit" name="todo" value="reset">Remove All
				Filters</button>
		</form>
		<div>
			<form action="HomeServlet">
				Filter By Category: <select name="catfilter">
					<option value="shoes" selected>Shoes</option>
					<option value="pants">Pants</option>
				</select>
				<button type="submit" name="todo" value="cfil">Filter</button>
			</form>
		</div>
		<div>
			<form action="HomeServlet">
				Filter By Brand: <select name="brandfilter">
					<option value="Nike" selected>Nike</option>
					<option value="CAT">CAT</option>
				</select>
				<button type="submit" name="todo" value="bfil">Filter</button>
			</form>
		</div>
		<div>
			<form action="HomeServlet">
				Sort By: <select name="sort">
					<option value="name" selected>Name</option>
					<option value="expen">Most Expensive</option>
					<option value="cheap">Least Expensive</option>
				</select>
				<button type="submit" name="todo" value="sort">Sort</button>
			</form>
		</div>

		<div class="buttons">

			<c:choose>
				<c:when test="${sessionScope.identity != null}">
					<button class="profile-button"
						onclick="location.href='account.jsp'">Profile</button>
					<button class="profile-button"
						onclick="location.href='SignoutServlet'">Sign Out</button>
					<c:if test="${sessionScope.identity.user == 'asd'}">
						<button class="profile-button"
							onclick="window.location.href='admin.jsp'">admin panel</button>
					</c:if>
				</c:when>
				<c:otherwise>
					<button class="profile-button" onclick="location.href='signin.jsp'">Sign
						In</button>
				</c:otherwise>
			</c:choose>
			
			<button onclick="window.location.href='cart.jsp'">Cart</button>



		</div>

	</header>

	<div class="bodySection">
		<form action="CartServlet">
			<input type="hidden" name="todo" value="add">
			<!-- <button
				style="display: block; margin-left: auto; margin-right: auto;"
				type="submit">GO TO CART</button> -->
			<!-- Your items here. You can loop through your items and display them. -->
			<table id="grid">
				<thead>
					<!-- <tr>
					<th id="th-title"></th>
					<th id="th-author"></th>
					<th id="th-author1">Product Info</th>

				</tr> -->
				</thead>


				<tbody>

					<%-- <c:forEach items="${sessionScope.sessionCatalog.catalog}" var="e">
					<tr>
						<td>${e.name}</td>
						  <td><img src="ImageRender/${e.id}" alt="product image"/></td>
						<td>Price: ${e.price}       |</td>
						<td>${e.desc}</td>
						<td>|Brand: ${e.brand}  |</td>
						<c:forEach items="${applicationScope.catalog.catalog}" var="g">
							<c:if test="${e.id == g.id}"><td>|Quantity Available: ${e.qty}  |</td></c:if>
						</c:forEach>
						<td>Quantity:<input type="text" name="qty${e.id}" value="1"></td>
						<td>Add to cart:<input type="checkbox" name="wishlist" value="${e.id}"></td>
					</tr>

				</c:forEach> --%>

					<jsp:include page="productCard.jsp" />

				</tbody>

			</table>


		</form>
	</div>
</body>
</html>