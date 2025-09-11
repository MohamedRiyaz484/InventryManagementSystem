<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 400px; margin: 40px auto; }
        input[type=text], input[type=email], input[type=password], input[type=tel] {
            width: 100%; padding: 8px; margin: 6px 0; box-sizing: border-box;
        }
        .error { color: red; font-size: 0.9em; }
        button { padding: 10px 20px; background-color: #4CAF50; border: none; color: white; cursor: pointer; }
        button:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <h2>Register</h2>

    <form method="post" action="/doregister" id="registerForm" onsubmit="return validateAndSubmit()">
        <!-- ✅ Name: only letters and spaces -->
        <label for="username">Username:</label>
        <input 
            type="text" 
            id="username" 
            name="name" 
            required 
            pattern="^[A-Za-z ]+$" 
            title="Name must contain only letters and spaces">

        <!-- ✅ Email: valid format -->
        <label for="email">Email:</label>
        <input 
            type="email" 
            id="email" 
            name="email" 
            required 
            title="Enter a valid email address">

        <!-- ✅ Phone: exactly 10 digits -->
        <label for="phone">Phone Number:</label>
        <input 
            type="tel" 
            id="phone" 
            name="phoneNumber" 
            required 
            maxlength="10" 
            pattern="\d{10}" 
            title="Phone number must be exactly 10 digits">

        <!-- ✅ Password: min 8 chars, 1 letter, 1 number, 1 special -->
        <label for="password">Password:</label>
        <input 
            type="password" 
            id="password" 
            name="pwd" 
            required 
            pattern="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$" 
            title="Password must be at least 8 characters and include letters, numbers, and special characters">

        <!-- ✅ Confirm Password -->
        <label for="repassword">Re-enter Password:</label>
        <input type="password" id="repassword" required>

        <!-- Error display -->
        <div id="error-msg" class="error">${error}</div>
        <br>

        <button type="submit">Register</button>
    </form>

    <br>
    <a href="/userlogin">Already have an account? Login here</a>

    <!-- ✅ JS Validation -->
    <script>
        function validateAndSubmit() {
            const form = document.getElementById("registerForm");

            const name = document.getElementById("username").value.trim();
            const email = document.getElementById("email").value.trim();
            const phone = document.getElementById("phone").value.trim();
            const pwd = document.getElementById("password").value;
            const repwd = document.getElementById("repassword").value;
            const errorMsg = document.getElementById("error-msg");

            // Name: only letters and spaces
            const nameRegex = /^[A-Za-z ]+$/;
            if (!nameRegex.test(name)) {
                errorMsg.innerText = "Name must contain only letters and spaces.";
                return false;
            }

            // Email format
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(email)) {
                errorMsg.innerText = "Please enter a valid email address.";
                return false;
            }

            // Phone number: exactly 10 digits
            const phoneRegex = /^\d{10}$/;
            if (!phoneRegex.test(phone)) {
                errorMsg.innerText = "Phone number must be exactly 10 digits.";
                return false;
            }

            // Password: min 8 chars, 1 letter, 1 number, 1 special char
            const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$/;
            if (!passwordRegex.test(pwd)) {
                errorMsg.innerText = "Password must be at least 8 characters and include letters, numbers, and special characters.";
                return false;
            }

            // Passwords match
            if (pwd !== repwd) {
                errorMsg.innerText = "Passwords do not match!";
                return false;
            }

            errorMsg.innerText = ""; // Clear previous errors
            return true; // Allow form to submit
        }
    </script>
</body>
</html>
