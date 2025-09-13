<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventory Management System</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            min-height: 100vh;
            display: flex;
        }
        .sidebar {
            width: 250px;
            background-color: #212529; /* dark theme */
            color: white;
            flex-shrink: 0;
        }
        .sidebar a {
            color: white;
            text-decoration: none;
            padding: 12px 20px;
            display: block;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
        .content {
            flex-grow: 1;
            padding: 20px;
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <h3 class="text-center py-3 border-bottom">IMS</h3>
        <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/products">Products</a>
        <a href="${pageContext.request.contextPath}/categories">Categories</a>
        <a href="${pageContext.request.contextPath}/orders/orderView">Orders</a>
        <a href="${pageContext.request.contextPath}/suppliers-ui">Suppliers</a>
        <a href="${pageContext.request.contextPath}/Customer/getCustomers">Customers</a>
        <a href="${pageContext.request.contextPath}/profile">Profile</a>
        <a href="${pageContext.request.contextPath}/reports">Reports</a>
        <hr class="text-white">
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>

    <!-- Main Content -->
    <div class="content">
        <h2>Welcome to Inventory Management System</h2>
        <p>Select an option from the sidebar to continue.</p>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
