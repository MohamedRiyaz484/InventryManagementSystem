<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.IMS.DTO.OrderDetailsDTO" %>
<html>
<head>
    <title>Orders</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <style>
        body {
            padding: 20px;
            background-color: #f8f9fa;
        }
        h2 {
            margin-top: 20px;
            margin-bottom: 15px;
        }
        table {
            background: white;
        }
        .table-hover tbody tr:hover {
            background-color: #f1f1f1;
        }
        .action-buttons {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2 class="text-center">Orders</h2>
    
    <!-- Action buttons -->
    <div class="action-buttons d-flex justify-content-center gap-3">
        <form action="/orders/orderType" method="get">
            <input type="hidden" name="type" value="In">
            <button type="submit" class="btn btn-success">➕ Add Purchase</button>
        </form>
        <form action="/orders/orderType" method="get">
            <input type="hidden" name="type" value="Out">
            <button type="submit" class="btn btn-primary">➕ Add Sales</button>
        </form>
    </div>

    <h2>Order Details</h2>
    
    <!-- Search -->
    <div class="mb-3">
        <input type="text" id="searchBox" class="form-control" placeholder="🔍 Search orders...">
    </div>

    <!-- Orders Table -->
    <table class="table table-bordered table-hover text-center align-middle" id="ordersTable">
        <thead class="table-dark">
            <tr>
                <th>Date</th>
                <th>Notes</th>
                <th>Quantity</th>
                <th>Unit Price</th>
				<th>Total Amount</th>
                <th>Order Type</th>
				
            </tr>
        </thead>
        <tbody>
        <%
            List<OrderDetailsDTO> details = (List<OrderDetailsDTO>) request.getAttribute("orders");
            if (details != null && !details.isEmpty()) {
                for (OrderDetailsDTO od : details) {
        %>
            <tr>
                <td><%= od.getDate() %></td>
                <td><%= od.getNotes() %></td>
                <td><%= od.getQuantity() %></td>
                <td>₹ <%= od.getUnitPrice() %></td>
				<td> <%= od.getTotalAmount()%> </td>
                <td>
                    <span class="badge <%= od.getType().equalsIgnoreCase("in") ? "bg-success" : "bg-primary" %>">
                        <%= od.getType().equalsIgnoreCase("in") ? "Purchase" : "Sale" %>
                    </span>
                </td>
				
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="5" class="text-muted">No data available</td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS + Search Script -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Simple search filter
    document.getElementById("searchBox").addEventListener("keyup", function() {
        const filter = this.value.toLowerCase();
        const rows = document.querySelectorAll("#ordersTable tbody tr");
        rows.forEach(row => {
            const text = row.textContent.toLowerCase();
            row.style.display = text.includes(filter) ? "" : "none";
        });
    });
</script>

</body>
</html>
