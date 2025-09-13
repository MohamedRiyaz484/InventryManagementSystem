<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.project.IMS.entity.Customer" %>
<html>
<head>
    <title>Update Customer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 500px;
            margin: 50px auto;
            background: #fff;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0px 2px 8px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
        }
        td {
            padding: 10px;
        }
        input[type="text"] {
            width: 95%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            font-size: 14px;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .back-btn {
            display: block;
            margin-top: 15px;
            text-align: center;
        }
        .back-btn a {
            text-decoration: none;
            color: #2196F3;
            font-size: 14px;
        }
        .back-btn a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Update Customer</h2>

    <%
        String id = request.getParameter("cusId");
    %>

    <form action="/Customer/update/<%=id%>" method="post">
        <input name="_method" type="hidden" value="put" />
        <table>
            <tr>
                <td>Customer Name:</td>
                <td>
                    <input value="<%= request.getParameter("cusName") %>" 
                           type="text" name="name" required>
                </td>
            </tr>
            <tr>
                <td>Customer Contact Info:</td>
                <td>
                    <input value="<%= request.getParameter("cusContact") %>" 
                           type="text" name="contactInfo" required>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form>

    <div class="back-btn">
        <a href="/Customer/getCustomers">← Back to Customer List</a>
    </div>
</div>
</body>
</html>
