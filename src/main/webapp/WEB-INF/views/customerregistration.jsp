<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Registration Form</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f4f8;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
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
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .form-box {
            width: 600px;
            padding: 20px;
            background-color: #fff;
            border: 2px solid #3498db;
            border-radius: 10px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        .form-box h2 {
            margin: 0;
            font-size: 24px;
            color: #2c3e50;
            margin-bottom: 20px;
        }

        .form-box .row {
            margin-bottom: 20px;
            text-align: left;
        }

        .form-box label {
            display: block;
            margin-bottom: 5px;
            font-size: 16px;
            color: #2c3e50;
        }

        .form-box input[type="text"],
        .form-box input[type="email"],
        .form-box input[type="date"],
        .form-box input[type="password"],
        .form-box input[type="file"] {
            width: calc(100% - 20px);
            padding: 10px;
            border: 1px solid #3498db;
            border-radius: 5px;
            font-size: 16px;
            margin-bottom: 10px;
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
            box-shadow: 0 5px 15px rgba(52, 152, 219, 0.4);
        }

        .form-box input[type="submit"]:hover {
            background-image: linear-gradient(45deg, #2980b9, #3498db);
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(52, 152, 219, 0.6);
        }

        .form-box .clear-btn {
            background-color: #e74c3c;
            border: none;
            color: white;
            padding: 8px 16px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            margin-left: 10px;
        }

        .form-box .clear-btn:hover {
            background-color: #c0392b;
        }

        .form-box hr {
            border: 0;
            border-top: 2px solid #3498db;
            margin: 20px 0;
        }

        .form-box select {
            appearance: none;
            background-color: #fff;
            background-image: url('data:image/svg+xml;charset=UTF-8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><polygon points="0,0 10,10 20,0" style="fill:%233498db"/></svg>');
            background-repeat: no-repeat;
            background-position: right 10px center;
            background-size: 12px;
            padding-right: 30px;
            transition: all 0.3s ease;
        }

        .form-box select:hover,
        .form-box select:focus {
            border-color: #2980b9;
            outline: none;
            box-shadow: 0 0 5px rgba(41, 128, 185, 0.5);
        }

        .form-box option {
            background-color: white;
            color: #333;
        }

        footer {
            background-color: #2c3e50;
            color: white;
            padding: 10px 0;
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
            gap: 20px;
        }

        .file-input-wrapper {
            display: flex;
            align-items: center;
            position: relative;
        }

        .file-input-wrapper input[type="file"] {
            flex: 1;
        }

        .file-input-wrapper .clear-icon {
            position: absolute;
            right: 10px;
            cursor: pointer;
            font-size: 18px;
            color: #e74c3c;
            background: white;
            border-radius: 50%;
            padding: 0 5px;
            user-select: none;
        }

        .file-input-wrapper .clear-icon:hover {
            color: #c0392b;
        }

        .phone-container {
            display: flex;
            align-items: center;
        }

        .phone-container select {
            padding: 10px;
            border: 1px solid #3498db;
            border-radius: 5px;
            font-size: 16px;
            margin-right: 10px;
            margin-bottom: 8px;
            width: 100px;
            /* Adjust width as needed */
        }

        .phone-container input[type="text"] {
            flex: 1;
        }
        .account-section {
            margin-top: 30px;
            background-color: #ecf0f1;
            border-radius: 10px;
            padding: 15px;
            border: 1px solid #3498db;
        }

        .account-section h3 {
            margin-top: 0;
            font-size: 20px;
            color: #2980b9;
        }
        #accountType {
            width: 98%; /* Increase the width as needed */
            padding: 10px; /* Add some padding for a more spacious look */
            font-size: 16px; /* Adjust font size if needed */
            border-radius: 5px;
        }
        #fixedDepositAccountForm input[type="number"],
        #fixedDepositAccountForm input[type="date"],
        #accountType {
            width: 98%; /* Set the width to 98% to match the styling */
            padding: 10px; /* Add padding for a more spacious look */
            font-size: 16px; /* Adjust font size for readability */
            border-radius: 5px; /* Add rounded corners for consistency */
            margin-bottom: 10px; /* Add some space between form elements */
            border: 1px solid #ccc; /* Add a light border for a clean look */
            box-sizing: border-box; /* Ensure padding and width are calculated correctly */
        }
        #currentAccountForm input[type="number"] {
        width: 98%; /* Set the width to 98% */
        padding: 10px; /* Add padding for a more spacious look */
        font-size: 16px; /* Adjust font size */
        border-radius: 5px; /* Add rounded corners */
        margin-bottom: 10px; /* Add space between form elements */
        border: 1px solid #ccc; /* Add a light border */
        box-sizing: border-box; /* Ensure padding and width are calculated correctly */
    }
    #fixedDepositAccountForm select,
    #fixedDepositAccountForm input[type="number"],
    #fixedDepositAccountForm input[type="date"] {
        width: 98%; /* Set the width to 98% */
        padding: 10px; /* Add padding for a more spacious look */
        font-size: 16px; /* Adjust font size */
        border-radius: 5px; /* Add rounded corners */
        margin-bottom: 10px; /* Add space between form elements */
        border: 1px solid #ccc; /* Add a light border */
        box-sizing: border-box; /* Ensure padding and width are calculated correctly */
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
            <h2>Customer Registration Form</h2>
            <form action="uploadCustomer" method="post" enctype="multipart/form-data">
                <!-- Customer Details -->
                <div class="row">
                    <label for="customerFirstName">First Name:</label>
                    <input type="text" id="customerFirstName" name="customerFirstName" required>

                    <label for="customerLastName">Last Name:</label>
                    <input type="text" id="customerLastName" name="customerLastName" required>

                    <label for="customerEmail">Email:</label>
                    <input type="email" id="customerEmail" name="customerEmail" required>

                    <label for="customerMobile">Mobile:</label>
                    <div class="phone-container">
                        <select id="countryCode" name="countryCode" required>
                            <option value="+1">+1 (USA)</option>
                            <option value="+44">+44 (UK)</option>
                            <option value="+91">+91 (India)</option>
                            <option value="+213">+213 (Algeria)</option>
                            <option value="+376">+376 (Andorra)</option>
                            <option value="+244">+244 (Angola)</option>
                            <option value="+1264">+1264 (Anguilla)</option>
                            <option value="+54">+54 (Argentina)</option>
                            <option value="+61">+61 (Australia)</option>
                            <option value="+43">+43 (Austria)</option>
                            <option value="+32">+32 (Belgium)</option>
                            <option value="+501">+501 (Belize)</option>
                            <option value="+229">+229 (Benin)</option>
                            <option value="+975">+975 (Bhutan)</option>
                            <option value="+591">+591 (Bolivia)</option>
                            <option value="+387">+387 (Bosnia and Herzegovina)</option>
                            <option value="+267">+267 (Botswana)</option>
                            <option value="+55">+55 (Brazil)</option>
                            <option value="+1242">+1242 (Bahamas)</option>
                            <option value="+973">+973 (Bahrain)</option>
                            <option value="+880">+880 (Bangladesh)</option>
                            <option value="+375">+375 (Belarus)</option>
                            <option value="+591">+591 (Bolivia)</option>
                            <option value="+59">+59 (Bolivia)</option>
                            <option value="+592">+592 (Guyana)</option>
                            <option value="+500">+500 (Falkland Islands)</option>
                            <option value="+298">+298 (Faroe Islands)</option>
                            <option value="+691">+691 (Federated States of Micronesia)</option>
                            <option value="+658">+658 (Hawaii)</option>
                            <option value="+679">+679 (Fiji)</option>
                            <option value="+62">+62 (Indonesia)</option>
                            <option value="+98">+98 (Iran)</option>
                            <option value="+964">+964 (Iraq)</option>
                            <option value="+353">+353 (Ireland)</option>
                            <option value="+972">+972 (Israel)</option>
                            <option value="+39">+39 (Italy)</option>
                            <option value="+252">+252 (Somalia)</option>
                            <option value="+211">+211 (South Sudan)</option>
                            <option value="+221">+221 (Senegal)</option>
                            <option value="+963">+963 (Syria)</option>
                            <option value="+1">+1 (USA)</option>
                            <option value="+967">+967 (Yemen)</option>
                            <option value="+260">+260 (Zambia)</option>
                            <option value="+263">+263 (Zimbabwe)</option>
                            <!-- Add more country codes as needed -->
                        </select>
                        <input type="text" id="customerMobile" name="customerMobile" required
                            placeholder="Enter phone number">
                    </div>

                    <label for="customerAddress">Address:</label>
                    <input type="text" id="customerAddress" name="customerAddress" required>

                    <label for="customerDob">Date of Birth:</label>
                    <input type="date" id="customerDob" name="customerDob" required>

                    <label for="customerPassword">Password:</label>
                    <input type="password" id="customerPassword" name="customerPassword" required>
                </div>

                <div class="account-section">
                    <h3>Account Type</h3>
                    <label for="accountType">Select Account Type:</label>
                    <select id="accountType" name="accountType" onchange="showAccountForm()" required>
                        <option value="">--Please Select Your Account Type--</option>
                        <option value="savings">Savings Account</option>
                        <option value="current">Current Account</option>
                        <option value="fixed_deposit">Fixed Deposit Account</option>
                    </select>
                </div>

                <!-- Savings Account Form -->
                <div id="savingsAccountForm" class="account-section" style="display: none;">
                    <h3>Savings Account Details</h3>
                    <label for="savingsInterestRate">Balance:</label>
                    <input type="text" id="savingsInterestRate" name="balance" min="500" placeholder="Enter balance"  oninput="checkMinBalance()">
                    <br>
                    <span id="balanceError" style="color: red; display: none;">Balance must be at least 500.</span>
                </div>

                <!-- Current Account Form -->
                <div id="currentAccountForm" class="account-section" style="display: none;">
                    <h3>Current Account Details</h3>
                
                    <label for="currentBalance">Balance:</label>
                    <input type="number" id="currentBalance" name="currentBalance" min="1000" placeholder="Enter balance" >
                </div>

                <!-- Fixed Deposit Account Form -->
                <div id="fixedDepositAccountForm" class="account-section" style="display: none;">
                    <h3>Fixed Deposit Account Details</h3>
                    
                    <label for="fdMaturityPeriod">Maturity Period (months):</label>
                    <select id="fdMaturityPeriod" name="fdMaturityPeriod" onchange="calculateEndDate(); calculateMaturityAmount();" >
                        <option value="12">12</option>
                        <option value="24">24</option>
                        <option value="36">36</option>
                    </select>
                    
                    <label for="fdBalance">Balance:</label>
                    <input type="number" id="fdBalance" name="fdBalance" min="1000" placeholder="Enter balance" oninput="calculateMaturityAmount();">
                    
                    <label for="fdStartDate">Start Date:</label>
                    <input type="date" id="fdStartDate" name="fdStartDate" onchange="calculateEndDate()" >
                    
                    <label for="fdEndDate">End Date:</label>
                    <input type="date" id="fdEndDate" name="fdEndDate" readonly>
                    
                    <label for="fdMaturityAmount">Maturity Amount:</label>
                    <input type="text" id="fdMaturityAmount" name="fdMaturityAmount" readonly placeholder="Maturity amount will be calculated">
                </div>
                <hr>

                <h2>KYC Details</h2>

                <!-- File Uploads -->
                <div class="row">
                    <div class="file-input-group">
                        <label for="file1">Upload Aadhaar (PDF):</label>
                        <div class="file-input-wrapper">
                            <input type="file" id="file1" name="customerFiles[0].fileData" accept=".pdf">
                            <span class="clear-icon"
                                onclick="document.getElementById('file1').value = '';">&#10006;</span>
                        </div>
                    </div>

                    <div class="file-input-group">
                        <label for="file2">Upload PAN Card (PDF):</label>
                        <div class="file-input-wrapper">
                            <input type="file" id="file2" name="customerFiles[1].fileData" accept=".pdf">
                            <span class="clear-icon"
                                onclick="document.getElementById('file2').value = '';">&#10006;</span>
                        </div>
                    </div>

                    <div class="file-input-group">
                        <label for="file3">Upload Passport Size Photo (JPG):</label>
                        <div class="file-input-wrapper">
                            <input type="file" id="file3" name="customerFiles[2].fileData" accept=".jpg">
                            <span class="clear-icon"
                                onclick="document.getElementById('file3').value = '';">&#10006;</span>
                        </div>
                    </div>

                    <div class="file-input-group">
                        <label for="file4">Upload Signature (JPG):</label>
                        <div class="file-input-wrapper">
                            <input type="file" id="file4" name="customerFiles[3].fileData" accept=".jpg">
                            <span class="clear-icon"
                                onclick="document.getElementById('file4').value = '';">&#10006;</span>
                        </div>
                    </div>
                </div>
                <input type="submit" id="submitbtn" value="Submit">
            </form>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <div class="footer-content">
            <p>&copy; 2024 INB STATE BANK. All rights reserved.</p>
        </div>
    </footer>
    <script>
        // Script to handle form resubmission warning
        window.addEventListener('beforeunload', function (e) {
            // Check if form has unsaved changes
            var form = document.querySelector('form');
            if (form && form.elements) {
                for (var i = 0; i < form.elements.length; i++) {
                    if (form.elements[i].value !== '') {
                        var confirmationMessage = 'You have unsaved changes. Are you sure you want to leave?';
                        e.returnValue = confirmationMessage; // Standard for most browsers
                        return confirmationMessage; // For older browsers
                    }
                }
            }
        });
    </script>
    <script>
        function showAccountForm() {
            var accountType = document.getElementById("accountType").value;
            document.getElementById("savingsAccountForm").style.display = accountType === "savings" ? "block" : "none";
            document.getElementById("currentAccountForm").style.display = accountType === "current" ? "block" : "none";
            document.getElementById("fixedDepositAccountForm").style.display = accountType === "fixed_deposit" ? "block" : "none";
        }

        function calculateEndDate() {
            var accountType = document.getElementById("accountType").value;
            if (accountType !== "fixed_deposit") {
                document.getElementById("fdEndDate").value = "";
            }
            var maturityPeriod = document.getElementById("fdMaturityPeriod").value;
            var startDate = document.getElementById("fdStartDate").value;

            if (maturityPeriod && startDate) {
                var startDateObj = new Date(startDate);
                startDateObj.setMonth(startDateObj.getMonth() + parseInt(maturityPeriod));
                var endDate = startDateObj.toISOString().split('T')[0];
                document.getElementById("fdEndDate").value = endDate;
            }
        }
        function checkMinBalance() {
        const balanceInput = document.getElementById('savingsInterestRate');
        const balanceError = document.getElementById('balanceError');
        const submitbtnn = document.getElementById('submitbtn');

        if (balanceInput.value < 500) {
            balanceError.style.display = 'inline';
            submitbtnn.disabled = true;

        } else {
            balanceError.style.display = 'none';
            submitbtnn.disabled = false;
        }
    }

    </script>
    <script>
        // Sample annual interest rate for Fixed Deposit
        var annualInterestRatee;
        var a = document.getElementById('fdMaturityPeriod').value;
        if (a == 12) {
             annualInterestRatee = 0.045;
        } else if (a == 24) {
             annualInterestRatee = 0.05;
        } else if (a == 36) {
             annualInterestRatee = 0.055;
        }
    
        function calculateEndDate() {
            const maturityPeriod = document.getElementById('fdMaturityPeriod').value;
            const startDate = document.getElementById('fdStartDate').value;
            
            if (startDate && maturityPeriod) {
                let start = new Date(startDate);
                let endDate = new Date(start.setMonth(start.getMonth() + parseInt(maturityPeriod)));
                document.getElementById('fdEndDate').value = endDate.toISOString().split('T')[0];
            }
        }
    
        function calculateMaturityAmount() {
            const balance = parseFloat(document.getElementById('fdBalance').value) || 0;
            const maturityPeriod = parseInt(document.getElementById('fdMaturityPeriod').value) || 0;
            
            if (balance >= 0 && maturityPeriod > 0) {
                // Convert months to years
                const years = maturityPeriod / 12;
                // Simple interest formula: A = P(1 + rt)
                const maturityAmount = balance * (1 + (annualInterestRatee * years));
                // Display the maturity amount
                document.getElementById('fdMaturityAmount').value = maturityAmount.toFixed(2);
            } else {
                document.getElementById('fdMaturityAmount').value = 'Invalid input';
            }
        }
    </script>
</body>

</html>