<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            
            background-image: url('images/forgot.jpg');
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
            background-color: rgba(0, 0, 0, 0.6) !important; /* Slightly darker overlay */
            z-index: 1;
        }

        .container { 
            position: relative;
            z-index: 2;
            width: 100%;
            max-width: 400px;
            padding: 40px; 
            background-color: rgba(0, 0, 0, 0.35) !important; /* More contrast for form */
            border-radius: 15px;
            box-shadow: 0 8px 32px 0 rgba(0, 0, 0, 0.3);
            backdrop-filter: blur(15px);
            -webkit-backdrop-filter: blur(15px);
            border: 1px solid rgba(255, 255, 255, 0.2) !important;
            text-align: center;
            transition: all 0.3s ease-in-out;
        }

        .container:hover {
            box-shadow: 0 12px 48px 0 rgba(0, 0, 0, 0.4);
            transform: translateY(-5px);
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
            margin-top: 15px;
            font-size: 0.9em;
            color: #e0e0e0;
            text-align: left;
            font-weight: bold;
        }
        
        input[type=text] {
            width: 100%; 
            padding: 12px; 
            margin: 8px 0;
            box-sizing: border-box;
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
        
        .error-msg {
            color: #ffcc00;
            font-size: 0.9em;
            text-align: center;
            margin-top: 10px;
            height: 20px;
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
        
        .login-link {
            display: block;
            margin-top: 30px;
            font-size: 0.95em;
        }

        .login-link a {
            color: #9affff;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s;
        }

        .login-link a:hover {
            color: #66e0ff;
            text-decoration: underline;
        }
    </style>

    <script>
        function validateEmail() {
            const emailInput = document.getElementById('email');
            const errorMsg = document.getElementById('error-msg');
            const email = emailInput.value.trim();
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (email === "" || !emailRegex.test(email)) {
                errorMsg.innerText = 'Enter valid email';
                return false;
            }

            errorMsg.innerText = '';
            return true;
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Forgot Password</h2>
    <form method="post" action="/send-forgot-otp" onsubmit="return validateEmail()">
        <label for="email">Enter your registered email:</label>
        <input type="text" id="email" name="email" required placeholder="example@email.com">
        <div class="error-msg" id="error-msg">${error}</div>
        <button type="submit">Send OTP</button>
    </form>
    <div class="login-link">
        <a href="/userlogin">Back to Login</a>
    </div>
	
</div>
</body>
</html>
