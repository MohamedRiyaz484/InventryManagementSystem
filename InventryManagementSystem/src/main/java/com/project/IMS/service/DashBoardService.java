package com.project.IMS.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.IMS.entity.*;
import com.project.IMS.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashBoardService {

    @Autowired private ProductRepository productRepo;
    @Autowired private SupplierRepository supplierRepo;
    @Autowired private CustomerRepository customerRepo;
    @Autowired private OrderRepository orderRepo;
    @Autowired private InventoryRepository inventoryRepo;
    @Autowired private LogRepository logRepo;

    private final ObjectMapper mapper = new ObjectMapper();

    public List<Product> getProductsByUser(Integer userId) { return productRepo.getByUserId(userId); }
    public List<Supplier> getSuppliersByUser(Integer userId) { return supplierRepo.getByUserId(userId); }
    public List<Customer> getCustomersByUser(Integer userId) { return customerRepo.getAllCustomers(userId); }
    public List<Order> getOrdersByUser(Integer userId) { return orderRepo.getOrdersByUserId(userId); }
    public List<Inventory> getInventoryByUser(Integer userId) { return inventoryRepo.getByUserId(userId); }
    public List<Log> getLogsByUser(Integer userId) { return logRepo.getLogs(userId); }

    // Chart Data: Inventory
    public String getInventoryLabelsJson(Integer userId) {
        try {
            List<String> labels = inventoryRepo.getByUserId(userId)
                    .stream().map(inv -> "Product#" + inv.getProduct()).toList();
            return mapper.writeValueAsString(labels);
        } catch (Exception e) { return "[]"; }
    }

    public String getInventoryDataJson(Integer userId) {
        try {
            List<Integer> quantities = inventoryRepo.getByUserId(userId)
                    .stream().map(Inventory::getQuantity).toList();
            return mapper.writeValueAsString(quantities);
        } catch (Exception e) { return "[]"; }
    }

    // Chart Data: Orders (in vs out)
    public String getOrderCountsJson(Integer userId) {
        try {
            long purchaseCount = orderRepo.getOrdersByUserId(userId).stream().filter(o -> "in".equals(o.getType())).count();
            long salesCount = orderRepo.getOrdersByUserId(userId).stream().filter(o -> "out".equals(o.getType())).count();
            return mapper.writeValueAsString(List.of(purchaseCount, salesCount));
        } catch (Exception e) { return "[0,0]"; }
    }
}
