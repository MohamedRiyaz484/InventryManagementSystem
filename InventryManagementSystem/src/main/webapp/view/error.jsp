<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    // Check login status (adjust the session key if needed)
    boolean isLoggedIn = session.getAttribute("user") != null;
    String backLink = isLoggedIn ? "/home" : "/userlogin";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap');

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Inter', sans-serif;
            background: url('/images/error.jpg') no-repeat center center fixed;
            background-size: cover;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #fff;
        }

        .error-container {
            background: rgba(0, 0, 0, 0.6);
            padding: 40px 50px;
            border-radius: 14px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.5);
            text-align: center;
            max-width: 600px;
            width: 90%;
        }

        h2 {
            color: #ff4d4d;
            font-size: 2rem;
            margin-bottom: 16px;
        }

        p {
            font-size: 1.1rem;
            margin-bottom: 24px;
        }

        a {
            display: inline-block;
            text-decoration: none;
            background-color: #3c64f6;
            color: #fff;
            padding: 12px 24px;
            font-size: 1rem;
            font-weight: 600;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: #2a4cca;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h2>Error Occurred</h2>
        <p>${message}</p>
        <a href="<%= backLink %>">Go Back</a>
    </div>
</body>
</html>
