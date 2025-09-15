
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Inventory System - Register</title>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap');

	* {
	    box-sizing: border-box;
	    margin: 0;
	    padding: 0;
	}

	body {
	    font-family: 'Inter', sans-serif;
	    background: url('/images/register.jpg') no-repeat center center fixed;
	    background-size: cover;
	    display: flex;
	    justify-content: flex-end;
	    align-items: center;
	    min-height: 100vh;
	    padding-right: 5vw;
	}

	.register-card {
	    background: linear-gradient(135deg, #ffffffdd, #f0f4ffdd);
	    border-radius: 16px;
	    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12), 0 8px 32px rgba(0, 0, 0, 0.14);
	    padding: 20px 40px;           /* Reduce padding slightly */
	    width: 500px;                 /* ✅ Increased width */
	    max-width: 95vw;
	    backdrop-filter: saturate(180%) blur(20px);
	    border: 1px solid #dde3f0;
	}

	.register-card:hover {
	    box-shadow: 0 6px 24px rgba(0, 0, 0, 0.16), 0 10px 44px rgba(0, 0, 0, 0.18);
	}

	h1 {
	    font-weight: 700;
	    font-size: 1.8rem;
	    margin-bottom: 8px;
	    text-align: center;
	    color: #222e50;
	    letter-spacing: 1px;
	}

	h3 {
	    font-weight: 600;
	    text-align: center;
	    margin-bottom: 16px;
	    color: #3f4a71;
	    font-size: 1.1rem;
	    letter-spacing: 0.03em;
	}

	form label {
	    display: block;
	    font-weight: 600;
	    margin-bottom: 4px;
	    color: #4a5678;
	    font-size: 0.95rem;
	    letter-spacing: 0.02em;
	}

	/* ↓↓↓ Input style with reduced vertical spacing ↓↓↓ */
	input[type="text"],
	input[type="email"],
	input[type="tel"],
	input[type="password"] {
	    width: 100%;
	    padding: 9px 14px;
	    border-radius: 8px;
	    border: 1.5px solid #cbd3e3;
	    background-color: #fafbff;
	    font-size: 1rem;
	    transition: border-color 0.3s ease, box-shadow 0.3s ease;
	    margin-bottom: 10px;
	    color: #2c3a63;
	    font-weight: 500;
	}

	input::placeholder {
	    color: #a3adc1;
	    font-weight: 400;
	}

	input:focus {
	    outline: none;
	    border-color: #5a8dee;
	    box-shadow: 0 0 6px rgba(90, 141, 238, 0.4);
	    background-color: #fff;
	}

	.error {
	    font-size: 0.9rem;
	    color: #e03e3e;
	    text-align: center;
	    margin-top: -4px;
	    margin-bottom: 8px;
	    font-weight: 600;
	    letter-spacing: 0.02em;
	    min-height: 18px;
	}

	button {
	    width: 100%;
	    background-color: #3c64f6;
	    color: #fff;
	    border: none;
	    padding: 11px 0;
	    font-weight: 700;
	    font-size: 1.05rem;
	    border-radius: 10px;
	    cursor: pointer;
	    box-shadow: 0 4px 14px rgba(60, 100, 246, 0.5);
	    transition: background-color 0.3s ease, box-shadow 0.3s ease;
	    letter-spacing: 0.05em;
	    margin-top: 4px;
	}

	button:hover {
	    background-color: #2a4cca;
	    box-shadow: 0 7px 20px rgba(42, 76, 202, 0.7);
	}

	.login-link {
	    display: block;
	    margin-top: 14px;
	    text-align: center;
	    font-size: 0.92rem;
	    color: #65768a;
	    font-weight: 600;
	    text-decoration: none;
	    transition: color 0.25s ease;
	    letter-spacing: 0.04em;
	}

	.login-link:hover {
	    color: #3c64f6;
	    text-decoration: underline;
	}

</style>

</head>
<body>
<div class="register-card" role="main" aria-label="Registration form">
    <h1>INVENTORY SYSTEM</h1>
    <h3>Sign Up for Your New Account</h3>

	<form id="registerForm" method="post" action="/send-otp" onsubmit="return validateAndSubmit();" novalidate>

        <label for="username">Username:</label>
        <input type="text" id="username" name="name" placeholder="Enter full name" required pattern="^[A-Za-z ]+$" autocomplete="name" />

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" placeholder="Enter email" required autocomplete="email" />

        <label for="phone">Phone Number:</label>
        <input type="tel" id="phone" name="phoneNumber" placeholder="10-digit number" maxlength="10" required pattern="\d{10}" autocomplete="tel" />

        <label for="password">Password:</label>
        <input type="password" id="password" name="pwd" placeholder="Minimum 8 characters with letters, numbers & symbols" required pattern="^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&]).{8,}$" autocomplete="new-password" />

        <label for="repassword">Re-enter Password:</label>
        <input type="password" id="repassword" placeholder="Re-enter password" required autocomplete="new-password" />

        <div id="error-msg" class="error" aria-live="polite"></div>

        <button type="submit" aria-label="Send One Time Password">Send OTP</button>
    </form>

    <a href="/userlogin" class="login-link">Already have an account? Login here</a>
</div>

<script>
    function validateAndSubmit() {
        const errorMsg = document.getElementById('error-msg');
        errorMsg.textContent = '';

        const name = document.getElementById('username').value.trim();
        const email = document.getElementById('email').value.trim();
        const phone = document.getElementById('phone').value.trim();
        const pwd = document.getElementById('password').value;
        const repwd = document.getElementById('repassword').value;

        if (!/^[A-Za-z ]+$/.test(name)) {
            errorMsg.textContent = 'Name must contain only letters and spaces.';
            return false;
        }
        if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
            errorMsg.textContent = 'Please enter a valid email address.';
            return false;
        }
        if (!/^\d{10}$/.test(phone)) {
            errorMsg.textContent = 'Phone number must be exactly 10 digits.';
            return false;
        }
        if (!/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&]).{8,}$/.test(pwd)) {
            errorMsg.textContent = 'Password must include letters, numbers, and special characters.';
            return false;
        }
        if (pwd !== repwd) {
            errorMsg.textContent = 'Passwords do not match!';
            return false;
        }

        return true; // Allow form submission
    }
</script>
</body>
</html>
