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

        <!-- Customer Search with Suggestions -->
        <div class="form-section mb-3">
            <label for="customerId" class="form-label fw-bold">Customer</label>
            <input class="form-control" list="customersList" id="customerId" name="customerId"
                   placeholder="Search or select a customer..." required onfocus="this.showPicker?.()">

            <datalist id="customersList">
                <%
                    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                    if (customers != null && !customers.isEmpty()) {
                        for (Customer c : customers) {
                %>
                    <option value="<%= c.getCustomerId() %> - <%= c.getName() %> (<%= c.getContactInfo() %>)"></option>
                <%
                        }
                    }
                %>
            </datalist>
        </div>

        <!-- Notes (Descriptive Box) -->
        <div class="form-section mb-3">
            <label for="notes" class="form-label fw-bold">Notes</label>
            <textarea id="notes" name="notes" class="form-control" rows="4" placeholder="Enter detailed notes..."></textarea>
        </div>

        <!-- Search Bar for Products -->
        <div class="form-section mb-3">
            <label for="productSearch" class="form-label fw-bold">Search Products</label>
            <input type="text" id="productSearch" class="form-control" placeholder="Type to search products in the table...">
        </div>

        <!-- Products Table -->
        <h5 class="mb-3">Select Products to Sell</h5>
        <table class="table table-bordered table-hover text-center align-middle" id="productsTable">
            <thead class="table-dark">
                <tr>
                    <th>Select</th>
                    <th>Product</th>
                    <th>Quantity To Sell</th>
                    <th>Product Price</th>
                    <th>Available Quantity</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<ProductDetailsDTO> products = (List<ProductDetailsDTO>) request.getAttribute("products");
                    if (products != null && !products.isEmpty()) {
                        for (ProductDetailsDTO p : products) {
                %>
                <tr>
                    <td><input type="checkbox" name="product_id" value="<%= p.getProductId() %>"></td>
                    <td><%= p.getName() %></td>
                    <td><input type="number" name="quantity" class="form-control" min="1"></td>
                    <td><input type="number" name="unit_price" class="form-control" value="<%= p.getPrice() %>" step="0.01"></td>
                    <td><%= p.getQuantity() %></td>
                </tr>
                <%
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

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- Product Search Script -->
<script>
    document.getElementById("productSearch").addEventListener("keyup", function() {
        let filter = this.value.toLowerCase();
        let rows = document.querySelectorAll("#productsTable tbody tr");
        rows.forEach(row => {
            let productName = row.cells[1].innerText.toLowerCase();
            row.style.display = productName.includes(filter) ? "" : "none";
        });
    });
</script>

</body>
</html>
