<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    String username = (String) session.getAttribute("username");
    Integer userId = (Integer) session.getAttribute("userId");
    if (username == null || userId == null) {
        response.sendRedirect("/userlogin"); // ✅ fixed
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 400px; margin: 40px auto; text-align: center; }
        button { padding: 10px 20px; background-color: #dc3545; border: none; color: white; cursor: pointer; }
        button:hover { background-color: #c82333; }
    </style>
</head>
<body>
    <h1>Welcome, <%= username %>!</h1>
    <p>Your user ID is: <%= userId %></p>

    <form action="/logout" method="post">
        <button type="submit">Logout</button>
    </form>
</body>
</html>
