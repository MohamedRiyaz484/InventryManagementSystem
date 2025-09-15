package com.project.IMS.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.IMS.DTO.PurchaseOrderForm;
import com.project.IMS.DTO.PurchaseProductDTO;
import com.project.IMS.DTO.SalesOrderForm;
import com.project.IMS.DTO.SalesProductDTO;
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

	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private InventoryRepository inventoryRepo;
	@Autowired
	private LogRepository logRepo;
	@Autowired
	private SupplierRepository supplierRepo;
	@Autowired
	private CustomerRepository customerRepo;

	@Transactional
	public void createPurchaseOrder(PurchaseOrderForm form, User user) {
	    Order order = new Order();
	    order.setUser(user);
	    order.setSupplier(supplierRepo.findById(form.getSupplierId()).orElseThrow());
	    order.setType("in");
	    order.setNotes(form.getNotes());

	    Order saved = orderRepo.save(order);
	    double total = 0.0;

	    for (PurchaseProductDTO dto : form.getProducts()) {
	        if (dto.getProductId() == null || dto.getQuantity() == null || dto.getUnitPrice() == null) {
	            continue;
	        }

	        Product product = productRepo.findById(dto.getProductId()).orElseThrow();
	        Inventory inventory = inventoryRepo.getByProductId(dto.getProductId());

	        // update inventory
	        inventory.setQuantity(inventory.getQuantity() + dto.getQuantity());

	        if (inventory.getQuantity() > inventory.getMaxLevel()) {
	            throw new StockExceededException("Max stock reached for product " + product.getName());
	        }
	        if (inventory.getQuantity().equals(0)) {
	            inventory.setMaxLevel(dto.getQuantity() * 2);
	            inventory.setMinLevel((int) Math.round(dto.getQuantity() * 0.5));
	            inventory.setReorderPoint(inventory.getMinLevel() + inventory.getMinLevel() / 2);
	        }

	        // update cost/price
	        product.setCost(dto.getUnitPrice());
	        product.setPrice(dto.getUnitPrice() + dto.getUnitPrice() * 0.2);
	        productRepo.save(product);

	        orderDetailRepo.saveOrderDetails(dto.getQuantity(), dto.getUnitPrice(), saved.getOrderId(), dto.getProductId());

	        Log log = new Log();
	        log.setUser(user);
	        log.setAction("ADD_STOCK");
	        log.setEntityType("product");
	        log.setEntityId(Long.valueOf(dto.getProductId()));
	        log.setTimestamp(OffsetDateTime.now());
	        log.setDetails("Added " + dto.getQuantity() + " units for product ID " + dto.getProductId() +
	                       " from " + order.getSupplier().getName());
	        logRepo.save(log);
	        
	        total += dto.getQuantity() * dto.getUnitPrice();
	    }

	    saved.setTotalAmount(total);
	    orderRepo.save(saved);
	}


	public void createSalesOrder(SalesOrderForm form, User user) {
        // Create order
        Order order = new Order();
        order.setUser(user);
        order.setCustomer(customerRepo.findById(form.getCustomerId())
                          .orElseThrow(() -> new RuntimeException("Customer not found")));
        order.setType("out"); // sale
        order.setNotes(form.getNotes());

        Order saved = orderRepo.save(order);

        double total = 0.0;

        // Iterate over selected products
        List<SalesProductDTO> products = form.getProducts();
        if (products != null) {
            for (SalesProductDTO dto : products) {
                if (dto.getId() == null || dto.getQuantity() == null || dto.getQuantity() <= 0) {
                    continue;
                }

                Product product = productRepo.findById(dto.getId())
                                    .orElseThrow(() -> new RuntimeException("Product not found"));
                Inventory inventory = inventoryRepo.getByProductId(dto.getId());

                if (inventory.getQuantity() < dto.getQuantity()) {
                    throw new OutOfStockException("Not enough stock for " + product.getName());
                }

                // Reduce inventory
                inventory.setQuantity(inventory.getQuantity() - dto.getQuantity());
                inventoryRepo.save(inventory);

                // Save order details
                orderDetailRepo.saveOrderDetails(dto.getQuantity(),
                                                 dto.getUnitPrice(),
                                                 saved.getOrderId(),
                                                 dto.getId());

                // Add log
                Log log = new Log();
                log.setUser(user);
                log.setAction("SALE");
                log.setEntityType("product");
                log.setEntityId(Long.valueOf(dto.getId()));
                log.setTimestamp(OffsetDateTime.now());
                log.setDetails("Sold " + dto.getQuantity() + " units of "
                        + product.getName() + " to customer " + order.getCustomer().getName());
                logRepo.save(log);

                total += dto.getQuantity() * dto.getUnitPrice();
            }
        }

        saved.setTotalAmount(total);
        orderRepo.save(saved);
    }

}
