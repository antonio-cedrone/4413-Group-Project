<!-- checkout.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link rel="stylesheet" type="text/css" href="css/checkout.css">
    <link rel="stylesheet" type="text/css" href="css/home.css"> <!-- Reuse existing CSS styles -->
    <script>
        function validateExpiryDate() {
            var today = new Date();
            var expiryDate = new Date(document.getElementById("expiryDate").value);
            
            if (expiryDate <= today) {
                alert("Expiry date must be in the future.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <header>
        <div class="logo">
            <img src="images/tempLogo.png" alt="Store Logo"> <span>store name idk yet</span>
        </div>
    </header>

    <div class="bodySection">
        <h2>Checkout</h2>
	
        <!-- Display Order Summary -->
        <table class="cart-items">
            <thead>
                <tr>
                    <th>Item Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${sessionScope.cart.cart}" var="item">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.price}</td>
                        <td>${item.qty}</td>
                        <td>${item.price * item.qty}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Display Total Price -->
        <div class="total-price">
            <p>Total Price: ${sessionScope.total}</p>
        </div>

        <!-- Personal Information Section -->
        <form action="PaymentServlet" method="post">
            <!-- Add fields for personal information -->
            <h2>Contact Information</h2>
            <fieldset>
                First Name:<br>
                <input type="text" name="firstName" required><br>
                Last Name:<br>
                <input type="text" name="lastName" required><br>
                Phone Number (10 digits):<br>
                <input type="text" name="phoneNumber" pattern="\d{10}" required><br>
                Email:<br>
                <input type="email" name="email" required><br>
            </fieldset>

            <!-- Delivery Address Section -->
            <h2>Delivery Address</h2>
            <!-- Add fields for delivery address -->
            <fieldset>
                Street Address:<br>
                <input type="text" name="streetAddress" required><br>
                Apartment or Unit # (optional):<br>
                <input type="text" name="apartmentUnit"><br>
                Province:<br>
                <input type="text" name="province" required><br>
                Postal Code:<br>
                <input type="text" name="postalCode" pattern="[A-Za-z]\d[A-Za-z] \d[A-Za-z]\d" required><br>
            </fieldset>

            <!-- Billing Information Section -->
            <h2>Billing Information</h2>
            <!-- Add fields for billing information -->
            <fieldset>
                Cardholder Name:<br>
                <input type="text" name="cardholderName" required><br>
                Card Number (16 digits):<br>
                <input type="text" name="cardNumber" pattern="\d{16}" required><br>
                Expiry Date:<br>
			    <input type="text" id="expiryDate" name="expiryDate" pattern="(0[1-9]|1[0-2])\/20\d{2}" 
			           placeholder="MM/YYYY" required onchange="validateExpiryDate()"><br>
                Billing Address:<br>
                <input type="text" name="billingAddress" required><br>
                Same as Delivery Address:
                <input type="checkbox" name="sameBillingAddress"><br>
            </fieldset>
            <br>
            <!-- Submit button to proceed with payment -->
            <button class="proceed-to-payment-button" type="submit" onclick="return validateExpiryDate()">Proceed to Payment</button>
        </form>
    </div>
</body>
</html>
