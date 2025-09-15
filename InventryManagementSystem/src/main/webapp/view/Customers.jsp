<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.project.IMS.entity.Customer" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Customers</title>
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
        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .btn-back {
            background: #007bff;
            color: #fff;
            border: none;
            padding: 8px 14px;
            border-radius: 6px;
            cursor: pointer;
            text-decoration: none;
        }
		#edit
		{
			height:30px;
		}
        .btn-back:hover {
            background: #0056b3;
        }
        .action-buttons {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <!-- Top Bar -->
    <div class="top-bar">
        <h2 class="text-center">Customers</h2>
        <a href="${pageContext.request.contextPath}/home" class="btn-back">Back to Main Menu</a>
    </div>

    <!-- Action Buttons -->
    <div class="action-buttons d-flex justify-content-center gap-3">
        <a class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addModal">➕ Add Customer</a>
    </div>

    <!-- Search -->
    <div class="mb-3">
        <input type="text" id="searchBox" class="form-control" placeholder="🔍 Search customers...">
    </div>

    <!-- Customer Table -->
    <table class="table table-bordered table-hover text-center align-middle" id="customerTable">
        <thead class="table-dark">
            <tr>
                <th>Name</th>
                <th>Contact Info</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<Customer> customers = (List<Customer>) request.getAttribute("users");
            if (customers != null && !customers.isEmpty()) {
                for (Customer customer : customers) {
        %>
            <tr>
                <td><%= customer.getName() %></td>
                <td><%= customer.getContactInfo() %></td>
                <td class="d-flex justify-content-center gap-2">
                    <!-- Edit Button -->
                    <a id="edit" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editModal<%= customer.getCustomerId() %>">✏️ Edit</a>
                    <!-- Remove Form -->
                    <form action="/Customer/remove/<%= customer.getCustomerId() %>" method="post" style="display:inline;">
                        <input type="submit" value="🗑 Remove" class="btn btn-danger btn-sm">
                    </form>
                </td>
            </tr>

            <!-- Edit Modal -->
            <div class="modal fade" id="editModal<%= customer.getCustomerId() %>" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content p-3">
                        <div class="modal-header">
                            <h5 class="modal-title">Edit Customer</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <form action="/Customer/update/<%= customer.getCustomerId() %>" method="post">
                            <input type="hidden" name="_method" value="put">
                            <div class="modal-body">
                                <input type="text" name="name" value="<%= customer.getName() %>" class="form-control mb-2" required>
                                <input type="text" name="contactInfo" value="<%= customer.getContactInfo() %>" class="form-control mb-2" required>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-success">Update</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="3" class="text-muted">No Customers Found</td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<!-- Add Modal -->
<div class="modal fade" id="addModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content p-3">
            <div class="modal-header">
                <h5 class="modal-title">Add Customer</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <form action="/Customer/AddCustomers" method="post">
                <div class="modal-body">
                    <input type="text" name="name" class="form-control mb-2" placeholder="Enter Name" required>
                    <input type="text" name="contactInfo" class="form-control mb-2" placeholder="Phone or Email" required>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Save</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS + Search -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Simple search filter
    document.getElementById("searchBox").addEventListener("keyup", function() {
        const filter = this.value.toLowerCase();
        const rows = document.querySelectorAll("#customerTable tbody tr");
        rows.forEach(row => {
            const text = row.textContent.toLowerCase();
            row.style.display = text.includes(filter) ? "" : "none";
        });
    });
</script>

</body>
</html>
