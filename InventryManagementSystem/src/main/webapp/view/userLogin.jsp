<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 400px;
            margin: 60px auto;
            padding: 30px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        input[type=text], input[type=password] {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .error {
            color: red;
            font-size: 0.9em;
            margin-top: 5px;
        }
        .expired-message {
            color: red;
            margin-bottom: 10px;
            text-align: center;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            border: none;
            color: white;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #0056b3;
        }
        .register-link {
            display: block;
            margin-top: 15px;
            text-align: center;
            font-size: 0.95em;
        }
    </style>
</head>
<body>

    <div class="container">

        <% if (request.getParameter("expired") != null) { %>
            <div class="expired-message">Your session has expired. Please log in again.</div>
        <% } %>

        <h2 style="text-align: center;">Login</h2>

        <form method="post" action="/dologin" id="loginForm" onsubmit="return validateLogin()">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="pwd" required>

            <div id="error-msg" class="error">${error}</div>

            <br>
            <button type="submit">Login</button>
        </form>

        <a class="register-link" href="/userregister">Don't have an account? Register here</a>
    </div>

    <script>
        function validateLogin() {
            const email = document.getElementById("email").value.trim();
            const pwd = document.getElementById("password").value.trim();
            const errorMsg = document.getElementById("error-msg");

            if (email === "") {
                errorMsg.innerText = "Email is required.";
                return false;
            }

            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailPattern.test(email)) {
                errorMsg.innerText = "Please enter a valid email address.";
                return false;
            }

            if (pwd === "") {
                errorMsg.innerText = "Password is required.";
                return false;
            }

            errorMsg.innerText = ""; // Clear error if validation passes
            return true;
        }
    </script>

</body>
</html>
