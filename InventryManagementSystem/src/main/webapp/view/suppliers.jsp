
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <%
    // Get logged-in userId from session
    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
        userId = 0; // or redirect to login page if you want
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Supplier Management</title>
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: #f5f6fa;
        margin: 20px;
        color: #333;
    }

    h1 {
        font-size: 28px;
        font-weight: 700;
        margin-bottom: 20px;
        color: #2c3e50;
    }

    .toolbar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 25px;
        flex-wrap: wrap;
        gap: 12px;
    }

    #searchInput {
        flex: 1;
        max-width: 350px;
        padding: 10px 14px;
        font-size: 14px;
        border: 1px solid #ccc;
        border-radius: 6px;
        background-color: #fff;
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
        color: #444;
    }

    /* Add Supplier Button (above table, aligned right) */
    .btn-add {
        display: inline-block;
        background-color: #4c8bf5;
        color: white;
        padding: 10px 18px;
        font-size: 15px;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        transition: background-color 0.3s ease;
        white-space: nowrap;
        box-shadow: 0 3px 8px rgba(76, 139, 245, 0.3);
        margin-bottom: 12px;
        float: right;
    }

    .btn-add:hover {
        background-color: #3367d6;
    }

    /* Table styles */
    table {
        width: 100%;
        border-collapse: collapse;
        background: #ffffff;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
        border-radius: 6px;
        overflow: hidden;
        margin-top: 10px;
    }

    th, td {
        padding: 14px 16px;
        border-bottom: 1px solid #eee;
        font-size: 14px;
    }

    th {
        background-color: #1c262f;
        color: #f0f0f0;
        font-weight: 700;
        text-align: left;
    }

    th:last-child {
        text-align: center;
    }

    td {
        color: #222; /* Darker text */
    }

    td:last-child {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 8px;
    }

    /* Action Buttons */
    .btn-edit {
        background-color: #f0ad4e;
        color: #fff;
        border: none;
        padding: 6px 12px;
        border-radius: 4px;
        font-size: 13px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .btn-edit:hover {
        background-color: #ec971f;
    }

    .btn-delete {
        background-color: #e74c3c;
        color: #fff;
        border: none;
        padding: 6px 12px;
        border-radius: 4px;
        font-size: 13px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .btn-delete:hover {
        background-color: #c0392b;
    }

    /* Modal styles */
    .modal {
        display: none;
        position: fixed;
        z-index: 1000;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.5);
    }

    .modal-content {
        background-color: #fff;
        margin: 8% auto;
        padding: 20px 25px;
        border-radius: 10px;
        width: 400px;
        box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
    }

    .modal-content h2 {
        font-size: 20px;
        font-weight: 600;
        margin-bottom: 20px;
        color: #2c3e50;
    }

    .modal-content label {
        display: block;
        margin-bottom: 6px;
        font-weight: bold;
        font-size: 14px;
        color: #333;
    }

    .modal-content input,
    .modal-content textarea {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 6px;
        box-sizing: border-box;
        font-size: 14px;
        margin-bottom: 15px;
    }

    .modal-footer {
        text-align: right;
    }

    .modal-footer button {
        padding: 8px 14px;
        font-size: 14px;
        border-radius: 5px;
        margin-left: 8px;
    }

    .btn-save {
        background-color: #2ecc71;
        color: white;
        border: none;
        cursor: pointer;
    }

    .btn-save:hover {
        background-color: #27ae60;
    }

    .btn-cancel {
        background-color: #7f8c8d;
        color: white;
        border: none;
        cursor: pointer;
    }

    .btn-cancel:hover {
        background-color: #636e72;
    }

    /* Toast notification */
    #toast {
        visibility: hidden;
        min-width: 250px;
        background-color: #e74c3c;
        color: white;
        text-align: center;
        border-radius: 4px;
        padding: 12px;
        position: fixed;
        top: 20px;
        right: 20px;
        z-index: 9999;
        font-size: 14px;
    }

    #toast.show {
        visibility: visible;
        animation: fadein 0.5s, fadeout 0.5s 3s;
    }

    @keyframes fadein {
        from { top: 0; opacity: 0; }
        to { top: 20px; opacity: 1; }
    }

    @keyframes fadeout {
        from { top: 20px; opacity: 1; }
        to { top: 0; opacity: 0; }
    }
</style>

</head>
<body>
<h1>Supplier Management</h1>
<input type="text" id="searchInput" placeholder="Search suppliers...">
<button class="btn-add" onclick="openAddModal()">Add Supplier</button>

