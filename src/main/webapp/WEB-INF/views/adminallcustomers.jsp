<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Admin Page</title>
<link rel="icon" type="image/x-icon" href="https://t4.ftcdn.net/jpg/00/61/06/27/360_F_61062796_NF87GPnWV0fQ2LhoYNlyjev0PocRwZj9.jpg">

<style>
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f0f4f8;
        color: #333;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        height: 100vh;
        overflow: hidden; /* Disable scroll */
    }

    header {
        background-color: #2c3e50;
        padding: 20px;
        display: flex;
        align-items: center;
        color: white;
    }

    .header-left {
        flex: 1;
        text-align: left;
    }

    .header-right {
        flex: 1;
        text-align: right; /* Admin name aligned to the right */
    }

    .header-center {
        text-align: center; /* Centered back button */
        position: relative;
        flex: 2; /* Adjust width proportionally */
    }

    header h1 {
        font-size: 28px;
        margin: 0;
    }

    .admin-name {
        font-size: 20px;
        color: #ecf0f1;
        font-weight: bold;
    }

    .back-button {
        background-color: #2c3e50;
        color: white;
        padding: 10px 20px;
        text-decoration: none;
        border-radius: 5px;
        font-size: 16px;
        display: inline-block;
    }

    .back-button:hover {
        background-color: #34495e;
    }

    footer {
        background-color: #2c3e50;
        color: white;
        padding: 10px 0;
        text-align: center;
        position: absolute; /* Positioned at the bottom of the page */
        bottom: 0;
        width: 100%;
    }

    .footer-content {
        display: flex;
        justify-content: space-around;
        flex-wrap: wrap;
        gap: 20px;
    }

    .footer-content div {
        flex: 1;
        min-width: 150px;
    }

    .footer-content h3 {
        color: #ecf0f1;
        font-size: 18px;
    }

    .footer-content p,
    .footer-content a {
        color: #bdc3c7;
        font-size: 14px;
        margin: 5px 0;
        text-decoration: none;
    }

    .footer-content a:hover {
        text-decoration: underline;
    }

    .footer-content .policies {
        display: flex;
        gap: 20px; /* Spacing between links */
    }

    /* New Styles for Table */
    .table-container {
        max-height: 450px; /* Adjust the height as needed */
        overflow-y: auto; /* Enable vertical scrolling */
        margin-top: -2.1%; /* Margin for spacing */
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin: 20px 0; /* Margin for spacing */
        /* margin-bottom: 10%; */
    }

    th, td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #2c3e50;
        color: white;
        font-weight: bold;
    }

    tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    tr:hover {
        background-color: #f1c40f;
        color: #2c3e50;
    }

    a {
        color: #3498db;
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }

    .action-links {
        display: flex;
        gap: 20px; /* Space between buttons */
    }

    .approve-button {
        background-color: #27ae60; /* Green */
        color: white;
        padding: 8px 16px;
        border-radius: 5px;
        text-decoration: none;
    }

    .approve-button:hover {
        background-color: #2ecc71; /* Lighter green */
    }

    .reject-button {
        background-color: #e74c3c; /* Red */
        color: white;
        padding: 8px 16px;
        border-radius: 5px;
        text-decoration: none;
    }

    .reject-button:hover {
        background-color: #c0392b; /* Darker red */
    }

    .pending-title {
        text-align: center;
        color: #2c3e50;
        font-size: 24px; /* Slightly smaller */
        margin-top: 20px;
        position: relative;
    }

    .pending-title::after {
        content: "";
        display: block;
        width: 100px; /* Adjust the width as needed */
        height: 2px;
        background-color: #2c3e50;
        margin: 10px auto 0;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    }
</style>
</head>
<body>

<!-- Header -->
<header>
    <div class="header-left">
        <h1>INB STATE BANK</h1>
    </div>
    <div class="header-center">
        <a href="/app/homepage" class="back-button">Back</a>
    </div>
    <div class="header-right">
        <div class="admin-name">
            Admin: 
            <%
                // Retrieve username from session
                String username = (String) session.getAttribute("username");
                if (username != null) {
                    out.print(username);
                } else {
                    out.print("Guest");  // Fallback if no username is found
                }
            %>
        </div>
    </div>
</header>

<!-- Page Content -->
<h1 class="pending-title">Customer Details</h1>
<hr>

<!-- Table Container with Scroll -->
<div class="table-container">
    <table>
        <tr>
            <th>Customer ID</th>
            <th>Customer First Name</th>
            <th>Customer Last Name</th>
            <th>Customer Email</th>
            <th>Customer Phone</th>
            <th>Customer Address</th>
            <th>Customer DOB</th>
            <th>Customer Account Creation Date</th>
            <th>Customer Account Status</th>
            <!-- <th colspan="2">Actions</th> -->
        </tr>
        <c:forEach items="${allCustomers}" var="customer">
            <tr>
                <td>${customer.customerId}</td>
                <td>${customer.customerFirstName}</td>
                <td>${customer.customerLastName}</td>
                <td>${customer.customerEmail}</td>
                <td>${customer.customerMobile}</td>
                <td>${customer.customerAddress}</td>
                <td>${customer.customerDob}</td>
                <td>${customer.customerAccountCreationDate}</td>
                <td>${customer.customerAccountStatus}</td>
                <!-- <td class="action-links">
                     <a href="approveCustomer?customerId=${customer.customerId}" class="approve-button">Unblock</a> -->
                    <!-- <a href="rejectCustomer?customerId=${customer.customerId}" class="reject-button">Reject</a>/a> -->
                <!-- </td> --> 
            </tr>
        </c:forEach>
    </table>
</div>

<!-- Footer -->
<footer>
    <div class="footer-content">
        <p>&copy; 2024 INB STATE BANK. All rights reserved.</p>
    </div>
</footer>

<script>
    // JavaScript to toggle password visibility
    document.getElementById('showPassword').addEventListener('change', function() {
        const passwordField = document.getElementById('password');
        passwordField.type = this.checked ? 'text' : 'password';
    });
</script>
</body>
</html>
