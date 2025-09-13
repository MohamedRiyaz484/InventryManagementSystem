package com.project.IMS.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.IMS.customExceptions.OutOfStockException;
import com.project.IMS.customExceptions.StockExceededException;
import com.project.IMS.entity.Inventory;
import com.project.IMS.entity.Log;
import com.project.IMS.entity.Order;
import com.project.IMS.entity.Product;
import com.project.IMS.entity.User;
import com.project.IMS.repository.CustomerRepository;
import com.project.IMS.repository.InventoryRepository;
import com.project.IMS.repository.LogRepository;
import com.project.IMS.repository.OrderDetailRepository;
import com.project.IMS.repository.OrderRepository;
import com.project.IMS.repository.ProductRepository;
import com.project.IMS.repository.SupplierRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired private OrderRepository orderRepo;
    @Autowired private OrderDetailRepository orderDetailRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private InventoryRepository inventoryRepo;
    @Autowired private LogRepository logRepo;
    @Autowired private SupplierRepository supplierRepo;
    @Autowired private CustomerRepository customerRepo;

    public void createPurchaseOrder(Long supplierId, List<Integer> productIds, List<Integer> quantities, List<Double> unitPrices, String notes, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setSupplier(supplierRepo.findById(supplierId).orElseThrow());
        order.setType("in");
        order.setNotes(notes);

        Order saved = orderRepo.save(order);
        double total = 0.0;

        for (int i = 0; i < productIds.size(); i++) {
            Integer productId = productIds.get(i);
            int qty = quantities.get(i);
            double unitPrice = unitPrices.get(i);

            Product product = productRepo.findById(productId).orElseThrow();
            Inventory inventory = inventoryRepo.getByProductId(productId);

            // update inventory
            inventory.setQuantity(inventory.getQuantity() + qty);
            
            if (inventory.getQuantity() > inventory.getMaxLevel()) {
                throw new StockExceededException("Max stock reached for product " + product.getName());
            }
            if(inventory.getQuantity().equals(0)) 
            {
            	inventory.setMaxLevel(qty*2);
            	inventory.setMinLevel((int)Math.round(qty*0.5));
            	inventory.setReorderPoint(inventory.getMinLevel()+inventory.getMinLevel()/2);  
            }
            // always update cost (not only when zero stock)
            product.setCost(unitPrice);
            product.setPrice(unitPrice+unitPrice*0.2);
            productRepo.save(product);
            orderDetailRepo.saveOrderDetails(qty, unitPrice, saved.getOrderId(), productId);
            Log log = new Log();
 	        log.setUser(user);
 	        log.setAction("ADD_STOCK");
 	        log.setEntityType("product");
 	        log.setEntityId(Long.valueOf(productIds.get(i)));
 	        log.setTimestamp(OffsetDateTime.now());
 	        log.setDetails("Added " + quantities.get(i) + " units for product ID " + productIds.get(i)+" from "+order.getSupplier().getName());
 	        logRepo.save(log);
            total += qty * unitPrice;
        }
        saved.setTotalAmount(total);
        orderRepo.save(saved);
    }

    public void createSalesOrder(Integer customerId, List<Integer> productIds, List<Integer> quantities, List<Double> unitPrices, String notes, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setCustomer(customerRepo.findById(customerId).orElseThrow());
        order.setType("out");
        order.setNotes(notes);

        Order saved = orderRepo.save(order);
        double total = 0.0;
        for (int i = 0; i < productIds.size(); i++) {
            Integer productId = productIds.get(i);
            int qty = quantities.get(i);
            double unitPrice = unitPrices.get(i);

            Product product = productRepo.findById(productId).orElseThrow();
            Inventory inventory = inventoryRepo.getByProductId(productId);

            if (inventory.getQuantity() < qty) {
                throw new OutOfStockException("Not enough stock for " + product.getName());
            }
            productRepo.save(product);
            inventory.setQuantity(inventory.getQuantity() - qty);
            inventoryRepo.save(inventory);

            orderDetailRepo.saveOrderDetails(qty, unitPrice, saved.getOrderId(), productId);

            Log log = new Log();
	        log.setUser(user);
	        log.setAction("SALE");
	        log.setEntityType("product");
	        log.setEntityId(Long.valueOf(productIds.get(i)));
	        log.setTimestamp(OffsetDateTime.now());
	        log.setDetails
	        ("Added " + qty + " units of " + product.getName() + " from supplier " + order.getCustomer().getName());
	        logRepo.save(log);
            total += qty * unitPrice;
        }

        saved.setTotalAmount(total);
        orderRepo.save(saved);
    }
}

