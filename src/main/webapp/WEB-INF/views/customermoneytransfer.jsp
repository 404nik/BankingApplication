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
/* Styles (unchanged from your original) */
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
    overflow: hidden;
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
.form-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: calc(100vh - 90px);
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
    text-align: center;
}
.form-box h1 {
    margin: 0;
    font-size: 24px;
    margin-bottom: 10px;
}
.form-box input[type="text"],
.form-box input[type="number"] {
    width: calc(100% - 20px);
    padding: 10px;
    border: 1px solid #3498db;
    border-radius: 5px;
    font-size: 16px;
    margin-bottom: 15px;
}
.form-box select {
    width: calc(100%);
    padding: 10px;
    border: 1px solid #3498db;
    border-radius: 5px;
    font-size: 16px;
    margin-bottom: 15px;
    text-align: center;
}
.form-box input[type="submit"] {
    padding: 12px 25px;
    font-size: 18px;
    color: white;
    background-image: linear-gradient(45deg, #3498db, #2980b9);
    border: none;
    border-radius: 8px;
    cursor: pointer;
    transition: all 0.3s ease;
}
.form-box input[type="submit"]:hover {
    background-image: linear-gradient(45deg, #2980b9, #3498db);
}
footer {
    background-color: #2c3e50;
    color: white;
    padding: 10px 0;
    text-align: center;
}
select {
    width: 235px;
    padding: 10px;
    border: 2px solid #3498db;
    border-radius: 5px;
    font-size: 16px;
    background-color: white;
    color: #2c3e50;
    /* box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2); */
    transition: border-color 0.3s ease;
    appearance: none;
    margin-bottom: 15px;
}

select:focus {
    border-color: #2980b9;
    outline: none;
}

select option {
    padding: 10px;
    font-size: 16px;
}

select:hover {
    cursor: pointer;
    box-shadow: 0 8px 20px rgba(52, 152, 219, 0.6);
}

select:disabled {
    background-color: #ecf0f1;
    cursor: not-allowed;
}
</style>
</head>
<body>
<header>
  <div class="header-left">
      <h1>INB STATE BANK</h1>
  </div>
  <div class="header-center">
      <a href="customerbackhomepage" class="back-button">Back</a>
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
                  out.print("Guest");  // Fallback if no username is found
              }
          %>
      </div>
  </div>
</header>
<div class="form-container">
<div class="form-box">
    <h1>Money Transfer </h1>
    <hr>
    <form action="validatingtransfer" method="post">
        <div id="fromAccountField">
            <label for="fromAccount">From Account Number:</label>
            <select name="fromAccount" required>
                <option value="">Select Account</option>
                <c:forEach var="account" items="${accountlist}">
                    <option value="${account.accountNumber}">
                        ${account.accountType} - ${account.accountNumber}
                    </option>
                </c:forEach>
            </select>
        </div>
      
        <div id="toAccountField">
            <label for="toAccount">To Account Number:</label>
            <input type="number" id="toAccount" name="toAccount" required>
        </div>
      
        <div id="amountField">
            <label for="amount">Amount:</label>
            <input type="number" id="amount" name="transferAmount" required>
        </div>
      
        <!-- <div id="chequeNumberField">
            <label for="chequeNumber">Cheque Number:</label>
            <input type="number" id="chequeNumber" name="chequeNumber" required>
        </div> -->
        <input type="submit" value="Submit">
    </form>
</div>
</div>
<footer>
    <div class="footer-content">
        <p>&copy; 2024 INB STATE BANK. All rights reserved.</p>
    </div>
</footer>
</body>
</html>
