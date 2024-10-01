<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.mindgate.main.domain.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Admin Page</title>
<link rel="icon" type="image/x-icon"
	href="https://t4.ftcdn.net/jpg/00/61/06/27/360_F_61062796_NF87GPnWV0fQ2LhoYNlyjev0PocRwZj9.jpg">
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

.header-button {
	display: inline-block;
	padding: 10px 20px;
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

header h1 {
	font-size: 28px;
	margin: 0;
}

.form-container {
	display: flex;
	justify-content: center;
	align-items: center;
	height: calc(100vh - 90px);
	/* Adjust height to fit header and footer */
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

.form-box input[type="text"], .form-box input[type="password"],
	.form-box input[type="number"] {
	width: calc(100% - 20px); /* Full width minus padding */
	padding: 10px;
	border: 1px solid #3498db;
	border-radius: 5px;
	font-size: 16px;
	margin-bottom: 15px;
	/* Reduced margin between input fields and buttons */
}

.form-box input[type="submit"], .form-box input[type="reset"] {
	padding: 12px 25px;
	font-size: 18px;
	color: white;
	background-image: linear-gradient(45deg, #3498db, #2980b9);
	/* Gradient button */
	border: none;
	border-radius: 8px;
	cursor: pointer;
	transition: all 0.3s ease;
	box-shadow: 0 5px 15px rgba(52, 152, 219, 0.4);
	margin: 10px; /* Margin between buttons */
	display: inline-block; /* Ensure buttons are aligned properly */
}

.form-box input[type="submit"]:hover, .form-box input[type="reset"]:hover
	{
	background-image: linear-gradient(45deg, #2980b9, #3498db);
	/* Reverse gradient on hover */
	transform: translateY(-5px);
	box-shadow: 0 8px 20px rgba(52, 152, 219, 0.6);
}

.form-box .message {
	color: #27ae60; /* Success message color */
	font-size: 14px;
	margin-top: 10px;
}

.form-box .error {
	color: #e74c3c; /* Error message color */
	font-size: 14px;
	margin-top: 10px;
}

.form-box select {
	width: calc(100%); /* Full width minus padding */
	padding: 10px;
	border: 1px solid #3498db;
	border-radius: 5px;
	font-size: 16px;
	margin-bottom: 15px; /* Same as input fields */
	background-color: #fff;
	color: #333;
	appearance: none; /* Remove default arrow in some browsers */
	-webkit-appearance: none;
	-moz-appearance: none;
	background-image:
		url('data:image/svg+xml;charset=US-ASCII,<svg xmlns="http://www.w3.org/2000/svg" width="10" height="10" viewBox="0 0 10 10"><polygon fill="%23498db" points="0,0 5,5 10,0"/></svg>');
	background-position: right 10px center;
	background-repeat: no-repeat;
	background-size: 10px;
}

.form-box select:focus {
	outline: none;
	box-shadow: 0 0 5px rgba(52, 152, 219, 0.6);
	border-color: #2980b9;
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

.footer-content p, .footer-content a {
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
			<a href="/app/customerbackhomepage" class="back-button">Back</a>
		</div>
		<div class="header-right">
			<div class="admin-name">
				Customer:
				<%
			// Retrieve username from session
			String username = (String) session.getAttribute("firstname");
			if (username != null) {
				out.print(username);
			} else {
				out.print("Guest"); // Fallback if no username is found
			}
			%>
			</div>
		</div>
	</header>


	<!-- Form Container -->
	<div class="form-container">
		<div class="form-box">
			<h1>Money Deposit</h1>
			<hr>

			<!-- <%-- 			<f:form action="validatingdeposit" modelAttribute="transaction">

				<select name="selectAccountNumber">
					<c:forEach var="account" items="accountlist">
						<option>${account}</option>
					</c:forEach>


				</select>

			</f:form> --%> -->

			<form action="validatingdeposit" method="post">
                <select name="transactionAccountNumber" required>
                    <option value="">Select Account</option>
                    <c:forEach var="account" items="${accountlist}">
                        <option value="${account.accountNumber}">
                            ${account.accountType} - ${account.accountNumber}
                        </option>
                    </c:forEach>
                </select>
            
                <input type="number" name="transactionAmount" required placeholder="Enter amount">
                <input type="submit" value="Deposit">
            </form>

<!-- 
			<%-- <form action="validatingdeposit" method="post">
				<table>
					<tr>
						<td><label for="toAccount">To Account Number:</label></td>
						<td><select id="toAccount" name="accountNumber" required>
								<option value="">Select Account</option>
								<%
								// Fetch the account list from session
								List<Account> accountList = (List<Account>) session.getAttribute("accountlist");
								if (accountList != null) {
									for (Account account : accountList) {
								%>
								<option value="<%=account.getAccountNumber()%>">
									<%=account.getAccountType()%> -
									<%=account.getAccountNumber()%>
								</option>
								<%
								}
								} else {
								%>
								<option value="">No accounts available</option>
								<%
								}
								%>
						</select></td>

					</tr>
					<tr>
						<td><label for="amount">Amount:</label></td>
						<td><input type="number" id="amount" name="transactionAmount"
							required></td>
					</tr>
				</table>
				<input type="submit" id="depositbtn" value="Deposit" disabled
					style="cursor: not-allowed;">
			</form>
 --%> -->
			<!-- Success or Error Message -->
			<%-- <c:if test="${not empty message}"> --%>
			<%-- <div class="message">${message}</div> --%>
			<%-- </c:if> --%>
			<%-- <c:if test="${not empty error}"> --%>
			<%-- <div class="error">${error}</div> --%>
			<%-- </c:if> --%>
		</div>
	</div>



	<!-- Footer -->
	<footer>
		<div class="footer-content">
			<p>&copy; 2024 INB STATE BANK. All rights reserved.</p>
		</div>
	</footer>
</body>
<script>
	const accountDropdown = document.getElementById('toAccount');
	const depositButton = document.getElementById('depositbtn');
	// acc

	// Function to enable/disable the button based on dropdown selection
	accountDropdown.addEventListener('change', function() {
		// Check if the dropdown value is empty or null
		if (accountDropdown.value === "" || accountDropdown.value === null) {
			depositButton.disabled = true; // Disable if no account selected
			depositButton.style.cursor = "not-allowed";

		} else {
			depositButton.disabled = false; // Enable if an account is selected
			depositButton.style.cursor = "pointer";
		}
	});
</script>
</html>