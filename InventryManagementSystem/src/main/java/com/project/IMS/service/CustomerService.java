package com.project.IMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.IMS.entity.Customer;
@Service
public interface CustomerService {

	public List<Customer> getAllCustomers(Integer i);
	
	public void addNewCustomer(Customer customer, Integer id);
	
	public void removeExistingCustomer(Integer id);
	
	public String updateCustomerById(Integer id ,
			 Customer newCustomer);
}
