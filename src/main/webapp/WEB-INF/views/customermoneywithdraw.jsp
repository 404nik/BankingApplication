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
       text-align: right;
   }
   .header-center {
       text-align: center;
       position: relative;
       flex: 2;
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
  .form-box hr {
      width: 100%;
      border: 0;
      border-top: 2px solid #bdc3c7;
      margin: 10px 0;
  }
  .form-box table {
      width: 100%;
      margin: 0 auto;
  }
  .form-box td {
      padding: 10px;
  }
  .form-box input[type="number"] {
      width: calc(100% - 20px);
      padding: 10px;
      border: 1px solid #3498db;
      border-radius: 5px;
      font-size: 16px;
      margin-bottom: 15px;
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
      margin: 10px;
  }
  .form-box input[type="submit"]:hover {
      background-image: linear-gradient(45deg, #2980b9, #3498db);
      transform: translateY(-5px);
      box-shadow: 0 8px 20px rgba(52, 152, 219, 0.6);
  }
  .form-box .available-balance {
      font-size: 16px;
      margin-top: 10px;
      color: #27ae60;
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
  .error-message {
      color: #e74c3c;
      font-size: 14px;
      margin-top: 10px;
  }
  
  footer {
     background-color: #2c3e50;
     color: white;
     padding: 10px 0;
     text-align: center;
 }
 select {
    width: 215px;
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
.success-message {
    color: #27ae60;
    background-color: #ecf9f3;
    padding: 10px;
    border: 1px solid #27ae60;
    margin-bottom: 15px;
    border-radius: 5px;
}

.error-message {
    color: #e74c3c;
    background-color: #f9ecec;
    padding: 10px;
    border: 1px solid #e74c3c;
    margin-bottom: 15px;
    border-radius: 5px;
}


</style>
<script>
function updateBalance() {
    const currentBalanceField = document.getElementById('currentBalance');
    const amountField = document.getElementById('amount');
    const availableBalanceField = document.getElementById('availableBalance');
    const withdrawButton = document.getElementById('withdrawButton');
    const errorMessage = document.getElementById('errorMessage');


    const currentBalance = parseFloat(currentBalanceField.value);
    const amount = parseFloat(amountField.value);


    if (!isNaN(currentBalance) && !isNaN(amount)) {
        const newBalance = currentBalance - amount;
        availableBalanceField.value = newBalance.toFixed(2);


        if (newBalance < 0) {
            errorMessage.textContent = "Insufficient balance!";
            withdrawButton.disabled = true; // Disable the button
            withdrawButton.style.cursor = "not-allowed";
        } else {
            errorMessage.textContent = "";
            withdrawButton.disabled = false; // Enable the button
            withdrawButton.style.cursor = "pointer";
        }
    } else {
        availableBalanceField.value = currentBalance.toFixed(2);
    }
}
</script>
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
<div class="form-container">
    <div class="form-box">
        <h1>Withdraw Money</h1>
        <hr>
        <!-- Display success or error messages -->
        <c:if test="${not empty successMessage}">
            <div class="success-message">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>
        
        <!-- Form elements -->
        <form action="validatingwithdraw" method="post">
            <table>
                <tr>
                    <td><label for="transactionAccountNumber">Select Account:</label></td>
                    <td>
                        <select name="transactionAccountNumber" required>
                            <option value="">Select Account</option>
                            <c:forEach var="account" items="${accountlist}">
                                <option value="${account.accountNumber}">
                                    ${account.accountType} - ${account.accountNumber}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <!-- <tr>
                    <td><label for="currentBalance">Current Account Balance:</label></td>
                    <td><input type="number" id="currentBalance" name="currentBalance" value="${currentBalance}" readonly></td>
                </tr> -->
                <tr>
                    <td><label for="amount">Amount:</label></td>
                    <td><input type="number" id="amount" name="transactionAmount" required></td>
                </tr>
                <!-- <tr>
                    <td><label for="availableBalance">Available Balance After Withdraw:</label></td>
                    <td><input type="number" id="availableBalance" name="availableBalance" readonly></td>
                </tr> -->
            </table>
            <input type="submit" value="Withdraw">
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
