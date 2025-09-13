<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Verify OTP - Forgot Password</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f5f5f5; }
        .container { max-width: 400px; margin: 60px auto; padding: 30px; background-color: white;
                     box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); border-radius: 8px; }
        input[type=text] { width: 100%; padding: 10px; margin: 8px 0;
                           border: 1px solid #ccc; border-radius: 4px; }
        button { width: 100%; padding: 10px; background-color: #28a745; border: none;
                 color: white; border-radius: 4px; cursor: pointer; font-size: 16px; }
        button:hover { background-color: #218838; }
        .error { color: red; font-size: 0.9em; text-align: center; }
    </style>
</head>
<body>
<div class="container">
    <h2 style="text-align: center;">Verify OTP</h2>
    <form method="post" action="/verify-forgot-otp">
        <input type="hidden" name="email" value="${email}">
        <label for="otp">Enter OTP sent to ${email}:</label>
        <input type="text" name="otp" required maxlength="6" placeholder="6-digit OTP">
        <button type="submit">Verify</button>
    </form>
    <div class="error">${error}</div>
</div>
</body>
</html>
