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
    /* Reset some basic elements */
    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }

    body {
        font-family: 'Arial', sans-serif;
        background-color: #f4f7fa;
        color: #333;
        display: flex;
        flex-direction: column;
        height: 100vh;
        overflow: hidden; /* Disable scroll */
    }

    header {
        background-color: #34495e;
        padding: 20px 40px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        color: white;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    .header-left h1 {
        font-size: 28px;
        margin: 0;
    }

    .header-right .admin-name {
        font-size: 18px;
        color: #ecf0f1;
        font-weight: bold;
    }

    .user-info {
        display: flex;
        justify-content: space-between;
        padding: 30px 50px;
        background-color: #ecf0f1;
        flex: 1;
        overflow-y: auto; /* Enable scroll if content overflows */
    }

    .user-details {
        flex: 1;
        display: flex;
        flex-direction: column;
        background-color: white;
        padding: 20px;
        border-radius: 8px;
        border: 2px solid #34495e;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        margin-right: 20px;
    }

    .user-details-top {
        display: flex;
        align-items: center;
        margin: 12px 0;
    }

    .user-details-top img {
        border-radius: 50%;
        height: 120px;
        width: 120px;
        object-fit: cover;
        margin-right: 20px;
        border: 2px solid #34495e;
    }

    .user-info-text p {
        font-size: 16px;
        margin: 5px 0;
        color: black;
    }

    .details-below-photo p {
        font-size: 14px;
        margin: 12px 0;
        color: black;
        padding: 8px;
        border: 2px outset #000000;
        border-radius: 5px;
        background-color: rgb(213, 227, 250);
        width: 100%; 
    }

    .user-info-text p {
        font-size: 14px;
        margin: 4px 0;
        color: black;
        padding: 8px;
        border: 2px outset #000000;
        border-radius: 5px;
        background-color: rgb(213, 227, 250);
        width: 550px;
    }

    .services-and-transaction {
        flex: 1;
        display: flex;
        flex-direction: column;
        justify-content: space-between; /* Align services at the top and transaction table at the bottom */
        height: 100%;
    }

    .services {
        height: 220px;
        background-color: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        border: #34495e 2px solid;
        margin-bottom: 20px;
    }

    .services h1 {
        font-size: 24px;
        margin-bottom: 10px;
        color: #34495e;
        text-align: center;
    }

    .button-row {
        display: flex;
        justify-content: center;
        gap: 15px; /* Added gap for equal spacing between buttons */
        margin-bottom: 20px; /* Adjusted margin for better spacing */
    }

    .button-row .btn1 {
        background-color: #34495e;
        color: white;
        border: none;
        padding: 15px;
        font-size: 16px;
        cursor: pointer;
        text-decoration: none;
        border-radius: 5px;
        width: 280px;
        transition: background-color 0.3s ease;
        text-align: center;
    }

    .button-row .btn1:hover {
        background-color: #2c3e50;
    }

    .divider {
        border: 2px;
        border-top: 2px solid #000000;
        width: 100%;
        margin-bottom: 15px;
    }

    /* Transaction table styling */
    .transaction-table {
        width: 100%;
        margin-top: 20px;
        border-collapse: collapse;
        background-color: white;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        border-radius: 8px;
        border: #34495e 2px solid;
    }

    .transaction-table th, .transaction-table td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    .transaction-table th {
        background-color: #34495e;
        color: white;
    }

    .transaction-table td {
        font-size: 14px;
        color: #333;
    }

    footer {
        background-color: #34495e;
        color: white;
        padding: 20px 40px;
        text-align: center;
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
        margin-bottom: 10px;
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
        gap: 15px;
    }

    /* Responsive Design */
    @media (max-width: 1200px) {
        .user-info {
            padding: 20px 30px;
        }

        .user-details {
            margin-right: 15px;
        }

        .services {
            padding: 15px;
        }

        .button-row .btn1 {
            padding: 10px 20px;
            margin: 0 8px;
            width: 200px;
        }
    }

    @media (max-width: 768px) {
        .user-info {
            flex-direction: column;
            padding: 20px;
        }

        .user-details {
            margin-right: 0;
            margin-bottom: 20px;
        }

        .services-and-transaction {
            width: 100%;
        }

        .footer-content {
            flex-direction: column;
            align-items: center;
        }

        .footer-content .policies {
            justify-content: center;
        }
    }
</style>
</head>
<body>

<!-- Header -->
<header>
    <div class="header-left">
        <h1>INB STATE BANK</h1>
    </div>
    <div class="header-right">
        <div class="admin-name">
            Admin: 
            <%
                // Retrieve username from session
                String username = (String) session.getAttribute("firstname");
                if (username != null) {
                    out.print(username);
                } else {
                    out.print("Guest");
                }
            %>
        </div>
    </div>
</header>

<!-- User Information Section -->
<div class="user-info">
    <!-- User Details on the Left -->
    <div class="user-details">
        <div class="user-details-top">
            <img src="data:image/jpeg;base64,${customerphoto}" alt="User Photo">
            <div class="user-info-text">
                <p><strong>Name:</strong> ${customername}</p>
                <p><strong>Customer ID:</strong> ${customerid}</p>
                <p><strong>Email:</strong> ${customeremail}</p>
                <p><strong>Phone:</strong> +91-XXXXXX1234</p>
            </div>
        </div>
        <div class="details-below-photo">
            <p><strong>IFSC Code:</strong> 1234SBIN001</p>
            <p><strong>MICR Code:</strong> 123450021</p>
        </div>
    </div>

    <!-- Services and Transaction Table on the Right -->
    <div class="services-and-transaction">
        <!-- Services Section -->
        <div class="services">
            <h1>Services</h1>
            <div class="button-row">
                <a href="/openAccount" class="btn1">Open Account</a>
                <a href="/viewTransactions" class="btn1">View Transactions</a>
            </div>
            <hr class="divider">
        </div>

        <!-- Transaction Table Section (on the bottom-right corner) -->
        <div class="transaction-table-container">
            <table class="transaction-table">
                <thead>
                    <tr>
                        <th>Transaction ID</th>
                        <th>Transaction Type</th>
                        <th>From Account</th>
                        <th>To Account</th>
                        <th>Amount</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="transaction" items="${transactions}">
                        <tr>
                            <td>${transaction.transactionId}</td>
                            <td>${transaction.transactionType}</td>
                            <td>${transaction.fromAccount}</td>
                            <td>${transaction.toAccount}</td>
                            <td>${transaction.amount}</td>
                            <td>${transaction.date}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Footer Section -->
<footer>
    <div class="footer-content">
        <div>
            <h3>Customer Care</h3>
            <p>Call Us: 1800-123-456</p>
            <p>Email: support@inbstatebank.com</p>
        </div>
        <div class="policies">
            <a href="/privacyPolicy">Privacy Policy</a>
            <a href="/termsAndConditions">Terms & Conditions</a>
        </div>
    </div>
</footer>

</body>
</html>
