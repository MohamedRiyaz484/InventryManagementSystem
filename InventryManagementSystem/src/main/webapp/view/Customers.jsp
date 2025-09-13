<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.project.IMS.entity.Customer" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Customer List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 90%;
            margin: 30px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 2px 8px rgba(0,0,0,0.1);
            position: relative;
        }
        h2 { text-align: center; color: #333; margin-bottom: 20px; }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .btn { padding: 6px 12px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; display: inline-block; min-width: 60px; text-align: center; }
        .btn-add { background: #4CAF50; color: white; }
        .btn-edit { background: #2196F3; color: white; }
        .btn-remove { background: #f44336; color: white; }

        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: center; }
        th { background: #f1f1f1; }

        /* Modal */
        .modal { display: none; position: fixed; z-index: 1000; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.6); }
        .modal:target { display: block; }
        .modal-content { background: #fff; margin: 8% auto; padding: 20px; border-radius: 8px; width: 400px; position: relative; box-shadow: 0px 2px 8px rgba(0,0,0,0.2); }
        .close { position: absolute; top: 10px; right: 15px; font-size: 18px; font-weight: bold; color: #333; text-decoration: none; }

        input[type="text"], input[type="submit"] { padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
        input[type="submit"] { background: #4CAF50; color: white; cursor: pointer; }
        
        #searchInput {
            width: 500px; /* smaller length */
            padding: 10px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        /* Highlight */
        .highlight {
            background-color: yellow;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Customers</h2>

    <div class="top-bar">
        <!-- Search Bar on Left -->
        <input type="text" id="searchInput" placeholder="Search Name" onkeyup="searchCustomers()">

        <!-- Add Customer Button on Right -->
        <a href="#addModal" class="btn btn-add">Add Customer</a>
    </div>

    <table id="customerTable">
        <tr>
            <th>Name</th>
            <th>Contact Info</th>
            <th>Remove</th>
            <th>Edit</th>
        </tr>
        <%
            List<Customer> customers = (List<Customer>) request.getAttribute("users");
            if (customers != null && !customers.isEmpty()) {
                for (Customer customer : customers) {
        %>
        <tr>
            <td class="custName"><%= customer.getName() %></td>
            <td><%= customer.getContactInfo() %></td>
            <td>
                <form action="/Customer/remove/<%= customer.getCustomerId() %>" method="post">
                    <input type="submit" value="Remove" class="btn btn-remove">
                </form>
            </td>
            <td>
                <a href="#editModal<%= customer.getCustomerId() %>" class="btn btn-edit">Edit</a>
            </td>
        </tr>

        <!-- Edit Modal -->
        <div id="editModal<%= customer.getCustomerId() %>" class="modal">
            <div class="modal-content">
                <a href="#" class="close">&times;</a>
                <h3>Edit Customer</h3>
                <form action="/Customer/update/<%= customer.getCustomerId() %>" method="post">
                    <input name="_method" type="hidden" value="put">
                    <input type="text" name="name" value="<%= customer.getName() %>" required>
                    <input type="text" name="contactInfo" value="<%= customer.getContactInfo() %>" required>
                    <input type="submit" value="Update">
                </form>
            </div>
        </div>

        <%      }
            } else { %>
        <tr><td colspan="4">No Customers Found</td></tr>
        <% } %>
    </table>
</div>

<!-- Add Customer Modal -->
<div id="addModal" class="modal">
    <div class="modal-content">
        <a href="#" class="close">&times;</a>
        <h3>Add Customer</h3>
        <form action="/Customer/AddCustomers" method="post">
            <input placeholder="Enter Name" type="text" name="name" required>
            <input placeholder="Phone or Email" type="text" name="contactInfo" required>
            <input type="submit" value="Save">
        </form>
    </div>
</div>

<script>
function searchCustomers() {
    let input = document.getElementById("searchInput").value.toLowerCase();
    let table = document.getElementById("customerTable");
    let rows = Array.from(table.rows).slice(1); // skip header

    let matched = [];
    let unmatched = [];

    rows.forEach(row => {
        let cell = row.querySelector(".custName");
        let nameText = cell.textContent.toLowerCase();

        // Remove previous highlights
        cell.innerHTML = cell.textContent;

        if (nameText.includes(input) && input !== "") {
            matched.push(row);
            // Highlight matching part
            let regex = new RegExp("(" + input + ")", "gi");
            cell.innerHTML = cell.textContent.replace(regex, '<span class="highlight">$1</span>');
        } else {
            unmatched.push(row);
        }
    });

    // Reorder table: matched first
    matched.concat(unmatched).forEach(row => table.appendChild(row));
}
</script>

</body>
</html>
