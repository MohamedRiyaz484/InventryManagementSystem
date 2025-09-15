package com.project.IMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.IMS.DTO.OrderDetailsDTO;
import com.project.IMS.DTO.ProductDetailsDTO;
import com.project.IMS.DTO.PurchaseOrderForm;
import com.project.IMS.DTO.SalesOrderForm;
import com.project.IMS.DTO.SalesProductDTO;
import com.project.IMS.entity.User;
import com.project.IMS.repository.CustomerRepository;
import com.project.IMS.repository.InventoryRepository;
import com.project.IMS.repository.OrderRepository;
import com.project.IMS.repository.ProductRepository;
import com.project.IMS.repository.SupplierRepository;
import com.project.IMS.repository.UserRepository;
import com.project.IMS.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	private ProductRepository products;
//	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private SupplierRepository supplierRepo;
	
	@Autowired
	private InventoryRepository inventoryRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	OrderService orderService;
	public OrdersController(OrderRepository orderRepo) 
	{
	this.orderRepo = orderRepo;	
	}

	
	@GetMapping("orderType")
	public String getOrderType(@RequestParam String type, Model mod,HttpServletRequest req)
	{
		Integer userId=Integer.parseInt(req.getSession(false).getAttribute("userId").toString());
		List<Object[]> results = this.products.getProducts(userId);
		System.out.println(results);
		List<ProductDetailsDTO> products = results.stream().map(row ->
		{
			return new ProductDetailsDTO(
					(Long)row[0],
					((String)row[1]),
					(String)row[2],
					(Double)row[3],
					(Double)row[4],
					(Integer)row[5],
					(Integer)row[6],
					(Integer)row[7]
					);
		}).toList();
		System.out.println(products);
		mod.addAttribute("products",products);
		

		if(type.equalsIgnoreCase("In")) 
		{
			mod.addAttribute("suppliers", supplierRepo.getByUserId(userId));
			return "PurchaseOrder";
		}
		mod.addAttribute("customers", customerRepo.getAllCustomers(userId));
		return "SalesOrder";
	}
	
	@GetMapping("/orderView")
	public String getPage(Model mod,HttpServletRequest req) 
	{
		Integer userId=Integer.parseInt(req.getSession(false).getAttribute("userId").toString());
		List<Object[]> results = orderRepo.getOrders(userId);
		
		List<OrderDetailsDTO> orders = results.stream().map(row -> {
		    return new OrderDetailsDTO(
		        ((java.sql.Date) row[0]).toLocalDate(),  // convert SQL date to LocalDate
		        (String) row[1],
		        ((Integer) row[2]).intValue(),
		        ((Double) row[3]).doubleValue(),
		        ((Number) row[4]).intValue(),
		        ((String) row[5]),
		        ((Double) row[6] != null)?(Double) row[6]:((Integer) row[2]).intValue()*
				        ((Double) row[3]).doubleValue()
		    );
		}).toList();
		mod.addAttribute("orders", orders);
		return "orders";
	}
	@PostMapping("/purchase")
	public String savePurchaseOrder(@ModelAttribute PurchaseOrderForm form,
	                                HttpServletRequest req) {
	    User user = userRepo.findById(
	        Integer.parseInt(req.getSession(false).getAttribute("userId").toString())
	    ).orElseThrow();

	    orderService.createPurchaseOrder(form, user);

	    return "redirect:/orders/orderView";
	}

	 @PostMapping("/sales")
	 public String saveSalesOrder(@ModelAttribute SalesOrderForm form,
	                              HttpServletRequest req) {

	     User user = userRepo.findById(
	         Integer.parseInt(req.getSession(false).getAttribute("userId").toString())
	     ).orElseThrow();

	     orderService.createSalesOrder(form, user);

	     return "redirect:/orders/orderView";
	 }


}
