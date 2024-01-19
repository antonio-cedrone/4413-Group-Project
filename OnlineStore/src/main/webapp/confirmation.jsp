<!-- confirmation.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <link rel="stylesheet" type="text/css" href="css/checkout.css">
    <!-- Add your other CSS links if needed -->
    <style>
        /* Additional CSS for centering and spacing the order summary */
        table {
            margin: 20px auto; /* Center the table */
            width: 80%; /* Adjust the width as needed */
        }

        th, td {
            padding: 10px; /* Add padding to cells */
        }

        p {
            text-align: center; /* Center the total price */
            margin-top: 20px; /* Add some top margin */
        }

        h2 {
            text-align: center; /* Center the heading */
        }

        /* Style the button */
        .start-new-session-button {
            display: block;
            margin: 20px auto; /* Center the button */
        }
    </style>
</head>
<body>
    <h2>Order Confirmation</h2>

    <!-- Display Order Summary -->
    <table>
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
    <p>Total Price: ${sessionScope.total}</p>

    <!-- Add a button to go back to the main page and start a new session -->
    <form action="HomeServlet" method="post">
        <button class="proceed-to-payment-button start-new-session-button" type="submit">Back to Main Menu</button>
    </form>
</body>
</html>
