<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Digital Cheque</title>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    body {
        font-family: 'Arial', sans-serif;
        background-color: #f4f4f4;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .cheque {
        width: 800px;
        height: 350px;
        background: linear-gradient(135deg, #f9fafc, #e3e6eb);
        border: 2px solid #333;
        border-radius: 12px;
        padding: 20px;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        position: relative;
        font-size: 16px;
        color: #333;
    }

    .cheque-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 20px;
    }

    .bank-info {
        display: flex;
        align-items: center;
    }

    .bank-logo {
        width: 100px;
        height: 50px;
        margin-right: 15px;
    }

    .bank-details {
        display: flex;
        flex-direction: column;
    }

    .bank-name {
        font-size: 1.5em;
        font-weight: bold;
        color: #0056b3;
    }

    .bank-address {
        font-size: 0.9em;
        color: #666;
    }

    .cheque-id {
        font-size: 1em;
        font-weight: bold;
        color: #333;
    }

    .cheque-body {
        margin-bottom: 20px;
    }

    .payee, .amount, .amount-numbers {
        margin-bottom: 15px;
    }

    .payee span, .amount span, .amount-numbers span {
        font-weight: bold;
        margin-right: 10px;
    }

    input[type="text"], input[type="number"], input[type="date"] {
        border: none;
        border-bottom: 2px solid #333;
        background-color: transparent;
        width: auto;
        font-size: 1.2em;
        padding-left: 10px;
        padding-right: 10px;
        outline: none;
        letter-spacing: 2px;
    }

    .cheque-footer {
        display: flex;
        justify-content: space-between;
        margin-top: 20px;
    }

    .date, .signature {
        width: 45%;
    }

    .cheque-footer-bottom {
        margin-top: 10px;
        display: flex;
        justify-content: space-between;
        font-size: 0.9em;
    }

    .account-number, .ifsc-code {
        font-weight: bold;
        color: #333;
    }
</style>
</head>
<body>
    <div class="cheque">
        <form action="" method="post">
            <div class="cheque-header">
                <div class="bank-info">
                    <img src="https://via.placeholder.com/100x50?text=BANK+LOGO" alt="Bank Logo" class="bank-logo">
                    <div class="bank-details">
                        <div class="bank-name">INB STATE BANK</div>
                        <div class="bank-address">South Bombay, Colaba, Mumbai, India 400002</div>
                    </div>
                </div>
                <div class="cheque-id">Cheque ID: <input type="text" name="chequeId" value="987654321" readonly></div>
            </div>
            <div class="cheque-body">
                <div class="payee">
                    <span>Pay to the Order of:</span> 
                    <input type="text" name="payeeName" placeholder="Enter Payee Name">
                </div>
                <div class="amount">
                    <span>Amount in Words:</span>
                    <input type="text" name="amountInWords" placeholder="Enter Amount in Words">
                </div>
                <div class="amount-numbers">
                    <span>Amount in Digits:</span>
                    <input type="number" name="amountInDigits" placeholder="₹ Enter Amount">
                </div>
            </div>
            <div class="cheque-footer">
                <div class="date">
                    <span>Date:</span>
                    <input type="date" name="chequeDate">
                </div>
                <div class="signature">
                    <span>Authorized Signature:</span>
                    <input type="text" name="signature" placeholder="Sign Here">
                </div>
            </div>
            <div class="cheque-footer-bottom">
                <div class="account-number">
                    <span>Account Number:</span> <input type="text" name="accountNumber" value="98765432101234" readonly>
                </div>
                <div class="ifsc-code">
                    <span>IFSC Code:</span> <input type="text" name="ifscCode" value="INB00011" readonly>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
