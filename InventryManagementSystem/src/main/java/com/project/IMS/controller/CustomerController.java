package com.project.IMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.IMS.entity.Customer;
import com.project.IMS.repository.CustomerRepository;
import com.project.IMS.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/Customer")
public class CustomerController {
	@Autowired
	private CustomerRepository repo;
	
	@Autowired 
	private CustomerService service;
	@GetMapping("/getCustomers")
	public String getAll(Model mod,HttpServletRequest req)
	{
		Integer userId= Integer.parseInt(req.getSession(false).getAttribute("userId").toString());
		mod.addAttribute("users", service.getAllCustomers(userId));
		return "Customers";
	}
	
	@PostMapping("/AddCustomers")
	public String addCustomers(@Valid @ModelAttribute 
			Customer cus,HttpServletRequest req) 
	{
		Integer userId= Integer.parseInt(req.getSession(false).getAttribute("userId").toString());
		
		service.addNewCustomer(cus,userId);
		return "redirect:/Customer/getCustomers";
	}
	@GetMapping("/AddCusPage")
	public String getAll(Customer cus)
	{
		return "GetCustomerInfo";
	}
	
	@PostMapping("/remove/{id}")
	public String deleteById(@PathVariable Integer id) 
	{
		service.removeExistingCustomer(id);
		return "redirect:/Customer/getCustomers";
	}
	@PostMapping("/updateCustomer")
	public String editCustomer() 
	{
		return "updateCustomers";
	}
	
	@PostMapping("/update/{id}")
	public String updateCustomerById(@PathVariable Integer id ,
			@Valid @ModelAttribute Customer newCustomer) 
	{
		service.updateCustomerById(id, newCustomer);
		 return "redirect:/Customer/getCustomers";
	}
}
