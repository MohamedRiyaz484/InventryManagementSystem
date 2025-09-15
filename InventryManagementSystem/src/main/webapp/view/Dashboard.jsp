<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.IMS.entity.*" %>

<html>
<head>
    <title>Dashboard</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f9f9f9; }
        h1 { margin-bottom: 20px; display: inline-block; }
        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .btn-back {
            background: #007bff;
            color: #fff;
            border: none;
            padding: 10px 16px;
            border-radius: 6px;
            cursor: pointer;
            text-decoration: none;
        }
        .btn-back:hover {
            background: #0056b3;
        }
        .cards { display: flex; gap: 20px; margin: 30px 0; flex-wrap: wrap; }
        .card {
            background: #fff; padding: 20px; border-radius: 12px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1); flex: 1; text-align: center; min-width: 150px;
        }
        .card h2 { margin: 10px 0; }
        .charts { display: flex; gap: 30px; flex-wrap: wrap; margin-top: 20px; }
        .chart-container {
            background: #fff; padding: 20px; border-radius: 12px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1); flex: 1; min-width: 400px;
        }
    </style>
</head>
<body>
    <!-- Top Bar -->
    <div class="top-bar">
        <h1>Welcome, ${username}!</h1>
        <a href="${pageContext.request.contextPath}/home" class="btn-back">Back to Main Menu</a>
    </div>

    <!-- Summary Cards -->
    <div class="cards">
        <div class="card">
            <h3>Products</h3>
            <h2><%= ((List<Product>)request.getAttribute("products")).size() %></h2>
        </div>
        <div class="card">
            <h3>Suppliers</h3>
            <h2><%= ((List<Supplier>)request.getAttribute("suppliers")).size() %></h2>
        </div>
        <div class="card">
            <h3>Customers</h3>
            <h2><%= ((List<Customer>)request.getAttribute("customers")).size() %></h2>
        </div>
        <div class="card">
            <h3>Orders</h3>
            <h2><%= ((List<Order>)request.getAttribute("orders")).size() %></h2>
        </div>
        <div class="card">
            <h3>Inventory Items</h3>
            <h2><%= ((List<Inventory>)request.getAttribute("inventory")).size() %></h2>
        </div>
    </div>

    <!-- Charts -->
    <div class="charts">
        <div class="chart-container">
            <h3>Orders by Type</h3>
            <canvas id="ordersChart"></canvas>
        </div>
        <div class="chart-container">
            <h3>Inventory Levels</h3>
            <canvas id="inventoryChart"></canvas>
        </div>
        <div class="chart-container">
            <h3>Orders Over Time</h3>
            <canvas id="ordersTimeChart"></canvas>
        </div>
    </div>

    <!-- Chart Scripts -->
    <script>
        <% List<Order> orders = (List<Order>) request.getAttribute("orders"); %>
        <% List<Inventory> inventory = (List<Inventory>) request.getAttribute("inventory"); %>

        // Orders by Type (Pie)
        const ordersData = {
            labels: ['In Orders', 'Out Orders'],
            datasets: [{
                data: [
                    <%= orders.stream().filter(o -> "in".equals(o.getType())).count() %>,
                    <%= orders.stream().filter(o -> "out".equals(o.getType())).count() %>
                ],
                backgroundColor: ['#4CAF50', '#FF5722']
            }]
        };
        new Chart(document.getElementById('ordersChart'), { type: 'pie', data: ordersData });

        // Inventory Levels (Bar)
        const inventoryLabels = [
            <% for (Inventory inv : inventory) { %>
                "<%= inv.getProduct() != null ? inv.getProduct().getName() : "N/A" %>",
            <% } %>
        ];
        const inventoryQuantities = [
            <% for (Inventory inv : inventory) { %>
                <%= inv.getQuantity() %>,
            <% } %>
        ];
        new Chart(document.getElementById('inventoryChart'), {
            type: 'bar',
            data: {
                labels: inventoryLabels,
                datasets: [{
                    label: 'Quantity',
                    data: inventoryQuantities,
                    backgroundColor: '#2196F3'
                }]
            }
        });

        // Orders Over Time (Line)
        const orderDates = [
            <% for (Order o : orders) { %> "<%= o.getDate() %>", <% } %>
        ];
        const orderTotals = [
            <% for (Order o : orders) { %> <%= o.getTotalAmount() != null ? o.getTotalAmount() : 0 %>, <% } %>
        ];
        new Chart(document.getElementById('ordersTimeChart'), {
            type: 'line',
            data: {
                labels: orderDates,
                datasets: [{
                    label: 'Order Amount',
                    data: orderTotals,
                    fill: false,
                    borderColor: '#673AB7',
                    tension: 0.1
                }]
            }
        });
    </script>
</body>
</html>