<table id="supplierTable">
    <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Location</th>
            <th>Phone Number</th>
            <!-- Removed Product Type column -->
            <!--<th>Product Type</th>-->
            <th>Actions</th> 
        </tr>
    </thead>
    <tbody>
        <!-- Rows will be rendered here -->
    </tbody>
</table>

<!-- Add/Edit Modal -->
<!-- Add/Edit Modal -->
<div id="supplierModal" class="modal">
    <div class="modal-content">
        <h2 id="modalTitle">Add New Supplier</h2>

        <!-- ✅ Updated form tag -->
        <form id="supplierForm" onsubmit="event.preventDefault(); saveSupplier();">
            <input type="hidden" id="supplierId" />
            <input type="hidden" id="userId" value="<%= userId %>" />

            <!-- Name -->
            <label for="name">Supplier Name</label>
            <input type="text" id="name" required pattern="^[a-zA-Z ]+$" title="Name must contain only letters and spaces">

            <!-- Email -->
            <label for="email">Email</label>
            <input type="email" id="email" required title="Please enter a valid email">

            <!-- Phone -->
            <label for="phone">Phone</label>
            <input type="text" id="phone" required pattern="^\d{10}$" title="Phone number must be exactly 10 digits">

            <!-- Location -->
            <label for="location">Address</label>
            <textarea id="location" rows="3" required title="Address is required"></textarea>

            <div class="modal-footer">
                <!-- ✅ Use type="submit" now -->
                <button type="submit" class="btn-save">Save</button>
                <button type="button" class="btn-cancel" onclick="closeModal()">Cancel</button>
            </div>
        </form>
    </div>
</div>


<div id="toast"></div>

<script>
let suppliers = [];
let editingSupplierId = null;

function showToast(message) {
    const toast = document.getElementById("toast");
    toast.textContent = message;
    toast.className = "show";
    setTimeout(() => { toast.className = toast.className.replace("show",""); }, 3500);
}

function fetchSuppliers() {
    fetch("/api/suppliers/getAll")
        .then(res => { if(!res.ok) throw new Error("Failed to load suppliers."); return res.json(); })
        .then(data => { suppliers = data; renderTable(suppliers); })
        .catch(() => showToast("Network error while fetching suppliers."));
}

function renderTable(data) {
    const tbody = document.querySelector("#supplierTable tbody");
    if (!tbody) {
        console.error("❌ tbody not found");
        return;
    }

    tbody.innerHTML = ""; // Clear existing rows

    if (!data || data.length === 0) {
        // Show "No result found" row
        const tr = document.createElement("tr");
        const td = document.createElement("td");
        td.setAttribute("colspan", "5"); // Number of columns (Name, Email, Location, Phone, Actions)
        td.style.textAlign = "center";
        td.style.color = "red";
        td.textContent = "No result found";
        tr.appendChild(td);
        tbody.appendChild(tr);
        return;
    }

    data.forEach(supplier => {
        if (!supplier || !supplier.supplierId) {
            console.warn("⚠️ Skipping invalid supplier:", supplier);
            return;
        }

        const tr = document.createElement("tr");

        const tdName = document.createElement("td");
        tdName.textContent = supplier.name || "";
        tr.appendChild(tdName);

        const tdContact = document.createElement("td");
        tdContact.textContent = supplier.contactInfo || "";
        tr.appendChild(tdContact);

        const tdLocation = document.createElement("td");
        tdLocation.textContent = supplier.location || "";
        tr.appendChild(tdLocation);

        const tdPhone = document.createElement("td");
        tdPhone.textContent = supplier.phoneNumber || "";
        tr.appendChild(tdPhone);

        // Product Type column removed
        // const tdProduct = document.createElement("td");
        // tdProduct.textContent = supplier.productType || "";
        // tr.appendChild(tdProduct);

        // Action buttons
        const tdActions = document.createElement("td");

        const editBtn = document.createElement("button");
        editBtn.className = "btn-edit";
        editBtn.textContent = "Edit";
        editBtn.addEventListener("click", () => {
            openEditModal(supplier.supplierId);
        });

        const deleteBtn = document.createElement("button");
        deleteBtn.className = "btn-delete";
        deleteBtn.textContent = "Delete";
        deleteBtn.addEventListener("click", () => {
            deleteSupplier(supplier.supplierId);
        });

        tdActions.appendChild(editBtn);
        tdActions.appendChild(deleteBtn);
        tr.appendChild(tdActions);

        tbody.appendChild(tr);
    });
}

