<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-image: url('images/forgot.jpg'); /* Use your image path */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            position: relative;
            color: #ffffff;
        }

        body::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: rgba(0, 0, 0, 0.6); /* Overlay for contrast */
            z-index: 1;
        }

        .container {
            position: relative;
            z-index: 2;
            width: 100%;
            max-width: 400px;
            padding: 40px;
            background-color: rgba(0, 0, 0, 0.35);
            border-radius: 15px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.3);
            backdrop-filter: blur(15px);
            -webkit-backdrop-filter: blur(15px);
            border: 1px solid rgba(255, 255, 255, 0.2);
            text-align: center;
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #ffffff;
            font-size: 2.2em;
            font-weight: 700;
        }

        label {
            display: block;
            margin: 15px 0 5px;
            font-size: 0.95em;
            color: #e0e0e0;
            text-align: left;
            font-weight: bold;
        }

        input[type="password"] {
            width: 100%;
            padding: 12px;
            margin-top: 6px;
            border: 1px solid rgba(255, 255, 255, 0.4);
            border-radius: 8px;
            background-color: rgba(255, 255, 255, 0.1);
            color: #ffffff;
            font-size: 1em;
            transition: border-color 0.3s, box-shadow 0.3s;
        }

        input::placeholder {
            color: rgba(255, 255, 255, 0.6);
        }

        input:focus {
            outline: none;
            border-color: #9affff;
            box-shadow: 0 0 10px rgba(153, 255, 255, 0.6);
        }

        button {
            width: 100%;
            padding: 14px;
            background-color: #7f58af;
            border: none;
            color: white;
            border-radius: 8px;
            cursor: pointer;
            font-size: 1.1em;
            font-weight: bold;
            margin-top: 25px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s, transform 0.2s, box-shadow 0.3s;
        }

        button:hover {
            background-color: #6a4990;
            transform: translateY(-3px);
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.3);
        }

        .error {
            color: #ffcc00;
            font-size: 0.9em;
            text-align: center;
            margin-top: 15px;
            min-height: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Reset Password</h2>
    <form method="post" action="/reset-password">
        <input type="hidden" name="email" value="${email}">

        <label for="pwd">New Password:</label>
        <input type="password" id="pwd" name="pwd" required placeholder="Enter new password">

        <label for="repwd">Re-enter Password:</label>
        <input type="password" id="repwd" name="repwd" required placeholder="Re-enter password">

        <button type="submit">Change Password</button>
    </form>
    <div class="error" id="error-msg">${error}</div>
</div>

<script>
    const form = document.querySelector("form");
    const pwdInput = document.getElementById("pwd");
    const repwdInput = document.getElementById("repwd");
    const errorDiv = document.getElementById("error-msg");

    form.addEventListener("submit", function(event) {
        const pwd = pwdInput.value.trim();
        const repwd = repwdInput.value.trim();

        // Regex: at least 8 chars, 1 letter, 1 number, 1 special char
        const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

        if (!regex.test(pwd)) {
            event.preventDefault();
            errorDiv.textContent = "Password must be at least 8 characters and include a letter, number, and special character!";
            return false;
        }

        if (pwd !== repwd) {
            event.preventDefault();
            errorDiv.textContent = "Passwords do not match!";
            return false;
        }

        errorDiv.textContent = "";
    });
</script>
</body>
</html>
