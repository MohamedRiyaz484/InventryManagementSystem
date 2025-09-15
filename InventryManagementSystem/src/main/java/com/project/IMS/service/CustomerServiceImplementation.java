package com.project.IMS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.IMS.entity.Customer;
import com.project.IMS.repository.CustomerRepository;

@Service
public class CustomerServiceImplementation implements CustomerService{
	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	@Autowired
	CustomerRepository repo;
	
	public List<Customer> getAllCustomers(Integer id)
	{
		List<Customer> customer = repo.getAllCustomers(id);
		if(!customer.isEmpty()) 
		{
			logger.info("Customer is displayed");
		}
		else {
			logger.error("Error occcured while retrieving Customer");
		}
		return customer;
	}

	@Override
	public void addNewCustomer(Customer customer , Integer id) {
		repo.saveCustomer(id, customer.getContactInfo(), customer.getName());
	}

	@Override
	public void removeExistingCustomer(Integer id) {
		if(repo.existsById(id)) {
		logger.info("Customer {} removed successfully",repo.findById(id).get().getName());	
		repo.deleteById(id);
		}
		
	}

	@Override
	public String updateCustomerById(Integer id, Customer newCustomer) {
		 Customer customer = repo.findById(id).get();
		 if(customer == null) 
		 {
			 logger.error("customer not found");
			 return "";
		 }
		 customer.setName(newCustomer.getName());
		 customer.setContactInfo(newCustomer.getContactInfo());
		 repo.save(customer);
		return null;
	}
	
	
}
