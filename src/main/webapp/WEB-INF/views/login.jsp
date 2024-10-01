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
        text-align: center;
    }

    header h1 {
        color: white;
        font-size: 28px;
        margin: 0;
    }

    .form-container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: calc(100vh - 90px); /* Adjust height to fit header and footer */
    }

    .form-box {
        width: 400px;
        padding: 20px;
        background-color: #fff;
        border: 2px solid #3498db;
        border-radius: 10px;
        box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
        display: flex;
        flex-direction: column;
        align-items: center;
        text-align: center; /* Center text */
    }

    .form-box h1 {
        margin: 0;
        font-size: 24px;
        margin-bottom: 10px; /* Reduced margin between title and hr */
    }

    .form-box hr {
        width: 100%;
        border: 0;
        border-top: 2px solid #bdc3c7;
        margin: 10px 0; /* Reduced margin around hr */
    }

    .form-box table {
        width: 100%;
        margin: 0 auto; /* Center table */
    }

    .form-box td {
        padding: 10px;
    }

    .form-box input[type="text"],
    .form-box input[type="password"] {
        width: calc(100% - 20px); /* Full width minus padding */
        padding: 10px;
        border: 1px solid #3498db;
        border-radius: 5px;
        font-size: 16px;
        margin-bottom: 15px; /* Reduced margin between input fields and buttons */
    }

    .form-box input[type="submit"],
    .form-box input[type="reset"] {
        padding: 12px 25px;
        font-size: 18px;
        color: white;
        background-image: linear-gradient(45deg, #3498db, #2980b9); /* Gradient button */
        border: none;
        border-radius: 8px;
        cursor: pointer;
        transition: all 0.3s ease;
        box-shadow: 0 5px 15px rgba(52, 152, 219, 0.4);
        margin: 10px; /* Margin between buttons */
        display: inline-block; /* Ensure buttons are aligned properly */
    }

    .form-box input[type="submit"]:hover,
    .form-box input[type="reset"]:hover {
        background-image: linear-gradient(45deg, #2980b9, #3498db); /* Reverse gradient on hover */
        transform: translateY(-5px);
        box-shadow: 0 8px 20px rgba(52, 152, 219, 0.6);
    }

    .form-box .errors {
        color: #e74c3c; /* Red for errors */
        font-size: 14px;
    }

    .form-box .show-password {
        margin-top: 10px; /* Space above checkbox */
        text-align: left; /* Align checkbox to the left */
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
    <h1>INB STATE BANK</h1>
</header>

<!-- Form Container -->
<div class="form-container">
    <div class="form-box">
        <h1>Admin Portal</h1>
        <hr>
        <f:form action="validation" method="post" modelAttribute="login" onsubmit="return validateForm()">
            <table>
                <tr>
                    <td>Username</td>
                    <td><f:input id="username" path="username" /></td>
                    <td><span id="usernameError" class="errors"></span></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><f:input id="password" type="password" path="password" /></td>
                    <td><span id="passwordError" class="errors"></span></td>
                </tr>
                <tr>
                    <td colspan="2" class="show-password">
                        <label>
                            <input type="checkbox" id="showPassword" />
                            Show Password
                        </label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <input type="reset" value="Clear" />
                        <input type="submit" value="Login" />
                    </td>
                </tr>
            </table>
        </f:form>
    </div>
</div>

<!-- Footer -->
<footer>
    <div class="footer-content">
        <p>&copy; 2024 INB STATE BANK. All rights reserved.</p>
    </div>
</footer>

<script>
    // Function to validate the form
    function validateForm() {
        let valid = true;
        
        // Get the form fields
        const username = document.getElementById('username').value.trim();
        const password = document.getElementById('password').value;
        
        // Get the error message elements
        const usernameError = document.getElementById('usernameError');
        const passwordError = document.getElementById('passwordError');
        
        // Clear previous errors
        usernameError.textContent = '';
        passwordError.textContent = '';
        
        // Validate username
        if (username === '') {
            usernameError.textContent = 'Username cannot be blank.';
            valid = false;
        }
        
        // Validate password
        if (password === '') {
            passwordError.textContent = 'Password cannot be blank.';
            valid = false;
        } else if (password.length < 6) {
            passwordError.textContent = 'Password must be at least 6 characters long.';
            valid = false;
        }
        
        return valid; // Return false if validation fails to prevent form submission
    }

    // JavaScript to toggle password visibility
    document.getElementById('showPassword').addEventListener('change', function() {
        const passwordField = document.getElementById('password');
        passwordField.type = this.checked ? 'text' : 'password';
    });
</script>
</body>
</html>