function openEditModal(id) {
    editingSupplierId = id;
    const supplier = suppliers.find(s => s.supplierId === id);
    if (!supplier) { showToast("Supplier not found! Please refresh the page."); return; }
    document.getElementById("modalTitle").textContent = "Edit Supplier";
    document.getElementById("supplierId").value = supplier.supplierId;
    document.getElementById("name").value = supplier.name;
    document.getElementById("email").value = supplier.contactInfo;
    document.getElementById("phone").value = supplier.phoneNumber;
    document.getElementById("location").value = supplier.location;
    // Removed productType input fill since field removed
    // document.getElementById("productType").value = supplier.productType;

    // Set userId from supplier.user or from session
    let userIdInput = document.getElementById("userId");
    if (supplier.user && supplier.user.id) {
        userIdInput.value = supplier.user.id;
    } else {
        // fallback: keep session userId
        userIdInput.value = "<%= userId %>";
    }
    document.getElementById("supplierModal").style.display = "block";
}

function openAddModal() {
    editingSupplierId = null;
    document.getElementById("modalTitle").textContent = "Add New Supplier";
    document.getElementById("supplierForm").reset();
    // Reset hidden supplierId
    document.getElementById("supplierId").value = "";
    // Set userId from session (JSP embedded)
    document.getElementById("userId").value = "<%= userId %>";
    document.getElementById("supplierModal").style.display = "block";
}

function closeModal() { 
    editingSupplierId = null;
    document.getElementById("supplierModal").style.display = "none"; 
}

function saveSupplier() {
    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const location = document.getElementById("location").value.trim();
    const userIdVal = parseInt(document.getElementById("userId").value, 10);

    if (!name) return showToast("Supplier Name is required.");
    if (!/^[a-zA-Z ]+$/.test(name)) return showToast("Supplier Name must contain only letters and spaces.");

    if (!email) return showToast("Email is required.");
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) return showToast("Invalid email format.");

    if (!phone) return showToast("Phone number is required.");
    if (!/^\d{10}$/.test(phone)) return showToast("Phone number must be exactly 10 digits.");

    if (!location) return showToast("Location is required.");

    // Build supplier object without productType (since removed)
    const supplier = {
        name,
        contactInfo: email,
        phoneNumber: phone,
        location,
        user: { id: userIdVal }
    };

    let url = "/api/suppliers/create";
    let method = "POST";

    if (editingSupplierId) {
        url = `/api/suppliers/update/\${editingSupplierId}`;
		//url = `/api/suppliers/update/\${editingSupplierId}`
        method = "PUT";
    }

    fetch(url, {
        method: method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(supplier)
    })
    .then(res => {
        if (res.ok) {
            closeModal();
            fetchSuppliers();
        } else {
            switch (res.status) {
                case 400: showToast("Invalid input!"); break;
                case 404: showToast("Supplier not found!"); break;
				case 409: showToast("Supplier with this email already exists!"); break; 
                case 415: showToast("Unsupported format!"); break;
                case 500: showToast("Server error!"); break;
                default: showToast(`Error ${res.status}`); 
            }
        }
    })
    .catch(() => showToast("Network error!"));
}

function deleteSupplier(id) {
    if (confirm("Are you sure you want to delete this supplier?")) {
        const deleteUrl = "/api/suppliers/delete/" + id;
        fetch(deleteUrl, { method: "DELETE" })
        .then(res => {
            if (res.ok) {
                fetchSuppliers();
            } else {
                showToast(`Error ${res.status} deleting supplier.`);
            }
        })
        .catch(() => showToast("Network error while deleting."));
    }
}

document.getElementById("searchInput").addEventListener("input", function() {
    const searchTerm = this.value.toLowerCase();
    const filtered = suppliers.filter(s => 
        (s.name && s.name.toLowerCase().includes(searchTerm)) ||
        (s.contactInfo && s.contactInfo.toLowerCase().includes(searchTerm)) ||
        (s.phoneNumber && s.phoneNumber.includes(searchTerm)) ||
        (s.location && s.location.toLowerCase().includes(searchTerm))
    );
    renderTable(filtered);
});

window.onclick = function(event) {
    const modal = document.getElementById("supplierModal");
    if(event.target === modal) closeModal();
}

fetchSuppliers();
</script>

</body>
</html>