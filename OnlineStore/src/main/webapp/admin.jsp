<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Panel</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
}

header {
	background-color: #333;
	color: #fff;
	padding: 20px;
}

nav {
	background-color: #f5f5f5;
	padding: 10px;
}

nav ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

nav ul li {
	display: inline;
	margin-right: 10px;
}

nav ul li a {
	text-decoration: none;
	color: #333;
	padding: 5px;
}

section {
	padding: 20px;
}

footer {
	background-color: #333;
	color: #fff;
	padding: 10px;
	text-align: center;
}

#displayBox {
	width: 95%;
	height: 600px;
	border: 1px solid #000;
	margin: 20px auto;
	
	padding: 10px;
	overflow: auto;
}
</style>
<script>
	// JavaScript function to handle options clicked
	function showContent(content) {
		document.getElementById('welcomeText').style.display = 'none';
		document.getElementById('displayBox').style.display = 'block';

		if (content === 'Inventory Content') {
			// Make an AJAX request to retrieve inventory data
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					// Update displayBox with inventory data
					var inventoryData = JSON.parse(xhr.responseText);
					var inventoryList = "<ul>";
					for (var i = 0; i < inventoryData.length; i++) {
						inventoryList += "<li>" + inventoryData[i].itemName
								+ " - Quantity: " + inventoryData[i].quantity
								+ "</li>";
					}
					inventoryList += "</ul>";
					document.getElementById('displayBox').innerHTML = inventoryList;
				}
			};
			xhr.open("GET", "admin", true);
			xhr.send();
		} 
		else if (content === 'User Accounts Content') {
			
	        // Make an AJAX request to retrieve user accounts data
	        var xhr = new XMLHttpRequest();
	        xhr.onreadystatechange = function () {
	            if (xhr.readyState == 4 && xhr.status == 200) {
	                // Update displayBox with user accounts data
	                var userData = JSON.parse(xhr.responseText);
	                var userList = "<ul>";
	                for (var i = 0; i < userData.length; i++) {
	                    userList += "<li>" + userData[i].username + " - Email: " + userData[i].email + "</li>";
	                }
	                userList += "</ul>";
	                document.getElementById('displayBox').innerHTML = userList;
	            }
	        };
	        xhr.open("GET", "adminUsers", true);
	        xhr.send();
	    } else {
	        // Handle other content options
	        document.getElementById('displayBox').innerHTML = "Error";
	    }
	}
</script>
</head>
<body>
	<header>
		<h1>Admin Panel</h1>
	</header>

	<nav>
	
			<ul>
			<li>
			<form action="AdminServlet">
			<button type="submit" name="todo" value="history">Check
					Sales History</button>
					</form>
					</li>
				<li>	<form action="AdminServlet">
			<button type="submit" name="todo" value="inventory">Check
					Inventory</button>
					</form></li>
					<li><form action="AdminServlet">
			<!-- <button type="submit" name="todo" value="accounts">Maintain
					User Accounts</button> -->
					<button><a href="#" onclick="showContent('User Accounts Content')">Maintain
					User Accounts</a></button>
					</form></li>
			<li style="float: right;"><a href="#"
				onclick="location.href='HomeServlet'">Back to Home</a></li>
		</ul>
		
	</nav>

	<section>
		<h2 id="welcomeText">Welcome, Administrator!</h2>
		<div id="displayBox">
		<c:choose>
		<c:when test="${sessionScope.display == 'inventory'}">
		<c:forEach items="${sessionScope.displayItems}" var="e">
			<form action="AdminServlet">
			<p>${e.name}: Remaining Quantity: <input type="text" name="newQty" value=${e.qty}> <input type="hidden" name="id" value=${e.id}><input type="submit" name="todo" value="update">
			</p>
			</form>
		</c:forEach>
		</c:when>
		<c:when test="${sessionScope.display == 'orders' }">
		<c:forEach items="${sessionScope.orders}" var="e">
		Order: ${e.dateOfPurchase}
		<c:forEach items="${e.order}" var="item">
			Item: ${item.name} Amount: ${item.amount}
		</c:forEach>
		<br/>
		</c:forEach>
		</c:when>
		
		</c:choose>
		</div>
	</section>

</body>
</html>
