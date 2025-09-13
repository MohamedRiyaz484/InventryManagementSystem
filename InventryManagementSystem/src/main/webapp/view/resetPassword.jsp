<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f5f5f5; }
        .container { max-width: 400px; margin: 60px auto; padding: 30px; background-color: white;
                     box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); border-radius: 8px; }
        input[type=password] { width: 100%; padding: 10px; margin: 8px 0;
                               border: 1px solid #ccc; border-radius: 4px; }
        button { width: 100%; padding: 10px; background-color: #17a2b8; border: none;
                 color: white; border-radius: 4px; cursor: pointer; font-size: 16px; }
        button:hover { background-color: #138496; }
        .error { color: red; font-size: 0.9em; text-align: center; }
    </style>
</head>
<body>
<div class="container">
    <h2 style="text-align: center;">Reset Password</h2>
    <form method="post" action="/reset-password">
        <input type="hidden" name="email" value="${email}">
        <label>New Password:</label>
        <input type="password" name="pwd" required placeholder="Enter new password">

        <label>Re-enter Password:</label>
        <input type="password" name="repwd" required placeholder="Re-enter password">

        <button type="submit">Change Password</button>
    </form>
    <div class="error">${error}</div>
</div>

<script>
    const form = document.querySelector("form");
    const pwdInput = form.querySelector("input[name='pwd']");
    const repwdInput = form.querySelector("input[name='repwd']");
    const errorDiv = document.querySelector(".error");

    form.addEventListener("submit", function(event) {
        const pwd = pwdInput.value.trim();
        const repwd = repwdInput.value.trim();

        // Regex: at least 8 chars, 1 letter, 1 number, 1 special char
        const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

        if (!regex.test(pwd)) {
            event.preventDefault(); // Stop form submission
            errorDiv.textContent = "Password must be at least 8 characters and include a letter, number, and special character!";
            return false;
        }

        if (pwd !== repwd) {
            event.preventDefault(); // Stop form submission
            errorDiv.textContent = "Passwords do not match!";
            return false;
        }

        // Clear error if all validations pass
        errorDiv.textContent = "";
    });
</script>
</body>
</html>
