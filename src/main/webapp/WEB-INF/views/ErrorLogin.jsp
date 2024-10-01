<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Error</title>
<link rel="icon" type="image/x-icon" href="https://t4.ftcdn.net/jpg/00/61/06/27/360_F_61062796_NF87GPnWV0fQ2LhoYNlyjev0PocRwZj9.jpg">
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        color: #333;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        text-align: center;
    }

    .error-container {
        background-color: #fff;
        padding: 20px;
        border: 2px solid #e74c3c;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        max-width: 400px;
        width: 100%;
    }

    .error-container img {
        max-width: 80px;
        margin-bottom: 15px;
    }

    h2 {
        color: #e74c3c;
        font-size: 36px;
        margin-bottom: 10px;
    }

    h3 {
        color: #e74c3c;
        font-size: 24px;
        margin: 10px 0;
    }

    a {
        display: inline-block;
        padding: 12px 25px;
        margin: 20px 10px;
        text-decoration: none;
        font-size: 18px;
        color: white;
        background-color: #3498db;
        border-radius: 8px;
        transition: all 0.3s ease;
    }

    a:hover {
        background-color: #2980b9;
    }
</style>
</head>
<body>
    <div class="error-container">
        <img src="https://img.icons8.com/ios/50/000000/error.png" alt="Error Icon"/>
        <h2>Error</h2>
        <hr>
        <h3>Invalid Username or Password</h3>
        <a href="/app/prelogin">Back to Login</a>
    </div>
</body>
</html>



