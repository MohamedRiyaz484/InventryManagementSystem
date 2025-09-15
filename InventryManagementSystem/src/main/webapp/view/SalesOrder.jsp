<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.IMS.entity.Customer" %>
<%@ page import="com.project.IMS.DTO.ProductDetailsDTO" %>

<html>
<head>
    <title>Create Sales Order</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <style>
        body {
            padding: 20px;
            background-color: #f8f9fa;
        }
        h2 {
            margin-bottom: 20px;
        }
        table {
            background: white;
        }
        .table-hover tbody tr:hover {
            background-color: #f1f1f1;
        }
        .form-section {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2 class="text-center">Create Sales Order</h2>

    <form action="/orders/sales" method="post" class="card p-4 shadow-sm">

        <!-- Customer Dropdown -->
        <div class="form-section mb-3">
            <label for="customerId" class="form-label fw-bold">Customer</label>
            <select id="customerId" name="customerId" class="form-select" required>
                <%
                    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                    if (customers != null && !customers.isEmpty()) {
                        for (Customer c : customers) {
                %>
                    <option value="<%= c.getCustomerId() %>">
                        <%= c.getName() %> (<%= c.getContactInfo() %>)
                    </option>
                <%
                        }
                    } else {
                %>
                    <option disabled>No customers available</option>
                <%
                    }
                %>
            </select>
        </div>

        <!-- Notes -->
        <div class="form-section mb-3">
            <label for="notes" class="form-label fw-bold">Notes</label>
            <input type="text" id="notes" name="notes" class="form-control" placeholder="Enter notes...">
        </div>

        <!-- Products Table -->
        <h5 class="mb-3">Select Products to Sell</h5>

        <!-- Search Box -->
        <div class="mb-3">
            <input type="text" id="productSearch" class="form-control" placeholder="🔍 Search products...">
        </div>

        <table id="productTable" class="table table-bordered table-hover text-center align-middle">
            <thead class="table-dark">
                <tr>
                    <th>Select</th>
                    <th>Product</th>
                    <th>Quantity To Sell</th>
                    <th>Unit Price</th>
                    <th>Available Quantity</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<ProductDetailsDTO> products = (List<ProductDetailsDTO>) request.getAttribute("products");
                    if (products != null && !products.isEmpty()) {
                        int index = 0;
                        for (ProductDetailsDTO p : products) {
                %>
                <tr>
                    <td>
                        <input type="checkbox" name="products[<%= index %>].id" value="<%= p.getProductId() %>">
                    </td>
                    <td><%= p.getName() %></td>
                    <td>
                        <input type="number" name="products[<%= index %>].quantity" class="form-control" min="0">
                    </td>
                    <td>
                        <input type="number" name="products[<%= index %>].unitPrice" value="<%= p.getPrice() %>" step="0.01" class="form-control">
                    </td>
                    <td><%= p.getQuantity() %></td>
                </tr>
                <%
                            index++;
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5" class="text-muted">No products available</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <!-- Save Button -->
        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary px-4">💾 Save Sales Order</button>
        </div>
    </form>
</div>

<!-- Bootstrap + Search Script -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.getElementById("productSearch").addEventListener("keyup", function() {
        let filter = this.value.toLowerCase();
        let rows = document.querySelectorAll("#productTable tbody tr");

        rows.forEach(row => {
            let productName = row.cells[1]?.textContent.toLowerCase();
            if (productName && productName.includes(filter)) {
                row.style.display = "";
            } else {
                row.style.display = "none";
            }
        });
    });
</script>

</body>
</html>
