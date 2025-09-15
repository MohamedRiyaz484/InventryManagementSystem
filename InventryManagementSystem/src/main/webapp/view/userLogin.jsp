<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet" />
	<style>
	    body {
	        font-family: 'Inter', Arial, sans-serif;
	        background: url('/images/register.jpg') no-repeat center center fixed;
	        background-size: cover;
	        margin: 0;
	        padding-right: 6vw;
	        display: flex;
	        justify-content: flex-end;
	        align-items: center;
	        min-height: 100vh;
	        color: #333;
	    }

	    .container {
	        background: linear-gradient(135deg, #ffffffdd, #f0f4ffdd);
	        border-radius: 16px;
	        box-shadow: 0 4px 16px rgba(0,0,0,0.12), 0 8px 32px rgba(0,0,0,0.14);
	        padding: 20px 40px; /* reduced padding */
	        width: 500px; /* reduced width for smaller form */
	        max-width: 95vw;
	        max-height: calc(100vh - 40px);
	        overflow-y: auto;
	        backdrop-filter: saturate(180%) blur(20px);
	        border: 1px solid #dde3f0;
	        transition: box-shadow 0.3s ease;
	        box-sizing: border-box;
	    }

	    .container:hover {
	        box-shadow: 0 6px 24px rgba(0,0,0,0.16), 0 10px 44px rgba(0,0,0,0.18);
	    }

	    h2 {
	        font-weight: 700;
	        font-size: 2rem; /* slightly smaller */
	        margin-bottom: 10px;
	        text-align: center;
	        color: #222e50;
	        letter-spacing: 1.2px;
	    }

	    label {
	        display: block;
	        font-weight: 600;
	        margin-bottom: 5px;
	        color: #4a5678;
	        font-size: 0.95rem;
	        letter-spacing: 0.03em;
	    }

	    input[type="text"], input[type="password"] {
	        width: 100%;
	        padding: 10px 14px; /* reduced padding */
	        border-radius: 8px;
	        border: 1.5px solid #cbd3e3;
	        background-color: #fafbff;
	        font-size: 1rem;
	        transition: border-color 0.3s ease, box-shadow 0.3s ease;
	        margin-bottom: 14px;
	        color: #2c3a63;
	        font-weight: 500;
	        box-sizing: border-box;
	    }

	    input[type="text"]::placeholder,
	    input[type="password"]::placeholder {
	        color: #a3adc1;
	        font-weight: 400;
	    }

	    input[type="text"]:focus,
	    input[type="password"]:focus {
	        outline: none;
	        border-color: #5a8dee;
	        box-shadow: 0 0 10px rgba(90, 141, 238, 0.4);
	        background-color: #fff;
	    }

	    .error {
	        font-size: 0.95rem;
	        color: #e03e3e;
	        text-align: center;
	        margin-top: -4px;
	        margin-bottom: 12px;
	        font-weight: 600;
	        letter-spacing: 0.03em;
	        min-height: 20px;
	    }

	    .expired-message {
	        color: #e03e3e;
	        margin-bottom: 12px;
	        text-align: center;
	        font-weight: 600;
	        letter-spacing: 0.03em;
	    }

	    button {
	        width: 100%;
	        background-color: #3c64f6;
	        color: #fff;
	        border: none;
	        padding: 12px 0; /* slightly smaller button */
	        font-weight: 700;
	        font-size: 1.1rem;
	        border-radius: 10px;
	        cursor: pointer;
	        box-shadow: 0 5px 16px rgba(60, 100, 246, 0.5);
	        transition: background-color 0.3s ease, box-shadow 0.3s ease;
	        letter-spacing: 0.05em;
	    }

	    button:hover {
	        background-color: #2a4cca;
	        box-shadow: 0 7px 22px rgba(42, 76, 202, 0.75);
	    }

	    .register-link {
	        display: block;
	        margin-top: 16px;
	        text-align: center;
	        font-size: 0.95rem;
	        color: #65768a;
	        font-weight: 600;
	        text-decoration: none;
	        transition: color 0.25s ease;
	        letter-spacing: 0.05em;
	    }

	    .register-link:hover {
	        color: #3c64f6;
	        text-decoration: underline;
	    }
	</style>

</head>
<body>

    <div class="container" role="main" aria-label="Login form">

        <% if (request.getParameter("expired") != null) { %>
            <div class="expired-message">Your session has expired. Please log in again.</div>
        <% } %>

        <h2>Login</h2>

        <form method="post" action="/dologin" id="loginForm" onsubmit="return validateLogin()" novalidate>
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" required placeholder="Enter your email" autocomplete="email">

            <label for="password">Password:</label>
            <input type="password" id="password" name="pwd" required placeholder="Enter your password" autocomplete="current-password">

            <div id="error-msg" class="error">${error}</div>

            <button type="submit" aria-label="Login">Login</button>
        </form>

        <a class="register-link" href="/userregister">Don't have an account? Register here</a>
        <a class="register-link" href="/forgot-password">Forgot Password?</a>
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

            errorMsg.innerText = "";
            return true;
        }
    </script>

</body>
</html>
