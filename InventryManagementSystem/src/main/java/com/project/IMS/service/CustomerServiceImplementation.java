package com.project.IMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.IMS.entity.Customer;
import com.project.IMS.repository.CustomerRepository;

@Service
public class CustomerServiceImplementation implements CustomerService{
	@Autowired
	CustomerRepository repo;
	
	public List<Customer> getAllCustomers(Integer id)
	{
		return repo.getAllCustomers(id);
	}

	@Override
	public void addNewCustomer(Customer customer , Integer id) {
		repo.saveCustomer(id, customer.getContactInfo(), customer.getName());
	}

	@Override
	public void removeExistingCustomer(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public String updateCustomerById(Integer id, Customer newCustomer) {
		 Customer customer = repo.findById(id).get();
		 customer.setName(newCustomer.getName());
		 customer.setContactInfo(newCustomer.getContactInfo());
		 repo.save(customer);
		return null;
	}
	
	
}
