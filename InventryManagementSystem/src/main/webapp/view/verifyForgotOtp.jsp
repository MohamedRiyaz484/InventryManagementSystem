<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Verify OTP - Forgot Password</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background-image: url('images/verify.jpg');
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
            background-color: rgba(0, 0, 0, 0.55);
            z-index: 1;
        }

        .container {
            position: relative;
            z-index: 2;
            max-width: 400px;
            padding: 35px;
            background-color: rgba(255, 255, 255, 0.08);
            border-radius: 15px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.35);
            backdrop-filter: blur(14px);
            -webkit-backdrop-filter: blur(14px);
            border: 1px solid rgba(255, 255, 255, 0.2);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #ffffff;
            font-size: 2em;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 6px;
            color: #dddddd;
        }

        input[type="text"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 15px;
            border-radius: 8px;
            border: 1px solid rgba(255, 255, 255, 0.3);
            background-color: rgba(255, 255, 255, 0.1);
            color: #ffffff;
            font-size: 1em;
        }

        input::placeholder {
            color: rgba(255, 255, 255, 0.6);
        }

        input:focus {
            outline: none;
            border-color: #9affff;
            box-shadow: 0 0 10px rgba(153, 255, 255, 0.5);
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #28a745;
            border: none;
            color: #ffffff;
            font-size: 1.1em;
            font-weight: bold;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s, box-shadow 0.3s;
        }

        button:hover {
            background-color: #218838;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
        }

        .error {
            color: #ffcc00;
            font-size: 0.9em;
            text-align: center;
            margin-top: 12px;
            min-height: 18px;
        }
    </style>

    <script>
        // Redirect to /userlogin after 5 minutes
        setTimeout(function () {
            alert("Timed out! Redirecting to login page...");
            window.location.href = "/userlogin";
        }, 300000); // 5 minutes = 300,000 ms
    </script>
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
