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
        justify-content: space-between;
        height: 100vh;
        overflow: hidden; /* Disable scroll */
    }

    header {
        background-color: #2c3e50;
        padding: 20px;
        display: flex;
        align-items: center;
        color: white;
        position: relative;
    }

    .header-left {
        flex: 1;
        text-align: left;
    }

    .header-right {
        flex: 1;
        text-align: right;
    }

    .header-center {
        flex: 3;
        text-align: center;
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

    .header-button {
        display: inline-block;
        padding: 10px 5px;
        background-color: #2c3e50;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        font-size: 16px;
        margin: 0 10px;
        transition: background-color 0.3s ease, transform 0.3s ease;
    }

    .header-button:hover {
        background-color: #34495e;
        transform: scale(1.05);
    }

    footer {
        background-color: #2c3e50;
        color: white;
        padding: 10px 0; /* Decreased padding to reduce height */
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
</style>
</head>
<body>

<!-- Header -->
<header>
    <div class="header-left">
        <h1>INB STATE BANK</h1>
    </div>
    <div class="header-center">
        <a href="unapprovedlist" class="header-button">New Customers</a>
        <a href="blockedlist" class="header-button">Blocked Customers</a>
        <a href="allcustomerlist" class="header-button">INB Customers</a>
        <a href= "newaccountapprovals" class="header-button">New Accounts</a>
        <a href= "newcheques" class="header-button">New Cheques</a>
        <a href="logout" class="header-button">Logout</a>
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
