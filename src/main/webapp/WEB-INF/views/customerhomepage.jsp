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
    
    .header-center {
        flex: 3;
        text-align: center;
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

    .user-info {
        display: flex;
        justify-content: space-between;
        padding: 30px 50px;
        /* height: 300%; */
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
        height: 650px;
        border-radius: 8px;
        border: 2px solid #34495e;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        margin-right: 20px;

        
    }

    .user-details-top {
        display: flex;
        align-items: center;
        margin: 12px 0;
        /* width: ; */

    }

    .user-details-top img {
        border-radius: 50%;
        height: 120px;
        width: 120px;
        object-fit: cover;
        margin-right: 20px;
        /* margin: 10px 0; */
        border: 2px solid #34495e;
    }

    .user-info-text p {
        font-size: 16px;
        margin: 5px 0;
        color: black;
    }

    .details-below-photo {
        margin-top: 10px;
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

    .services {
        flex: 1;
        height: 220px;
        /* display: flex; */
        flex-direction: column;
        justify-content: center;
        align-items: center;
        background-color: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        border: #34495e 2px solid;
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

    .btn1 {
        background-color: #34495e;
        color: white;
        border: none;
        padding: 5px; /* Adjusted padding for consistent sizing */
        font-size: 16px;
        cursor: pointer;
        text-decoration: none;
        border-radius: 5px;
        width: 280px; /* Set width to full to fit within container */
        transition: background-color 0.3s ease;
        text-align: center; /* Ensures the button text is centered */
    }

    .button-row .btn1 {
        background-color: #34495e;
        color: white;
        border: none;
        padding: 15px; /* Adjusted padding for consistent sizing */
        font-size: 16px;
        cursor: pointer;
        text-decoration: none;
        border-radius: 5px;
        width: 280px; /* Set width to full to fit within container */
        transition: background-color 0.3s ease;
        text-align: center; /* Ensures the button text is centered */
    }

    .button-row .btn1:hover {
        background-color: #2c3e50; /* Darker shade on hover */
    }

    .divider {
        border: 2px;
        border-top: 2px solid #000000;
        width: 100%;
        margin-bottom: 15px;
    }

    .trasaction-table-container {
        max-height: 20px; /* Adjust the height as needed */
        overflow-y: auto; 
        margin-top: 100%; /* Margin for spacing */
        margin-left: -100%;
        padding: 20px;
        border: #34495e 2px solid;
        border-radius: 8px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        display: block;
        
    }

    .scrollable {
        width: 680px;
        overflow-y: auto;
        max-height: 270px;
        max-width: 620px;
        margin-left: -630px;
        margin-top: 230px;

    }

    .transaction-table {
        /* margin-left: -10%; */
        margin-top: 0px;
        width: 500px;

        max-height: 100px;
        border-collapse: collapse;

        /* overflow-y: auto; */
        
    }
    .lasttrans{
        text-align: center;
        margin-bottom: 2px;
        color: #34495e;
        font-weight: bolder;
    }

    tr, th, td{
        border: 2px solid #34495e;
        padding: 10px;
        text-align: center;
        /* overflow-y:auto ; */
    }

    /* th, td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
        overflow-y: auto;
    }

    th {
        background-color: #2c3e50;
        color: white;
        font-weight: bold;
        overflow-y: auto;
    } */

    .loadMoreBtn{
        text-align:center; 
        width: 300px;
        margin-top: 15px; 
        margin-left: -633px;
        /* display: inline-block; */
    }

    .loadMoreBtn .loadBtn{ 
        display: inline-block;
        background-color: #34495e;
        color: white;
        border: none;
        padding: 10px; 
        font-size: 16px;
        cursor: pointer;
        text-decoration: none;
        border-radius: 5px;
        width: 600px;
        transition: background-color 0.3s ease;
        text-align: center; 
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

    /* Scrollbar styling for user-info */
    .user-info::-webkit-scrollbar {
        width: 8px;
    }

    .user-info::-webkit-scrollbar-thumb {
        background-color: #34495e;
        border-radius: 4px;
    }

    .user-info::-webkit-scrollbar-track {
        background-color: #ecf0f1;
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

        .services {
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
    select {
    width: 300px;
    padding: 3px;
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

<!-- Header -->
<header>
    <div class="header-left">
        <h1>INB STATE BANK</h1>
    </div>
    <div class="header-center">
        <a href="logoutcontroller" class="header-button">Logout</a>
        <a href="createnewaccount" class="header-button">Create New Account</a>
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

<!-- User Information Section -->
<div class="user-info">
    <!-- User Details on the Left -->
    <div class="user-details">
        <div class="user-details-top">
            <img src="data:image/jpeg;base64,${customerphoto}" alt="User Photo">
            <div class="user-info-text">
                <!-- // Retrieve user details from session -->
                <p><strong>Name:</strong> ${firstname} ${lastname}</p>
                <p><strong>Customer ID:</strong> ${customerid}</p>
                <p><strong>Email:</strong> ${customeremail}</p>
            </div>
        </div>
        <!-- Details below the photo -->
        <div class="details-below-photo">
            <!-- <p><strong>Account Number:</strong> 123456789012</p> -->
            <p><strong>IFSC Code:</strong> INB00011</p>
            <p><strong>Branch:</strong> Nariman Point, South Mumbai</p>
            <p><strong>Address:</strong> ${customeraddress}</p>
            <p><strong>Date of Birth:</strong> ${customerdob}</p>
            <p><strong>Account Creation Date:</strong> ${customeraccountcreationdate}</p>
            <p><strong>Mobile:</strong> ${customermobile}</p>
            <!-- <p><strong>Balance:</strong> ${balance}</p> -->
            <form action="getbalance" method="post">
                <select name="accountNumber1" required>
                    <option value="">Select Account</option>
                    <c:forEach var="account" items="${accountlist}">
                    console.log(accountlist);
                        <option value="${account.accountNumber}">
                            ${account.accountType} - ${account.accountNumber}
                        </option>
                    </c:forEach>
                </select>
                <input type="submit" class="btn1" value="Get Balance">
                <p><strong>Balance:</strong>${accountbalance1}</p>
                <p><strong>OverDraft Balance:</strong>${overdraftbalance}</p>
            </form>
            <!-- <p>  -->
        </div>
    </div>


    <!-- Services on the Right -->
    <div class="services">
        <h1>Services At Your Fingertips</h1>
        <div class="divider" ></div>
        <div>
            <div class="button-row">
                <a class="btn1" href="customerdepositmoneycontroller">Deposit Money</a>
                <a class="btn1" href="customertransfermoneycontroller">Transfer Money</a>
            </div>
            <div class="button-row">
                <a class="btn1" href="customerwithdrawmoneycontroller">Withdraw Money</a>
                <a class="btn1"  href="customerchequecontroller">Cheque</a>
            </div>
        </div>
    </div>
    <div class="transaction-table-container">
        <div class="scrollable">
            <form action="getLast5" method="post">
                <select name="last5Transaction" required>
                    <option value="">Select Account</option>
                    <c:forEach var="account" items="${accountlist}">
                        <option value="${account.accountNumber}">
                            ${account.accountType} - ${account.accountNumber}
                        </option>
                    </c:forEach>
                </select>
                <input type="submit" class="btn1" value="Get Transactions">
            </form>
            <table class="transaction-table" id="transactionTable">
                <tr>
                    <th>Transaction ID</th>
                    <th>Transaction Date</th>
                    <th>Transaction Type</th>
                    <th>Amount</th>
                    <th>From Account</th>
                    <th>To Account</th>
                    <!-- <th>Status</th> -->
                </tr>
                <c:forEach var="transaction" items="${dtolist}" varStatus="status">
                    <tr class="transaction-row" <c:if test="${status.count > 5}">style="display: none;"</c:if>>
                        <td>${transaction.transactionId}</td>
                        <td>${transaction.transactionDate}</td>
                        <td>${transaction.transactionType}</td>
                        <td>${transaction.amount}</td>
                        <td>${transaction.fromAccountNumber}</td>
                        <td>
                            <c:if test="${transaction.toAccountNumber != -1}">
                                ${transaction.toAccountNumber}
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <!-- Load More Button -->
        <div class="loadMoreBtn">
            <a id="loadMoreBtn" class="loadBtn" onclick="loadMore()">Show All</a>
        </div>
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
    // JavaScript to handle loading more transactions
    let transactionsToShow = 5; // Initial count
    const increment = 100; // Number of rows to show on each load more
    const rows = document.querySelectorAll('.transaction-row'); // Get all transaction rows

    function loadMore() {
        let visibleCount = 0;

        // Count currently visible rows
        rows.forEach(row => {
            if (row.style.display !== 'none') {
                visibleCount++;
            }
        });

        // Show more rows
        for (let i = visibleCount; i < visibleCount + increment; i++) {
            if (rows[i]) {
                rows[i].style.display = '';
            }
        }

        // Hide the load more button if all rows are shown
        if (visibleCount + increment >= rows.length) {
            document.getElementById('loadMoreBtn').style.display = 'none';
        }
    }
</script>
</html>
